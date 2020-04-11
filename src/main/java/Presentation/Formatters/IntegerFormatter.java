package Presentation.Formatters;

import javafx.scene.control.TextFormatter;

import java.text.DecimalFormat;
import java.text.ParsePosition;

/**
 * Форматтер для текстового поля, принимающего целочисленные значения.
 */
public class IntegerFormatter extends TextFormatter<Integer> {
    public IntegerFormatter() {
        super(IntegerFormatter::apply);
    }

    private static Change apply(Change c) {
        if (c.getControlNewText().isEmpty()) {
            return c;
        }

        DecimalFormat format = new DecimalFormat("#");

        ParsePosition parsePosition = new ParsePosition(0);
        Object object = format.parse(c.getControlNewText(), parsePosition);

        if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
            return null;
        } else {
            return c;
        }
    }
}
