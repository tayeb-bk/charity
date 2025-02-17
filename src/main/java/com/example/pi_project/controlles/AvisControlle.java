package com.example.pi_project.controlles;

import com.example.pi_project.entities.Avis;
import com.example.pi_project.services.IAvisService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/Avis")
public class AvisControlle {
    @Autowired
    private IAvisService ivisService;

    @GetMapping("/getAviss")
    public List<Avis> retrieveAllAviss() {
        return ivisService.getAllAvis();
    }

    @PostMapping("/addAvis")
    public Avis addAvis(@RequestBody Avis f) {
        System.out.println("Received Avis: " + f.toString());
        return ivisService.addAvis(f);
    }


    @PutMapping("/updateAvis")
    public Avis updateAvis(@RequestBody Avis f) {
        return ivisService.updateAvis(f);
    }

    @GetMapping("/getAvisById/{idAvis}")
    public Avis retrieveAvis(@PathVariable long idAvis) {
        return ivisService.getAvisById(idAvis);
    }

    @DeleteMapping("/getDeleteAvisById/{idAvis}")
    public void removeAvis(@PathVariable long idAvis) {
        ivisService.deleteAvis(idAvis);
    }
}
