public class ZodiacSign extends StarSign {

    private DateInterval interval;
    public static final String[] NAMES = {
            "Capricorn",
            "Aquarius",
            "Pisces",
            "Aries",
            "Taurus",
            "Gemini",
            "Cancer",
            "Leo",
            "Virgo",
            "Libra",
            "Scorpio",
            "Sagittarius"
    };

    public ZodiacSign(String name) {
        super(convertName(name));
    }

    public DateInterval getInterval() {
        return interval;
    }

    public String getDescription() {
        return new String[] {
                "Practical and prudent Ambitious and disciplined Patient and careful Humorous and reserved Pessimistic and fatalistic Miserly and grudging",
                "Friendly and humanitarian Honest and loyal Original and inventive Independent and intellectual Intractable and contrary Perverse and unpredictable Unemotional and detached",
                "Imaginative and sensitive Compassionate and kind Selfless and unworldly Intuitive and sympathetic Escapist and idealistic Secretive and vague Weak-willed and easily led",
                "Adventurous and energetic Pioneering and courageous Enthusiastic and confident Dynamic and quick-witted Selfish and quick-tempered Impulsive and impatient Foolhardy and daredevil",
                "Patient and reliable Warmhearted and loving Persistent and determined Placid and security loving Jealous and possessive Resentful and inflexible Self-indulgent and greedy",
                "Adaptable and versatile Communicative and witty Intellectual and eloquent Youthful and lively Nervous and tense Superficial and inconsistent Cunning and inquisitive",
                "Emotional and loving Intuitive and imaginative Shrewd and cautious Protective and sympathetic Changeable and moody Overemotional and touchy Clinging and unable to let go",
                "Generous and warmhearted Creative and enthusiastic Broad-minded and expansive Faithful and loving Pompous and patronizing Bossy and interfering Dogmatic and intolerant",
                "Modest and shy Meticulous and reliable Practical and diligent Intelligent and analytical Fussy and a worrier Overcritical and harsh Perfectionist and conservative",
                "Diplomatic and urbane Romantic and charming Easygoing and sociable Idealistic and peaceable Indecisive and changeable Gullible and easily influenced Flirtatious and self-indulgent",
                "Determined and forceful Emotional and intuitive Powerful and passionate Exciting and magnetic Jealous and resentful Compulsive and obsessive Secretive and obstinate",
                "Optimistic and freedom-loving Jovial and good-humored Honest and straightforward Intellectual and philosophical Blindly optimistic and careless Irresponsible and superficial Tactless and restless"
        }["apquisriauemaneourubcoag".indexOf(super.getName().substring(1, 3)) / 2];
    }

    private void setDateInterval() {
        int[][][] dates = {
                {{22}, {12}}, {{19}, {1}},
                {{20}, {1}}, {{18}, {2}},
                {{19}, {2}}, {{20}, {3}},
                {{21}, {3}}, {{19}, {4}},
                {{20}, {4}}, {{20}, {5}},
                {{21}, {5}}, {{20}, {6}},
                {{21}, {6}}, {{22}, {7}},
                {{23}, {7}}, {{22}, {8}},
                {{23}, {8}}, {{22}, {9}},
                {{23}, {9}}, {{22}, {10}},
                {{23}, {10}}, {{21}, {11}},
                {{22}, {11}}, {{21}, {12}}
        };
        int index = "apquisriauemaneourubcoag".indexOf(super.getName().substring(1, 3)) / 2;
        interval = new DateInterval(dates[index][0][0], dates[index][0][1], dates[index][1][0], dates[index][1][1]);
    }

    private static String convertName(String name) {
        return NAMES["apquisriauemaneourubcoag".indexOf(name.toLowerCase().substring(1, 3)) / 2];
    }
}
