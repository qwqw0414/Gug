package com.joje.gug;

import com.joje.gug.controller.GugController;

public class ApplicationRun {

	public static void main(String[] args) {
		try {
			System.out.println("[Application RUN]");
			GugController gugController = new GugController();
			gugController.solution();
			System.out.println("[Application END]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
