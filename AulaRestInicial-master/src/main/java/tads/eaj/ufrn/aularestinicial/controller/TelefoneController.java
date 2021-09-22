package tads.eaj.ufrn.aularestinicial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tads.eaj.ufrn.aularestinicial.model.Mensagem;
import tads.eaj.ufrn.aularestinicial.model.Telefone;
import tads.eaj.ufrn.aularestinicial.service.TelefoneService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-Total-Count")
@RequestMapping("/telefone")
public class TelefoneController {

    private TelefoneService service;

    @Autowired
    public void setService(TelefoneService service) {
        this.service = service;
    }

    @GetMapping
    public List<Telefone> getAllPessoa(){
        return service.getAllTelefone();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Telefone> getTelefone(@PathVariable Long id) {
        Optional<Telefone> t = service.getTelefoneById(id);
        if (t.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(t.get());
        }
    }

    @PostMapping
    public Telefone Insert(@RequestBody Telefone telefone) {
    return service.insert(telefone);
}

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Telefone t){
        return service.getTelefoneById(id)
                .map( record -> {
                    if (record.getId().equals(t.getId())){
                        service.update(t);
                        return ResponseEntity.ok(t);
                    }else{
                        return ResponseEntity.notFound().build();
                    }
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return service.getTelefoneById(id)
                .map( record -> {
                    Mensagem m = new Mensagem();
                    m.setMensagem("Deletado com sucesso");
                    service.delete(record.getId());
                    return ResponseEntity.ok(m);
                }).orElse(ResponseEntity.notFound().build());
    }
}