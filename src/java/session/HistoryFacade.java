package session;

import entity.Buyer;
import entity.Furniture;
import entity.History;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class HistoryFacade extends AbstractFacade<History> {

    @PersistenceContext(unitName = "JPTVR19KlishinWebKithcenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoryFacade() {
        super(History.class);
    }
    
    public List<History> findHistoriesWithFurniture(Buyer buyer) {
        try {
            return em.createQuery("SELECT h FROM History h WHERE h.takeonDate = NULL AND h.buyer = :buyer")
                    .setParameter("buyer", buyer)
                    .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    public List<Furniture> findBoughtFurniture(Buyer buyer) {
        try {
            List<Furniture> listBoughtFurniture = em.createQuery("SELECT h.furniture FROM History h WHERE h.buyer = :buyer")
                    .setParameter("buyer", buyer)
                    .getResultList();
            return listBoughtFurniture;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    
}
