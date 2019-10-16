package test.toDeleteAPI.demo.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import test.toDeleteAPI.demo.userPojo.userPojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class principal implements UserDetails {

     private userPojo pojo;

     public principal(userPojo pojo) {
         this.pojo = pojo;
     }

     @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

         List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

         this.pojo.converRolesToArray().forEach(roles -> {
             GrantedAuthority grantedRoles = new SimpleGrantedAuthority("ROLE_" + roles);
             grantedAuthorityList.add(grantedRoles);
         });

         this.pojo.converAuthoritiesToArray().forEach(authorities -> {
             GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authorities);
             grantedAuthorityList.add(grantedAuthority);
         });

         return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return pojo.getPassword();
    }

    @Override
    public String getUsername() {
        return pojo.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
