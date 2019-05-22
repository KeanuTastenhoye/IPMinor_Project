package be.ucll.gerechten.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "gerecht")
public class Gerecht {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String description;

    private double price;

    @NotNull
    private String type;

    public Gerecht(int id, String description, double price, String type) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public Gerecht(String description, double price, String type) {
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public Gerecht() {

    }

    public int getid() { return id; }

    public String getDescription() { return description; }

    public double getPrice() { return price; }

    public String getType() { return type; }

    public void setid(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id is not correct!");
        }
        this.id = id;
    }

    public void setDescription(String description) {
        if (description == null ||
            description.trim().isEmpty() ||
            description.length() < 4 ||
            description.length() > 50) {
            throw new IllegalArgumentException("Description is not correct!");
        }
        this.description = description;
    }

    public void setPrice(double price) {
        if (price < 0.1 ||
            price > 10.00) {
            throw new IllegalArgumentException("Price is too small or too big!");
        }
        this.price = price;
    }

    public void setType(String type) {
        if (type == null ||
            type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type is not correct!");
        }
        this.type = type;
    }
}
