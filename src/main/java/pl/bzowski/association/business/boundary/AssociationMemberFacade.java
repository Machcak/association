package pl.bzowski.association.business.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.bzowski.association.business.entity.AssociationMember;
import pl.bzowski.association.business.entity.Leadership;
import pl.bzowski.association.business.entity.LeadershipMember;

/**
 *
 * @author Machcak
 */
@Stateless
public class AssociationMemberFacade extends AbstractFacade<AssociationMember> {
    @PersistenceContext(unitName = "persistence")
    private EntityManager em;
    
    @Inject
    private LeadershipMemberFacade leadershipMemberFacade;

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

    public void saveLeadershipMembers(Leadership leadership, List<AssociationMember> source, List<AssociationMember> target) {
        getEntityManager()
                .createQuery("DELETE FROM LeadershipMember lm WHERE lm.leadership = :leadership")
                .setParameter("leadership", leadership)
                .executeUpdate();
        for(AssociationMember t : target){
            LeadershipMember lm = new LeadershipMember(leadership, t);
            leadershipMemberFacade.create(lm);
        }
    }

}
