//package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.userDetailsConfig;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.user.User;
//
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//public class PrincipalDetailsTest implements UserDetails {
//
//    private Long id;
//
//    private String username;
//
//    private String password;
//
//    private int active;
//
//    private Collection<? extends GrantedAuthority> authorities;
//
//    public PrincipalDetailsTest(Long id, String username, String password, int active, Collection<? extends GrantedAuthority> authorities) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.active = active;
//        this.authorities = authorities;
//    }
//
//    public static PrincipalDetailsTest create(User user) {
//
//       return new PrincipalDetailsTest(
//                user.getUserID(),
//                user.getUsername(),
//                user.getPassword(),
//                1,
//                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList())
//        );
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public int getActive() {
//        return active;
//    }
//
//    public void setActive(int active) {
//        this.active = active;
//    }
//
//    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
//        this.authorities = authorities;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
