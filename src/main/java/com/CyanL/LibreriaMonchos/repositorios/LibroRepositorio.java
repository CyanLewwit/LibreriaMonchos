
package com.CyanL.LibreriaMonchos.repositorios;

import com.CyanL.LibreriaMonchos.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String>{
    
    @Query("SELECT t FROM Libro t WHERE t.isbn=:isbn")
    public Libro buscarXisbn(@Param("isbn") long isbn);

    @Query("SELECT t FROM Libro t WHERE t.titulo=:titulo")
    public Libro buscarXtitulo(@Param("titulo") String titulo);
    
}
