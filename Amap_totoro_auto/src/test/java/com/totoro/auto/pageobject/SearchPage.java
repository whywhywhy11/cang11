package com.totoro.auto.pageobject;

import com.alipay.auto.common.BaseCase;
import com.totoro.auto.base.AppBaseCase;
import com.totoro.auto.misc.TestData;
import com.totoro.auto.util.Utils;
import com.totoro.client.annotation.AndroidFindBy;
import com.totoro.client.annotation.iOSFindBy;
import com.totoro.client.internal.MobilePlatform;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by grace on 2019/11/5.
 */
public class SearchPage extends BaseCase {

    @AndroidFindBy(className = "android.support.v7.widget.RecyclerView")
    private List<WebElement> search_list;

    @AndroidFindBy(className = "android.widget.EditText")
    @iOSFindBy(name = "搜索")
    private WebElement edit_text;

    @iOSFindBy(name = "NMButton")
    private WebElement search_btn;

    /**
     * 搜索页列表选中某一个
     */
    public void clickItem(){
        sleep(1000);
        if (MobilePlatform.ANDROID.equalsIgnoreCase(AppBaseCase.platform)){
            search_list.get(0).click();
        }else {
            Utils.click(180,180);
        }
        sleep(500);
    }
    /**
     * check在POI详情页
     */
    public void checkInPOIpage(){
        if (Utils.waitForText("查看详情",500)){
            Utils.clickText("查看详情");
        }
        Utils.checkExistOfText("搜周边");

    }

    /**
     *附近页进入美食/酒店等页面
     */
    public void chooseSubTiele(String typename){
        Utils.clickText(typename);
    }

    /**
     * 附近 -- 美食
     */
    public void goFoodList(){
        if (MobilePlatform.ANDROID.equals(AppBaseCase.platform)){
            chooseSubTiele(TestData.food);
        }else {
            //点击美食icon位置
            Utils.click(48,216);
        }
        sleep(1000);
    }

    /**
     * check在搜索结果页
     */
    public void checkInResultListPage(){
        Utils.checkExistOfText("姥姥家春饼店(望京店)");
    }

    /**
     * 搜索首页一筐搜
     */
    public void searchByKeyword(String keyword){
        edit_text.clear();
        Utils.inputTextWithTotoro(keyword);
        sleep(500);
        if (MobilePlatform.IOS.equalsIgnoreCase(AppBaseCase.platform)){
           search_btn.click();
        }
        sleep(500);

    }

    /**
     * check是否在主图搜索列表
     */
    public void checkInSearchResult(){
        Utils.checkExistOfText("<font color=&apos;#4287ff&apos;>绿茶餐厅</font>(远洋未来广场店)");
    }
}
