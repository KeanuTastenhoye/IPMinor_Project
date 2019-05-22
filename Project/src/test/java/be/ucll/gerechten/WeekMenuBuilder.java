package be.ucll.gerechten;

import be.ucll.gerechten.model.WeekMenu;

public class WeekMenuBuilder {

    public WeekMenuBuilder() {

    }

    public static WeekMenuBuilder eenWeekMenu() { return new WeekMenuBuilder(); }

    public static WeekMenuBuilder eenGoedWeekMenu() { return eenWeekMenu(); }

    public WeekMenu build() {
        WeekMenu weekMenu = new WeekMenu();
        return weekMenu;
    }
}
