package pl.bzowski.association.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pl.bzowski.association.business.boundary.MemberAdder;

/**
 *
 * @author Machcak
 */
@Entity
@Table(name = "leadership")
public class Leadership implements Serializable, MemberAdder.HaveingId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private LeadershipType leadershipType;
        
    private String number;
    
    @Temporal(TemporalType.DATE)
    private Date since;
    
    @Temporal(TemporalType.DATE)
    private Date continuesTo;
    
    @ManyToOne
    private LeadershipMember leadershipMember;
   
    @OneToMany(mappedBy = "leadership")
    private List<Meeting> meetings;

    @OneToMany(mappedBy = "leadership")
    private List<Resolution> resolutions;
    
    public Leadership(){
        
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LeadershipType getLeadershipType() {
        return leadershipType;
    }

    public void setLeadershipType(LeadershipType leadershipType) {
        this.leadershipType = leadershipType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public Date getSince() {
        return since;
    }

    public void setContinuesTo(Date continuesTo) {
        this.continuesTo = continuesTo;
    }

    public Date getContinuesTo() {
        return continuesTo;
    }

    public LeadershipMember getLeadershipMember() {
        return leadershipMember;
    }

    public void setLeadershipMember(LeadershipMember leadershipMember) {
        this.leadershipMember = leadershipMember;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Leadership other = (Leadership) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public List<Resolution> getResolutions() {
        return resolutions;
    }

    public void setResolutions(List<Resolution> resolutions) {
        this.resolutions = resolutions;
    }   
}
