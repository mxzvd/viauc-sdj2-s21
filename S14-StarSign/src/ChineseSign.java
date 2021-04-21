public class ChineseSign extends StarSign {

    private int year;
    public static final String[] NAMES = {
            "Monkey",
            "Rooster",
            "Dog",
            "Pig",
            "Rat",
            "Ox",
            "Tiger",
            "Rabbit",
            "Dragon",
            "Snake",
            "Horse",
            "Sheep"
    };

    public ChineseSign(int year) {
        super(getName(year));
        this.year = year;
    }

    public String getYears(int count, int fromYear) {
        String sign = getName(fromYear) + " ";
        StringBuilder toReturn = new StringBuilder(sign);
        count += fromYear;
        while (++fromYear <= count) {
            toReturn.append(sign);
        }
        return toReturn.toString();
    }

    public String getDescription() {
        return new String[] {
                "fun, energetic, and active",
                "independent, practical, hard-working, and observant",
                "patient, diligent, generous, faithful, and kind",
                "loving, tolerant, honest, and appreciative of luxury",
                "quick-witted, smart, charming, and persuasive",
                "patient, kind, stubborn, and conservative",
                "authoritative, emotional, courageous, and intense",
                "popular, compassionate, and sincere",
                "energetic, fearless, warm-hearted, and charismatic",
                "charming, gregarious, introverted, generous, and smart",
                "energetic, independent, impatient, and enjoy traveling",
                "mild-mannered, shy, kind, and peace-loving"
        }[year % 12];
    }

    private static String getName(int year) {
        return NAMES[year % 12];
    }
}