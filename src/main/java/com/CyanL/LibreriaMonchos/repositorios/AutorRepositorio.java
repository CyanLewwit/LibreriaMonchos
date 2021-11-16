
package com.CyanL.LibreriaMonchos.repositorios;

import com.CyanL.LibreriaMonchos.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String> {

    @Query("SELECT p FROM Autor p WHERE p.nombre= :nombre")
    public Autor buscarXnombre(@Param("nombre") String nombre);
    
}
