package com.totoro.auto.util;

import com.alipay.auto.common.CommonBaseCase;
import com.alipay.auto.utils.LogWriter;
import com.totoro.auto.base.AppBaseCase;
import com.totoro.client.android.AndroidDriver;
import com.totoro.client.internal.MobileDriver;
import com.totoro.client.internal.MobilePlatform;
import com.totoro.client.ios.IOSDriver;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * Created by wuguo on 17/9/15.
 */
public class Utils {
    public static LogWriter customLog = new LogWriter();
    static MobileDriver driver = CommonBaseCase.driver;
    public static int width = driver.getWindowSize().width;
    public static int height = driver.getWindowSize().height;
    public static String deviceid = "";

    public static boolean isElementDisplayed(WebElement element) {
        boolean isDisplayed = false;
        try {
            isDisplayed = element.isDisplayed();
        } catch (Exception e) {
        }
        return isDisplayed;
    }

    public static void sleep(int time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void inputByShell(String Udid, String content) {
        String cmd = "adb -s " + Udid + " shell input text " + content;
        com.alipay.auto.utils.Utils.getOutputStrNull(cmd, 4 * 1000);
    }

    public static void click(double x, double y) {
        double X = x * width;
        double Y = y * height;
        driver.click((float) X, (float) Y);
    }

    public static void longClick(double x, double y, float duration) {
        double X = x * width;
        double Y = y * height;
        driver.longClick((float) X, (float) Y, duration);
    }

    /**
     * @param startxPercent
     * @param startyPercent
     * @param endxPercent
     * @param endyPercent
     * @param durating
     */
    public static void swipe(double startxPercent, double startyPercent, double endxPercent, double endyPercent, int durating) {
        double startX = startxPercent * width;
        double startY = startyPercent * height;
        double endX = endxPercent * width;
        double endY = endyPercent * height;
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            driver.swipe(startX, startY, endX, endY, durating);
            Utils.sleep(2 * 1000);
        } else {
            driver.drag(startX, startY, endX, endY, durating);
            Utils.sleep(2 * 1000);
        }
    }

    /**
     * @param startx
     * @param starty
     * @param endx
     * @param endy
     * @param durating
     * @param times
     */
    public static void swipe(double startx, double starty, double endx, double endy, int durating, int times) {
        for (int i = 0; i < times; i++) {
            driver.swipe(startx, starty, endx, endy, durating);
        }
    }


    public static String getDeviceId() {
        if (deviceid != ""){
            return deviceid;
        }
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            deviceid = CmdUtil.run("adb -s " + AppBaseCase.udid + " shell 'service call iphonesubinfo 1 | grep -o \"[0-9a-f]\\{8\\} \" | tail -n+3 | while read a; do echo -n \"\\u${a:4:4}\\u${a:0:4}\"; done'");
        } else {
//             deviceid = ((IOSDriver) driver).deviceInfo().split("\"IDFA\":\"")[1].split("\"")[0];
            deviceid = AppBaseCase.udid;
        }
        return deviceid;
    }


    /**
     * 在一定时间内是否返回唯一的某字符串
     *
     * @param text    字符串
     * @param timeout 时间/秒
     * @return
     */
    public static boolean waitForText(String text, int timeout) {
        boolean existed = false;
        for (int time = 0; time < timeout; ) {
            try {
                if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
                    driver.findElementByXPath("//*[@text='" + text + "']");
                } else {
                    if (!driver.findElementByName(text).isDisplayed()) {
                        throw new Exception("没有找到" + text);
                    }
                }
                existed = true;
                break;
            } catch (Exception e) {
                time++;
                sleep(1000);
                continue;
            }
        }
        if (existed) {
            customLog.log("找到 " + text);
        } else {
            customLog.log("未找到 " + text);
        }
        return existed;
    }

    /**
     * 在一定时间内是否返回唯一的view
     *
     * @param ID      元素ID
     * @param timeout 时间/秒
     * @return
     */
    public static boolean waitForViewByID(String ID, int timeout, String viewDescription) {
        boolean existed = false;
        for (int time = 0; time < timeout; ) {
            try {
                if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
                    driver.findElement(By.id(ID));
                    existed = true;
                    break;
                }
            } catch (Exception e) {
                time++;
                sleep(1000);
                continue;
            }
        }
        if (existed) {
            customLog.log("找到 " + ID + "::" + viewDescription);
        } else {
            customLog.log("未找到 " + ID + "::" + viewDescription);
        }
        return existed;
    }

    /**
     * 根据文本找到element获取位置信息
     *
     * @param text
     * @return
     */
    public static Rectangle getPointByContainsText(String text) {
        WebElement ele = null;
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            ele = driver.findElementByXPath("//*[contains(@text,'" + text + "')]");
        } else {
            ele = driver.findElementByName(text);
        }
        if (ele != null) {

            return ele.getRect();
        } else {
            return null;
        }
    }


    public static boolean waitForContainsText(String text, int timeout) {
        boolean existed = false;
        for (int time = 0; time < timeout; ) {
            try {
                if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
                    driver.findElementByXPath("//*[contains(@text,'" + text + "')]");
                } else {
                    driver.findElementByPartialLinkText(text);
                }
                existed = true;
                break;
            } catch (Exception e) {
                time++;
                sleep(1000);
                continue;
            }
        }
        if (existed) {
            customLog.log("找到 " + text);
        } else {
            customLog.log("未找到 " + text);
        }
        return existed;
    }

    public static void clickText(String text) {
        sleep(1000);
        try {
            if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
                driver.findElementByXPath("//*[@text='" + text + "']").click();
            } else {
                ((IOSDriver) driver).findElementByName(text).click();
            }
        } catch (Exception e) {
            customLog.log(text + " 点击失败");
        }
    }


    public static void clickContainsText(String text) {
        sleep(1000);
        try {
            if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
                driver.findElementByXPath("//*[contains(@text,'" + text + "')]").click();
            } else {
                ((IOSDriver) driver).findElementByPartialLinkText(text).click();
            }
        } catch (Exception e) {
            customLog.log(text + " 点击失败");
        }
    }

    public static void clickContainsTextIfExist(String text) {
        if (waitForContainsText(text, 5)) {
            clickContainsText(text);
        }
    }

    public static void checkExistOfToast(String toast) {
        Assert.assertTrue("未检测到toast：" + toast, ((AndroidDriver) driver).getToastMessage().contains(toast));
    }

    /**
     * 在屏幕正中心长按
     */
    public static void clickLongOnScreenCenter() {
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            int height = driver.getWindowSize().height;
            int width = driver.getWindowSize().width;
            driver.swipe(width * 1 / 4, height * 1 / 4, width * 1 / 4, height * 1 / 4, 1000);
        } else {
            longClick(0.25, 0.25, 1000);
        }
    }

    /**
     * 滑动至text出现
     */
    public static void swipeUntilTextDisplay(String text) {
        int i = 0;
        while (i < 10) {
            if (i == 10) {
                customLog.log("上滑10次页面仍然没有找到：" + text);
                break;
            }
            if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
                if (!driver.getPageSource().contains(text)) {
                    driver.swipe(width * 1 / 2, height * 3 / 4, width * 1 / 2, height * 1 / 4, 1000);
                    Utils.sleep(1000);
                    i++;
                } else {
                    customLog.log("上滑页面找到：" + text);
                    break;
                }
            } else {
                //ios getPageSource会拿到所有控件树（包括可见与不可见）
                if (!((IOSDriver) driver).findElementByName(text).isDisplayed()) {
                    driver.swipe(width * 1 / 2, height * 3 / 4, width * 1 / 2, height * 1 / 2, 1000);
                    Utils.sleep(1000);
                    i++;
                } else {
                    customLog.log("上滑页面找到：" + text);
                    break;
                }
            }

        }
    }

    public static void checkExistOfText(String text) {
        Assert.assertTrue("未找到：" + text, waitForText(text, 20));
    }

    /**
     * 调用Totoro输入法进行无UI输入
     */
    public static void inputTextWithTotoro(String text) {
        sleep(1000);
        driver.sendKeys(text);
//        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
//            CmdUtil.run("adb -s "+ AppBaseCase.udid + " shell am broadcast -a ADB_INPUT_TEXT  --es msg "+text);
//        }else {
//
//        }
    }


    /**
     * 初始化权限弹窗
     */
    public static void Initialization() {
        sleep(2000);
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            if (driver.getPageSource().contains("将为您提供以下服务")) {
                customLog.log("首次启动App进入欢迎界面");
                clickText("进入高德地图");
                clickText("去开启以上服务");
                sleep(12000);
                if (driver.getPageSource().contains("选择你的出行偏好")) {
                    clickText("跳过");
                    sleep(18000);
                }
                if (driver.getPageSource().contains("添加至主屏幕")) {
                    clickText("取消");
                    sleep(2000);
                }
                clickText("好的");
                clickText("好的");
                sleep(5000);
                Utils.click(0.5, 0.5);
                sleep(1000);
                Utils.swipe(0.5, 0.3, 0.5, 0.8, 100);
                sleep(2000);
                CmdUtil.run("adb -s " + AppBaseCase.udid + " shell ime set com.totoro.android.server/.tools.UnicodeIME");
                CmdUtil.run("adb -s " + AppBaseCase.udid + " shell ime enable com.totoro.android.server/.tools.UnicodeIME");

            }
        } else {
            try {
                if (driver.findElementByName("去开启以上服务").isDisplayed()) {
                    customLog.log("首次启动App进入欢迎界面");
                    clickText("去开启以上服务");
                    sleep(3000);
                    try {
                        WebElement alert0 = driver.findElementByName("始终允许");
                        if (alert0.isDisplayed()) {
                            alert0.click();
                            customLog.log("点击第一个弹窗");
                            sleep(2000);
                            driver.acceptAlert();
                            customLog.log("点击第二个弹窗");
                            sleep(5000);
                            driver.acceptAlert();
                            customLog.log("点击第三个弹窗");
                            sleep(2000);
                        }
                    } catch (Throwable e) {
                        customLog.log("没有找到系统弹窗");
                    }
                }
//                driver.acceptAlert();
            } catch (Throwable e) {
                customLog.log("不需要授予权限操作");
            }
            try {
                if (driver.findElementByName("选择你的出行偏好").isDisplayed()) {
                    clickText("跳过");
                    sleep(3000);
                    customLog.log("进入选择你的出行偏好，点击跳过");
                }
            } catch (Throwable e) {
                customLog.log("没有进入选择你的出行偏好");
            }
            try {
                if (Utils.waitForContainsText("我的在这里", 2)) {
                    clickText("好的");
                    clickText("好的");
                    sleep(2000);
                    Utils.click(0.5, 0.5);
                    sleep(1000);
                    Utils.swipe(0.5, 0.3, 0.5, 0.8, 100);
                    sleep(2000);
                    customLog.log("发现引导浮层，点击好的");
                }
            } catch (Throwable e) {
                customLog.log("没有发现引导浮层");
            }
        }
        Utils.waitForText("首页", 10);
    }

    public static void swipeWithAdb(double startx, double starty, double endx, double endy, int durating) {
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            int x = (int) (startx * width);
            int y = (int) (starty * height);
            int X = (int) (endx * width);
            int Y = (int) (endy * height);
            CmdUtil.run("adb -s " + AppBaseCase.udid + " shell input swipe " + x + " " + y + " " + X + " " + Y + " " + durating);
        } else {
            driver.drag(startx * width, starty * height, endx * width, endy * height, durating);
        }
    }

    public static void clickOnTextIfExist(String text) {
        if (waitForContainsText(text, 2)) {
            clickContainsText(text);
        }
    }

    /**
     * 点击返回按键
     */
    public static void pressBack() {

//        AndroidDriver androidDriver = (AndroidDriver) driver;
//        androidDriver.pressKeyCode(key);

        driver.back();

    }

    /**
     * 使用checkbox是选中状态
     *
     * @param element
     * @return
     */
    public static boolean setCheckBoxCheckedByResourceId(WebElement element) {
        if (element != null) {
            if (!element.isSelected()) {
                element.click();
            }
            return true;
        }
        return false;
    }
}
