package ia.ffy.foodforyou.login.security.service;

import ia.ffy.foodforyou.login.model.User;
import ia.ffy.foodforyou.login.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User with email:" + email + "not found."));

        return UserDetailsImpl.build(user);
    }

    public Optional<User> findById(String id) {
        return this.userRepository.findById(id);
    }

}