package com.joje.gug.controller;

import java.util.Arrays;

import com.joje.gug.common.utils.FileIOUtil;
import com.joje.gug.core.GugCore;
import com.joje.gug.core.GugCoreImpl;
import com.joje.gug.vo.GugVo;

public class GugController {

	private static FileIOUtil file = new FileIOUtil();
	private static final int LIMIT = 1000;
	private GugCore gugCore = new GugCoreImpl();

	public void solution() throws Exception {
		
		// 처리 파일명 리스트 조회
		String[] fileNames = file.getFileNames();
		
		for(String fileName : fileNames) {
			System.out.println("[INFO] GUG START : FileName=" + fileName);
			
			//데이터 셋
			String txt = file.read(fileName);
			GugVo gug = new GugVo(txt);
			
			//순차 대입
			System.out.println(">> Seq Access Start");
			this.seqGug(gug);
			System.out.println(">> Seq Access End");
			
			//무차별 대입
			System.out.println(">> Random Access Start");
			this.randomGug(gug);
			System.out.println(">> Random Access End");
			
			//완료시 저장
			if(gug.countOfZero() == 0) {
				file.write(fileName, gug.toString());
			}
		}
	}
	
	/**
	 * 무차별 대입 해결
	 * @param gug
	 * @throws Exception
	 */
	private void randomGug(GugVo gug) throws Exception {
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				for(int num = 1; num <= 9; num++) {
					if(gugCore.isValid(num, row, col, gug)) {
						GugVo tempGug = new GugVo(gug.get());
						System.out.printf("[INFO] Random Access : num=%d row=%d col=%d \n", num, row, col);
						tempGug.set(num, row, col);
						this.seqGug(tempGug);
						// 무차별 대입으로 완료시 중단
						if(tempGug.countOfZero() == 0) {
							gug.set(tempGug.get());
							break;
						}
					}
				}
			}
		}
	}
	
	/**
	 * 순차 대입 해결
	 * @param gug
	 * @throws Exception
	 */
	private void seqGug(GugVo gug) throws Exception {
		int zeroCnt = -1; // 공백 상태
		int typeSw = 0; // 0:low 1:high
		int tryCnt = 0; // 시도횟수
		int loofCnt = 0; // 데드락 상태 체크

		while (loofCnt < 3 && tryCnt++ < LIMIT && 0 < gug.countOfZero()) {
			System.out.println("[INFO] TRY STEP = " + tryCnt + "[0=" + gug.countOfZero() + "]");

			// 잔여 공백 수
			zeroCnt = gug.countOfZero();

			// 스위치 여부에 따라 처리 진행 (0:low 1:high)
			if (typeSw == 0) {
				gugCore.lowGug(gug);
			} else {
				gugCore.highGug(gug);
			}

			// 잔여 공백 수가 처리 전과 일치시 처리 타입 변경
			if (zeroCnt == gug.countOfZero()) {
				typeSw = typeSw == 1 ? 0 : 1;
				loofCnt++;
				System.out.println("[INFO] SW = " + (typeSw == 0 ? "LOW" : "HIGH"));
			}else {
				loofCnt = 0;
			}
		}
	}
}
