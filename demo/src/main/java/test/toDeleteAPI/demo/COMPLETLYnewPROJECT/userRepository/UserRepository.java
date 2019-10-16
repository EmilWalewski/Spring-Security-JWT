package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.user.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(" from User where username=:username")
    Optional<User> findByUsername(@Param("username") String username);
}
