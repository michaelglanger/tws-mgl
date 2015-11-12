/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.fundamental.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author mlanger
 */
@Entity
@Table(name = "FINANCIAL_STATEMENT")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "FinancialStatement.findAll", query = "SELECT f FROM FinancialStatement f"),
        @NamedQuery(name = "FinancialStatement.findById", query = "SELECT f FROM FinancialStatement f WHERE f.id = :id"),
        @NamedQuery(name = "FinancialStatement.findByEndDate", query = "SELECT f FROM FinancialStatement f WHERE f.endDate = :endDate"),
        @NamedQuery(name = "FinancialStatement.findByFiscalYear", query = "SELECT f FROM FinancialStatement f WHERE f.fiscalYear = :fiscalYear")})
public class FinancialStatement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "FISCAL_YEAR")
    private Integer fiscalYear;

    @JoinColumn(name = "BALANCE_SHEET", referencedColumnName = "ID")
    @ManyToOne
    private BalanceSheet balanceSheet;

    @JoinColumn(name = "CASHFLOW_STATEMENT", referencedColumnName = "ID")
    @ManyToOne
    private CashflowStatement cashflowStatement;

    @JoinColumn(name = "FISCAL_PERIOD", referencedColumnName = "ID")
    @ManyToOne
    private FiscalPeriodType fiscalPeriod;

    @JoinColumn(name = "INCOME_STATEMENT", referencedColumnName = "ID")
    @ManyToOne
    private IncomeStatement incomeStatement;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "financialStatement")
    private Collection<CompanyFinancialStatement> companyFinancialStatementCollection;

    public FinancialStatement() {
    }

    public FinancialStatement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(Integer fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public BalanceSheet getBalanceSheet() {
        return balanceSheet;
    }

    public void setBalanceSheet(BalanceSheet balanceSheet) {
        this.balanceSheet = balanceSheet;
    }

    public CashflowStatement getCashflowStatement() {
        return cashflowStatement;
    }

    public void setCashflowStatement(CashflowStatement cashflowStatement) {
        this.cashflowStatement = cashflowStatement;
    }

    public FiscalPeriodType getFiscalPeriod() {
        return fiscalPeriod;
    }

    public void setFiscalPeriod(FiscalPeriodType fiscalPeriod) {
        this.fiscalPeriod = fiscalPeriod;
    }

    public IncomeStatement getIncomeStatement() {
        return incomeStatement;
    }

    public void setIncomeStatement(IncomeStatement incomeStatement) {
        this.incomeStatement = incomeStatement;
    }

    @XmlTransient
    public Collection<CompanyFinancialStatement> getCompanyFinancialStatementCollection() {
        return companyFinancialStatementCollection;
    }

    public void setCompanyFinancialStatementCollection(Collection<CompanyFinancialStatement> companyFinancialStatementCollection) {
        this.companyFinancialStatementCollection = companyFinancialStatementCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinancialStatement)) {
            return false;
        }
        FinancialStatement other = (FinancialStatement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgl.fundamental.entities.FinancialStatement[ id=" + id + " ]";
    }

}
