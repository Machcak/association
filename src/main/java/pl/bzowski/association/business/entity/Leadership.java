package pl.bzowski.association.business.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Machcak
 */
@Entity
public class Leadership implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String type;
    
    private Integer membersCount;
    
    private String number;
    
    @Temporal(TemporalType.DATE)
    private Date since;
    
    @Temporal(TemporalType.DATE)
    private Date continuesTo;
    
    @OneToOne(mappedBy = "leadership")
    private LeadershipMember leadershipMember;
    
    public Leadership(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(Integer membersCount) {
        this.membersCount = membersCount;
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

}
