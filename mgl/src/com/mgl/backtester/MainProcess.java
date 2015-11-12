/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.backtester;

import com.impetus.labs.korus.core.message.Message;
import com.impetus.labs.korus.core.process.Process;
import com.sun.corba.se.spi.presentation.rmi.StubAdapter;

/**
 *
 * @author axjyb
 */
public class MainProcess extends Process {

    @Override
    public void service(Message msg) {
        
        if (msg.containsKey("action")) {
            String action = (String) msg.get("action");
            if ("start".equals(action)) {
                
            }
        }
        
        
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
