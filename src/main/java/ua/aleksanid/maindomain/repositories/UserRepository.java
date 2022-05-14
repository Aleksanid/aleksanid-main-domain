package ua.aleksanid.maindomain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.aleksanid.maindomain.models.AppUser;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<AppUser, Long> {
    List<AppUser> findAll();

    AppUser findByLogin(String login);

}