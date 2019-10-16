package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.userRepository;

import org.springframework.stereotype.Service;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.user.User;

import java.util.Optional;

@Service
public class UserRepositoryService {

    private UserRepository userRepository;

    public UserRepositoryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByID(Long id){ return userRepository.findById(id);}

    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id){ userRepository.deleteById(id);}
}
