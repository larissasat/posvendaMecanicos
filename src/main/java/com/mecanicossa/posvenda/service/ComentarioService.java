
package com.mecanicossa.posvenda.service;

import com.macanicossa.posvenda.repository.ComentarioRepository;
import com.mecanicossa.posvenda.model.Comentario;
import com.mecanicossa.posvenda.model.Servico;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


public class ComentarioService {
    
    @Autowired
    ComentarioRepository comentarioRepository;
    
    
      public Comentario criar(Comentario comen) {
        comen.setId(0);
        comentarioRepository.save(comen);
        return comen;
    }
    
    public List<Comentario> listaTodos(Integer idServico){
        return comentarioRepository.findByServicoId(idServico);
    }
    
    
    public Comentario buscarPorId (Integer id){
        return comentarioRepository.findById(id).orElseThrow();
    }
    
    
    public void excluir (Integer id){
        Comentario comentarioEncontrado = buscarPorId(id);
        comentarioRepository.deleteById(comentarioEncontrado.getId());
    }
    
    public Comentario atualizar (Integer id, Comentario comentarioEnviado){
        Comentario comentarioEncontrado = buscarPorId(id);
        comentarioEncontrado.setNomeCliente(comentarioEnviado.getNomeCliente());
        comentarioEncontrado.setCarro(comentarioEnviado.getCarro());
        comentarioEncontrado.setNota(comentarioEnviado.getNota());
        comentarioEncontrado.setTextoComentario(comentarioEnviado.getTextoComentario());
        comentarioRepository.save(comentarioEncontrado);
        return comentarioEncontrado;
    }
    
}
