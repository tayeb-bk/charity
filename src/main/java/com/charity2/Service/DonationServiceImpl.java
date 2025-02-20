package com.charity2.Service;

import com.charity2.Repository.DonationRepository;
import com.charity2.entities.Donation;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;

    @Autowired
    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public Donation ajouterDonnation(Donation donation) {
        return donationRepository.save(donation);
    }
}
