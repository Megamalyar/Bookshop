package dao;

import model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Malyar on 21.04.2015.
 */
public class UserDaoImpl implements UserDao {

    @PersistenceContext(unitName = "bookshopUnit")
    private static EntityManager em;

    private static EntityTransaction tx;

    static {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("bookshopUnit");
        System.out.println(entityManagerFactory.getClass().getSimpleName());
        em = entityManagerFactory.createEntityManager();
        System.out.println("EntityManager Created : " + em);
        tx = em.getTransaction();
    }

    @Override
    public User getUserById(Long id) {
        User user = em.find(User.class, id);
        return user;
    }

    @Override
    public User getUserByName(String name) {
        List<User> allUsers = em.createQuery(
                "SELECT a FROM User a WHERE a.userName = '"
                        + name + "'", User.class).getResultList();
        if (allUsers != null) {
            return allUsers.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Serializable createUser(User user) {
        tx.begin();
        em.persist(user);
        tx.commit();
        return user.getUserId();
    }
}
