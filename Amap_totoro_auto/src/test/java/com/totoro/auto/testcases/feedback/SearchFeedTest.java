package com.totoro.auto.testcases.feedback;

import com.alipay.auto.common.CaseInfo;
import com.totoro.auto.base.AppBaseCase;
import com.totoro.auto.pageobject.SearchFeedPage;
import com.totoro.auto.util.CheckFeedBackResult;
import com.totoro.auto.util.Utils;
import com.totoro.client.internal.MobilePlatform;
import org.junit.Assert;
import org.junit.Test;


/**
 * @author beishuo
 * @version V1.0
 * @Title: SearchFeedTest.java
 * @Package com.totoro.auto.testcases.feedback
 * @Description: (信息服务 - 搜索反馈 - 无离线数据)
 * @date 2019/10/29 12:39
 */
public class SearchFeedTest extends AppBaseCase {


    @Override
    public void setUp() {
        super.setUp();
        String downloadScheme = "";
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            downloadScheme = "androidamap://openFeature?featureName=enlargement&sourceApplication=AmapPush-792";
        } else {
            downloadScheme = "iosamap://openFeature?featureName=OfflineMap&sourceApplication=flash";
        }
        caseUtils.scheme(downloadScheme);
        downloadManagePage.deleteOfflineData("北京市");
        searchFeedPage.goMy();
        loginPage.login();
        searchFeedPage.backToMap();
    }


    //车站POI ------------------ START-------------------------------------

    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "3103_1805", description = "车站POI：途径线路错误页_地图线路缺失提交反馈", author = "北朔", level = "0")
    public void TC3103_1805() {

        String scheme = "amapuri://poi/detail?poiname=沙河(地铁站)&lon=116.288865&lat=40.148278";
        caseUtils.scheme(scheme);
        searchFeedPage.goToFeedback();
        searchFeedPage.clickText(SearchFeedPage.INCORRECTROUTE);
        searchFeedPage.clickText(SearchFeedPage.MISSINGBUSLINE);
        searchFeedPage.enterTextView("补充详细的描述，加速审核", "test2", 1, "问题描述");
        searchFeedPage.addPicture("上传车站站牌，现场等照片，帮助我们加快审核");
        searchFeedPage.enterTextField("方便我们联系您核实情况", "15800000000", 0, "您的联系电话");
        searchFeedPage.clickSubmit("提交");
        searchFeedPage.confirmSuccess();
        Assert.assertTrue(SearchFeedPage.data_check_fail, CheckFeedBackResult.getResult(Utils.getDeviceId(), "3103", "1805"));
    }

    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "3103_1806", description = "车站POI：车站POI：途径线路错误页_页面交互验证_地图线路多余提交反馈", author = "北朔", level = "0")
    public void TC3103_1806() {

        String scheme = "amapuri://poi/detail?poiname=西直门南(公交站)&lon=116.355743&lat=39.936214";
        caseUtils.scheme(scheme);
        searchFeedPage.goToFeedback();
        searchFeedPage.clickText(SearchFeedPage.INCORRECTROUTE);
        searchFeedPage.clickText(SearchFeedPage.REDUNDANTBUSLINE);
        searchFeedPage.swipeByTimes(0.5, 0.7, 0.5, 0.3, 1000, 1);
        //searchFeedPage.clickText("请选择问题线路");
        searchFeedPage.clickTextFieldByIndex("请选择问题线路", 0);
        searchFeedPage.clickText("44路内环");
        searchFeedPage.clickText("确定");
        searchFeedPage.enterTextView("补充详细的描述，加速审核", "test1", 1, "问题描述");
        searchFeedPage.addPicture("上传车站站牌，现场等照片，帮助我们加快审核");
        searchFeedPage.enterTextField("方便我们联系您核实情况", "15800000000", 1, "您的联系电话");
        searchFeedPage.clickSubmit("提交");
        searchFeedPage.confirmSuccess();
        Assert.assertTrue(SearchFeedPage.data_check_fail, CheckFeedBackResult.getResult(Utils.getDeviceId(), "3103", "1806"));

    }


    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "3104_1803", description = "车站POI：车站错误页_页面交互验证_车站位置错误提交反馈", author = "北朔", level = "0")
    public void TC3104_1803() {

        String scheme = "amapuri://poi/detail?poiname=东直门枢纽站(公交站)&lon=116.438904&lat=39.942814";
        caseUtils.scheme(scheme);
        searchFeedPage.goToFeedback();
        searchFeedPage.clickText(SearchFeedPage.INCORRECTSTATION);
        searchFeedPage.clickText(SearchFeedPage.STATIONPOSITIONINCORRECT);
        searchFeedPage.clickTextFieldByIndex("请选择问题车站所在线路", 0);
        searchFeedPage.clickText("106路");
        searchFeedPage.clickText("确定");
        searchFeedPage.clickText("点击编辑所在位置");
        searchFeedPage.swipeByTimes(0.5, 0.5, 0.5, 0.3, 1000, 1);
        searchFeedPage.clickText("确认选点");
        searchFeedPage.swipeByTimes(0.5, 0.7, 0.5, 0.3, 1000, 1);
        searchFeedPage.enterTextView("请描述车站存在的问题", "test1", 1, "问题描述");
        searchFeedPage.addPicture("上传车站站牌，现场等照片，帮助我们加快审核");
        searchFeedPage.enterTextField("方便我们联系您核实情况", "15800000000", 1, "您的联系电话");
        searchFeedPage.clickSubmit("提交");
        searchFeedPage.confirmSuccess();
        Assert.assertTrue(SearchFeedPage.data_check_fail, CheckFeedBackResult.getResult(Utils.getDeviceId(), "3104", "1803"));

    }


    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "3000_1808", description = "车站POI：其他问题页_提交反馈", author = "北朔", level = "0")
    public void TC3000_1808() {

        String scheme = "amapuri://poi/detail?poiname=望京西(地铁站)&lon=116.449884&lat=39.995724";
        caseUtils.scheme(scheme);
        searchFeedPage.goToFeedback();
        searchFeedPage.clickText(SearchFeedPage.OTHERS);
        searchFeedPage.enterTextView("补充详细的描述，加速审核", "test1", 1, "问题描述");
        searchFeedPage.addPicture("上传问题相关照片，加快审核");
        searchFeedPage.enterTextField("方便我们联系您核实情况", "15800000000", 0, "您的联系电话");
        searchFeedPage.clickSubmit("提交");
        searchFeedPage.confirmSuccess();
        Assert.assertTrue(SearchFeedPage.data_check_fail, CheckFeedBackResult.getResult(Utils.getDeviceId(), "3000", "1808"));

    }

    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "3103_1802", description = "车站POI：车站错误页_页面交互验证_车站名称错误提交反馈", author = "北朔", level = "0")
    public void TC3103_1802() {

        String scheme = "amapuri://poi/detail?poiname=望京西(地铁站)&lon=116.449884&lat=39.995724";
        caseUtils.scheme(scheme);
        searchFeedPage.goToFeedback();
        searchFeedPage.clickText(SearchFeedPage.INCORRECTSTATION);
        searchFeedPage.clickText(SearchFeedPage.STATIONNAMEINCORRECT);
        searchFeedPage.clickTextFieldByIndex("请选择问题车站所在线路", 0);
        searchFeedPage.clickText("13号线");
        searchFeedPage.clickText("确定");
        searchFeedPage.enterTextField("请输入正确的名称", "东直门站", 1, "正确名称");
        searchFeedPage.swipeByTimes(0.5, 0.7, 0.5, 0.3, 1000, 1);
        searchFeedPage.enterTextView("请描述车站存在的问题", "test1", 1, "问题描述");
        searchFeedPage.addPicture("上传车站站牌，现场等照片，帮助我们加快审核");
        searchFeedPage.enterTextField("方便我们联系您核实情况", "15800000000", 2, "您的联系电话");
        searchFeedPage.clickSubmit("提交");
        searchFeedPage.confirmSuccess();
        Assert.assertTrue(SearchFeedPage.data_check_fail, CheckFeedBackResult.getResult(Utils.getDeviceId(), "3103", "1802"));

    }

    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "3102_1801", description = "车站POI：车站错误-提交反馈-找不到车站", author = "北朔", level = "0")
    public void TC3102_1801() {

        String scheme = "amapuri://poi/detail?poiname=广顺南大街北口(公交站)&lon=116.471786&lat=39.99157";
        caseUtils.scheme(scheme);
        searchFeedPage.goToFeedback();
        searchFeedPage.clickText(SearchFeedPage.INCORRECTSTATION);
        searchFeedPage.clickText(SearchFeedPage.CANNOTFINDSTATION);
        searchFeedPage.clickTextFieldByIndex("请选择问题车站所在线路", 0);
        searchFeedPage.clickText("131路");
        searchFeedPage.clickText("确定");
        searchFeedPage.swipeByTimes(0.5, 0.7, 0.5, 0.3, 1000, 1);
        searchFeedPage.enterTextView("请描述车站存在的问题", "test1", 1, "问题描述");
        searchFeedPage.addPicture("上传车站站牌，现场等照片，帮助我们加快审核");
        searchFeedPage.enterTextField("方便我们联系您核实情况", "15800000000", 1, "您的联系电话");
        searchFeedPage.clickSubmit("提交");
        searchFeedPage.confirmSuccess();
        Assert.assertTrue(SearchFeedPage.data_check_fail, CheckFeedBackResult.getResult(Utils.getDeviceId(), "3102", "1801"));

    }

    //车站POI ------------------ END-------------------------------------


    //失效POI ------------------ START-------------------------------------

    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "2010_3201", description = "失效POI：提交反馈-修改营业状态-正常营业", author = "北朔", level = "0")
    public void TC2010_3201() {
        //TODO 最后编写 失效POI 用例

    }

    //失效POI ------------------ END---------------------------------------


    //普通POI ------------------ START-------------------------------------

    //TODO 自动化API有问题
    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "2003_0006", description = "普通POI：提交反馈-名称、电话等错误", author = "北朔", level = "0")
    public void TC2003_0006() {

        String scheme = "amapuri://search/around?keywords=首开广场&lon=116.473195&lat=39.993253";
        caseUtils.scheme(scheme);
        searchFeedPage.clickSoukaiSearch();
        searchFeedPage.goToFeedback();
        searchFeedPage.clickText("电话、名称等错误");
        searchFeedPage.enterText("首开广场（修改）(装修中)", "首开广场");
        searchFeedPage.clickText("名称");
        searchFeedPage.enterText("北京市朝阳区北京市朝阳区阜荣街10号", "阜荣街10号");
        searchFeedPage.clickText("地址");
        //searchFeedPage.enterTextField("支持固话及手机", "010-62881144", 0, "营业电话");
        searchFeedPage.swipeByTimes(0.5, 0.7, 0.5, 0.3, 1000, 1);
        //searchFeedPage.enterTextField("请输入您的联系电话", "15800000000", 2, "您的联系电话");
        searchFeedPage.addPicture("请确保照片中店铺名称清晰可见");
        searchFeedPage.enterTextView("补充详细的描述，加速审核", "test1", 3, "问题描述");
        searchFeedPage.clickSubmit("提交");
        Assert.assertTrue(SearchFeedPage.data_check_fail, CheckFeedBackResult.getResult(Utils.getDeviceId(), "2003", "0006"));

    }


    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "2002_0013", description = "普通POI：提交反馈-地点不存在", author = "北朔", level = "0")
    public void TC2002_0013() {

        String scheme = "amapuri://search/around?keywords=首开广场&lon=116.473195&lat=39.993253";
        caseUtils.scheme(scheme);
        searchFeedPage.clickSoukaiSearch();
        searchFeedPage.goToFeedback();
        searchFeedPage.clickText("地点不存在");
        searchFeedPage.enterTextView("如永久停业、拆迁等", "test2", 1, "问题描述");
        searchFeedPage.addPicture("请确保照片中店铺名称清晰可见");
        searchFeedPage.enterTextField("请输入您的联系电话", "15800000000", 0, "您的联系电话");
        searchFeedPage.clickSubmit("提交");
        Assert.assertTrue(SearchFeedPage.data_check_fail, CheckFeedBackResult.getResult(Utils.getDeviceId(), "2002", "0013"));

    }


    //普通POI ------------------ END-------------------------------------


    //道路POI ------------------ START-----------------------------------


    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "5004_1201", description = "道路POI-道路名称错误", author = "北朔", level = "0")
    public void TC5004_1201() {

        String scheme = "amapuri://search/around?keywords=京密路&lon=116.487573&lat=39.985996";
        caseUtils.scheme(scheme);
        searchFeedPage.clickRoadSearch();
        searchFeedPage.goToFeedback();
        searchFeedPage.clickText(SearchFeedPage.ROADNAMEERROR);
        searchFeedPage.enterTextField("请输入正确的名称", "test1", 0, "正确名称");
        searchFeedPage.addPicture("请拍摄道路路牌或现场照片，帮助我们加快审核");
        searchFeedPage.enterTextField("方便我们联系您核实情况", "15800000000", 1, "您的联系电话");
        searchFeedPage.clickSubmit("提交");
        searchFeedPage.confirmSuccess();
        Assert.assertTrue(SearchFeedPage.data_check_fail, CheckFeedBackResult.getResult(Utils.getDeviceId(), "5004", "1201"));

    }


    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "5006_1205", description = "道路POI：道路施工-提交反馈-道路施工", author = "北朔", level = "0")
    public void TC5006_1205() {

        String scheme = "amapuri://search/around?keywords=京密路&lon=116.487573&lat=39.985996";
        caseUtils.scheme(scheme);
        searchFeedPage.clickRoadSearch();
        searchFeedPage.goToFeedback();
        searchFeedPage.clickText(SearchFeedPage.ROADCONSTRUCTION);
        searchFeedPage.clickText(SearchFeedPage.MAINTAINED);
        searchFeedPage.enterTextView("补充详细的描述，加速审核", "test1", 1, "问题描述");
        searchFeedPage.addPicture("上传道路现场图片，帮助我们加快审核");
        searchFeedPage.enterTextField("方便我们联系您核实情况", "15800000000", 0, "您的联系电话");
        searchFeedPage.clickSubmit("提交");
        searchFeedPage.confirmSuccess();
        Assert.assertTrue(SearchFeedPage.data_check_fail, CheckFeedBackResult.getResult(Utils.getDeviceId(), "5006", "1205"));
    }


    //道路POI ------------------ END-------------------------------------

}
