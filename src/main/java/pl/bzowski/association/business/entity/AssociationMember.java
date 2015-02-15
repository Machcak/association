package pl.bzowski.association.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Machcak
 */
@Entity
public class AssociationMember implements Serializable {
       
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 3,max = 40)
    private String firstName;
    
    @NotNull
    @Size(min = 3,max = 40)
    private String lastName;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date since;
    
    @NotNull
    private Boolean active;
    
    @OneToMany(mappedBy = "associationMember")
    private List<Report> reports;

    @ManyToMany(mappedBy = "associationMembers")
    private List<Leadership> leaderships;

    public List<Leadership> getLeaderships() {
        return leaderships;
    }

    public void setLeaderships(List<Leadership> leaderships) {
        this.leaderships = leaderships;
    }
    
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

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        return true;
    }

   

}