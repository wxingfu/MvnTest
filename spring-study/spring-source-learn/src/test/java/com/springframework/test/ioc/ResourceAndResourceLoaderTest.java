package com.springframework.test.ioc;

import cn.hutool.core.io.IoUtil;
import org.junit.Assert;
import org.junit.Test;
import com.springframework.core.io.*;

import java.io.InputStream;

/*
 *
 * @author weixf
 * @date 2022-06-23
 */
public class ResourceAndResourceLoaderTest {

    @Test
    public void testResourceLoader() throws Exception {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

        //加载classpath下的资源
        Resource resource = resourceLoader.getResource("classpath:hello.txt");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
        Assert.assertEquals(content, "hello world");

        //加载文件系统资源
        resource = resourceLoader.getResource("src/test/resources/hello.txt");
        Assert.assertTrue(resource instanceof FileSystemResource);
        inputStream = resource.getInputStream();
        content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
        Assert.assertEquals(content, "hello world");

        //加载url资源
        resource = resourceLoader.getResource("https://www.baidu.com");
        Assert.assertTrue(resource instanceof UrlResource);
        inputStream = resource.getInputStream();
        content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }
}