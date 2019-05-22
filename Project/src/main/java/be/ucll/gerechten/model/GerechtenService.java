package be.ucll.gerechten.model;

import be.ucll.gerechten.repository.DagRepo;
import be.ucll.gerechten.repository.GerechtRepo;
import be.ucll.gerechten.repository.WeekRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerechtenService {
    @Autowired
    GerechtRepo gerechtRepo;

    @Autowired
    DagRepo dagRepo;

    @Autowired
    WeekRepo weekRepo;

    public GerechtenService() {

    }

    /*
    ###############################################
    # Gerecht
    ###############################################
    */

    public List<Gerecht> getAllGerechten() { return gerechtRepo.findAll(); }

    public Gerecht findGerechtByid(int id) { return gerechtRepo.findGerechtByid(id); }

    public Gerecht addGerecht(Gerecht gerecht) {
        //Eerst gerecht zoeken, dan id setten(anders wordt een nieuw gerecht aan DB
        //toegevoegd --> Dit is voor UPDATE via REST controller)
        Gerecht g = gerechtRepo.findGerechtByid(gerecht.getid());
        if (g != null) {
            gerecht.setid(g.getid());
        }
        return gerechtRepo.save(gerecht);
    }

    public void deleteGerecht(Gerecht gerecht) { gerechtRepo.delete(gerecht); }

    public void updateGerecht(Gerecht nieuwGerecht) { gerechtRepo.save(nieuwGerecht); }

     /*
    ###############################################
    # DagMenu
    ###############################################
    */

    public List<DagMenu> getAllDagMenus() { return dagRepo.findAll(); }

    public DagMenu findDagMenuByDatum(String datum) { return dagRepo.findDagMenuByDatum(datum); }

    public void addMenu(DagMenu menu) {
        dagRepo.save(menu);
        //commitToDatabase(menu);
    }

    public void updateMenu(DagMenu nieuwMenu, String datum) {
        nieuwMenu.setDatum(datum);

        DagMenu origineelMenu = dagRepo.findDagMenuByDatum(datum);
        nieuwMenu.setid(origineelMenu.getid());

        dagRepo.save(nieuwMenu);

        //commitToDatabase(nieuwMenu);
    }

    public void deleteMenu(String datum) {
        DagMenu menu = dagRepo.findDagMenuByDatum(datum);
        dagRepo.delete(menu);
    }

    /*private void commitToDatabase(DagMenu menu) {
        //Eerst dit, dit is controle tegen dubbele waardes
        addGerecht(menu.getSoep());
        addGerecht(menu.getDagschotel());
        addGerecht(menu.getVeggie());

        dagRepo.save(menu);
    }*/

     /*
    ###############################################
    # WeekMenu
    ###############################################
    */

    public List<WeekMenu> getAllWeekMenus() { return weekRepo.findAll(); }

    public WeekMenu findWeekMenuByid(int id) { return weekRepo.findWeekMenuByid(id);}

}
