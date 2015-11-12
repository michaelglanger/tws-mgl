/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.fundamental.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mlanger
 */
@Entity
@Table(name = "FISCAL_PERIOD_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FiscalPeriodType.findAll", query = "SELECT f FROM FiscalPeriodType f"),
    @NamedQuery(name = "FiscalPeriodType.findById", query = "SELECT f FROM FiscalPeriodType f WHERE f.id = :id"),
    @NamedQuery(name = "FiscalPeriodType.findByPeriodType", query = "SELECT f FROM FiscalPeriodType f WHERE f.periodType = :periodType")})
public class FiscalPeriodType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "PERIOD_TYPE")
    private String periodType;

    @OneToMany(mappedBy = "fiscalPeriod")
    private Collection<FinancialStatement> financialStatementCollection;

    public FiscalPeriodType() {
    }

    public FiscalPeriodType(Integer id) {
        this.id = id;
    }

    public FiscalPeriodType(Integer id, String periodType) {
        this.id = id;
        this.periodType = periodType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    @XmlTransient
    public Collection<FinancialStatement> getFinancialStatementCollection() {
        return financialStatementCollection;
    }

    public void setFinancialStatementCollection(Collection<FinancialStatement> financialStatementCollection) {
        this.financialStatementCollection = financialStatementCollection;
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
        if (!(object instanceof FiscalPeriodType)) {
            return false;
        }
        FiscalPeriodType other = (FiscalPeriodType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgl.fundamental.entities.FiscalPeriodType[ id=" + id + " ]";
    }
    
}
