package com.edhec.commonedhecapps.service;

import com.edhec.commonedhecapps.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     * @throws DataAccessException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException;

    public List<User> getUsers();

    public boolean DoesUserExist(String username);

}
