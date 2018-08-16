package com.example.form.Mapper;

import com.example.form.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by cj on 2018/8/15.
 */
@Mapper
public interface UserMapper {
    @Results({
            @Result(property = "nickName", column = "nick_name")
    })
    @Select("SELECT * FROM USER")
    List<User> findAll();

    @Results({
            @Result(property = "nickName", column = "nick_name")
    })
    @Select("SELECT * FROM USER WHERE nick_name = #{nickName}")
    User findByNickName(@Param("nickName") String nickName);

    @Results({
            @Result(property = "nickName", column = "nick_name")
    })
    @Select("SELECT * FROM USER WHERE id = #{id}")
    User findById(@Param("id") int id);

    @Results({
            @Result(property = "nickName", column = "nick_name")
    })
    @Insert("INSERT INTO USER(nick_name, pwd) VALUES(#{nickName}, #{pwd})")
    int save(User user);

    @Results({
            @Result(property = "nickName", column = "nick_name")
    })
    @Select("SELECT * FROM USER WHERE nick_name LIKE CONCAT('%',#{key},'%') OR pwd LIKE CONCAT('%',#{key},'%')")
    List<User> findByNickNameLikeOrPwdLike(@Param("key") String key);

    @Delete("DELETE FROM USER WHERE id = #{id}")
    User delete(@Param("id") int id);

    @Results({
            @Result(property = "nickName", column = "nick_name")
    })
    @Update("update user set nick_name=#{nickName} where id=#{id}")
    int updateNickName(User user);

    @Results({
            @Result(property = "nickName", column = "nick_name")
    })
    @Update("update user set pwd=#{pwd} where id=#{id}")
    int updatePwd(User user);
}

