package com.example.pi_project.services;

import com.example.pi_project.entities.Avis;
import com.example.pi_project.entities.Avis;
import com.example.pi_project.repositories.AvisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AvisImpl implements IAvisService{

    @Autowired
  private   AvisRepository AvisRepository;

    @Override
    public List<Avis> getAllAvis() {
        return (List<Avis>) AvisRepository.findAll();
    }

    @Override
    public Avis getAvisById(long idAvis) {
        return AvisRepository.findById(idAvis).orElse(null);
    }

    @Override
    public Avis addAvis(Avis f) {
        return AvisRepository.save(f);
    }

    @Override
    public Avis updateAvis(Avis f) {
        return AvisRepository.save(f);
    }


    @Override
    public void deleteAvis(long idAvis) {
        AvisRepository.deleteById(idAvis);

    }
}
