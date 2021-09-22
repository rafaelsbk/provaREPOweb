package tads.eaj.ufrn.aularestinicial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tads.eaj.ufrn.aularestinicial.model.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	List<Pessoa> findAllByDeletedIsNull();
	Optional<Pessoa> findByDeletedIsNullAndId(Long id);
}