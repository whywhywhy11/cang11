package com.totoro.auto.testcases.smoke;

import com.alipay.auto.common.CaseInfo;
import com.totoro.auto.base.AppBaseCase;
import com.totoro.auto.misc.CommonRouteTestData;
import com.totoro.auto.misc.TestData;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by grace on 2019/11/1.
 */
public class TC1001 extends AppBaseCase {
    @CaseInfo(ci = true, type = "totoro", bizline = "高德",summary = "1001",description = "驾车-路线规划&导航",author ="grace.zxj",level = "0")
    @Test
    public void TC1001(){
        baseMapPage.goRoute();
        commonRoutePage.setTrafficTypeForNew(CommonRouteTestData.routeTypeDrive,false);
        commonRoutePage.searchStartPoint(TestData.shoukaiguangchang);
        commonRoutePage.searchEndPoint(TestData.endPointName_tiananmen);
        routPlanResultPage.clickStartNavi();
        naviPage.clickAgree();
        naviPage.checkInNaviPage();
    }
}
