package com.mgl.entities;

import com.mgl.fundamental.controller.BalanceSheetJpaController;
import com.mgl.fundamental.controller.CashflowStatementJpaController;
import com.mgl.fundamental.controller.CompanyFinancialStatementJpaController;
import com.mgl.fundamental.controller.CompanyJpaController;
import com.mgl.fundamental.controller.ExchangeJpaController;
import com.mgl.fundamental.controller.FinancialStatementJpaController;
import com.mgl.fundamental.controller.FiscalPeriodTypeJpaController;
import com.mgl.fundamental.controller.IncomeStatementJpaController;
import com.mgl.fundamental.controller.PeriodTypeJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Michael G. Langer on 5/27/2015.
 */
public class ControllerFactory {

    private static final String PERSISTENCE_UNIT_NAME = "PU2";
    private EntityManagerFactory factory;
    
    // Technical entities controllers
    private BarJpaController bjc;
    private ContractJpaController cjc;
    
    // Fundamental entities controllers
    private BalanceSheetJpaController balanceSheetController;
    private CashflowStatementJpaController cashflowStatementController;
    private CompanyFinancialStatementJpaController companyFinancialStatementController;
    private CompanyJpaController companyController;
    private ExchangeJpaController exchangeController;
    private FinancialStatementJpaController financialStatementController;
    private FiscalPeriodTypeJpaController fiscalPeriodTypeController;
    private IncomeStatementJpaController incomeStatementController;
    private PeriodTypeJpaController periodTypeController;
    

    private EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory;
    }
    
    public PeriodTypeJpaController getPeriodTypeJpaController() {
        if (periodTypeController == null) {
            periodTypeController = new PeriodTypeJpaController(getEntityManagerFactory());
        }
        return periodTypeController;
    }
    
    public IncomeStatementJpaController getIncomeStatementJpaController() {
        if (incomeStatementController == null) {
            incomeStatementController = new IncomeStatementJpaController(getEntityManagerFactory());
        }
        return incomeStatementController;
    }
    
    public FiscalPeriodTypeJpaController getFiscalPeriodTypeJpaController() {
        if (fiscalPeriodTypeController == null) {
            fiscalPeriodTypeController = new FiscalPeriodTypeJpaController(getEntityManagerFactory());
        }
        return fiscalPeriodTypeController;
    }
    
    public FinancialStatementJpaController getFinancialStatementJpaController() {
        if (financialStatementController == null) {
            financialStatementController = new FinancialStatementJpaController(getEntityManagerFactory());
        }
        return financialStatementController;
    }
    
    public ExchangeJpaController getExchangeJpaController() {
        if (exchangeController == null) {
            exchangeController = new ExchangeJpaController(getEntityManagerFactory());
        }
        return exchangeController;
    }

    public CompanyJpaController getCompanyJpaController() {
        if (companyController == null) {
            companyController = new CompanyJpaController(getEntityManagerFactory());
        }
        return companyController;
    }
    
    public CompanyFinancialStatementJpaController getCompanyFinancialStatementJpaController() {
        if (companyFinancialStatementController == null) {
            companyFinancialStatementController = new CompanyFinancialStatementJpaController(getEntityManagerFactory());
        }
        return companyFinancialStatementController;
    }
    
    public CashflowStatementJpaController getCashflowStatementJpaController() {
        if (cashflowStatementController == null) {
            cashflowStatementController = new CashflowStatementJpaController(getEntityManagerFactory());
        }
        return cashflowStatementController;
    }
    
    public BalanceSheetJpaController getBalanceSheetJpaController() {
        if (balanceSheetController == null) {
            balanceSheetController = new BalanceSheetJpaController(getEntityManagerFactory());
        }
        return balanceSheetController;
    }
    
    public BarJpaController getBarJpaController() {
        if (bjc == null) {
            bjc = new BarJpaController(getEntityManagerFactory());
        }
        return bjc;
    }

    public ContractJpaController getContractJpaController() {
        if (cjc == null) {
            cjc = new ContractJpaController(getEntityManagerFactory());
        }
        return cjc;
    }
}
