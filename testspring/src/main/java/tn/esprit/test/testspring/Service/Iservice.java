package tn.esprit.test.testspring.Service;

import tn.esprit.test.testspring.entity.Commenter;
import tn.esprit.test.testspring.entity.CompagneCollecte;
import tn.esprit.test.testspring.entity.Donation;

import java.util.List;

public interface Iservice {
    Donation ajouterDonation(Donation d) ;
    Donation getDonationById(Long id);
    List<Donation> getAllDonation();
    Donation saveDonation(Donation d);
    void deleteDonation(Long id);
//    Donation updateDonation(Long id, Donation donationDetails);


    CompagneCollecte ajouterCompagneCollecte(CompagneCollecte c) ;
    List<CompagneCollecte > getAllCompagneCollecte ();
    CompagneCollecte getCompagneCollecteById(Long id);
    CompagneCollecte saveCompagneCollecte(CompagneCollecte c);
    void deleteCompagneCollecte(Long id);
    CompagneCollecte updateCompagneCollecte(Long id, CompagneCollecte collecte);












    Commenter ajouterCommenter(Commenter commenter);
    List<Commenter> getAllCommenters();

    Commenter saveCommenter(Commenter commenter);
    void deleteCommenter(Long id);

//    Commenter updateCommenter(Long id ,Commenter commenter);




}
