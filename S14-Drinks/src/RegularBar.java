public class RegularBar extends Bar {

    private static String[] drinkTypes = {
            "Carlsberg",
            "Corona",
            "RedWine",
            "RoseWine",
            "WhiteWine"
    };

    @Override protected Drink makeDrink(String name) {
        switch (name) {
            case "Carlsberg" : return new Beer("Carlsberg");
            case "Corona" : return new Beer("Corona");
            case "RedWine" : return new Wine("RedWine", "red");
            case "RoseWine" : return new Wine("RoseWine", "rose");
            case "WhiteWine" : return new Wine("WhiteWine", "white");
            default : return null;
        }
    }

    @Override public String[] getDrinkTypes() {
        return drinkTypes;
    }
}
