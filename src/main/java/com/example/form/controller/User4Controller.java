package com.example.form.controller;

import com.example.form.bean.User;
import com.example.form.bean.response.Result1;
import com.example.form.repository.User4Repository;
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
    public User4Repository user4Repository;
    @GetMapping("/")
    public Result1 getUser(){
        List<User> userList=user4Repository.findAll();
        return  Result1.getSuccess("获取所有用户成功",userList);
    }
    @PostMapping("/")
    public Result1 createUser(@ModelAttribute User user){
        String nickName=user.getNickName();
        String pwd=user.getPwd();
        if(StringUtils.isEmpty(nickName)){
            return Result1.getFail(400,"您输入的昵称为空");
        }
        if(pwd.length()<6||pwd.length()>13){
            return Result1.getFail(400,"您输入的密码长度不符合规范");
        }
        Optional<User> userOptional=user4Repository.findByNickName(nickName);
        if(userOptional.isPresent()){
            return Result1.getFail(400,"您输入的昵称已存在");
        }
        user4Repository.save(user);
        return Result1.getSuccess("创建用户成功",user);
    }
    @GetMapping("/{id}")
    public Result1 findByID(@PathVariable int id){
        Optional<User> userOptional=user4Repository.findById(id);
        if(userOptional.isPresent()){
            return Result1.getSuccess("查找成功",userOptional);
        }
        return Result1.getFail(404,"查找失败");
    }
    @GetMapping("/search")
    public Result1 searchUser(@RequestParam String key){
        if(StringUtils.isEmpty(key)){
            return Result1.getSuccess("您输入的关键字为空，显示所有用户",user4Repository.findAll());
        }
        List<User> list=user4Repository.findByNickNameLikeOrPwdLike("%"+key+"%","%"+key+"%");
        return Result1.getSuccess("查找成功",list);
    }
    @PutMapping("/{id}/info")
    public Result1 updateUser(@PathVariable int id,@RequestParam String nickName){
        if(StringUtils.isEmpty(nickName)){
            return Result1.getFail(400,"您输入的昵称为空");
        }
        Optional<User> userByNickName= user4Repository.findByNickName(nickName);
        if(userByNickName.isPresent()){
            if(userByNickName.get().getId()==id){
                return Result1.getSuccess("修改成功",userByNickName);
            }
            return Result1.getFail(400,"您输入的昵称已存在");
        }
        Optional<User> userOptional=user4Repository.findById(id);
        if(userOptional.isPresent()){
            userOptional.get().setNickName(nickName);
            user4Repository.save(userOptional.get());
            return Result1.getSuccess("修改昵称成功",userOptional);
        }
        return Result1.getFail(404,"该用户不存在");
    }
    @PutMapping("/{id}/pwd")
    public Result1 modifyPwd(@PathVariable int id,@RequestParam String oldPwd,@RequestParam String newPwd){
        if(StringUtils.isEmpty(newPwd)||newPwd.length()<6||newPwd.length()>13){
            return Result1.getFail(400,"您输入的新密码不符合规范");
        }
        Optional<User> userOptional=user4Repository.findById(id);
        if(!userOptional.get().getPwd().equals(oldPwd)){
            return Result1.getFail(400,"您输入的旧密码不匹配");
        }
        userOptional.get().setPwd(newPwd);
        user4Repository.save(userOptional.get());
        return Result1.getSuccess("修改密码成功",userOptional);
    }
    @DeleteMapping("/{id}")
    public Result1 deleteUser(@PathVariable int id){
        Optional<User> userOptional=user4Repository.findById(id);
        if(userOptional.isPresent()){
            user4Repository.delete(userOptional.get());
            return Result1.getSuccess("删除用户成功",user4Repository.findAll());
        }
        return Result1.getFail(404,"该用户不存在");
    }
}
