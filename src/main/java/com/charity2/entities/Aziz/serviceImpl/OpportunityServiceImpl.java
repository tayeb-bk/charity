package com.charity2.entities.Aziz.serviceImpl;

import com.charity2.entities.Aziz.entity.Opportunity;
import com.charity2.entities.Aziz.repo.OpportunityRepository;
import com.charity2.entities.Aziz.service.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OpportunityServiceImpl implements OpportunityService {

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Override
    public Opportunity createOpportunity(Opportunity opportunity) {
        return opportunityRepository.save(opportunity);
    }

    @Override
    public List<Opportunity> getAllOpportunities() {
        return opportunityRepository.findAll();
    }

    @Override
    public Opportunity getOpportunityById(Long id) {
        return opportunityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Opportunity not found with id: " + id));
    }



    @Override
    public void deleteOpportunity(Long id) {
        opportunityRepository.deleteById(id);
    }
}
