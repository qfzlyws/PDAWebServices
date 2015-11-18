package com.ft.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.internal.OracleTypes;

public class OracleHelper implements OracleInterface {
	Connection conn = null;
	CallableStatement stmt = null;
	Statement st = null;
	String[] arguments = null;
	ResultSet resultset = null;

	@Override
	public String executeProcedure(String procedure, String args, String dbconn) {
		String result = "";
		String sql = "call P_WS_" + procedure + "("; // 需要執行的過程

		// 參數檢查
		if (procedure == null)
			procedure = "";
		if (args == null)
			args = "";
		if (dbconn == null)
			dbconn = "";

		if (procedure.length() == 0 || args.length() == 0 || dbconn.length() == 0)
			return "请求参数不完整！";

		// 將以tab製表符分隔的字符串轉換成數組
		arguments = args.split("\t");

		// 創建要執行的命令
		for (int i = 0; i < arguments.length; i++) {
			sql = sql + "?,";
		}

		sql = sql.substring(0, sql.length() - 1) + ",?)";

		try {
			// 獲取數據庫連接
			conn = DBConnection.getDBConn(dbconn);

			// 創建SQL執行對象
			stmt = conn.prepareCall(sql);

			// 為輸入參數賦值
			for (int i = 0; i < arguments.length; i++) {
				stmt.setString(i + 1, arguments[i]);
			}

			// 註冊輸出參數類型
			stmt.registerOutParameter(arguments.length + 1, OracleTypes.VARCHAR);

			// 執行過程調用
			conn.setAutoCommit(false);

			stmt.execute();

			// 獲取返回值
			result = stmt.getString(arguments.length + 1);

			if (result.substring(0, 1) == "0")
				conn.commit();
			else
				conn.rollback();

		} catch (Exception e) {
			result = "1." + e.getMessage();
			CommonLogger.getLogger().info(e.getMessage());
			// e.printStackTrace();
		} finally {
			try {
				conn.rollback();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				CommonLogger.getLogger().info(e.getMessage());
				// e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public ResultSet executeQuery(String sql, String dbconn) {

		try {
			// 獲取數據庫連接
			conn = DBConnection.getDBConn(dbconn);

			// 創建SQL執行對象
			st = conn.createStatement();

			// 執行SQL語句
			resultset = st.executeQuery(sql);

		} catch (Exception e) {
			CommonLogger.getLogger().info(e.getMessage());
		} finally {
			try {
				resultset.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				CommonLogger.getLogger().info(e.getMessage() + e.getSQLState());
			}
		}

		return resultset;
	}

	@Override
	public String querySQL(String sessionid, String ip, String factno, String program, String tablename,
			String sqlindex, String sqlargs, String dbconn) {
		String sql = "";
		String result = "";
		String args = "";

		args = sessionid + "\t" + ip + "\t" + factno + "\t" + program + "\t" + tablename + "\t" + sqlindex + "\t"
				+ sqlargs;

		result = executeProcedure("P_WS_SELECTSQL", args, dbconn);

		if (result.substring(0, 1).equals("0"))
			sql = result.substring(2);

		return sql;
	}

}
