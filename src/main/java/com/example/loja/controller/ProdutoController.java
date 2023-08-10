package com.example.loja.controller;

import com.example.loja.model.entity.Produto;
import com.example.loja.model.repository.ProdutoDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("produtos")
public class ProdutoController {
    ProdutoDAO dao = new ProdutoDAO();
    @ResponseBody
    @GetMapping("/listar")
    public ModelAndView listaProdutos(ModelMap model) {
        model.addAttribute("produtos", dao.buscarProdutos());
        return new ModelAndView("/list", model);
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id, ModelMap model) {
        model.addAttribute("produto", dao.buscarProduto(id));
        return new ModelAndView("createAndUpdate", model);
    }
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        dao.remove(id);
        return new ModelAndView("redirect:/produtos/listar");
    }

    @GetMapping("/form")
    public String form(Produto produto){
        return "/createAndUpdate";
    }
    @PostMapping("/save")
    public ModelAndView save(Produto produto){
        dao.save(produto);

        return new ModelAndView("redirect:/produtos/listar");
    }
    @PostMapping("/update")
    public ModelAndView update(Produto produto) {
        dao.update(produto);
        return new ModelAndView("redirect:/produtos/listar");
    }

}
