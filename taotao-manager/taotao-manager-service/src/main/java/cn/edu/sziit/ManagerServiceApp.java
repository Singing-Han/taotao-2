package cn.edu.sziit;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.sziit.mapper")
public class ManagerServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ManagerServiceApp.class,args);
    }
}
