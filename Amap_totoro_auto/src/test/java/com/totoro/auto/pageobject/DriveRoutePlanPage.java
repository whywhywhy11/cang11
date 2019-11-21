package com.totoro.auto.pageobject;

import com.alipay.auto.common.BaseCase;
import com.totoro.auto.util.Utils;
import com.totoro.client.annotation.AndroidFindBy;
import com.totoro.client.deeplearning.adstract.IDLRect;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;

public class DriveRoutePlanPage extends BaseCase {

    private String schemeRoutePlan = "androidamap://openFeature?featureName=FromTo&sourceApplication=amap&page=car";
    protected int overallTimeout = 5000;

    @AndroidFindBy(id = "com.autonavi.minimap:id/route_edit_add")
    private WebElement route_edit_add;

    @AndroidFindBy(id = "com.autonavi.minimap:id/route_edit_start_text")
    private WebElement route_edit_start_text;

    @AndroidFindBy(id = "com.autonavi.minimap:id/route_edit_mid_text")
    private WebElement route_edit_mid_text;

    @AndroidFindBy(id = "com.autonavi.minimap:id/route_edit_end_text")
    private WebElement route_edit_end_text;

    private String main_contsent_rl_id = "com.autonavi.minimap:id/main_content_rl";






    /**
     * scheme打开后，会弹出list，点击list的
     */
    public void confirmPoint() {
        WebElement listView = driver.findElementById("android:id/list");
        if (listView != null) {
            listView.click();
        }


    }

    /**
     * 在驾车的路线规划结果页，点击更多，然后点击报错
     */
    public void stepFeedBackAtRoutePlan() {
        sleep(2000);
        IDLRect more = driver.searchImgInCurrentPage(new File(System.getProperty("user.dir") + "/pic/feedback/更多.jpg"));
        if(more !=  null){
            more.click(driver);// 点击路线规划结果页右上角的更多按钮
        }

//        Rectangle rectangle = Utils.getPointByContainsText("已关闭");
//        float x = rectangle.x + rectangle.width / 2;
//        float y = rectangle.y + rectangle.height + 50;
//        driver.click(x, y);
//        driver.findElementById("com.autonavi.minimap:id/list_view").findElements(By.id("com.autonavi.minimap:id/widget_img")).get(2).click();
        Utils.clickText("报错");
//        IDLRect idlRect = driver.searchImgInCurrentPage(new File(System.getProperty("user.dir") + "/pic/feedback/报错.jpg"));
//        idlRect.click(driver);

    }

    /**
     * 进入驾车的路线规划页
     */
    public void goRoutePlan(){
        caseUtils.scheme(schemeRoutePlan);
        sleep(overallTimeout * 2);
        Utils.clickOnTextIfExist("我知道了");
    }

    /**
     * 点击增加途径点
     */
    public void addViaPoint(){
        route_edit_add.click();
        sleep(1000);
    }

    public int selectSug(String sug) {
        List<WebElement> elementList = driver.findElements(By.id(main_contsent_rl_id));
        for (int i=0; i<elementList.size(); i++) {
            if(elementList.get(i).getText() == sug) {
                return i;
            }
        }
        return 1;
    }
    public void chooseSug(int sugIndex) {
        WebElement element = driver.findElements(By.id(main_contsent_rl_id)).get(sugIndex);
        element.click();
    }

    public void setStartViaEnd(String start,String via,String end){
        //输入起点
        route_edit_start_text.click();
        Utils.inputTextWithTotoro(start);
        sleep(3000);
        chooseSug(1);
        sleep(overallTimeout*2);
        //输入途经点
        route_edit_mid_text.click();
        Utils.inputTextWithTotoro(via);
        chooseSug(1);
        sleep(overallTimeout * 2);
        //输入终点
        route_edit_end_text.click();
        Utils.inputTextWithTotoro(end);
        chooseSug(1);
        sleep(3000);
    }

    public void swipeUpRouteDetaill() {
        Utils.swipe(0.5, 0.8, 0.5, 0.1, 100, 3);
    }

    /**
     * 开始导航
     */
    public void startNaving(){
        Utils.clickText("开始导航");
        Utils.clickOnTextIfExist("继续");
        Utils.clickOnTextIfExist("同意");
        Utils.clickOnTextIfExist("跳过");
        Utils.clickOnTextIfExist("确定");
        if(Utils.waitForText("允许修改系统设置", overallTimeout)) {
            Utils.clickText("允许修改系统设置");
            Utils.pressBack();;
        }
    }



}
