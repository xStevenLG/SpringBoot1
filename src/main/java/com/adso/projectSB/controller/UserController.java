
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

    @GetMapping("/lista/{id}")
    public String deleteRegister(@PathVariable Long id) {
        serviceRegistro.deleteRegister(id);
        return "redirect:/lista";
    }
    @GetMapping("/registro/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Registro registro = serviceRegistro.getRegisterById(id);
        model.addAttribute("registro", registro);
        return "pages/editRegistro"; // Nombre de la vista del formulario de edición
    }

    @PostMapping("/registro/update/{id}")
    public String updateRegister(@PathVariable("id") Long id, @ModelAttribute Registro registro) {
        registro.setId(id); // Asegura que el ID esté establecido
        serviceRegistro.updateRegister(registro);
        return "redirect:/lista"; // Redirige a la lista de registros después de actualizar
    }
}




