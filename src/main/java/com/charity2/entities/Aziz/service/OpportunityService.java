package com.charity2.entities.Aziz.service;

import com.charity2.entities.Aziz.entity.Opportunity;

import java.util.List;

public interface OpportunityService {
    Opportunity createOpportunity(Opportunity opportunity);
    List<Opportunity> getAllOpportunities();
    Opportunity getOpportunityById(Long id);

    void deleteOpportunity(Long id);
}

