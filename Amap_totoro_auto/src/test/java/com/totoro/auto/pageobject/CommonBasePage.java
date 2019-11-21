package com.totoro.auto.pageobject;

import com.alipay.auto.common.BaseCase;
import com.totoro.auto.util.Utils;


/**
 * @author beishuo
 * @version V1.0
 * @Title: CommonBasePage.java
 * @Package com.totoro.auto.pageobject
 * @Description: (抽象到父类, 公共方法)
 * @date 2019/10/29 15:05
 */
public abstract class CommonBasePage extends BaseCase {

    public static String text_home = "首页";
    public static String text_mine = "我的";
    public static String text_feedback = "反馈";
    public static String text_confirm_success = "提交成功";
    public static String data_check_fail = "数据回传校验结果失败";

    /**
     * 回到首页
     */
    public void backToMap() {
        if (Utils.waitForText(text_home, 2)) {
            Utils.clickText(text_home);
            customLog.log("点击：" + text_home);
        } else {
            Utils.pressBack();
        }
    }

    /**
     * 我的界面
     */
    public void goMy() {
        if (Utils.waitForText(text_mine, 2)) {
            Utils.clickText(text_mine);
            customLog.log("点击：" + text_mine);

        } else {
            Utils.pressBack();
        }
    }

    /**
     * 点击反馈
     */
    public void goToFeedback() {
        sleep(5000);
        Utils.clickOnTextIfExist(text_feedback);
        customLog.log("点击：" + text_feedback);

    }

    /**
     * 滑动次数
     *
     * @param times
     */
    public void swipeByTimes(double startx, double starty, double endx, double endy, int durating, int times) {
        sleep(2000);
        int i = 0;
        while (i < times) {
            //Utils.swipe(0.5, 0.7, 0.5, 0.3, 100);
            Utils.swipe(startx, starty, endx, endy, durating);
            customLog.log("滑动第" + i + "次");
            i++;
        }
    }

    /**
     * 确认提交成功
     */
    public void confirmSuccess() {
        Utils.checkExistOfText(text_confirm_success);
    }


    public void clickXY(double x, double y) {
        sleep(2000);
        Utils.click(x, y);
    }


}
