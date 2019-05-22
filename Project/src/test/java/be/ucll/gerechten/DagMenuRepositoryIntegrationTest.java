package be.ucll.gerechten;

import be.ucll.gerechten.model.DagMenu;
import be.ucll.gerechten.model.Gerecht;
import be.ucll.gerechten.repository.DagRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DagMenuRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DagRepo dagRepo;

    @Test
    public void get_all_dagmenus() {
        DagMenu menu1 = DagMenuBuilder.eenGoedDagMenu().build();
        entityManager.persist(menu1);
        entityManager.flush();

        DagMenu menu2 = new DagMenu("Maandag",
                                    "25-02-2019",
                                    new Gerecht("Spinazie Soep", 1, "Soep"),
                                    new Gerecht("Spaghetti Bolognese", 4, "Dagschotel"),
                                    new Gerecht("Spinazie pizza", 4, "Veggie"));
        entityManager.persist(menu2);
        entityManager.flush();

        List<DagMenu> gevondenDagMenus = dagRepo.findAll();

        assertThat(gevondenDagMenus.size() == 2);
        assertThat(gevondenDagMenus.contains(menu1));
        assertThat(gevondenDagMenus.contains(menu2));
    }

    @Test
    public void find_dagmenu_by_given_datum() {
        DagMenu menu = new DagMenu("Maandag",
                                 "25-02-2019",
                                  new Gerecht("Spinazie Soep", 1, "Soep"),
                                  new Gerecht("Spaghetti Bolognese", 4, "Dagschotel"),
                                  new Gerecht("Spinazie pizza", 4, "Veggie"));
        entityManager.persist(menu);
        entityManager.flush();

        DagMenu gevonden = dagRepo.findDagMenuByDatum(menu.getDatum());

        assertThat(gevonden.getDag()).isEqualTo(menu.getDag());
        assertThat(gevonden.getDatum()).isEqualTo(menu.getDatum());

        assertThat(gevonden.getSoep().getDescription()).isEqualTo(menu.getSoep().getDescription());
        assertThat(gevonden.getSoep().getPrice()).isEqualTo(menu.getSoep().getPrice());
        assertThat(gevonden.getSoep().getType()).isEqualTo(menu.getSoep().getType());

        assertThat(gevonden.getDagschotel().getDescription()).isEqualTo(menu.getDagschotel().getDescription());
        assertThat(gevonden.getDagschotel().getPrice()).isEqualTo(menu.getDagschotel().getPrice());
        assertThat(gevonden.getDagschotel().getType()).isEqualTo(menu.getDagschotel().getType());

        assertThat(gevonden.getVeggie().getDescription()).isEqualTo(menu.getVeggie().getDescription());
        assertThat(gevonden.getVeggie().getPrice()).isEqualTo(menu.getVeggie().getPrice());
        assertThat(gevonden.getVeggie().getType()).isEqualTo(menu.getVeggie().getType());
    }

    @Test
    public void add_dagmenu() {
        DagMenu menu = new DagMenu("Maandag",
                                 "25-02-2019",
                                  new Gerecht("Spinazie Soep", 1, "Soep"),
                                  new Gerecht("Spaghetti Bolognese", 4, "Dagschotel"),
                                  new Gerecht("Spinazie pizza", 4, "Veggie"));

        DagMenu toegevoegdDagMenu = dagRepo.save(menu);

        assertThat(toegevoegdDagMenu.getDag()).isEqualTo("Maandag");
        assertThat(toegevoegdDagMenu.getDatum()).isEqualTo("25-02-2019");

        assertThat(toegevoegdDagMenu.getSoep().getDescription()).isEqualTo("Spinazie Soep");
        assertThat(toegevoegdDagMenu.getSoep().getPrice()).isEqualTo(1);
        assertThat(toegevoegdDagMenu.getSoep().getType()).isEqualTo("Soep");

        assertThat(toegevoegdDagMenu.getDagschotel().getDescription()).isEqualTo("Spaghetti Bolognese");
        assertThat(toegevoegdDagMenu.getDagschotel().getPrice()).isEqualTo(4);
        assertThat(toegevoegdDagMenu.getDagschotel().getType()).isEqualTo("Dagschotel");

        assertThat(toegevoegdDagMenu.getVeggie().getDescription()).isEqualTo("Spinazie pizza");
        assertThat(toegevoegdDagMenu.getVeggie().getPrice()).isEqualTo(4);
        assertThat(toegevoegdDagMenu.getVeggie().getType()).isEqualTo("Veggie");
    }

    @Test
    public void delete_dagmenu() {
        int aantalMenus = dagRepo.findAll().size();

        DagMenu menu = new DagMenu("Maandag",
                                 "25-02-2019",
                                  new Gerecht("Spinazie Soep", 1, "Soep"),
                                  new Gerecht("Spaghetti Bolognese", 4, "Dagschotel"),
                                  new Gerecht("Spinazie pizza", 4, "Veggie"));

        DagMenu toegevoegdDagMenu = dagRepo.save(menu);

        int nieuwAantalMenus = dagRepo.findAll().size();

        dagRepo.delete(dagRepo.findDagMenuByDatum(toegevoegdDagMenu.getDatum()));

        assertThat(dagRepo.findAll().size() == aantalMenus);
        assertThat(not(dagRepo.findAll().size() == nieuwAantalMenus));
        assertThat(not(dagRepo.findDagMenuByDatum(toegevoegdDagMenu.getDatum())));
    }

    @Test
    public void update_dagmenu() {
        DagMenu menu = new DagMenu("Maandag",
                                 "25-02-2019",
                                  new Gerecht("Spinazie Soep", 1, "Soep"),
                                  new Gerecht("Spaghetti Bolognese", 4, "Dagschotel"),
                                  new Gerecht("Spinazie pizza", 4, "Veggie"));

        DagMenu toegevoegdDagMenu = dagRepo.save(menu);

        DagMenu veranderdMenu = new DagMenu("Maandag",
                                          "25-02-2019",
                                           new Gerecht("Spinnen Soep", 1, "Soep"),
                                           new Gerecht("Curryworsten", 4, "Dagschotel"),
                                           new Gerecht("Groenten Taco", 4, "Veggie"));

        veranderdMenu.setid(toegevoegdDagMenu.getid());

        dagRepo.save(veranderdMenu);

        assertThat(not(dagRepo.findDagMenuByDatum(toegevoegdDagMenu.getDatum()).getSoep().getDescription().equals("Spinazie Soep")));
        assertThat(not(dagRepo.findDagMenuByDatum(toegevoegdDagMenu.getDatum()).getSoep().getDescription().equals("Spaghetti Bolognese")));
        assertThat(not(dagRepo.findDagMenuByDatum(toegevoegdDagMenu.getDatum()).getSoep().getDescription().equals("Spinazie pizza")));

        assertThat(dagRepo.findDagMenuByDatum(toegevoegdDagMenu.getDatum()).getSoep().getDescription().equals("Spinnen Soep"));
        assertThat(dagRepo.findDagMenuByDatum(toegevoegdDagMenu.getDatum()).getSoep().getDescription().equals("Currywirsten"));
        assertThat(dagRepo.findDagMenuByDatum(toegevoegdDagMenu.getDatum()).getSoep().getDescription().equals("Groenten Taco"));
    }
}
