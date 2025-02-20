package com.charity2.Controller;

import com.charity2.Service.DonationService;
import com.charity2.entities.Donation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/donnation")

public class DonationController {
    @Autowired
    DonationService donationService;

    @PostMapping("/post-Donnation")
    public Donation ajouterDonnation(@RequestBody Donation donation) {
        return donationService.ajouterDonnation(donation);
    }
}
