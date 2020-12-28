package cn.edu.sziit;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.sziit.mapper")
public class SolrApp {
    public static void main(String[] args) {
        SpringApplication.run(SolrApp.class,args);
    }
}
