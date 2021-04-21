public class Main {
    public static void main(String[] args) {

        StarSignFactory z = new ZodiacStarSignFactory();
        StarSignFactory c = new ZodiacAndChineseStarSignFactory();

        System.out.println(z.getStarSign("Aquarius"));
        System.out.println(z.getStarSign("Aries"));
        System.out.println(z.getStarSign("Aquarius"));
        System.out.println(z.getStarSign("Gemini"));
        System.out.println(z.getStarSign("Aries"));
        System.out.println(z.getStarSign("Gemini"));

        System.out.println(c.getStarSign("2020"));
        System.out.println(c.getStarSign("2001"));
        System.out.println(c.getStarSign("2020"));
        System.out.println(c.getStarSign("2002"));
        System.out.println(c.getStarSign("2003"));
        System.out.println(c.getStarSign("2002"));
        System.out.println(c.getStarSign("2003"));
    }
}
