package com.totoro.auto.pageobject;

import com.alipay.auto.common.BaseCase;
import com.totoro.auto.util.Utils;
import com.totoro.client.annotation.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class NaviEndReportErrorPage extends BaseCase {
    private int timeOut = 5000;

    @AndroidFindBy (id = "com.autonavi.minimap:id/edit_input")
    private WebElement edit_input;


    public void clickPlanDetour() {
        Utils.clickText("方案绕路");
    }
    /**
     *点击道路不通
     */
    public void clickRouteBlocked() {
        Utils.clickText("道路不通");
    }

    /**
     * 点击禁转向
     */
    public void clickForbiddenTrans() {
        Utils.clickText("禁转向");
    }

    /**
     * 点击摄像头位置有误
     */
    public void eleCameraError(){
        Utils.clickText("摄像头位置有误");
    }

    /**
     * 点击提示限速有误
     */
    public void speedLimitError(){
        Utils.clickText("限速提示有误");
    }

    /**
     * 点击方案有违规驾驶
     */
    public void illegalOperation(){
        Utils.clickText("方案有违规驾驶");
    }

    public void submitErrorMessage(String descripe,String phoneNumber){
        if(descripe.length()>0){
            Utils.clickContainsText("请描述问题");
            edit_input.click();
            Utils.inputTextWithTotoro(descripe);
            Utils.clickText("完成");
        }

        if(phoneNumber.trim().length() > 0){
            Utils.clickText("请输入您的手机号码");
            edit_input.click();
            Utils.inputTextWithTotoro(phoneNumber);
            Utils.clickText("完成");
        }
        Utils.clickText("提交");
        sleep(3000);
    }

}
