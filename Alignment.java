
class Alignment {
    private String entryX;
    private String entryY;
    private int[][] matrix;

    private int[][] direction;
    private String[] alignResult;
    private int scoreResult = 0;

    public Alignment(String entryX, String entryY) {
        this.entryX = entryX;
        this.entryY = entryY;

        matrix = new int[entryX.length() + 1][entryY.length() + 1];
        matrix[0][0] = 0;

        direction = new int[entryX.length() + 1][entryY.length() + 1];
        for (int ia = 0; ia < direction.length; ia++) {
            direction[ia][0] = -1;
        }
        for (int ib = 0; ib < direction[0].length; ib++) {
            direction[0][ib] = -1;
        }

        fillGrid();

    }

    // public void fillGrid() {
    // for (int ia = 1; ia < matrix.length; ia++) {
    // for (int ib = 1; ib < matrix[0].length; ib++) {
    // int[] neighbors = new int[] { matrix[ia - 1][ib] - 2, matrix[ia - 1][ib - 1],
    // matrix[ia][ib - 1] - 2 };
    // matrix[ia][ib] = baseScore(neighbors, ia - 1, ib - 1);
    // }
    // }
    // }

    public void fillGrid() {
        for (int ia = 0; ia < matrix.length; ia++) {
            for (int ib = 0; ib < matrix[0].length; ib++) {
                if (ia == 0)
                    matrix[ia][ib] = -(ib * 2);
                if (ib == 0)
                    matrix[ia][ib] = -(ia * 2);
                if (ia > 0 && ib > 0) {
                    int[] neighbors = new int[] { matrix[ia][ib - 1] - 2, matrix[ia - 1][ib - 1],
                            matrix[ia - 1][ib] - 2 };
                    matrix[ia][ib] = baseScore(neighbors, ia - 1, ib - 1);
                }
            }
        }
    }

    public int baseScore(int[] neighbors, int x, int y) {
        // neighbors of the current box we are working on.
        // array size of 3. 0 is left. 1 is up-left. 2 is up.

        if (entryX.charAt(x) == entryY.charAt(y))
            neighbors[1] += 1;
        else
            neighbors[1] -= 1;

        int choice = Math.max(Math.max(neighbors[0], neighbors[1]), neighbors[2]);
        if (choice == neighbors[1])
            direction[x + 1][y + 1] = 1;
        else if (choice == neighbors[0]) // TENDENCY BIAS: TOP-LEFT > LEFT> TOP
            direction[x + 1][y + 1] = 0;
        else
            direction[x + 1][y + 1] = 2;

        return choice;
    }

    public String[] align() {
        String[] result = new String[2];
        result[0] = "";
        result[1] = "";

        int posX = entryX.length() - 1;
        int posY = entryY.length() - 1;

        while (posX >= 0 && posY >= 0) {
            int arrow = direction[posX + 1][posY + 1];
            if (arrow == 0) {
                result[0] = "-" + result[0];
                result[1] = entryY.charAt(posY) + result[1];
                posY--;
            } else if (arrow == 1) {
                result[0] = entryX.charAt(posX) + result[0];
                result[1] = entryY.charAt(posY) + result[1];
                posX--;
                posY--;
            } else if (arrow == 2) {
                result[0] = entryX.charAt(posX) + result[0];
                result[1] = "-" + result[1];
                posX--;
            } else {
                System.out.println("!!>>ALERT<<!!");
            }
        }

        alignResult = result;
        scoreResult = score();

        return result;
    }

    private int score() {
        int score = 0;
        for (int ia = 0; ia < alignResult[0].length(); ia++) {
            if (alignResult[0].charAt(ia) == alignResult[1].charAt(ia))
                score++;
            else if (alignResult[0].charAt(ia) == '-' || alignResult[1].charAt(ia) == '-')
                score -= 2;
            else if (alignResult[0].charAt(ia) != alignResult[1].charAt(ia))
                score--;
        }
        return score;
    }

    public String getX() {
        return entryX;
    }

    public String getY() {
        return entryY;
    }

    public int getScore() {
        return scoreResult;
    }

    public int[][] getGrid() {
        return matrix;
    }

    public void setX(String x) {
        this.entryX = x;
    }

    public void setY(String y) {
        this.entryY = y;
    }

    // public static void main(String[] args) {
    // Alignment exo = new Alignment("CTTG", "ATCTG");
    // // Alignment exo = new Alignment("CGG", "CAAGT");
    // testAlpha(exo);

    // // int[][] testArr = exo.direction;
    // // for (int ia = 0; ia < testArr.length; ia++) {
    // // System.out.println();
    // // for (int ib = 0; ib < testArr[0].length; ib++)
    // // System.out.print(testArr[ia][ib] + " ");
    // // }
    // // System.out.println();

    // System.out.println("-----RESULTS-----");
    // String[] results = exo.align();
    // System.out.println(results[0]);
    // System.out.println(results[1]);
    // }

    private static void testAlpha(Alignment exo) {
        int[][] grid = exo.getGrid();
        System.out.println();
        System.out.print("      ");
        for (int ia = 0; ia < grid[0].length - 1; ia++)
            System.out.print(exo.getY().charAt(ia) + " ");
        for (int ia = 0; ia < grid.length; ia++) {
            System.out.println();
            if (ia > 0)
                System.out.print(exo.getX().charAt(ia - 1) + " ");
            else
                System.out.print("   ");
            for (int ib = 0; ib < grid[0].length; ib++) {
                System.out.print(grid[ia][ib] + " ");
            }
        }
        System.out.println();
    }

}