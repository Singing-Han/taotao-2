package cn.edu.sziit.service.impl;

import cn.edu.sziit.service.TestService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class TestServiceImpl implements TestService {
    public void syaHi() {
        System.out.println("执行了 Service的 sayHi 方法~~~");
    }
}
