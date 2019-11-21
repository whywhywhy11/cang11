package com.totoro.auto.testcases.smoke;

import com.alipay.auto.common.CaseInfo;
import com.totoro.auto.base.AppBaseCase;
import org.junit.Test;

/**
 * Created by grace on 2019/11/5.
 */
public class TC3001 extends AppBaseCase{
    @CaseInfo(ci = true, type = "totoro", bizline = "高德",summary = "3001",description = "搜索-主页热词泛搜、poi详情页",author ="grace.zxj",level = "0")
    @Test
    public void TC3001(){
        baseMapPage.clickLongOnScreenRightConner();
        baseMapPage.goSearch();
        baseMapPage.clickHotel();
        searchPage.clickItem();
        searchPage.checkInPOIpage();

    }

}
