package be.ucll.gerechten.controller;

import be.ucll.gerechten.model.Gerecht;
import be.ucll.gerechten.model.GerechtenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class GerechtenController implements WebMvcConfigurer {
    @Autowired
    private GerechtenService gerechtenService;

    @GetMapping("/home")
    public String home() { return "index"; }

    @GetMapping("/gerecht")
    public String gerechten(Model model) {
        model.addAttribute("gerechten", gerechtenService.getAllGerechten());
        return "gerechten";
    }

    @GetMapping("/gerecht/add")
    public String addGerechtForm() { return "addGerecht"; }

    @PostMapping("/gerecht/add")
    public String addGerecht(@RequestParam("type") String type, @Valid Gerecht gerecht, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            return "addGerecht";
        } else {
            gerecht.setType(type);
            gerechtenService.addGerecht(gerecht);
            model.addAttribute("gerechten", gerechtenService.getAllGerechten());
            return "gerechten";
        }
    }

    @GetMapping("/gerecht/update")
    public String updateGerechtForm(@RequestParam(value="id", required=true)int id, Model model) {
        model.addAttribute("gerecht", gerechtenService.findGerechtByid(id));
        return "updateGerecht";
    }

    @PostMapping("/gerecht/update")
    public String updateGerecht(@Valid Gerecht nieuwGerecht, Model model) {
        gerechtenService.updateGerecht(nieuwGerecht);
        model.addAttribute("gerechten", gerechtenService.getAllGerechten());
        return "gerechten";
    }

    @GetMapping("/gerecht/delete")
    public String deleteGerechtForm(@RequestParam(value="id", required=true)int id, Model model) {
        model.addAttribute("gerecht", gerechtenService.findGerechtByid(id));
        return "deleteGerecht";
    }

    @PostMapping("/gerecht/delete")
    public String deleteGerecht(@RequestParam(value="id", required=true)int id, Model model) {
        gerechtenService.deleteGerecht(gerechtenService.findGerechtByid(id));
        model.addAttribute("gerechten", gerechtenService.getAllGerechten());
        return "gerechten";
    }
}