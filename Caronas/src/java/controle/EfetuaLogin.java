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
public class EfetuaLogin extends HttpServlet {

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
      out.println("<title>Servlet EfetuaLogin</title>");      
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet EfetuaLogin at " + request.getContextPath() + "</h1>");
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
    String username = request.getParameter("login");
    String senha = request.getParameter("senha");
    PrintWriter retorno = response.getWriter();
    if(username.equals(senha) && !username.isEmpty()){
      // login efetuado com sucesso
      retorno.print(this.constroiPagina(username));
    }else{
      // falha ao executar o login
      retorno.print(this.constroiPagina(""));
    }
    retorno.close();
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
//      processRequest(request, response);
    this.doGet(request, response);
  }
  
  private String constroiPagina(String usuario){
    String pagina;
    if (usuario.isEmpty()) {
      pagina = "<!DOCTYPE html>\n"
              + "<html>\n"
              + "  <head>\n"
              + "    <title>Caronas | Inicio</title>\n"
              + "    <meta charset=\"UTF-8\">\n"
              + "    <link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\">\n"
              + "    <script src=\"http://code.jquery.com/jquery-1.9.1.js\"></script>\n"
              + "  </head>\n"
              + "  <body>\n"
              + "    <header class=\"container\">\n"
              + "      <h1 id=\"logo\"><a href=\"index.html\">Caronas</a></h1>\n"
              + "      <nav id=\"menu\">\n"
              + "        <ul>\n"
              + "          <li><a href=\"index.html\" class=\"active\">Inicio</a></li>\n"
              + "          <li><a href=\"cadastro.html\">Cadastro</a></li>\n"
              + "        </ul>\n"
              + "      </nav>\n"
              + "    </header>\n"
              + "    <section class=\"container\">\n"
              + "      <article id=\"form\">\n"
              + "        <header>\n"
              + "          <h1>Login</h1>\n"
              + "        </header>\n"
              + "        <form method=\"post\" action=\"EfetuaLogin\">\n"
              + "          <p><label for=\"login\">Login</label><input type=\"text\" id=\"login\" name=\"login\"></p>\n"
              + "          <p><label for=\"senha\">Senha</label><input type=\"password\" id=\"senha\" name=\"senha\"></p>\n"
              + "          <p style=\"font-weight: bold;\">Login / Senha incorretos</p>\n"
              + "          <p><input type=\"submit\" value=\"Logar!\"></p>\n"
              + "        </form>\n"
              + "      </article>\n"
              + "    </section>\n"
              + "    <footer class=\"container\">\n"
              + "      <p>Desenvolvimento Web - UFSCar Sorocaba - 2013</p>\n"
              + "    </footer>\n"
              + "    <script>\n"
              + "      $(document).ready(function(){\n"
              + "\n"
              + "      });\n"
              + "    </script>\n"
              + "  </body>\n"
              + "</html>\n";
    } else {
      pagina = "<!DOCTYPE html>\n"
              + "<html>\n"
              + "  <head>\n"
              + "    <title>Caronas | Ola " + usuario + "!</title>\n"
              + "    <meta charset=\"UTF-8\">\n"
              + "    <link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\">\n"
              + "    <script src=\"http://code.jquery.com/jquery-1.9.1.js\"></script>\n"
              + "  </head>\n"
              + "  <body>\n"
              + "    <header class=\"container\">\n"
              + "      <h1 id=\"logo\"><a href=\"index.html\">Caronas</a></h1>\n"
              + "      <nav id=\"menu\">\n"
              + "        <ul>\n"
              + "          <li><a href=\"index.html\" class=\"active\">Inicio</a></li>\n"
              + "          <li><a href=\"#.html\">Perfil</a></li>\n"
              + "        </ul>\n"
              + "      </nav>\n"
              + "    </header>\n"
              + "    <section class=\"container\">\n"
              + "      <article id=\"form\">\n"
              + "        <header>\n"
              + "          <h1>Ola " + usuario + "!</h1>\n"
              + "        </header>\n"
              + "      </article>\n"
              + "    </section>\n"
              + "    <footer class=\"container\">\n"
              + "      <p>Desenvolvimento Web - UFSCar Sorocaba - 2013</p>\n"
              + "    </footer>\n"
              + "    <script>\n"
              + "      $(document).ready(function(){\n"
              + "\n"
              + "      });\n"
              + "    </script>\n"
              + "  </body>\n"
              + "</html>\n";
    }

    return pagina;
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
