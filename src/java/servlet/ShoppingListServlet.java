package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 843876
 */
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String action = request.getParameter("action");
        if (action.equals("logout")){
                        session.invalidate();
            request.setAttribute("message", "Logout Successful");
        }
        
        
        if ((session.getId() != null) && (session.getAttribute("username") != null)) {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);
            //return;
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("itemList") == null) {
            ArrayList<String> itemList = new ArrayList<>();
            session.setAttribute("itemList", itemList);
        }

        String action = request.getParameter("action");
        String username = request.getParameter("username");

        if (action.equals("register") && username != null) {
            session.setAttribute("username", username);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);
        }

        if (action.equals("add")) {
            session.getAttribute("itemList");
            String item = request.getParameter("item");
            //session.setAttribute("itemList",itemList.append(item));
            //itemList.append(item);
        }

    }

}
