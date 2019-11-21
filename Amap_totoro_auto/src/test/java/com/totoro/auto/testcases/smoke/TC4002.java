package com.totoro.auto.testcases.smoke;

import com.alipay.auto.common.CaseInfo;
import com.totoro.auto.base.AppBaseCase;
import org.junit.Test;

/**
 * Created by grace on 2019/11/1.
 */
public class TC4002 extends AppBaseCase{
    @CaseInfo(ci = true, type = "totoro", bizline = "高德",summary = "4002",description = "基础地图-离线地图",author ="grace.zxj",level = "0")
    @Test
    public void TC4002(){
        baseMapPage.goMore();
        baseMapPage.clickOfflineMap();
        downloadManagePage.checkInDownloadPage();
        downloadManagePage.getIntoCityList();
        downloadManagePage.checkInCityListPage();
    }
}
