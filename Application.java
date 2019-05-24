package be.ucll.gerechten;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
/*
    private AtomicInteger nextMealId = new AtomicInteger();
    private AtomicInteger nextMenuId = new AtomicInteger();

    private Gerecht bloemkoolSoep = new Gerecht(nextMealId.incrementAndGet(), "Bloemkool Soep", 1, "Soep");
    private Gerecht tomatenSoep = new Gerecht(nextMealId.incrementAndGet(), "Tomaten Soep", 1, "Soep");
    private Gerecht pompoenSoep = new Gerecht(nextMealId.incrementAndGet(), "Pompoen Soep", 1, "Soep");
    private Gerecht kervelSoep = new Gerecht(nextMealId.incrementAndGet(), "Kervel Soep", 1, "Soep");

    private Gerecht cordonBleu = new Gerecht(nextMealId.incrementAndGet(), "Cordon bleu", 4.20, "Dagschotel");
    private Gerecht konijnMetPruimen = new Gerecht(nextMealId.incrementAndGet(), "Konijn met pruimen", 4.20, "Dagschotel");
    private Gerecht hertenRagout = new Gerecht(nextMealId.incrementAndGet(), "Herten Ragout", 4.20, "Dagschotel");
    private Gerecht lamskotelettenMetboontjes = new Gerecht(nextMealId.incrementAndGet(), "Lamskoteletten met boontjes", 4.20, "Dagschotel");

    private Gerecht groentenLasagne = new Gerecht(nextMealId.incrementAndGet(), "Groenten lasagne", 4, "Veggie");
    private Gerecht veggiePasta = new Gerecht(nextMealId.incrementAndGet(), "Veggie pasta", 4, "Veggie");
    private Gerecht veggieBurger = new Gerecht(nextMealId.incrementAndGet(), "Veggie burger", 4, "Veggie");
    private Gerecht kaasKroketten = new Gerecht(nextMealId.incrementAndGet(), "Kaas kroketten", 4, "Veggie");

    private DagMenu maandag = new DagMenu(nextMenuId.incrementAndGet(), "Maandag", "18-02-2019", bloemkoolSoep, cordonBleu, groentenLasagne);
    private DagMenu dinsdag = new DagMenu(nextMenuId.incrementAndGet(), "Dinsdag", "19-02-2019", tomatenSoep, konijnMetPruimen, veggiePasta);
    private DagMenu woensdag = new DagMenu(nextMenuId.incrementAndGet(), "Woensdag", "20-02-2019", pompoenSoep, hertenRagout, veggieBurger);
    private DagMenu donderdag = new DagMenu(nextMenuId.incrementAndGet(), "Donderdag", "21-02-2019", kervelSoep, lamskotelettenMetboontjes, kaasKroketten);
*/
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
/*
    @Bean
    @Order(1)
    public CommandLineRunner runnerGerechten(GerechtRepo gerechtRepo) {
        return GerechtArgs -> {
            gerechtRepo.save(bloemkoolSoep);
            gerechtRepo.save(tomatenSoep);
            gerechtRepo.save(pompoenSoep);
            gerechtRepo.save(kervelSoep);

            gerechtRepo.save(cordonBleu);
            gerechtRepo.save(konijnMetPruimen);
            gerechtRepo.save(hertenRagout);
            gerechtRepo.save(lamskotelettenMetboontjes);

            gerechtRepo.save(groentenLasagne);
            gerechtRepo.save(veggiePasta);
            gerechtRepo.save(veggieBurger);
            gerechtRepo.save(kaasKroketten);
        };
    }

    @Bean
    @Order(2)
    public CommandLineRunner runnerDagMenus(DagRepo dagRepo) {
        return DagArgs -> {
            dagRepo.save(maandag);
            dagRepo.save(dinsdag);
            dagRepo.save(woensdag);
            dagRepo.save(donderdag);
        };
    }

    @Bean
    @Order(3)
    public CommandLineRunner runnerWeekMenus(WeekRepo weekRepo, DagRepo dagRepo) {
        return WeekArgs -> {
            WeekMenu menu = new WeekMenu();

            menu.addDagInWeekMenu(dagRepo.findDagMenuByDatum("18-02-2019"));
            menu.addDagInWeekMenu(dagRepo.findDagMenuByDatum("19-02-2019"));
            menu.addDagInWeekMenu(dagRepo.findDagMenuByDatum("20-02-2019"));
            menu.addDagInWeekMenu(dagRepo.findDagMenuByDatum("21-02-2019"));

            weekRepo.save(menu);
        };
    }
*/
}