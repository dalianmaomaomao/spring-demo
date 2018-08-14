package com.example.form.controller;

import com.example.form.bean.User;
import com.example.form.bean.response.Result1;
import com.example.form.repository.User3Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by cj on 2018/8/12.
 */
@RestController
@RequestMapping("/user3")
public class User3Controller {
    @Autowired
    public User3Repository user3Repository;

    @GetMapping("/")
    public Result1 getUser() {
        List<User> list = user3Repository.findAll();
        return Result1.getSuccess("getUser success", list);
    }

    @PostMapping("/")
    public Result1 createUser(User user) {
        String nickName = user.getNickName();
        String pwd = user.getPwd();
        if (StringUtils.isEmpty(nickName)) {
            return Result1.getFail(400, "您输入的昵称为空");
        }
        if (pwd.length() < 6 || pwd.length() > 13) {
            return Result1.getFail(400, "您输入的密码长度不符合规范");
        }
        if (user3Repository.findByNickName(nickName).isPresent()) {
            return Result1.getFail(400, "您输入的昵称已存在");
        }
        user3Repository.save(user);
        return Result1.getResult(200, "create success", user);
    }

    @GetMapping("/{id}")
    public Result1 findUserByID(@PathVariable int id) {
        Optional<User> user = user3Repository.findById(id);
        if (user.isPresent()) {
            return Result1.getSuccess("find user success", user);
        }
        return Result1.getFail(404, "find user fail");
    }

    @GetMapping("/search")
    public Result1 searchUser(@RequestParam String key) {
        if(StringUtils.isEmpty(key)){
            return Result1.getSuccess("key为空，显示所有用户信息",user3Repository.findAll());
        }
        List list = user3Repository.findByNickNameLikeOrPwdLike("%" + key + "%", "%" + key + "%");
        return Result1.getSuccess("search user success", list);
    }

    @PutMapping("/{id}/info")
    public Result1 updateUser(@PathVariable int id, @RequestParam String nickName) {
        if (StringUtils.isEmpty(nickName)) {
            return Result1.getFail(400, "the nickName is illegal");
        }
        Optional<User> u = user3Repository.findByNickName(nickName);
        if (u.isPresent()) {
            if (id == u.get().getId()) {
                return Result1.getSuccess("update success", u);
            }
            return Result1.getFail(400, "the nickName is exist");
        }
        Optional<User> userOptional = user3Repository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setNickName(nickName);
            user3Repository.save(user);
            return Result1.getSuccess("update success", userOptional);
        }
        return Result1.getFail(404, "update fail");
    }

    @PutMapping("/{id}/pwd")
    public Result1 modifyPwd(@PathVariable int id, @RequestParam String oldPwd, @RequestParam String newPwd) {
        if (StringUtils.isEmpty(newPwd) || newPwd.length() < 6 || newPwd.length() > 11) {
            return Result1.getFail(400, "the newPwd is illegal");
        }
        Optional<User> userOptional = user3Repository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (oldPwd.equals(user.getPwd())) {
                user.setPwd(newPwd);
                user3Repository.save(user);
                return Result1.getSuccess("modifyPwd success", userOptional);
            }
            return Result1.getFail(403, "oldPwd is mismatching");
        }
        return Result1.getFail(404, "this user is not exist");
    }

    @DeleteMapping("/{id}")
    public Result1 deleteUser(@PathVariable int id) {
        Optional<User> userOptional = user3Repository.findById(id);
        if (userOptional.isPresent()) {
            user3Repository.deleteById(id);
            return Result1.getSuccess("delete user success", null);
        }
        return Result1.getFail(404, "this user not exists");
    }
}
