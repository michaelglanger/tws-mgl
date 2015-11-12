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
@Table(name = "EXCHANGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exchange.findAll", query = "SELECT e FROM Exchange e"),
    @NamedQuery(name = "Exchange.findById", query = "SELECT e FROM Exchange e WHERE e.id = :id"),
    @NamedQuery(name = "Exchange.findByCode", query = "SELECT e FROM Exchange e WHERE e.code = :code"),
    @NamedQuery(name = "Exchange.findByCountry", query = "SELECT e FROM Exchange e WHERE e.country = :country"),
    @NamedQuery(name = "Exchange.findByName", query = "SELECT e FROM Exchange e WHERE e.name = :name")})
public class Exchange implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CODE")
    private String code;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy = "exchange")
    private Collection<Company> companyCollection;

    public Exchange() {
    }

    public Exchange(Integer id) {
        this.id = id;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Company> getCompanyCollection() {
        return companyCollection;
    }

    public void setCompanyCollection(Collection<Company> companyCollection) {
        this.companyCollection = companyCollection;
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
        if (!(object instanceof Exchange)) {
            return false;
        }
        Exchange other = (Exchange) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgl.fundamental.entities.Exchange[ id=" + id + " ]";
    }
    
}
