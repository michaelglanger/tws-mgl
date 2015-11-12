package com.mgl.fundamental;

import com.ib.controller.ApiController;
import com.ib.controller.NewContract;
import com.ib.controller.Types;
import com.mgl.entities.ControllerFactory;
import com.mgl.fundamental.entities.*;
import com.mgl.fundamental.exceptions.FundamentalMissingExchangeException;
import com.mgl.fundamental.exceptions.FundamentalParsingException;
import com.mgl.fundamental.reportfinancialstatements.AnnualPeriods;

import com.mgl.fundamental.reportfinancialstatements.CoID;
import com.mgl.fundamental.reportfinancialstatements.FiscalPeriod;
import com.mgl.fundamental.reportfinancialstatements.Issue;
import com.mgl.fundamental.reportfinancialstatements.IssueID;
import com.mgl.fundamental.reportfinancialstatements.ObjectFactory;
import com.mgl.fundamental.reportfinancialstatements.ReportFinancialStatements;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Created by Michael G. Langer on 21/09/2015.
 */
public class FundamentalDataLoaderWorker extends SwingWorker implements ApiController.IFundamentalsHandler {

    private ApiController apiController;
    private NewContract nc;
    private boolean finished = false;
    private ControllerFactory controllerFactory;

    public FundamentalDataLoaderWorker(NewContract nc, ApiController apiController) {
        this.nc = nc;
        this.apiController = apiController;
        controllerFactory = new ControllerFactory();
    }

    @Override
    public void fundamentals(String str) {
//        saveXMLToDisk(str);
        processXML(str);
        finished = true;
    }

    /**
     * This method converts the Financial Statement in XML format into Java
     * Objects based on the XSD generated classes.
     *
     * @param xml a Report Financial Statement in XML format
     * @return a ReportFinancialStatements
     */
    protected ReportFinancialStatements getReportFinancialStatementsFromXML(String xml) throws FundamentalParsingException {
        try (StringReader reader = new StringReader(xml)) {

            JAXBContext jaxbContext = JAXBContext.newInstance(ReportFinancialStatements.class, ObjectFactory.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (ReportFinancialStatements) jaxbUnmarshaller.unmarshal(reader);

        } catch (JAXBException ex) {
            Logger.getLogger(FundamentalDataLoaderWorker.class.getName()).log(Level.SEVERE, null, ex);
            throw new FundamentalParsingException(ex);
        }
    }

    private Issue findRightIssueInFinancialStatementIssues(ReportFinancialStatements rs) {
        Issue issue = null;
        if (rs != null && rs.getIssues() != null && rs.getIssues().getIssue() != null) {
            for (Issue i : rs.getIssues().getIssue()) {
                    if (nc.symbol().equals(i.getTicker())) {
                        issue = i;
                    }
                }
        }
        return issue;
    }
    
    /**
     * This method processes the Financial Statement in XML format into the
     * business objects.
     *
     * @param xml a Report Financial Statement in XML format
     */
    private void processXML(String xml) {

        try {
            ReportFinancialStatements rs = getReportFinancialStatementsFromXML(xml);

            Company company = controllerFactory.getCompanyJpaController().findCompany(nc.symbol());
            if (company == null) {
                // company doesn't exist in db.
                Issue issue = findRightIssueInFinancialStatementIssues(rs);
                if (issue != null) {
                    CompanyHelper companyHelper = new CompanyHelper();
                    company = companyHelper.createCompany(issue.getTicker(), rs.getCoIDs(), issue, rs.getCoGeneralInfo());

                    Exchange exchange = controllerFactory.getExchangeJpaController().findExchange(issue.getExchange().getCode());
                    if (exchange != null) {
                        company.setExchange(exchange);
                    } else {
                        Logger.getLogger(FundamentalDataLoaderWorker.class.getName()).log(Level.SEVERE, "The following exchange is missing in the DB: " + issue.getExchange().getCode());
                        throw new FundamentalMissingExchangeException("The following exchange is missing in the DB: " + issue.getExchange().getCode());
                    }

                    GeneralHelper helper = new GeneralHelper();
                    for (FiscalPeriod fp : rs.getFinancialStatements().getAnnualPeriods().getFiscalPeriod()) {
                        FinancialStatement fs = new FinancialStatement();
                        FiscalPeriodType fiscalPeriodType = controllerFactory.getFiscalPeriodTypeJpaController().findFiscalPeriodType(fp.getType());
                        fs.setFiscalPeriod(fiscalPeriodType);
                        fs.setFiscalYear(new Integer(fp.getFiscalYear())); // Number cast exception
                        fs.setEndDate(helper.stringToDate(fp.getEndDate()));

                        CompanyFinancialStatement companyFinancialStatement = new CompanyFinancialStatement(company, fs);
                        try {
                            controllerFactory.getCompanyFinancialStatementJpaController().create(companyFinancialStatement);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        company.getCompanyFinancialStatementCollection().add(companyFinancialStatement);

                        // here
                    }

                    try {
                        controllerFactory.getCompanyJpaController().create(company);
                    } catch (Exception ex) {
                        Logger.getLogger(FundamentalDataLoaderWorker.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                // Company already exists; update info.
            }

            for (FiscalPeriod fp : rs.getFinancialStatements().getAnnualPeriods().getFiscalPeriod()) {

            }

        } catch (FundamentalParsingException ex) {
            Logger.getLogger(FundamentalDataLoaderWorker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void saveXMLToDisk(String xml) {
        try {
            Files.write(Paths.get("./financial_statements/" + nc.symbol() + getCurrentDateString() + ".xml"), xml.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getCurrentDateString() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String strDate = sdfDate.format(now);

        return strDate;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        finished = false;
        apiController.reqFundamentals(nc, Types.FundamentalType.ReportsFinStatements, FundamentalDataLoaderWorker.this);
        int maxruns = 0;
        while (finished == false) {
            Thread.sleep(1000);
            if (maxruns >= 300) {
//                if (log != null) {
//                    log.log("Timeout " + contract.getSymbol() + " duration: " + duration + " duration unit: " + durationUnit.name());
//                }
                return Boolean.FALSE;
            }
            maxruns++;
        }
        return Boolean.TRUE;
    }

}
