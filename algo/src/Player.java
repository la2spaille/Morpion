public class Player {
    public char symbol;
    public String name;

    Player(char s, String n) {
        this.symbol = s;
        this.name = n;
    }

    public int play(int x, int y) {
       return x + (y - 1) * 3 - 1;
    }
}
