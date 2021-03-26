package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.UserDao;
import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignupBusinessService {
    @Autowired
    UserDao userDao;

    @Autowired
    PasswordCryptographyProvider passwordCryptographyProvider;

    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity signup(UserEntity userEntity) throws SignUpRestrictedException {
        //Check if any existing user has same User Name or not
        UserEntity userByUserName = userDao.getUserByUserName(userEntity.getUserName());
        if (userByUserName != null) {
            throw new SignUpRestrictedException("SGR-001", "Try any other Username, this Username has already been taken");
        }

        //Check if any existing user has same Email ID or not
        UserEntity userByEmailID = userDao.getUserByEmailId(userEntity.getEmail());
        if (userByEmailID != null) {
            throw new SignUpRestrictedException("SGR-002", "This user has already been registered, try with any other emailId");
        }

        //Convert the password into the hash code
        String encryptedPassword[] = passwordCryptographyProvider.encrypt(userEntity.getPassword());
        userEntity.setSalt(encryptedPassword[0]);
        userEntity.setPassword(encryptedPassword[1]);

        //insert data into the database
        return userDao.createUser(userEntity);
    }
}
