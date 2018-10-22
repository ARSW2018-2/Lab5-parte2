/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Point[] pts1=new Point[]{new Point(10, 20),new Point(35, 45)};
        Point[] pts2=new Point[]{new Point(50, 60),new Point(75, 85)};
        Point[] pts3=new Point[]{new Point(100, 120),new Point(165, 185)};
        
        
        Blueprint bp=new Blueprint("_authorname_", "_bpname_ ",pts);
        Blueprint bp1=new Blueprint("Sergio", "Aponte ",pts);
        Blueprint bp2=new Blueprint("Oscar", "Beltran ",pts);
        Blueprint bp3=new Blueprint("Nicolas", "Gomez",pts);
        
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        blueprints.put(new Tuple<>(bp1.getAuthor(),bp1.getName()), bp1);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);
        
    }    
    
    
  
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        List<Blueprint> valorResp=new ArrayList<Blueprint>(blueprints.values());
        Set<Blueprint> respuesta= new HashSet<Blueprint>();
        
        for(int i=0;i<valorResp.size();i++){
            Blueprint p= valorResp.get(i);
            if(p.getAuthor().equalsIgnoreCase(author)){
                System.out.println("LOGRADO");
            
                respuesta.add(p);
            }
        }        
        
        System.out.println("que tamaño devuelve"+respuesta.size());
        return respuesta;
    }

    @Override
    public Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException {
        List<Blueprint> valorResp=new ArrayList<Blueprint>(blueprints.values());
        Set<Blueprint> respuesta= new HashSet<Blueprint>(); 
        for(int i=0;i<valorResp.size();i++){
            Blueprint p= valorResp.get(i);
            respuesta.add(p);
        }
        return respuesta;
    }

    @Override
    public void eraserMemory() throws BlueprintNotFoundException {
        blueprints.clear();
    }
}
