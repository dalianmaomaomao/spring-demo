package com.example.form.repository;

import com.example.form.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

/**
 * Created by cj on 2018/8/13.
 */
public interface User4Repository extends JpaRepository<User,Integer>{
    Optional<User> findByNickNameLikeOrPwdLike(String nickName, String pwd);
    Optional<User> findByNickName(String nickName);
}
