package pl.bzowski.association.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Machcak
 */
@Entity
@Table(name = "balance")
@NamedQueries({
    @NamedQuery(name = "Balance.findAll", query = "SELECT b FROM Balance b"),
    @NamedQuery(name = "Balance.findById", query = "SELECT b FROM Balance b WHERE b.id = :id"),
    @NamedQuery(name = "Balance.findByIncomingdate", query = "SELECT b FROM Balance b WHERE b.incomingdate = :incomingdate"),
    @NamedQuery(name = "Balance.findByBalanceeventId", query = "SELECT b FROM Balance b WHERE b.balanceeventId = :balanceeventId"),
    @NamedQuery(name = "Balance.findByAmount", query = "SELECT b FROM Balance b WHERE b.amount = :amount"),
    @NamedQuery(name = "Balance.findByAccountbalance", query = "SELECT b FROM Balance b WHERE b.accountbalance = :accountbalance")})
public class Balance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
 
    @Basic(optional = false)
    @NotNull
    @Column(name = "incomingdate")
    @Temporal(TemporalType.DATE)
    private Date incomingdate;

    @Basic(optional = false)
    @NotNull
    @Column(name = "balanceevent_id")
    private long balanceeventId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;

    @Basic(optional = false)
    @NotNull
    @Column(name = "accountbalance")
    private BigDecimal accountbalance;
    
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    @ManyToOne
    private AssociationMember memberId;
    
    @JoinColumn(name = "balanceterm_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Balanceterm balancetermId;

    public Balance() {
    }

    public Balance(Long id) {
        this.id = id;
    }

    public Balance(Long id, Date incomingdate, long balanceeventId, BigDecimal amount, BigDecimal accountbalance) {
        this.id = id;
        this.incomingdate = incomingdate;
        this.balanceeventId = balanceeventId;
        this.amount = amount;
        this.accountbalance = accountbalance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getIncomingdate() {
        return incomingdate;
    }

    public void setIncomingdate(Date incomingdate) {
        this.incomingdate = incomingdate;
    }

    public long getBalanceeventId() {
        return balanceeventId;
    }

    public void setBalanceeventId(long balanceeventId) {
        this.balanceeventId = balanceeventId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAccountbalance() {
        return accountbalance;
    }

    public void setAccountbalance(BigDecimal accountbalance) {
        this.accountbalance = accountbalance;
    }

    public AssociationMember getMemberId() {
        return memberId;
    }

    public void setMemberId(AssociationMember memberId) {
        this.memberId = memberId;
    }

    public Balanceterm getBalancetermId() {
        return balancetermId;
    }

    public void setBalancetermId(Balanceterm balancetermId) {
        this.balancetermId = balancetermId;
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
        if (!(object instanceof Balance)) {
            return false;
        }
        Balance other = (Balance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.bzowski.association.business.entity.Balance[ id=" + id + " ]";
    }

}
