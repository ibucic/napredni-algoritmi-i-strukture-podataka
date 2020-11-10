public class Player {

    private String playerName;
    private String playerSurname;
    private int position;   // 1: PG, 2: SG, 3: SF, 4: PF, 5: C
    private int overall;
    private int salary;     // salary * 10 000 000

    public Player(String playerName, String playerSurname, int position, int overall, int salary) {
        this.playerName = playerName;
        this.playerSurname = playerSurname;
        this.position = position;
        if (overall < 45)
            this.overall = 45;
        else this.overall = Math.min(overall, 99);
        this.salary = Math.max(salary, 0);
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerSurname() {
        return playerSurname;
    }

    public int getPosition() {
        return position;
    }

    public int getOverall() {
        return overall;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        String playerPosition = null;
        if (position == 1)
            playerPosition = "PG";
        if (position == 2)
            playerPosition = "SG";
        if (position == 3)
            playerPosition = "SF";
        if (position == 4)
            playerPosition = "PF";
        if (position == 5)
            playerPosition = "C";
        return (getPlayerName() + " " + getPlayerSurname() + " " + playerPosition + " --- overall: " + getOverall() + " , salary: " + getSalary());
    }

}
