package com.totoro.auto.pageobject;

import com.alipay.auto.common.BaseCase;
import com.totoro.auto.util.Utils;

/**
 * Created by grace on 2019/11/4.
 */
public class NaviPage extends BaseCase{
    /**
     * 首次进入导航时,展示同意页面
     */
    public void clickAgree(){
        Utils.clickText("同意");
        sleep(1000);
    }
    /**
     * 判断是否在导航页
     */
    public void checkInNaviPage(){
       sleep(1000);
        Utils.checkExistOfText("退出");
    }
}
