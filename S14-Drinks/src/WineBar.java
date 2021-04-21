public class WineBar extends Bar {

    private static String[] drinkTypes = {
            "RedWine",
            "RoseWine",
            "WhiteWine"
    };

    @Override protected Drink makeDrink(String name) {
        switch (name) {
            case "RedWine" : return new Beer("RedWine");
            case "RoseWine" : return new Beer("RoseWine");
            case "WhiteWine" : return new Beer("WhiteWine");
            default : return null;
        }
    }

    @Override public String[] getDrinkTypes() {
        return drinkTypes;
    }
}
