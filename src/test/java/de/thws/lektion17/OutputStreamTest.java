package de.thws.lektion17;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class OutputStreamTest {

    //Gegeben sei ein OutputStream mit der folgenden write‐Methode:
    //public void write(byte b[], int off, int len) throws IOException
    //{ … }

//     Einschränkungen:
//            1. b != null
//            2. off >= 0
//            3. len >= 0
//            4. off + len <= b.length
//            5. off + len darf keinen Integer-Overflow produzieren


    @TempDir
    Path tempDir;

    private FileOutputStream fos;

    @BeforeEach
    void setUp() throws IOException {
        File file = tempDir.resolve("test.bin").toFile();
        fos = new FileOutputStream(file);
    }

    @AfterEach
    void tearDown() throws IOException {
        fos.close();
    }

    // ---------------------------------------------------------------
    // Positiv-Test: gültige Parameter sollen ohne Exception schreiben
    // ---------------------------------------------------------------

    @Test
    void validParameters_shouldWriteWithoutException() {
        byte[] buf = {1, 2, 3, 4, 5};
        assertDoesNotThrow(() -> fos.write(buf, 0, 5));
    }

    @Test
    void validParameters_offsetAndLen_shouldWriteWithoutException() {
        byte[] buf = {1, 2, 3, 4, 5};
        assertDoesNotThrow(() -> fos.write(buf, 2, 3));
    }

    @Test
    void validParameters_lenZero_shouldWriteWithoutException() {
        byte[] buf = {1, 2, 3};
        // len=0 ist erlaubt: nichts wird geschrieben, kein Fehler
        assertDoesNotThrow(() -> fos.write(buf, 0, 0));
    }

    // ---------------------------------------------------------------
    // Negativ-Tests: jede Verletzung soll eine Exception auslösen
    // ---------------------------------------------------------------

    /** Regel 1: b darf nicht null sein */
    @Test
    void nullArray_shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> fos.write(null, 0, 1));
    }

    /** Regel 2: off muss >= 0 sein */
    @Test
    void negativeOffset_shouldThrowIndexOutOfBoundsException() {
        byte[] buf = {1, 2, 3};
        assertThrows(IndexOutOfBoundsException.class,
                () -> fos.write(buf, -1, 2));
    }

    /** Regel 3: len muss >= 0 sein */
    @Test
    void negativeLength_shouldThrowIndexOutOfBoundsException() {
        byte[] buf = {1, 2, 3};
        assertThrows(IndexOutOfBoundsException.class,
                () -> fos.write(buf, 0, -1));
    }

    /** Regel 4a: off + len > b.length (Überschreitung am Ende) */
    @Test
    void offsetPlusLengthExceedsArray_shouldThrowIndexOutOfBoundsException() {
        byte[] buf = {1, 2, 3}; // length = 3
        // off=1, len=3 → 1+3=4 > 3
        assertThrows(IndexOutOfBoundsException.class,
                () -> fos.write(buf, 1, 3));
    }

    /** Regel 4b: off selbst liegt bereits hinter dem Array-Ende */
    @Test
    void offsetBeyondArrayLength_shouldThrowIndexOutOfBoundsException() {
        byte[] buf = {1, 2, 3}; // length = 3
        // off=5 >= length=3
        assertThrows(IndexOutOfBoundsException.class,
                () -> fos.write(buf, 5, 0));
    }

    /** Regel 5: off + len produziert Integer-Overflow (ergibt negatives Ergebnis) */
    @Test
    void offsetPlusLengthOverflow_shouldThrowIndexOutOfBoundsException() {
        byte[] buf = new byte[10];
        // Integer.MAX_VALUE + 1 → Overflow auf negative Zahl
        assertThrows(IndexOutOfBoundsException.class,
                () -> fos.write(buf, 1, Integer.MAX_VALUE));
    }
}
