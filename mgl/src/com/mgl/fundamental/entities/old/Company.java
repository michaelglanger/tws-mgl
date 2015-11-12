/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.fundamental.entities.old;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael G. Langer
 */
public class Company {

    private String companyName;
    private String repNo;
    private String irsNo;
    private String cikNo;

    private String mainTicker;

    private List<Issue> issues = new ArrayList<Issue>();
    
}
