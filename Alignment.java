
class Alignment {
    private String entryX;
    private String entryY;
    private int[][] matrix;

    public Alignment(String entryX, String entryY) {
        this.entryX = entryX;
        this.entryY = entryY;

        matrix = new int[entryX.length() + 1][entryY.length() + 1];
        matrix[0][0] = 0;
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
                    int[] neighbors = new int[] { matrix[ia - 1][ib] - 2, matrix[ia - 1][ib - 1],
                            matrix[ia][ib - 1] - 2 };
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

        return choice;
    }

    public String[] align() {
        String[] result = new String[2];

        // int posX = entryX.length() - 1;
        // int posY = entryY.length() - 1;

        return result;
    }

    public String getX() {
        return entryX;
    }

    public String getY() {
        return entryY;
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

    public static void main(String[] args) {
        Alignment exo = new Alignment("CTTG", "ATCTG");
        testAlpha(exo);
    }

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
    }

}