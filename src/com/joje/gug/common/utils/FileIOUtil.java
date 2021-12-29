package com.joje.gug.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileIOUtil {

	private static PropertiesUtil prop = new PropertiesUtil();

	// 리소스 디렉토리 경로
	private static final String ROOT_PATH = prop.get("path.root");
	private static final String INPUT_PATH = ROOT_PATH + prop.get("path.input");
	private static final String OUTPUT_PATH = ROOT_PATH + prop.get("path.output");

	public FileIOUtil() {
		File inputDir = new File(INPUT_PATH);
		File outputDir = new File(OUTPUT_PATH);

		if (inputDir.mkdirs())
			System.out.println(">> Make Dir PATH=" + INPUT_PATH);

		if (outputDir.mkdirs())
			System.out.println(">> Make Dir PATH=" + OUTPUT_PATH);

	}

	/**
	 * 파일 읽기
	 * @param fileName
	 * @return String
	 */
	public String read(String fileName) {
		String result = "";
		String filePath = INPUT_PATH + fileName;
		String line;

		System.out.println(">> Read File =" + filePath);
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			while ((line = br.readLine()) != null) {
				result += line.trim();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 파일 쓰기
	 * 
	 * @param fileName
	 * @param data
	 */
	public void write(String fileName, String data) {
		String filePath = OUTPUT_PATH + fileName;

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
			bw.write(data);
			System.out.println(">> Write File =" + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 해당 디렉토리에 있는 파일명 모두 조회
	 * 
	 * @return String[]
	 */
	public String[] getFileNames() {
		File file = new File(INPUT_PATH);
		String[] fileNames = file.list();
		return fileNames;
	}
}
