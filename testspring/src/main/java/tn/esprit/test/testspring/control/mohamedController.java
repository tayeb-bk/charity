package tn.esprit.test.testspring.control;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.test.testspring.Service.Iservice;
import tn.esprit.test.testspring.entity.Commenter;
import tn.esprit.test.testspring.entity.CompagneCollecte;
import tn.esprit.test.testspring.entity.Donation;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
public class mohamedController {
    Iservice iservice;
    @PostMapping("/ajouterDonation")

    public Donation ajouterDonnation( @RequestBody Donation d) {
        return iservice.ajouterDonation(d);
    }

    @GetMapping("/getDonationById{id}")
    public Donation getDonationById(@PathVariable("id")Long id) {
        return iservice.getDonationById(id);
    }

    @GetMapping("/getAllDonation")
    public List<Donation> getAllDonation() {
        return iservice.getAllDonation();
    }
    @PutMapping("/saveDonation")
    public Donation saveDonation(@RequestBody Donation d) {
        return iservice.saveDonation(d);
    }
    @DeleteMapping("/deleteDonation{id}")
    public void deleteDonation(@PathVariable("id")Long id) {
        iservice.deleteDonation(id);
    }
//    @PutMapping("/updateDonation{id}")
//    public Donation updateDonation(@PathVariable("id")Long id, @RequestBody Donation donationDetails) {
//        return iservice.updateDonation(id, donationDetails);
//    }






    @PostMapping("/ajouterCompagneCollecte")
    public CompagneCollecte ajouterCompagneCollecte(@RequestBody CompagneCollecte c) {
        return iservice.ajouterCompagneCollecte(c);
    }
    @GetMapping("/getAllCompagneCollecte")
    public List<CompagneCollecte> getAllCompagneCollecte() {
        return iservice.getAllCompagneCollecte();
    }

    @GetMapping("/getCompagneCollecteById{id}")
    public CompagneCollecte getCompagneCollecteById(@PathVariable Long id) {
        return iservice.getCompagneCollecteById(id);
    }
    @PutMapping("/saveCompagneCollecte")
    public CompagneCollecte saveCompagneCollecte(@RequestBody CompagneCollecte c) {
        return iservice.saveCompagneCollecte(c);
    }

    @DeleteMapping("/deleteCompagneCollecte{id}")
    public void deleteCompagneCollecte(@PathVariable Long id) {
        iservice.deleteCompagneCollecte(id);
    }

@PutMapping("/updateCompagneCollecte/{id}")
    public CompagneCollecte updateCompagneCollecte(@PathVariable Long id, @RequestBody CompagneCollecte collecte) {
        return iservice.updateCompagneCollecte(id, collecte);
    }




    @PostMapping("/ajouterCommenter")
    public Commenter ajouterCommenter(@RequestBody Commenter commenter) {
        return iservice.ajouterCommenter(commenter);
    }

    @GetMapping("/getAllCommenters")
    public List<Commenter> getAllCommenters() {
        return iservice.getAllCommenters();
    }

    @PutMapping("/saveCommenter")
    public Commenter saveCommenter(@RequestBody Commenter commenter) {
        return iservice.saveCommenter(commenter);
    }
    @DeleteMapping("/deleteCommenter/{id}")
    public void deleteCommenter(@PathVariable Long id) {
        iservice.deleteCommenter(id);
    }
//    @PutMapping("/updateCommenter/{id}")
//    public Commenter updateCommenter(@PathVariable Long id, @RequestBody Commenter commenter) {
//        return iserviceCompagneCollecte.updateCommenter(id, commenter);
//    }





}
