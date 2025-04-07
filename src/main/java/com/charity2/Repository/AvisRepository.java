package com.charity2.Repository;

import com.charity2.entities.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AvisRepository extends JpaRepository<Avis, Long> {
    List<Avis> findByCategoriesContaining(String category);
    List<Avis> findByTagsContaining(String tag);
}
