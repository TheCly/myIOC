package com.tryMyAnnotation.dao;

import com.tryMyAnnotation.anno.Bean;
import com.tryMyAnnotation.anno.Di;
import com.tryMyAnnotation.bean.User;

/**
 * @author gim 2023/9/15 下午10:05
 */
@Bean
public class UserDaoImpl implements UserDao {
    @Di
    private User user;
    @Override
    public User getUser() {
        return user;
    }
}
