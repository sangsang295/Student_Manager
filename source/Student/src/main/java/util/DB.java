package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 请注意：为了方便部署，现已将数据库链接改为使用文件配置的形式，
 * 如需设置数据库链接及账户密码，请修改“WEB-INF”文件夹内的“db.properties”文件。
 * 
 * 注：修改了数据库驱动的版本，该版本推荐适用于：MySQL 8.0.33 。
 * 
 * 如果所用数据库不是此版本，请在运行前先更换WEB-INF/lib中的数据库驱动以适配数据库。
 *
 */
public class DB implements ServletContextListener {

	private static String dbURL = "";

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// Servlet容器启动后，加载数据库类，并读入数据库配置文件
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		InputStream dbPropertiesFile = sce.getServletContext().getResourceAsStream("/WEB-INF/db.properties");
		Properties dbProperties = new Properties();
		try {
			dbProperties.load(dbPropertiesFile);
			dbURL = dbProperties.getProperty("db.url");
		} catch (IOException e) {
			e.printStackTrace();
		}
		ServletContextListener.super.contextInitialized(sce);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContextListener.super.contextDestroyed(sce);
	}

	// 获取数据库连接
	public static Connection getConn() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 关闭对象 释放资源
	public static void close(Connection conn, PreparedStatement pst, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (pst != null)
				pst.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}