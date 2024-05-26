package ch.fhnw.mada.tools;

public class BitStringManager {
    public static String bytesToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            // took me hours to fix this -.-
            // https://stackoverflow.com/questions/12310017/how-to-convert-a-byte-to-its-binary-string-representation
            sb.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        }
        boolean found1 = false;
        while (!found1) {
            String lastChar = sb.substring(sb.length()-1);
            sb.deleteCharAt(sb.length()-1);
            if (lastChar.equals("1")) {
                found1 = true;
            }
        }
        return sb.toString();
    }

    public static byte[] bitStringToBytes(String bitString) {
        if (bitString.length()%8 != 0) {
            System.out.println("Error with bitString");
            return new byte[0];
        }
        byte[] bytes = new byte[bitString.length()/8];
        for (int i = 0; i < bitString.length()/8; i++) {
            bytes[i] = (byte) Integer.parseInt(bitString.substring(i*8, i*8+8));
        }
        return bytes;
    }
}
