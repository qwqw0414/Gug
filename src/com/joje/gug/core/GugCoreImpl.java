package com.joje.gug.core;

import com.joje.gug.vo.GugVo;
import com.joje.gug.vo.GugsVo;

public class GugCoreImpl implements GugCore {

	public void lowGug(GugVo gug) {
		try {
//			테이블에 0이 존재할 경우 진행
			if (gug.countOfZero() > 0) {
				for (int row = 0; row < 9; row++) {
					for (int col = 0; col < 9; col++) {
//						해당 위치에 값이 0인 상태인 경우
						if (gug.get(row, col) == 0) {
							int cnt = 0; // 해당 위치에 입력 가능한 수 카운트
							int temp = 0;// 임시 저장 변수
//							1~9 숫자 별 조회
							for (int num = 1; num <= 9; num++) {
								// 해당 위치에 입력 가능한 수
								if (this.isValid(gug.getRow(row), num) && this.isValid(gug.getCol(col), num)
										&& this.isValid(gug.getBlock(row, col), num)) {
									temp = num;
									cnt++;
//									System.out.println("row=" + row + " col=" + col + " num=" + temp + " cnt=" + cnt);
								}
							}
							// 해당 위치에 들어갈 수 있는 숫자가 유일한 경우 해당값 대입
							if (cnt == 1) {
								gug.set(temp, row, col);
//								gug.print();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void highGug(GugVo gug) {
		GugsVo gugs = new GugsVo();

		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				String temp = "";
				if (gug.get(row, col) == 0) {
					// 해당 위치에 유효한 숫자 String 형태로 저장
					for (int num = 1; num <= 9; num++) {
						if (this.isValid(num, row, col, gug)) {
							temp += num;
						}
					}
				}
				// 임시테이블에 대입
				gugs.set(temp, row, col);
			}
		}

//		gugs.print();

		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (gug.get(row, col) == 0) {
					for (int num = 1; num <= 9; num++) {
						if (this.isValid(num, row, col, gug) && this.isUnique(num, row, col, gugs)) {
//							System.out.printf("num=%d row=%d col=%d \n", num, row, col);
//							System.out.println(Arrays.toString(gug.getRow(row)));
//							System.out.println(Arrays.toString(gug.getCol(col)));
//							System.out.println(Arrays.toString(gug.getBlock(row, col)));
//							System.out.println(Arrays.toString(gugs.getRow(row)));
//							System.out.println(Arrays.toString(gugs.getCol(col)));
//							System.out.println(Arrays.toString(gugs.getBlock(row, col)));
							gug.set(num, row, col);
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * 해당 배열에 해당 숫자가 유일한 수 인지 여부 판단
	 * 
	 * @param line
	 * @param num
	 * @return
	 */
	private boolean isValid(int[] line, int num) {
		for (int n : line) {
			if (n == num) {
				return false;
			}
		}
		return line.length > 0;
	}

	public boolean isValid(int num, int row, int col, GugVo gug) {
		return this.isValid(gug.getRow(row), num) && 
			   this.isValid(gug.getCol(col), num) && 
			   this.isValid(gug.getBlock(row, col), num);
	}

	private boolean isUnique(String[] line, int num) {
		int cnt = 0;
		for (String s : line) {
			if (s.contains(String.valueOf(num))) {
				cnt++;
			}
		}
		return cnt == 1;
	}
	
	private boolean isUnique(int num, int row, int col, GugsVo gugs) {
		return this.isUnique(gugs.getRow(row), num) || 
			   this.isUnique(gugs.getCol(col), num) || 
			   this.isUnique(gugs.getBlock(row, col), num);
	}
}
