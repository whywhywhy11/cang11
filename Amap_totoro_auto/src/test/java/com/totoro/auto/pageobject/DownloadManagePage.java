package com.totoro.auto.pageobject;

import com.totoro.auto.util.Utils;

/**
 * @author beishuo
 * @version V1.0
 * @Title: DownloadManagePage.java
 * @Package com.totoro.auto.pageobject
 * @Description: 下载管理页面
 * @date 2019/10/29 12:40
 */
public class DownloadManagePage extends CommonBasePage {

    /**
     * 根据 cityName 删除数据
     *
     * @param cityName
     */
    public void deleteOfflineData(String cityName) {

        Utils.clickOnTextIfExist("继续");
        Utils.clickOnTextIfExist("知道了");

        if (Utils.waitForText("基础功能包+直辖市", 2)) {

            Utils.clickText("基础功能包+直辖市");

            if (Utils.waitForText(cityName, 2)) {
                Utils.clickText(cityName);
                Utils.clickText("删除");
                Utils.clickText("确定");
            }
        }

        //返回上一页
        Utils.pressBack();

    }

    /**
     * 城市列表
     */
    public void getIntoCityList() {

        Utils.clickOnTextIfExist("继续");
        Utils.clickOnTextIfExist("知道了");
        Utils.clickText("城市列表");
    }

    /**
     * 下载离线数据
     *
     * @param cityName
     */
    public void downloadDataForCity(String cityName) {

        Utils.clickText(cityName);

        if (Utils.waitForContainsText("下载地图+导航", 2)) {
            Utils.clickContainsText("下载地图+导航");
            Utils.clickOnTextIfExist("下载(");
            //等待10分钟下载地图及导航离线数据
            sleep(600000);
        }

        //返回上一页
        Utils.pressBack();

    }

    /**
     * 检查是否进入离线地图页面
     */
    public void checkInDownloadPage(){
        Utils.sleep(500);
        Utils.checkExistOfText("基础功能包");
    }


    /**
     * 检查是否在城市列表页
     */
    public void checkInCityListPage(){
        Utils.sleep(500);
        Utils.checkExistOfText("北京市");
    }


}
