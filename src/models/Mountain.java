/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Mountain implements Serializable {

    private static final long serialVersionUID = 1L;
    private String code;
    private String name;
    private String province;
    private String description;

    public Mountain() {
    }

    public Mountain(String code) {
        this.code = code;
    }

    public Mountain(String code, String name, String province, String description) {
        this.code = code;
        this.name = name;
        this.province = province;
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Mountain) {
            Mountain mt = (Mountain) obj;
            return this.getCode().equals(mt.getCode());
        }
        return false;
    }

    public String getCode() {
        return code;
    }
    
    // Getter & setter for code
    public void setCode(String code) {
        String tmp = code;
        if (tmp.length() < 2) {
            tmp = "0" + tmp;
        }
        if (!tmp.startsWith("MT")) {
            tmp = "MT" + tmp;
        }
        this.code = tmp;
    }

    // Getter & setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter & setter for province
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    // Getter & setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.code;
    }
}