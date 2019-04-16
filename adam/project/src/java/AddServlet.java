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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author aniap
 */
public class AddServlet extends HttpServlet {

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
            throws ServletException, IOException, TransformerException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext sc = getServletContext();
        List<String> wordsPL;
        List<String> wordsENG;
        wordsPL = (List<String>) sc.getAttribute("pol");
        wordsENG = (List<String>) sc.getAttribute("eng");
        wordsPL.add(request.getParameter("namePL"));
        wordsENG.add(request.getParameter("nameENG"));
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        try {
            File fXmlFile = new File("C:\\Users\\aniap\\Desktop\\projekt www\\ProjectWWW\\adam\\project\\src\\java\\wordsList2.xml");
            DocumentBuilder documentBuilder;
            documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element mainEl = document.createElement("words");
            document.appendChild(mainEl);
            for(int i = 0; i < wordsPL.size(); i ++){
            Element wordEl = document.createElement("word");
            mainEl.appendChild(wordEl);
            Element itemPL = document.createElement("item");
            wordEl.appendChild(itemPL);
            Attr attr = document.createAttribute("lang");
            attr.setValue("PL");
            itemPL.setAttributeNode(attr);
            itemPL.appendChild(document.createTextNode(wordsPL.get(i)));
            Element itemENG = document.createElement("item");
            wordEl.appendChild(itemENG);
            Attr attr2 = document.createAttribute("lang");
            attr2.setValue("ENG");
            itemENG.setAttributeNode(attr2);
            itemENG.appendChild(document.createTextNode(wordsENG.get(i)));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult("C:\\Users\\aniap\\Desktop\\projekt www\\ProjectWWW\\adam\\project\\src\\java\\wordsList2.xml");
            transformer.transform(domSource, streamResult);
            response.sendRedirect("/project/Servlet1");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(AddServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddServlet</title>");  
            out.println("Tu na razie jest sciernisko, ale bedzie dodawanie");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddServlet at " + request.getContextPath() + "</h1>");
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
        try {
            processRequest(request, response);
        } catch (TransformerException ex) {
            Logger.getLogger(AddServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (TransformerException ex) {
            Logger.getLogger(AddServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
