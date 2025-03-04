package tn.esprit.test.testspring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.test.testspring.entity.CompagneCollecte;

public interface CompagneCollecteRepo extends JpaRepository<CompagneCollecte, Long> {
}
