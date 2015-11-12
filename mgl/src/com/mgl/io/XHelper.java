package com.mgl.io;

import java.util.ArrayList;
import java.util.List;

import com.ib.controller.NewContract;
import com.ib.controller.Types.SecType;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class XHelper {
	
	private static XHelper instance;
	private static final String PRE_URL = "../../../META-INF/";
	
	private XHelper() {
	}
	
	public static XHelper getInstance() {
		if (instance == null) 
			instance = new XHelper();
		return instance;
	}
	
	public Ctrs getCtrs(String fileName) {
		XStream xstream = new XStream(new StaxDriver());
		xstream.alias("ctrs", Ctrs.class);
		xstream.alias("contract", Contract.class);
		xstream.useAttributeFor(Contract.class, "symbol");
		xstream.useAttributeFor(Contract.class, "secType");
		xstream.useAttributeFor(Contract.class, "exchange");
		xstream.useAttributeFor(Contract.class, "currency");
		xstream.useAttributeFor(Contract.class, "companyName");
                xstream.useAttributeFor(Contract.class, "index");

		java.net.URL URL = XHelper.class.getResource(PRE_URL + fileName);
		Ctrs o = (Ctrs) xstream.fromXML(URL);

		return o;
	}

	public List<NewContract> fromCtrsToNCList(Ctrs ctrs) {
		List<NewContract> nContractList = new ArrayList<NewContract>();
		for (Contract c : ctrs.getContracts()) {
			NewContract contract = new NewContract();
			contract.symbol( c.getSymbol() );
			contract.secType( this.getSecType(c.getSecType()) );
                        String sEx = c.getExchange();
                        String[] ssEx = sEx.split("\\|");
                        if (ssEx.length > 1) {
                            contract.exchange( ssEx[0] );
                            contract.primaryExch( ssEx[1] );
                        } else {
                            contract.exchange( c.getExchange() );
                        }
			
                        
			contract.currency( c.getCurrency() );
			nContractList.add(contract);
		}
		return nContractList;
	}
	
	public List<NewContract> fromFileNameToNCList(String filename) {
		return fromCtrsToNCList( getCtrs(filename) );
	}

	/**
	 * Returns the SecType for a String
	 * @param s a String
	 * @return a SecType
	 */
	public SecType getSecType(String s) {
		switch (s) {
		case "STK":
			return SecType.STK;
		default:
			break;
		}
		
		return null;
	}
	
}
