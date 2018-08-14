package com.example.form.repository;

import com.example.form.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by cj on 2018/8/12.
 */
@Repository
public interface User3Repository extends JpaRepository<User, Integer> {
     List findByNickNameLikeOrPwdLike(String nickName, String pwd);
     Optional<User> findByNickName(String nickName);
}
