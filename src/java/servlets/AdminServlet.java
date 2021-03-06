package servlets;


import entity.Buyer;
import entity.User;
import entity.Role;
import entity.UserRoles;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.FurnitureFacade;
import session.HistoryFacade;
import session.BuyerFacade;
import session.UserFacade;
import session.UserRolesFacade;
import session.RoleFacade;


@WebServlet(name = "AdminServlet", urlPatterns = {
    "/listBuyers",
    "/adminPanel",
    "/setRole",
        

})
public class AdminServlet extends HttpServlet {
    @EJB 
    private FurnitureFacade furnitureFacade;
    @EJB 
    private BuyerFacade buyerFacade;
    @EJB
    private HistoryFacade historyFacade;
    @EJB
    private UserFacade userFacade;
    @EJB private UserRolesFacade userRolesFacade;
    @EJB private RoleFacade roleFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "У вас нет прав! Войдите в систему с соответствующими правами!");
            request.getRequestDispatcher("/showLoginForm").forward(request, response);
            return;
        }
        User user = (User) session.getAttribute("user");
        if(user == null){
            request.setAttribute("info", "У вас нет прав! Войдите в систему с соответствующими правами!");
            request.getRequestDispatcher("/showLoginForm").forward(request, response);
            return;
        }
        boolean isRole = userRolesFacade.isRole("ADMIN", user);
        if(!isRole){
            request.setAttribute("info", "У вас нет прав! Войдите в систему с соответствующими правами!");
            request.getRequestDispatcher("/showLoginForm").forward(request, response);
            return;
        }
        String path = request.getServletPath();

        switch (path) {
            case "/listBuyers":
                request.setAttribute("listBuyers", "true");
                List<Buyer> listBuyers = buyerFacade.findAll();
                request.setAttribute("listBuyers", listBuyers);
                request.getRequestDispatcher(LoginServlet.pathToJsp.getString("listBuyers")).forward(request, response);
                break;
            case "/adminPanel":
                request.setAttribute("adminPanel", "true");
                List<Role> listRoles = roleFacade.findAll();
                request.setAttribute("listRoles", listRoles);
                List<User> listUsers = userFacade.findAll();
                Map<User,List<Role>> usersMap = new HashMap<>();
                for(User u : listUsers){
                    usersMap.put(u, userRolesFacade.listRolesForUser(user));
                }
                request.setAttribute("usersMap", usersMap);
                request.getRequestDispatcher(LoginServlet.pathToJsp.getString("adminPanel")).forward(request, response);
                break;
                
            case "/setRole":
                String userId = request.getParameter("roleId");
                String roleId = request.getParameter("userId");
                if("".equals(userId) || userId == null
                        || "".equals(roleId) || roleId == null){
                    request.setAttribute("userId", userId);
                    request.setAttribute("roleId", roleId);
                    request.setAttribute("info", "Заполните все поля");
                    request.getRequestDispatcher("/adminPanel").forward(request, response);
                    break;
                }
                user = userFacade.find(Long.parseLong(userId));
                Role role = roleFacade.find(Long.parseLong(roleId));
                UserRoles userRoles = new UserRoles(user, role);
                if(!"admin".equals(user.getLogin())){
                    userRolesFacade.setNewRole(userRoles);
                    request.setAttribute("info", "Роль изменена");
                }else{
                    request.setAttribute("userId", userId);
                    request.setAttribute("roleId", roleId);
                    request.setAttribute("info", "Изменить роль невозможно");
                }
                request.getRequestDispatcher("/adminPanel").forward(request, response);
                break;
                
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
