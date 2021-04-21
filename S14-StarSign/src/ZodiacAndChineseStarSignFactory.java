public class ZodiacAndChineseStarSignFactory extends StarSignFactory {
    @Override
    protected StarSign createStarSign(String name) {
        return new ChineseSign(Integer.parseInt(name));
    }
}
