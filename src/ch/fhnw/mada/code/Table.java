package ch.fhnw.mada.code;

import java.util.HashMap;

public class Table {
    HashMap<Integer, Code> codes;


    public Table() {
        codes = new HashMap<>();
    }

    public Table(String tableString) {
        codes = new HashMap<>();
        String[] charCodes = tableString.split("-");
        for (String charCodeStr: charCodes) {
            String[] charCode = charCodeStr.split(":");
            codes.put(Integer.parseInt(charCode[0]), new Code(Integer.parseInt(charCode[0]), charCode[1]));
        }
    }

    public void addChar(int ascii) {
        if (codes.containsKey(ascii)) {
            codes.get(ascii).increaseCount();
        } else {
            codes.put(ascii, new Code(ascii));
        }
    }

    public HashMap<Integer, Code> getCodes() {
        return codes;
    }

    public void setCode(int ascii, String code) {
        codes.get(ascii).setCode(code);
    }

    public String getCode(int ascii) {
        return codes.get(ascii).getCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int key : codes.keySet()) {
            sb.append(key).append(":").append(codes.get(key).getCode()).append("-");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}
