package com.totoro.auto.pageobject;

import com.alipay.auto.common.BaseCase;
import com.totoro.auto.base.AppBaseCase;
import com.totoro.auto.util.Utils;
import com.totoro.client.annotation.AndroidFindBy;
import com.totoro.client.annotation.iOSFindBy;
import com.totoro.client.deeplearning.adstract.IDLRect;
import com.totoro.client.internal.MobilePlatform;
import com.totoro.client.ios.IOSDriver;
import org.junit.Assert;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

import java.io.File;

public class BaseMapFeedPage extends BaseCase {

    @AndroidFindBy(xpath = "//*[@class='android.widget.EditText']")
    @iOSFindBy(className="XCUIElementTypeTextField")
    private WebElement edit_text;

    @AndroidFindBy(xpath = "//*[@text='位置']")
    @iOSFindBy(id = "位置")
    private WebElement location;

    @AndroidFindBy(xpath = "//*[@text='提交']")
    @iOSFindBy(name = "提交")
    private WebElement submit;

    @AndroidFindBy(xpath = "//*[@text='继续提交']")
    @iOSFindBy(id = "继续提交")
    private WebElement submit_second;

    @AndroidFindBy(xpath = "//*[@text='从手机相册选择']")
    @iOSFindBy(id = "相册")
    private WebElement photo_shop;

    @AndroidFindBy(xpath = "//*[@text='请输入缺失的道路名称']")
    @iOSFindBy(id = "请输入缺失的道路名称")
    private WebElement edit_roadname;

    @AndroidFindBy(xpath = "//*[@text='相机胶卷']")
    @iOSFindBy(name = "相机胶卷")
    private WebElement camera_roll;

    @AndroidFindBy(xpath = "//*[@text='道路名称']")
    @iOSFindBy(name = "道路名称")
    private WebElement road_name;

    @AndroidFindBy(xpath = "//*[@text='确认选点']")
    @iOSFindBy(name = "确定选点")
    private WebElement confirm_choose;

    @AndroidFindBy(xpath = "//*[@text='确定选点']")
    @iOSFindBy(name = "确认选点")
    private WebElement confirm_choose2;

    @AndroidFindBy(xpath = "//*[@text='新增公交站']")
    @iOSFindBy(name = "新增公交站")
    private WebElement new_busstation;

    @AndroidFindBy(xpath = "//*[@text='新增线路']")
    @iOSFindBy(name = "新增线路")
    private WebElement new_busroad;

    /**
     * 长按地图选点
     */
    public void choosePointAtMap() {
        sleep(3000);
        Utils.clickLongOnScreenCenter();
        sleep(2000);
    }

    /**
     * 点击新增
     */
    public void addNewPoint() {
        //因为更改属性为view找不到该点。
        boolean check = Utils.waitForText("新增", 2);
        if (check) {
            Utils.clickText("新增");
            sleep(2000);
        }
        driver.longClick(506, 1806, 1);
        sleep(3000);

    }

    /**
     * 新增地点（商户、楼宇等）
     */
    public void addNewBuilding() {

//        clickOnText("新增地点（商户、楼宇等）",overallTimeout,false);

        Utils.clickText("新增地点");
    }

    /**
     * 输入地点名称
     *
     * @param name
     */
    public void setPointName(String name) {
        //clickOnView("com.autonavi.minimap:id/text_input",overallTimeout,false,"输入这个地点的招牌名称");
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)){
            edit_text.click();
        }else {
            sleep(2000);
            IDLRect idlRect = driver.searchImgInCurrentPage(new File(System.getProperty("user.dir") + "/pic/feedback/输入这个地点的招牌名称.jpg"));
            idlRect.click(driver);
        }
        sleep(2000);
        Utils.inputTextWithTotoro(name);
        Utils.clickText("确定");
        sleep(2000);
        //clickOnText("提交",overallTimeout,false);
        //clickOnText("继续新增名称",overallTimeout,false);
    }

    /**
     * 点击重选选择位置后确认选点
     */
    public void setLocation() {
        //点击地图位置的来选择地点
        int x = location.getLocation().getX();
        int y = location.getLocation().getY();
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            driver.click(x, y + 150);
        }else {
            driver.click(x, y + 100);
        }
        sleep(2000);
        Utils.clickText("确认选点");
    }


    /**
     * 现场照片添加
     */
    public void setLocalPic() {
        Utils.swipeUntilTextDisplay("请确保照片中店铺名称清晰可见");
        sleep(2000);
        WebElement e = driver.findElementByXPath("//*[@text='请确保照片中店铺名称清晰可见']");
        Rectangle rectangle = e.getRect();
        driver.click(rectangle.x+50,rectangle.y-100);
//        IDLRect idlRect = driver.searchImgInCurrentPage(new File(System.getProperty("user.dir") + "/pic/feedback/拍照.jpg"));
//        if (idlRect == null){
//
//        }else {
//            idlRect.click(driver);
//        }
        sleep(2000);
        photo_shop.click();
//        driver.click(552,282);
        if (MobilePlatform.IOS.equalsIgnoreCase(AppBaseCase.platform)){
            driver.acceptAlert();
            sleep(2000);
            camera_roll.click();
        }
        sleep(2000);
        driver.click(250,500);
    }

    /**
     * 点击提交
     */
    public void clickSubmit() {
        sleep(2000);
        Utils.swipeUntilTextDisplay("提交");
        sleep(2000);
        submit.click();
        try {
            if (submit_second.isDisplayed())
                submit_second.click();
        } catch (Throwable e) {
            customLog.log("没有找到继续提交按钮");
        }
    }

    /**
     * 新增道路
     */
    public void addRoute(){
        Utils.clickText("新增道路");
        sleep(3000);
    }

    /**
     * 输入道路名称
     */
    public void setRoadName(String name){
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)){
            edit_text.click();
        }else {
            IDLRect idlRect = driver.searchImgInCurrentPage(new File(System.getProperty("user.dir") + "/pic/feedback/请输入缺失的道路名称.jpg"));
            idlRect.click(driver);
        }
        Utils.inputTextWithTotoro(name);
        road_name.click();
        sleep(2000);
    }

    /**
     * 确认提交成功
     */
    public void confirmSuccess(){
//       sleep(5000);
       Utils.checkExistOfText("提交成功");

    }

    /**
     * 新增公交站
     */
    public void addBusStation(){
        Utils.clickText("新增公交站");
        sleep(2000);
    }

    /**
     * 设置车站名称和线路名称
     * @param stationName
     * @param lineName
     */
    public void setStationNameAndLineName(String stationName,String lineName) {
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)){
            Utils.clickText("例如，三元桥站");
            Utils.inputTextWithTotoro("三元桥站");
            sleep(2000);
            Utils.clickText("例如，110路");
            Utils.inputTextWithTotoro("110路");
        }else {
            IDLRect idlRect = driver.searchImgInCurrentPage(new File(System.getProperty("user.dir") + "/pic/feedback/例如三元桥站.jpg"));
            idlRect.click(driver);
            sleep(1000);
            driver.sendKeys("三元桥站");
            new_busstation.click();
            sleep(2000);
            idlRect = driver.searchImgInCurrentPage(new File(System.getProperty("user.dir") + "/pic/feedback/例如110路.jpg"));
            idlRect.click(driver);
            sleep(1000);
            driver.sendKeys("110路");
            new_busstation.click();
        }

    }

    /**
     * 新增公交线路
     */
    public void addBusLine(){
        Utils.clickText("新增公交线路");
        sleep(2000);
    }

    /**
     * 设置线路名称
     * @param lineName
     */
    public void setLineName(String lineName) {
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)){
            sleep(2000);
            Utils.clickText("例如，110路");
            Utils.inputTextWithTotoro(lineName);

        }else {
            IDLRect idlRect = driver.searchImgInCurrentPage(new File(System.getProperty("user.dir") + "/pic/feedback/例如110路.jpg"));
            idlRect.click(driver);
            sleep(1000);
            driver.sendKeys(lineName);
            new_busroad.click();
        }

    }

    /**
     * 新增商户，改版后的
     */
    public void addNewShop(){
        Utils.clickText("新增商户");
        sleep(2000);
    }

    /**
     * 添加商户确认跳转
     */
    public void confirmShop(){
        sleep(2000);
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)){
            Utils.checkExistOfText("添加门店");
        }else {
            Utils.checkExistOfText("添加/认领我的门店");
        }

    }

    /**
     * 点击确认选点
     */
    public void clickConfirmChoose(){
        try{
            confirm_choose.click();
        }catch (Throwable e){
            confirm_choose2.click();
        }
    }

}
