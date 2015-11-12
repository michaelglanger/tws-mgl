/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.fundamental.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mlanger
 */
@Entity
@Table(name = "COMPANY_FINANCIAL_STATEMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompanyFinancialStatement.findAll", query = "SELECT c FROM CompanyFinancialStatement c"),
    @NamedQuery(name = "CompanyFinancialStatement.findById", query = "SELECT c FROM CompanyFinancialStatement c WHERE c.id = :id")})
public class CompanyFinancialStatement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "COMPANY", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Company company;
    @JoinColumn(name = "FINANCIAL_STATEMENT", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private FinancialStatement financialStatement;

    public CompanyFinancialStatement() {
    }

    public CompanyFinancialStatement(Integer id) {
        this.id = id;
    }

    public CompanyFinancialStatement(Company aCompany, FinancialStatement aFinancialStatement) {
        company = aCompany;
        financialStatement = aFinancialStatement;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public FinancialStatement getFinancialStatement() {
        return financialStatement;
    }

    public void setFinancialStatement(FinancialStatement financialStatement) {
        this.financialStatement = financialStatement;
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
        if (!(object instanceof CompanyFinancialStatement)) {
            return false;
        }
        CompanyFinancialStatement other = (CompanyFinancialStatement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgl.fundamental.entities.CompanyFinancialStatement[ id=" + id + " ]";
    }
    
}
