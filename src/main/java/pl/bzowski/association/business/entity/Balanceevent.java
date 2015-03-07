package pl.bzowski.association.business.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Machcak
 */
@Entity
@Table(name = "balanceevent")
@NamedQueries({
    @NamedQuery(name = "Balanceevent.findAll", query = "SELECT b FROM Balanceevent b"),
    @NamedQuery(name = "Balanceevent.findById", query = "SELECT b FROM Balanceevent b WHERE b.id = :id"),
    @NamedQuery(name = "Balanceevent.findByEventname", query = "SELECT b FROM Balanceevent b WHERE b.eventname = :eventname")})
public class Balanceevent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "eventname")
    private long eventname;

    public Balanceevent() {
    }

    public Balanceevent(Long id) {
        this.id = id;
    }

    public Balanceevent(Long id, long eventname) {
        this.id = id;
        this.eventname = eventname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getEventname() {
        return eventname;
    }

    public void setEventname(long eventname) {
        this.eventname = eventname;
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
        if (!(object instanceof Balanceevent)) {
            return false;
        }
        Balanceevent other = (Balanceevent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.bzowski.association.business.entity.Balanceevent[ id=" + id + " ]";
    }

}
