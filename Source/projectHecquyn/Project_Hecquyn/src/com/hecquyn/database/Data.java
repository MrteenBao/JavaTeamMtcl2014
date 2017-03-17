/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hecquyn.database;

/**
 *
 * @author Vuong Gia Phu
 */
public class Data {
    private String link;
    private String text;
    
    public Data(String link, String text) {
        this.link = link;
        this.text = text;
    }
    
    public String getLink() {
        return link;
    }
    
    public String getText() {
        return text;
    }
}
