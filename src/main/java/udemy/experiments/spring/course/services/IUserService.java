package udemy.experiments.spring.course.services;

import udemy.experiments.spring.course.models.User;

public interface IUserService {
    Iterable<User> getUsers();
    boolean userExists(Long id);
    User getUser(Long id);
    void addUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
}
