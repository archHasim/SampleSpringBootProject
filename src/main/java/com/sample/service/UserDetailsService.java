package com.sample.service;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sample.dao.UserDao;
import com.sample.exception.UserNotActivatedException;
import com.sample.model.Authority;
import com.sample.model.User;

/**
 * Class to validate authorized user.
 * 
 * @author hmolla
 *
 */
@Component("userDetailsService")
public class UserDetailsService
        implements
            org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger LOG = LoggerFactory
            .getLogger(UserDetailsService.class);

    @Autowired
    private UserDao userDao;

    /**
     * Method to fetch user data based on user name (non-Javadoc)
     * 
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String userName) {
        LOG.info("Started executing loadUserByUsername");
        LOG.debug("Authenticating {}", userName);

        User userFromDatabase;
        userFromDatabase = userDao.findByUsername(userName);

        if (userFromDatabase == null) {
            throw new UsernameNotFoundException("User " + userName
                    + " was not found in the database");
        } else if (!userFromDatabase.isActivated()) {
            throw new UserNotActivatedException("User " + userName
                    + " is not activated");
        }

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : userFromDatabase.getAuthorities()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
                    authority.getName());
            grantedAuthorities.add(grantedAuthority);
        }
        LOG.info("Finished executing loadUserByUsername");
        return new org.springframework.security.core.userdetails.User(
                userFromDatabase.getUsername(), userFromDatabase.getPassword(),
                grantedAuthorities);

    }

}
