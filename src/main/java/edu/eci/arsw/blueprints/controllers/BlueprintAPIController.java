/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hcadavid
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/blueprint")
public class BlueprintAPIController {
    
    @Autowired
    BlueprintsServices bbps;

    @GetMapping
    public ResponseEntity<?> getBlueprintsHandler() {
        try {
            return new ResponseEntity<>(bbps.getAllBlueprints(), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintNotFoundException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{author}")
    public ResponseEntity<?> getBlueprintByAuthorHandler(@PathVariable("author") String author) {
        try {
            return new ResponseEntity<>(bbps.getBlueprintsByAuthor(author), HttpStatus.ACCEPTED);
        } catch (Exception  ex) {
            Logger.getLogger(BlueprintNotFoundException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{author}/{bpname}")
    public ResponseEntity<?> getBlueprintHandler(@PathVariable("author") String author, @PathVariable("bpname") String bpname) {
        try {
            return new ResponseEntity<>(bbps.getBlueprint(author, bpname), HttpStatus.ACCEPTED);
        } catch (Exception  ex) {
            Logger.getLogger(BlueprintNotFoundException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> postOrderHandler(@RequestBody Blueprint blueprint) {
        try {
            bbps.addNewBlueprint(blueprint);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception  ex) {
            Logger.getLogger(BlueprintNotFoundException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
    /**
    @PutMapping("/{author}/{bpname}")
    public ResponseEntity<?> putBlueprintHandler(@PathVariable("author") String author, @PathVariable("bpname") String bpname, @RequestBody Blueprint blueprint) {
        try {
            bbps.updateBlueprint(author, bpname, blueprint);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception  ex) {
            Logger.getLogger(BlueprintNotFoundException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }    
**/
    }
    

