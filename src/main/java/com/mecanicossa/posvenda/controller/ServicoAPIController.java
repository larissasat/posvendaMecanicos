
package com.mecanicossa.posvenda.controller;

import com.mecanicossa.posvenda.model.Servico;
import com.mecanicossa.posvenda.service.ServicoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/servico")
public class ServicoAPIController {
    
    @Autowired
    ServicoService servicoService;
    
    
    @GetMapping ("/listar")
    public ResponseEntity<List> listar(){
        List<Servico> listagem = servicoService.listarTodos();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
        
    }
    
    @PostMapping ("/adicionar")
    public ResponseEntity<Servico> addServico(@RequestBody Servico serv){
        var novoServico = servicoService.criar(serv);
        return new ResponseEntity<>(novoServico, HttpStatus.CREATED);
    }
    
    @GetMapping ("/buscar/{id}")
    public ResponseEntity<Servico> pesquisar(@PathVariable Integer id){
        Servico servicoEncontrado = servicoService.buscarPorId(id);
        return new ResponseEntity<>(servicoEncontrado, HttpStatus.OK);
    }
    
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Servico> excluir(@PathVariable Integer id){
        servicoService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
     
     @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizarServico(@PathVariable Integer id, @RequestBody Servico serv) {
        serv.setId(id);
        
        Optional<Servico> servicoAtualizadoOptional = servicoService.atualizar(serv);

        return servicoAtualizadoOptional
                .map(servico -> new ResponseEntity<>(servico, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
     
}
