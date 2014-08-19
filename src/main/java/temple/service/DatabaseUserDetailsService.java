package temple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import temple.dao.UserDao;
import temple.model.User;

/**
 * User: shenzhang
 * Date: 8/19/14
 * Time: 10:54 PM
 */
@Service("userDetailsService")
public class DatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByName(username);

        return null;
    }
}
