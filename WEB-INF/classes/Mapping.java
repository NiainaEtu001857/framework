/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1857.framework;

/**
 *
 * @author Hasinjo
 */
public class Mapping {

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Mapping(String className, String method) {
        this.className = className;
        this.method = method;
    }

    public Mapping() {
    }
    String className;
    String method;
}
