package com.joje.gug.core;

import com.joje.gug.vo.GugVo;

public interface GugCore {

	public void lowGug(GugVo gug);
	public void highGug(GugVo gug);
	public boolean isValid(int num, int row, int col, GugVo gug);
}
