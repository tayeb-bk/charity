package com.charity2.Service;
import com.charity2.Repository.AvisRepository;
import com.charity2.entities.Avis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AvisServiceImpl implements AvisService {
    private final AvisRepository avisRepository;
    @Autowired
    public AvisServiceImpl(AvisRepository avisRepository) {
        this.avisRepository = avisRepository;
    }
    @Override
    public Avis createAvis(Avis avis) {
        return avisRepository.save(avis);
    }
    @Override
    public List<Avis> getAllAvis() {
        return avisRepository.findAll();
    }
    @Override
    public Optional<Avis> getAvisById(Long id) {
        return avisRepository.findById(id);
    }
    @Override
    public Avis updateAvis(Avis avis) {
        return avisRepository.save(avis);
    }
    @Override
    public void deleteAvis(Long id) {
        avisRepository.deleteById(id);
}


}
