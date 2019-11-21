package com.totoro.auto.testcases.basemap;

import com.alipay.auto.common.CaseInfo;
import com.totoro.auto.base.AppBaseCase;
import com.totoro.auto.misc.CommonRouteTestData;
import com.totoro.auto.util.Utils;
import org.junit.Test;


public class TC12616063 extends AppBaseCase {

    @CaseInfo(ci = true,type="totoro", bizline="", summary="Totoro测试用例",description="Totoro测试用例", author = "育昕", level="0")
    @Test
    public void TC12616063() {
        Utils.sleep(5*1000);
        baseMapPage.goRoute();
        commonRoutePage.setTrafficType(CommonRouteTestData.routeTypeDrive);
        drivePlainingPage.goToMaintain();
        drivePlainingPage.checkIsMaintainPage();
        drivePlainingPage.goTokillVirus();
        drivePlainingPage.checkKillVirusExistOfToast();
    }

}
