package com.totoro.auto.pageobject;

import com.alipay.auto.common.BaseCase;
import com.totoro.auto.util.Utils;


public class RouteReportError extends BaseCase {

    private int timeOut = 3000;
    /**
     * 输入错误信息的描述
     *
     * @param text
     */
    public void submitErrorMessage(String text) {
        Utils.clickContainsText("请描述问题");
        Utils.inputTextWithTotoro(text);
    }

    /**
     * 点击页面的提交按钮
     */
    public void submit() {
        Utils.clickContainsText("提交");
    }

    /**
     * 点击方案绕路
     */
    public void clickPlanDetour(){
        Utils.clickContainsText("方案绕路");
    }

    /**
     * 移动图片标记打破绕路路口的位置
     */
    public void moveCrossingLocation(){

        Utils.swipe(0.4, 0.5, 0.4, 0.3, timeOut*2);
        Utils.clickText("确定");
    }

    public void submitErrorMessage(String descripe,String phoneNumber){
        if(descripe.length()>0){
            Utils.clickText("请描述问题");
            Utils.inputTextWithTotoro(descripe);
            Utils.clickText("完成");
        }

        if(phoneNumber.trim().length() > 0){
            Utils.clickText("请输入您的手机号码");
            Utils.inputTextWithTotoro(phoneNumber);
            Utils.clickText("完成");
        }
        Utils.clickText("提交");
        sleep(5000);
    }

    /**
     *点击道路不通
     */
    public void clickRouteBlocked(){
        Utils.clickText("道路不通");
    }
    /**
     *点击施工封闭
     */
    public void clickConstructionRouteBlocked(){
        Utils.clickText("施工封闭");
    }

    /**
     *点击其他问题
     */
    public void clickOthers(){
        Utils.clickText("其他问题");
    }
}
