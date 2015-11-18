package com.ft.util;

import java.sql.ResultSet;

/**
 * @author 董猛
 * @version 1.0
 * Oracle數據庫操作接口
 *
 */
public interface OracleInterface {
	/**
	 * 執行oracle過程函數
	 * @param procedure 過程名
	 * @param args 參數列表
	 * @param dbconn 數據庫連接
	 * */
	public String executeProcedure(String procedure,String args,String dbconn);
	
	/**
	 * 查詢idatamac80sql表中的SQL語句
	 * @param sessionid 會話ID
	 * @param ip 請求的IP地址
	 * @param programe 程序名稱
	 * @param tablename 顯示資料的表格名稱
	 * @param sqlindex 第一個SQL
	 * @param sqlargs sql所需要的參數
	 * */
	public String querySQL(String sessionid,String ip,String factno,String program,String tablename,String sqlindex,String sqlargs,String dbconn);
	
	/**
	 * 執行SQL語句
	 * @param sql 需要執行的SQL
	 * */
	public ResultSet executeQuery(String sql,String dbconn);
}
