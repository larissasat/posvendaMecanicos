
package com.mecanicossa.posvenda.controller;


import com.mecanicossa.posvenda.model.Servico;
import com.mecanicossa.posvenda.model.Comentario;
import java.util.ArrayList;
import java.util.List;
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
        listaServico.add(servico);
        return "redirect:/listagem";
    }
    
    @GetMapping ("/listagem")
    public String listaForm(Model model){
        model.addAttribute("lista", listaServico);
        return "lista";
    }
    
    
    @GetMapping ("/exibir")
    public String mostraComentarios(Model model,@RequestParam String id){
        Integer idServico = Integer.parseInt(id);
        Servico servicoEncontrado = new Servico();
        for (Servico s:listaServico){
            if (s.getId() == idServico){
                servicoEncontrado = s;
                break;
            }
        }
        
        List<Comentario> comentariosEncontrados = new ArrayList<>();
        for (Comentario c:listaComentario){
            if (c.getServico().getId() == idServico){
                comentariosEncontrados.add(c);
            }
        }
        
        model.addAttribute("servico", servicoEncontrado);
        model.addAttribute("comentario", new Comentario());
        model.addAttribute("comentarios", comentariosEncontrados);
        return "exibir";
    }
    
    

    
    @PostMapping ("/gravar-comentario")
    public String salvarComentario(@ModelAttribute Servico servico, @ModelAttribute Comentario comentario, Model model){
        comentario.setId(listaComentario.size()+1);
        comentario.setServico(servico);
        listaComentario.add(comentario);
        return "redirect:/listagem";
    }
    
    
    
    
}

    

