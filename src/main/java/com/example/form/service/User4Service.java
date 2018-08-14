package com.example.form.service;

import com.example.form.bean.response.Result1;
import org.springframework.stereotype.Service;

/**
 * Created by cj on 2018/8/14.
 */
@Service
public interface User4Service {
    Result1 getUser();

    Result1 createUser(String nickName, String pwd);

    Result1 findByID(int id);

    Result1 searchUser(String key);

    Result1 updateUser(int id, String nickName);

    Result1 modifyPwd(int id, String oldPwd, String newPwd);

    Result1 deleteUser(int id);
}
