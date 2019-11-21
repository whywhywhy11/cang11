package com.totoro.auto.testcases.smoke;

import com.alipay.auto.common.CaseInfo;
import com.totoro.auto.base.AppBaseCase;
import org.junit.Test;

/**
 * Created by grace on 2019/11/5.
 */
public class TC3003 extends AppBaseCase {
    @CaseInfo(ci = true, type = "totoro", bizline = "高德",summary = "3003",description = "搜索-一框搜poi、idq plus",author ="grace.zxj",level = "0")
    @Test
    public void TC3003(){
        baseMapPage.clickLongOnScreenRightConner();
        baseMapPage.goSearch();
        searchPage.searchByKeyword("绿茶餐厅");
        searchPage.checkInSearchResult();
    }
}
