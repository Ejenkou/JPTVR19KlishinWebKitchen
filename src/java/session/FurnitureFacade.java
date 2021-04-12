package session;

import entity.Furniture;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FurnitureFacade extends AbstractFacade<Furniture> {

    @PersistenceContext(unitName = "JPTVR19KlishinWebKithcenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FurnitureFacade() {
        super(Furniture.class);
    }
    
}
