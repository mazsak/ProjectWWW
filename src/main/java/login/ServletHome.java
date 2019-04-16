package login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Acerek
 */
public class ServletHome extends HttpServlet {

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

        String login = "";

        try (PrintWriter out = response.getWriter()) {
            
            Cookie[] cookies = request.getCookies(); //pobranie tablicy z ciasteczkami
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {//przeglądanie tablicy z ciasteczkami
                    Cookie cookie = cookies[i];
                    if (cookie.getName().equals("login")) {
                        response.sendRedirect("index.jsp");
                        break;
                    }
                }
                if (login.equals("")) {
                    out.println("Nie jestes zalogowany<br>");
                    showGuestVersion(response);
                }
            } else {
                if (login.equals("")) {
                    out.println("Nie jestes zalogowany<br>");
                    showGuestVersion(response);
                }
            }
        }
    }
    
    private void showGuestVersion(HttpServletResponse response) throws IOException{
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Witaj gościu!</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<br><br>Nawigacja: ");
            out.println("<br><a href=\"Login\">Logowanie</a>");
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
        processRequest(request, response);
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
