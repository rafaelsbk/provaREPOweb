package tads.eaj.ufrn.aularestinicial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tads.eaj.ufrn.aularestinicial.model.Telefone;
import tads.eaj.ufrn.aularestinicial.model.Pessoa;

import java.util.List;
import java.util.Optional;

public interface TelefoneRepo extends JpaRepository<Telefone, Long> {
    List<Telefone> findAllByDeletedIsNull();
    Optional<Telefone> findByDeletedIsNullAndId(Long id);
}