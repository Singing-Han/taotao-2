package cn.edu.sziit.service.impl;

import cn.edu.sziit.mapper.UserMapper;
import cn.edu.sziit.pojo.User;
import cn.edu.sziit.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String doLogin(User user) throws Exception {

        User loginUser = userMapper.selectOne(user);
        if (loginUser != null) {
            return "login_success"+user.getId();
        }


        // 如果查询的用户为空，返回空
        return null;
    }
}
