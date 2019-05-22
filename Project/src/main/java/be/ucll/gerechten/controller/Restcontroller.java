package be.ucll.gerechten.controller;

import be.ucll.gerechten.model.DagMenu;
import be.ucll.gerechten.model.GerechtenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class Restcontroller {
    @Autowired
    GerechtenService gerechtenService;

    @GetMapping("/weekMenu")
    public List<DagMenu> menus() {
        return gerechtenService.getAllDagMenus();
    }

    @PostMapping("/weekMenu/add")
    @ResponseStatus(HttpStatus.CREATED)
    public List<DagMenu> addMenu(@RequestBody @Valid DagMenu dagMenu) {
        gerechtenService.addMenu(dagMenu);
        return gerechtenService.getAllDagMenus();
    }

    @PostMapping("/weekMenu/update/{datum}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<DagMenu> updateMenu(@PathVariable("datum") String datum,
                                    @RequestBody @Valid DagMenu menu) {
        //DagMenu oudeMenu = gerechtenService.findDagMenuByDatum(datum);
        //menu.setid(oudeMenu.getid());
        gerechtenService.updateMenu(menu, datum);
        return gerechtenService.getAllDagMenus();
    }

    @PostMapping("/weekMenu/delete/{datum}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<DagMenu> deleteMenu(@PathVariable("datum") String datum) {
        gerechtenService.deleteMenu(datum);
        return gerechtenService.getAllDagMenus();
    }
}