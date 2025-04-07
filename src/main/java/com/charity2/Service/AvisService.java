package com.charity2.Service;

import com.charity2.dto.AvisRequest;
import com.charity2.entities.Avis;
import java.util.List;
import java.util.Optional;

public interface AvisService {
    Avis createAvis(AvisRequest avisRequest);
    List<Avis> getAllAvis();
    Optional<Avis> getAvisById(Long id);
    Avis updateAvis(Avis avis);
    void deleteAvis(Long id);
    List<Avis> getAvisByCategory(String category);
    List<Avis> getAvisByTag(String tag);
}