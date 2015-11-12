/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.fundamental.test;

import com.mgl.fundamental.reportfinancialstatements.ObjectFactory;
import com.mgl.fundamental.reportfinancialstatements.ReportFinancialStatements;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Michael Langer
 */
public class TestReportFinancialStatements {
    
    public static void main(String[] args) {

	 try {

		File file = new File("C:\\Documents and Settings\\Michael Langer\\My Documents\\NetBeansProjects\\ibdb\\IBDB_v1\\src\\META-INF\\ReportFinancialStatements.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(ReportFinancialStatements.class, ObjectFactory.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		JAXBElement customer = (JAXBElement) jaxbUnmarshaller.unmarshal(file);
                ReportFinancialStatements rs = (ReportFinancialStatements) customer.getValue();
		System.out.println(rs); 

	  } catch (JAXBException e) {
		e.printStackTrace();
	  }

	}   
}
