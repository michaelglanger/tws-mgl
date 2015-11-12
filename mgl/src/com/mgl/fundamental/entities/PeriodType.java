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
@Table(name = "PERIOD_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeriodType.findAll", query = "SELECT p FROM PeriodType p"),
    @NamedQuery(name = "PeriodType.findById", query = "SELECT p FROM PeriodType p WHERE p.id = :id"),
    @NamedQuery(name = "PeriodType.findByCode", query = "SELECT p FROM PeriodType p WHERE p.code = :code"),
    @NamedQuery(name = "PeriodType.findByValue", query = "SELECT p FROM PeriodType p WHERE p.value = :value"),
    @NamedQuery(name = "PeriodType.findByColumn4", query = "SELECT p FROM PeriodType p WHERE p.column4 = :column4")})
public class PeriodType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CODE")
    private String code;
    @Column(name = "VALUE")
    private String value;
    @Basic(optional = false)
    @Column(name = "COLUMN_4")
    private int column4;
    @OneToMany(mappedBy = "periodType")
    private Collection<CashflowStatement> cashflowStatementCollection;
    @OneToMany(mappedBy = "periodType")
    private Collection<IncomeStatement> incomeStatementCollection;
    @OneToMany(mappedBy = "periodType")
    private Collection<BalanceSheet> balanceSheetCollection;

    public PeriodType() {
    }

    public PeriodType(Integer id) {
        this.id = id;
    }

    public PeriodType(Integer id, int column4) {
        this.id = id;
        this.column4 = column4;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getColumn4() {
        return column4;
    }

    public void setColumn4(int column4) {
        this.column4 = column4;
    }

    @XmlTransient
    public Collection<CashflowStatement> getCashflowStatementCollection() {
        return cashflowStatementCollection;
    }

    public void setCashflowStatementCollection(Collection<CashflowStatement> cashflowStatementCollection) {
        this.cashflowStatementCollection = cashflowStatementCollection;
    }

    @XmlTransient
    public Collection<IncomeStatement> getIncomeStatementCollection() {
        return incomeStatementCollection;
    }

    public void setIncomeStatementCollection(Collection<IncomeStatement> incomeStatementCollection) {
        this.incomeStatementCollection = incomeStatementCollection;
    }

    @XmlTransient
    public Collection<BalanceSheet> getBalanceSheetCollection() {
        return balanceSheetCollection;
    }

    public void setBalanceSheetCollection(Collection<BalanceSheet> balanceSheetCollection) {
        this.balanceSheetCollection = balanceSheetCollection;
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
        if (!(object instanceof PeriodType)) {
            return false;
        }
        PeriodType other = (PeriodType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgl.fundamental.entities.PeriodType[ id=" + id + " ]";
    }
    
}
