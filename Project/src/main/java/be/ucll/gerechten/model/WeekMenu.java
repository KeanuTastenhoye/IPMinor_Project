package be.ucll.gerechten.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "weekMenu")
public class WeekMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id = 0;

    @OneToMany
    @MapKeyColumn(name = "dagnummer")
    private Map<String, DagMenu> week = new HashMap<>();

    public WeekMenu() {}

    public WeekMenu(int id) {
        setid(id);
    }

    public int getid() { return id; }

    public Map<String, DagMenu> getWeek() { return this.week; }

    public void setid(int id) { this.id = id; }

    public void setWeek(List<DagMenu> week) {
        for (DagMenu menu: week) {
            this.addDagInWeekMenu(menu);
        }
    }

    public void addDagInWeekMenu(DagMenu dag) {
        this.week.put(dag.getDag(), dag);
    }

    public void deleteDagVanWeekMenu(DagMenu dag) {
        this.week.remove(dag.getDag());
    }

    public boolean weekMenuIsLeeg(){
        return week.isEmpty();
    }

}