package com.example.form.repository;

import com.example.form.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cj on 2018/8/6.
 */
@Repository
public interface User2Repository extends JpaRepository<User,Integer>{
    List<User> findByNickNameLikeOrPwdLike(String nickName,String pwd);
//    List<User> findByPwdLike(String pwd);

}
