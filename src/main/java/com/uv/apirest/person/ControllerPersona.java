package com.uv.apirest.person;

import com.uv.apirest.person.Persona;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.uv.apirest.person.IRepositoryPersona;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "*")
public class ControllerPersona {

    @Autowired
    private IRepositoryPersona repositoryPersona;

    @RequestMapping(value = "/persona", method = RequestMethod.GET)
    @ApiOperation(value = "Find all persona", notes = "Return all persona")
    public List<Persona> getAllPersona() {
        return repositoryPersona.findAll();
    }

    @RequestMapping(value = "/persona/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Find a persona", notes = "Return a persona by ID")
    public Persona getPersona(@PathVariable Long id) {
        Optional<Persona> persona = repositoryPersona.findById(id);
        if(persona.isPresent()){
            return persona.get();
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/persona", method = RequestMethod.POST)
    @ApiOperation(value = "Create a persona", notes = "Create a new persona")
    public Boolean saveDepartment(@RequestBody Persona persona) {
        try {
            repositoryPersona.save(persona);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
