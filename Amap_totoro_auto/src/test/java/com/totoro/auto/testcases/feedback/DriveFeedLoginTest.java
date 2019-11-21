package com.totoro.auto.testcases.feedback;

import com.alipay.auto.common.CaseInfo;
import com.totoro.auto.base.AppBaseCase;
import com.totoro.auto.util.CheckFeedBackResult;
import com.totoro.auto.util.Utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DriveFeedLoginTest extends AppBaseCase {

    private String schemeRoute = "androidamap://route?sourceApplication=softname&t=2&dev=0&dname=天安门&sname=阜荣街首开广场";
    @Before
    public void setUp() {
        super.setUp();
        // 进入导航设置页面
        routeSettingPage.goRouteSetting();
        routeSettingPage.openTrafficRestriction();
        //进入路线规划
        driveRoutePlanPage.goRoutePlan();
        //添加途经点
        driveRoutePlanPage.addViaPoint();
        driveRoutePlanPage.setStartViaEnd("shoukaiguangchang","东直门","tiananmen");
        Utils.clickOnTextIfExist("知道了");
//        Utils.click(260, 1045);
        driveRoutePlanPage.swipeUpRouteDetaill();
    }

    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "4005_0402", description = "路线规划结果详情页报错--方案绕路", author = "知末", level = "0")
    public void TC4005_0402() {
        // 进入导航设置页面
        routeSettingPage.goRouteSetting();
        routeSettingPage.openTrafficRestriction();
        //进入路线规划
        driveRoutePlanPage.goRoutePlan();
        //添加途经点
        driveRoutePlanPage.addViaPoint();
        driveRoutePlanPage.setStartViaEnd("shoukaiguangchang","东直门","tiananmen");
        Utils.clickOnTextIfExist("知道了");
//        Utils.click(260, 1045);
        driveRoutePlanPage.swipeUpRouteDetaill();
        //点击报错
        driveRoutePlanPage.stepFeedBackAtRoutePlan();
        //点击方案绕路
        routeReportError.clickPlanDetour();
        routeReportError.moveCrossingLocation();
        routeReportError.submitErrorMessage("", "");
        Assert.assertTrue("数据回传校验结果失败", CheckFeedBackResult.getResult(Utils.getDeviceId(), "4005", "0402"));

    }

    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "4001_0403", description = "路线规划结果详情页报错--道路不通", author = "知末", level = "0")
    public void TC4001_0403() {
        //点击报错
        driveRoutePlanPage.stepFeedBackAtRoutePlan();
        //点击道路不通
        routeReportError.clickRouteBlocked();
        routeReportError.moveCrossingLocation();
        routeReportError.clickConstructionRouteBlocked();
        routeReportError.submitErrorMessage("test03","");
        //routeReportError.checkRouteDetail();
        Assert.assertTrue("数据回传校验结果失败",CheckFeedBackResult.getResult(Utils.getDeviceId(),"4001","0403"));
    }

    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "5000_0404", description = "路线规划结果详情页报错--其他问题", author = "知末", level = "0")
    public void TC5000_0404() {
        //点击报错
        driveRoutePlanPage.stepFeedBackAtRoutePlan();
        //点击其他问题
        routeReportError.clickOthers();
        routeReportError.moveCrossingLocation();
        routeReportError.submitErrorMessage("test04","");
        //routeReportError.checkRouteDetail();
        Assert.assertTrue("数据回传校验结果失败",CheckFeedBackResult.getResult(Utils.getDeviceId(),"5000","0404"));
    }


}
