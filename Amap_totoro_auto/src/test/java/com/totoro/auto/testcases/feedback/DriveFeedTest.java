package com.totoro.auto.testcases.feedback;

import com.alipay.auto.common.CaseInfo;
import com.totoro.auto.base.AppBaseCase;
import com.totoro.auto.pageobject.DriveRoutePlanPage;
import com.totoro.auto.util.CheckFeedBackResult;
import com.totoro.auto.util.Utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DriveFeedTest extends AppBaseCase {


    @Before
    public void setUp() {
        super.setUp();
        String schemeRoute = "androidamap://route?sourceApplication=softname&t=2&dev=0&dname=天安门&sname=阜荣街首开广场";
        caseUtils.scheme(schemeRoute);
        sleep(7000);
        driveRoutePlanPage.confirmPoint();
        Utils.clickContainsTextIfExist("知道了");
        driveRoutePlanPage.stepFeedBackAtRoutePlan();
    }

    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "4001_1503", description = "路线规划结果图面页报错--道路不通", author = "长微", level = "0")
    public void TC4001_1503() {

        Utils.clickContainsText("道路不通");
        Utils.swipe(0.5, 0.5, 0.6, 0.5, 100);
        Utils.clickContainsText("确定");
        Utils.clickContainsText("单向通行");
        routeReportError.submitErrorMessage("test002");
        Utils.clickText("完成");
        routeReportError.submit();
        sleep(3000);
        Assert.assertTrue("数据回传校验结果失败", CheckFeedBackResult.getResult(Utils.getDeviceId(), "4001", "1503"));

    }

    @Test
    @CaseInfo(ci = true, type = "totoro", bizline = "高德", summary = "5000_1504", description = "路线规划结果图面页报错--其他问题", author = "长微", level = "0")
    public void TC5000_1504() {
        driveRoutePlanPage.stepFeedBackAtRoutePlan();
        Utils.clickContainsText("其他问题");
        Utils.swipe(0.5, 0.5, 0.6, 0.5, 100);
        Utils.clickContainsText("确定");
        routeReportError.submitErrorMessage("test02");
        Utils.clickText("完成");
        routeReportError.submit();
        sleep(4000);
        Assert.assertTrue("数据回传校验结果失败", CheckFeedBackResult.getResult(Utils.getDeviceId(), "5000", "1504"));

    }
}
