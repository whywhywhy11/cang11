package com.totoro.auto.pageobject;

import com.alipay.auto.common.BaseCase;
import com.totoro.auto.util.CmdUtil;
import com.totoro.auto.util.Utils;

public class DriveFeeNaviEndPage extends BaseCase {


    private String discrete_seek_bar_dsb_seek_bar = "com.amap.mockgps:id/discrete_seek_bar_dsb_seek_bar";

    protected int overallTimeout = 5000;

    public void startGps(String scheme) {
        caseUtils.scheme(scheme);

        if(Utils.waitForViewByID(discrete_seek_bar_dsb_seek_bar, overallTimeout, "校验是否进如mockGps页面")) {
            Utils.swipe(49, 1694, 1000, 1694, overallTimeout, 1);
        }
    }

    public void closeMockGps() {
        CmdUtil.run("am force-stop com.amap.mockgps");
    }
    public void exitNavi() {
        Utils.clickOnTextIfExist("退出");
        sleep(600);
        Utils.clickOnTextIfExist("退出导航");
    }

}
