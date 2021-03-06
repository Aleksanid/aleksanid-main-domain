package ua.aleksanid.maindomain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.aleksanid.maindomain.models.AppUser;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByLogin(String login);
}