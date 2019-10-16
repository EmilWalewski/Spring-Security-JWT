package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.user.Role;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.user.RoleName;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


    Optional<Role> findByRoleName(RoleName roleName);
}
