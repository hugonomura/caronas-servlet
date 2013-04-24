/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hugo
 */
public class BuscaCidade extends HttpServlet {

  /**
   * Processes requests for both HTTP
   * <code>GET</code> and
   * <code>POST</code> methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try {
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet BuscaCidade</title>");      
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet BuscaCidade at " + request.getContextPath() + "</h1>");
      out.println("</body>");
      out.println("</html>");
    } finally {      
      out.close();
    }
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP
   * <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String estado = request.getParameter("uf");
    String cidades = "";
    response.setCharacterEncoding("UTF-8");
    if(estado.equals("SP")){
      cidades += "<option value=\"São Paulo\">São Paulo</option>";
      cidades += "<option value=\"São Roque\">São Roque</option>";
      cidades += "<option value=\"Sorocaba\">Sorocaba</option>";
    }
    if(estado.equals("RJ")){
      cidades += "<option value=\"Angra dos reis\">Angra dos Reis</option>";
      cidades += "<option value=\"Parati\">Parati</option>";
      cidades += "<option value=\"Rio de Janeiro\">Rio de Janeiro</option>";
    }
    if(estado.equals("SC")){
      cidades += "<option value=\"Bandeirante\">Bandeirante</option>";
      cidades += "<option value=\"Florianópolis\">Florianópolis</option>";
      cidades += "<option value=\"Princesa\">Princesa</option>";
    }
    PrintWriter writer = response.getWriter();
    writer.print(cidades);
    writer.close();
  }

  /**
   * Handles the HTTP
   * <code>POST</code> method.
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
