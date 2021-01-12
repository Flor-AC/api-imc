package com.uv.apirest.user;

import com.uv.apirest.user.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryUsuario extends JpaRepository<Usuario, Long>{
    
    List<Usuario> findByUsername(String username);
    
}
