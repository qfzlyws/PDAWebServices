package com.ft.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * @author 董猛
 * @version 1.0 日誌記錄
 */
public class CommonLogger {
	private static Logger commonlogger = Logger.getLogger("PDAWebServices");

	public static Logger getLogger() {
		/*try {
			FileHandler fileHandler = new FileHandler("D:/PDAWebServices.log");
			commonlogger.addHandler(fileHandler);
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		return commonlogger;
	}
}
