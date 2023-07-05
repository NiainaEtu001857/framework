/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1857.framework.servlet;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import etu1857.framework.Mapping;
import traitment.Utilitaire;

public class FontServlet extends HttpServlet {

    // private void init(HttpServletRequest request, PrintWriter out){
    //     try {

    //         // get Url 

    //         // StringBuffer url = request.getRequestURL();
    //         // String queryString = request.getQueryString();

    //         // if (queryString != null) {
    //         //     url.append("?").append(queryString);
    //         // }

    //         // String completeUrl = url.toString();

    //         // // -------------------------vue-------------------------------------
    //         ServletContext context = getServletConfig().getServletContext();
    //         String Realpath = context.getRealPath("/");
    //         out.println(Realpath);
    //         File file = new File(Realpath);
    //         // utilitaire.ScanPackage(Realpath, file);
    //         // HashMap<String,Mapping> list = utilitaire.getInstancesWithClass();
    //         // // Mapping mapping = utilitaire.getMappingWithHashMap(list, Realpath);
    //         // String annotation = completeUrl.split("ConceptionFramework/")[1].split("/")[0];
    //         // System.out.println(annotation);

            
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //     }
    // }
    
    private void init( HttpServletResponse response , PrintWriter out)throws ServletException, IOException {
        String Realpath = context.getRealPath("/");
        out.println(Realpath);
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // RequestDispatcher dispat = request.getRequestDispatcher("index.jsp");
        // dispat.forward(request,response);
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
        PrintWriter out = response.getWriter();
        init(request,out);
        // processRequest(request, response);
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
        PrintWriter out = response.getWriter();
        init(request,out);
        // processRequest(request, response);
    }
}
/*

 public class FrontServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        // init(request,out);
        // processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        // init(request,out);
        // processRequest(request, response);
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
// package etu1857.framework.servlet