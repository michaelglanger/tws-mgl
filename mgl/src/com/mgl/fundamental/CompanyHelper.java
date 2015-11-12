/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.fundamental;

import com.mgl.fundamental.entities.Company;
import com.mgl.fundamental.reportfinancialstatements.CoGeneralInfo;
import com.mgl.fundamental.reportfinancialstatements.CoID;
import com.mgl.fundamental.reportfinancialstatements.CoIDs;
import com.mgl.fundamental.reportfinancialstatements.Issue;
import com.mgl.fundamental.reportfinancialstatements.IssueID;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael G. Langer
 */
public class CompanyHelper {
    
    private static final String COMPANY_NAME = "CompanyName";
    private static final String ISIN = "ISIN";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    
    
    public Company createCompany(String ticker, CoIDs coIDs, Issue issue, CoGeneralInfo generalInfo) {
        Company company = new Company();
        company.setTicker(ticker);
        setCompanyName(coIDs, company);
        setIsin(issue, company);
        setGeneralInfo(generalInfo, company);
        return company;
    }
    
    public void setCompanyName(CoIDs coIDs, Company company) {
        for (CoID coid : coIDs.getCoID()) {
            if (COMPANY_NAME.equals(coid.getType())) {
                company.setCompanyName(coid.getValue());
            }
        }  
    }
    
    public void setIsin(Issue issue, Company company) {
        for (IssueID issueId : issue.getIssueID()) {
            if (ISIN.equals(issueId.getType())) {
                company.setIsin(issueId.getValue());
            }
        }
    }
    
    public void setGeneralInfo(CoGeneralInfo generalInfo, Company company) {
        company.setReportingCurrency( generalInfo.getReportingCurrency().getCode() );
        
        String sLastAnnual = generalInfo.getLatestAvailableAnnual();
        if (sLastAnnual != null && !"".equals(sLastAnnual)) {
            GeneralHelper helper = new GeneralHelper();
            company.setLastAvailableAnnual( helper.stringToDate(sLastAnnual));
        }
        
        String sLastInterim = generalInfo.getLatestAvailableInterim();
        if (sLastInterim != null && !"".equals(sLastInterim)) {
            DateFormat format = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
            try {
                Date date = format.parse(sLastInterim);
                company.setLastAvailableInterim(date);
            } catch (ParseException ex) {
                Logger.getLogger(CompanyHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
