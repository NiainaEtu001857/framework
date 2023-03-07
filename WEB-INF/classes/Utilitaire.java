/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitment;

/**
 *
 * @author ITU
 */
public class Utilitaire {

    String[] Url;

    public Utilitaire(String url) {
        String[] valiny = url.split("/");
        this.setUrl(valiny);
    }

    public String[] getUrl() {
        return Url;
    }

    public void setUrl(String[] url) {
        Url = url;
    }
}
