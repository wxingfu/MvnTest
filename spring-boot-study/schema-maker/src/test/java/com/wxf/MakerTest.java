package com.wxf;

import com.wxf.maker.*;
import com.wxf.pdm.PDM;
import com.wxf.pdm.Parser;
import com.wxf.repository.MyRepository;
import com.wxf.table.Convert;
import com.wxf.table.Schema;
import com.wxf.utility.DBConst;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author weixf
 * @since 2022-01-21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MakerTest {
    @Autowired
    private Maker maker;

    @Test
    public void test() throws Exception {
        maker.createJavaFile();
    }
}
