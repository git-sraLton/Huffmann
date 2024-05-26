package ch.fhnw.mada.code;

public class Code implements Comparable {
    String code;
    int count;
    int ascii;

    public Code(int ascii) {
        code = null;
        count = 1;
        this.ascii = ascii;
    }

    public Code(int ascii, String code) {
        this.ascii = ascii;
        this.code = code;
    }

    public void increaseCount() {
        count++;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public int getCount() {
        return count;
    }

    public int getAscii() {
        return ascii;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Code) {
            return getCount() - ((Code) o).getCount();
        } else {
            return 1;
        }
    }
}