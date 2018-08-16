package com.example.form.service.Impl;

import com.example.form.Mapper.UserMapper;
import com.example.form.bean.User;
import com.example.form.bean.response.Result1;
import com.example.form.repository.User4Repository;
import com.example.form.service.User4Service;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

/**
 * Created by cj on 2018/8/14.
 */
@Service
public class User4ServerImpl implements User4Service {
    //    @Autowired
//    User4Repository user4Repository;
    @Autowired
    UserMapper user4Repository;

    public Result1 getUser() {
        List<User> userList = user4Repository.findAll();
        return Result1.getSuccess("获取所有用户成功", userList);
    }

    public Result1 createUser(String nickName, String pwd) {
        if (StringUtils.isEmpty(nickName)) {
            return Result1.getFail(400, "您输入的昵称为空");
        }
        if (pwd.length() < 6 || pwd.length() > 13) {
            return Result1.getFail(400, "您输入的密码长度不符合规范");
        }
        User userByNickName = user4Repository.findByNickName(nickName);
        if (null != userByNickName) {
            return Result1.getFail(400, "您输入的昵称已存在");
        }
        User user = new User();
        user.setNickName(nickName);
        user.setPwd(pwd);
        user4Repository.save(user);
        return Result1.getSuccess("创建用户成功", user);
    }

    public Result1 findByID(int id) {
        User userOptional = user4Repository.findById(id);
        if (userOptional != null) {
            return Result1.getSuccess("查找成功", userOptional);
        }
        return Result1.getFail(404, "查找失败");
    }

    public Result1 searchUser(String key) {
        if (StringUtils.isEmpty(key)) {
            return Result1.getSuccess("您输入的关键字为空，显示所有用户", user4Repository.findAll());
        }
        List<User> userOptional = user4Repository.findByNickNameLikeOrPwdLike(key);
        return Result1.getSuccess("查找成功", userOptional);
    }

    public Result1 updateUser(int id, String nickName) {
        if (StringUtils.isEmpty(nickName)) {
            return Result1.getFail(400, "您输入的昵称为空");
        }
        User userByNickName = user4Repository.findByNickName(nickName);
        if (userByNickName != null) {
            if (userByNickName.getId() == id) {
                return Result1.getSuccess("修改成功", userByNickName);
            }
            return Result1.getFail(400, "您输入的昵称已存在");
        }
        User userOptional = user4Repository.findById(id);
        if (userOptional != null) {
            userOptional.setNickName(nickName);
           user4Repository.updateNickName(userOptional);
            return Result1.getSuccess("修改昵称成功", userOptional);
        }
        return Result1.getFail(404, "该用户不存在");
    }

    public Result1 modifyPwd(int id, String oldPwd, String newPwd) {
        if (StringUtils.isEmpty(newPwd) || newPwd.length() < 6 || newPwd.length() > 13) {
            return Result1.getFail(400, "您输入的新密码不符合规范");
        }
        User userOptional = user4Repository.findById(id);
        if (!userOptional.getPwd().equals(oldPwd)) {
            return Result1.getFail(400, "您输入的旧密码不匹配");
        }
        userOptional.setPwd(newPwd);
        user4Repository.updatePwd(userOptional);
        return Result1.getSuccess("修改密码成功", userOptional);
    }

    public Result1 deleteUser(int id) {
        User userOptional = user4Repository.findById(id);
        if (userOptional != null) {
            user4Repository.delete(id);
            return Result1.getSuccess("删除用户成功", user4Repository.findAll());
        }
        return Result1.getFail(404, "该用户不存在");
    }
}
