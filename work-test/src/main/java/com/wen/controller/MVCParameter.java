package com.wen.controller;

import com.wen.controller.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by Administrator on 2016/6/7.
 */
@RequestMapping("/param")
@Controller
public class MVCParameter {

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String formSubmit(UserDto userDto) {
        System.out.println("用户名" + userDto.getUserName());
        System.out.println("密码" + userDto.getPassword());

        return "index.jsp";
    }

}
