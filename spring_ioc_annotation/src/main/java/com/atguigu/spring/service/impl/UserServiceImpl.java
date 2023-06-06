package com.atguigu.spring.service.impl;

import com.atguigu.spring.dao.UserDao;
import com.atguigu.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Date:2022/7/2
 * Author:ybc
 * Description:
 */
@Service
public class UserServiceImpl implements UserService {

    //这种情况很有意思，备注下：此时userDaoImpl1和userDaoImpl两个实例都在容器内，但required = false，然后去容器中获取UserDao竟然可以获取到，没有报错。
    @Autowired(required = false)
    @Qualifier("userDaoImpl1")
    private UserDao userDao;

    @Override
    public void saveUser() {
        userDao.saveUser();
    }
}
