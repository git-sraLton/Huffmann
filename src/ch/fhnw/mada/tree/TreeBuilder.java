package ch.fhnw.mada.tree;

import ch.fhnw.mada.code.Code;
import ch.fhnw.mada.code.Table;

import java.util.*;

public class TreeBuilder {

    private Table table;

    public TreeBuilder() {
    }

    public Table fillTable(Table table, Leaf leaf) {
        this.table = table;
        walkThrough(leaf, "");
        return table;
    }

    private void walkThrough(Leaf leaf, String code) {
        if (leaf.getZero() != null) {
            walkThrough(leaf.getZero(), code + "0");
        } else {
            table.setCode(leaf.getAscii(), code);
        }
        if (leaf.getOne() != null) {
            walkThrough(leaf.getOne(), code + "1");
        } else {
            table.setCode(leaf.getAscii(), code);
        }
    }

    public Leaf buildTree(Table table) {
        Collection<Code> values = table.getCodes().values();
        ArrayList<Code> codes = new ArrayList<>(values);
        codes.sort(Code::compareTo);
        ArrayList<Leaf> leaves = new ArrayList<>();
        while (!codes.isEmpty()) {
            leaves.add(generateLeafPair(codes.remove(0), codes.remove(0)));
            if (codes.size() == 1) {
                leaves.add(new Leaf(codes.remove(0).getAscii(), null, null));
            }
        }
        return getTopLeaf(leaves);
    }

    public Leaf buildTreeFromCodes(Table table) {
        Collection<Code> values = table.getCodes().values();
        ArrayList<Code> codes = new ArrayList<>(values);
        Leaf topLeaf = new Leaf(-1, null, null);
        while (!codes.isEmpty()) {
            Leaf currentLeaf = topLeaf;
            Code code = codes.remove(0);
            char[] characters = code.getCode().toCharArray();
            for (int i = 0; i < code.getCode().length(); i++) {
                if (characters[i] == '1') {
                    if (currentLeaf.getOne() == null) {
                        Leaf newLeaf = new Leaf(-1, null, null);
                        currentLeaf.setOne(newLeaf);
                    }
                    currentLeaf = currentLeaf.getOne();
                } else if (characters[i] == '0') {
                    if (currentLeaf.getZero() == null) {
                        Leaf newLeaf = new Leaf(-1, null, null);
                        currentLeaf.setZero(newLeaf);
                    }
                    currentLeaf = currentLeaf.getZero();
                }
            }
            currentLeaf.setAscii(code.getAscii());
        }
        return topLeaf;
    }

    private Leaf getTopLeaf(List<Leaf> leaves) {
        List<Leaf> parents = new ArrayList<>();
        int leavesAmount = leaves.size() * 2;
        if (leaves.get(leaves.size() - 1).getOne() == null) {
            leavesAmount--;
        }
        int power = 0;
        while (Math.pow(2, power) < leavesAmount) {
            power++;
        }
        int listLimit = (int) Math.pow(2, power - 1) / 2;
        if (Math.pow(2, power) == leavesAmount) {
            listLimit = leavesAmount / 2;
        }
        parents.add(0, combineLeaves(leaves.subList(0, listLimit)));
        if (leaves.size() > 1) {
            parents.add(0, getTopLeaf(leaves));
        } else if (leaves.size() == 1) {
            parents.add(0, leaves.remove(0));
        }
        if (parents.size() == 1) {
            return parents.get(0);
        } else {
            return getTopLeaf(parents);
        }
    }

    private Leaf combineLeaves(List<Leaf> leaves) {
        ArrayList<Leaf> nextFloor = new ArrayList<>();
        while (leaves.size() > 1) {
            nextFloor.add(new Leaf(-1, leaves.remove(0), leaves.remove(0)));
        }
        if (nextFloor.size() > 1) {
            return combineLeaves(nextFloor);
        }
        return nextFloor.get(0);
    }

    private Leaf generateLeafPair(Code zero, Code one) {
        Leaf lZero = new Leaf(zero.getAscii(), null, null);
        Leaf lOne = new Leaf(one.getAscii(), null, null);
        return new Leaf(-1, lZero, lOne);
    }
}
