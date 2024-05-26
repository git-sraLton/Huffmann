package ch.fhnw.mada.code;

import java.util.stream.Collectors;

public class Encoder {

    public static String encode(Table table, String text){
        StringBuilder encoded = new StringBuilder(text.chars().mapToObj(table::getCode).collect(Collectors.joining()));
        encoded.append("1");
        int loop = 8 - (encoded.length() % 8);
        for (int i = 0; i < loop; i++) {
            encoded.append("0");
        }
        return encoded.toString();
    }

}
