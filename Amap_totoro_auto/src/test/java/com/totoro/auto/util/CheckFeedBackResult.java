package com.totoro.auto.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.auto.utils.LogWriter;
import com.totoro.auto.base.AppBaseCase;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CheckFeedBackResult {
    private Map<String, String> params = new HashMap<>();
    private static String baseUrl = "http://qa.client.gaode.test/HuiChuan/getOneResult";

    public CheckFeedBackResult(String diu, String typeId, String errorId) {
        params.put("uid", diu);
        params.put("typeid", typeId);
        params.put("errorid", errorId);
//        logUtil = LogUtils.getInstance();
    }

    public static Boolean getResult(String diu, String typeId, String errorId) {
        String request = baseUrl + "?uid=" + diu + "&typeid=" + typeId + "&errorid=" + errorId;
        String response = HttpUtil.get(request);
        if (response == null) {
            return false;
        }
        JSONObject object = JSON.parseObject(response);

        if (response.trim().contains("AOS的数据库中暂时还没有该条数据")) {
            AppBaseCase.customLog.log("校验未通过。AOS返回结果:" + response);
            AppBaseCase.customLog.log("请求参数:" + request);
            return false;
        }
        JSONArray result = object.getJSONArray("Data");
        JSONObject vailds = result.getJSONObject(0);
        String valid = vailds.getString("valid");
        String info = vailds.getString("Info");
        JSONObject detail = vailds.getJSONObject("detail");
        String created_at = detail.getString("created_at");
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.parse(created_at);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long startTime=date.getTime();//获取到上报时间
        long endTime=System.currentTimeMillis(); //获取当前时间
        Long s = (endTime - startTime) / (1000 * 60);
        if (s > 5){//超过5分钟，证明该数据不是本次上报
            AppBaseCase.customLog.log("校验未通过。近五分钟未获取到用反上报数据，请确认上报id与校验id是否一致");
            AppBaseCase.customLog.log("请求参数:" + request);
            return false;
        }
        if(valid.equals("false")) {
            AppBaseCase.customLog.log("校验未通过。AOS返回结果:" + info);
            return false;
        } else {
            AppBaseCase.customLog.log("校验通过。AOS返回结果:" + response);
            return true;
        }
    }
}
