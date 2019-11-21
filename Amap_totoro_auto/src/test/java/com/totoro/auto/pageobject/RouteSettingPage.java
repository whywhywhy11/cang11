package com.totoro.auto.pageobject;

import com.alipay.auto.common.BaseCase;
import com.totoro.auto.util.Utils;
import com.totoro.client.annotation.AndroidFindBy;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class RouteSettingPage extends BaseCase {

    private String schemeSetting = "amapuri://drive/settings";

    @AndroidFindBy(id = "com.autonavi.minimap:id/car_limit_checkbox")
    private WebElement car_limit_checkbox;

    /**
     * 进入导航设置页面
     */
    public void goRouteSetting() {
        caseUtils.scheme(schemeSetting);
        //861添加
        Utils.clickText("货车");
        Utils.clickText("小客车");
    }

    /**
     * 打开小客车避开限行的开关
     */
    public void openTrafficRestriction() {
        Assert.assertTrue("打开小客车避开限行开关的操作失败", Utils.setCheckBoxCheckedByResourceId(car_limit_checkbox));
    }


}
