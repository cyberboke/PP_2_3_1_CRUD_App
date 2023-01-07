package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void add(User user);

    void deleteById(long id);

    void edit(long id, User user);

    User getById(long id);

}
