package com.totoro.auto.pageobject;

import com.alipay.auto.common.BaseCase;
import com.totoro.auto.util.Utils;

/**
 * Created by grace on 2019/10/30.
 */
public class CarSettingPage extends BaseCase{
    String text_addCar = "车辆添加";

    public void checkInAddCarPage(){
        Utils.checkExistOfText(text_addCar);
    }
}
