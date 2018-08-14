package com.example.form.repository;

import com.example.form.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

/**
 * Created by cj on 2018/8/5.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}
