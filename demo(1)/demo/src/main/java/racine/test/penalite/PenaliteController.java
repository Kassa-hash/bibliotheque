package racine.test.penalite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PenaliteController {

    @Autowired
    private PenaliteService penaliteService;

    @GetMapping
    public List<Penalite> getAllPenalites() {
        return penaliteService.getAllPenalites();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Penalite> getPenaliteById(@PathVariable Long id) {
//        return penaliteService.getPenaliteById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @PostMapping
    public Penalite createPenalite(@RequestBody Penalite penalite) {
        return penaliteService.createPenalite(penalite);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Penalite> updatePenalite(@PathVariable Long id, @RequestBody Penalite penaliteDetails) {
//        try {
//            return ResponseEntity.ok(penaliteService.updatePenalite(id, penaliteDetails));
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePenalite(@PathVariable Long id) {
//        penaliteService.deletePenalite(id);
//        return ResponseEntity.noContent().build();
//    }
}

