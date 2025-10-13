/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Thexe
 */
@Entity
@Table(name = "tbl_users", catalog = "tolpawiring", schema = "")
@NamedQueries({
    @NamedQuery(name = "TblUsers.findAll", query = "SELECT t FROM TblUsers t")
    , @NamedQuery(name = "TblUsers.findById", query = "SELECT t FROM TblUsers t WHERE t.id = :id")
    , @NamedQuery(name = "TblUsers.findByFlname", query = "SELECT t FROM TblUsers t WHERE t.flname = :flname")
    , @NamedQuery(name = "TblUsers.findByEmail", query = "SELECT t FROM TblUsers t WHERE t.email = :email")
    , @NamedQuery(name = "TblUsers.findByUsername", query = "SELECT t FROM TblUsers t WHERE t.username = :username")
    , @NamedQuery(name = "TblUsers.findByPassword", query = "SELECT t FROM TblUsers t WHERE t.password = :password")
    , @NamedQuery(name = "TblUsers.findByRole", query = "SELECT t FROM TblUsers t WHERE t.role = :role")
    , @NamedQuery(name = "TblUsers.findByAccountCreated", query = "SELECT t FROM TblUsers t WHERE t.accountCreated = :accountCreated")})
public class TblUsers implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "flname")
    private String flname;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Basic(optional = false)
    @Column(name = "account_created")
    @Temporal(TemporalType.DATE)
    private Date accountCreated;

    public TblUsers() {
    }

    public TblUsers(Integer id) {
        this.id = id;
    }

    public TblUsers(Integer id, String flname, String email, String username, String password, Date accountCreated) {
        this.id = id;
        this.flname = flname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.accountCreated = accountCreated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getFlname() {
        return flname;
    }

    public void setFlname(String flname) {
        String oldFlname = this.flname;
        this.flname = flname;
        changeSupport.firePropertyChange("flname", oldFlname, flname);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String oldEmail = this.email;
        this.email = email;
        changeSupport.firePropertyChange("email", oldEmail, email);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        String oldUsername = this.username;
        this.username = username;
        changeSupport.firePropertyChange("username", oldUsername, username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String oldPassword = this.password;
        this.password = password;
        changeSupport.firePropertyChange("password", oldPassword, password);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        String oldRole = this.role;
        this.role = role;
        changeSupport.firePropertyChange("role", oldRole, role);
    }

    public Date getAccountCreated() {
        return accountCreated;
    }

    public void setAccountCreated(Date accountCreated) {
        Date oldAccountCreated = this.accountCreated;
        this.accountCreated = accountCreated;
        changeSupport.firePropertyChange("accountCreated", oldAccountCreated, accountCreated);
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
        if (!(object instanceof TblUsers)) {
            return false;
        }
        TblUsers other = (TblUsers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Project.TblUsers[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
