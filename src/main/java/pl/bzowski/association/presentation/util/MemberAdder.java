package pl.bzowski.association.presentation.util;

import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import org.primefaces.model.DualListModel;
import pl.bzowski.association.business.boundary.AssociationMemberFacade;
import pl.bzowski.association.business.entity.AssociationMember;
import pl.bzowski.association.business.entity.Leadership;
import pl.bzowski.association.business.entity.Meeting;

/**
 *
 * @author Machcak
 */
public class MemberAdder {
    
    public interface HaveingId {
        Long getId();
    }
    
    @Inject private AssociationMemberFacade associationMemberFacade; 

    public DualListModel<AssociationMember> prepareAddMember(HaveingId selected) {
        List<AssociationMember> source = associationMemberFacade.findAll();
        Long leadershipId = selected.getId();
        List<AssociationMember> target = associationMemberFacade.findAllMembersOfLeadership(leadershipId);
        wywalZSourceZapisyZTarget(source, target);
        return new DualListModel<>(source, target);
    }
    
    private void wywalZSourceZapisyZTarget(List<AssociationMember> source, List<AssociationMember> target) {
        Iterator<AssociationMember> iterator = source.iterator();
        while (iterator.hasNext()) {
            AssociationMember next = iterator.next();
            boolean contains = target.contains(next);
            if(contains){
                iterator.remove();
            }
        }
    }

    public void saveLeadershipMembers(Leadership selected, List<AssociationMember> source, List<AssociationMember> target) {
        associationMemberFacade.saveLeadershipMembers(selected, source, target);
    }
    
    public void saveMeetingMembers(Meeting selected, List<AssociationMember> source, List<AssociationMember> target) {
        associationMemberFacade.saveMeetingMembers(selected, source, target);
    }

}
