package pl.bzowski.association.business.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Machcak
 */
@Entity
public class MeetingMember implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private Meeting meeting;
    
    @OneToOne
    private AssociationMember member;

    public MeetingMember(){
        
    }
    
    public MeetingMember(Meeting meeting, AssociationMember member) {
        this.meeting = meeting;
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MeetingMember)) {
            return false;
        }
        MeetingMember other = (MeetingMember) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.bzowski.association.business.entity.MeetingMember[ id=" + id + " ]";
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public AssociationMember getMember() {
        return member;
    }

    public void setMember(AssociationMember member) {
        this.member = member;
    }
    
}
