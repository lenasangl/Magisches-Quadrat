import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MagischesQuadrat {

    static boolean isMagicSquare(int[][] square) {
        if (square == null || square.length == 0) return false;
        int n = square.length;

        // sicherstellen, dass jede Zeile auch die Länge n hat
        for (int[] row : square) {
            if (row == null || row.length != n) return false;
        }

        // 1. gewünschte Summe/Sollsumme = Summe der ersten Zeile
        int targetSum = 0;
        for (int j = 0; j < n; j++) {
            targetSum += square[0][j];
        }

        // 2. Zeilen prüfen
        for (int i = 1; i < n; i++) {
            int rowSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += square[i][j];
            }
            if (rowSum != targetSum) return false;
        }

        // 3. Spalten prüfen
        for (int j = 0; j < n; j++) {
            int colSum = 0;
            for (int i = 0; i < n; i++) {
                colSum += square[i][j];
            }
            if (colSum != targetSum) return false;
        }

        // 4. Diagonalen prüfen
        int diag1 = 0;
        int diag2 = 0;
        for (int i = 0; i < n; i++) {
            diag1 += square[i][i];
            diag2 += square[i][n - 1 - i];
        }
        if (diag1 != targetSum || diag2 != targetSum) return false;

        // Wenn alles passt
        return true;
    }



    @Test
    public void testIsMagicSquare() {
        int[][] magic3x3 = {
                {4, 9, 2},
                {3, 5, 7},
                {8, 1, 6}
        };
        assertTrue(MagischesQuadrat.isMagicSquare(magic3x3));

        int[][] notMagic = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        assertFalse(MagischesQuadrat.isMagicSquare(notMagic));


        // weitere Tests
        int[][] notMagic2 = {
                {2, 7, 6},
                {9, 5, 1},
                {4, 2, 8} // 2 statt 3 -> Zeile "kaputt"
        };
        assertFalse(MagischesQuadrat.isMagicSquare(notMagic2));

        int[][]notMagic3 = {
                {2, 7, 6},
                {9, 5, 1},
                {8, 1, 6} // erste Spalte 2+9+8=19 und nicht 15 --> ist dann falsch
        };
        assertFalse(MagischesQuadrat.isMagicSquare(notMagic3));

        int[][] notMagic4 = {
                {4, 9, 2},
                {3, 5, 7},
                {8, 1, 5} // letzte Zahl 5 statt 6 --> ebenfalls fehler
        };
        assertFalse(MagischesQuadrat.isMagicSquare(notMagic4));

        int[][] jagged = {
                {1, 2, 3},
                {4, 5}   // kürzere Zeile (2 statt 3 Zahlen)
        };
        assertFalse(MagischesQuadrat.isMagicSquare(jagged));

        int[][] empty = new int[0][0]; // leeres Array --> if (square == null || square.length == 0) return false; --> somit false
        assertFalse(MagischesQuadrat.isMagicSquare(empty));

        int[][] one = {{42}}; //nur ein Wert --> sollte trotzdem true geben
        assertTrue(MagischesQuadrat.isMagicSquare(one));

        assertFalse(MagischesQuadrat.isMagicSquare(null)); //--> null-Fall

        int[][] fourXfour = { // 4x4 Werte
                {16, 2, 3, 13},
                {5, 11, 10, 8},
                {9, 7, 6, 12},
                {4, 14, 15, 1}
        };
        assertTrue(MagischesQuadrat.isMagicSquare(fourXfour));

    }

}