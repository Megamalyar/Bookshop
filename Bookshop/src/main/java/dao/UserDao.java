package dao;

import model.User;

import java.io.Serializable;

/**
 * Created by Malyar on 21.04.2015.
 */
public interface UserDao {

    public User getUserById(Long id);

    public User getUserByName(String name);

    public Serializable createUser(User user);
}
