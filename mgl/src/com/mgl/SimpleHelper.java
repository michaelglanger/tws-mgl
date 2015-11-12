package com.mgl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SimpleHelper {

	public String getEndDateTime() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String endDateTime = sdf.format(c.getTime());
		return endDateTime;
	}

}
