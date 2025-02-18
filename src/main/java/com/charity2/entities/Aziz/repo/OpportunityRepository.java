package com.charity2.entities.Aziz.repo;

import com.charity2.entities.Aziz.entity.Categorie;
import com.charity2.entities.Aziz.entity.Opportunity;
import com.charity2.entities.Aziz.entity.Type;
import com.charity2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {


}
