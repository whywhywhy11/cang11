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
 * @Title: SearchFeedWithOfflineTest.java
 * @Package com.totoro.auto.testcases.feedback
 * @Description: (信息服务 - 搜索反馈 - 离线数据)
 * @date 2019/10/29 17:21
 */

public class SearchFeedWithOfflineTest extends AppBaseCase {

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
        downloadManagePage.getIntoCityList();
        downloadManagePage.downloadDataForCity("北京市");

    }


    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "5002_1202", description = "道路POI-道路不存在", author = "北朔", level = "0")
    public void TC5002_1202() {

        String scheme = "amapuri://search/around?keywords=京密路&lon=116.487573&lat=39.985996";
        caseUtils.scheme(scheme);
        searchFeedPage.clickRoadSearch();
        searchFeedPage.goToFeedback();
        searchFeedPage.clickText(SearchFeedPage.ROADNOTEXISTED);
        searchFeedPage.enterTextView("补充详细的描述，加速审核", "test2", 1, "问题描述");
        //上传问题相关照片，加快审核
        searchFeedPage.addPicture("上传问题相关照片，加快审核");
        searchFeedPage.enterTextField("方便我们联系您核实情况", "15800000000", 0, "您的联系电话");
        searchFeedPage.clickSubmit("提交");
        Assert.assertTrue(SearchFeedPage.data_check_fail, CheckFeedBackResult.getResult(Utils.getDeviceId(), "5002", "1202"));

    }

    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "5006_1206", description = "道路POI-道路施工", author = "北朔", level = "0")
    public void TC5006_1206() {

        String scheme = "amapuri://search/around?keywords=京密路&lon=116.487573&lat=39.985996";
        caseUtils.scheme(scheme);
        searchFeedPage.clickRoadSearch();
        searchFeedPage.goToFeedback();
        searchFeedPage.clickText(SearchFeedPage.ROADCONSTRUCTION);
        searchFeedPage.clickText(SearchFeedPage.OTHERS);
        searchFeedPage.enterTextView("补充详细的描述，加速审核", "test2", 1, "问题描述");
        searchFeedPage.addPicture("上传道路现场图片，帮助我们加快审核");
        searchFeedPage.enterTextField("方便我们联系您核实情况", "15800000000", 0, "您的联系电话");
        searchFeedPage.clickSubmit("提交");
        searchFeedPage.confirmSuccess();
        Assert.assertTrue(SearchFeedPage.data_check_fail, CheckFeedBackResult.getResult(Utils.getDeviceId(), "5006", "1206"));


    }


    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "5005_1207", description = "道路POI-道路改道", author = "北朔", level = "0")
    public void TC5005_1207() {

        String scheme = "amapuri://search/around?keywords=京密路&lon=116.487573&lat=39.985996";
        caseUtils.scheme(scheme);
        searchFeedPage.clickRoadSearch();
        searchFeedPage.goToFeedback();
        searchFeedPage.clickText(SearchFeedPage.ROADCHANGED);
        searchFeedPage.enterTextView("请描述道路现状", "test2", 1, "问题描述");
        searchFeedPage.addPicture("上传道路现场图片，帮助我们加快审核");
        searchFeedPage.enterTextField("方便我们联系您核实情况", "15800000000", 0, "您的联系电话");
        searchFeedPage.clickSubmit("提交");
        //searchFeedPage.confirmSuccess();
        Assert.assertTrue(SearchFeedPage.data_check_fail, CheckFeedBackResult.getResult(Utils.getDeviceId(), "5006", "1206"));


    }

    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "5000_1208", description = "道路POI-其他问题", author = "北朔", level = "0")
    public void TC5000_1208() {

        String scheme = "amapuri://search/around?keywords=京密路&lon=116.487573&lat=39.985996";
        caseUtils.scheme(scheme);
        searchFeedPage.clickRoadSearch();
        searchFeedPage.goToFeedback();
        searchFeedPage.clickText(SearchFeedPage.OTHERS);
        searchFeedPage.enterTextView("补充详细的描述，加速审核", "test2", 1, "问题描述");
        searchFeedPage.addPicture("上传问题相关照片，加快审核");
        searchFeedPage.enterTextField("方便我们联系您核实情况", "15800000000", 0, "您的联系电话");
        searchFeedPage.clickSubmit("提交");
        //searchFeedPage.confirmSuccess();
        Assert.assertTrue(SearchFeedPage.data_check_fail, CheckFeedBackResult.getResult(Utils.getDeviceId(), "5000", "1208"));
    }

    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "2007_0005", description = "普通POI-位置错误", author = "北朔", level = "0")
    public void TC2007_0005() {

        String scheme = "amapuri://poi/detail?poiname=颐和园&lon=116.470760&lat=39.991920";
        caseUtils.scheme(scheme);
        searchFeedPage.goToFeedback();
        searchFeedPage.clickText(SearchFeedPage.INCORRECTPOSITION);
        searchFeedPage.clickXY(0.5, 0.42);
        searchFeedPage.swipeByTimes(0.5, 0.5, 0.5, 0.3, 1000, 1);
        searchFeedPage.clickText("确认选点");
        searchFeedPage.enterText("北京市海淀区新建宫门路19号", "text2");
        searchFeedPage.clickText("地址");
        searchFeedPage.enterTextField("请输入您的联系电话", "15800000000", 0, "您的联系电话");
        searchFeedPage.swipeByTimes(0.5, 0.7, 0.5, 0.3, 1000, 1);
        searchFeedPage.addPicture("请确保照片中店铺名称清晰可见");
        searchFeedPage.enterTextView("补充详细的描述，加速审核", "test1", 2, "问题描述");
        searchFeedPage.clickSubmit("提交");
        //Assert.assertTrue(SearchFeedPage.data_check_fail, CheckFeedBackResult.getResult(Utils.getDeviceId(), "2007", "0005"));

    }


}
