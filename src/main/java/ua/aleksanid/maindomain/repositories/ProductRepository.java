package ua.aleksanid.maindomain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.aleksanid.maindomain.models.Product;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
}