/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.fundamental.entities.old;

/**
 *
 * @author Michael Langer
 */
public abstract class Statement {
    
    protected Company company;
    
    public Statement(Company aCompany) {
        this.company = aCompany;
    }
    
    public Company getCompany() {
        return company;
    }
    
    public void setCompany(Company aCompany){
        this.company = aCompany;
    }
    
}
