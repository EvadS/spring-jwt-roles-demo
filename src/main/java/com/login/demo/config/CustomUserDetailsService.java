package com.login.demo.config;


import com.login.demo.repository.UserEntityRepository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import  com.login.demo.entity.User;
import java.util.Optional;


@Component
public class CustomUserDetailsService implements UserDetailsService {


    private final UserEntityRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    public CustomUserDetailsService(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }

    /// достаю из базы данных моего юзера по логину, конвертирую его в CustomUser и возвращаю
    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> dbUser = userRepository.findByEmail(username);
        logger.info("Fetched user : " + dbUser + " by " + username);
        return dbUser.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Couldn't find a matching user email in the database for " + username));
    }



    public UserDetails loadUserById(Long id) {
        Optional<User> dbUser = userRepository.findById(id);
        logger.info("Fetched user : " + dbUser + " by " + id);
        return dbUser.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Couldn't find a matching user id in the database for " + id));
    }
}
