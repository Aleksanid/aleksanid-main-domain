package ua.aleksanid.maindomain.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.aleksanid.maindomain.models.AppUser;
import ua.aleksanid.maindomain.repositories.UserRepository;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = LogManager.getLogger(UserDetailsServiceImpl.class);

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        AppUser appUser = userRepository.findByLogin(login);
        if (appUser == null) {
            logger.error("Failed auth with username: {}", login);
            throw new UsernameNotFoundException(login);
        }
        return new AuthUser(appUser.getLogin(), appUser.getPassword(), Collections.emptyList(), appUser.getId());
    }
}
