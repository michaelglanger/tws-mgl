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
 *
 * @author mlanger
 */
@Entity
@Table(name = "CASHFLOW_STATEMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CashflowStatement.findAll", query = "SELECT c FROM CashflowStatement c"),
    @NamedQuery(name = "CashflowStatement.findById", query = "SELECT c FROM CashflowStatement c WHERE c.id = :id"),
    @NamedQuery(name = "CashflowStatement.findByOnet", query = "SELECT c FROM CashflowStatement c WHERE c.onet = :onet"),
    @NamedQuery(name = "CashflowStatement.findBySded", query = "SELECT c FROM CashflowStatement c WHERE c.sded = :sded"),
    @NamedQuery(name = "CashflowStatement.findBySamt", query = "SELECT c FROM CashflowStatement c WHERE c.samt = :samt"),
    @NamedQuery(name = "CashflowStatement.findByObdt", query = "SELECT c FROM CashflowStatement c WHERE c.obdt = :obdt"),
    @NamedQuery(name = "CashflowStatement.findBySnci", query = "SELECT c FROM CashflowStatement c WHERE c.snci = :snci"),
    @NamedQuery(name = "CashflowStatement.findBySocf", query = "SELECT c FROM CashflowStatement c WHERE c.socf = :socf"),
    @NamedQuery(name = "CashflowStatement.findByOtlo", query = "SELECT c FROM CashflowStatement c WHERE c.otlo = :otlo"),
    @NamedQuery(name = "CashflowStatement.findByScex", query = "SELECT c FROM CashflowStatement c WHERE c.scex = :scex"),
    @NamedQuery(name = "CashflowStatement.findBySicf", query = "SELECT c FROM CashflowStatement c WHERE c.sicf = :sicf"),
    @NamedQuery(name = "CashflowStatement.findByItli", query = "SELECT c FROM CashflowStatement c WHERE c.itli = :itli"),
    @NamedQuery(name = "CashflowStatement.findBySfcf", query = "SELECT c FROM CashflowStatement c WHERE c.sfcf = :sfcf"),
    @NamedQuery(name = "CashflowStatement.findByFcdp", query = "SELECT c FROM CashflowStatement c WHERE c.fcdp = :fcdp"),
    @NamedQuery(name = "CashflowStatement.findByFpss", query = "SELECT c FROM CashflowStatement c WHERE c.fpss = :fpss"),
    @NamedQuery(name = "CashflowStatement.findByFprd", query = "SELECT c FROM CashflowStatement c WHERE c.fprd = :fprd"),
    @NamedQuery(name = "CashflowStatement.findByFtlf", query = "SELECT c FROM CashflowStatement c WHERE c.ftlf = :ftlf"),
    @NamedQuery(name = "CashflowStatement.findBySfee", query = "SELECT c FROM CashflowStatement c WHERE c.sfee = :sfee"),
    @NamedQuery(name = "CashflowStatement.findBySncc", query = "SELECT c FROM CashflowStatement c WHERE c.sncc = :sncc"),
    @NamedQuery(name = "CashflowStatement.findByScip", query = "SELECT c FROM CashflowStatement c WHERE c.scip = :scip"),
    @NamedQuery(name = "CashflowStatement.findBySctp", query = "SELECT c FROM CashflowStatement c WHERE c.sctp = :sctp"),
    @NamedQuery(name = "CashflowStatement.findByStatementDate", query = "SELECT c FROM CashflowStatement c WHERE c.statementDate = :statementDate"),
    @NamedQuery(name = "CashflowStatement.findByPeriodLength", query = "SELECT c FROM CashflowStatement c WHERE c.periodLength = :periodLength")})
public class CashflowStatement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ONET")
    private Long onet;
    @Column(name = "SDED")
    private Long sded;
    @Column(name = "SAMT")
    private Long samt;
    @Column(name = "OBDT")
    private Long obdt;
    @Column(name = "SNCI")
    private Long snci;
    @Column(name = "SOCF")
    private Long socf;
    @Column(name = "OTLO")
    private Long otlo;
    @Column(name = "SCEX")
    private Long scex;
    @Column(name = "SICF")
    private Long sicf;
    @Column(name = "ITLI")
    private Long itli;
    @Column(name = "SFCF")
    private Long sfcf;
    @Column(name = "FCDP")
    private Long fcdp;
    @Column(name = "FPSS")
    private Long fpss;
    @Column(name = "FPRD")
    private Long fprd;
    @Column(name = "FTLF")
    private Long ftlf;
    @Column(name = "SFEE")
    private Long sfee;
    @Column(name = "SNCC")
    private Long sncc;
    @Column(name = "SCIP")
    private Long scip;
    @Column(name = "SCTP")
    private Long sctp;
    @Column(name = "STATEMENT_DATE")
    @Temporal(TemporalType.DATE)
    private Date statementDate;
    @Column(name = "PERIOD_LENGTH")
    private Integer periodLength;
    @JoinColumn(name = "PERIOD_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private PeriodType periodType;
    @OneToMany(mappedBy = "cashflowStatement")
    private Collection<FinancialStatement> financialStatementCollection;

    public CashflowStatement() {
    }

    public CashflowStatement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOnet() {
        return onet;
    }

    public void setOnet(Long onet) {
        this.onet = onet;
    }

    public Long getSded() {
        return sded;
    }

    public void setSded(Long sded) {
        this.sded = sded;
    }

    public Long getSamt() {
        return samt;
    }

    public void setSamt(Long samt) {
        this.samt = samt;
    }

    public Long getObdt() {
        return obdt;
    }

    public void setObdt(Long obdt) {
        this.obdt = obdt;
    }

    public Long getSnci() {
        return snci;
    }

    public void setSnci(Long snci) {
        this.snci = snci;
    }

    public Long getSocf() {
        return socf;
    }

    public void setSocf(Long socf) {
        this.socf = socf;
    }

    public Long getOtlo() {
        return otlo;
    }

    public void setOtlo(Long otlo) {
        this.otlo = otlo;
    }

    public Long getScex() {
        return scex;
    }

    public void setScex(Long scex) {
        this.scex = scex;
    }

    public Long getSicf() {
        return sicf;
    }

    public void setSicf(Long sicf) {
        this.sicf = sicf;
    }

    public Long getItli() {
        return itli;
    }

    public void setItli(Long itli) {
        this.itli = itli;
    }

    public Long getSfcf() {
        return sfcf;
    }

    public void setSfcf(Long sfcf) {
        this.sfcf = sfcf;
    }

    public Long getFcdp() {
        return fcdp;
    }

    public void setFcdp(Long fcdp) {
        this.fcdp = fcdp;
    }

    public Long getFpss() {
        return fpss;
    }

    public void setFpss(Long fpss) {
        this.fpss = fpss;
    }

    public Long getFprd() {
        return fprd;
    }

    public void setFprd(Long fprd) {
        this.fprd = fprd;
    }

    public Long getFtlf() {
        return ftlf;
    }

    public void setFtlf(Long ftlf) {
        this.ftlf = ftlf;
    }

    public Long getSfee() {
        return sfee;
    }

    public void setSfee(Long sfee) {
        this.sfee = sfee;
    }

    public Long getSncc() {
        return sncc;
    }

    public void setSncc(Long sncc) {
        this.sncc = sncc;
    }

    public Long getScip() {
        return scip;
    }

    public void setScip(Long scip) {
        this.scip = scip;
    }

    public Long getSctp() {
        return sctp;
    }

    public void setSctp(Long sctp) {
        this.sctp = sctp;
    }

    public Date getStatementDate() {
        return statementDate;
    }

    public void setStatementDate(Date statementDate) {
        this.statementDate = statementDate;
    }

    public Integer getPeriodLength() {
        return periodLength;
    }

    public void setPeriodLength(Integer periodLength) {
        this.periodLength = periodLength;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(PeriodType periodType) {
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
        if (!(object instanceof CashflowStatement)) {
            return false;
        }
        CashflowStatement other = (CashflowStatement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgl.fundamental.entities.CashflowStatement[ id=" + id + " ]";
    }
    
}
