package com.example.pi_project.services;

import com.example.pi_project.entities.Avis;

import java.util.List;

public interface IAvisService {
    Avis addAvis(Avis Avis);
    List<Avis> getAllAvis();
    Avis getAvisById(long idAvis);
    void deleteAvis(long idAvis);
    Avis updateAvis(Avis Avis);
}
