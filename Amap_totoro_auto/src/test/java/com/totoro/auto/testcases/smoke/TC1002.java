package com.totoro.auto.testcases.smoke;

import com.alipay.auto.common.CaseInfo;
import com.totoro.auto.base.AppBaseCase;
import com.totoro.auto.misc.CommonRouteTestData;
import com.totoro.auto.misc.TestData;
import org.junit.Test;

/**
 * Created by grace on 2019/11/5.
 */
public class TC1002 extends AppBaseCase {
    @CaseInfo(ci = true, type = "totoro", bizline = "高德",summary = "1002",description = "打车-路线规划",author ="grace.zxj",level = "0")
    @Test
    public void TC1002(){
        baseMapPage.goRoute();
        commonRoutePage.setTrafficTypeForNew(CommonRouteTestData.routeTypeCall,false);
        commonRoutePage.searchStartPoint(TestData.shoukaiguangchang);
        commonRoutePage.searchEndPoint(TestData.soho);
        routPlanResultPage.checkInTaxiPlainResultPage();

    }
}
