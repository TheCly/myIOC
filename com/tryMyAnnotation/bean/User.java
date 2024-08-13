package com.tryMyAnnotation.bean;

import com.tryMyAnnotation.anno.Bean;

/**
 * @author gim 2023/9/15 下午10:03
 */
@Bean
public class User {
    private int id;
    private String name;
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public void print(){
        System.out.println("HelloWord");
    }
}
