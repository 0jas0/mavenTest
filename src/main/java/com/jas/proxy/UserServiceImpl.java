package com.jas.proxy;

/**
 * Created by Administrator on 2017/11/7.
 */
public class UserServiceImpl implements UserService {
    @Override
    public String getName() {
        return "七月与安生";
    }

    @Override
    public int getAge() {
        return 18;
    }
}
