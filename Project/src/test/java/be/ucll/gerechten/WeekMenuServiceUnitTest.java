package be.ucll.gerechten;

import be.ucll.gerechten.model.DagMenu;
import be.ucll.gerechten.model.Gerecht;
import be.ucll.gerechten.model.GerechtenService;
import be.ucll.gerechten.model.WeekMenu;
import be.ucll.gerechten.repository.DagRepo;
import be.ucll.gerechten.repository.GerechtRepo;
import be.ucll.gerechten.repository.WeekRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

// Bridge between Spring Boot test features and JUnit
@RunWith(SpringRunner.class)
public class WeekMenuServiceUnitTest {

    @TestConfiguration
    static class WeekMenuServiceTestContextConfiguration {

        // Creates an instance of service in order to be able to autowire it
        @Bean
        public GerechtenService weekMenuService() { return new GerechtenService(); }
    }

    @Autowired
    private GerechtenService weekMenuService;

    // Service is dependent of Repository, however implementation of Repository is
    // not important => therefor mock it
    // Mock support of Spring Boot Test

    //Otherwise errors, has to be mentioned because it is declared in Gerechtenservice
    @MockBean
    private GerechtRepo gerechtRepo;

    //Otherwise errors, has to be mentioned because it is declared in Gerechtenservice
    @MockBean
    private DagRepo dagRepo;

    @MockBean
    private WeekRepo weekRepo;

    private Gerecht gerecht1, gerecht2, gerecht3, gerecht4, gerecht5, gerecht6, gerecht7, gerecht8, gerecht9;
    private DagMenu dagMenu1, dagMenu2, dagMenu3;
    private WeekMenu weekMenu1, weekMenu2;
    private List<WeekMenu> weekMenus;

    @Before
    public void setUp() {
        gerecht1 = new Gerecht("Dolfijnen Soep", 1, "Soep");
        gerecht2 = new Gerecht("Koninginnehapje met frieten", 2, "Dagschotel");
        gerecht3 = new Gerecht("Tofu sla", 3, "Veggie");

        gerecht4 = new Gerecht("Vis Soep", 1, "Soep");
        gerecht5 = new Gerecht("Stoofvlees met frietjes", 4, "Dagschotel");
        gerecht6 = new Gerecht("Amandel sla", 4, "Veggie");

        gerecht7 = new Gerecht("Tomaten Soep", 1, "Soep");
        gerecht8 = new Gerecht("Cordon Bleu", 4, "Dagschotel");
        gerecht9 = new Gerecht("Veggie Burger", 3, "Veggie");

        dagMenu1 = new DagMenu("Donderdag", "28-02-2019", gerecht1, gerecht2, gerecht3);
        dagMenu2 = new DagMenu("Vrijdag", "1-03-2019", gerecht4, gerecht5, gerecht6);
        dagMenu3 = new DagMenu("Maandag", "4-03-2019", gerecht5, gerecht6, gerecht7);

        weekMenu1 = new WeekMenu();
        weekMenu2 = new WeekMenu();

        weekMenus = new ArrayList<>();

        weekMenu1.addDagInWeekMenu(dagMenu1);
        weekMenu1.addDagInWeekMenu(dagMenu2);

        weekMenu2.addDagInWeekMenu(dagMenu3);

        weekMenus.add(weekMenu1);
        weekMenus.add(weekMenu2);
    }

    @Test
    public void should_get_all_weekMenus() {
        //Mock
        Mockito.when(weekRepo.findAll()).thenReturn(weekMenus);

        //When
        List<WeekMenu> gevondenWeekMenus = weekMenuService.getAllWeekMenus();

        //Then
        assertThat(gevondenWeekMenus.size()).isEqualTo(2);
        assertThat(gevondenWeekMenus).contains(weekMenu1);
        assertThat(gevondenWeekMenus).contains(weekMenu2);
    }

    @Test
    public void should_find_weekMenu_by_given_id() {
        //Mock
        Mockito.when(weekRepo.findWeekMenuByid(weekMenu2.getid())).thenReturn(weekMenu2);

        //Given
        int id = weekMenu2.getid();

        //When
        WeekMenu gevondenMenu = weekMenuService.findWeekMenuByid(id);

        //Then
        assertThat(gevondenMenu.getid()).isEqualTo(id);
    }
}
