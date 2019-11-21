package com.totoro.auto.testcases.feedback;

import com.alipay.auto.common.CaseInfo;
import com.totoro.auto.base.AppBaseCase;
import com.totoro.auto.util.CheckFeedBackResult;
import com.totoro.auto.util.Utils;
import com.totoro.client.ios.IOSDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class BaseMapFeedTest extends AppBaseCase {
    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="2001_1901",description="XY戳点页--新增地点--新增地点（商户、楼宇）", author = "育昕", level="0")
    public void TC2001_1901() {
        baseMapFeedPage.choosePointAtMap();
        baseMapFeedPage.addNewPoint();
        baseMapFeedPage.addNewBuilding();
        baseMapFeedPage.setPointName("北京市东城区景山前街4号");
        baseMapFeedPage.setLocation();
        baseMapFeedPage.setLocalPic();
        baseMapFeedPage.clickSubmit();
        Assert.assertTrue("数据回传校验结果失败",CheckFeedBackResult.getResult(Utils.getDeviceId(),"2001","1901"));
    }

    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="5001_1904",description="XY戳点页--新增地点--新增道路", author = "育昕", level="0")
    public void TC5001_1904() {
        baseMapFeedPage.choosePointAtMap();
        baseMapFeedPage.addNewPoint();
        baseMapFeedPage.addRoute();
        baseMapFeedPage.setRoadName("北京市朝阳区天府街99号");
        Utils.clickText("点击编辑所在位置");
        baseMapFeedPage.clickConfirmChoose();
        baseMapFeedPage.clickConfirmChoose();
        baseMapFeedPage.clickSubmit();
        baseMapFeedPage.confirmSuccess();
        Assert.assertTrue("数据回传校验结果失败",CheckFeedBackResult.getResult(Utils.getDeviceId(),"5001","1904"));
    }

    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="3001_1902",description="XY戳点页--新增地点--新增公交站", author = "育昕", level="0")
    public void TC3001_1902() throws Exception {
        baseMapFeedPage.choosePointAtMap();
        baseMapFeedPage.addNewPoint();
        baseMapFeedPage.addBusStation();
        baseMapFeedPage.setStationNameAndLineName("三元桥站","110路");
        Utils.clickText("点击编辑所在位置");
        baseMapFeedPage.clickConfirmChoose();
        baseMapFeedPage.clickSubmit();
        baseMapFeedPage.confirmSuccess();
        Assert.assertTrue("数据回传校验结果失败",CheckFeedBackResult.getResult(Utils.getDeviceId(),"3001","1902"));
    }

    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="3001_1903",description="XY戳点页--新增地点--新增公交线路", author = "育昕", level="0")
    public void TC3001_1903() throws Exception {
        baseMapFeedPage.choosePointAtMap();
        baseMapFeedPage.addNewPoint();
        baseMapFeedPage.addBusLine();
        baseMapFeedPage.setLineName("119");
        baseMapFeedPage.clickSubmit();
        baseMapFeedPage.confirmSuccess();
        Assert.assertTrue("数据回传校验结果失败",CheckFeedBackResult.getResult(Utils.getDeviceId(),"3001","1903"));
    }

    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="2001_1901_2",description="XY戳点页--详情页新增地点--新增商户", author = "育昕", level="0")
    public void TC2001_1901_2() throws Exception {
        baseMapFeedPage.choosePointAtMap();
        baseMapFeedPage.addNewPoint();
        //mainPage.goPointDetail();
        baseMapFeedPage.addNewShop();
        //feedbackPage_mainPage.addNewBuilding();
        baseMapFeedPage.confirmShop();
//        Assert.assertTrue("数据回传校验结果失败",CheckFeedBackResult.getResult(Utils.getDeviceId(),"2001","1901"));
    }

    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="5001_1904_2",description="XY戳点页--详情页新增地点--新增道路", author = "育昕", level="0")
    public void TC5001_1904_2() throws Exception {
        baseMapFeedPage.choosePointAtMap();
        //mainPage.goPointDetail();
        baseMapFeedPage.addNewPoint();
        baseMapFeedPage.addRoute();
        baseMapFeedPage.setRoadName("北京市朝阳区天府街99号");
        Utils.clickText("点击编辑所在位置");
        baseMapFeedPage.clickConfirmChoose();
        baseMapFeedPage.clickConfirmChoose();
        baseMapFeedPage.clickSubmit();
        Assert.assertTrue("数据回传校验结果失败",CheckFeedBackResult.getResult(Utils.getDeviceId(),"5001","1904"));
    }

    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="3001_1902_2",description="XY戳点页--详情页新增地点--新增公交站", author = "育昕", level="0")
    public void TC3001_1902_2() throws Exception {
        baseMapFeedPage.choosePointAtMap();
        //mainPage.goPointDetail();
        baseMapFeedPage.addNewPoint();
        baseMapFeedPage.addBusStation();
        baseMapFeedPage.setStationNameAndLineName("三元桥站","110路");
        Utils.clickText("点击编辑所在位置");
        baseMapFeedPage.clickConfirmChoose();
        baseMapFeedPage.clickSubmit();
        Assert.assertTrue("数据回传校验结果失败",CheckFeedBackResult.getResult(Utils.getDeviceId(),"3001","1902"));
    }


    @Test
    @CaseInfo(ci = true,type="totoro", bizline="高德", summary="3001_1903_2",description="XY戳点页--详情页新增地点--新增公交线路", author = "育昕", level="0")
    public void TC3001_1903_2() throws Exception {
        baseMapFeedPage.choosePointAtMap();
        //mainPage.goPointDetail();
        baseMapFeedPage.addNewPoint();
        baseMapFeedPage.addBusLine();
        baseMapFeedPage.setLineName("110");
        baseMapFeedPage.clickSubmit();
        Assert.assertTrue("数据回传校验结果失败",CheckFeedBackResult.getResult(Utils.getDeviceId(),"3001","1903"));
    }
}
