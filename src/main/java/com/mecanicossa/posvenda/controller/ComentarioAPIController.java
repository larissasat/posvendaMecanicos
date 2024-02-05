
package com.mecanicossa.posvenda.controller;

import com.mecanicossa.posvenda.model.Comentario;
import com.mecanicossa.posvenda.service.ComentarioService;
import java.util.List;
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
@RequestMapping ("/comentario")
public class ComentarioAPIController {
    
    @Autowired
    ComentarioService comentarioService;
    
    @PostMapping ("/adicionar")
    public ResponseEntity<Comentario> addComentario(@RequestBody Comentario com){
        var novoComentario = comentarioService.criar(com);
        return new ResponseEntity<>(novoComentario, HttpStatus.CREATED);
    }
    
    @GetMapping ("/buscar/{idServico}")
    public ResponseEntity<List> pesquisar(@PathVariable Integer idServico){
        List<Comentario> comentarioEncontrado = comentarioService.listar(idServico);
        return new ResponseEntity<>(comentarioEncontrado, HttpStatus.OK);
    }
    
    @DeleteMapping ("/escluir/{id}")
    public ResponseEntity<Comentario> excluir(@PathVariable Integer id){
        comentarioService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Comentario> atualizarComentario(@PathVariable Integer id, @RequestBody Comentario com){
        var comentarioEditado = comentarioService.atualizar(id, com);
        return new ResponseEntity<>(comentarioEditado, HttpStatus.OK);
    }
}
