package utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CharCheck {

    public static final String[] ALL_SINGLE_PUNCTS_ARRAY = {"+", "-", "*", "/", "<", ">", "(", ")", "[", "]", "{", "}", ",", ";", "="};
    public static final String[] ALL_DOUBLE_PUNCTS_ARRAY = {"==", "!=", ">=", "<="};

    public static final Set<String> ALL_SINGLE_PUNCTS_SET = new HashSet<>(List.of(ALL_SINGLE_PUNCTS_ARRAY));
    public static final Set<String> ALL_DOUBLE_PUNCTS_SET = new HashSet<>(List.of(ALL_DOUBLE_PUNCTS_ARRAY));

    public static boolean isBlank(String currChar) {
        return currChar.isBlank();
    }

    public static boolean isDigit(String currChar) {
        return Character.isDigit(currChar.charAt(0));
    }

    public static boolean isIdent1(String currChar) {
        char c = currChar.charAt(0);
        return Character.isLetter(c);
    }

    public static boolean isIdent2(String currChar) {
        char c = currChar.charAt(0);
        return Character.isLetterOrDigit(c);
    }

    public static boolean isDoublePunct(String text, int position) {
        if (position + 1 >= text.length()) return false;
        String startOfTwoChar = text.substring(position, position + 1);
        return ALL_DOUBLE_PUNCTS_SET.contains(startOfTwoChar);
    }

    public static boolean isSinglePunct(String currChar) {
        return ALL_SINGLE_PUNCTS_SET.contains(currChar);
    }

}
