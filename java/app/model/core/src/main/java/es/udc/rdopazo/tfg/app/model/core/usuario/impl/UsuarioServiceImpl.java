package es.udc.rdopazo.tfg.app.model.core.usuario.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.usuario.UsuarioService;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.dao.UsuarioDao;

@Service
public class UsuarioServiceImpl<U extends Usuario> implements UsuarioService<U> {

    @Autowired
    UsuarioDao<U> dao;

    public List<U> getAll() {

        return this.dao.getAll();
    }

    public U getById(Long id) {
        return this.dao.getById(id);
    }

    public List<U> getByField(String field, Object value) {
        return this.dao.getListByField(field, value);
    }

    @Transactional
    public U add(U usuario) {
        usuario.setCreationDate(new Date());
        usuario.setNombre(usuario.getNombre().toLowerCase());
        this.dao.add(usuario);
        return usuario;
    }

    @Transactional
    public U update(U usuario) {
        this.dao.update(usuario);
        return usuario;
    }

    @Transactional
    public void delete(Long id) {
        this.dao.remove(this.dao.getById(id));
    }

    public String authenticate(String nombre, String pass) {

        List<U> usuario = this.dao.getListByField("nombre", nombre.toLowerCase());
        if ((!usuario.isEmpty()) && (usuario.get(0).getPassword().equals(pass))) {
            return usuario.get(0).getRole().toString();
        } else {
            return null;
        }
    }

    public boolean evaluateToken(String nombre, String token) {
        List<U> usuario = this.dao.getListByField("nombre", nombre.toLowerCase());
        if (token.equals(usuario.get(0).getToken())) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public String setToken(String nombre, String token) {
        List<U> usuario = this.dao.getListByField("nombre", nombre.toLowerCase());
        usuario.get(0).setToken(token);
        this.update(usuario.get(0));
        return token;
    }

}
