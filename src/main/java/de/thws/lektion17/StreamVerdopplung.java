package de.thws.lektion17;

import java.io.*;

public class StreamVerdopplung extends OutputStream {
    private final OutputStream stream1;
    private final OutputStream stream2;

    // -------------------------------------------------------------------------
    // a) Konstruktor
    // -------------------------------------------------------------------------

    /**
     * Erstellt einen OutputStreamDoubler, der alle Schreibvorgänge
     * an beide übergebenen Streams weiterleitet.
     *
     * @param stream1 erster Ziel-OutputStream
     * @param stream2 zweiter Ziel-OutputStream
     */
    public StreamVerdopplung(OutputStream stream1, OutputStream stream2) {
        this.stream1 = stream1;
        this.stream2 = stream2;
    }

    // -------------------------------------------------------------------------
    // b) close()
    // -------------------------------------------------------------------------

    /**
     * Schließt beide Streams. Tritt beim Schließen eines Streams eine
     * IOException auf, wird dennoch versucht, den anderen zu schließen.
     * Wenn mindestens eine Exception aufgetreten ist, wird eine neue
     * IOException geworfen, die angibt, welcher Stream(s) betroffen war(en).
     */
    @Override
    public void close() throws IOException {
        IOException fehler1 = null;
        IOException fehler2 = null;

        try {
            stream1.close();
        } catch (IOException e) {
            fehler1 = e;
            System.err.println("Fehler beim Schließen von Stream 1: " + e.getMessage());
        }

        try {
            stream2.close();
        } catch (IOException e) {
            fehler2 = e;
            System.err.println("Fehler beim Schließen von Stream 2: " + e.getMessage());
        }

        if (fehler1 != null && fehler2 != null) {
            IOException kombiniert = new IOException(
                    "Fehler beim Schließen von Stream 1 UND Stream 2: "
                            + fehler1.getMessage() + " | " + fehler2.getMessage());
            kombiniert.addSuppressed(fehler1);
            kombiniert.addSuppressed(fehler2);
            throw kombiniert;
        } else if (fehler1 != null) {
            throw new IOException("Fehler beim Schließen von Stream 1: " + fehler1.getMessage(), fehler1);
        } else if (fehler2 != null) {
            throw new IOException("Fehler beim Schließen von Stream 2: " + fehler2.getMessage(), fehler2);
        }
    }

    // -------------------------------------------------------------------------
    // c) write(int b)
    // -------------------------------------------------------------------------

    /**
     * Schreibt ein Byte in beide Streams. Eine IOException in einem Stream
     * verhindert nicht das Schreiben in den anderen – der Stack-Trace wird
     * auf der Konsole ausgegeben und die Verarbeitung wird fortgesetzt.
     *
     * @param b das zu schreibende Byte (als int, nur die unteren 8 Bits)
     */
    @Override
    public void write(int b) throws IOException {
        try {
            stream1.write(b);
        } catch (IOException e) {
            System.err.println("Fehler beim Schreiben in Stream 1:");
            e.printStackTrace();
        }

        try {
            stream2.write(b);
        } catch (IOException e) {
            System.err.println("Fehler beim Schreiben in Stream 2:");
            e.printStackTrace();
        }
    }

    // -------------------------------------------------------------------------
    // main-Methode zur Demonstration
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        // try-with-resources schließt den StreamVerdopplung automatisch,
        // was intern auch stream1 und stream2 schließt.
        try (FileOutputStream fos1 = new FileOutputStream("file1.txt");
             FileOutputStream fos2 = new FileOutputStream("file2.txt");
             StreamVerdopplung doubler = new StreamVerdopplung(fos1, fos2)) {

            // Beliebige Bytefolge: "Hallo, Welt!\n"
            byte[] nachricht = "Hallo, Welt!\n".getBytes();
            doubler.write(nachricht);

            // Zusätzlich einzelne Bytes demonstrieren
            doubler.write('A');
            doubler.write('B');
            doubler.write('C');
            doubler.write('\n');

            System.out.println("Daten erfolgreich in file1.txt und file2.txt geschrieben.");

        } catch (IOException e) {
            System.err.println("Fehler im Hauptprogramm: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
