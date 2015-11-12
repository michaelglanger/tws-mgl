/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.io;

import com.ib.controller.ApiConnection.ILogger;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Michael G. Langer
 */
public class TextPaneLogger implements ILogger {

    private PropertyChangeListener listener;
    
    public TextPaneLogger(PropertyChangeListener listener) {
        this.listener = listener;
    }
    
    @Override
    public void log(String valueOf) {
        listener.propertyChange( new PropertyChangeEvent(this, "UpdaterLog", null, valueOf));
    }
    
}
