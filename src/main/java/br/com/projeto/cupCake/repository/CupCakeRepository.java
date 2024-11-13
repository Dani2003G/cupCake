package br.com.projeto.cupCake.repository;

import br.com.projeto.cupCake.model.CupCake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupCakeRepository extends JpaRepository<CupCake, Long> {
}
