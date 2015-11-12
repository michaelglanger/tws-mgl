/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Michael G. Langer
 */
@Entity
@Table(name = "CONTRACT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contract.findAll", query = "SELECT c FROM ContractEntity c"),
    @NamedQuery(name = "Contract.findById", query = "SELECT c FROM ContractEntity c WHERE c.id = :id"),
    @NamedQuery(name = "Contract.findBySymbol", query = "SELECT c FROM ContractEntity c WHERE c.symbol = :symbol"),
    @NamedQuery(name = "Contract.findByExchange", query = "SELECT c FROM ContractEntity c WHERE c.exchange = :exchange"),
    @NamedQuery(name = "Contract.findByCurrency", query = "SELECT c FROM ContractEntity c WHERE c.currency = :currency")})
public class ContractEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "SYMBOL")
    private String symbol;
    
    @Column(name = "COMPANY_NAME")
    private String name;
    
    @Column(name = "EXCHANGE")
    private String exchange;
    
    @Column(name = "CURRENCY")
    private String currency;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contract", fetch=FetchType.LAZY)
    private Collection<BarEntity> barCollection;

    public ContractEntity() {
    }

    public ContractEntity(Integer id) {
        this.id = id;
    }

    public ContractEntity(Integer id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }
    
    public ContractEntity(Integer id, String symbol, String name) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
    }

    public ContractEntity(String symbol, String exchange, String currency) {
        this.symbol = symbol;
        this.exchange = exchange;
        this.currency = currency;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @XmlTransient
    public Collection<BarEntity> getBarCollection() {
        return barCollection;
    }

    public void setBarCollection(Collection<BarEntity> barCollection) {
        this.barCollection = barCollection;
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
        if (!(object instanceof ContractEntity)) {
            return false;
        }
        ContractEntity other = (ContractEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgl.entities.ContractEntity[ id=" + id + " ]";
    }
    
}
