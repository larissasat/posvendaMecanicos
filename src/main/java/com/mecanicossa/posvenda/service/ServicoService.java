
package com.mecanicossa.posvenda.service;

import com.macanicossa.posvenda.repository.ServicoRepository;
import com.mecanicossa.posvenda.model.Servico;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


public class ServicoService {
    
    @Autowired
    ServicoRepository servicoRepository;
    
    
    public List<Servico> listaTodos(){
        return servicoRepository.findAll();
    }
    
    public Servico buscarPorId (Integer id){
        return servicoRepository.findById(id).orElseThrow();
    }
    
    public void excluir (Integer id){
        Servico servicoEncontrado = buscarPorId(id);
        servicoRepository.deleteById(servicoEncontrado.getId());
    }
    
    public Servico criar(Servico serv) {
        serv.setId(0);
        servicoRepository.save(serv);
        return serv;
    }
    
    
    public Servico atualizar (Integer id, Servico servicoEnviado){
        Servico servicoEncontrado = buscarPorId(id);
        servicoEncontrado.setNomeServico(servicoEnviado.getNomeServico());
        servicoEncontrado.setDetalhamento(servicoEnviado.getDetalhamento());
        servicoRepository.save(servicoEncontrado);
        return servicoEncontrado;
    }
}
