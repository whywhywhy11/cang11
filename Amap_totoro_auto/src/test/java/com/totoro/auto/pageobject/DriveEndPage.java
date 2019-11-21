package com.totoro.auto.pageobject;

import com.alipay.auto.common.BaseCase;
import com.totoro.auto.util.Utils;
import com.totoro.client.annotation.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class DriveEndPage extends BaseCase {

    @AndroidFindBy( id = "com.autonavi.minimap:id/destination_error_category")
    private WebElement destination_error_category;
    protected int overallTimeout = 5000;

    public void clickReportError() {
        int y = driver.getWindowSize().height-30;
        Utils.click(280, y);
        sleep(1000);
        if(! Utils.waitForText("目的地错误", overallTimeout)){
            Utils.pressBack();
            Utils.click(280, y);
        }
//        destination_error_category.click();
    }
}
