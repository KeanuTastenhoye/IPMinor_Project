package be.ucll.gerechten;

import be.ucll.gerechten.model.DagMenu;
import be.ucll.gerechten.model.Gerecht;

public class DagMenuBuilder {
    private String dag;
    private String datum;
    private Gerecht soep;
    private Gerecht dagschotel;
    private Gerecht veggie;

    private DagMenuBuilder() {

    }

    public static DagMenuBuilder eenDagMenu() { return new DagMenuBuilder(); }

    public static DagMenuBuilder eenGoedDagMenu() {
        return eenDagMenu().metDag("Maandag")
                           .metDatum("25-02-2019")
                           .metSoep(new Gerecht("Spinazie Soep", 1, "Soep"))
                           .metDagSchotel(new Gerecht("Spaghetti Bolognese", 4, "Dagschotel"))
                           .metVeggie(new Gerecht("Spinazie pizza", 4, "Veggie"));
    }

    public DagMenuBuilder metDag(String dag) {
        this.dag = dag;
        return this;
    }

    public DagMenuBuilder metDatum(String datum) {
        this.datum = datum;
        return this;
    }

    public DagMenuBuilder metSoep(Gerecht soep) {
        this.soep = soep;
        return this;
    }

    public DagMenuBuilder metDagSchotel(Gerecht dagschotel) {
        this.dagschotel = dagschotel;
        return this;
    }

    public DagMenuBuilder metVeggie(Gerecht veggie) {
        this.veggie = veggie;
        return this;
    }

    public DagMenu build() {
        DagMenu dagMenu = new DagMenu();
        dagMenu.setDag(dag);
        dagMenu.setDatum(datum);
        dagMenu.setSoep(soep);
        dagMenu.setDagschotel(dagschotel);
        dagMenu.setVeggie(veggie);
        return dagMenu;
    }
}
