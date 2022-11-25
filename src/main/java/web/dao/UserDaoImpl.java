package web.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    //private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
        //logger.info("User create complete" + user);

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
