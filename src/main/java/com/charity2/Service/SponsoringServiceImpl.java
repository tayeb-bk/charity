package com.charity2.Service;


import com.charity2.Repository.SponsoringRepository;

import com.charity2.entities.Sponsoring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SponsoringServiceImpl implements SponsoringService{


        private final SponsoringRepository sponsoringRepository;

        @Autowired
        public SponsoringServiceImpl(SponsoringRepository sponsoringRepository) {
            this.sponsoringRepository = sponsoringRepository;
        }

        @Override
        public Sponsoring createSponsoring(Sponsoring sponsoring) {
            return sponsoringRepository.save(sponsoring);
        }

        @Override
        public List<Sponsoring> getAllSponsorings() {
            return sponsoringRepository.findAll();
        }

        @Override
        public Optional<Sponsoring> getSponsoringById(Long id) {
            return sponsoringRepository.findById(id);
        }

        @Override
        public Sponsoring updateSponsoring(Sponsoring sponsoring) {
            return sponsoringRepository.save(sponsoring);
        }

        @Override
        public void deleteSponsoring(Long id) {
            sponsoringRepository.deleteById(id);
        }
}
