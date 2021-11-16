
package com.CyanL.LibreriaMonchos.repositorios;

import com.CyanL.LibreriaMonchos.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {
    
    @Query("SELECT p FROM Autor p WHERE p.nombre= :nombre")
    public Editorial buscarXname(@Param("nombre") String nombre);
    
    
    
}
