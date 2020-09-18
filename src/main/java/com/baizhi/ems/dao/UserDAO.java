package com.baizhi.ems.dao;
import com.baizhi.ems.entity.User;

public interface UserDAO {
    //登陆的方法
    User findByUsernameAndPassword(String username, String password);

    //保存用户的方法
    void save(User user);

}
