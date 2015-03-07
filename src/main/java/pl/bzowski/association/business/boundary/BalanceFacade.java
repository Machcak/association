package pl.bzowski.association.business.boundary;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.bzowski.association.business.entity.Balance;

/**
 *
 * @author Machcak
 */
@Stateless
public class BalanceFacade extends AbstractFacade<Balance> {
    @PersistenceContext(unitName = "persistence")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BalanceFacade() {
        super(Balance.class);
    }

}
