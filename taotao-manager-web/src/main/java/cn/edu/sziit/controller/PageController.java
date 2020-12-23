package cn.edu.sziit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

    @RequestMapping("/{path}")
    public String page(@PathVariable(name="path")String path){
//        System.out.println("=======" + path);
        return path;
    }




}
