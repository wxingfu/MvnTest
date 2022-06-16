package com.wxf.utility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

import static oracle.net.aso.C01.n;

/**
 * @author weixf
 * @since 2022-01-21
 */
@Slf4j
@Component
public class ExeSQL {

    @Resource
    private JdbcTemplate jdbcTemplate;

    private Connection con;

    private boolean mflag = false;

    private final FDate fDate = new FDate();


    public ExeSQL() {
    }

    public ExeSQL(Connection tConnection) {
        con = tConnection;
        mflag = true;
    }


    /**
     * 获取唯一的返回值
     *
     * @param sql String
     * @return String
     */
    public String getOneValue(String sql) {

        List<Object> objectList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>());
        String mValue = (String) objectList.get(1);
        return StrTool.cTrim(mValue);
        /*
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String mValue = "";
        log.debug("ExecSQL : " + sql);

        // add by yt，如果没有传入连接，则类创建
        if (!mflag) {
            con = DBConnPool.getConnection().getCon();
        }

        try {
            pstmt = con.prepareStatement(StrTool.GBKToUnicode(sql),
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                // 其实并不是很合适，主要是因为有可能取得对象的数据类型有误
                mValue = rs.getString(1);
                break;
            }
            rs.close();
            pstmt.close();
            // 如果连接是类创建的，则关闭连接
            if (!mflag) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // @@错误处理
            log.error("### Error ExeSQL at getOneValue: " + sql);
            // 设置返回值
            mValue = "";
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    // 由于描述的问题，导致执行的sql错误百出，因此pstmt的关闭需要特殊处理
                    try {
                        pstmt.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } finally {
                        pstmt.close();
                    }
                }
                if (!mflag) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            // 强制回收
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    // 由于描述的问题，导致执行的sql错误百出，因此pstmt的关闭需要特殊处理
                    try {
                        pstmt.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } finally {
                        pstmt.close();
                    }
                }
                if (!mflag) {
                    if (con.isClosed() == false) {
                        con.close();
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return StrTool.cTrim(mValue);*/
    }

    /**
     * 把ResultSet中取出的数据转换为相应的数据值字符串
     * 输出：如果成功执行，返回True，否则返回False，并且在Error中设置错误的详细信息
     *
     * @param rsmd ResultSetMetaData
     * @param rs   ResultSet
     * @param i    int
     * @return String
     */
    public String getDataValue(ResultSetMetaData rsmd, ResultSet rs, int i) {
        String strValue = "";

        try {
            int dataType = rsmd.getColumnType(i);
            int dataScale = rsmd.getScale(i);
            int dataPrecision = rsmd.getPrecision(i);
            // 数据类型为字符
            if ((dataType == Types.CHAR) || (dataType == Types.VARCHAR)) {
                // 由于存入数据库的数据是GBK模式，因此没有必要做一次unicodeToGBK
                // strValue = StrTool.unicodeToGBK(rs.getString(i));
                strValue = rs.getString(i);
            }
            // 数据类型为日期、时间
            else if ((dataType == Types.TIMESTAMP) || (dataType == Types.DATE)) {
                strValue = fDate.getString(rs.getDate(i));
            }
            // 数据类型为浮点
            else if ((dataType == Types.DECIMAL) || (dataType == Types.FLOAT)) {
                // strValue = String.valueOf(rs.getFloat(i));
                // 采用下面的方法使得数据输出的时候不会产生科学计数法样式
                strValue = String.valueOf(rs.getBigDecimal(i));
                // 去零处理
                strValue = PubFun.getInt(strValue);
            }
            // 数据类型为整型
            else if ((dataType == Types.INTEGER) || (dataType == Types.SMALLINT)
                    || dataType == Types.TINYINT) {
                strValue = String.valueOf(rs.getInt(i));
                strValue = PubFun.getInt(strValue);
            }
            // 数据类型为浮点
            else if (dataType == Types.NUMERIC) {
                if (dataScale == 0) {
                    if (dataPrecision == 0) {
                        // strValue = String.valueOf(rs.getDouble(i));
                        // 采用下面的方法使得数据输出的时候不会产生科学计数法样式
                        strValue = String.valueOf(rs.getBigDecimal(i));
                    } else {
                        strValue = String.valueOf(rs.getLong(i));
                    }
                } else {
                    // strValue = String.valueOf(rs.getDouble(i));
                    // 采用下面的方法使得数据输出的时候不会产生科学计数法样式
                    strValue = String.valueOf(rs.getBigDecimal(i));
                }
                strValue = PubFun.getInt(strValue);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return StrTool.cTrim(strValue);
    }


    /**
     * 功能：可以执行输入的任意查询SQL语句。 输入：任意一个查询语句的字符串csql 返回：一个SSRS类的实例，内为查询结果
     *
     * @param sql String
     * @return SSRS
     */
    public SSRS execSQL(String sql) {
        SSRS tSSRS = new SSRS(n);
        jdbcTemplate.query(sql, rs -> {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                tSSRS.SetText(getDataValue(metaData, rs, i));
            }
        });
        return tSSRS;

        /*PreparedStatement pstmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        SSRS tSSRS = null;

        log.debug("ExecSQL : " + sql);
        // add by yt
        if (!mflag) {
            con = DBConnPool.getConnection().getCon();
        }

        try {
            pstmt = con.prepareStatement(StrTool.GBKToUnicode(sql),
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);

            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();

            int n = rsmd.getColumnCount();
            tSSRS = new SSRS(n);

            // 取得总记录数
            while (rs.next()) {
                for (int j = 1; j <= n; j++) {
                    tSSRS.SetText(getDataValue(rsmd, rs, j));
                }
            }

            rs.close();
            pstmt.close();

            if (!mflag) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // @@错误处理
            log.error(
                    "##### Error Sql in ExeSQL at execSQL(String sql): " + sql);

            tSSRS = null;
            // tSSRS.ErrorFlag = true;
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    // 由于描述的问题，导致执行的sql错误百出，因此pstmt的关闭需要特殊处理
                    try {
                        pstmt.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } finally {
                        pstmt.close();
                    }
                }
                if (!mflag) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            // 强制回收
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    // 由于描述的问题，导致执行的sql错误百出，因此pstmt的关闭需要特殊处理
                    try {
                        pstmt.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } finally {
                        pstmt.close();
                    }
                }
                if (!mflag) {
                    if (con.isClosed() == false) {
                        con.close();
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return tSSRS;*/
    }
}