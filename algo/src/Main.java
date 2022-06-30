import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void main(String[] args) {
        Area area = new Area();
        char[] s = {'X', 'O'};
        Player[] allPlayer = new Player[2];
        for (int i = 0; i < 2; i++) {
            System.out.println("Entrez le nom du joueur " + i + " (" + s[i] + ")");
            String n = sc.next();
            allPlayer[i] = new Player(s[i], n);
        }
        int t = 9;
        int i = 0;
        while (t > 0) {
            System.out.println("ligne");
            int l = sc.nextInt();
            System.out.println("Cologne");
            int c = sc.nextInt();
            area.setSymbol(allPlayer[i].play(l, c), allPlayer[i].symbol);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            area.draw();
            i = i == 0 ? 1 : 0;
            t--;
        }
    }
}