package com.example.form.controller;

import com.example.form.bean.User;
import com.example.form.bean.response.Result;
import com.example.form.repository.User2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by cj on 2018/8/6.
 */
@RestController
@RequestMapping("/user2")
public class User2Controller {
    @Autowired
    private User2Repository user2Repository;
    @GetMapping("/")
    public Result getUser(){
     List<User> arrayList=user2Repository.findAll();
     return Result.getSuccess(arrayList);
    }
    @PostMapping("/")
    public Result createUser(User user){
        user2Repository.save(user);
        return Result.getResult(200,"create success",null);
    }
    @GetMapping("/{id}")
    public Result findUserByID(@PathVariable Integer id){
        try {
            User user = user2Repository.getOne(id);
            System.out.println(user);
            return Result.getResult(200,"find success",user);
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return Result.getFail(404,"search fail");
        }
    }
    @GetMapping("/search")
    public Result searchUserByIDAndPwd(@RequestParam String key){
        List<User> list=user2Repository.findByNickNameLikeOrPwdLike("%"+key+"%","%"+key+"%");
        return Result.getResult(200,"search success",list);
    }

    @PutMapping("/{id}")
    public Result updateUser(@PathVariable Integer id,@ModelAttribute User user){
        try {
            User user3=user2Repository.getOne(id);
            user3.setNickName(user.getNickName());
            user3.setPwd(user.getPwd());
            user2Repository.save(user3);
            return Result.getResult(200,"update success",user3);
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return Result.getFail(404,"user inexistent");
        }

    }
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id){
        user2Repository.deleteById(id);
        return Result.getResult(200,"delete success",null);
    }
}
