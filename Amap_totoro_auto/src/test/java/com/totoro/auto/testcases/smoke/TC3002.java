package com.totoro.auto.testcases.smoke;

import com.alipay.auto.common.CaseInfo;
import com.totoro.auto.base.AppBaseCase;
import org.junit.Test;

/**
 * Created by grace on 2019/11/5.
 */
public class TC3002 extends AppBaseCase {
    @CaseInfo(ci = true, type = "totoro", bizline = "高德",summary = "3002",description = "搜索-feed流、附近搜",author ="grace.zxj",level = "0")
    @Test
    public void TC3002(){
        baseMapPage.goNear();
        searchPage.goFoodList();
        searchPage.checkInResultListPage();

    }
}
