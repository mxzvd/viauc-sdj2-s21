package utility;

import javafx.util.StringConverter;

// Used in the OverviewViewController to bind the critical values from strings to actual floats in the viewmodel.
public class DoubleStringConverter extends StringConverter<Number> {

    @Override public String toString(Number number) {
        return number == null || number.intValue() == 0 ? "" : number.toString();
    }

    @Override public Number fromString(String s) {
        try {
            return s.isEmpty() ? Double.NaN : Double.parseDouble(s);
        } catch (Exception e) {
            return Double.NaN;
        }
    }
}
