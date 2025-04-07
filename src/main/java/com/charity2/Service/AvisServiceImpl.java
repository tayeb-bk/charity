package com.charity2.Service;
import com.charity2.Service.SentimentAnalysisService;
import com.charity2.Repository.AvisRepository;
import com.charity2.dto.AvisRequest;
import com.charity2.entities.Avis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@Service
public class AvisServiceImpl implements AvisService {
    @Autowired
    private ProfanityFilterService profanityFilterService;
    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;
    @Autowired
    private AvisRepository avisRepository;

    @Override
    public Avis createAvis(AvisRequest avisRequest) {
        Avis avis = new Avis();
        avis.setContenue(profanityFilterService.filterProfanity(avisRequest.getContent()));
        avis.setCategories(avisRequest.getCategories());
        avis.setTags(avisRequest.getTags());

        Map<String, Object> sentimentResults = sentimentAnalysisService.analyzeSentiment(avisRequest.getContent()); // Change to Map<String, Object>
        avis.setSentimentScore((Float) sentimentResults.get("averageScore")); // Cast to Float
        avis.setSentenceSentiments(sentimentResults.get("sentenceSentiments").toString()); // Convert to String

        return avisRepository.save(avis);
    }

    @Override
    public List<Avis> getAllAvis() {
        return avisRepository.findAll(); // Verify this line
    }

    @Override
    public Optional<Avis> getAvisById(Long id) {
        return Optional.empty();
    }

    @Override
    public Avis updateAvis(Avis avis) {
        return null;
    }

    @Override
    public void deleteAvis(Long id) {

    }

    @Override
    public List<Avis> getAvisByCategory(String category) {
        return avisRepository.findByCategoriesContaining(category);
    }

    @Override
    public List<Avis> getAvisByTag(String tag) {
        return avisRepository.findByTagsContaining(tag);
    }

    // Other CRUD operations...
}