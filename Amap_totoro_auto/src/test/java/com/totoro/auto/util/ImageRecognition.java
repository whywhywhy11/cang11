package com.totoro.auto.util;

import com.alipay.auto.common.CommonBaseCase;
import com.totoro.auto.base.AppBaseCase;
import com.totoro.client.internal.MobileDriver;
import com.totoro.client.internal.MobilePlatform;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.features2d.*;
import org.opencv.highgui.Highgui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.opencv.calib3d.Calib3d;


public class ImageRecognition {
    private static Mat source;
    private static Mat dst;
    private static float nndrRatio = 0.7f;//这里设置既定值为0.7，该值可自行调整
    static MobileDriver driver  = CommonBaseCase.driver;
    public static int width = driver.getWindowSize().width;
    public static int height =  driver.getWindowSize().height;

    private static int matchesPointCount = 0;

    public float getNndrRatio() {
        return nndrRatio;
    }

    public void setNndrRatio(float nndrRatio) {
        this.nndrRatio = nndrRatio;
    }

    public static int getMatchesPointCount() {
        return matchesPointCount;
    }

    public void setMatchesPointCount(int matchesPointCount) {
        this.matchesPointCount = matchesPointCount;
    }

    /**
     * 基于特征点的 SURF 匹配的图像识别点击方式，兼容不同分辨率
     *
     * @param driver  WebDriver
     * @param dstPath 用来进行对比的局部图片
     */
    public static void clickBySURFTemplateMatching(String dstPath, int max) {
        try {
            double tapX, tapY;
            int x, y;
            String originalFilePath = System.getProperty("user.dir") + "/pic/screen.png";
            String udid = AppBaseCase.udid;
            x = width;
            y = height;
            if (MobilePlatform.IOS.equalsIgnoreCase(AppBaseCase.platform)) {
                CmdUtil.run("idevicescreenshot -u " + udid + ";mv -f *.png ./pic/screen.png");
            } else {
                CmdUtil.run("adb -s " + udid + " shell screencap -p /data/local/tmp/screenshot.jpg");
                CmdUtil.run("adb -s " + udid + " pull /data/local/tmp/screenshot.jpg ./pic/screen.png");
                CmdUtil.run("adb -s " + udid + " shell rm /data/local/tmp/screenshot.jpg");
            }
            nu.pattern.OpenCV.loadShared();
            Mat templateImage = Highgui.imread(dstPath, Highgui.CV_LOAD_IMAGE_COLOR);
            Mat originalImage = Highgui.imread(originalFilePath, Highgui.CV_LOAD_IMAGE_COLOR);
            MatOfKeyPoint templateKeyPoints = new MatOfKeyPoint();
            //指定特征点算法SURF
            FeatureDetector featureDetector = FeatureDetector.create(FeatureDetector.SURF);
            //获取模板图的特征点
            featureDetector.detect(templateImage, templateKeyPoints);
            //提取模板图的特征点
            MatOfKeyPoint templateDescriptors = new MatOfKeyPoint();
            DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.SURF);
//        Logger.info("提取模板图的特征点");
            descriptorExtractor.compute(templateImage, templateKeyPoints, templateDescriptors);

            //显示模板图的特征点图片
            Mat outputImage = new Mat(templateImage.rows(), templateImage.cols(), Highgui.CV_LOAD_IMAGE_COLOR);
//        Logger.info("在图片上显示提取的特征点");
            Features2d.drawKeypoints(templateImage, templateKeyPoints, outputImage, new Scalar(255, 0, 0), 0);

            //获取原图的特征点
            MatOfKeyPoint originalKeyPoints = new MatOfKeyPoint();
            MatOfKeyPoint originalDescriptors = new MatOfKeyPoint();
            featureDetector.detect(originalImage, originalKeyPoints);
//        Logger.info("提取原图的特征点");
            descriptorExtractor.compute(originalImage, originalKeyPoints, originalDescriptors);

            List<MatOfDMatch> matches = new LinkedList();
            DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);
//        Logger.info("寻找最佳匹配");
            /**
             * knnMatch方法的作用就是在给定特征描述集合中寻找最佳匹配
             * 使用KNN-matching算法，令K=2，则每个match得到两个最接近的descriptor，然后计算最接近距离和次接近距离之间的比值，当比值大于既定值时，才作为最终match。
             */
            descriptorMatcher.knnMatch(templateDescriptors, originalDescriptors, matches, 2);

//        Logger.info("计算匹配结果");
            LinkedList<DMatch> goodMatchesList = new LinkedList();

            //对匹配结果进行筛选，依据distance进行筛选
            matches.forEach(match -> {
                DMatch[] dmatcharray = match.toArray();
                DMatch m1 = dmatcharray[0];
                DMatch m2 = dmatcharray[1];

                if (m1.distance <= m2.distance * nndrRatio) {
                    goodMatchesList.addLast(m1);
                }
            });

            matchesPointCount = goodMatchesList.size();
            //当匹配后的特征点大于等于 max 个，则认为模板图在原图中，该值可以自行调整
            if (matchesPointCount >= max) {
//            Logger.info("SURF算法找到了"+dstPath.split("pic/")[1]+"！");

                List<KeyPoint> templateKeyPointList = templateKeyPoints.toList();
                List<KeyPoint> originalKeyPointList = originalKeyPoints.toList();
                LinkedList<org.opencv.core.Point> objectPoints = new LinkedList();
                LinkedList<Point> scenePoints = new LinkedList();
                goodMatchesList.forEach(goodMatch -> {
                    objectPoints.addLast(templateKeyPointList.get(goodMatch.queryIdx).pt);
                    scenePoints.addLast(originalKeyPointList.get(goodMatch.trainIdx).pt);
                });
                MatOfPoint2f objMatOfPoint2f = new MatOfPoint2f();
                objMatOfPoint2f.fromList(objectPoints);
                MatOfPoint2f scnMatOfPoint2f = new MatOfPoint2f();
                scnMatOfPoint2f.fromList(scenePoints);
                //使用 findHomography 寻找匹配上的关键点的变换
                Mat homography = Calib3d.findHomography(objMatOfPoint2f, scnMatOfPoint2f, Calib3d.RANSAC, 3);

                /**
                 * 透视变换(Perspective Transformation)是将图片投影到一个新的视平面(Viewing Plane)，也称作投影映射(Projective Mapping)。
                 */
                Mat templateCorners = new Mat(4, 1, CvType.CV_32FC2);
                Mat templateTransformResult = new Mat(4, 1, CvType.CV_32FC2);
                templateCorners.put(0, 0, new double[]{0, 0});
                templateCorners.put(1, 0, new double[]{templateImage.cols(), 0});
                templateCorners.put(2, 0, new double[]{templateImage.cols(), templateImage.rows()});
                templateCorners.put(3, 0, new double[]{0, templateImage.rows()});
                //使用 perspectiveTransform 将模板图进行透视变以矫正图象得到标准图片
                Core.perspectiveTransform(templateCorners, templateTransformResult, homography);

                //矩形四个顶点
                double[] pointA = templateTransformResult.get(0, 0);
                double[] pointB = templateTransformResult.get(1, 0);
                double[] pointC = templateTransformResult.get(2, 0);
                double[] pointD = templateTransformResult.get(3, 0);

                int clickX = ((int) pointA[0] + (int) pointB[0])/2;
                int clickY = ((int) pointA[1] + (int) pointD[1])/2;

                File picture = new File(originalFilePath);
                BufferedImage sourceImg = null;
                try {
                    sourceImg = ImageIO.read(new FileInputStream(picture));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tapX = (double) clickX / sourceImg.getWidth() * x;
                tapY = (double) clickY / sourceImg.getHeight() * y;
                AppBaseCase.customLog.log("tapX:" + tapX + "\n");
                AppBaseCase.customLog.log("tapY:" + tapY + "\n");
                driver.click((float)tapX, (float)tapY);
//            Highgui.imwrite(System.getProperty("user.dir") +"/pic/模板图在原图中的位置.jpg", originalImage);
            } else {
                AppBaseCase.customLog.log("SURF算法没有找到"+dstPath.split("pic/")[1]+"！");
            }

//            Highgui.imwrite(System.getProperty("user.dir") +"/pic/模板特征点.jpg", outputImage);
            AppBaseCase.customLog.log("匹配的像素点总数：" + getMatchesPointCount());
        }catch (Throwable e){
            AppBaseCase.customLog.log("SURF图片对比执行失败！");
        }
    }


//    /**
//     * 基于opencv模板对比图像识别方式的点击（概率性存在分辨率不同导致识别不准确的问题）
//     *
//     * @param driver  WebDriver
//     * @param dstPath 用来进行对比的局部图片
//     */
//    public static void clickByOpencvTemplateMatching(WebDriver driver, String dstPath) {
//        double tapX, tapY, startX, startY, endX, endY;
//        int x, y;
//        TouchAction action;
//        String sourcePath = System.getProperty("user.dir") + "/pic/screen.png";
//        Properties prop;
//        prop = CommonAction.loadProperties();
//        String udid = prop.getProperty("udid");
//        if (CommonAction.getPlatform(driver) == CommonAction.IOSPLATFORM) {
//            x = driver.manage().window().getSize().getWidth();
//            y = driver.manage().window().getSize().getHeight();
//            CmdUtil.run("idevicescreenshot -u " + udid + ";mv -f *.png ./pic/screen.png");
//            action = new TouchAction((IOSDriver) driver);
//        } else {
//            x = driver.manage().window().getSize().getWidth();
//            y = driver.manage().window().getSize().getHeight();
//            CmdUtil.run("adb -s " + udid + " shell screencap -p /data/local/tmp/screenshot.jpg");
//            CmdUtil.run("adb -s " + udid + " pull /data/local/tmp/screenshot.jpg ./pic/screen.png");
//            CmdUtil.run("adb -s " + udid + " shell rm /data/local/tmp/screenshot.jpg");
//            action = new TouchAction((AndroidDriver) driver);
//        }
//        //压缩图片
//        reduceImg(sourcePath, 734, 1308, null);
//        nu.pattern.OpenCV.loadShared();
//        //将文件读入为OpenCV的Mat格式
//        source = Highgui.imread(sourcePath);
//        dst = Highgui.imread(dstPath);
//        //创建于原图相同的大小，储存匹配度
//        Mat result = Mat.zeros(source.rows(), source.cols(), CvType.CV_32FC1);
//        //调用模板匹配方法
//        Imgproc.matchTemplate(source, dst, result, Imgproc.TM_SQDIFF);
//        //规格化
//        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1);
//        //获得最可能点，MinMaxLocResult是其数据格式，包括了最大、最小点的位置x、y
//        Core.MinMaxLocResult mlr = Core.minMaxLoc(result);
//        Point matchLoc = mlr.minLoc;
//        File picture = new File(sourcePath);
//        BufferedImage sourceImg = null;
//        try {
//            sourceImg = ImageIO.read(new FileInputStream(picture));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        startX = matchLoc.x / sourceImg.getWidth() * x;
//        startY = matchLoc.y / sourceImg.getHeight() * y;
//        endX = (double) dst.width() / sourceImg.getWidth() * x;
//        endY = (double) dst.height() / sourceImg.getHeight() * y;
//        tapX = (startX + startX + endX) / 2;
//        tapY = (startY + startY + endY) / 2;
////        Logger.info("tapX:" + tapX + "\n");
////        Logger.info("tapY:" + tapY + "\n");
//        action.tap((new Double(tapX)).intValue(), (new Double(tapY)).intValue()).perform();
//    }


    /**
     * 图片压缩
     *
     * @param imgsrc
     * @param widthdist  宽
     * @param heightdist 高
     * @param rate
     */
    public static void reduceImg(String imgsrc, int widthdist, int heightdist, Float rate) {
        try {
            File srcfile = new File(imgsrc);
            // 检查文件是否存在
            if (!srcfile.exists()) {
                return;
            }
            // 如果rate不为空说明是按比例压缩
            if (rate != null && rate > 0) {
                // 获取文件高度和宽度
                int[] results = getImgWidth(srcfile);
                if (results == null || results[0] == 0 || results[1] == 0) {
                    return;
                } else {
                    widthdist = (int) (results[0] * rate);
                    heightdist = (int) (results[1] * rate);
                }
            }
            // 开始读取文件并进行压缩
            Image src = javax.imageio.ImageIO.read(srcfile);
            BufferedImage tag = new BufferedImage(widthdist,
                    heightdist, BufferedImage.TYPE_INT_RGB);

            tag.getGraphics().drawImage(
                    src.getScaledInstance(widthdist, heightdist,
                            Image.SCALE_SMOOTH), 0, 0, null);

            String formatName = imgsrc.substring(imgsrc.lastIndexOf(".") + 1);
            ImageIO.write(tag, /*"GIF"*/ formatName /* format desired */, new File(imgsrc) /* target */);
            //   out.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public static int[] getImgWidth(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int result[] = {0, 0};
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            result[0] = src.getWidth(null); // 得到源图宽
            result[1] = src.getHeight(null); // 得到源图高
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 裁剪图片方法
     * @param bufferedImage 图像源
     * @param startX 裁剪开始x坐标
     * @param startY 裁剪开始y坐标
     * @param endX 裁剪结束x坐标
     * @param endY 裁剪结束y坐标
     * @return
     */
    public static BufferedImage cropImage(BufferedImage bufferedImage, int startX, int startY, int endX, int endY) {

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        if (startX == -1) {
            startX = 0;
        }
        if (startY == -1) {
            startY = 0;
        }
        if (endX == -1) {
            endX = width - 1;
        }
        if (endY == -1) {
            endY = height - 1;
        }
        BufferedImage result = new BufferedImage(endX - startX, endY - startY, 4);
        for (int x = startX; x < endX; ++x) {
            for (int y = startY; y < endY; ++y) {
                int rgb = bufferedImage.getRGB(x, y);
                result.setRGB(x - startX, y - startY, rgb);
            }
        }
        return result;
    }
}
