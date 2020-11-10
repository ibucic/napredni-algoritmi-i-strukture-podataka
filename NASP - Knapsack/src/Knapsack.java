import java.util.ArrayList;

public class Knapsack {

    public static void main(String[] args) {

        ArrayList<Player> player_set = new ArrayList<>();

        // Adding elements to the set
        player_set.add(new Player("Derrick", "Rose", 1, 85, 1));
        player_set.add(new Player("Damian", "Lillard", 1, 92, 3));
        player_set.add(new Player("Stephen", "Curry", 1, 95, 4));

        player_set.add(new Player("Lou", "Williams", 2, 84, 1));
        player_set.add(new Player("Luka", "Doncic", 2, 93, 1));
        player_set.add(new Player("James", "Harden", 2, 96, 4));

        player_set.add(new Player("Ben", "Simmons", 3, 87, 2));
        player_set.add(new Player("Giannis", "Antetokounmpo", 3, 96, 3));
        player_set.add(new Player("LeBron", "James", 3, 97, 4));

        player_set.add(new Player("Zion", "Williamson", 4, 81, 1));
        player_set.add(new Player("Pascal", "Siakam", 4, 87, 2));
        player_set.add(new Player("Anthony", "Davis", 4, 94, 3));

        player_set.add(new Player("Jusuf", "Nurkic", 5, 83, 1));
        player_set.add(new Player("Rudy", "Gobert", 5, 87, 2));
        player_set.add(new Player("Nikola", "Jokic", 5, 94, 3));

//        for (Player p : player_set) {
//            System.out.println(p.toString());
//        }


        int numberOfPositions = 5;
        int numberOfPlayers = player_set.size();
        int salaryCap = 12;    // 120 000 000
//        System.out.println(numberOfPlayers);
        int[] numberPlayer = new int[]{0, 0, 0, 0, 0};

        int[][] table = new int[numberOfPlayers][salaryCap + 1];
        int[][] keepTable = new int[numberOfPlayers][salaryCap + 1];
//        System.out.println(table.length);
//        System.out.println(table[0].length);
//        for (int[] ints : table) {
//            for (int j = 0; j < table[0].length; j++) {
//                System.out.print(ints[j] + " ");
//            }
//            System.out.print("\n");
//        }

        int row = 0;
        int historyMax = 0;
        int[] playerPosition = new int[]{0, 0, 0, 0, 0}; // broj u kategoriji prije
        int historyMaxOffset = 0; // pomaknut za tezinu
        int newValue; // zbrojeno trenutna + prosla pomaknuta

        for (int i = 1; i < numberOfPositions + 1; i++) {
            for (Player player : player_set) {
                if (i == 1) {
                    if (player.getPosition() == i) {
                        numberPlayer[i - 1] += 1;
                        for (int k = 0; k <= salaryCap; k++) {
                            if (player.getSalary() <= k) {
                                table[row][k] = player.getOverall();
                                keepTable[row][k] = 1;
                            }
                        }
                        row += 1;
                    }
                } else {
                    if (player.getPosition() == i) {
                        numberPlayer[i - 1] += 1;
                        for (int k = 0; k <= salaryCap; k++) {
                            for (int p = 0; p < numberPlayer[i - 2]; p++) {
                                int x = row - (p + 1 + playerPosition[i - 1]);
                                if (historyMax < table[x][k]) {
                                    historyMax = table[x][k];
                                }
                            }
                            if (player.getSalary() > k) {
                                if (historyMax != 0) {
                                    table[row][k] = historyMax;
                                }
                            }
                            if (player.getSalary() <= k) {
                                for (int p = 0; p < numberPlayer[i - 2]; p++) {
                                    int x = row - (p + 1 + playerPosition[i - 1]);
                                    int y = k - player.getSalary();
                                    if (historyMaxOffset < table[x][y]) {
                                        historyMaxOffset = table[x][y];
                                    }
                                }
                                newValue = player.getOverall() + historyMaxOffset;
                                if (historyMax > newValue) {
                                    table[row][k] = historyMax;
                                } else {
                                    table[row][k] = newValue;
                                    keepTable[row][k] = 1;
                                }
                            }
                            historyMax = 0;
                            historyMaxOffset = 0;
                        }
                        row += 1;
                        playerPosition[i - 1] += 1;
                    }
                }
            }
        }

        System.out.println("Players:\n");
//        System.out.println("1 - PG\n2 - SG\n3 - SF\n4 - PF\n5 - C\n\n");
        for (Player p : player_set) {
            System.out.println(p.toString());
        }

        System.out.println("\nKnapsack table:");
        System.out.print("         Player |");
        for (int i = 0; i <= 120; i += 10) {
            System.out.printf("%5d M |", i);
        }
        System.out.print("\n");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        int i = 0;
        while (i < table.length) {
            for (Player p : player_set) {
                System.out.printf("%15s |", p.getPlayerName());
                for (int j = 0; j < table[0].length; j++) {
                    System.out.printf("%7d |", table[i][j]);
                }
                i += 1;
                System.out.print("\n");
                System.out.printf("%15s |", p.getPlayerSurname());
                for (int k = 10; k <= 130; k += 10) {
                    System.out.print("        |");
                }
                System.out.print("\n");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
            }
        }


//        System.out.println("\n");
//        for (int i1 = 0; i1 < keepTable.length; i1++) {
//            for (int j = 0; j < keepTable[0].length; j++)
//                System.out.print(keepTable[i1][j] + "   ");
//            System.out.print("\n");
//        }
//        System.out.println("\n");
//
//        for (int[] ints : table) {
//            for (int j = 0; j < table[0].length; j++) {
//                System.out.print(ints[j] + "   ");
//            }
//            System.out.print("\n");
//        }

        row -= 1;
        int k = salaryCap;
        int numPos = numberOfPositions - 1;
        int bestOverall = table[0][0];
        for (int[] ints : table) {
            for (int y = 0; y < table[0].length; y++) {
                if (ints[y] > bestOverall)
                    bestOverall = ints[y];
            }
        }
        System.out.println("Best overall is: " + bestOverall + "\n");
        System.out.println("\nOptimal result:\n");

        int move = 0;
        while (bestOverall != table[row][k]) {
            row -= 1;
            move++;
        }
//        System.out.println(table[row][k]);

        while (true) {
            if (keepTable[row][k] == 1) {
                System.out.println(player_set.get(row).toString());

                k = k - player_set.get(row).getSalary();
                row = row - numberPlayer[numPos] + move;
                if (move != 0)
                    move = 0;
                numPos -= 1;

                if (row < 0 || k < 0)
                    break;
            } else {
                row -= 1;
                if (row < 0)
                    break;
            }
        }
    }

}
