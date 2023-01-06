package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void add(User user);

    void deleteById(User userId);

    void edit(User user);

    User getById(long id);
}
