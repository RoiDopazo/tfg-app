package es.udc.rdopazo.tfg.app.model.core.usuario.impl;

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

    @Transactional
    public U add(U usuario) {
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

    public boolean authenticate(String nombre, String pass) {

        List<U> usuario = this.dao.getListByField("nombre", nombre);
        if ((!usuario.isEmpty()) && (usuario.get(0).getPassword().equals(pass))) {
            return true;
        } else {
            return false;
        }
    }

}
