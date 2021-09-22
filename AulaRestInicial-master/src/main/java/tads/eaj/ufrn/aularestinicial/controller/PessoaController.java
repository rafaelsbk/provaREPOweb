package tads.eaj.ufrn.aularestinicial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tads.eaj.ufrn.aularestinicial.model.Mensagem;
import tads.eaj.ufrn.aularestinicial.model.Pessoa;

import tads.eaj.ufrn.aularestinicial.service.PessoaService;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-Total-Count")
@RequestMapping("/pessoa")
public class PessoaController {

	private PessoaService service;

	@Autowired
	public void setService(PessoaService service) {
		this.service = service;
	}

	@GetMapping
	public List<Pessoa> getAllPessoa(){
		return service.getAllPessoa();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pessoa> getPessoa(@PathVariable Long id) {
		Optional<Pessoa> p = service.getPessoaById(id);
		if (p.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(p.get());
		}
	}

	@PostMapping
	public Pessoa newPessoa(@RequestBody Pessoa pessoa) {
		return service.insert(pessoa);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Pessoa p){
		return service.getPessoaById(id)
				.map( record -> {
					if (record.getId().equals(p.getId())){
						service.update(p);
						return ResponseEntity.ok(p);
					}else{
						return ResponseEntity.notFound().build();
					}
				}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		return service.getPessoaById(id)
				.map( record -> {
					Mensagem m = new Mensagem();
					m.setMensagem("Deletado com sucesso");
					service.delete(record.getId());
					return ResponseEntity.ok(m);
				}).orElse(ResponseEntity.notFound().build());
	}
}
