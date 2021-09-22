package tads.eaj.ufrn.aularestinicial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tads.eaj.ufrn.aularestinicial.model.Telefone;
import tads.eaj.ufrn.aularestinicial.repository.TelefoneRepo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TelefoneService {

    private TelefoneRepo repositoryT;

    @Autowired
    public void setRepository(TelefoneRepo repository) {
        this.repositoryT = repository;
    }

    public Optional<Telefone> getTelefoneById(Long id){
        return repositoryT.findByDeletedIsNullAndId(id);
    }

    public List<Telefone> getAllTelefone(){
        return repositoryT.findAllByDeletedIsNull();
    }

    public Telefone insert(Telefone n){
        return repositoryT.save(n);
    }

    public Telefone update(Telefone n){
        return  repositoryT.saveAndFlush(n);
    }
    public Telefone delete(Long id){
        Telefone t = repositoryT.getById(id);
        t.setDeleted(new Date());
        return repositoryT.saveAndFlush(t);
    }
}