package session;

import entity.Role;
import entity.User;
import entity.UserRoles;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserRolesFacade extends AbstractFacade<UserRoles> {
    
    @PersistenceContext(unitName = "JPTVR19KlishinWebKithcenPU")
    private EntityManager em;
    
    @EJB private RoleFacade roleFacade;
   
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRolesFacade() {
        super(UserRoles.class);
    }

    public boolean isRole(String roleName, User user) {
        try {
            UserRoles userRoles = (UserRoles) em.createQuery("SELECT userRoles FROM UserRoles userRoles WHERE userRoles.role.roleName = :roleName AND userRoles.user = :user")
                    .setParameter("roleName", roleName)
                    .setParameter("user", user)
                    .getSingleResult();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getTopRoleForUser(User user) {
        List<UserRoles> listUserRoles = em.createQuery("SELECT userRoles FROM UserRoles userRoles WHERE userRoles.user = :user")
                .setParameter("user", user)
                .getResultList();
        for(int i=0;i<listUserRoles.size();i++){
            if("ADMIN".equals(listUserRoles.get(i).getRole().getRoleName())){
                return "ADMIN";
            }
        }
        for(int i=0;i<listUserRoles.size();i++){
            if("MANAGER".equals(listUserRoles.get(i).getRole().getRoleName())){
                return "MANAGER";
            }
        }
        for(int i=0;i<listUserRoles.size();i++){
            if("READER".equals(listUserRoles.get(i).getRole().getRoleName())){
                return "READER";
            }
        }
        return "-";
    }
    
    public List<Role> listRolesForUser(User user){
        try {
            return em.createQuery("SELECT userRoles.role FROM UserRoles userRoles WHERE userRoles.user = :user")
                .setParameter("user", user)
                .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    public void setNewRole(UserRoles userRoles) {
        this.em.createQuery("DELETE FROM UserRoles userRoles WHERE userRoles.user = :user")
                .setParameter("user", userRoles.getUser())
                .executeUpdate();
        UserRoles ur;
        if("ADMIN".equals(userRoles.getRole().getRoleName())){
            ur = new UserRoles(userRoles.getUser(),roleFacade.findByName("ADMIN"));
            this.create(ur);
            ur = new UserRoles(userRoles.getUser(),roleFacade.findByName("MANAGER"));
            this.create(ur);
            ur = new UserRoles(userRoles.getUser(),roleFacade.findByName("READER"));
            this.create(ur);
        }
        if("MANAGER".equals(userRoles.getRole().getRoleName())){
            ur = new UserRoles(userRoles.getUser(),roleFacade.findByName("MANAGER"));
            this.create(ur);
            ur = new UserRoles(userRoles.getUser(),roleFacade.findByName("READER"));
            this.create(ur);
        }
        if("READER".equals(userRoles.getRole().getRoleName())){
            ur = new UserRoles(userRoles.getUser(),roleFacade.findByName("READER"));
            this.create(ur);
        }
    }

    public List<String> findRoles(User user) {
         return em.createQuery("SELECT ur.role.roleName FROM UserRoles ur WHERE ur.user = :user")
                .setParameter("user", user)
                .getResultList();
         
    }
    
}
