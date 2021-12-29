package com.joje.gug.vo;

import java.util.Arrays;

public class GugsVo {
	
	private String[][] table = new String[9][9];
	
	public void set(String[][] table) {
		for(int row = 0; row < this.table.length; row++) {
			for(int col = 0; col < this.table[row].length; col++) {
				this.table[row][col] = table[row][col];
			}
		}
	}
	
	public void set(String str, int row, int col) {
		if(str != null) {
			this.table[row][col] = str;
		}else {
			System.out.println("[ERROR] GugsVo.set : Invalid String str=" + str);
		}
	}
	
	public String[] getRow(int row) {
		return this.table[row];
	}
	
	public String[] getCol(int col) {
		String[] result = new String[9];
		for(int i = 0; i < this.table.length; i++) {
			result[i] = this.table[i][col];
		}
		return result;
	}
	
	public String[] getBlock(int block) {
		String[] result = new String[9];
		for(int i = 0; i < result.length; i++) {
			int row = i / 3 + block / 3 * 3;
			int col = i % 3 + block % 3 * 3;
			result[i] = this.table[row][col];
		}
		return result;
	}
	
	public String[] getBlock(int row, int col) {
		String[] result = new String[9];
		int index = 0;
		row = row / 3 * 3;
		col = col / 3 * 3;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				result[index++] = this.table[i + row][j + col];
			}
		}
		return result;
	}
	
	public void print() {
		for(String[] str : this.table) {
			System.out.println(Arrays.toString(str));
		}
	}
}
