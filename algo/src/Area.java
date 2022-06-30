import java.awt.*;

public class Area {
    public  char[] areaArray = new char[9];

    public Area() {
        this.draw();
    }

    public void draw() {
        for (int i = 0; i < 3; i++) {
            System.out.print(" ___");
        }
        System.out.println("");
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if(this.areaArray[i + j * 3 ] !='X' && this.areaArray[i + j * 3 ]  !='O' ) {
                    System.out.print("|___");
                } else {
                    System.out.print("|_"+this.areaArray[i + (j) * 3 ]+"_");
                }
            }
            System.out.print("|");
            System.out.println("");
        }
    }

    public boolean setSymbol(int i, char c) {
        if (this.areaArray[i] != 'X' && this.areaArray[i] != 'O') {
            this.areaArray[i] = c;
        } else {
            return false;
        }
        return true;
    }
}