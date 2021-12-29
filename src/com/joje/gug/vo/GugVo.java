package com.joje.gug.vo;

public class GugVo {

	private int[][] table = new int[9][9];

	public GugVo(String data) throws RuntimeException {
		this.set(data);
	}

	public GugVo(int[][] table) throws RuntimeException {
		this.set(table);
	}
	
	public int[][] get() {
		return this.table;
	}

	public int get(int row, int col) {
		return this.table[row][col];
	}
	
	public int[] getRow(int row) {
		return this.table[row];
	}
	
	public int[] getCol(int col) {
		int[] result = new int[9];
		for(int i = 0; i < this.table.length; i++) {
			result[i] = this.table[i][col];
		}
		return result;
	}
	
	public int[] getBlock(int block) {
		int[] result = new int[9];
		for(int i = 0; i < result.length; i++) {
			int row = i / 3 + block / 3 * 3;
			int col = i % 3 + block % 3 * 3;
			result[i] = this.table[row][col];
		}
		return result;
	}
	
	public int[] getBlock(int row, int col) {
		int[] result = new int[9];
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
	
	public void set(int num, int row, int col) {
		if(0 < num && num <= 9) {
			this.table[row][col] = num;
		}else {
			System.out.println("[ERROR] GugVo.set : Invalid Number");
		}
	}
	
	/**
	 * String => int[][] 파싱
	 * @param data
	 * @throws RuntimeException
	 */
	public void set(String data) throws RuntimeException {
		if (data != null && data.length() == 81) {
			// ROW 조회
			for (int i = 0; i < table.length; i++) {
				// COL 조회
				for (int j = 0; j < table[i].length; j++) {
					table[i][j] = Integer.parseInt(String.valueOf(data.charAt(i * 9 + j)));
				}
			}
		}
	}
	
	/**
	 * 해당 테이블 깊은 복사
	 * @param table
	 */
	public void set(int[][] table) {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				this.table[i][j] = table[i][j];
			}
		}
	}
	
	/**
	 * 테이블에 0 인 값을 카운트
	 * @return
	 */
	public int countOfZero() {
		int cnt = 0;
		for (int i = 0; i < this.table.length; i++) {
			for (int j = 0; j < this.table[i].length; j++) {
				if(this.table[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}
	
	/**
	 * TALBE PRINT
	 */
	public void print() {
		System.out.println(">> PRINT START [0=" + this.countOfZero() +"]");
		final String COL = "| ";
		for (int i = 0; i < this.table.length; i++) {
			String line = "";
			if (i % 3 == 0 && i != 0)
				System.out.println("");

			for (int j = 0; j < this.table[i].length; j++) {
				if (j % 3 == 0) {
					line += COL;
				}
				line += this.table[i][j] + " ";
			}
			System.out.println(line + COL);
		}
		System.out.println("<< PRINT END");
	}
	
	@Override
	public String toString() {
		String str = "";
		for(int[] row : table) {
			for(int col : row) {
				str += col;
			}
			str += "\n";
		}
		return str;
	}
}
