package com.totoro.auto.testcases.feedback;

import com.alipay.auto.common.CaseInfo;
import com.totoro.auto.base.AppBaseCase;
import com.totoro.auto.pageobject.BusFeedPage;
import com.totoro.auto.util.CheckFeedBackResult;
import com.totoro.auto.util.Utils;
import com.totoro.client.internal.MobilePlatform;
import org.junit.Assert;
import org.junit.Test;

public class BusFeedTest extends AppBaseCase {
    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="4006_0301",description="公交规划结果详情页报错--公交方案不合理", author = "育昕", level="0")
    public void TC4006_0301() throws Exception {
        String busRouteScheme = "amapuri://route/plan?sid=BGVIS1&t=1&dlat=39.908692&dev=0&dname=天安门&slat=39.993253&dlon=116.397477&did=BGVIS2&slon=116.473195&m=0&sname=首开广场";
        caseUtils.scheme(busRouteScheme);
        Utils.waitForContainsText("分",10);
        busFeedPage.chooseAnyBusTripInList();
//        busFeedPage.swipeUpToShowBusTripMap();
        busFeedPage.clickOnFeedbackIconBottom();
        busFeedPage.busRouteUnacceptable();
        busFeedPage.clickPlanDetour();
        busFeedPage.inputQuestion();
        busFeedPage.enterPhoneNum("18811228888",0);
        busFeedPage.clickSubmit();
        busFeedPage.confirmSuccess();
        Assert.assertTrue("数据回传校验结果失败",CheckFeedBackResult.getResult(Utils.getDeviceId(),"4006","0301"));
    }

    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="4009_0307",description="公交规划结果详情页报错--步行路线错误", author = "育昕", level="0")
    public void TC4009_0307() throws Exception {
        busFeedPage.goMy();
        loginPage.login();
        busFeedPage.backToMap();
        String busRouteScheme = "amapuri://route/plan/?backScheme=http://www.taobao.com&sid=BGVIS1&slat=39.908287&slon=116.475783&sname=大望路（地铁站）&did=BGVIS2&dlat=39.909187&dlon=116.397451&dname=天安门&dev=0&m=0&t=1";
        caseUtils.scheme(busRouteScheme);// 进入路线规划结果页
//        busFeedPage.choosePlainByModeNew("步行少");
        busFeedPage.chooseFirstBusTripInList();
        busFeedPage.clickOnFeedbackIconBottom();
        busFeedPage.walkRouteError();
        busFeedPage.routeBlocked();
        busFeedPage.chooseLocation();
        busFeedPage.sureToChoosePointOnMap();
        busFeedPage.enterQuestionDes("补充详细的描述，加速审核","test02");
        busFeedPage.enterPhoneNum("18811228888",0);
        busFeedPage.clickSubmit();
        busFeedPage.confirmSuccess();
        Assert.assertTrue("数据回传校验结果失败",CheckFeedBackResult.getResult(Utils.getDeviceId(),"4009","0307"));
    }

    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="7002_0318",description="公交规划结果详情页报错--定位不准", author = "育昕", level="0")
    public void TC7002_0318() throws Exception {
        String busRouteScheme = "amapuri://route/plan/?backScheme=http://www.taobao.com&sid=BGVIS1&slat=39.908287&slon=116.475783&sname=大望路（地铁站）&did=BGVIS2&dlat=39.909187&dlon=116.397451&dname=天安门&dev=0&m=0&t=1";
        caseUtils.scheme(busRouteScheme);// 进入路线规划结果页
        busFeedPage.choosePlainByModeNew("地铁优先");
        busFeedPage.chooseFirstBusTripInList();
        busFeedPage.clickOnFeedbackIconBottom();
        busFeedPage.currentPositionError();
        busFeedPage.otherQuestion();
        busFeedPage.chooseLocation();
        busFeedPage.sureToChoosePointOnMap();
        busFeedPage.enterQuestionDes("补充详细的描述，加速审核","test05");
        busFeedPage.enterPhoneNum("18811228888",1);
        busFeedPage.clickSubmit();
        busFeedPage.confirmSuccess();
        if (MobilePlatform.IOS.equalsIgnoreCase(AppBaseCase.platform))
            Assert.assertTrue("数据回传校验结果失败",CheckFeedBackResult.getResult(Utils.getDeviceId(),"7002","0318"));
        else
            Assert.assertTrue("数据回传校验结果失败",CheckFeedBackResult.getResult(Utils.getDeviceId(),"7001","0318"));

    }

    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="3103_1411",description="公交规划结果图面页报错--车站错误", author = "育昕", level="0")
    public void TC3103_1411() throws Exception {
        String busRouteScheme = "amapuri://route/plan?sid=BGVIS1&t=1&dlat=31.249571&dev=0&dname=上海站&slat=31.193935&dlon=121.45575&did=BGVIS2&slon=121.320205&m=0&sname=上海虹桥站";
        caseUtils.scheme(busRouteScheme); // 进入路线规划结果页
        busFeedPage.choosePlainByModeNew("换乘少");
        busFeedPage.chooseFirstBusTripInList();
        busFeedPage.clickOnFeedbackIcon();
        busFeedPage.stationError();
        busFeedPage.otherQuestion();
        busFeedPage.enterQuestionDes("补充详细的描述，加速审核","test05");
        busFeedPage.enterPhoneNum("18811228888",0);
        busFeedPage.clickSubmit();
        busFeedPage.confirmSuccess();
        Assert.assertTrue("数据回传校验结果失败",CheckFeedBackResult.getResult(Utils.getDeviceId(),"3103","1411"));
    }


    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="4002_1412",description="公交规划结果图面页报错--其他问题", author = "育昕", level="0")
    public void TC4002_1412() throws Exception {
        String busRouteScheme = "amapuri://route/plan?sid=BGVIS1&t=1&dlat=31.249571&dev=0&dname=上海站&slat=31.193935&dlon=121.45575&did=BGVIS2&slon=121.320205&m=0&sname=上海虹桥站";
        caseUtils.scheme(busRouteScheme); // 进入路线规划结果页
        busFeedPage.choosePlainByModeNew("时间短");
        busFeedPage.chooseFirstBusTripInList();
        busFeedPage.clickOnFeedbackIcon();
        busFeedPage.otherQuestion();
        busFeedPage.enterQuestionDes("补充详细的描述，加速审核","test01");
        busFeedPage.enterPhoneNum("18811228888",0);
        busFeedPage.clickSubmit();
        busFeedPage.confirmSuccess();
        Assert.assertTrue("数据回传校验结果失败",CheckFeedBackResult.getResult(Utils.getDeviceId(),"4002","1412"));
    }

//    @Test
//    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="7001_1414",description="公交规划结果图面页报错--定位不准", author = "育昕", level="0")
//    public void TC7001_1414() throws Exception {
//        String busRouteScheme = "amapuri://route/plan?sid=BGVIS1&t=1&dlat=31.249571&dev=0&dname=上海站&slat=31.193935&dlon=121.45575&did=BGVIS2&slon=121.320205&m=0&sname=上海虹桥站";
//        caseUtils.scheme(busRouteScheme); // 进入路线规划结果页
//        busFeedPage.choosePlainByModeNew("地铁优先");
//        busFeedPage.chooseFirstBusTripInList();
//        busFeedPage.clickOnFeedbackIcon();
//        busFeedPage.currentPositionError();
//        busFeedPage.locationToOther();
//        busFeedPage.chooseLocation();
//        busFeedPage.sureToChoosePointOnMap();
//        busFeedPage.enterQuestionDes("补充详细的描述，加速审核","test05");
//        busFeedPage.enterPhoneNum("18811228888",1);
//        busFeedPage.clickSubmit();
//        busFeedPage.confirmSuccess();
//        Assert.assertTrue("数据回传校验结果失败",CheckFeedBackResult.getResult(Utils.getDeviceId(),"7001","1414"));
//    }

    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="4007_1601",description="步行规划结果图面页报错--方案绕路", author = "育昕", level="0")
    public void TC4007_1601() throws Exception {
        busFeedPage.goMy();
        loginPage.logoutIfLogin();
        busFeedPage.backToMap();
        busFeedPage.goRoute();
        Utils.clickOnTextIfExist("步行");
        busFeedPage.searchStartStopPointForWalk("望京SOHO", "天安门");
        busFeedPage.clickOnFeedbackIconByImg();
        busFeedPage.clickPlanDetour();
        busFeedPage.enterQuestionDes("请描述绕路位置或更好的方案","test01");
        busFeedPage.clickSubmit();
        busFeedPage.confirmSuccess();
        Assert.assertTrue("数据回传校验结果失败",CheckFeedBackResult.getResult(Utils.getDeviceId(),"4007","1601"));
    }

    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="2003_1603",description="步行规划结果图面页报错--目的地错误", author = "育昕", level="0")
    public void TC2003_1603() throws Exception {
        busFeedPage.goMy();
        loginPage.logoutIfLogin();
        busFeedPage.backToMap();
        busFeedPage.goRoute();
        //commonRoutePage.setTrafficType(CommonRouteTestData.routeTypeFoot);
        Utils.clickOnTextIfExist("步行");
        busFeedPage.searchStartStopPointForWalk("望京SOHO", "天安门");
        busFeedPage.clickOnFeedbackIconByImg();
        Utils.clickText("目的地错误");
        busFeedPage.enterQuestionDes("描述目的地现状，帮助我们加快审核", "test03");
        busFeedPage.enterPhoneNum("15800000000",0);
        busFeedPage.clickSubmit();
        busFeedPage.confirmSuccess();
        Assert.assertTrue("数据回传校验结果失败",CheckFeedBackResult.getResult(Utils.getDeviceId(),"2003","1603"));
    }


    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="2003_0503",description="步行规划结果详情页报错--目的地错误", author = "育昕", level="0")
    public void TC2003_0503() throws Exception {
        busFeedPage.goMy();
        loginPage.logoutIfLogin();
        loginPage.login();
        busFeedPage.backToMap();
        busFeedPage.goRoute();
        Utils.clickOnTextIfExist("步行");
        busFeedPage.searchStartStopPointForWalk("望京SOHO", "天安门");
        busFeedPage.clickRoadDetail();
        busFeedPage.swipeResultByTimes(10);
        busFeedPage.clickError();
        busFeedPage.clickWrongdestination();
        busFeedPage.enterQuestionDes("描述目的地现状，帮助我们加快审核", "test03");
        busFeedPage.enterPhoneNum("15800000000",0);
        busFeedPage.clickSubmit();
        busFeedPage.confirmSuccess();
        Assert.assertTrue("数据回传校验结果失败",CheckFeedBackResult.getResult(Utils.getDeviceId(),"2003","0503"));
    }
}
