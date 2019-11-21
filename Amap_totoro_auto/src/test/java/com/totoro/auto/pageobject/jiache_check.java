package com.totoro.auto.pageobject;

import com.alipay.auto.common.BaseCase;
import com.totoro.auto.base.AppBaseCase;
import com.totoro.auto.util.Utils;
import com.totoro.client.annotation.AndroidFindBy;
import com.totoro.client.annotation.iOSFindBy;
import com.totoro.client.deeplearning.adstract.IDLRect;
import com.totoro.client.internal.MobilePlatform;
import com.totoro.client.ios.IOSDriver;
import org.junit.Assert;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;

public class jiache_check extends BaseCase {

    public  Boolean check_guihua() {
        sleep ( 5000 );
        WebElement e0 = driver.findElementByXPath ( "//*[@text='开始导航']" );
        if (e0 == null) {
            return false;
        }
        if (e0 != null) {
            return true;
        }

        return null;
    }


    public  Boolean check_daohang() {
        sleep ( 5000 );
        IDLRect e1 = driver.searchImgInCurrentPage(new File(System.getProperty("user.dir") + "/pic/jiache/110.jpg"));
        if (e1 == null) {
            return false;
        }
        if (e1 != null) {
            return true;
        }

        return null;
    }


//以上是导航类校验--------------------------------------------------------------------------------------------------------------------------------------------------------------








    public  Boolean check_fan(String diu, String typeId, String errorId) {
        String Url = "http://qa.client.gaode.test/HuiChuan/getOneResult/";
        String Url1 = Url + "?uid=" + diu + "&typeid=" + typeId + "&errorid=" + errorId;
        sleep ( 5000 );
        int ff =0;


        try {
            CloseableHttpClient client = null;
            CloseableHttpResponse response = null;
            try {
                HttpGet httpGet = new HttpGet ( Url1);

                client = HttpClients.createDefault ();
                response = client.execute ( httpGet );
                HttpEntity entity = response.getEntity ();
                String result = EntityUtils.toString ( entity );
                //System.out.println ( result );

                //解析
                int count1 = 0;int index1 = 0;int f1=0;
                String pp1 ="\"sourcepage\": \"28\"";
                while( ( index1 = result.indexOf(pp1, index1) ) != -1 )
                {
                    index1 = index1+pp1.length();
                    count1++;
                }
                if(count1>0 ){f1=1;}

                int count2 = 0;int index2 = 0;int f2=0;
                String pp2 ="\"type\": \"1009\"";
                while( ( index2 = result.indexOf(pp2, index2) ) != -1 )
                {
                    index2 = index2+pp2.length();
                    count2++;
                }
                if(count2>0 ){f2=1;}

                int count3 = 0;int index3 = 0;int f3=0;
                String pp3 ="\"error_id\": \"2805\"";
                while( ( index3 = result.indexOf(pp3, index3) ) != -1 )
                {
                    index3 = index3+pp3.length();
                    count3++;
                }
                if(count3>0 ){f3=1;}

                int count4 = 0;int index4 = 0;int f4=0;
                String pp4 ="\"subtype\": \"\\u5176\\u4ed6\\u95ee\\u9898\"";
                while( ( index4 = result.indexOf(pp4, index4) ) != -1 )
                {
                    index4 = index4+pp4.length();
                    count4++;
                }
                if(count4>0 ){f4=1;}

                int count5 = 0;int index5 = 0;int f5=0;
                String pp5 ="\"uDes\": \"test5\"";
                while( ( index5 = result.indexOf(pp5, index5) ) != -1 )
                {
                    index5 = index5+pp5.length();
                    count5++;
                }
                if(count5>0 ){f5=1;}

                int count6 = 0;int index6 = 0;int f6=0;
                String pp6 ="\"object_adcode\": \"110000\"";
                while( ( index6 = result.indexOf(pp6, index6) ) != -1 )
                {
                    index6 = index6+pp6.length();
                    count6++;
                }
                if(count6>0 ){f6=1;}


                ff=f1+f2+f3+f4+f5+f6;
                //System.out.println ( ff );

            } finally {
                if (response != null) {
                    response.close ();
                }
                if (client != null) {
                    client.close ();
                }
            }
        }

        catch (Exception e) {
            e.printStackTrace ();
        }


        if (ff == 6) {
            return  true;
        }
        if (ff != 6) {
            return false;
        }
        return null;
    }
}

