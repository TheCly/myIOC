package com.tryMyAnnotation.service;

import com.tryMyAnnotation.dao.UserDao;
import com.tryMyAnnotation.anno.Bean;
import com.tryMyAnnotation.anno.Di;
import com.tryMyAnnotation.bean.User;

/**
 * @author gim 2023/9/15 下午10:05
 */
@Bean
public class UserServiceImpl implements UserService {
    @Di
    private UserDao userDao ;
    @Override
    public User getUser() {
        return userDao.getUser();
    }
}
