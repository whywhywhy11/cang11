package com.totoro.auto.pageobject;

import com.totoro.auto.util.Utils;
import junit.framework.Assert;

public class DrivePlainingPage extends BaseMapPage {
    //上门保养
    String routeToolMaintain = "上门保养";
    String text_jiyou = "机滤套餐";
    String text_order = "订单";
    String text_recommond = "人气推荐";
    String text_firstMaintain = "小保养";
    String killVirus = "车内除菌";
    String text_routeToolMore = "更多";
    String ongoing = "数据完善中";
    String text_carSetting = "车辆设置";

    public void goToMaintain() {
        Utils.clickText(text_routeToolMore);
        Utils.clickText(routeToolMaintain);
    }

    //上门保养页面
    public void checkIsMaintainPage() {

        Assert.assertTrue("未找到机滤套餐", Utils.waitForText(text_jiyou, 2));
        Assert.assertTrue("未找到订单", Utils.waitForText(text_order, 2));
        Assert.assertTrue("未找到人气推荐", Utils.waitForText(text_recommond, 2));
        Assert.assertTrue("未找到小保养", Utils.waitForText(text_firstMaintain, 2));
    }

    //车内除菌
    public void goTokillVirus() {
        Utils.clickText(killVirus);
    }

    public void checkKillVirusExistOfToast() {
        Utils.checkExistOfToast(ongoing);
    }

    //进入车辆设置页面
    public void carSetting(){
        Utils.clickText(text_carSetting);
    }

}
