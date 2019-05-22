package be.ucll.gerechten.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dagMenu")
public class DagMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int id;

    @NotNull
    private String dag;

    @NotNull
    private String datum;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Gerecht soep;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Gerecht dagschotel;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Gerecht veggie;

    public DagMenu(int id, String dag, String datum, Gerecht soep, Gerecht dagschotel, Gerecht veggie) {
        this.setid(id);
        this.setDag(dag);
        this.setDatum(datum);
        this.setSoep(soep);;
        this.setDagschotel(dagschotel);
        this.setVeggie(veggie);
    }

    public DagMenu(String dag, String datum, Gerecht soep, Gerecht dagschotel, Gerecht veggie) {
        this.setDag(dag);
        this.setDatum(datum);
        this.setSoep(soep);;
        this.setDagschotel(dagschotel);
        this.setVeggie(veggie);
    }

    public DagMenu() {}

    public int getid() { return id; }
    public String getDag() { return dag; }
    public String getDatum() { return datum; }
    public Gerecht getSoep() { return soep; }
    public Gerecht getDagschotel() { return dagschotel; }
    public Gerecht getVeggie() { return veggie; }

    public void setid(int id) { this.id = id; }
    public void setDatum(String datum) { this.datum = datum; }

    public void setDag(String dag) {
        if (!dag.toLowerCase().equals("maandag") &&
            !dag.toLowerCase().equals("dinsdag") &&
            !dag.toLowerCase().equals("woensdag") &&
            !dag.toLowerCase().equals("donderdag") &&
            !dag.toLowerCase().equals("vrijdag")) {
            throw new IllegalArgumentException("Foute dag!");
        }
        //Alleen de 1e letter is een hoofdletter
        this.dag = dag.substring(0,1).toUpperCase() + dag.substring(1).toLowerCase();
    }

    public void setSoep(Gerecht soep) { this.soep = soep; }
    public void setDagschotel(Gerecht dagschotel) { this.dagschotel = dagschotel; }
    public void setVeggie(Gerecht veggie) { this.veggie = veggie; }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DagMenu) {
            DagMenu dag = (DagMenu) o;
            if (this.getDatum().equals(dag.getDatum())) {
                return true;
            }
        }
        return false;
    }

}
