package de.thws.lektion17;

import java.io.*;
import java.util.Scanner;
/*
 * Vergleicht drei Kopiermethoden einer Musikdatei:
 *   1. Byteweise mit ungepuffertem Stream
 *   2. Byteweise mit gepuffertem Stream
 *   3. Mit byte[]-Puffer (1024 Bytes) und ungepuffertem Stream
 */
public class StreamZeitmessung {

    private static final int WIEDERHOLUNGEN = 10;
    private static final int PUFFER_GROESSE = 1024;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String quellPfad = frageDateiPfad(scanner);
        scanner.close();

        File quelldatei = new File(quellPfad);

        System.out.println("\n=== Starte Zeitmessungen (" + WIEDERHOLUNGEN + " Durchläufe) ===\n");

        long[] zeitenUngepuffert = new long[WIEDERHOLUNGEN];
        long[] zeitenGepuffert = new long[WIEDERHOLUNGEN];
        long[] zeitenByteArray = new long[WIEDERHOLUNGEN];

        for (int i = 0; i < WIEDERHOLUNGEN; i++) {
            System.out.println("--- Durchlauf " + (i + 1) + " ---");

            zeitenUngepuffert[i] = kopiereUngepuffert(quelldatei, i + 1);
            zeitenGepuffert[i] = kopiereGepuffert(quelldatei, i + 1);
            zeitenByteArray[i] = kopiereByteArray(quelldatei, i + 1);
        }

        druckeStatistik("Ungepuffert (byteweise)", zeitenUngepuffert);
        druckeStatistik("Gepuffert (byteweise)", zeitenGepuffert);
        druckeStatistik("Byte-Array 1024 (ungepuff.)", zeitenByteArray);
        druckeVergleich(zeitenUngepuffert, zeitenGepuffert, zeitenByteArray);
    }

    // -------------------------------------------------------------------------
    // Methode 1: Ungepufferter Stream, byteweise
    // -------------------------------------------------------------------------
    private static long kopiereUngepuffert(File quelle, int durchlauf) {
        File ziel = new File(quelle.getParent(), "kopie_ungepuffert_" + durchlauf + ".mp3");
        long dauer = -1;

        try (FileInputStream fis = new FileInputStream(quelle);
             FileOutputStream fos = new FileOutputStream(ziel)) {

            long start = System.nanoTime();
            int b;
            while ((b = fis.read()) != -1) {
                fos.write(b);
            }
            long end = System.nanoTime();
            dauer = end - start;

            pruefeGroesse(quelle, ziel, "Ungepuffert");
            System.out.printf("  [1] Ungepuffert:   %,12d ns%n", dauer);

        } catch (FileNotFoundException e) {
            System.err.println("  Datei nicht gefunden: " + e.getMessage());
            System.err.println("  Bitte stellen Sie sicher, dass der Pfad korrekt ist.");
        } catch (IOException e) {
            System.err.println("  Fehler beim Kopieren (ungepuffert): " + e.getMessage());
        }

        return dauer;
    }

    // -------------------------------------------------------------------------
    // Methode 2: Gepufferter Stream, byteweise
    // -------------------------------------------------------------------------
    private static long kopiereGepuffert(File quelle, int durchlauf) {
        File ziel = new File(quelle.getParent(), "kopie_gepuffert_" + durchlauf + ".mp3");
        long dauer = -1;

        try (FileInputStream fis = new FileInputStream(quelle);
             BufferedInputStream bis = new BufferedInputStream(fis);
             FileOutputStream fos = new FileOutputStream(ziel);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            long start = System.nanoTime();
            int b;
            while ((b = bis.read()) != -1) {
                bos.write(b);
            }
            long end = System.nanoTime();
            dauer = end - start;

            pruefeGroesse(quelle, ziel, "Gepuffert");
            System.out.printf("  [2] Gepuffert:     %,12d ns%n", dauer);

        } catch (FileNotFoundException e) {
            System.err.println("  Datei nicht gefunden: " + e.getMessage());
            System.err.println("  Bitte stellen Sie sicher, dass der Pfad korrekt ist.");
        } catch (IOException e) {
            System.err.println("  Fehler beim Kopieren (gepuffert): " + e.getMessage());
        }

        return dauer;
    }

    // -------------------------------------------------------------------------
    // Methode 3: Ungepufferter Stream, byte[]-Puffer (1024 Bytes)
    // -------------------------------------------------------------------------
    private static long kopiereByteArray(File quelle, int durchlauf) {
        File ziel = new File(quelle.getParent(), "kopie_bytearray_" + durchlauf + ".mp3");
        long dauer = -1;

        try (FileInputStream fis = new FileInputStream(quelle);
             FileOutputStream fos = new FileOutputStream(ziel)) {

            byte[] puffer = new byte[PUFFER_GROESSE];

            long start = System.nanoTime();
            int gelesenBytes;
            while ((gelesenBytes = fis.read(puffer)) != -1) {
                fos.write(puffer, 0, gelesenBytes);
            }
            long end = System.nanoTime();
            dauer = end - start;

            pruefeGroesse(quelle, ziel, "Byte-Array");
            System.out.printf("  [3] Byte-Array:    %,12d ns%n", dauer);

        } catch (FileNotFoundException e) {
            System.err.println("  Datei nicht gefunden: " + e.getMessage());
            System.err.println("  Bitte stellen Sie sicher, dass der Pfad korrekt ist.");
        } catch (IOException e) {
            System.err.println("  Fehler beim Kopieren (byte[]): " + e.getMessage());
        }

        return dauer;
    }

    // -------------------------------------------------------------------------
    // Hilfsmethoden
    // -------------------------------------------------------------------------

    /**
     * Fragt den Benutzer nach dem Dateipfad und validiert, bis eine lesbare Datei angegeben wird.
     */
    private static String frageDateiPfad(Scanner scanner) {
        while (true) {
            System.out.print("Bitte geben Sie den Pfad zur Musikdatei ein: ");
            String pfad = scanner.nextLine().trim();

            // Entferne mögliche Anführungszeichen (Drag & Drop in Terminal)
            if (pfad.startsWith("\"") && pfad.endsWith("\"")) {
                pfad = pfad.substring(1, pfad.length() - 1);
            }

            File datei = new File(pfad);
            if (!datei.exists()) {
                System.err.println("  Fehler: Die Datei wurde nicht gefunden. Bitte erneut versuchen.");
            } else if (!datei.isFile()) {
                System.err.println("  Fehler: Der Pfad zeigt auf kein reguläres File. Bitte erneut versuchen.");
            } else if (!datei.canRead()) {
                System.err.println("  Fehler: Die Datei kann nicht gelesen werden (fehlende Berechtigung).");
            } else {
                System.out.printf("  Datei gefunden: %s  (%.2f MB)%n",
                        datei.getName(), datei.length() / (1024.0 * 1024.0));
                return pfad;
            }
        }
    }

    /**
     * Vergleicht Dateigröße von Original und Kopie und gibt eine Meldung aus.
     */
    private static void pruefeGroesse(File original, File kopie, String methode) {
        if (kopie.exists() && original.length() == kopie.length()) {
            System.out.printf("      ✔ %s: Größe stimmt überein (%,d Bytes)%n",
                    methode, original.length());
        } else {
            System.err.printf("      ✘ %s: Größenabweichung! Original=%,d, Kopie=%,d%n",
                    methode, original.length(), kopie.exists() ? kopie.length() : -1);
        }
    }

    /**
     * Berechnet und druckt Minimum, Maximum und Durchschnitt für ein Zeitarray.
     */
    private static void druckeStatistik(String bezeichnung, long[] zeiten) {
        long summe = 0, min = Long.MAX_VALUE, max = Long.MIN_VALUE;
        int gueltig = 0;

        for (long z : zeiten) {
            if (z < 0) continue;  // fehlgeschlagene Durchläufe überspringen
            summe += z;
            if (z < min) min = z;
            if (z > max) max = z;
            gueltig++;
        }

        if (gueltig == 0) {
            System.out.printf("%-36s: keine gültigen Messwerte%n", bezeichnung);
            return;
        }

        long avg = summe / gueltig;
        System.out.printf("%n%-36s:%n", bezeichnung);
        System.out.printf("  Durchschnitt: %,14d ns  = %,.1f ms%n", avg, avg / 1_000_000.0);
        System.out.printf("  Minimum:      %,14d ns%n", min);
        System.out.printf("  Maximum:      %,14d ns%n", max);
    }

    /**
     * Druckt den Vergleich (Faktoren und Prozentsätze) zwischen den drei Methoden.
     */
    private static void druckeVergleich(long[] ungepuffert, long[] gepuffert, long[] byteArr) {
        long avgUng = mittelwert(ungepuffert);
        long avgGep = mittelwert(gepuffert);
        long avgByt = mittelwert(byteArr);

        if (avgUng <= 0 || avgGep <= 0 || avgByt <= 0) {
            System.out.println("\nKein Vergleich möglich – unvollständige Messwerte.");
            return;
        }

        System.out.println("\n=== Vergleich der Methoden ===");
        System.out.printf("Gepuffert ist %.1fx schneller als Ungepuffert%n",
                (double) avgUng / avgGep);
        System.out.printf("Byte-Array ist %.1fx schneller als Ungepuffert%n",
                (double) avgUng / avgByt);
        System.out.printf("Byte-Array ist %.1fx schneller als Gepuffert%n",
                (double) avgGep / avgByt);

        System.out.printf("%nZeitersparnis Gepuffert vs. Ungepuffert: %.1f %%%n",
                (1.0 - (double) avgGep / avgUng) * 100);
        System.out.printf("Zeitersparnis Byte-Array vs. Ungepuffert: %.1f %%%n",
                (1.0 - (double) avgByt / avgUng) * 100);
        System.out.printf("Zeitersparnis Byte-Array vs. Gepuffert:   %.1f %%%n",
                (1.0 - (double) avgByt / avgGep) * 100);
    }

    private static long mittelwert(long[] zeiten) {
        long summe = 0;
        int gueltig = 0;
        for (long z : zeiten) {
            if (z >= 0) {
                summe += z;
                gueltig++;
            }
        }
        return gueltig > 0 ? summe / gueltig : -1;
    }
}