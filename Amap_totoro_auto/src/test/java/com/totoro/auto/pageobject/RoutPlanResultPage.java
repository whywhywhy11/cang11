package com.totoro.auto.pageobject;

import com.alipay.auto.common.BaseCase;
import com.totoro.auto.misc.CommonRouteTestData;
import com.totoro.auto.misc.TestData;
import com.totoro.auto.util.Utils;

/**
 * Created by grace on 2019/11/4.
 */
public class RoutPlanResultPage extends BaseCase{
    /**
     * 点击开始导航
     */
    public void clickStartNavi(){
        Utils.clickText("开始导航");
        sleep(1000);
    }

    /**
     * check在打车页面
     */
    public void checkInTaxiPlainResultPage(){
        sleep(1000);
        Utils.checkExistOfText("现在");
    }

    /**
     * check是否在公交规划结果页
     */
    public void checkInBusResulePage(){
        sleep(2000);
        Utils.checkExistOfText("公交跨城");
    }

    /**
     * check是否在骑步行/驾车规划结果页
     */
    public void checkInPlanResult(){
        sleep(1000);
        Utils.checkExistOfText("路线详情");
    }

    /**
     * checktab中是否展示骑行
     */
    public boolean checkExistRide(){
        return Utils.waitForText(CommonRouteTestData.routeTypeRide,500);
    }

    /**
     * 是否在火汽飞规划结果页
     */
    public void checkInCTAResultPage(){
        sleep(1000);
        Utils.checkExistOfText("后一天");
    }
}
