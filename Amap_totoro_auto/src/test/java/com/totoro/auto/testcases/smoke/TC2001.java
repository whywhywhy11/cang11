package com.totoro.auto.testcases.smoke;

import com.alipay.auto.common.CaseInfo;
import com.totoro.auto.base.AppBaseCase;
import com.totoro.auto.misc.CommonRouteTestData;
import com.totoro.auto.misc.TestData;
import org.junit.Test;

/**
 * Created by grace on 2019/11/5.
 */
public class TC2001 extends AppBaseCase {
    @CaseInfo(ci = true, type = "totoro", bizline = "高德",summary = "2001",description = "公交-规划公交路线后切换各tab检查",author ="grace.zxj",level = "0")
    @Test
    public void TC2001(){
        baseMapPage.goRoute();
        commonRoutePage.setTrafficTypeForNew(CommonRouteTestData.routeTypeBus,false);
        commonRoutePage.searchStartPoint(TestData.shoukaiguangchang);
        commonRoutePage.searchEndPoint(TestData.langfang);
        routPlanResultPage.checkInBusResulePage();
        commonRoutePage.setTrafficTypeForNew(CommonRouteTestData.routeTypeFoot,false);
        routPlanResultPage.checkInPlanResult();
        boolean isExistRide = routPlanResultPage.checkExistRide();
        commonRoutePage.setTrafficTypeForNew(CommonRouteTestData.routeTypeRide,!isExistRide);
        routPlanResultPage.checkInPlanResult();
        commonRoutePage.setTrafficTypeForNew(CommonRouteTestData.routeTypeTrain,true);
        routPlanResultPage.checkInCTAResultPage();
        commonRoutePage.setTrafficTypeForNew(CommonRouteTestData.routeTypeCoach,true);
        routPlanResultPage.checkInCTAResultPage();

    }
}
