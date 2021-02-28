
/*
 *    Să  se  implementeze  folosind  facilităţile  de  sincronizare
 *   specifice  limbajului  Java problema accesului concurent a
 *   scriitorilor şi cititorilor la un fisier. Realizaţi o soluţie
 *   ce oferă acces concurent pentru citire şi exclusiv pentru scriere.
 * */


import java.io.File;

public class Main {

    public static void start() {
        File file = new File("file.txt");

        for (int i = 0; i < 6; i++) {
            Writer writer = new Writer(file, "Writer" + i);
            writer.start();
        }

        for (int i = 0; i < 5; i++) {
            Reader reader = new Reader(file, "Reader" + i);
            reader.start();
        }
    }

    public static void main(String[] args) {
        start();
    }
}
