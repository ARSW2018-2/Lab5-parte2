/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author 2098325
 */
public interface FilterBlue {
  
    
    public Blueprint filter(Blueprint blueprint);

    public default Set<Blueprint> FilterBlue(Set<Blueprint> blueprints) {
        Set<Blueprint> filterBlueprints = new HashSet<Blueprint>();;
        blueprints.forEach((blueprint) -> {
            filterBlueprints.add(filter(blueprint));
        });
        return filterBlueprints;
    }

    
    
    
}
