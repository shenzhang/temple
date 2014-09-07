package temple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import temple.dao.UserDao;
import temple.model.User;

/**
 * User: shenzhang
 * Date: 8/20/14
 * Time: 9:22 PM
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    public User getUser(int id) {
        return userDao.getUserById(id);
    }

    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUserById(id);
    }

    @Transactional
    public void updateUser(int id, User user) {
        userDao.updateUserById(id, user);
    }

    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }
}
