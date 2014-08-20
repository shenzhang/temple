package temple.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import temple.dao.UserDao;
import temple.model.User;

import java.util.Collection;

import static com.google.common.collect.Lists.newArrayList;

/**
 * User: shenzhang
 * Date: 8/19/14
 * Time: 10:54 PM
 */
@Service("userDetailsService")
public class DatabaseUserDetailsService implements UserDetailsService, InitializingBean {
    @Autowired
    private UserDao userDao;

    private Collection<GrantedAuthority> authorities = newArrayList();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserById(Integer.parseInt(username));

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), true, true,
                true, true, newArrayList(authorities));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
