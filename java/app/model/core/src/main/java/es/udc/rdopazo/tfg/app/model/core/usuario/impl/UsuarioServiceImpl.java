package es.udc.rdopazo.tfg.app.model.core.usuario.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.usuario.UsuarioService;
import es.udc.rdopazo.tfg.app.model.core.util.security.MyEncryptorService;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.dao.UsuarioDao;
import es.udc.rdopazo.tfg.app.util.enums.Role;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.UniqueConstraintException;

@Service
public class UsuarioServiceImpl<U extends Usuario> implements UsuarioService<U> {

    @Autowired
    private UsuarioDao<U> dao;

    @Autowired
    private MyEncryptorService encryptor;

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
            if (!(field.equals("")) && !(value.equals(""))) {
                return this.dao.getListByField(field, value, index, count);
            } else {
                return this.dao.getAll(index, count);
            }
        }
    }

    @Transactional
    public U add(U usuario) throws UniqueConstraintException {
        usuario.setCreationDate(new Date());
        usuario.setUsername(usuario.getUsername().toLowerCase());
        usuario.setToken(this.generateRandomRefreshToken(usuario.getUsername()));
        usuario.setPassword(this.encryptor.encrypt(usuario.getPassword()));
        if (this.dao.getListByField("username", usuario.getUsername()).size() > 0) {
            throw new UniqueConstraintException("Username", usuario.getUsername());
        } else {
            this.dao.add(usuario);
            return usuario;
        }
    }

    @Transactional
    public U update(U usuario) {
        usuario.setPassword(this.encryptor.encrypt(usuario.getPassword()));
        this.dao.update(usuario);
        return usuario;
    }

    @Transactional
    public U updateNoPass(U usuario) {
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
        String password = this.encryptor.decrypt(usuario.get(0).getPassword());
        if ((!usuario.isEmpty()) && (password.equals(pass))) {
            if (usuario.get(0).getToken() != null) {
                return usuario.get(0);
            } else {
                usuario.get(0).setToken(this.generateRandomRefreshToken(usuario.get(0).getUsername()));
                this.dao.update(usuario.get(0));
                return usuario.get(0);
            }
        } else {
            return null;
        }
    }

    private String generateRandomRefreshToken(String username) {
        Random rand = new Random();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String strToken = rand.nextInt(99999 + 1) + "-" + username + "-" + timestamp.getTime();
        String refreshToken = this.encryptor.encrypt(strToken);

        return refreshToken;
    }

    @Transactional
    public U validateRefreshToken(String refreshToken) throws InstanceNotFoundException {
        String username = this.encryptor.decrypt(refreshToken).split("-")[1];

        U user = this.getByUsername(username);
        if (user.getToken().equals(refreshToken)) {
            user.setToken(this.generateRandomRefreshToken(user.getUsername()));
            this.updateNoPass(user);
            return user;
        } else {
            user.setToken(null);
            this.updateNoPass(user);
            return null;
        }
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
