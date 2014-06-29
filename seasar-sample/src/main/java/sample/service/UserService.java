package sample.service;

import sample.entity.User;

public class UserService extends AbstractService<User> {

    public User findById(Integer id) {
        return select().id(id).getSingleResult();
    }
}