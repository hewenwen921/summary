package com.wen.controller;

import com.wen.controller.dto.UserDto;
import org.springframework.data.crossstore.HashMapChangeSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2016/6/7.
 */
@Controller
@RequestMapping("/mvc")
public class MVCParameter {

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String formSubmit(UserDto userDto) {
        System.out.println("用户名" + userDto.getUserName());
        System.out.println("密码" + userDto.getPassword());

        return "index.jsp";
    }

    //测试页面
    @RequestMapping("/test")
    public String test() {
        return "redirect:dd";
    }

    //ajax保存
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Map save(String sn, String phone) {
//        String sn=request.getParameter("sn");
        System.out.println(sn);
        System.out.println(phone);
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", "0");

        return map;
    }


    //ajax保存
    @ResponseBody
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public void json(String sn, String phone,String name) {
        System.out.println(sn);
        System.out.println(phone);
        System.out.println(name);
    }
}
