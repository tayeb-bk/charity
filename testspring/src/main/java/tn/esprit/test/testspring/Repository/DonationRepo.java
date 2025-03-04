package tn.esprit.test.testspring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.test.testspring.entity.Donation;

public interface DonationRepo extends JpaRepository<Donation, Long > {

}
