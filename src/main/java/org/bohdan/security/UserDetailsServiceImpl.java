package org.bohdan.security;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.UserDao;
import org.bohdan.model.Role;
import org.bohdan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final static Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);

    private final UserDao userDao;

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findEntityByLogin(s);
        logger.info("LOG: loadUserByUsername user ==> " + user.getLogin());
        return new UserSecurity(user.getLogin(), user.getPassword(),
                Collections.singletonList(Role.getAuthorities(user)), user.getStatus());
    }
}
