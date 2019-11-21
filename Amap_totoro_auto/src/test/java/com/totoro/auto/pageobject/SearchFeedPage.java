package com.totoro.auto.pageobject;


import com.totoro.auto.base.AppBaseCase;
import com.totoro.auto.util.Utils;
import com.totoro.client.annotation.AndroidFindBy;
import com.totoro.client.annotation.iOSFindBy;
import com.totoro.client.deeplearning.adstract.IDLRect;
import com.totoro.client.internal.MobilePlatform;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;

/**
 * @author beishuo
 * @version V1.0
 * @Title: SearchFeedPage.java
 * @Package com.totoro.auto.pageobject
 * @Description: 搜索用反Page界面
 * @date 2019/10/29 12:41
 */
public class SearchFeedPage extends CommonBasePage {

    public static final String INCORRECTROUTE = "途径线路错误";
    public static final String INCORRECTSTATION = "车站错误";

    public static final String MISSINGBUSLINE = "地图线路缺失";
    public static final String REDUNDANTBUSLINE = "地图线路多余";
    public static final String OTHERS = "其他问题";

    public static final String STATIONPOSITIONINCORRECT = "车站位置错误";
    public static final String STATIONNAMEINCORRECT = "车站名称错误";
    public static final String CANNOTFINDSTATION = "找不到车站";


    public static final String ROADNAMEERROR = "道路名称错误";
    public static final String ROADNOTEXISTED = "道路不存在";
    public static final String ROADCONSTRUCTION = "道路施工";
    public static final String ROADCHANGED = "道路改道";


    public static final String CONSTRUCTION = "建设中";
    public static final String NOTOPENED = "已建成未开通";
    public static final String MAINTAINED = "道路维修中";

    public static final String INCORRECTPOSITION = "地图位置错误";
    public static final String NOTEXISTED = "不存在";
    public static final String INCORRECTINFO = "信息错误";
    public static final String INCORRECTBORDER = "地点边界错误";
    public static final String ADDBORDER = "新增地点边界";


    @AndroidFindBy(xpath = ".//*[@content-desc='B0FFFAB6J2']")
    private WebElement soukaiPOI;

    @AndroidFindBy(xpath = ".//*[@content-desc='BZDCPW02AW']")
    private WebElement roadPOI;

    @iOSFindBy(className = "XCUIElementTypeTextView")
    private List<WebElement> type_textview;

    @iOSFindBy(className = "XCUIElementTypeTextField")
    private List<WebElement> type_textfield;

    @iOSFindBy(id = "PhotoCapture")
    private WebElement photo_capture;


    @AndroidFindBy(xpath = "//*[@text='从手机相册选择']")
    @iOSFindBy(id = "相册")
    private WebElement photo_shop;


    @AndroidFindBy(xpath = "//*[@text='拍照']")
    @iOSFindBy(id = "拍照")
    private WebElement photograph;

    @iOSFindBy(name = "使用照片")
    private WebElement chcek_photo;


    /**
     * 点击text
     *
     * @param str
     */
    public void clickText(String str) {
        sleep(3000);
        Utils.clickText(str);
        customLog.log("点击：" + str);

    }

    /**
     * @param text
     * @param index
     */
    public void clickTextFieldByIndex(String text, int index) {

        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            clickText(text);
        } else {
            type_textfield.get(index).click();
        }

    }

    /**
     * @param text
     * @param index
     */
    public void clickTextViewByIndex(String text, int index) {

        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            clickText(text);
        } else {
            type_textview.get(index).click();
        }

    }

    /**
     * 输入内容
     *
     * @param text
     * @param info
     */
    public void enterText(String text, String info) {
        sleep(3000);
        Utils.swipeUntilTextDisplay(text);
        Utils.clickText(text);
        Utils.inputTextWithTotoro(info);
    }

    /**
     * 问题描述
     *
     * @param text
     * @param info
     * @param index
     * @param click
     */
    public void enterTextView(String text, String info, int index, String click) {
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            enterText(text, info);
        } else {
            driver.scrollUp();
            sleep(2000);
            type_textview.get(index).click();
            Utils.inputTextWithTotoro(info);
            Utils.clickText(click);
        }
    }

    /**
     * 联系电话、选择线路
     *
     * @param text
     * @param info
     * @param index
     * @param click
     */
    public void enterTextField(String text, String info, int index, String click) {
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            enterText(text, info);
        } else {
            //driver.scrollUp();
            sleep(2000);
            type_textfield.get(index).click();
            Utils.inputTextWithTotoro(info);
            Utils.clickText(click);
        }
    }


    public void enterTextByPic(String text, String info, String name, String click) {
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            enterText(text, info);
        } else {
            sleep(2000);
            IDLRect idlRect = driver.searchImgInCurrentPage(new File(System.getProperty("user.dir") + "/pic/feedback/" + name));
            idlRect.click(driver);
            Utils.inputTextWithTotoro(info);
            Utils.clickText(click);
        }
    }


    /**
     * 点击提交按钮
     *
     * @param info
     */
    public void clickSubmit(String info) {

        sleep(2000);
        Utils.swipeUntilTextDisplay(info);
        sleep(2000);
        Utils.clickText(info);

    }

    public void clickSoukaiSearch() {
        sleep(2000);
        //首开广场（修改）(装修中)
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            soukaiPOI.click();
        } else {
            Utils.clickText("北京市朝阳区阜荣街10号");
        }
        sleep(2000);
    }


    public void clickRoadSearch() {
        sleep(2000);
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            roadPOI.click();
        } else {
            Utils.clickText("朝阳区");
        }
        sleep(2000);
    }


    /**
     * 添加照片
     */
    public void addPicture(String text) {

        sleep(2000);

        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {

        } else {

            WebElement e = driver.findElementByName(text);
            Rectangle rectangle = e.getRect();
            driver.click(rectangle.x + 40, rectangle.y - 60);
            sleep(2000);
            photograph.click();
            sleep(2000);
            photo_capture.click();
            sleep(2000);
            chcek_photo.click();

        }

    }

}
