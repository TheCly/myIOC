package com.tryMyAnnotation;

import com.tryMyAnnotation.bean.ApplicationContext;
import com.tryMyAnnotation.bean.ApplicationContextImpl;
import com.tryMyAnnotation.controller.UserController;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContextImpl("com/tryMyAnnotation");
        UserController userController = (UserController) applicationContext.getBean(UserController.class);
        userController.getUser();
    }

}

