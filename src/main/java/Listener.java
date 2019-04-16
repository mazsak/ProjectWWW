import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Listener implements ServletContextListener{

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        synchronized (sce.getServletContext()) {
            String nameFile = sc.getInitParameter("nameFile");

            try {
                    File fileXML = new File("D:\\Projects\\Java\\WWW\\ProjectWWW\\" + nameFile);

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
                            eng.add(element.getElementsByTagName("item").item(1).getTextContent());
                        }
                    }

                    sc.setAttribute("pol", pol);
                    sc.setAttribute("eng", eng);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
