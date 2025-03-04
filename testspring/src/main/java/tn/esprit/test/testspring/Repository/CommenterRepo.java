package tn.esprit.test.testspring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.test.testspring.entity.Commenter;

public interface CommenterRepo extends JpaRepository<Commenter, Long> {
}
