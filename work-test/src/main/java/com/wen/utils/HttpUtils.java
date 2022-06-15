package com.wen.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Apache.HttpClient工具类
 * 对HttpClient封装
 */
public final class HttpUtils {

    private HttpUtils() {
    }

    /**
     * POST-Json请求
     *
     * @param url          请求的地址
     * @param headerMap    表示头部信息的MAP
     * @param parameterMap 表示json参数的MAP
     */
    public static Map<String, Object> postJson(String url, Map<String, Object> headerMap, Map<String, Object> parameterMap) {
        Map<String, Object> mapResult = null;
        JSONObject jsonParam = new JSONObject();

        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            if (null != parameterMap) {
                for (Iterator<String> iter = headerMap.keySet().iterator(); iter.hasNext(); ) {
                    String name = iter.next();
                    String value = headerMap.get(name).toString();
                    httpPost.setHeader(name, value);
                }
            }

            if (null != parameterMap) {
                for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
                    jsonParam.put(entry.getKey(), entry.getValue());
                }
            }

            StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");//解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            String result = EntityUtils.toString(httpEntity);
            mapResult = JSON.parseObject(result, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapResult;
    }

    /**
     * post请求
     *
     * @param url 请求的地址
     * @param headerMap 表示头部信息的MAP
     * @param parameterMap 表示参数信息的MAP
     * @return
     */
    public static Map<String, Object> post(String url, Map<String, Object> headerMap, Map<String, Object> parameterMap) {
        Assert.hasText(url);
        Map<String, Object> mapResult = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            if (null != headerMap && !headerMap.isEmpty()) {
                for (Iterator<String> iter = headerMap.keySet().iterator(); iter.hasNext(); ) {
                    String name = iter.next();
                    String value = headerMap.get(name).toString();
                    httpPost.setHeader(name, value);
                }
            }

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            if (parameterMap != null) {
                for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
                    String name = entry.getKey();
                    String value = ConvertUtils.convert(entry.getValue());
                    if (StringUtils.isNotEmpty(name)) {
                        nameValuePairs.add(new BasicNameValuePair(name, value));
                    }
                }
            }

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            String result = EntityUtils.toString(httpEntity);
            EntityUtils.consume(httpEntity);
//            System.out.println("-----------" + result);

            mapResult = JSON.parseObject(result, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mapResult;
    }


    /**
     * 发送 get请求
     */
    public static void get(String url) {

        CloseableHttpClient client = HttpClients.createDefault();
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        //get请求返回结果
        JSONObject jsonResult = null;
        try {
            nameValuePairs.add(new BasicNameValuePair("token", "0b9883bd87746646b24938600ed7c4a4"));
            nameValuePairs.add(new BasicNameValuePair("ticket", "0b9883bd87746646b24938600ed7c4a4"));

            //发送get请求
            HttpGet request = new HttpGet(url + (StringUtils.contains(url, "?") ? "&" : "?") + EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, "UTF-8")));
//            request.setHeader("Content-Type","text/json; charset=UTF-8");
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
//                response.s
                System.out.println(entity.getContentType());

                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(entity);

                System.out.println(strResult);
                /**把json字符串转换成json对象**/
//                    jsonResult = JSONObject.parseObject(strResult);
//                url = URLDecoder.decode(url, "UTF-8");
            } else {
                System.out.println("get请求提交失败:" + url);
            }

        } catch (IOException e) {
            System.out.println("get请求提交失败:" + url);
        }
//        return jsonResult;
    }
}
