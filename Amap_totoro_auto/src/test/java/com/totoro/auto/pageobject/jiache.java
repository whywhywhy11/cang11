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

public class jiache extends BaseCase {

    public void a1() {
        //点击路线，id方法
        sleep(5000);
        WebElement e1 = driver.findElementById("com.autonavi.minimap:id/route_line_icon");
        e1.click();
    }

    public void a2() {
        //点击历史记录，图片方法
        sleep(5000);
        IDLRect idlRect2 = driver.searchImgInCurrentPage(new File(System.getProperty("user.dir") + "/pic/jiache/lishijilu.jpg"));
        idlRect2.click(driver);
    }


    public void a3() {
        //点击搜索框输入地址,点击后输入文字
        WebElement e3 = driver.findElementById("com.autonavi.minimap:id/txt_hotword");
        e3.click();
        sleep ( 2000 );
        driver.sendKeys ( "三元桥站" );
    }


    public void a4() {
        //点击搜索中路线
        sleep(5000);
        IDLRect idlRect4 = driver.searchImgInCurrentPage(new File(System.getProperty("user.dir") + "/pic/jiache/113.jpg"));
        idlRect4.click(driver);
    }

    public void a5() {
        //点击搜索按钮，xpath方法
        sleep(5000);
        WebElement e5 = driver.findElementByXPath("//*[@text='搜索']");
        e5.click();
    }


    public void a6() {
        //点击路线，点击text方法
        sleep(3000);
        Utils.clickText("路线");
    }

    public void a7() {
        //长按主图
        sleep(3000);
        Utils.clickLongOnScreenCenter();
        sleep(2000);
    }

    public void a8() {
        //点击开始导航
        sleep(6000);
        WebElement e8 = driver.findElementByXPath("//*[@text='开始导航']");
        e8.click();
    }

    public void a9() {
        //切换偏好
        sleep(5000);
        IDLRect idlRect4 = driver.searchImgInCurrentPage(new File(System.getProperty("user.dir") + "/pic/jiache/146.jpg"));
        idlRect4.click(driver);
    }


    public void a10() {
        //切换驾车，在切换打车，再切换驾车
        sleep(3000);
        WebElement e10a = driver.findElementByXPath("//*[@text='驾车']");
        e10a.click();
        sleep(3000);
        WebElement e10b = driver.findElementByXPath("//*[@text='打车']");
        e10b.click();
        sleep(3000);
        WebElement e10c = driver.findElementByXPath("//*[@text='驾车']");
        e10c.click();
    }

    public void a11() {
        //滑动屏幕
        sleep(5000);
        int height = driver.getWindowSize().height;
        int width = driver.getWindowSize().width;
        driver.swipe(width * 1 / 2, height * 3 / 4, width * 1 / 2, height * 1 / 4, 1000);
        sleep(1000);
        driver.swipe(width * 1 / 2, height * 3 / 4, width * 1 / 2, height * 1 / 4, 1000);
        sleep(1000);
        driver.swipe(width * 1 / 2, height * 3 / 4, width * 1 / 2, height * 1 / 4, 1000);
    }

//以上是通用操作步骤-------------------------------------------------------------------------------------------------------------------------------------------









    public void fan_1() {
        //点击语音button
        sleep(5000);
        WebElement f1 = driver.findElementById("com.autonavi.minimap:id/lottie_view");
        f1.click();

        //点击语音卡片中帮助
        sleep(2000);
        WebElement f3 = driver.findElementByXPath("//*[@text='帮助']");
        f3.click();

        //滑动屏幕
        sleep(5000);
        int height = driver.getWindowSize().height;
        int width = driver.getWindowSize().width;
        driver.swipe(width * 1 / 2, height * 3 / 4, width * 1 / 2, height * 1 / 4, 1000);
        sleep(1000);
        driver.swipe(width * 1 / 2, height * 3 / 4, width * 1 / 2, height * 1 / 4, 1000);
        sleep(1000);
        driver.swipe(width * 1 / 2, height * 3 / 4, width * 1 / 2, height * 1 / 4, 1000);

        //点击反馈中我要反馈
        sleep(2000);
        WebElement f4 = driver.findElementByXPath("//*[@text='我要反馈语音问题']");
        f4.click();

        //点击其他问题
        sleep(2000);
        WebElement f5 = driver.findElementByXPath("//*[@text='其他问题']");
        f5.click();

        //点击描述并输入
        sleep(2000);
        Utils.clickText("补充详细的描述，加速审核");
        sleep(1000);
        driver.sendKeys ( "test5" );

        //再次滑动屏幕
        sleep(1000);
        driver.swipe(width * 1 / 2, height * 3 / 4, width * 1 / 2, height * 1 / 4, 1000);

        //点击联系并输入
        sleep(2000);
        Utils.clickText("方便我们联系您核实情况");
        sleep(1000);
        driver.sendKeys ( "18811304744" );

        //点击添加图片、拍摄图片、上传图片
        sleep(2000);
        IDLRect idlRectf8 = driver.searchImgInCurrentPage(new File(System.getProperty("user.dir") + "/pic/jiache/tianjiatu.jpg"));
        idlRectf8.click(driver);
        sleep(2000);
        WebElement f81 = driver.findElementById("com.autonavi.minimap:id/camera_item");
        f81.click();
        sleep(2000);
        WebElement f82 = driver.findElementById("com.autonavi.minimap:id/id_capture_btn");
        f82.click();
        sleep(6000);
        WebElement f83 = driver.findElementById("com.autonavi.minimap:id/camera_ok_btn");
        f83.click();

        //点击提交
        sleep(3000);
        WebElement f9 = driver.findElementByXPath("//*[@text='提交']");
        f9.click();
        sleep(8000);
    }











}