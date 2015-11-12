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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 *
 * @author Michael G. Langer
 */
@Entity
@Table(name = "COMPANY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findById", query = "SELECT c FROM Company c WHERE c.id = :id"),
    @NamedQuery(name = "Company.findByCompanyName", query = "SELECT c FROM Company c WHERE c.companyName = :companyName"),
    @NamedQuery(name = "Company.findByTicker", query = "SELECT c FROM Company c WHERE c.ticker = :ticker"),
    @NamedQuery(name = "Company.findByIsin", query = "SELECT c FROM Company c WHERE c.isin = :isin"),
    @NamedQuery(name = "Company.findByReportingCurrency", query = "SELECT c FROM Company c WHERE c.reportingCurrency = :reportingCurrency"),
    @NamedQuery(name = "Company.findByLastAvailableAnnual", query = "SELECT c FROM Company c WHERE c.lastAvailableAnnual = :lastAvailableAnnual"),
    @NamedQuery(name = "Company.findByLastAvailableInterim", query = "SELECT c FROM Company c WHERE c.lastAvailableInterim = :lastAvailableInterim"),
    @NamedQuery(name = "Company.findBySharesOut", query = "SELECT c FROM Company c WHERE c.sharesOut = :sharesOut")})
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    
    @Column(name = "COMPANY_NAME")
    private String companyName;
    @Column(name = "TICKER")
    private String ticker;
    @Column(name = "ISIN")
    private String isin;
    @Column(name = "REPORTING_CURRENCY")
    private String reportingCurrency;
    @Column(name = "LAST_AVAILABLE_ANNUAL")
    @Temporal(TemporalType.DATE)
    private Date lastAvailableAnnual;
    @Column(name = "LAST_AVAILABLE_INTERIM")
    @Temporal(TemporalType.DATE)
    private Date lastAvailableInterim;
    @Column(name = "SHARES_OUT")
    private Long sharesOut;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<CompanyFinancialStatement> companyFinancialStatementCollection;
    @JoinColumn(name = "EXCHANGE", referencedColumnName = "ID")
    @ManyToOne
    private Exchange exchange;

    public Company() {
    }

    public Company(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getReportingCurrency() {
        return reportingCurrency;
    }

    public void setReportingCurrency(String reportingCurrency) {
        this.reportingCurrency = reportingCurrency;
    }

    public Date getLastAvailableAnnual() {
        return lastAvailableAnnual;
    }

    public void setLastAvailableAnnual(Date lastAvailableAnnual) {
        this.lastAvailableAnnual = lastAvailableAnnual;
    }

    public Date getLastAvailableInterim() {
        return lastAvailableInterim;
    }

    public void setLastAvailableInterim(Date lastAvailableInterim) {
        this.lastAvailableInterim = lastAvailableInterim;
    }

    public Long getSharesOut() {
        return sharesOut;
    }

    public void setSharesOut(Long sharesOut) {
        this.sharesOut = sharesOut;
    }

    @XmlTransient
    public Collection<CompanyFinancialStatement> getCompanyFinancialStatementCollection() {
        return companyFinancialStatementCollection;
    }

    public void setCompanyFinancialStatementCollection(Collection<CompanyFinancialStatement> companyFinancialStatementCollection) {
        this.companyFinancialStatementCollection = companyFinancialStatementCollection;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
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
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgl.fundamental.entities.Company[ id=" + id + " ]";
    }
    
}
