/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mb
 */
public class NewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> listByRequest = new ArrayList<>();
        List<String> listBySession;
        List<String> listByCookie = new ArrayList<>();
        String name = request.getParameter("name");

        //Request
        listByRequest.add(name);
        request.setAttribute("listByRequest", listByRequest);

        //Session
        HttpSession session = request.getSession();
        if (session.getAttribute("listBySession") == null) {
            listBySession = new ArrayList<>();
        } else {
            listBySession = (List<String>) session.getAttribute("listBySession");
        }
        listBySession.add(name);
        session.setAttribute("listBySession", listBySession);

        //Cookie
        Cookie[] arrCookie = request.getCookies();
        String txt = "";
        if (arrCookie != null) {
            for (Cookie c : arrCookie) {
                if (c.getName().equals("listByCookie")) {
                    txt += c.getValue();
                    c.setMaxAge(0);
                }
            }
        }
        if(txt.isEmpty()){
            txt= name;
        }
        else{
            txt+="/"+name;
        }
        //add name trong cookie vao listByCookie
        if (txt != null && txt.length() != 0) {
            String[] arr = txt.split("/");
            for(String x : arr) {
                if(x!=null)
                listByCookie.add(x);
            }
        }
        
        System.out.println(listByCookie);
        Cookie c = new Cookie("listByCookie",txt);
        c.setMaxAge(30*24*60*60);
        response.addCookie(c);
        request.setAttribute("listByCookie", listByCookie);
        request.getRequestDispatcher("home.jsp").forward(request, response);

    }


/**
 * Handles the HTTP <code>POST</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
