import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {
                @WebInitParam(name = "namePattern", value = "^[A-Z][A-Za-z0-9]{2,}$"),
                @WebInitParam(name = "user", value = "Rahul"),
                @WebInitParam(name = "passwordPattern", value = "[A-Za-z0-9]{8,}$")
        }
)
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String user = request.getParameter("user");
        String password = request.getParameter("pwd");

        String userName = getServletConfig().getInitParameter("namePattern");
        String userId = getServletConfig().getInitParameter("user");
        String userPassword = getServletConfig().getInitParameter("passwordPattern");

        if (userId.equals(user) && Pattern.matches(userPassword, password) && Pattern.matches(userName, name)) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red> Either username Or password is wrong </font>");
            requestDispatcher.include(request, response);
        }
    }
}
