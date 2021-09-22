package tads.eaj.ufrn.aularestinicial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tads.eaj.ufrn.aularestinicial.model.Pessoa;
import tads.eaj.ufrn.aularestinicial.repository.PessoaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

	private PessoaRepository repository;

	@Autowired
	public void setRepository(PessoaRepository repository) {
		this.repository = repository;
	}

	public Optional<Pessoa> getPessoaById(Long id){
		return repository.findByDeletedIsNullAndId(id);
	}

	public List<Pessoa> getAllPessoa(){
		return repository.findAllByDeletedIsNull();
	}

	public Pessoa insert(Pessoa p){
		return repository.save(p);
	}

	public Pessoa update(Pessoa p){
		return  repository.saveAndFlush(p);
	}
	public Pessoa delete(Long id){
		Pessoa p = repository.getById(id);
		p.setDeleted(new Date());
		return repository.saveAndFlush(p);
	}
}
