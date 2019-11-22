package com.totoro.auto.testcases.jiache;

import com.alipay.auto.common.CaseInfo;
import com.totoro.auto.base.AppBaseCase;
import com.totoro.auto.util.CheckFeedBackResult;
import com.totoro.auto.util.Utils;
import com.totoro.client.ios.IOSDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class jia1 extends AppBaseCase {

   // @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="5001_1993_1",description="长按主图规划路线进入导航", author = "海洋", level="0")
    public void TC5001_1993_1() throws Exception {

        jiache.a7();
        jiache.a6();
        jiache.a8();
        Assert.assertTrue("预期页面校验失败",jiache_check.check_daohang());

    }

    //@Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="5001_1993_2",description="长按主图规划路线", author = "海洋", level="0")
    public void TC5001_1993_2() throws Exception {

        jiache.a7();
        jiache.a7();
        jiache.a6();
        Assert.assertTrue("预期页面校验失败",jiache_check.check_guihua());

    }


   // @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="5001_1993_3",description="搜索规划路线", author = "海洋", level="0")
    public void TC5001_1993_3() throws Exception {

        jiache.a3();
        jiache.a5();
        jiache.a4();
        Assert.assertTrue("预期页面校验失败",jiache_check.check_guihua());
    }


//以上是导航类的-------------------------------------------------------------------------------------------------------------------------------------------------------------









    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="5001_1993_20",description="小德反馈", author = "海洋", level="0")
    public void TC5001_1993_20() throws Exception {

        jiache.fan_1();
        jiache.fan_11();
        Assert.assertTrue("参数检验结果为失败",jiache_check.check_fan(Utils.getDeviceId(),"1009","2805"));

    }

    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="5001_1993_21",description="小德反馈", author = "海洋", level="0")
    public void TC5001_1993_21() throws Exception {

        jiache.fan_1();
        jiache.fan_12();
        Assert.assertTrue("参数检验结果为失败",jiache_check.check_fan(Utils.getDeviceId(),"1006","2802"));

    }

    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="5001_1993_22",description="小德反馈", author = "海洋", level="0")
    public void TC5001_1993_22() throws Exception {

        jiache.fan_1();
        jiache.fan_13();
        Assert.assertTrue("参数检验结果为失败",jiache_check.check_fan(Utils.getDeviceId(),"1007","2803"));

    }

    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="5001_1993_23",description="小德反馈", author = "海洋", level="0")
    public void TC5001_1993_23() throws Exception {

        jiache.fan_1();
        jiache.fan_14();
        Assert.assertTrue("参数检验结果为失败",jiache_check.check_fan(Utils.getDeviceId(),"1012","2806"));

    }

    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="5001_1993_24",description="小德反馈", author = "海洋", level="0")
    public void TC5001_1993_24() throws Exception {

        jiache.fan_1();
        jiache.fan_15();
        Assert.assertTrue("参数检验结果为失败",jiache_check.check_fan(Utils.getDeviceId(),"1008","2804"));

    }

    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="5001_1993_25",description="小德反馈", author = "海洋", level="0")
    public void TC5001_1993_25() throws Exception {

        jiache.fan_1();
        jiache.fan_16();
        Assert.assertTrue("参数检验结果为失败",jiache_check.check_fan(Utils.getDeviceId(),"1005","2801"));

    }






}
