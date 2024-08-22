package com.heima.wemedia;

import com.heima.file.service.FileStorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: TestFile
 * @version: 1.0
 * @Author: pyipXt
 * @Description: TODO
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestFile {
    @Autowired
    private FileStorageService fileStorageService;

    @Test
    public void test01(){
        System.out.println(fileStorageService);
    }
}
