package com.totoro.auto.pageobject;

import com.alipay.auto.common.BaseCase;
import com.totoro.auto.base.AppBaseCase;
import com.totoro.auto.misc.CommonRouteTestData;
import com.totoro.auto.util.Utils;
import com.totoro.client.annotation.AndroidFindBy;
import com.totoro.client.annotation.iOSFindBy;
import com.totoro.client.internal.MobilePlatform;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CommonRoutePage extends BaseCase {

    @AndroidFindBy(id = "com.autonavi.minimap:id/tab_tool_bar_img")
    private WebElement tab_tool_bar_img;

    @AndroidFindBy(id = "com.autonavi.minimap:id/ele_guide_close")
    private WebElement ele_guide_close;

    @AndroidFindBy(id = "com.autonavi.minimap:id/route_edit_start_text")
    @iOSFindBy(name = "我的位置")
    private WebElement route_edit_start_text;

    @AndroidFindBy(id = "com.autonavi.minimap:id/route_edit_end_text")
    @iOSFindBy(name = "输入终点")
    private WebElement route_edit_end_text;

    @AndroidFindBy(id = "com.autonavi.minimap:id/sug_list")
    private List<WebElement> sug_list;

    @iOSFindBy(name = "NMButton")
    private WebElement search_btn;

    @iOSFindBy(name = "搜索")
    private WebElement end_search_btn;


    private int overallTimeout = 500;


    public void setTrafficType(String typeName) {
        tab_tool_bar_img.click();

        if (CommonRouteTestData.routeTypeRide.equals(typeName)) {
            driver.findElementByXPath("//*[@text='" + typeName + "']");
            ele_guide_close.click();
        } else if (CommonRouteTestData.routeTypeCall.equals(typeName)) {
            driver.findElementByXPath("//*[@text='" + typeName + "']");
            if (driver.getPageSource().contains("立即去抢")) {
                driver.back();
            }
        } else {
            driver.findElementByXPath("//*[@text='" + typeName + "']");
        }

    }

    /**
    1015tab合并，驾车、打车、公交地铁不在更多里
     */
    public void setTrafficTypeForNew(String typeName, boolean isClickMore){
        if (isClickMore){
            Utils.clickText("更多");

        }
        if (CommonRouteTestData.routeTypeRide.equals(typeName)){
            Utils.clickText(CommonRouteTestData.routeTypeRide);
            sleep(1000);
            ele_guide_close.click();
        }else if (CommonRouteTestData.routeTypeCall.equals(typeName)){
            Utils.clickText(CommonRouteTestData.routeTypeCall);
            if (driver.getPageSource().contains("立即去抢")) {
                driver.back();
            }
        }else {
            Utils.clickText(typeName);
        }
        sleep(2000);

    }

    /**
     * 输入起点,并选中列表中第一个地点
     */
    public void searchStartPoint(String startPoint){
        sleep(overallTimeout*4);
        route_edit_start_text.click();
        sleep(overallTimeout);
        route_edit_start_text.clear();
        sleep(overallTimeout);
        Utils.inputTextWithTotoro(startPoint);
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)){
            sug_list.get(0).click();
            if (Utils.waitForText("确定起点",overallTimeout*2)){
                searchFromList("确定起点");
            }
        }
        else{
            search_btn.click();
            searchFromList("确认起点");
        }
       sleep(overallTimeout*2);

    }

    /**
     * 输入终点,并选中列表中第一个地点
     */
    public void searchEndPoint(String endPoint){
        sleep(overallTimeout*2);
        route_edit_end_text.click();
        sleep(overallTimeout);
        route_edit_end_text.clear();
        sleep(overallTimeout);
        Utils.inputTextWithTotoro(endPoint);
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)){
            sug_list.get(0).click();
            if (Utils.waitForText("确定终点",overallTimeout*2)){
                searchFromList("确定终点");
            }
        }
        else{
            end_search_btn.click();
            searchFromList("确认终点");

        }
        sleep(overallTimeout*5);

    }

    /**
     * poi搜索框列表选择
     */
    public void searchFromList(String name){
        WebElement startPointList = driver.findElementByName(name);
        Rectangle rect = startPointList.getRect();
        int height = rect.getHeight();
        int width = rect.getWidth();
        int x = rect.getX();
        int y= rect.getY();
        double realX = x + 0.5*width;
        double realY = y + 1.5*height;
        Utils.click(realX,realY);
    }


}
