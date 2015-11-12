/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.entities;

import com.ib.controller.Bar;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author axjyb
 */
@Entity
@Table(name = "BAR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BAR.findAll", query = "SELECT b FROM BarEntity b"),
    @NamedQuery(name = "BAR.findById", query = "SELECT b FROM BarEntity b WHERE b.id = :id"),
    @NamedQuery(name = "BAR.findByDate", query = "SELECT b FROM BarEntity b WHERE b.date = :date"),
    @NamedQuery(name = "BAR.findByBopen", query = "SELECT b FROM BarEntity b WHERE b.bopen = :bopen"),
    @NamedQuery(name = "BAR.findByBclose", query = "SELECT b FROM BarEntity b WHERE b.bclose = :bclose"),
    @NamedQuery(name = "BAR.findByLow", query = "SELECT b FROM BarEntity b WHERE b.low = :low"),
    @NamedQuery(name = "BAR.findByHigh", query = "SELECT b FROM BarEntity b WHERE b.high = :high"),
    @NamedQuery(name = "BAR.findByVolume", query = "SELECT b FROM BarEntity b WHERE b.volume = :volume")})
public class BarEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    
    @Basic(optional = false)
    @Column(name = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "BOPEN", scale = 4, precision = 12)
    private BigDecimal bopen;
    
    @Basic(optional = false)
    @Column(name = "BCLOSE", scale = 4, precision = 12)
    private BigDecimal bclose;
    
    @Basic(optional = false)
    @Column(name = "LOW", scale = 4, precision = 12)
    private BigDecimal low;
    
    @Basic(optional = false)
    @Column(name = "HIGH", scale = 4, precision = 12)
    private BigDecimal high;
    
    @Column(name = "VOLUME")
    private Long volume;
    
    @JoinColumn(name = "CONTRACT", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ContractEntity contract;

    public BarEntity() {
    }

    public BarEntity(Integer id) {
        this.id = id;
    }

    public BarEntity(Integer id, Date date, BigDecimal bopen, BigDecimal bclose, BigDecimal low, BigDecimal high) {
        this.id = id;
        this.date = date;
        this.bopen = bopen;
        this.bclose = bclose;
        this.low = low;
        this.high = high;
    }
    
    public BarEntity(Bar bar) {
//        DecimalFormat df2 = new DecimalFormat("####.####");
                
//        double dopen = bar.open().toString();
//        Double.toString(dopen).toString();
//        String open = df2.format(dopen);
        this.bopen = new BigDecimal(Double.toString(bar.open()).toString());
        this.bclose = new BigDecimal(Double.toString(bar.close()).toString());
        this.low = new BigDecimal(Double.toString(bar.low()).toString());
        this.high = new BigDecimal(Double.toString(bar.high()).toString());
        this.volume = bar.volume();
        this.date = new Date(bar.time()*1000);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getBopen() {
        return bopen;
    }

    public void setBopen(BigDecimal bopen) {
        this.bopen = bopen;
    }

    public BigDecimal getBclose() {
        return bclose;
    }

    public void setBclose(BigDecimal bclose) {
        this.bclose = bclose;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
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
        if (!(object instanceof BarEntity)) {
            return false;
        }
        BarEntity other = (BarEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgl.entities.Bar[ id=" + id + " ]";
    }
    
}
