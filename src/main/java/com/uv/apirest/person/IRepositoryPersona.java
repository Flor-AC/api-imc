package com.uv.apirest.person;

import com.uv.apirest.person.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryPersona extends JpaRepository<Persona,Long>{
    
}
