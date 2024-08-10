package org.example.finalvalentina.web;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.example.finalvalentina.entities.Salesman;
import org.example.finalvalentina.repositories.SalesmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;
import java.util.List;

@SessionAttributes({"a", "e"})
@Controller
@AllArgsConstructor
public class SalesmanController {

    @Autowired
    private SalesmanRepository salesmanRepository;
    static int num=0;

    //Main method to render the current record from the salesman reposirity
    @GetMapping(path="/index")
    public String salesman(Model model, @RequestParam(name="keyword",defaultValue =
            "") String keyword){
        List<Salesman> salesman;
        if (keyword.isEmpty()) {
            salesman = salesmanRepository.findAll();
        } else {
            long key = Long.parseLong(keyword);
            salesman= salesmanRepository.findSalesmanById(key);
        }
        model.addAttribute("listSalesman", salesman);
        return "salesman";
    }



    //save method which will add a new record to repository after getting the input fields from the salesman.html
    @PostMapping(path="/save")
    public String save(Model model, Salesman salesman, BindingResult
            bindingResult, ModelMap mm, HttpSession session) {

        //model.addAttribute("salesman", salesman);
        //List<String> itemTypes = Arrays.asList("Washing Machine", "Refrigerator", "Music System");
        //model.addAttribute("itemTypes", itemTypes);

        if (bindingResult.hasErrors()) {
            return "salesman";
        } else {
            salesmanRepository.save(salesman);
            if (num == 2) {
                mm.put("e", 2);
                mm.put("a", 0);
            } else {
                mm.put("a", 1);
                mm.put("e", 0);
            }
            return "redirect:index";
        }
    }

    //delete method which takes as input the id of a user and deletes it from the repository and database
    @GetMapping("/delete")
    public String delete(Long id){
        salesmanRepository.deleteById(id);
        return "redirect:/index";
    }

    //edit method which takes as input the id of a user and looks for the record in the repository to be edited
    @GetMapping("/editSalesman")
    public String editSalesman(Model model, Long id, HttpSession session){
        num = 2;
        session.setAttribute("info", 0);
        Salesman salesman = salesmanRepository.findById(id).orElse(null);
        if(salesman==null) throw new RuntimeException("Salesman does not exist");
        model.addAttribute("salesman", salesman);
        return "editSalesman";
    }




}
