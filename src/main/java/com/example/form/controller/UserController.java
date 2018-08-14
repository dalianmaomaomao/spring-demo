package com.example.form.controller;

import com.example.form.bean.User;
import com.example.form.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by cj on 2018/8/5.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    static Map<Integer,User> map= new HashMap<>();
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/")
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    @PostMapping("/")
    public String createUser(@ModelAttribute User user){
        //map.put(user.getId(),user);
        userRepository.save(user);
        return "success";
    }
    @GetMapping("/{id}")
    public User searchUserbyId(@PathVariable int id){
        System.out.println("searchUserbyId"+id);
        userRepository.findById(id);
        return map.get(id);
    }
    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id,@ModelAttribute User user){
        map.remove(id);
        map.put(id,user);
        System.out.println("id"+id+","+user.getId()+","+user.getNickName());
        User user1=userRepository.getOne(id);
        user1.setNickName(user.getNickName());
//        user1.setId(user.getId());
        userRepository.save(user1);


        return "success";
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        //map.remove(id);
        userRepository.deleteById(id);
        return "success";
    }
}
