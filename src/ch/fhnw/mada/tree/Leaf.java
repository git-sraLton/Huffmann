package ch.fhnw.mada.tree;

public class Leaf {
    int ascii;
    Leaf zero;
    Leaf one;

    public Leaf(int ascii, Leaf zero, Leaf one) {
        this.ascii = ascii;
        this.zero = zero;
        this.one = one;
    }

    public int getAscii() {
        return ascii;
    }

    public void setAscii(int ascii) {
        this.ascii = ascii;
    }

    public Leaf getZero() {
        return zero;
    }

    public void setZero(Leaf zero) {
        this.zero = zero;
    }

    public Leaf getOne() {
        return one;
    }

    public void setOne(Leaf one) {
        this.one = one;
    }

}
