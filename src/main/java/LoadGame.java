import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoadGame")
public class LoadGame extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = getServletContext();
        synchronized (getServletContext()){
            String lang = request.getParameter("lang");
            List<String> words;
            if(lang.equals("pol"))
                words = (List<String>) sc.getAttribute("pol");
            else
                words = (List<String>) sc.getAttribute("eng");
        }
    }
}
