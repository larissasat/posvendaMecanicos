
package com.mecanicossa.posvenda.controller;


import com.mecanicossa.posvenda.model.Servico;
import com.mecanicossa.posvenda.model.Comentario;
import com.mecanicossa.posvenda.service.ComentarioService;
import com.mecanicossa.posvenda.service.ServicoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;



@Controller
public class PosvendaController {
    
    private List<Servico> listaServico = new ArrayList<>();
    private List<Comentario> listaComentario = new ArrayList<>();
    
    @Autowired
    ServicoService servicoService;
    
    @Autowired
    ComentarioService comentarioService;
    
    
    @GetMapping ("/")
    public String inicio(){
        return "index";
    }
    
    @GetMapping ("/inicio")
    public String inicio2(){
        return "index";
    }
    
    @GetMapping ("/cadastrar-servico")
    public String cadastramento(Model model){
       model.addAttribute("servico", new Servico());
       return "cadastroServico";
    }
    
    @PostMapping ("/gravar-servico")
    public String salvando(@ModelAttribute Servico servico, Model model){
        servico.setId(listaServico.size()+1);
        servicoService.criar(servico);
        return "redirect:/listagem";
    }
    
    @GetMapping ("/listagem")
    public String listaForm(Model model){
        model.addAttribute("lista", servicoService.listarTodos());
        return "lista";
    }
    
    @GetMapping("/excluir")
    public String excluirServico(@RequestParam String id){
        Integer idServico = Integer.parseInt(id);
        servicoService.excluir(idServico);
        return "redirect:/listagem";
    }
    
    @GetMapping("/editar")
    public String editarServico(@RequestParam String id, Model model){
         Integer idServico = Integer.parseInt(id);
         Servico servicoEncontrado = servicoService.buscarPorId(idServico);
         model.addAttribute("servico", servicoEncontrado);
         return "editar";
    }
    
    @PostMapping("atualizar")
    public String atualizarServico(@ModelAttribute Servico servico){
        servicoService.atualizar(servico);
        return "redirect:/listagem";
    }
    
    
    @GetMapping ("/exibir")
    public String mostraComentarios(Model model,@RequestParam String id){
        Integer idServico = Integer.parseInt(id);
        
        Servico servicoEncontrado = new Servico();
        servicoEncontrado = servicoService.buscarPorId(idServico);
        
        List<Comentario> comentariosEncontrados = new ArrayList<>();
        comentariosEncontrados = comentarioService.listar(idServico);
        
        model.addAttribute("servico", servicoEncontrado);
        model.addAttribute("comentario", new Comentario());
        model.addAttribute("comentarios", comentariosEncontrados);
        return "exibir";
    }
    
    
    @PostMapping ("/gravar-comentario")
    public String salvarComentario(@ModelAttribute Servico servico, @ModelAttribute Comentario comentario, Model model){
        comentario.setServico(servico);
        comentarioService.criar(comentario);
        return "redirect:/listagem";
    }
    
    
    
    
}

    

