package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    /*
    * gives the user form the database on the basis of the userName
    *
    * @param userName user name to be searched
    *
    * @return user details if found else null
    * */
    public UserEntity getUserByUserName(final String userName) {
        try {
            return entityManager.createNamedQuery("userByUserName", UserEntity.class).
                    setParameter("userName", userName).getSingleResult();
        }
        catch (NoResultException nre) {
            return null;
        }
    }

    /*
     * gives the user form the database on the basis of the Email ID
     *
     * @param email email id to be searched
     *
     * @return user details if found else null
     * */
    public UserEntity getUserByEmailId(final String email) {
        try {
            return entityManager.createNamedQuery("userByEmailId", UserEntity.class).
                    setParameter("email", email).getSingleResult();
        }
        catch (NoResultException nre) {
            return null;
        }
    }

    /*
    * insert the user in the database
    *
    * @param userEntity user details to be inserted
    *
    * @return userEntity user which got inserted
    * */
    public UserEntity createUser(UserEntity userEntity) {
        entityManager.persist(userEntity);
        return userEntity;
    }
}
