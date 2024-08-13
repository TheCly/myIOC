package com.tryMyAnnotation.controller;

import com.tryMyAnnotation.anno.Bean;
import com.tryMyAnnotation.anno.Di;
import com.tryMyAnnotation.service.UserService;

/**
 * @author gim 2023/9/15 下午10:04
 */
@Bean
public class UserController {
    @Di
   private UserService userService;
    public void getUser(){
        userService.getUser().print();
    }
}
