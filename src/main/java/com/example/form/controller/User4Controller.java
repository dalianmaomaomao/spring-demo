package com.example.form.controller;

import com.example.form.bean.User;
import com.example.form.bean.response.Result1;
import com.example.form.repository.User4Repository;
import com.example.form.service.User4Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by cj on 2018/8/13.
 */
@RestController
@RequestMapping("/user4")
public class User4Controller {
    @Autowired
    User4Service user4Service;
    @GetMapping("/")
    public Result1 getUser(){
        return user4Service.getUser();
    }
    @PostMapping("/")
    public Result1 createUser(@ModelAttribute User user){
        String nickName=user.getNickName();
        String pwd=user.getPwd();
        return user4Service.createUser(nickName,pwd);
    }
    @GetMapping("/{id}")
    public Result1 findByID(@PathVariable int id){
        return user4Service.findByID(id);
    }
    @GetMapping("/search/")
    public Result1 searchUser(@RequestParam String key){
       return user4Service.searchUser(key);
    }
    @PutMapping("/{id}/info")
    public Result1 updateUser(@PathVariable int id,@RequestParam String nickName){
        return user4Service.updateUser(id,nickName);
    }
    @PutMapping("/{id}/pwd")
    public Result1 modifyPwd(@PathVariable int id,@RequestParam String oldPwd,@RequestParam String newPwd){
        return user4Service.modifyPwd(id,oldPwd,newPwd);
    }
    @DeleteMapping("/{id}")
    public Result1 deleteUser(@PathVariable int id){
        return user4Service.deleteUser(id);
    }
}
