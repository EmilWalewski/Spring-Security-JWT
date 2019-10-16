package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.user;

import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "principal_roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", unique = true)
    private Long roleID;

    @Enumerated(value = EnumType.STRING)
    @NaturalId
    @Column(name = "role_name", length = 15)
    private RoleName roleName;

//@Column(name = "permissions")

    //private String permissions;

    public Role() {
    }

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

}
