package pl.bzowski.association.business.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import pl.bzowski.association.presentation.util.MemberAdder;

/**
 *
 * @author Machcak
 */
@Entity
public class Meeting implements Serializable, MemberAdder.HaveingId{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String number;
    
    private String description;
    
    @ManyToMany
    private Collection<AssociationMember> associationMembers;
    
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

    public Collection<AssociationMember> getAssociationMembers() {
        return associationMembers;
    }

    public void setAssociationMembers(Collection<AssociationMember> associationMembers) {
        this.associationMembers = associationMembers;
    }
}
