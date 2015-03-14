package pl.bzowski.association.business.boundary;

import java.math.BigDecimal;
import java.util.List;
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

    public BigDecimal getAccountBalance() {
        return em.createNamedQuery(Balance.ACCOUNT_BALANCE, BigDecimal.class)
                .getSingleResult();
    }

    public List<Balance> findAllOrderByIncomingdateDesc() {
        return em.createNamedQuery(Balance.FIND_ALL_ORDER_BY_INCOMINGDATE_DESC, Balance.class).getResultList();
    }

}
