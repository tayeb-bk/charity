package com.example.pi_project.services;

import com.example.pi_project.entities.Avis;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AvisImpl implements IAvisService{
    @Override
    public Avis addAvis(Avis Avis) {
        return null;
    }

    @Override
    public List<Avis> getAllAvis() {
        return List.of();
    }

    @Override
    public Avis getAvisById(long idAvis) {
        return null;
    }

    @Override
    public void deleteAvis(long idAvis) {

    }

    @Override
    public Avis updateAvis(Avis Avis) {
        return null;
    }
}
