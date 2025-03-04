package tn.esprit.test.testspring.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.test.testspring.Repository.CommenterRepo;
import tn.esprit.test.testspring.Repository.CompagneCollecteRepo;
import tn.esprit.test.testspring.Repository.DonationRepo;
import tn.esprit.test.testspring.entity.Commenter;
import tn.esprit.test.testspring.entity.CompagneCollecte;
import tn.esprit.test.testspring.entity.Donation;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class service implements Iservice{

DonationRepo donationRepo;
CompagneCollecteRepo compagneCollecteRepo;
CommenterRepo commenterRepo;
    @Override
    public Donation ajouterDonation(Donation d) {
        return donationRepo.save(d);
    }
    @Override
    public Donation getDonationById(Long id) {
        Optional<Donation> optionalDonation = donationRepo.findById(id);
        return optionalDonation.orElse(null);
    }

    @Override
    public List<Donation> getAllDonation() {
        return donationRepo.findAll();
    }

    @Override
    public Donation saveDonation(Donation d) {
        return donationRepo.save(d);
    }

    @Override
    public void deleteDonation(Long id) {
        donationRepo.deleteById(id);
    }

//    @Override
//    public Donation updateDonation(Long id, Donation donationDetails) {
//        Optional<Donation> optionalDonation = donationRepo.findById(id);
//        if (optionalDonation.isPresent()) {
//            Donation donation = optionalDonation.get();
//            if (donationDetails.getNom() != null) {
//                donation.setNom(donationDetails.getAdresse());
//            }
//            if (donationDetails.getPrenom() != null) {
//                donation.setPrenom(donationDetails.getAdresse());
//            }
//
//            if (donationDetails.getAdresse() != null) {
//                donation.setAdresse(donationDetails.getAdresse());
//            }
//            if (donationDetails.getNumero() != null) {
//                donation.setNumero(donationDetails.getNumero());
//            }
//            if (donationDetails.getDescription() != null) {
//                donation.setDescription(donationDetails.getDescription());
//            }
//            if (donationDetails.getMoyenPaiement() != null) {
//                donation.setMoyenPaiement(donationDetails.getMoyenPaiement());
//            }
//            if (donationDetails.getTypeDonation() != null) {
//                donation.setTypeDonation(donationDetails.getTypeDonation());
//            }
//            if (donationDetails.getEtat() != null) {
//                donation.setEtat(donationDetails.getEtat());
//            }
//
//            return donationRepo.save(donation);
//        } else {
//            return null;
//        }
//    }






    @Override
    public CompagneCollecte ajouterCompagneCollecte(CompagneCollecte collecte) {
        return compagneCollecteRepo.save(collecte);
    }

    @Override
    public List<CompagneCollecte> getAllCompagneCollecte() {
        return compagneCollecteRepo.findAll();
    }

    @Override
    public CompagneCollecte getCompagneCollecteById(Long id) {
        Optional<CompagneCollecte> optionalCompagneCollecte = compagneCollecteRepo.findById(id);
        return optionalCompagneCollecte.orElse(null) ;
    }

    @Override
    public CompagneCollecte saveCompagneCollecte(CompagneCollecte c) {
        return compagneCollecteRepo.save(c);
    }

    @Override
    public void deleteCompagneCollecte(Long id) {
        compagneCollecteRepo.deleteById(id);

    }

    @Override
    public CompagneCollecte updateCompagneCollecte(Long id, CompagneCollecte collecte) {
        Optional<CompagneCollecte> optionalCampagne = compagneCollecteRepo.findById(id);
        if (optionalCampagne.isPresent()) {
            CompagneCollecte campagne = optionalCampagne.get();

            if (collecte.getTitre() != null) {
                campagne.setTitre(collecte.getTitre());
            }
            if (collecte.getDescription() != null) {
                campagne.setDescription(collecte.getDescription());
            }
            if (collecte.getObjectif() != 0.0f) {
                campagne.setObjectif(collecte.getObjectif());
            }
            if (collecte.getMontantCollecte() != 0.0f) {
                campagne.setMontantCollecte(collecte.getMontantCollecte());
            }
            if (collecte.getDateDebut() != null) {
                campagne.setDateDebut(collecte.getDateDebut());
            }
            if (collecte.getDateFin() != null) {
                campagne.setDateFin(collecte.getDateFin());
            }
            if (collecte.getMoyenPaiement() != null) {
                campagne.setMoyenPaiement(collecte.getMoyenPaiement());
            }

            return compagneCollecteRepo.save(campagne);
        } else {
            return null;
        }
    }

    @Override
    public Commenter ajouterCommenter(Commenter commenter) {
        return commenterRepo.save(commenter);
    }

    @Override
    public List<Commenter> getAllCommenters() {
        return commenterRepo.findAll();
    }

    @Override
    public Commenter saveCommenter(Commenter commenter) {
        return commenterRepo.save(commenter);
    }

    @Override
    public void deleteCommenter(Long id) {
        commenterRepo.deleteById(id);
    }






}
