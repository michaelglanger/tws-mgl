/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.fundamental.entities;

import com.mgl.fundamental.IncomeStatementEnum;
import com.mgl.fundamental.reportfinancialstatements.LineItem;
import com.mgl.fundamental.reportfinancialstatements.Statement;

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
@Table(name = "INCOME_STATEMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IncomeStatement.findAll", query = "SELECT i FROM IncomeStatement i"),
    @NamedQuery(name = "IncomeStatement.findById", query = "SELECT i FROM IncomeStatement i WHERE i.id = :id"),
    @NamedQuery(name = "IncomeStatement.findBySrev", query = "SELECT i FROM IncomeStatement i WHERE i.srev = :srev"),
    @NamedQuery(name = "IncomeStatement.findBySore", query = "SELECT i FROM IncomeStatement i WHERE i.sore = :sore"),
    @NamedQuery(name = "IncomeStatement.findByRtlr", query = "SELECT i FROM IncomeStatement i WHERE i.rtlr = :rtlr"),
    @NamedQuery(name = "IncomeStatement.findByScor", query = "SELECT i FROM IncomeStatement i WHERE i.scor = :scor"),
    @NamedQuery(name = "IncomeStatement.findBySgrp", query = "SELECT i FROM IncomeStatement i WHERE i.sgrp = :sgrp"),
    @NamedQuery(name = "IncomeStatement.findBySsga", query = "SELECT i FROM IncomeStatement i WHERE i.ssga = :ssga"),
    @NamedQuery(name = "IncomeStatement.findByErad", query = "SELECT i FROM IncomeStatement i WHERE i.erad = :erad"),
    @NamedQuery(name = "IncomeStatement.findBySdpr", query = "SELECT i FROM IncomeStatement i WHERE i.sdpr = :sdpr"),
    @NamedQuery(name = "IncomeStatement.findBySinn", query = "SELECT i FROM IncomeStatement i WHERE i.sinn = :sinn"),
    @NamedQuery(name = "IncomeStatement.findBySuie", query = "SELECT i FROM IncomeStatement i WHERE i.suie = :suie"),
    @NamedQuery(name = "IncomeStatement.findBySooe", query = "SELECT i FROM IncomeStatement i WHERE i.sooe = :sooe"),
    @NamedQuery(name = "IncomeStatement.findByEtoe", query = "SELECT i FROM IncomeStatement i WHERE i.etoe = :etoe"),
    @NamedQuery(name = "IncomeStatement.findBySopi", query = "SELECT i FROM IncomeStatement i WHERE i.sopi = :sopi"),
    @NamedQuery(name = "IncomeStatement.findBySnin", query = "SELECT i FROM IncomeStatement i WHERE i.snin = :snin"),
    @NamedQuery(name = "IncomeStatement.findByNgla", query = "SELECT i FROM IncomeStatement i WHERE i.ngla = :ngla"),
    @NamedQuery(name = "IncomeStatement.findBySont", query = "SELECT i FROM IncomeStatement i WHERE i.sont = :sont"),
    @NamedQuery(name = "IncomeStatement.findByEibt", query = "SELECT i FROM IncomeStatement i WHERE i.eibt = :eibt"),
    @NamedQuery(name = "IncomeStatement.findByTtax", query = "SELECT i FROM IncomeStatement i WHERE i.ttax = :ttax"),
    @NamedQuery(name = "IncomeStatement.findByTiat", query = "SELECT i FROM IncomeStatement i WHERE i.tiat = :tiat"),
    @NamedQuery(name = "IncomeStatement.findByCmin", query = "SELECT i FROM IncomeStatement i WHERE i.cmin = :cmin"),
    @NamedQuery(name = "IncomeStatement.findByCeia", query = "SELECT i FROM IncomeStatement i WHERE i.ceia = :ceia"),
    @NamedQuery(name = "IncomeStatement.findByCgap", query = "SELECT i FROM IncomeStatement i WHERE i.cgap = :cgap"),
    @NamedQuery(name = "IncomeStatement.findByNibx", query = "SELECT i FROM IncomeStatement i WHERE i.nibx = :nibx"),
    @NamedQuery(name = "IncomeStatement.findByStxi", query = "SELECT i FROM IncomeStatement i WHERE i.stxi = :stxi"),
    @NamedQuery(name = "IncomeStatement.findByNinc", query = "SELECT i FROM IncomeStatement i WHERE i.ninc = :ninc"),
    @NamedQuery(name = "IncomeStatement.findBySani", query = "SELECT i FROM IncomeStatement i WHERE i.sani = :sani"),
    @NamedQuery(name = "IncomeStatement.findByCiac", query = "SELECT i FROM IncomeStatement i WHERE i.ciac = :ciac"),
    @NamedQuery(name = "IncomeStatement.findByXnic", query = "SELECT i FROM IncomeStatement i WHERE i.xnic = :xnic"),
    @NamedQuery(name = "IncomeStatement.findBySdaj", query = "SELECT i FROM IncomeStatement i WHERE i.sdaj = :sdaj"),
    @NamedQuery(name = "IncomeStatement.findBySdni", query = "SELECT i FROM IncomeStatement i WHERE i.sdni = :sdni"),
    @NamedQuery(name = "IncomeStatement.findBySdws", query = "SELECT i FROM IncomeStatement i WHERE i.sdws = :sdws"),
    @NamedQuery(name = "IncomeStatement.findBySdbf", query = "SELECT i FROM IncomeStatement i WHERE i.sdbf = :sdbf"),
    @NamedQuery(name = "IncomeStatement.findByDdps1", query = "SELECT i FROM IncomeStatement i WHERE i.ddps1 = :ddps1"),
    @NamedQuery(name = "IncomeStatement.findByVdes", query = "SELECT i FROM IncomeStatement i WHERE i.vdes = :vdes"),
    @NamedQuery(name = "IncomeStatement.findByStatementDate", query = "SELECT i FROM IncomeStatement i WHERE i.statementDate = :statementDate"),
    @NamedQuery(name = "IncomeStatement.findByPeriodLength", query = "SELECT i FROM IncomeStatement i WHERE i.periodLength = :periodLength")})
public class IncomeStatement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "SREV")
    private Long srev;
    @Column(name = "SORE")
    private Long sore;
    @Column(name = "RTLR")
    private Long rtlr;
    @Column(name = "SCOR")
    private Long scor;
    @Column(name = "SGRP")
    private Long sgrp;
    @Column(name = "SSGA")
    private Long ssga;
    @Column(name = "ERAD")
    private Long erad;
    @Column(name = "SDPR")
    private Long sdpr;
    @Column(name = "SINN")
    private Long sinn;
    @Column(name = "SUIE")
    private Long suie;
    @Column(name = "SOOE")
    private Long sooe;
    @Column(name = "ETOE")
    private Long etoe;
    @Column(name = "SOPI")
    private Long sopi;
    @Column(name = "SNIN")
    private Long snin;
    @Column(name = "NGLA")
    private Long ngla;
    @Column(name = "SONT")
    private Long sont;
    @Column(name = "EIBT")
    private Long eibt;
    @Column(name = "TTAX")
    private Long ttax;
    @Column(name = "TIAT")
    private Long tiat;
    @Column(name = "CMIN")
    private Long cmin;
    @Column(name = "CEIA")
    private Long ceia;
    @Column(name = "CGAP")
    private Long cgap;
    @Column(name = "NIBX")
    private Long nibx;
    @Column(name = "STXI")
    private Long stxi;
    @Column(name = "NINC")
    private Long ninc;
    @Column(name = "SANI")
    private Long sani;
    @Column(name = "CIAC")
    private Long ciac;
    @Column(name = "XNIC")
    private Long xnic;
    @Column(name = "SDAJ")
    private Long sdaj;
    @Column(name = "SDNI")
    private Long sdni;
    @Column(name = "SDWS")
    private Long sdws;
    @Column(name = "SDBF")
    private Long sdbf;
    @Column(name = "DDPS1")
    private Long ddps1;
    @Column(name = "VDES")
    private Long vdes;
    @Column(name = "STATEMENT_DATE")
    @Temporal(TemporalType.DATE)
    private Date statementDate;
    @Column(name = "PERIOD_LENGTH")
    private Integer periodLength;
    @OneToMany(mappedBy = "incomeStatement")
    private Collection<FinancialStatement> financialStatementCollection;
    @JoinColumn(name = "PERIOD_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private PeriodType periodType;

    public IncomeStatement() {
    }

    public IncomeStatement(Integer id) {
        this.id = id;
    }

    private Long getLongValue(LineItem li) {
        return li.getValue().movePointRight(6).longValue();
    }

    private void process(LineItem li) {
        switch (li.getCoaCode()) {
            case "AA":
                srev = getLongValue(li);
                break;
            case IncomeStatementEnum.SORE.name():
                break;
            case IncomeStatementEnum.RTLR.name():
                break;
            case IncomeStatementEnum.SCOR.name():
                break;






//                            SGRP("Gross Profit"),
//                            SSGA("Selling/General/Admin. Expenses, Total"),
//                            ERAD("Research &amp; Development"),
//                            SDPR("Depreciation/Amortization"),
//                            SINN("Interest Exp.(Inc.),Net-Operating, Total"),
//                            SUIE("Unusual Expense (Income)"),
//                            SOOE("Other Operating Expenses, Total"),
//                            ETOE("Total Operating Expense"),
//                            SOPI("Operating Income"),
//                            SNIN("Interest Inc.(Exp.),Net-Non-Op., Total"),
//                            NGLA("Gain (Loss) on Sale of Assets"),
//                            SONT("Other, Net"),
//                            EIBT("Net Income Before Taxes"),
//                            TTAX("Provision for Income Taxes"),
//                            TIAT("Net Income After Taxes"),
//                            CMIN("Minority Interest"),
//                            CEIA("Equity In Affiliates"),
//                            CGAP("U.S. GAAP Adjustment"),
//                            NIBX("Net Income Before Extra. Items"),
//                            STXI("Total Extraordinary Items"),
//                            NINC("Net Income"),
//                            SANI("Total Adjustments to Net Income"),
//                            CIAC("Income Available to Com Excl ExtraOrd"),
//                            XNIC("Income Available to Com Incl ExtraOrd"),
//                            SDAJ("Dilution Adjustment"),
//                            SDNI("Diluted Net Income"),
//                            SDWS("Diluted Weighted Average Shares"),
//                            SDBF("Diluted EPS Excluding ExtraOrd Items"),
//                            DDPS1("DPS - Common Stock Primary Issue"),
//                            VDES("Diluted Normalized EPS");
        }
    }

    public IncomeStatement(Statement statement) {
        for (LineItem li : statement.getLineItem()) {

        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSrev() {
        return srev;
    }

    public void setSrev(Long srev) {
        this.srev = srev;
    }

    public Long getSore() {
        return sore;
    }

    public void setSore(Long sore) {
        this.sore = sore;
    }

    public Long getRtlr() {
        return rtlr;
    }

    public void setRtlr(Long rtlr) {
        this.rtlr = rtlr;
    }

    public Long getScor() {
        return scor;
    }

    public void setScor(Long scor) {
        this.scor = scor;
    }

    public Long getSgrp() {
        return sgrp;
    }

    public void setSgrp(Long sgrp) {
        this.sgrp = sgrp;
    }

    public Long getSsga() {
        return ssga;
    }

    public void setSsga(Long ssga) {
        this.ssga = ssga;
    }

    public Long getErad() {
        return erad;
    }

    public void setErad(Long erad) {
        this.erad = erad;
    }

    public Long getSdpr() {
        return sdpr;
    }

    public void setSdpr(Long sdpr) {
        this.sdpr = sdpr;
    }

    public Long getSinn() {
        return sinn;
    }

    public void setSinn(Long sinn) {
        this.sinn = sinn;
    }

    public Long getSuie() {
        return suie;
    }

    public void setSuie(Long suie) {
        this.suie = suie;
    }

    public Long getSooe() {
        return sooe;
    }

    public void setSooe(Long sooe) {
        this.sooe = sooe;
    }

    public Long getEtoe() {
        return etoe;
    }

    public void setEtoe(Long etoe) {
        this.etoe = etoe;
    }

    public Long getSopi() {
        return sopi;
    }

    public void setSopi(Long sopi) {
        this.sopi = sopi;
    }

    public Long getSnin() {
        return snin;
    }

    public void setSnin(Long snin) {
        this.snin = snin;
    }

    public Long getNgla() {
        return ngla;
    }

    public void setNgla(Long ngla) {
        this.ngla = ngla;
    }

    public Long getSont() {
        return sont;
    }

    public void setSont(Long sont) {
        this.sont = sont;
    }

    public Long getEibt() {
        return eibt;
    }

    public void setEibt(Long eibt) {
        this.eibt = eibt;
    }

    public Long getTtax() {
        return ttax;
    }

    public void setTtax(Long ttax) {
        this.ttax = ttax;
    }

    public Long getTiat() {
        return tiat;
    }

    public void setTiat(Long tiat) {
        this.tiat = tiat;
    }

    public Long getCmin() {
        return cmin;
    }

    public void setCmin(Long cmin) {
        this.cmin = cmin;
    }

    public Long getCeia() {
        return ceia;
    }

    public void setCeia(Long ceia) {
        this.ceia = ceia;
    }

    public Long getCgap() {
        return cgap;
    }

    public void setCgap(Long cgap) {
        this.cgap = cgap;
    }

    public Long getNibx() {
        return nibx;
    }

    public void setNibx(Long nibx) {
        this.nibx = nibx;
    }

    public Long getStxi() {
        return stxi;
    }

    public void setStxi(Long stxi) {
        this.stxi = stxi;
    }

    public Long getNinc() {
        return ninc;
    }

    public void setNinc(Long ninc) {
        this.ninc = ninc;
    }

    public Long getSani() {
        return sani;
    }

    public void setSani(Long sani) {
        this.sani = sani;
    }

    public Long getCiac() {
        return ciac;
    }

    public void setCiac(Long ciac) {
        this.ciac = ciac;
    }

    public Long getXnic() {
        return xnic;
    }

    public void setXnic(Long xnic) {
        this.xnic = xnic;
    }

    public Long getSdaj() {
        return sdaj;
    }

    public void setSdaj(Long sdaj) {
        this.sdaj = sdaj;
    }

    public Long getSdni() {
        return sdni;
    }

    public void setSdni(Long sdni) {
        this.sdni = sdni;
    }

    public Long getSdws() {
        return sdws;
    }

    public void setSdws(Long sdws) {
        this.sdws = sdws;
    }

    public Long getSdbf() {
        return sdbf;
    }

    public void setSdbf(Long sdbf) {
        this.sdbf = sdbf;
    }

    public Long getDdps1() {
        return ddps1;
    }

    public void setDdps1(Long ddps1) {
        this.ddps1 = ddps1;
    }

    public Long getVdes() {
        return vdes;
    }

    public void setVdes(Long vdes) {
        this.vdes = vdes;
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

    @XmlTransient
    public Collection<FinancialStatement> getFinancialStatementCollection() {
        return financialStatementCollection;
    }

    public void setFinancialStatementCollection(Collection<FinancialStatement> financialStatementCollection) {
        this.financialStatementCollection = financialStatementCollection;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
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
        if (!(object instanceof IncomeStatement)) {
            return false;
        }
        IncomeStatement other = (IncomeStatement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgl.fundamental.entities.IncomeStatement[ id=" + id + " ]";
    }
    
}
