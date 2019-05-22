package be.ucll.gerechten;

import be.ucll.gerechten.controller.Restcontroller;
import be.ucll.gerechten.model.DagMenu;
import be.ucll.gerechten.model.Gerecht;
import be.ucll.gerechten.model.GerechtenService;
import be.ucll.gerechten.model.WeekMenu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
// Bootstraps the controller and auto-configures MockMvc, this are testing controllers
// without starting a full HTTP server
@WebMvcTest(Restcontroller.class)
public class RestControllerUnitTest {

    @Autowired
    private MockMvc restcontroller;

    @MockBean
    private GerechtenService gerechtenService;

    @Test
    public void givenTwoMenus_WhenGetMenus_ThenReturnJSON() throws Exception {
        Gerecht gerecht1 = new Gerecht("Dolfijnen Soep", 1, "Soep");
        Gerecht gerecht2 = new Gerecht("Koninginnehapje met frieten", 2, "Dagschotel");
        Gerecht gerecht3 = new Gerecht("Tofu sla", 3, "Veggie");

        List<Gerecht> gerechten = new ArrayList<>();

        gerechten.add(gerecht1);
        gerechten.add(gerecht2);
        gerechten.add(gerecht3);

        DagMenu dagMenu = DagMenuBuilder.eenGoedDagMenu().build();
        DagMenu dagMenu2 = new DagMenu("Woensdag", "27-02-2019", gerecht1, gerecht2, gerecht3);

        WeekMenu weekMenu = new WeekMenu();

        weekMenu.addDagInWeekMenu(dagMenu);
        weekMenu.addDagInWeekMenu(dagMenu2);

        when(gerechtenService.getAllDagMenus()).thenReturn(Arrays.asList(dagMenu, dagMenu2));

        restcontroller.perform(get("/weekMenu")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].dag").value("Maandag"))
                .andExpect(jsonPath("$[0].datum").value("25-02-2019"))

                .andExpect(jsonPath("$[0].soep.description").value("Spinazie Soep"))
                .andExpect(jsonPath("$[0].soep.price").value(1))
                .andExpect(jsonPath("$[0].soep.type").value("Soep"))

                .andExpect(jsonPath("$[0].dagschotel.description").value("Spaghetti Bolognese"))
                .andExpect(jsonPath("$[0].dagschotel.price").value(4))
                .andExpect(jsonPath("$[0].dagschotel.type").value("Dagschotel"))

                .andExpect(jsonPath("$[0].veggie.description").value("Spinazie pizza"))
                .andExpect(jsonPath("$[0].veggie.price").value(4))
                .andExpect(jsonPath("$[0].veggie.type").value("Veggie"))

                .andExpect(jsonPath("$[1].dag").value("Woensdag"))
                .andExpect(jsonPath("$[1].datum").value("27-02-2019"))

                .andExpect(jsonPath("$[1].soep.description").value("Dolfijnen Soep"))
                .andExpect(jsonPath("$[1].soep.price").value(1))
                .andExpect(jsonPath("$[1].soep.type").value("Soep"))

                .andExpect(jsonPath("$[1].dagschotel.description").value("Koninginnehapje met frieten"))
                .andExpect(jsonPath("$[1].dagschotel.price").value(2))
                .andExpect(jsonPath("$[1].dagschotel.type").value("Dagschotel"))

                .andExpect(jsonPath("$[1].veggie.description").value("Tofu sla"))
                .andExpect(jsonPath("$[1].veggie.price").value(3))
                .andExpect(jsonPath("$[1].veggie.type").value("Veggie"));
    }

    @Test
    public void givenTwoMenus_WhenDeleteMenu_ThenReturnJSON() throws Exception {
        Gerecht gerecht1 = new Gerecht("Dolfijnen Soep", 1, "Soep");
        Gerecht gerecht2 = new Gerecht("Koninginnehapje met frieten", 2, "Dagschotel");
        Gerecht gerecht3 = new Gerecht("Tofu sla", 3, "Veggie");

        List<Gerecht> gerechten = new ArrayList<>();

        gerechten.add(gerecht1);
        gerechten.add(gerecht2);
        gerechten.add(gerecht3);

        DagMenu dagMenu = DagMenuBuilder.eenGoedDagMenu().build();
        DagMenu dagMenu2 = new DagMenu("Woensdag", "27-02-2019", gerecht1, gerecht2, gerecht3);

        WeekMenu weekMenu = new WeekMenu();

        weekMenu.addDagInWeekMenu(dagMenu);
        weekMenu.addDagInWeekMenu(dagMenu2);

        when(gerechtenService.getAllDagMenus()).thenReturn(Arrays.asList(dagMenu, dagMenu2));

        gerechtenService.deleteMenu(dagMenu2.getDatum());

        when(gerechtenService.getAllDagMenus()).thenReturn(Arrays.asList(dagMenu));

        restcontroller.perform(post("/weekMenu/delete/" + dagMenu2.getDatum())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$", hasSize(1)))

                .andExpect(jsonPath("$[0].dag").value("Maandag"))
                .andExpect(jsonPath("$[0].datum").value("25-02-2019"))

                .andExpect(jsonPath("$[0].soep.description").value("Spinazie Soep"))
                .andExpect(jsonPath("$[0].soep.price").value(1))
                .andExpect(jsonPath("$[0].soep.type").value("Soep"))

                .andExpect(jsonPath("$[0].dagschotel.description").value("Spaghetti Bolognese"))
                .andExpect(jsonPath("$[0].dagschotel.price").value(4))
                .andExpect(jsonPath("$[0].dagschotel.type").value("Dagschotel"))

                .andExpect(jsonPath("$[0].veggie.description").value("Spinazie pizza"))
                .andExpect(jsonPath("$[0].veggie.price").value(4))
                .andExpect(jsonPath("$[0].veggie.type").value("Veggie"));
        }

    @Test
    public void givenOneMenus_WhenUpdateMenu_ThenReturnJSON() throws Exception {
        Gerecht gerecht1 = new Gerecht("Dolfijnen Soep", 1, "Soep");
        Gerecht gerecht2 = new Gerecht("Koninginnehapje met frieten", 2, "Dagschotel");
        Gerecht gerecht3 = new Gerecht("Tofu sla", 3, "Veggie");

        DagMenu dagMenu = DagMenuBuilder.eenGoedDagMenu().build();
        DagMenu nieuwDagMenu = new DagMenu("Maandag", "25-02-2019", gerecht1, gerecht2, gerecht3);

        when(gerechtenService.getAllDagMenus()).thenReturn(Arrays.asList(dagMenu));

        gerechtenService.updateMenu(nieuwDagMenu, dagMenu.getDatum());

        when(gerechtenService.getAllDagMenus()).thenReturn(Arrays.asList(nieuwDagMenu));

        restcontroller.perform(post("/weekMenu/update/25-02-2019")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"dag\":\"Maandag\",\"datum\":\"25-02-2019\",\"soep\":{\"description\":\"Dolfijnen Soep\",\"price\": 1,\"type\":\"Soep\"},\"dagschotel\":{\"description\":\"Koninginnehapje met frieten\",\"price\":2,\"type\":\"Dagschotel\"},\"veggie\":{\"description\":\"Tofu sla\",\"price\":3,\"type\":\"Veggie\"}}"))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$", hasSize(1)))

                .andExpect(jsonPath("$[0].dag").value("Maandag"))
                .andExpect(jsonPath("$[0].datum").value("25-02-2019"))

                .andExpect(jsonPath("$[0].soep.description").value(nieuwDagMenu.getSoep().getDescription()))
                .andExpect(jsonPath("$[0].soep.price").value(nieuwDagMenu.getSoep().getPrice()))
                .andExpect(jsonPath("$[0].soep.type").value(nieuwDagMenu.getSoep().getType()))

                .andExpect(jsonPath("$[0].dagschotel.description").value(nieuwDagMenu.getDagschotel().getDescription()))
                .andExpect(jsonPath("$[0].dagschotel.price").value(nieuwDagMenu.getDagschotel().getPrice()))
                .andExpect(jsonPath("$[0].dagschotel.type").value(nieuwDagMenu.getDagschotel().getType()))

                .andExpect(jsonPath("$[0].veggie.description").value(nieuwDagMenu.getVeggie().getDescription()))
                .andExpect(jsonPath("$[0].veggie.price").value(nieuwDagMenu.getVeggie().getPrice()))
                .andExpect(jsonPath("$[0].veggie.type").value(nieuwDagMenu.getVeggie().getType()));
    }

    @Test
    public void givenNoMenus_WhenAddMenu_ThenReturnJSON() throws Exception {
        DagMenu dagMenu = DagMenuBuilder.eenGoedDagMenu().build();
        when(gerechtenService.getAllDagMenus()).thenReturn(Arrays.asList(dagMenu));

        restcontroller.perform(post("/weekMenu/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"dag\":\"Maandag\",\"datum\":\"25-02-2019\",\"soep\":{\"description\":\"Spinazie Soep\",\"price\": 1,\"type\":\"Soep\"},\"dagschotel\":{\"description\":\"Spaghetti Bolognese\",\"price\":4,\"type\":\"Dagschotel\"},\"veggie\":{\"description\":\"Spinazie pizza\",\"price\":4,\"type\":\"Veggie\"}}"))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$", hasSize(1)))

                .andExpect(jsonPath("$[0].dag").value("Maandag"))
                .andExpect(jsonPath("$[0].datum").value("25-02-2019"))

                .andExpect(jsonPath("$[0].soep.description").value("Spinazie Soep"))
                .andExpect(jsonPath("$[0].soep.price").value(1))
                .andExpect(jsonPath("$[0].soep.type").value("Soep"))

                .andExpect(jsonPath("$[0].dagschotel.description").value("Spaghetti Bolognese"))
                .andExpect(jsonPath("$[0].dagschotel.price").value(4))
                .andExpect(jsonPath("$[0].dagschotel.type").value("Dagschotel"))

                .andExpect(jsonPath("$[0].veggie.description").value("Spinazie pizza"))
                .andExpect(jsonPath("$[0].veggie.price").value(4))
                .andExpect(jsonPath("$[0].veggie.type").value("Veggie"));
    }
}
