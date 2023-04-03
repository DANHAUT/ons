package com.edhec.commonedhecapps.service.impl;

import com.edhec.commonedhecapps.model.User;
import com.edhec.commonedhecapps.service.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @PersistenceContext
    EntityManager entityManager;

    /**
     *
     */
    private static final long serialVersionUID = 6384460058124202695L;

    //@Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {
//        List<Prospect> queryResult = entityManager.createQuery("from Prospect p where p.cntGuiId = :cntGuiId").setParameter("cntGuiId", username).getResultList();

  //      if (queryResult != null && queryResult.size() > 0) {
  //          return (Prospect) queryResult.get(0);
  //      } else { // no gui id found, trying a normal login (with email)
            User user = entityManager.find(User.class, username);
            if (user != null) {
                return user;
  //          }
        }

        return null;
    }


    public User loadByCntId(String cntId) throws UsernameNotFoundException, DataAccessException {
        User user = (User) entityManager.createQuery("from User u where u.cntId = :cntId").setParameter("cntId", cntId).getSingleResult();
        return user;
    }

    public List<User> loadListByCntId(String cntId) throws UsernameNotFoundException, DataAccessException {
        List<User> users = entityManager.createQuery("from User u where u.cntId = :cntId").setParameter("cntId", cntId).getResultList();
        return users;
    }

    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        Query query = entityManager.createQuery("FROM User u ORDER BY u.username");
        return query.getResultList();
    }

    public boolean DoesUserExist(String login) {
        boolean exist = false;
        try {
            User user = new User();
            Query query = entityManager.createQuery("FROM User u WHERE username = :login").setParameter("login", login);
            user = (User) query.getSingleResult();
            if (user != null) {
                exist = true;
            }
        } catch (NoResultException e) {
            // TODO: handle exception

        } catch (Exception e) {
            java.lang.System.out.println(e);
        }
        return exist;
    }
}
