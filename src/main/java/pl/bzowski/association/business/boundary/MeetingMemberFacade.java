package pl.bzowski.association.business.boundary;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.bzowski.association.business.entity.Meeting;
import pl.bzowski.association.business.entity.MeetingMember;

/**
 *
 * @author Machcak
 */
@Stateless
public class MeetingMemberFacade extends AbstractFacade<MeetingMember> {
    @PersistenceContext(unitName = "persistence")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MeetingMemberFacade() {
        super(MeetingMember.class);
    }

}
