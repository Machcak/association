package pl.bzowski.association.business.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import pl.bzowski.association.business.boundary.MemberAdder;

/**
 *
 * @author Machcak
 */
@Entity
@Table(name = "meeting")
public class Meeting implements Serializable, MemberAdder.HaveingId{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1161888420785802366L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String number;
    
    private String description;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dayOf;
   
    @ManyToMany
    private Collection<AssociationMember> associationMembers;
    
    @OneToOne(mappedBy = "meeting")
    private Report report;
        
    @ManyToOne
    private Leadership leadership;

    @OneToMany(mappedBy = "meeting", fetch = FetchType.EAGER)
    private Set<Resolution> resolutions;
    
    public Meeting() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }    

    public Date getDayOf() {
        return dayOf;
    }

    public void setDayOf(Date dayOf) {
        this.dayOf = dayOf;
    }

    public Collection<AssociationMember> getAssociationMembers() {
        return associationMembers;
    }

    public void setAssociationMembers(Collection<AssociationMember> associationMembers) {
        this.associationMembers = associationMembers;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Report getReport() {
        return report;
    }

    public Leadership getLeadership() {
        return leadership;
    }

    public void setLeadership(Leadership leadership) {
        this.leadership = leadership;
    }    

    public Set<Resolution> getResolutions() {
        return resolutions;
    }

    public void setResolutions(Set<Resolution> resolutions) {
        this.resolutions = resolutions;
    }
    
    
}
