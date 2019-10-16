package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.userDetailsConfig;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.user.User;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.userRepository.UserRepositoryService;

@Service
public class PrincipalDetailsService implements UserDetailsService {

    private UserRepositoryService userRepositoryService;

    public PrincipalDetailsService(UserRepositoryService userRepositoryService) {
        this.userRepositoryService = userRepositoryService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) {

//        User user = userRepositoryService.getByUsername(s)
//                .orElseThrow(() ->
//                        new UsernameNotFoundException("User not found with username or email : " + s)
//                );
//
//        return PrincipalDetailsTest.create(user);

      return new PrincipalDetails(userRepositoryService.getByUsername(s).orElseThrow(()->new UsernameNotFoundException("User "+s+" not found")));
    }
}
