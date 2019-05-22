package be.ucll.gerechten;

import be.ucll.gerechten.model.Gerecht;
import be.ucll.gerechten.repository.GerechtRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
// Needed for testing the persistence layer
// Here using the in-memory DB H2
@DataJpaTest
public class GerechtRepositoryIntegrationTest {
    // Needed to add some data already in our DB to test things
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GerechtRepo gerechtRepo;

    @Test
    public void get_all_gerechten() {
        //given
        Gerecht gerecht1 = GerechtBuilder.eenGoedGerecht().build();
        //puts objects into the in-memory DB
        entityManager.persist(gerecht1);
        entityManager.flush();

        Gerecht gerecht2 = new Gerecht("Haaienvinnen soep", 1, "Soep");
        entityManager.persist(gerecht2);
        entityManager.flush();

        //when
        List<Gerecht> gevondenGerechten = gerechtRepo.findAll();

        //then
        assertThat(gevondenGerechten.size() == 2);
        assertThat(gevondenGerechten.contains(gerecht1));
        assertThat(gevondenGerechten.contains(gerecht2));
    }

    @Test
    public void find_gerecht_by_given_id() {
        //given
        Gerecht gerecht = new Gerecht("Haaienvinnen soep", 1, "Soep");
        entityManager.persist(gerecht);
        entityManager.flush();

        //when
        Gerecht gevonden = gerechtRepo.findGerechtByid(gerecht.getid());

        //then
        assertThat(gevonden.getDescription()).isEqualTo(gerecht.getDescription());
        assertThat(gevonden.getPrice()).isEqualTo(gerecht.getPrice());
        assertThat(gevonden.getType()).isEqualTo(gerecht.getType());
    }

    @Test
    public void add_gerecht() {
        //given
        Gerecht gerecht = new Gerecht("Haaienvinnen soep", 1, "Soep");

        //when
        Gerecht toegevoegdGerecht = gerechtRepo.save(gerecht);

        //then
        assertThat(toegevoegdGerecht.getDescription()).isEqualTo("Haaienvinnen soep");
        assertThat(toegevoegdGerecht.getPrice()).isEqualTo(1);
        assertThat(toegevoegdGerecht.getType()).isEqualTo("Soep");
    }

    @Test
    public void delete_gerecht() {
        int aantalGerechten = gerechtRepo.findAll().size();

        Gerecht gerecht = new Gerecht("Haaienvinnen soep", 1, "Soep");
        Gerecht toegevoegdGerecht = gerechtRepo.save(gerecht);

        int nieuwAantalGerechten = gerechtRepo.findAll().size();

        gerechtRepo.delete(gerechtRepo.findGerechtByid(toegevoegdGerecht.getid()));

        assertThat(not(gerechtRepo.findGerechtByid(toegevoegdGerecht.getid())));
        assertThat(gerechtRepo.findAll().size() == aantalGerechten);
        assertThat(not(gerechtRepo.findAll().size() == nieuwAantalGerechten));
    }

    @Test
    public void update_gerecht() {
        Gerecht gerecht = new Gerecht("Haaienvinnen soep", 1, "Soep");
        Gerecht toegevoegdGerecht = gerechtRepo.save(gerecht);

        Gerecht veranderdGerecht = new Gerecht("Walvis soep", 1, "Soep");
        veranderdGerecht.setid(toegevoegdGerecht.getid());

        //gerechtRepo.delete(toegevoegdGerecht);
        gerechtRepo.save(veranderdGerecht);

        assertThat(not(gerechtRepo.findGerechtByid(toegevoegdGerecht.getid()).getDescription().equals("Haaienvinnen soep")));
        assertThat(gerechtRepo.findGerechtByid(toegevoegdGerecht.getid()).getDescription().equals("Walvis soep"));
    }
}
