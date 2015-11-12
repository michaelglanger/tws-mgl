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
@Table(name = "BALANCE_SHEET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BalanceSheet.findAll", query = "SELECT b FROM BalanceSheet b"),
    @NamedQuery(name = "BalanceSheet.findById", query = "SELECT b FROM BalanceSheet b WHERE b.id = :id"),
    @NamedQuery(name = "BalanceSheet.findByAcsh", query = "SELECT b FROM BalanceSheet b WHERE b.acsh = :acsh"),
    @NamedQuery(name = "BalanceSheet.findByAcae", query = "SELECT b FROM BalanceSheet b WHERE b.acae = :acae"),
    @NamedQuery(name = "BalanceSheet.findByAsti", query = "SELECT b FROM BalanceSheet b WHERE b.asti = :asti"),
    @NamedQuery(name = "BalanceSheet.findByScsi", query = "SELECT b FROM BalanceSheet b WHERE b.scsi = :scsi"),
    @NamedQuery(name = "BalanceSheet.findByAacr", query = "SELECT b FROM BalanceSheet b WHERE b.aacr = :aacr"),
    @NamedQuery(name = "BalanceSheet.findByAtrc", query = "SELECT b FROM BalanceSheet b WHERE b.atrc = :atrc"),
    @NamedQuery(name = "BalanceSheet.findByAitl", query = "SELECT b FROM BalanceSheet b WHERE b.aitl = :aitl"),
    @NamedQuery(name = "BalanceSheet.findByAppy", query = "SELECT b FROM BalanceSheet b WHERE b.appy = :appy"),
    @NamedQuery(name = "BalanceSheet.findBySoca", query = "SELECT b FROM BalanceSheet b WHERE b.soca = :soca"),
    @NamedQuery(name = "BalanceSheet.findByAtca", query = "SELECT b FROM BalanceSheet b WHERE b.atca = :atca"),
    @NamedQuery(name = "BalanceSheet.findByAptc", query = "SELECT b FROM BalanceSheet b WHERE b.aptc = :aptc"),
    @NamedQuery(name = "BalanceSheet.findByAdep", query = "SELECT b FROM BalanceSheet b WHERE b.adep = :adep"),
    @NamedQuery(name = "BalanceSheet.findByAppn", query = "SELECT b FROM BalanceSheet b WHERE b.appn = :appn"),
    @NamedQuery(name = "BalanceSheet.findByAgwi", query = "SELECT b FROM BalanceSheet b WHERE b.agwi = :agwi"),
    @NamedQuery(name = "BalanceSheet.findByAint", query = "SELECT b FROM BalanceSheet b WHERE b.aint = :aint"),
    @NamedQuery(name = "BalanceSheet.findBySinv", query = "SELECT b FROM BalanceSheet b WHERE b.sinv = :sinv"),
    @NamedQuery(name = "BalanceSheet.findByAltr", query = "SELECT b FROM BalanceSheet b WHERE b.altr = :altr"),
    @NamedQuery(name = "BalanceSheet.findBySola", query = "SELECT b FROM BalanceSheet b WHERE b.sola = :sola"),
    @NamedQuery(name = "BalanceSheet.findByAtot", query = "SELECT b FROM BalanceSheet b WHERE b.atot = :atot"),
    @NamedQuery(name = "BalanceSheet.findByLapb", query = "SELECT b FROM BalanceSheet b WHERE b.lapb = :lapb"),
    @NamedQuery(name = "BalanceSheet.findByLpba", query = "SELECT b FROM BalanceSheet b WHERE b.lpba = :lpba"),
    @NamedQuery(name = "BalanceSheet.findByLaex", query = "SELECT b FROM BalanceSheet b WHERE b.laex = :laex"),
    @NamedQuery(name = "BalanceSheet.findByLstd", query = "SELECT b FROM BalanceSheet b WHERE b.lstd = :lstd"),
    @NamedQuery(name = "BalanceSheet.findByLcld", query = "SELECT b FROM BalanceSheet b WHERE b.lcld = :lcld"),
    @NamedQuery(name = "BalanceSheet.findBySocl", query = "SELECT b FROM BalanceSheet b WHERE b.socl = :socl"),
    @NamedQuery(name = "BalanceSheet.findByLtcl", query = "SELECT b FROM BalanceSheet b WHERE b.ltcl = :ltcl"),
    @NamedQuery(name = "BalanceSheet.findByLltd", query = "SELECT b FROM BalanceSheet b WHERE b.lltd = :lltd"),
    @NamedQuery(name = "BalanceSheet.findByLclo", query = "SELECT b FROM BalanceSheet b WHERE b.lclo = :lclo"),
    @NamedQuery(name = "BalanceSheet.findByLttd", query = "SELECT b FROM BalanceSheet b WHERE b.lttd = :lttd"),
    @NamedQuery(name = "BalanceSheet.findByStld", query = "SELECT b FROM BalanceSheet b WHERE b.stld = :stld"),
    @NamedQuery(name = "BalanceSheet.findBySbdt", query = "SELECT b FROM BalanceSheet b WHERE b.sbdt = :sbdt"),
    @NamedQuery(name = "BalanceSheet.findByLmin", query = "SELECT b FROM BalanceSheet b WHERE b.lmin = :lmin"),
    @NamedQuery(name = "BalanceSheet.findBySltl", query = "SELECT b FROM BalanceSheet b WHERE b.sltl = :sltl"),
    @NamedQuery(name = "BalanceSheet.findByLtll", query = "SELECT b FROM BalanceSheet b WHERE b.ltll = :ltll"),
    @NamedQuery(name = "BalanceSheet.findBySrpr", query = "SELECT b FROM BalanceSheet b WHERE b.srpr = :srpr"),
    @NamedQuery(name = "BalanceSheet.findBySprs", query = "SELECT b FROM BalanceSheet b WHERE b.sprs = :sprs"),
    @NamedQuery(name = "BalanceSheet.findByScms", query = "SELECT b FROM BalanceSheet b WHERE b.scms = :scms"),
    @NamedQuery(name = "BalanceSheet.findByQpic", query = "SELECT b FROM BalanceSheet b WHERE b.qpic = :qpic"),
    @NamedQuery(name = "BalanceSheet.findByQred", query = "SELECT b FROM BalanceSheet b WHERE b.qred = :qred"),
    @NamedQuery(name = "BalanceSheet.findByQtsc", query = "SELECT b FROM BalanceSheet b WHERE b.qtsc = :qtsc"),
    @NamedQuery(name = "BalanceSheet.findByQedg", query = "SELECT b FROM BalanceSheet b WHERE b.qedg = :qedg"),
    @NamedQuery(name = "BalanceSheet.findByQugl", query = "SELECT b FROM BalanceSheet b WHERE b.qugl = :qugl"),
    @NamedQuery(name = "BalanceSheet.findBySote", query = "SELECT b FROM BalanceSheet b WHERE b.sote = :sote"),
    @NamedQuery(name = "BalanceSheet.findByQtle", query = "SELECT b FROM BalanceSheet b WHERE b.qtle = :qtle"),
    @NamedQuery(name = "BalanceSheet.findByQtel", query = "SELECT b FROM BalanceSheet b WHERE b.qtel = :qtel"),
    @NamedQuery(name = "BalanceSheet.findByQtco", query = "SELECT b FROM BalanceSheet b WHERE b.qtco = :qtco"),
    @NamedQuery(name = "BalanceSheet.findByQtpo", query = "SELECT b FROM BalanceSheet b WHERE b.qtpo = :qtpo"),
    @NamedQuery(name = "BalanceSheet.findByStbp", query = "SELECT b FROM BalanceSheet b WHERE b.stbp = :stbp"),
    @NamedQuery(name = "BalanceSheet.findByStatementDate", query = "SELECT b FROM BalanceSheet b WHERE b.statementDate = :statementDate"),
    @NamedQuery(name = "BalanceSheet.findByPeriodLength", query = "SELECT b FROM BalanceSheet b WHERE b.periodLength = :periodLength")})
public class BalanceSheet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ACSH")
    private Long acsh;
    @Column(name = "ACAE")
    private Long acae;
    @Column(name = "ASTI")
    private Long asti;
    @Column(name = "SCSI")
    private Long scsi;
    @Column(name = "AACR")
    private Long aacr;
    @Column(name = "ATRC")
    private Long atrc;
    @Column(name = "AITL")
    private Long aitl;
    @Column(name = "APPY")
    private Long appy;
    @Column(name = "SOCA")
    private Long soca;
    @Column(name = "ATCA")
    private Long atca;
    @Column(name = "APTC")
    private Long aptc;
    @Column(name = "ADEP")
    private Long adep;
    @Column(name = "APPN")
    private Long appn;
    @Column(name = "AGWI")
    private Long agwi;
    @Column(name = "AINT")
    private Long aint;
    @Column(name = "SINV")
    private Long sinv;
    @Column(name = "ALTR")
    private Long altr;
    @Column(name = "SOLA")
    private Long sola;
    @Column(name = "ATOT")
    private Long atot;
    @Column(name = "LAPB")
    private Long lapb;
    @Column(name = "LPBA")
    private Long lpba;
    @Column(name = "LAEX")
    private Long laex;
    @Column(name = "LSTD")
    private Long lstd;
    @Column(name = "LCLD")
    private Long lcld;
    @Column(name = "SOCL")
    private Long socl;
    @Column(name = "LTCL")
    private Long ltcl;
    @Column(name = "LLTD")
    private Long lltd;
    @Column(name = "LCLO")
    private Long lclo;
    @Column(name = "LTTD")
    private Long lttd;
    @Column(name = "STLD")
    private Long stld;
    @Column(name = "SBDT")
    private Long sbdt;
    @Column(name = "LMIN")
    private Long lmin;
    @Column(name = "SLTL")
    private Long sltl;
    @Column(name = "LTLL")
    private Long ltll;
    @Column(name = "SRPR")
    private Long srpr;
    @Column(name = "SPRS")
    private Long sprs;
    @Column(name = "SCMS")
    private Long scms;
    @Column(name = "QPIC")
    private Long qpic;
    @Column(name = "QRED")
    private Long qred;
    @Column(name = "QTSC")
    private Long qtsc;
    @Column(name = "QEDG")
    private Long qedg;
    @Column(name = "QUGL")
    private Long qugl;
    @Column(name = "SOTE")
    private Long sote;
    @Column(name = "QTLE")
    private Long qtle;
    @Column(name = "QTEL")
    private Long qtel;
    @Column(name = "QTCO")
    private Long qtco;
    @Column(name = "QTPO")
    private Long qtpo;
    @Column(name = "STBP")
    private Long stbp;
    @Column(name = "STATEMENT_DATE")
    @Temporal(TemporalType.DATE)
    private Date statementDate;
    @Column(name = "PERIOD_LENGTH")
    private Integer periodLength;
    @OneToMany(mappedBy = "balanceSheet")
    private Collection<FinancialStatement> financialStatementCollection;
    @JoinColumn(name = "PERIOD_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private PeriodType periodType;

    public BalanceSheet() {
    }

    public BalanceSheet(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getAcsh() {
        return acsh;
    }

    public void setAcsh(Long acsh) {
        this.acsh = acsh;
    }

    public Long getAcae() {
        return acae;
    }

    public void setAcae(Long acae) {
        this.acae = acae;
    }

    public Long getAsti() {
        return asti;
    }

    public void setAsti(Long asti) {
        this.asti = asti;
    }

    public Long getScsi() {
        return scsi;
    }

    public void setScsi(Long scsi) {
        this.scsi = scsi;
    }

    public Long getAacr() {
        return aacr;
    }

    public void setAacr(Long aacr) {
        this.aacr = aacr;
    }

    public Long getAtrc() {
        return atrc;
    }

    public void setAtrc(Long atrc) {
        this.atrc = atrc;
    }

    public Long getAitl() {
        return aitl;
    }

    public void setAitl(Long aitl) {
        this.aitl = aitl;
    }

    public Long getAppy() {
        return appy;
    }

    public void setAppy(Long appy) {
        this.appy = appy;
    }

    public Long getSoca() {
        return soca;
    }

    public void setSoca(Long soca) {
        this.soca = soca;
    }

    public Long getAtca() {
        return atca;
    }

    public void setAtca(Long atca) {
        this.atca = atca;
    }

    public Long getAptc() {
        return aptc;
    }

    public void setAptc(Long aptc) {
        this.aptc = aptc;
    }

    public Long getAdep() {
        return adep;
    }

    public void setAdep(Long adep) {
        this.adep = adep;
    }

    public Long getAppn() {
        return appn;
    }

    public void setAppn(Long appn) {
        this.appn = appn;
    }

    public Long getAgwi() {
        return agwi;
    }

    public void setAgwi(Long agwi) {
        this.agwi = agwi;
    }

    public Long getAint() {
        return aint;
    }

    public void setAint(Long aint) {
        this.aint = aint;
    }

    public Long getSinv() {
        return sinv;
    }

    public void setSinv(Long sinv) {
        this.sinv = sinv;
    }

    public Long getAltr() {
        return altr;
    }

    public void setAltr(Long altr) {
        this.altr = altr;
    }

    public Long getSola() {
        return sola;
    }

    public void setSola(Long sola) {
        this.sola = sola;
    }

    public Long getAtot() {
        return atot;
    }

    public void setAtot(Long atot) {
        this.atot = atot;
    }

    public Long getLapb() {
        return lapb;
    }

    public void setLapb(Long lapb) {
        this.lapb = lapb;
    }

    public Long getLpba() {
        return lpba;
    }

    public void setLpba(Long lpba) {
        this.lpba = lpba;
    }

    public Long getLaex() {
        return laex;
    }

    public void setLaex(Long laex) {
        this.laex = laex;
    }

    public Long getLstd() {
        return lstd;
    }

    public void setLstd(Long lstd) {
        this.lstd = lstd;
    }

    public Long getLcld() {
        return lcld;
    }

    public void setLcld(Long lcld) {
        this.lcld = lcld;
    }

    public Long getSocl() {
        return socl;
    }

    public void setSocl(Long socl) {
        this.socl = socl;
    }

    public Long getLtcl() {
        return ltcl;
    }

    public void setLtcl(Long ltcl) {
        this.ltcl = ltcl;
    }

    public Long getLltd() {
        return lltd;
    }

    public void setLltd(Long lltd) {
        this.lltd = lltd;
    }

    public Long getLclo() {
        return lclo;
    }

    public void setLclo(Long lclo) {
        this.lclo = lclo;
    }

    public Long getLttd() {
        return lttd;
    }

    public void setLttd(Long lttd) {
        this.lttd = lttd;
    }

    public Long getStld() {
        return stld;
    }

    public void setStld(Long stld) {
        this.stld = stld;
    }

    public Long getSbdt() {
        return sbdt;
    }

    public void setSbdt(Long sbdt) {
        this.sbdt = sbdt;
    }

    public Long getLmin() {
        return lmin;
    }

    public void setLmin(Long lmin) {
        this.lmin = lmin;
    }

    public Long getSltl() {
        return sltl;
    }

    public void setSltl(Long sltl) {
        this.sltl = sltl;
    }

    public Long getLtll() {
        return ltll;
    }

    public void setLtll(Long ltll) {
        this.ltll = ltll;
    }

    public Long getSrpr() {
        return srpr;
    }

    public void setSrpr(Long srpr) {
        this.srpr = srpr;
    }

    public Long getSprs() {
        return sprs;
    }

    public void setSprs(Long sprs) {
        this.sprs = sprs;
    }

    public Long getScms() {
        return scms;
    }

    public void setScms(Long scms) {
        this.scms = scms;
    }

    public Long getQpic() {
        return qpic;
    }

    public void setQpic(Long qpic) {
        this.qpic = qpic;
    }

    public Long getQred() {
        return qred;
    }

    public void setQred(Long qred) {
        this.qred = qred;
    }

    public Long getQtsc() {
        return qtsc;
    }

    public void setQtsc(Long qtsc) {
        this.qtsc = qtsc;
    }

    public Long getQedg() {
        return qedg;
    }

    public void setQedg(Long qedg) {
        this.qedg = qedg;
    }

    public Long getQugl() {
        return qugl;
    }

    public void setQugl(Long qugl) {
        this.qugl = qugl;
    }

    public Long getSote() {
        return sote;
    }

    public void setSote(Long sote) {
        this.sote = sote;
    }

    public Long getQtle() {
        return qtle;
    }

    public void setQtle(Long qtle) {
        this.qtle = qtle;
    }

    public Long getQtel() {
        return qtel;
    }

    public void setQtel(Long qtel) {
        this.qtel = qtel;
    }

    public Long getQtco() {
        return qtco;
    }

    public void setQtco(Long qtco) {
        this.qtco = qtco;
    }

    public Long getQtpo() {
        return qtpo;
    }

    public void setQtpo(Long qtpo) {
        this.qtpo = qtpo;
    }

    public Long getStbp() {
        return stbp;
    }

    public void setStbp(Long stbp) {
        this.stbp = stbp;
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
        if (!(object instanceof BalanceSheet)) {
            return false;
        }
        BalanceSheet other = (BalanceSheet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgl.fundamental.entities.BalanceSheet[ id=" + id + " ]";
    }
    
}
