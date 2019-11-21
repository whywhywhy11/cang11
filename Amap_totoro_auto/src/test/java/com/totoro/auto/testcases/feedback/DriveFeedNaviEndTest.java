package com.totoro.auto.testcases.feedback;

import com.alipay.auto.common.CaseInfo;
import com.totoro.auto.base.AppBaseCase;
import com.totoro.auto.util.CheckFeedBackResult;
import com.totoro.auto.util.CmdUtil;
import com.totoro.auto.util.Utils;
import org.junit.Assert;
import org.junit.Test;

public class DriveFeedNaviEndTest extends AppBaseCase {
    private String mockGpsScheme = "mockuri://route/drive?slat=39.993253&slon=116.473195&sname=首开广场&dlat=40.001083&dlon=116.486790&dname=绿地中心B座&dev=0&m=0&t=2&showResult=0";
    private String routeScheme = "amapuri://route/plan?sid=BGVIS1&t=0&dlon=116.48215&dlat=39.99599&slat=39.993269&did=BGVIS2&slon=116.473182&m=0";

    @Override
    public void setUp() {
        super.setUp();
        CmdUtil.run("am force-stop com.amap.mockgps");
        routeSettingPage.goRouteSetting();
        routeSettingPage.openTrafficRestriction();
        driveRoutePlanPage.goRoutePlan();
        driveRoutePlanPage.addViaPoint();
        driveRoutePlanPage.setStartViaEnd("首开广场","望京伯爵城中央公园","绿地中心B座(启阳路)");
        Utils.clickOnTextIfExist("知道了");
        driveRoutePlanPage.startNaving();
        driveFeeNaviEndPage.startGps(mockGpsScheme);
        sleep(70 * 1000);
        driveFeeNaviEndPage.closeMockGps();
        sleep(3000);
        driveFeeNaviEndPage.exitNavi();
        sleep(4000);
        driveEndPage.clickReportError();
    }

    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "4005_0602", description = "导航结束报错--方案绕路", author = "知末", level = "0")
    public void TC4005_0602() {
        naviEndReportErrorPage.clickPlanDetour();
        routeReportError.moveCrossingLocation();
        naviEndReportErrorPage.submitErrorMessage("test10", "");
        sleep(10000);
        Assert.assertTrue("数据回传校验结果失败", CheckFeedBackResult.getResult(Utils.getDeviceId(), "4005", "0602"));

    }

    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "4001_0603", description = "导航结束报错--道路不通", author = "知末", level = "0")
    public void TC4001_0603() {
        naviEndReportErrorPage.clickRouteBlocked();
        routeReportError.moveCrossingLocation();
        naviEndReportErrorPage.clickForbiddenTrans();
        naviEndReportErrorPage.submitErrorMessage("test11", "");
        sleep(10000);
        Assert.assertTrue("数据回传校验结果失败", CheckFeedBackResult.getResult(Utils.getDeviceId(), "4001", "0603"));

    }

    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "6001_0604", description = "导航结束报错--摄像头位置有误", author = "知末", level = "0")
    public void TC6001_0604() {
        naviEndReportErrorPage.eleCameraError();
        routeReportError.moveCrossingLocation();
        naviEndReportErrorPage.submitErrorMessage("test12", "");
        sleep(10000);
        Assert.assertTrue("数据回传校验结果失败", CheckFeedBackResult.getResult(Utils.getDeviceId(), "6001", "0604"));
    }

    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "6002_0605", description = "导航结束报错--限速提示有误", author = "知末", level = "0")
    public void TC6002_0605() {
        naviEndReportErrorPage.speedLimitError();
        routeReportError.moveCrossingLocation();
        naviEndReportErrorPage.submitErrorMessage("test13", "");
        sleep(10000);
        Assert.assertTrue("数据回传校验结果失败", CheckFeedBackResult.getResult(Utils.getDeviceId(), "6002", "0605"));

    }
    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "5008_0606", description = "导航结束报错--限速提示有误", author = "知末", level = "0")
    public void TC5008_0606() {
        naviEndReportErrorPage.illegalOperation();
        routeReportError.moveCrossingLocation();
        naviEndReportErrorPage.submitErrorMessage("test14", "");
        sleep(10000);
        Assert.assertTrue("数据回传校验结果失败", CheckFeedBackResult.getResult(Utils.getDeviceId(), "5008", "0606"));
    }

}
