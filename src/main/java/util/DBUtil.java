package util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DBUtil {

    private static final String JNDI_NAME = "java:comp/env/jdbc/MyTestDB";
    private static DataSource dataSource;

    static {
        try {
            // 1. 獲取初始上下文
            Context initCtx = new InitialContext();
            // 2. 透過 JNDI 名稱查找資源
            dataSource = (DataSource) initCtx.lookup(JNDI_NAME);
        } catch (NamingException e) {
            // 如果 JNDI 查找失敗 (例如配置錯誤)，拋出運行時異常
            System.err.println("JNDI 查找 DataSource 失敗: " + JNDI_NAME);
            e.printStackTrace();
            throw new RuntimeException("無法初始化資料庫連線池", e);
        }
    }
    
    public static DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}