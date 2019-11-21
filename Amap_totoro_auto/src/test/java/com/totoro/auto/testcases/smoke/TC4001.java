package com.totoro.auto.testcases.smoke;

import com.alipay.auto.common.CaseInfo;
import com.totoro.auto.base.AppBaseCase;
import com.totoro.auto.misc.CommonRouteTestData;
import com.totoro.auto.pageobject.CommonRoutePage;
import org.junit.Test;

/**
 * Created by grace on 2019/10/30.
 */
public class TC4001 extends AppBaseCase{
    @CaseInfo(ci = true, type = "totoro", bizline = "高德",summary = "4001",description = "基础地图-车辆设置",author ="grace.zxj",level = "0")
    @Test
    public void TC4001(){
        baseMapPage.goRoute();
        commonRoutePage.setTrafficTypeForNew(CommonRouteTestData.routeTypeDrive,false);
        drivePlainingPage.carSetting();
        carSettingPage.checkInAddCarPage();

    }
}
