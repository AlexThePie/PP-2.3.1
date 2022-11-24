package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);

    }

    @Override
    public List<User> listUsers() {
        List userList = entityManager.createQuery("FROM User").getResultList();
        return userList;
    }

    @Override
    public void changeUser(User user) {
        entityManager.merge(user);
        /*entityManager.createQuery("UPDATE User u SET u.firstName = :fn, u.lastName= :ln, u.email = :em where u.id = :id")
                .setParameter("fn",user.getFirstName())
                .setParameter("ln",user.getLastName())
                .setParameter("em",user.getEmail())
                .setParameter("id",user.getId())
                .executeUpdate();*/

    }

    @Override
    public void removeUser(Long id) {
        entityManager.createQuery("DELETE FROM User u WHERE u.id = :id ").setParameter("id", id).executeUpdate();
    }

    @Override
    public User findById(Long id) {
        /*User user = (User) entityManager.createQuery("FROM User u WHERE u.id = :id").setParameter("id",id).getSingleResult();
        return user;*/
        return entityManager.find(User.class, id);
    }
}
