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
        if (action != null && action.equals("logout")) {
            session.invalidate();
            request.setAttribute("message", "Logout Successful");
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);
        }

        if ((session.getId() == null) || (session.getAttribute("username") == null)) {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);

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
            ArrayList<String> itemList = (ArrayList) session.getAttribute("itemList");
            String item = request.getParameter("itemInput");
            itemList.add(item);
            request.setAttribute("itemList", itemList);
            session.setAttribute("itemList", itemList);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);
        }

        if (action.equals("delete")) {
            ArrayList<String> itemList = (ArrayList) session.getAttribute("itemList");
            String itemToDelete = request.getParameter("item");
            itemList.remove(itemToDelete);
            request.setAttribute("itemList", itemList);
            session.setAttribute("itemList", itemList);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);
        }
    }
}
