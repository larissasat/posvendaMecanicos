
package com.macanicossa.posvenda.repository;

import com.mecanicossa.posvenda.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {
    
    
    
    
}
