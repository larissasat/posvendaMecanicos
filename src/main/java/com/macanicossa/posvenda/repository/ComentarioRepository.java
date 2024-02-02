
package com.macanicossa.posvenda.repository;

import com.mecanicossa.posvenda.model.Comentario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    
    List<Comentario> findByServicoId(Integer idServico);
    
}
