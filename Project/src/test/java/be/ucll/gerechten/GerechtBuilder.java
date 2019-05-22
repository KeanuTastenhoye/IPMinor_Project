package be.ucll.gerechten;

import be.ucll.gerechten.model.Gerecht;

public class GerechtBuilder {
    //private int id;
    private String description;
    private double price;
    private String type;

    private GerechtBuilder() {

    }

    public static GerechtBuilder eenGerecht() {
        return new GerechtBuilder();
    }

    public static GerechtBuilder eenGoedGerecht() {
        return eenGerecht().metDescription("Wortel soep").metPrice(1).metType("Soep");
    }

    public GerechtBuilder metDescription(String description) {
        this.description = description;
        return this;
    }

    public GerechtBuilder metPrice(double price) {
        this.price = price;
        return this;
    }

    public GerechtBuilder metType(String type) {
        this.type = type;
        return this;
    }

    public Gerecht build() {
        Gerecht gerecht = new Gerecht();
        gerecht.setDescription(description);
        gerecht.setPrice(price);
        gerecht.setType(type);
        return gerecht;
    }
}