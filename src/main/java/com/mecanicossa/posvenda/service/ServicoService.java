
package com.mecanicossa.posvenda.service;

import com.mecanicossa.posvenda.model.Servico;
import com.mecanicossa.posvenda.repository.ServicoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {
    
    @Autowired
    ServicoRepository servicoRepository;
    
    
    public List<Servico> listarTodos(){
        return servicoRepository.findAll();
    }
    
    
    public Servico buscarPorId(Integer id){
        return servicoRepository.findById(id).orElseThrow();
    }
    
    public void excluir(Integer id){
        Servico servicoEncontrado = buscarPorId(id);
        servicoRepository.deleteById(servicoEncontrado.getId());
    }
    
    public Servico criar(Servico serv){
        serv.setId(0);
        servicoRepository.save(serv);
        return serv;
        
    }
    
    
    public Optional<Servico> atualizar(Servico servico) {
        Optional<Servico> servicoExistente = servicoRepository.findById(servico.getId());

        if (servicoExistente.isPresent()) {
            Servico servicoAtualizado = servicoExistente.get();
            servicoAtualizado.setNomeServico(servico.getNomeServico());
            servicoAtualizado.setDetalhamento(servico.getDetalhamento());
           
            servicoRepository.save(servicoAtualizado);

            return Optional.of(servicoAtualizado);
        } else {
            return Optional.empty(); 
        }
    }
    
}
