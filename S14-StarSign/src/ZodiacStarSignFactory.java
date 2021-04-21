public class ZodiacStarSignFactory extends StarSignFactory {
    @Override
    protected StarSign createStarSign(String name) {
        return new ZodiacSign(name);
    }
}
