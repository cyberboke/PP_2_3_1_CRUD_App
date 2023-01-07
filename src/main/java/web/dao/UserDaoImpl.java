package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT c from User c", User.class);
        return query.getResultList();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteById(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void edit(long id, User user) {
        User userToBeUpdated = entityManager.find(User.class, id);
        userToBeUpdated.setName(user.getName());
        userToBeUpdated.setAge(user.getAge());
        userToBeUpdated.setEmail(user.getEmail());
        entityManager.merge(userToBeUpdated);
    }

    @Override
    public User getById(long id) {
        return entityManager.find(User.class, id);
    }
}
