package ch.fhnw.mada;

import ch.fhnw.mada.code.Decoder;
import ch.fhnw.mada.code.Encoder;
import ch.fhnw.mada.code.Table;
import ch.fhnw.mada.tools.BitStringManager;
import ch.fhnw.mada.tools.FileManager;
import ch.fhnw.mada.tree.Leaf;
import ch.fhnw.mada.tree.TreeBuilder;

import java.util.Base64;

public class Main {

    public static void main(String[] args) {
        Table table = new Table();
        String text = FileManager.readFile("data/input/text.txt");

        // add all chars from file into table and count them
        text.chars().forEach(table::addChar);

        // build the tree and write the generated codes to the table
        TreeBuilder treeBuilder = new TreeBuilder();
        table = treeBuilder.fillTable(table, treeBuilder.buildTree(table));

        // write table to dec_tab.txt
        FileManager.writeFile("data/output/dec_tab.txt", table.toString());
        // write bitString as bytes to output.dat
        FileManager.writeByteArray("data/output/output.dat", BitStringManager.bitStringToBytes(Encoder.encode(table, text)));


        // Aufgabe 11
        String tableString = FileManager.readFile("data/input/dec_tab-mada.txt");
        Table givenTable = new Table(tableString);
        Leaf topLeaf = treeBuilder.buildTreeFromCodes(givenTable);
        String encoded = BitStringManager.bytesToString(FileManager.readByteArray("data/input/output-mada.dat"));
        FileManager.writeFile("data/output/given-text.txt", Decoder.decode(topLeaf, encoded));

    }
}
