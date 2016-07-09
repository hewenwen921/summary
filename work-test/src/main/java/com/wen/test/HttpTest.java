package com.wen.test;



import com.wen.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/1.
 */
public class HttpTest {
    public static void main(String args[]) {
        Map<String, Object> headerMap = new HashMap<String, Object>();
        Map<String, Object> parameterMap = new HashMap<String, Object>();

        headerMap.put("token", "80fae2abc2e92954c07e6b11b8c260e9");
        parameterMap.put("token", "80fae2abc2e92954c07e6b11b8c260e9");
        String url = "http://localhost:8083/web/storeAddress/searchDefault";

        Map map = HttpUtils.postJson(url, headerMap, parameterMap);

        System.out.println(map);
//        Map<String, Object> parameterMap = new HashMap<String, Object>();
//        Map<String, Object> headerMap = new HashMap<String, Object>();
//        headerMap.put("ticket","0b9883bd87746646b24938600ed7c4a4");
//        headerMap.put("token","0b9883bd87746646b24938600ed7c4a4");

//        parameterMap.put("_input_charset", "utf-8");

//        HttpUtils.post(url,parameterMap,headerMap);

    }
}
