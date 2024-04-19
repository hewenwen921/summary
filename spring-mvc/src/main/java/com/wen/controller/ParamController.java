package com.wen.controller;


import com.wen.controller.dto.UserDto;
import com.wen.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 各种请求参数的记录
 */
@Controller
@RequestMapping("/mvc")
public class ParamController {

    /**
     * 打开param页面
     * @return
     */
    @RequestMapping("/param")
    public String param(){
        return "param";
    }


    @ResponseBody
    @RequestMapping(value = "/object", method = RequestMethod.POST)
    public String formSubmit(List<UserDto> userDtoList) {
//        System.out.println("用户名" + userDto.getUserName());
//        System.out.println("密码" + userDto.getPassword());

        return "ok";
    }


    //ajax保存
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public User save(String sn, String phone) {
////        String sn=request.getParameter("sn");
        System.out.println(sn);
        System.out.println(phone);
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", "0");
        User user=new User();
        user.setName("wenwen");

        return user;
    }


    //ajax保存
    @ResponseBody
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public void json(String sn, String phone, String name) {
        System.out.println(sn);
        System.out.println(phone);
        System.out.println(name);
    }
}

