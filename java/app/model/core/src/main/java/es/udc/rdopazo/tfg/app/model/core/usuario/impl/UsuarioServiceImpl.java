package es.udc.rdopazo.tfg.app.model.core.usuario.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.usuario.UsuarioService;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.dao.UsuarioDao;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.enums.Role;

@Service
public class UsuarioServiceImpl<U extends Usuario> implements UsuarioService<U> {

    @Autowired
    UsuarioDao<U> dao;

    public List<U> getAll(Integer index, Integer count) {

        return this.dao.getAll(index, count);
    }

    public U getById(Long id) throws InstanceNotFoundException {
        U user = this.dao.getById(id);
        if (user == null) {
            throw new InstanceNotFoundException(id, "User not found");
        }
        return user;
    }

    public List<U> getByField(String field, Object value) {
        return this.getByField(field, value, null, null);
    }

    public List<U> getByField(String field, Object value, Integer index, Integer count) {
        if (field.equals("role")) {
            try {
                String valueStr = (String) value;
                Role role = Role.valueOf(valueStr.toUpperCase());
                return this.dao.getListByField(field, role, index, count);
            } catch (Exception e) {
                return new ArrayList<U>();
            }
        } else {
            return this.dao.getListByField(field, value, index, count);
        }
    }

    @Transactional
    public U add(U usuario) {
        usuario.setCreationDate(new Date());
        usuario.setUsername(usuario.getUsername().toLowerCase());
        usuario.setToken(this.generateRandomRefreshToken(usuario.getUsername()));
        this.dao.add(usuario);
        return usuario;
    }

    @Transactional
    public U update(U usuario) {
        this.dao.update(usuario);
        return usuario;
    }

    @Transactional
    public void delete(Long id) throws InstanceNotFoundException {
        this.dao.remove(this.getById(id));
    }

    @Transactional
    public U authenticate(String nombre, String pass) {
        List<U> usuario = this.dao.getListByField("username", nombre.toLowerCase());
        if ((!usuario.isEmpty()) && (usuario.get(0).getPassword().equals(pass))) {
            usuario.get(0).setToken(this.generateRandomRefreshToken(usuario.get(0).getUsername()));
            this.dao.update(usuario.get(0));
            return usuario.get(0);
        } else {
            return null;
        }
    }

    private String generateRandomRefreshToken(String username) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("mySecret");
        Random rand = new Random();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String strToken = rand.nextInt(99999 + 1) + "-" + username + "-" + timestamp.getTime();
        String refreshToken = encryptor.encrypt(strToken);

        return refreshToken;
    }

    @Transactional
    public U validateRefreshToken(String refreshToken) throws InstanceNotFoundException {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("mySecret");
        String username = encryptor.decrypt(refreshToken).split("-")[1];

        U user = this.getByUsername(username);
        if (user.getToken().equals(refreshToken)) {
            user.setToken(this.generateRandomRefreshToken(user.getUsername()));
            this.update(user);
            return user;
        } else {
            return null;
        }
    }

    public boolean evaluateToken(String nombre, String token) {
        List<U> usuario = this.dao.getListByField("username", nombre.toLowerCase());
        if (token.equals(usuario.get(0).getToken())) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public String setToken(String nombre, String token) {
        List<U> usuario = this.dao.getListByField("username", nombre.toLowerCase());
        usuario.get(0).setToken(token);
        this.update(usuario.get(0));
        return token;
    }

    public U getByUsername(String username) throws InstanceNotFoundException {
        List<U> user = this.dao.getListByField("username", username);
        if (user.size() > 0) {
            return user.get(0);
        } else {
            throw new InstanceNotFoundException(username, "User with username not found");
        }
    }

}
