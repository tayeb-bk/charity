package com.charity2.Service;


import com.charity2.entities.Sponsoring;

import java.util.List;
import java.util.Optional;

public interface SponsoringService {
    Sponsoring createSponsoring(Sponsoring sponsoring);
    List<Sponsoring> getAllSponsorings();
    Optional<Sponsoring> getSponsoringById(Long id);
    Sponsoring updateSponsoring(Sponsoring sponsoring);
    void deleteSponsoring(Long id);
}
