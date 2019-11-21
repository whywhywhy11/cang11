package com.totoro.auto.pageobject;

import com.alipay.auto.common.BaseCase;
import com.totoro.auto.util.Utils;
import com.totoro.client.annotation.AndroidFindBy;
import com.totoro.client.annotation.iOSFindBy;
import org.openqa.selenium.WebElement;

public class BaseMapPage extends BaseCase {

    protected  int overallTimeout = 1000;

    @AndroidFindBy(xpath = "//*[@text='路线']")
    @iOSFindBy(name = "路线")
    private WebElement route_old;

    @AndroidFindBy(id = "com.autonavi.minimap:id/route_line_icon")
    private WebElement route_new;

    @iOSFindBy(name = "查找地点公交地铁")
    @AndroidFindBy(id = "com.autonavi.minimap:id/txt_hotword")
    private WebElement txt_hotword;




    public void goRoute(){
        Utils.sleep(overallTimeout);
        try {
            route_new.click();
        }catch (Throwable e){
            route_old.click();
        }
    }

    /**
     * 主图 -- 工具箱
     */
     public void goMore(){
         Utils.sleep(500);
         Utils.swipeUntilTextDisplay("更多工具");
         Utils.clickText("更多工具");
         Utils.sleep(500);
     }

    /**
     *主图 -- 工具箱 -- 离线地图
     */
    public void clickOfflineMap(){
        Utils.swipeUntilTextDisplay("离线地图");
        Utils.clickText("离线地图");
        if (Utils.waitForText("继续",overallTimeout)){
            Utils.clickText("继续");
            Utils.clickText("知道了");
        }
    }

    /**
     * 主图长按
     */
    public void clickLongOnScreenRightConner(){
        int height = driver.getScreenSize().getHeight();
        int width = driver.getScreenSize().getWidth();
        Utils.swipe(width*1/2,height*1/2,width*1/8,height*1/2,400);
    }

    /**
     * 主图长按
     */
    public void clickLongOnScreenCenter(){
        int height = driver.getScreenSize().getHeight();
        int width = driver.getScreenSize().getWidth();
        Utils.swipe(width*1/4,height*1/4,width*1/4,height*1/4,400);
    }

    /**
     * 主图点击一框搜
     */
    public void goSearch(){
        sleep(500);
        txt_hotword.click();
        sleep(500);
    }

    /**
     * 主图redesign 点击泛搜词(酒店)
     */
    public void clickHotel(){
        sleep(500);
        Utils.clickText("酒店");
        sleep(overallTimeout);
    }

    /**
     * 主图 -- 附近
     */
    public void goNear(){
        sleep(500);
        Utils.clickText("附近");
        sleep(1000);
    }



}
