package be.ucll.gerechten;

import be.ucll.gerechten.model.WeekMenu;
import be.ucll.gerechten.repository.WeekRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WeekMenuRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private WeekRepo weekRepo;

    @Test
    public void get_all_weekmenus() {
        WeekMenu menu1 = WeekMenuBuilder.eenGoedWeekMenu().build();
        entityManager.persist(menu1);
        entityManager.flush();

        WeekMenu menu2 = new WeekMenu();
        entityManager.persist(menu2);
        entityManager.flush();

        List<WeekMenu> gevondenWeekMenus = weekRepo.findAll();

        assertThat(gevondenWeekMenus.size() == 2);
        assertThat(gevondenWeekMenus.contains(menu1));
        assertThat(gevondenWeekMenus.contains(menu2));
    }

    @Test
    public void find_weekmenu_by_given_id() {
        WeekMenu menu = new WeekMenu();
        entityManager.persist(menu);
        entityManager.flush();

        WeekMenu gevonden = weekRepo.findWeekMenuByid(menu.getid());

        System.out.println("MENUUUUUU " + menu.getid() + " " + menu.getWeek());
        System.out.println("GEVONDENN " + gevonden.getid() + " " + gevonden.getWeek());

        assertThat(gevonden.getid() == menu.getid());
    }
}
