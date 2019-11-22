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
        //fan11
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
                int index1 = 0;int f1=0;
                String pp1 ="\"sourcepage\": \"28\"";
                index1 = result.indexOf ( pp1, index1 );
                if(index1>0) {f1=f1+1;}


                if(typeId=="1009") {
                    int index2 = 0;
                    String pp2 = "\"type\": \"1009\"";
                    index2 = result.indexOf ( pp2, index2 );
                    if (index2 > 0) {
                        f1 = f1 + 1;
                    }

                    int index3 = 0;
                    String pp3 = "\"error_id\": \"2805\"";
                    index3 = result.indexOf ( pp3, index3 );
                    if (index3 > 0) {
                        f1 = f1 + 1;
                    }

                    int index4 = 0;
                    String pp4 = "\"subtype\": \"\\u5176\\u4ed6\\u95ee\\u9898\"";
                    index4 = result.indexOf ( pp4, index4 );
                    if (index4 > 0) {
                        f1 = f1 + 1;
                    }

                    int index5 = 0;
                    String pp5 = "\"uDes\": \"test5\"";
                    index5 = result.indexOf ( pp5, index5 );
                    if (index5 > 0) {
                        f1 = f1 + 1;
                    }

                    int index6 = 0;
                    String pp6 = " \"contact\": \"18811304834\"";
                    index6 = result.indexOf ( pp6, index6 );
                    if (index6 > 0) {
                        f1 = f1 + 1;
                    }
                }

                if(typeId=="1006") {
                    int index2 = 0;
                    String pp2 = "\"type\": \"1006\"";
                    index2 = result.indexOf ( pp2, index2 );
                    if (index2 > 0) {
                        f1 = f1 + 1;
                    }

                    int index3 = 0;
                    String pp3 = "\"error_id\": \"2802\"";
                    index3 = result.indexOf ( pp3, index3 );
                    if (index3 > 0) {
                        f1 = f1 + 1;
                    }

                    int index4 = 0;
                    String pp4 = "\"subtype\": \"\\u641c\\u7d22\\u7ed3\\u679c\\u9519\\u8bef\"";
                    index4 = result.indexOf ( pp4, index4 );
                    if (index4 > 0) {
                        f1 = f1 + 1;
                    }

                    int index5 = 0;
                    String pp5 = "\"uDes\": \"test4\"";
                    index5 = result.indexOf ( pp5, index5 );
                    if (index5 > 0) {
                        f1 = f1 + 1;
                    }

                    int index6 = 0;
                    String pp6 = " \"contact\": \"18811304844\"";
                    index6 = result.indexOf ( pp6, index6 );
                    if (index6 > 0) {
                        f1 = f1 + 1;
                    }
                }

                if(typeId=="1007") {
                    int index2 = 0;
                    String pp2 = "\"type\": \"1007\"";
                    index2 = result.indexOf ( pp2, index2 );
                    if (index2 > 0) {
                        f1 = f1 + 1;
                    }

                    int index3 = 0;
                    String pp3 = "\"error_id\": \"2803\"";
                    index3 = result.indexOf ( pp3, index3 );
                    if (index3 > 0) {
                        f1 = f1 + 1;
                    }

                    int index4 = 0;
                    String pp4 = "\"subtype\": \"\\u65e0\\u6cd5\\u4f7f\\u7528\"";
                    index4 = result.indexOf ( pp4, index4 );
                    if (index4 > 0) {
                        f1 = f1 + 1;
                    }

                    int index5 = 0;
                    String pp5 = "\"uDes\": \"test3\"";
                    index5 = result.indexOf ( pp5, index5 );
                    if (index5 > 0) {
                        f1 = f1 + 1;
                    }

                    int index6 = 0;
                    String pp6 = " \"contact\": \"18811304854\"";
                    index6 = result.indexOf ( pp6, index6 );
                    if (index6 > 0) {
                        f1 = f1 + 1;
                    }
                }

                if(typeId=="1012") {
                    int index2 = 0;
                    String pp2 = "\"type\": \"1012\"";
                    index2 = result.indexOf ( pp2, index2 );
                    if (index2 > 0) {
                        f1 = f1 + 1;
                    }

                    int index3 = 0;
                    String pp3 = "\"error_id\": \"2806\"";
                    index3 = result.indexOf ( pp3, index3 );
                    if (index3 > 0) {
                        f1 = f1 + 1;
                    }

                    int index4 = 0;
                    String pp4 = "\"subtype\": \"\\u65e0\\u6cd5\\u5524\\u9192\"";
                    index4 = result.indexOf ( pp4, index4 );
                    if (index4 > 0) {
                        f1 = f1 + 1;
                    }

                    int index5 = 0;
                    String pp5 = "\"uDes\": \"test1\"";
                    index5 = result.indexOf ( pp5, index5 );
                    if (index5 > 0) {
                        f1 = f1 + 1;
                    }

                    int index6 = 0;
                    String pp6 = " \"contact\": \"18811304854\"";
                    index6 = result.indexOf ( pp6, index6 );
                    if (index6 > 0) {
                        f1 = f1 + 1;
                    }
                }

                if(typeId=="1008") {
                    int index2 = 0;
                    String pp2 = "\"type\": \"1008\"";
                    index2 = result.indexOf ( pp2, index2 );
                    if (index2 > 0) {
                        f1 = f1 + 1;
                    }

                    int index3 = 0;
                    String pp3 = "\"error_id\": \"2804\"";
                    index3 = result.indexOf ( pp3, index3 );
                    if (index3 > 0) {
                        f1 = f1 + 1;
                    }

                    int index4 = 0;
                    String pp4 = "\"subtype\": \"\\u8bed\\u97f3\\u52a9\\u624b\\u5efa\\u8bae\"";
                    index4 = result.indexOf ( pp4, index4 );
                    if (index4 > 0) {
                        f1 = f1 + 1;
                    }

                    int index5 = 0;
                    String pp5 = "\"uDes\": \"test6\"";
                    index5 = result.indexOf ( pp5, index5 );
                    if (index5 > 0) {
                        f1 = f1 + 1;
                    }

                    int index6 = 0;
                    String pp6 = " \"contact\": \"18811304854\"";
                    index6 = result.indexOf ( pp6, index6 );
                    if (index6 > 0) {
                        f1 = f1 + 1;
                    }
                }

                if(typeId=="1005") {
                    int index2 = 0;
                    String pp2 = "\"type\": \"1005\"";
                    index2 = result.indexOf ( pp2, index2 );
                    if (index2 > 0) {
                        f1 = f1 + 1;
                    }

                    int index3 = 0;
                    String pp3 = "\"error_id\": \"2801\"";
                    index3 = result.indexOf ( pp3, index3 );
                    if (index3 > 0) {
                        f1 = f1 + 1;
                    }

                    int index4 = 0;
                    String pp4 = "\"subtype\": \"\\u8bed\\u97f3\\u8bc6\\u522b\\u9519\\u8bef\"";
                    index4 = result.indexOf ( pp4, index4 );
                    if (index4 > 0) {
                        f1 = f1 + 1;
                    }

                    int index5 = 0;
                    String pp5 = "\"uDes\": \"test7\"";
                    index5 = result.indexOf ( pp5, index5 );
                    if (index5 > 0) {
                        f1 = f1 + 1;
                    }

                    int index6 = 0;
                    String pp6 = " \"contact\": \"18811304854\"";
                    index6 = result.indexOf ( pp6, index6 );
                    if (index6 > 0) {
                        f1 = f1 + 1;
                    }
                }

                int index7 = 0;
                String pp7 ="\"object_adcode\": \"110000\"";
                index7 = result.indexOf ( pp7, index7 );
                if(index7>0) {f1=f1+1;}


                ff=f1;
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


        if (ff == 7) {
            return  true;
        }
        if (ff != 7) {
            return false;
        }
        return null;
    }






}

