package com.joje.gug.common.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtil {

	private final String PROPERTIES_PATH = "/application.properties";
	private Properties prop = new Properties();

	public PropertiesUtil() {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(PROPERTIES_PATH), "UTF-8"));

			prop.load(br);
			System.out.println(">> Application.properties 로드 : " + PROPERTIES_PATH);
		} catch (NullPointerException | FileNotFoundException e) {
			System.out.println("[ERROR] 설정파일을 찾을 수 없습니다. [path]=[" + PROPERTIES_PATH + "]");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String get(String key) {
		return prop.getProperty(key);
	}
}
