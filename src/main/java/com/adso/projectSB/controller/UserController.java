
package com.adso.projectSB.controller;


import com.adso.projectSB.model.Registro;
import com.adso.projectSB.service.ServiceRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller //Para definir la clase como controlador
public class UserController {
    @Autowired //Instanciar una clase dentro de una clase
    private ServiceRegistro serviceRegistro;

    @GetMapping("/register/new") //
    public String FormRegister(Model model){
        model.addAttribute("registro", new Registro());
        return "pages/registro";
    }

    @PostMapping("/registro")
    public String CreateUser(@ModelAttribute Registro registro){
        serviceRegistro.saveRegister(registro);
        return "pages/index";
    }

    @GetMapping("/registro")
    public String ListRegister(Model model){
        model.addAttribute("result", serviceRegistro.getAllRegistro());
        return "fragments/dataSave";
    }

    @GetMapping("/")
    public String index(){
        return "pages/index";
    }

    @RequestMapping("/lista")
    public String result(Model modelo) {
        modelo.addAttribute("result", this.serviceRegistro.getAllRegistro());
        return "pages/lista";
    }

    @GetMapping("/login")
    public String login() {
        return "pages/login";
    }




}

