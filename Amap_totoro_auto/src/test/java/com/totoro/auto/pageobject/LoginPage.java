package com.totoro.auto.pageobject;

import com.alipay.auto.common.BaseCase;
import com.totoro.auto.base.AppBaseCase;
import com.totoro.auto.util.Utils;
import com.totoro.client.annotation.AndroidFindBy;
import com.totoro.client.annotation.iOSFindBy;
import com.totoro.client.deeplearning.adstract.IDLRect;
import com.totoro.client.internal.MobilePlatform;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;

public class LoginPage extends BaseCase {

    @AndroidFindBy(xpath = ".//*[@content-desc='设置']")
    @iOSFindBy(id = "设置")
    private WebElement setting_icon;

    @iOSFindBy(name = "账号与安全")
    private WebElement account;

    @AndroidFindBy(id = "com.autonavi.minimap:id/route_line_icon")
    private WebElement route_line_icon;

    @AndroidFindBy(className ="android.widget.EditText")
    @iOSFindBy(className = "XCUIElementTypeTextField")
    private List<WebElement> edittext_list;

    @AndroidFindBy(id = "com.autonavi.minimap:id/button2")
    @iOSFindBy(name = "退出")
    private WebElement button2;

    @AndroidFindBy(id = "com.autonavi.minimap:id/button1")
    private WebElement button1;

    @iOSFindBy(className = "XCUIElementTypeSecureTextField")
    private List<WebElement> password_list;


    /**
     *  点击登录/注册
     */
    public void clickStepLogin() {
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)) {
            Utils.clickText("点击登录");
        }else{
            setting_icon.click();
            sleep(1000);
            account.click();
        }
    }


    /**
     * 账号登录时会弹出是否要绑定手机的页面，如果出现就点击返回按钮
     */
    public void cancleBindeTel() {
        if (Utils.waitForContainsText("绑定手机号",2)) {
            driver.back();
            sleep(2000);
        }
    }

    /**
     *
     * @param ifUse 是否使用本地记录
     */
    public void LocalHistory(boolean ifUse){
        if(Utils.waitForText("本地已有使用记录",8)){
            if(ifUse){
                button2.click();
            }else{
                button1.click();
            }
        }
    }

    /**
     * 登录
     *
     */
    public void login() {
        String userName = "amap_13146442557ACZR2OQEI";
        String password = "460576";
        if (Utils.waitForContainsText("lv",2) || !Utils.waitForText("登录后开启足迹地图",2)) {
            customLog.log("已处于登录状态");// 已登录状态直接返回
            return;
        }
        clickStepLogin();
        sleep(2000);
        Utils.clickOnTextIfExist("其他登录方式");
        sleep(3000);
        Utils.clickOnTextIfExist("密码登录");
        sleep(3000);
        edittext_list.get(0).click();
        Utils.inputTextWithTotoro(userName);
        try {
            edittext_list.get(1).click();
        }catch (Throwable e){
            password_list.get(0).click();
        }
        Utils.inputTextWithTotoro(password);
        sleep(2000);
        Utils.clickText("登录");
        sleep(3000);
        if (Utils.waitForText("账号与安全",2)) {
            Utils.clickText("返回");
            sleep(1000);
        }
        cancleBindeTel();
        LocalHistory(false);
        sleep(5000);
        Utils.clickText("全部收取");
        sleep(5000);
        Utils.clickText("下次再说");
        customLog.log("登录操作结束");
        // 绑定手机号
        sleep(3000);
    }

    /**
     * 875及以上版本退出
     */
    public void logoutIfLogin() {
        if (Utils.waitForContainsText("Lv", 2)) {
            setting_icon.click();
            sleep(2000);
            Utils.swipeUntilTextDisplay("退出登录");
            Utils.clickText("退出登录");
            button2.click();
            sleep(6000);
            customLog.log("退出登录完成");
        }

    }

}
