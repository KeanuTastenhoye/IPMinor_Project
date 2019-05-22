package be.ucll.gerechten;

import be.ucll.gerechten.model.DagMenu;
import be.ucll.gerechten.model.Gerecht;
import be.ucll.gerechten.repository.DagRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class RestControllerIntegrationTest {

    @Autowired
    private MockMvc restController;

    @Autowired
    private DagRepo dagRepo;

    private void createTestMenu(String dag,
                                String datum,
                                String soepDescription,
                                double soepPrice,
                                String soepType,
                                String dagschotelDescription,
                                double dagschotelPrice,
                                String dagschotelType,
                                String veggieDescription,
                                double veggiePrice,
                                String veggieType) {
        Gerecht soep = new Gerecht(soepDescription, soepPrice, soepType);
        Gerecht dagschotel = new Gerecht(dagschotelDescription, dagschotelPrice, dagschotelType);
        Gerecht veggie = new Gerecht(veggieDescription, veggiePrice, veggieType);

        DagMenu menu = new DagMenu(dag, datum, soep, dagschotel, veggie);
        dagRepo.saveAndFlush(menu);
    }

    @Test
    public void givenMenu_WhenGetMenus_thenStatus200AndJSONofMenus() throws Exception {
        createTestMenu( "Dinsdag",
                        "26-02-2019",
                        "Vis Soep",
                        1,
                        "Soep",
                        "Stoofvlees met frietjes",
                        4,
                        "Dagschotel",
                        "Tofu sla",
                        4,
                        "Veggie");

        restController.perform(get("/weekMenu")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))

                .andExpect(jsonPath("$[0].dag").value("Dinsdag"))
                .andExpect(jsonPath("$[0].datum").value("26-02-2019"))

                .andExpect(jsonPath("$[0].soep.description").value("Vis Soep"))
                .andExpect(jsonPath("$[0].soep.price").value(1))
                .andExpect(jsonPath("$[0].soep.type").value("Soep"))

                .andExpect(jsonPath("$[0].dagschotel.description").value("Stoofvlees met frietjes"))
                .andExpect(jsonPath("$[0].dagschotel.price").value(4))
                .andExpect(jsonPath("$[0].dagschotel.type").value("Dagschotel"))

                .andExpect(jsonPath("$[0].veggie.description").value("Tofu sla"))
                .andExpect(jsonPath("$[0].veggie.price").value(4))
                .andExpect(jsonPath("$[0].veggie.type").value("Veggie"));
    }

    @Test
    public void givenNoMenu_WhenUpdateMenu_ThenStatus200AndJSONofMenus() throws Exception {
        restController.perform(post("/weekMenu/update/26-02-2019")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"dag\":\"Dinsdag\",\"datum\":\"26-02-2019\",\"soep\":{\"description\":\"Tomaten Soep\",\"price\": 1,\"type\":\"Soep\"},\"dagschotel\":{\"description\":\"Stoofvlees met frietjes\",\"price\":4,\"type\":\"Dagschotel\"},\"veggie\":{\"description\":\"Tofu sla\",\"price\":4,\"type\":\"Veggie\"}}"))
                .andExpect(status().is(201))

                .andExpect(jsonPath("$[0].dag").value("Dinsdag"))
                .andExpect(jsonPath("$[0].datum").value("26-02-2019"))

                .andExpect(jsonPath("$[0].soep.description").value("Tomaten Soep"))
                .andExpect(jsonPath("$[0].soep.price").value(1))
                .andExpect(jsonPath("$[0].soep.type").value("Soep"))

                .andExpect(jsonPath("$[0].dagschotel.description").value("Stoofvlees met frietjes"))
                .andExpect(jsonPath("$[0].dagschotel.price").value(4))
                .andExpect(jsonPath("$[0].dagschotel.type").value("Dagschotel"))

                .andExpect(jsonPath("$[0].veggie.description").value("Tofu sla"))
                .andExpect(jsonPath("$[0].veggie.price").value(4))
                .andExpect(jsonPath("$[0].veggie.type").value("Veggie"));
    }

    @Test
    public void givenMenu_WhenDeleteMenu_ThenStatus200AndJSONofMenus() throws Exception {
        restController.perform(post("/weekMenu/delete/26-02-2019")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void givenNoMenu_WhenAddMenu_ThenStatus200AndJSONofMenus() throws Exception {
        restController.perform(post("/weekMenu/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"dag\":\"Woensdag\",\"datum\":\"27-02-2019\",\"soep\":{\"description\":\"Dolfijnen Soep\",\"price\": 1,\"type\":\"Soep\"},\"dagschotel\":{\"description\":\"Koninginnehapje met frieten\",\"price\":2,\"type\":\"Dagschotel\"},\"veggie\":{\"description\":\"Tofu sla\",\"price\":3,\"type\":\"Veggie\"}}"))
                .andExpect(status().is(201))

                .andExpect(jsonPath("$[0].dag").value("Woensdag"))
                .andExpect(jsonPath("$[0].datum").value("27-02-2019"))

                .andExpect(jsonPath("$[0].soep.description").value("Dolfijnen Soep"))
                .andExpect(jsonPath("$[0].soep.price").value(1))
                .andExpect(jsonPath("$[0].soep.type").value("Soep"))

                .andExpect(jsonPath("$[0].dagschotel.description").value("Koninginnehapje met frieten"))
                .andExpect(jsonPath("$[0].dagschotel.price").value(2))
                .andExpect(jsonPath("$[0].dagschotel.type").value("Dagschotel"))

                .andExpect(jsonPath("$[0].veggie.description").value("Tofu sla"))
                .andExpect(jsonPath("$[0].veggie.price").value(3))
                .andExpect(jsonPath("$[0].veggie.type").value("Veggie"));
    }
}
