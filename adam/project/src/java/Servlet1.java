/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author aniap
 */
public class Servlet1 extends HttpServlet {

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
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Words: </h1></br>");
            ServletContext sc = getServletContext();
            try {
                    File fileXML = new File("C:\\Users\\aniap\\Desktop\\projekt www\\ProjectWWW\\adam\\project\\src\\java\\wordsList2.xml");

                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(fileXML);

                    doc.getDocumentElement().normalize();

                    NodeList nList = doc.getElementsByTagName("word");

                    List<String> pol = new ArrayList<String>();
                    List<String> eng = new ArrayList<String>();
                    for (int i = 0; i < nList.getLength(); i++) {
                        Node data = nList.item(i);

                        if (data.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) data;

                            pol.add(element.getElementsByTagName("item").item(0).getTextContent());
                            out.println("PL: ");
                            out.println(element.getElementsByTagName("item").item(0).getTextContent()+"</br>");
                            eng.add(element.getElementsByTagName("item").item(1).getTextContent());
                            out.println("ENG: ");
                            out.println(element.getElementsByTagName("item").item(1).getTextContent());
                            out.println("<form method=\"post\" action=\"DeleteServlet\">");
                            out.println("<input type=\"hidden\" name =\"id\" value=\""+i+"\"/>");
                            out.println("<input type=\"submit\" value=\"delete\"/></br>");
                            out.println("</form>");
                            out.println("<form method=\"post\" action=\"EditWordServlet\">");
                            out.println("<input type=\"hidden\" name =\"id\" value=\""+i+"\"/>");
                            out.println("<input type=\"submit\" value=\"edit\"/></br>");
                            out.println("</form>");
                            out.println("</br></br>");
                        }
                    }

                    sc.setAttribute("pol", pol);
                    sc.setAttribute("eng", eng);

            out.println("<form method=\"post\" action=\"AddServlet\">");
            out.println("</br>Add Word: ");
            out.println("<input type=\"text\" name=\"namePL\"/>");
            out.println("<input type=\"text\" name=\"nameENG\"/>");
            out.println("<input type=\"submit\" value=\"add\"/></br>");
            out.println("</br>Na liscie w parametrach: </br>");
            for(int i=0; i<pol.size();i++){
                out.println("PL: "+pol.get(i)+"</br>");
                out.println("ENG: "+eng.get(i)+"</br></br>");
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            out.println("</form>");
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
