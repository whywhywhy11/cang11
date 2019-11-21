package com.totoro.auto.util;

import com.alipay.auto.utils.LogWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.totoro.auto.base.AppBaseCase;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class HttpUtil {

    /**
     * 向指定URL发送GET方法的请求
     *
     */
    public static String get(String url) {
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
//            Map<String, String> header = getBasicAuthHeader("Get",url);
            // 打开和URL之间的连接
//            URLConnection connection = realUrl.openConnection();
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestMethod("GET"); // 设置请求方式
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            connection.setRequestProperty("Date", header.get("Date"));
//            connection.setRequestProperty("Authorization", header.get("Authorization"));
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            AppBaseCase.customLog.log("从服务请求返回结果时发生异常:" + e.toString());
            AppBaseCase.customLog.log("Exception occur when send http get request!");
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

}
