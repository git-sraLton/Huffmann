package ch.fhnw.mada.code;

import ch.fhnw.mada.tree.Leaf;

public class Decoder {
    public static String decode(Leaf leaf, String text) {
        StringBuilder sb = new StringBuilder();
        Leaf currentLeaf = leaf;
        for (char character: text.toCharArray()) {
            if (character == '1') {
                if (currentLeaf.getOne().getAscii() > -1) {
                    sb.append((char) currentLeaf.getOne().getAscii());
                    currentLeaf = leaf;
                } else {
                    currentLeaf = currentLeaf.getOne();
                }
            }
            if (character == '0') {
                if (currentLeaf.getZero().getAscii() > -1) {
                    sb.append((char) currentLeaf.getZero().getAscii());
                    currentLeaf = leaf;
                } else {
                    currentLeaf = currentLeaf.getZero();
                }
            }
        }
        return sb.toString();
    }
}
