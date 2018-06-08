package es.udc.rdopazo.tfg.app.model.core.usuario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import es.udc.rdopazo.tfg.app.model.core.BaseTest;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.dao.UsuarioDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.user.JpaUser;
import es.udc.rdopazo.tfg.app.util.enums.Role;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.UniqueConstraintException;

public class UsuarioServiceTest<U extends Usuario> extends BaseTest {

    @Autowired
    private UsuarioService<U> userService;

    @Autowired
    private UsuarioDao<U> userDao;

    private U insertValidUser(String username, String password) throws UniqueConstraintException {
        U user = (U) new JpaUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail("test@mail.com");
        user.setRole(Role.USER);
        return this.userService.add(user);
    }

    @Transactional
    @Test
    public void testGetAll() throws UniqueConstraintException {
        try {
            Long idUser1 = this.insertValidUser("user1", "user1").getId();
            Long idUser2 = this.insertValidUser("user2", "user2").getId();
            List<U> users = this.userService.getAll(null, null);
            assertEquals(users.size(), 2);

            users = this.userService.getAll(0, 1);
            assertEquals(users.size(), 1);
        } finally {
            this.userDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetById() throws UniqueConstraintException, InstanceNotFoundException {
        try {
            boolean exc = false;
            Long idUser1 = this.insertValidUser("user1", "user1").getId();
            Long idUser2 = this.insertValidUser("user2", "user2").getId();
            U user = this.userService.getById(idUser1);
            assertEquals(user.getId(), idUser1);

            try {
                user = this.userService.getById(0L);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertTrue(exc);
        } finally {
            this.userDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetByFieldStringObjectIntegerInteger() throws UniqueConstraintException {
        try {
            Long idUser1 = this.insertValidUser("user1", "user1").getId();
            Long idUser2 = this.insertValidUser("user2", "user2").getId();
            List<U> user = this.userService.getByField("username", "user1", null, null);
            assertEquals(user.size(), 1);
            user = this.userService.getByField("role", "USER", null, null);
            assertEquals(user.size(), 2);
            assertEquals(user.get(0).getId(), idUser1);

            user = this.userService.getByField("role", "ASASA", null, null);
            assertEquals(user.size(), 0);

            user = this.userService.getByField("", "", null, null);
            assertEquals(user.size(), 2);

        } finally {
            this.userDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testUpdate() throws UniqueConstraintException, InstanceNotFoundException {
        try {
            boolean exc = false;
            Long idUser1 = this.insertValidUser("user1", "user1").getId();
            Long idUser2 = this.insertValidUser("user2", "user2").getId();
            U user = this.userService.getById(idUser1);
            user.setEmail("test2@email.com");
            user = this.userService.update(user);
            assertEquals(user.getEmail(), "test2@email.com");

        } finally {
            this.userDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testDelete() throws UniqueConstraintException, InstanceNotFoundException {
        try {
            boolean exc = false;
            Long idUser1 = this.insertValidUser("user1", "user1").getId();
            Long idUser2 = this.insertValidUser("user2", "user2").getId();
            this.userService.delete(idUser1);
            List<U> users = this.userService.getAll(null, null);

            assertEquals(users.size(), 1);

            try {
                this.userService.delete(0L);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertTrue(exc);

        } finally {
            this.userDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testAuthenticate() throws UniqueConstraintException {
        try {
            boolean exc = false;
            Long idUser1 = this.insertValidUser("user1", "user1").getId();
            Long idUser2 = this.insertValidUser("user2", "user2").getId();
            U user = this.userService.authenticate("user1", "user1");

            assertEquals(user.getId(), idUser1);

        } finally {
            this.userDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testValidateRefreshToken() throws UniqueConstraintException, InstanceNotFoundException {
        try {
            boolean exc = false;
            Long idUser1 = this.insertValidUser("user1", "user1").getId();
            Long idUser2 = this.insertValidUser("user2", "user2").getId();
            U user = this.userService.authenticate("user1", "user1");
            this.userService.validateRefreshToken(user.getToken());

        } finally {
            this.userDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetByUsername() throws UniqueConstraintException, InstanceNotFoundException {
        try {
            boolean exc = false;
            Long idUser1 = this.insertValidUser("user1", "user1").getId();
            Long idUser2 = this.insertValidUser("user2", "user2").getId();
            U user = this.userService.getByUsername("user2");

            assertEquals(user.getId(), idUser2);

            try {
                this.userService.getByUsername("user.....");
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertTrue(exc);

        } finally {
            this.userDao.clearTable();
        }
    }

}
