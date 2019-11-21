# 框架说明

Totoro是由支付宝工程效能团队自主研发的支持Android、iOS和H5自动化测试框架，集成了团队多年在移动端的积累和经验，
android基于UIautomator2，ios基于WDA, 全面支持Android, IOS以及H5的自动化测试

# 框架优势

1. 安装简单

    环境准备，Android需要android sdk, java, maven， IOS平台需要Xcode, node, java, maven及apple账号
2. 执行高效

    server启动后，脚本和手机直接通信，没有中间层的转发，执行更高效。
3. 执行稳定可靠

    手机里的server运行稳定，并在框架层提供重新启动机制，保证执行延续稳定
4. 集成很多难点的解决方案（设备断开，弹窗，H5...）

    测试中，Android的adb server的断开、设备的掉线、弹窗处理， ios的弹窗处理，H5的测试等，都集成了通用的解决方案。

环境安装文档：https://lark.alipay.com/oeqy62/sgffza/sb88z7

# 模块结构说明



# 接入说明

Totoro Playground工程是集团内部使用Totoro的一个Demo，并支持在SLM云端执行用例。继承BaseCase后(参考com.alipay.auto.test.CommonCase)， 能够无缝的实现本地调试和在[蚂蚁云测Solomon平台](http://slm.alipay.net)运行
本地调试的时候，需要手动指定platform,在slm平台运行的时候，会自动获取platform，Udid等信息。

添加mvn仓库，获取TotoroClient
```
<repository>
    <id>mobile-snapshot-1</id>
        <url>http://mvn.dev.alipay.net:8081/artifactory/content/repositories/mobile-snapshot</url>
            <snapshots>
            <enabled>true</enabled>
           </snapshots>
</repository>
```

添加依赖
```
<dependency>
   <groupId>com.alipay.totoro</groupId>
   <artifactId>bizlib</artifactId>
   <version>1.0.0-SNAPSHOT</version>
</dependency>
```

用例脚本必须继承BaseCase，可参考com.alipay.auto.test.CommonCase，

```java
public void setCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //platform必须要填
        capabilities.setCapability(Capability.PLATFORM, platform);
        if(MobilePlatform.ANDROID.equalsIgnoreCase(platform)){
            //可不填，如果为空，会自动选取本地的设备sn号
            capabilities.setCapability(Capability.DEVICEID, "");
            capabilities.setCapability(Capability.ANDROID_ACTIVITY, "com.eg.android.AlipayGphone.AlipayLogin");
            capabilities.setCapability(Capability.PACKAGE, "com.eg.android.AlipayGphone");
        }else if(MobilePlatform.IOS.equalsIgnoreCase(platform)){
            //可不填，如果为空，会自动选取本地的设备sn号
            capabilities.setCapability(Capability.DEVICEID, "");
            capabilities.setCapability(Capability.PACKAGE, "com.alipay.wallet.rc");
        }
        setCustomCapabilities(capabilities);
    }
```
这个方法是自定义参数信息，Android和ios需要填相应的参数，会在执行中被回调。
在本地调试platform，需要手动切换，在slm平台运行的时候，这些参数信息会自动被替代，不需要改一行代码，就能再slm平台上运行


# 针对头疼的弹窗，提供了专门的解决方案

`driver.handleAlertByValue("朕知道了");`

Android平台是通过关键字匹配来处理弹窗，虽然兼容了slm平台所有的机器，但是仍然不能包含所有，这个api会追加加新的关键字，便于灵活处理
IOS平台处理指定关键字的弹窗

`driver.acceptAlert();`

Android平台处理含有正向关键字的弹窗，目前包含有 好，允许，始终允许，总是允许，同意，确定，确 定。
IOS平台处理系统弹窗的正向按钮

`driver.dismissAlert();`

Android平台处理含有负向关键字的弹窗，目前包含有 取消，取 消，放弃。
IOS平台处理系统弹窗的负向按钮

# api说明
```java
WebElement ele = driver.findElementByName("登录");
//按文本查找，如果没找到会抛错，

WebElement ele1 = driver.findElementByNameWithoutExp("登录");
//按文本查找, 如果找不到，会返回null， 所以，使用的时候需要判断是否为空
```
很多类似这样的api会成对出现， 区别就是在找不到控件的时候，上面的api会抛错，下面的api会返回一个null.

详见API清单：https://lark.alipay.com/oeqy62/sgffza/fqv5lo

# 问题反馈
- 如果您在使用中遇到任何的问题，可以加入钉钉群 ![image.png | center | 100x200](https://private-alipayobjects.alipay.com/alipay-rmsdeploy-image/skylark/png/9febfaf0-f3c1-48bd-82c7-7affab9cd5ee.png)反馈。
- 你还可以在[蚂蚁云测Solomon平台](http://slm.alipay.net) 任何一个页面点击"意见反馈"进行反馈
- 或是在当前的项目[新建ISSUE](http://gitlab.alipay-inc.com/bjee/playground/issues/new)
- 直接联系@花沫 @松茸 (iOS相关) @伍果 @睿远 (Android相关)




