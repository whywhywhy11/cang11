package com.totoro.auto.pageobject;

import com.alipay.auto.common.BaseCase;
import com.totoro.auto.base.AppBaseCase;
import com.totoro.auto.util.Utils;
import com.totoro.client.annotation.AndroidFindBy;
import com.totoro.client.annotation.iOSFindBy;
import com.totoro.client.deeplearning.adstract.IDLRect;
import com.totoro.client.internal.MobilePlatform;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;

public class BusFeedPage extends BaseCase {

    @AndroidFindBy(xpath = "android.view.ViewGroup")
    @iOSFindBy(className = "XCUIElementTypeCell")
    private List<WebElement> bus_trip_in_list;

    @AndroidFindBy(xpath = "//*[@text='提交']")
    @iOSFindBy(name = "提交")
    private WebElement submit;

    @AndroidFindBy(xpath = "//*[@text='继续提交']")
    @iOSFindBy(id = "继续提交")
    private WebElement submit_second;

    @AndroidFindBy(xpath = "//*[@text='现在出发']")
    @iOSFindBy(id = "现在出发")
    private WebElement now_go;

    @AndroidFindBy(id = "com.autonavi.minimap:id/mine_icon")
    @iOSFindBy(id = "我的")
    private WebElement mine_icon;

    @AndroidFindBy(id = "com.autonavi.minimap:id/button2")
    @iOSFindBy(name = "退出")
    private WebElement button2;

    @AndroidFindBy(id = "com.autonavi.minimap:id/route_line_icon")
    private WebElement route_line_icon;

    @AndroidFindBy(id = "com.autonavi.minimap:id/route_edit_start_text")
    @iOSFindBy(id = "我的位置")
    private WebElement id_satrtInputLine;

    @AndroidFindBy(id = "com.autonavi.minimap:id/route_edit_end_text")
    @iOSFindBy(id = "输入终点")
    private WebElement id_endInputLine;

    @AndroidFindBy(id = "com.autonavi.minimap:id/sug_list")
    private List<WebElement> sug_list;

    @AndroidFindBy(id = "com.autonavi.minimap:id/main_content_rl")
    @iOSFindBy(name = " default_generalsearch_sugg_tqueryicon_normal")
    private WebElement main_content_rl;

    @iOSFindBy(className = "XCUIElementTypeTextView")
    private List<WebElement> question_description;

    @iOSFindBy(className = "XCUIElementTypeTextField")
    private List<WebElement> phone_number;

    @iOSFindBy(id = "metronone")
    private WebElement no_subway;

    @iOSFindBy(className = "XCUIElementTypeCell")
    private List<WebElement> element_typecell;

    /**
     * start scheme
     *
     * @param uri
     */
    public void startScheme(String uri) {
        caseUtils.scheme(uri);
        Utils.waitForText("分", 10);
    }

    /**
     * 点击第1个公交路线
     */
    public void chooseAnyBusTripInList() {
        clickOperateCloseIcon();
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform))
            Utils.click(0.5, 0.42);
        else{
            //预防ios图像识别失败，点击空白区域去掉浮层（如果有）
            Utils.click(0.05,0.11);
            bus_trip_in_list.get(1).click();
        }

    }

    /**
     * 判断是否存在浮层按钮，存在先点击浮层
     */
    public void clickOperateCloseIcon() {
        sleep(2000);
        IDLRect idlRect = driver.searchImgInCurrentPage(new File(System.getProperty("user.dir") + "/pic/feedback/浮层关闭按钮.jpg"));
        if (idlRect != null) {
            Utils.click(0.5, 0.5);
            customLog.log("发现浮层，点掉！");
        } else {
            customLog.log("没有发现活动浮层");
        }
    }

    /**
     * 在公交线路详情页下滑显示公交线路图区页
     * 820改版，显示图面中的按钮点击不起作用，需要进行下滑
     */
    public void swipeUpToShowBusTripMap() {
        sleep(5000);
        //运营模块化弹窗消失
        clickOperateCloseIcon();
        Utils.swipe(0.5, 0.8, 0.5, 0.4, 100);
    }

    /**
     * 在公交线路详情页点击反馈icon,报错的icon
     */
    public void clickOnFeedbackIcon() {
        sleep(5000);
        //运营模块化弹窗消失
        clickOperateCloseIcon();
//        driver.swipe(0.5, 0.8, 0.5, 0.2, 100);
        //判断图像识别是否误点
        if (Utils.waitForText("分享",2))
            driver.swipe(0.5, 0.2, 0.5, 0.8, 100);
        sleep(3000);
        Utils.clickText("报错");
        if (!Utils.waitForText("请选择问题类型",2)){
            IDLRect idlRect = driver.searchImgInCurrentPage(new File(System.getProperty("user.dir") + "/pic/feedback/报错3.jpg"));
            idlRect.click(driver);
        }
        sleep(2000);
    }

    /**
     * 在公交线路详情页底部点击反馈icon,报错的icon
     */
    public void clickOnFeedbackIconBottom() {
        sleep(5000);
        //运营模块化弹窗消失
        clickOperateCloseIcon();
        sleep(2000);
        Utils.click(0.5,0.64);
        if (!Utils.waitForText("分享",2))
            Utils.click(0.5,0.64);
        sleep(3000);
        Utils.clickText("报错");
//        if (!Utils.waitForText("请选择问题类型",2)){
//            IDLRect idlRect = driver.searchImgInCurrentPage(new File(System.getProperty("user.dir") + "/pic/feedback/报错3.jpg"));
//            idlRect.click(driver);
//        }
        sleep(2000);
    }

    /**
     * 在公交线路详情页点击反馈icon,报错的icon（图片查找）
     */
    public void clickOnFeedbackIconByImg() {
        sleep(5000);
        //运营模块化弹窗消失
        clickOperateCloseIcon();
        sleep(2000);
        IDLRect idlRect = driver.searchImgInCurrentPage(new File(System.getProperty("user.dir") + "/pic/feedback/报错.jpg"));
        idlRect.click(driver);
        if (!Utils.waitForText("请选择问题类型",2)){
            Utils.click(0.94,0.23);
        }
    }

    /**
     * 点击公交方案不合理
     */
    public void busRouteUnacceptable() {
        Utils.clickText("公交方案不合理");
        sleep(3000);
    }

    /**
     * 点击方案绕路
     */
    public void clickPlanDetour() {
        Utils.clickText("方案绕路");
        sleep(3000);
    }

    /**
     * 添加问题描述
     */
    public void inputQuestion() {
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            Utils.swipeUntilTextDisplay("补充详细的描述，加速审核");
            Utils.clickText("补充详细的描述，加速审核");
            Utils.inputTextWithTotoro("test01");
        } else {
            question_description.get(1).click();
            Utils.inputTextWithTotoro("test01");
            Utils.clickText("问题描述");

        }

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
     * 确认提交成功
     */
    public void confirmSuccess() {
//       sleep(5000);
        Utils.checkExistOfText("提交成功");
    }

    public void choosePlainByModeNew(String mode) {
        Utils.waitForText("现在出发", 10);
        clickOperateCloseIcon();
        if (!Utils.waitForText(mode, 2)) {
            //根据元素位置横滑tab
            double Y = now_go.getLocation().getY() + 20;
            double height = driver.getWindowSize().height;
            double percent = Y / height;
            percent = (double) Math.round(percent * 100) / 100;
            Utils.swipeWithAdb(0.8, percent, 0.4, percent, 1000);
        }
        sleep(1000);
        Utils.clickText(mode);
        if (MobilePlatform.IOS.equalsIgnoreCase(AppBaseCase.platform) && mode.equals("不坐地铁")) {
            no_subway.click();
        }
        Utils.waitForContainsText("分", 10);
    }

    /**
     * 点击公交结果列表的第一条数据
     */
    public void chooseFirstBusTripInList() {
        Utils.waitForContainsText("分",10);
        clickOperateCloseIcon();
        Utils.click(0.5, 0.42);
    }

    /**
     * 点击步行路线错误
     */
    public void walkRouteError() {
        Utils.clickText("步行路线错误");
        sleep(3000);
    }

    /**
     * 车站错误
     */
    public void stationError() {
        Utils.clickText("车站错误");
        sleep(2000);
    }

    /**
     * 道路不通
     */
    public void routeBlocked() {
        Utils.clickText("道路不通");
        sleep(3000);
    }

    /**
     * 点击选择位置
     */
    public void chooseLocation() {
        Utils.swipeUntilTextDisplay("点击编辑所在位置");
        Utils.clickText("点击编辑所在位置");
    }

    /**
     * 点击确认选点
     */
    public void sureToChoosePointOnMap() {
        Utils.clickText("确认选点");
        sleep(1000);
    }

    /**
     * 输入问题描述
     *
     * @param text 定位控件的文案
     * @param info 要输入的内容
     */
    public void enterQuestionDes(String text, String info) {
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            Utils.swipeUntilTextDisplay(text);
            Utils.clickText(text);
            Utils.inputTextWithTotoro(info);
        } else {
            driver.scrollUp();
            sleep(2000);
            question_description.get(1).click();
            Utils.inputTextWithTotoro(info);
            Utils.clickText("问题描述");
        }
    }

    /**
     * 定位不准
     */
    public void currentPositionError() {
        Utils.clickText("定位不准");
        sleep(2000);
    }

    /**
     * 点击其他问题
     */
    public void otherQuestion() {
        Utils.clickText("其他问题");
        sleep(3000);
    }

    /**
     * 输入联系方式
     */
    public void enterPhoneNum(String num, int i) {
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            Utils.swipeUntilTextDisplay("方便我们联系您核实情况");
            Utils.clickText("方便我们联系您核实情况");
            Utils.inputTextWithTotoro(num);
        } else {
            driver.scrollUp();
            sleep(2000);
            phone_number.get(i).click();
            Utils.inputTextWithTotoro(num);
            Utils.clickText("您的联系电话");
        }

    }

    /**
     * 选中定位到其他地方
     */
    public void locationToOther() {
        Utils.clickText("定位到其他地方");
        sleep(3000);
    }


    /**
     * 进入我的页面
     */
    public void goMy() {
        try {
            if (mine_icon.isDisplayed()) {
                mine_icon.click();
                sleep(3000);
            }
        } catch (Throwable e) {
            Utils.clickText("我的");
            sleep(3000);
            if (Utils.waitForText("下次再说", 2))
                Utils.clickText("下次再说");
        }

    }
    /**
     * 回到首页
     */
    public void backToMap() {
        if (Utils.waitForText("首页", 2)) {
            Utils.clickText("首页");
        } else {
            driver.back();
        }
    }

    /**
     * 进入路径页面
     */
    public void goRoute() {
        if (Utils.waitForText("路线", 2)) {
            Utils.clickText("路线");
        } else {
            //910及以上改版
            route_line_icon.click();
        }

    }

    public void searchStartStopPointForWalk(String startPoint, String endPoint) {
        id_satrtInputLine.click();
        Utils.inputTextWithTotoro(startPoint);
        sleep(5000);
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            main_content_rl.click();
        }else {
            element_typecell.get(0).click();
        }
        id_endInputLine.click();
        Utils.inputTextWithTotoro(endPoint);
        sleep(5000);
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            main_content_rl.click();
        }else {
            element_typecell.get(0).click();
        }
    }

    /**
     * 进入路线详情
     */
    public void clickRoadDetail() {
        Utils.waitForContainsText("分", 10);
        Utils.click(0.07,0.95);
//        IDLRect idlRect = driver.searchImgInCurrentPage(new File(System.getProperty("user.dir") + "/pic/feedback/路线详情.jpg"));
//        if (idlRect != null)
//            idlRect.click(driver);
//        else{
//
//        }
    }

    /**
     * 点击报错
     */
    public void clickError() {
        sleep(2000);
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            IDLRect idlRect = driver.searchImgInCurrentPage(new File(System.getProperty("user.dir") + "/pic/feedback/报错2.jpg"));
            if (idlRect != null)
                idlRect.click(driver);
        }else {
            Utils.clickText("报错");
        }
    }

    /**
     * 点击目的地错误
     */
    public void clickWrongdestination() {
        Utils.clickText("目的地错误");
    }


    public void swipeResultByTimes(int times) {
        int i = 0;
        while (i < times) {
            Utils.swipe(0.5, 0.7, 0.5, 0.1, 100);
            customLog.log("滑动第" + i + "次");
            i++;
        }
    }


}
