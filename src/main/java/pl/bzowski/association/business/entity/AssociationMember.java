package pl.bzowski.association.business.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import pl.bzowski.association.business.boundary.MemberAdder;

/**
 *
 * @author Machcak
 */
@NamedQueries({
    @NamedQuery(name = AssociationMember.findAllMembersOfLeadership,
            query = "SELECT lm.member FROM LeadershipMember lm WHERE lm.leadership.id = :leadershipId ")
})
@Entity
public class AssociationMember implements Serializable, MemberAdder.HaveingId {
    
    public static final String findAllMembersOfLeadership = "AssociationMember.findAllMembersOfLeadership";

    @OneToOne(mappedBy = "member")
    private LeadershipMember leadershipMember;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 40)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 40)
    private String lastName;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date since;

    @NotNull
    private Boolean active;

    @ManyToMany(mappedBy = "associationMembers")
    private List<Meeting> meetings;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "associationMember")
    private List<Balance> balanceList;
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LeadershipMember getLeadershipMember() {
        return leadershipMember;
    }

    public void setLeadershipMember(LeadershipMember leadershipMember) {
        this.leadershipMember = leadershipMember;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }   

    public List<Balance> getBalanceList() {
        return balanceList;
    }

    public void setBalanceList(List<Balance> balanceList) {
        this.balanceList = balanceList;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AssociationMember other = (AssociationMember) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
