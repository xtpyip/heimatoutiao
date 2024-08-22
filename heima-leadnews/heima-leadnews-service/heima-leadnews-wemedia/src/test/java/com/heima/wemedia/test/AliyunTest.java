package com.heima.wemedia.test;

import com.heima.common.aliyun.GreenImageScan;
import com.heima.common.aliyun.GreenTextScan;
import com.heima.file.service.FileStorageService;
import com.heima.wemedia.WemediaApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: AliyunTest
 * @version: 1.0
 * @Author: pyipXt
 * @Description: 图片文章test
 **/
@SpringBootTest(classes = WemediaApplication.class)
@RunWith(SpringRunner.class)
public class AliyunTest {

    @Autowired
    private GreenTextScan greenTextScan;

    @Autowired
    private GreenImageScan greenImageScan;

    @Autowired
    private FileStorageService fileStorageService;

//    @Test
//    public void testScanText() throws Exception {
//        Map map = greenTextScan.greeTextScan("我是一个好人");
//        System.out.println(map);
//    }
//
//    @Test
//    public void testScanImage() throws Exception {
//        byte[] bytes = fileStorageService.downLoadFile("http://192.168.200.130:9000/leadnews/2024/08/09/e53b5e4ca55f429caf253c5ca3e5c2b7.jpg");
//        Map map = greenImageScan.imageScan(Arrays.asList(bytes));
//        System.out.println(map);
//    }
}