package pl.bzowski.association.business.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.bzowski.association.business.entity.AssociationMember;

/**
 *
 * @author Machcak
 */
@Stateless
public class AssociationMemberFacade extends AbstractFacade<AssociationMember> {
    @PersistenceContext(unitName = "persistence")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AssociationMemberFacade() {
        super(AssociationMember.class);
    }

    public List<AssociationMember> findAllMembersOfLeadership(Long leadershipId) {
        return getEntityManager()
                .createNamedQuery(AssociationMember.findAllMembersOfLeadership, AssociationMember.class)
                .setParameter("leadershipId", leadershipId).getResultList();
    }

}
