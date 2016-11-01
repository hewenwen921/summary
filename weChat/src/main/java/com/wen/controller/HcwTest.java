package com.wen.controller;

import com.wen.utils.wechat.SignUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 测试接口
 */
@Controller
@RequestMapping("/test")
public class HcwTest {

    /**
     * 开发者接入，填写服务器配置
     * @param request
     * @param response
     */
    @RequestMapping("verify")
    public void verify(HttpServletRequest request, HttpServletResponse response) {
        String signature = request.getParameter("signature");
        String echostr = request.getParameter("echostr");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");

        PrintWriter out = null;
        try {
            out = response.getWriter();
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败
            if (SignUtil.checkSignature("wen", signature, timestamp, nonce)) {
                out.print(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
            out = null;
        }
    }
}
