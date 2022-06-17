package com.wxf.utility;

import com.wxf.repository.MyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * @author weixf
 * @since 2022-01-21
 */
@Slf4j
@Component
public class ExeSQL {

    @Autowired
    private MyRepository myRepository;

    public String getOneValue(String sql) {
        return myRepository.getOneValue(sql);
    }

    public SSRS execSQL(String sql) {
        return myRepository.execSQL(sql);
    }
}