/**
 * PDAServices.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ft.pda;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;

import com.ft.util.OracleHelper;

public class PDAServices {
	//
	String callReturn = "WebService返回信息！";

	/**
	 * @param function
	 *            過程名稱
	 * @param args
	 *            參數
	 * @param dbstring
	 *            數據源名稱
	 */
	public String PDACall(String function, String args, String dbconn) {

		OracleHelper ora = new OracleHelper();
		args = getSession().getId() + "\t" + args;

		callReturn = ora.executeProcedure(function, args, dbconn);

		return callReturn;
	}

	/**
	 * @param sql
	 *            sql語句
	 * @param column
	 *            欄位名稱
	 * @param dbconn
	 *            數據庫連接
	 */
	public String getColumnValue(String sql, String column, String dbconn) {
		String value = "";
		ResultSet rt = null;

		OracleHelper ora = new OracleHelper();

		rt = ora.executeQuery(sql, dbconn);
		

		return value;
	}

	private HttpSession getSession() {
		MessageContext mc = MessageContext.getCurrentContext(); // 獲取請求上下文信息
		HttpServletRequest request = (HttpServletRequest) mc.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST); // 獲取request對象

		return request.getSession();
	}

}
