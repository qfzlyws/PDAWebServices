package com.ft.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
	private static Connection conn = null;

	public static Connection getDBConn(String dbconn) throws NamingException, SQLException {

		Context sourceCtx = new InitialContext();
		DataSource ds = (DataSource) sourceCtx.lookup("java:comp/env/" + dbconn);
		conn = ds.getConnection();

		return conn;
	}
}
