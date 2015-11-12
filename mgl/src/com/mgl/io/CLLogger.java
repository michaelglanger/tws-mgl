package com.mgl.io;

import com.ib.controller.ApiConnection.ILogger;

public class CLLogger implements ILogger {

	@Override
	public void log(String valueOf) {
		System.out.print( valueOf );
	}

}
