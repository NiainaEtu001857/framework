/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1857.framework.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Utilities;

import etu1857.framework.Mapping;
import traitment.Utilitaire;

/**
 *
 * @author ITU
 */
public class FontServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = request.getRequestURL().toString();
            String valiny = request.getPathInfo();
            // --------------------------classe---------------------------------
            Utilitaire utilitaire = new Utilitaire(valiny);
            Mapping mapping = new Mapping();

            // -------------------------vue-------------------------------------

            String[] urls = utilitaire.getUrl();
            out.println("Voici l' url : " + url + "<br>");
            out.print(valiny + "<br>");

            // ------------------------mapping-----------------------------------
            out.println("Voici les mapping : <br>");
            if (urls[1] != null) {
                mapping.setClasse(urls[1]);
            }
            if (urls[2] != null) {
                mapping.setMethode(urls[2]);
            }
            out.print("la Classe : " + mapping.getClasse());
            out.print("la methode : " + mapping.getMethode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
