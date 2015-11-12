package com.mgl.io;

import com.ib.controller.ApiConnection.ILogger;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.ib.controller.ApiController;
import com.ib.controller.Formats;
import com.ib.controller.ApiController.IConnectionHandler;
import com.ib.controller.ApiController.ITimeHandler;

public class ConnectionHandler implements IConnectionHandler, AutoCloseable {

	private final ILogger m_inLogger = new CLLogger();
	private ILogger m_outLogger = new CLLogger();
	private final ApiController apiController = new ApiController(this, m_inLogger, m_outLogger);
	
	private String ip;
	private int port;
	private int appID;
	
        public ConnectionHandler(ILogger logger) {
            this();
            m_outLogger = logger;
        }
        
	public ConnectionHandler() {
		ResourceBundle rb = ResourceBundle.getBundle("config");
		ip = rb.getString("ip");
		port = Integer.parseInt(rb.getString("port"));
		appID = Integer.parseInt( rb.getString("appID") );
	}
	
	public void connect() {
		apiController.connect( ip, port, appID, null);
	}
	
	public void disconnect() {
		apiController.connectionClosed();
	}
	
	public ApiController getController() {
		return apiController;
	}
	
	@Override
	public void connected() {
		apiController.reqCurrentTime( new ITimeHandler() {
			@Override public void currentTime(long time) {
                            m_outLogger.log("Server date/time is " + Formats.fmtDate(time * 1000) );
			}
		});
	}

	@Override
	public void disconnected() {
		// TODO Auto-generated method stub

	}

	@Override
	public void accountList(ArrayList<String> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(Exception e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void message(int id, int errorCode, String errorMsg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show(String string) {
		// TODO Auto-generated method stub

	}

    @Override
    public void close() throws Exception {
        this.disconnect();
        apiController.disconnect();
    }

}
