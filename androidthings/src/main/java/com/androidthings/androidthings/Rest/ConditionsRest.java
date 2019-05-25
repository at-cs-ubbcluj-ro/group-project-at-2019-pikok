package com.androidthings.androidthings.Rest;

import com.androidthings.androidthings.Domain.Condition;
import com.androidthings.androidthings.Domain.ConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conditions")
public class ConditionsRest {

    @Autowired
    ConditionRepository repo;

    @GetMapping
    @Transactional(readOnly = true)
    ResponseEntity<List<Condition>> getConditions() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    @Transactional
    ResponseEntity<Condition> postCondition(@RequestBody Condition c) {
        return ResponseEntity.ok(repo.save(c));
    }

    @DeleteMapping("/{id}")
    @Transactional
    void deleteCondition(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @PutMapping()
    @Transactional
    ResponseEntity<Condition> updateCondition(@RequestBody Condition c) {
        Condition cnd = repo.findById(c.getId()).get();
        cnd.setHumidity(c.getHumidity());
        cnd.setName(c.getName());
        cnd.setTemperature(c.getTemperature());
        cnd.setX(c.getX());
        cnd.setY(c.getY());
        cnd.setSelected(c.isSelected());
        return ResponseEntity.ok(cnd);
    }

    @CrossOrigin
    @PutMapping("/{id}/select")
    @Transactional
    ResponseEntity<Condition> selectCondition(@PathVariable Long id) {
        Optional<Condition> selected = repo.findAll().stream().filter(Condition::isSelected).findFirst();
        if(selected.isPresent()) {
            Condition selectedEntity = selected.get();
            selectedEntity.setSelected(false);
            repo.save(selectedEntity);
        }
        Condition newSelected = repo.findById(id).get();
        newSelected.setSelected(true);
        return ResponseEntity.ok(repo.save(newSelected));
    }

    @GetMapping("/selected")
    @Transactional(readOnly = true)
    ResponseEntity<Condition> getSelected() {
        return ResponseEntity.ok(repo.findAll().stream().filter(Condition::isSelected).findFirst().orElse(null));
    }

}
