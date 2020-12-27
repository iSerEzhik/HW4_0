import com.sun.org.apache.bcel.internal.generic.GotoInstruction;

import java.util.Random;
import java.util.Scanner;

public class tikToe {
    public static final int size = 5;
    public static final int forWin=4;
    public static final char empty = '*';
    public static final char circle = 'O';
    public static final char cross = 'X';
    public static final char[][] map = new char[size][size];
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        newMap();
        printMap();
        int winner=0;
        System.out.println("Карта: ");
        do{

            humanStep();
            System.out.println("Карта после вашего хода: ");
            printMap();
            if (checkWin(map)) {
                winner=1;
                break;
            }
            robotStep();
            System.out.println("Карта после хода компьютера: ");
            printMap();
            if (checkWin(map)) {
                winner = 0;
                break;
            }

        }while (!checkWin(map));
        if (winner==1) System.out.println("Победило человечество!!!!!");
        else System.out.println("Победил компуктер(((");
    }

    public static void newMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = empty;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i < size + 1; i++) {
            System.out.print(i + " ");
        }

        for (int i = 0; i < size; i++) {
            System.out.print("\n" + (i + 1) + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j] + " ");
            }
        }
        System.out.println();
    }

    public static boolean ifEmpty(int x, int y) {
        if (map[x][y] == empty) {
            return (true);
        } else {
            return (false);
        }
    }

    public static boolean checkWin(char[][] map)
    { int k=1;
        char mainD=empty,extraD=empty,hor=empty,ver=empty;
        boolean bufHor=false; boolean bufVer=false; boolean bufMainD=false;boolean bufExtraD=false;
        for (int i = 0; i <=size-forWin; i++)              //Проверка вертикаль
            for (int j = 0; j <=size-forWin; j++) {
                if (bufVer&&k==4)  return true;
                for (int g = 0; g < forWin-1; g++){
                    if (bufVer&&k==4) return true;
                    for (int v = 0; v < forWin-1; v++)
                        if (map[v + i][g + j] == map[v + i + 1][g + j] && map[v + i][g + j] != empty) {
                            bufVer = true;
                            k++;
                        }
                        else {
                            k=1;
                            bufVer = false;
                            break;
                        }
                }
            }
        if (bufVer&&k==4)  return true;
        else
            k=1;
        for (int i = 0; i <=size-forWin; i++) //проверка горизонталь
            for (int j = 0; j <=size-forWin; j++) {
                if (bufHor&&k==4) return true;
                for (int v = 0; v < forWin-1; v++) {
                    if (bufHor&&k==4) return true;
                    for (int g = 0; g < forWin-1; g++)
                        if (map[v + i][g + j] == map[v + i][g + j + 1] && map[v + i][g + j] != empty) {
                            bufHor = true;
                            k++;
                        }
                        else {
                            k=1;
                            bufHor = false;
                            break;
                        }
                }

            }
        if (bufHor&&k==4) return true;
        else
            k=1;
        for (int i = 0; i <=size-forWin; i++) //проверка главная диагональ
            for (int j = 0; j <= size-forWin; j++) {
                if (bufMainD&&k==4) return true;
                for (int d = 0; d < forWin-1; d++)
                    if (map[d + i][d + j] == map[d + i + 1][d + j + 1] && map[d + i][d + j] != empty) {
                        bufMainD = true;
                        k++;
                    }
                    else
                    {
                        k=1;
                        bufMainD=false;
                        break;

                    }
            }
        if (bufMainD&&k==4) return true;
        else k=1;
        for (int i = 0; i <=size-forWin; i++) //проверка побочная диагональ
            for (int j = 0; j <=size-forWin; j++) {
                if (bufExtraD) return true;
                for (int d = forWin-1;d > 0; d--)
                    if (map[d + i][forWin-1-d + j] == map[d + i - 1][forWin-1-d + j + 1] && map[d + i][forWin-1-d + j] != empty) {
                        bufExtraD = true;
                        k++;
                    }
                    else
                    {
                        k=1;
                        bufExtraD=false;
                        break;
                    }
            }
        if (bufExtraD&&k==4) return true;
        return false;
    }

    public static void humanStep()
    {
        int x, y;
        do {
            System.out.println("Введите координаты клетки (X,Y): ");
            x = scan.nextInt() - 1;
            y = scan.nextInt() - 1;
        }
        while (!ifEmpty(x, y));
        map[x][y] = cross;
    }
    public static int[] nextStepWin(char who)
    {
        int[] kor=new int[2];
        boolean t=false;
        char[][] copyMap=new char[size][size];
        int i=0,j=0;
        for ( i = 0; i < size; i++) {
            for (j = 0; j <size; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        int k=1;
        for ( i = 0; i <size ; i++) {
            if (t==true) break;
            for ( j = 0; j < size; j++) {
                if (copyMap[i][j]==empty)
                    copyMap[i][j]=who;

                else continue;
                if (checkWin(copyMap))
                {
                    t=true;
                    break;
                }
                else copyMap[i][j]=empty;

            }

        }
        if (t==true)
        {
            kor[0]=--i;
            kor[1]=j;
        }
        else{
            kor[0]=-1;
            kor[1]=-1;
        }
        return kor;

    }
    public static int[] findCross()
    {
        int[] kor= new int[2];
        int i=0,j=0;
        while (map[i][j]!=cross)
        {
            j++;
            if (j==size) {
                i++;
                j = 0;
            }
        }
        kor[0]=i;
        kor[1]=j;
        return kor;
    }
    public static boolean ifNextStepWin(int[] kor)
    {
        if (kor[0]!=-1) return true;
        else return false;
    }

    public static void robotStep()
    {
        int[] kor;
        int i,j;
        if (ifNextStepWin(nextStepWin(circle)))
        {
            kor=nextStepWin(circle);
            map[kor[0]][kor[1]]=circle;
            return;
        }
        else if (ifNextStepWin(nextStepWin(cross)))
        {
            kor=nextStepWin(cross);
            map[kor[0]][kor[1]]=circle;
            return;
        }
        else
        {   kor=findCross();
            i=kor[0];
            j=kor[1];
            int k=1;
            while (k<size)
            { if(i-k>0&&j-k>0&&map[i-k][j-k]==empty)
            {
                i=i-k;
                j=j-k;
                break;
            }
            else
            if(i+k<size&&j-k>0&&map[i+k][j-k]==empty)
            {
                i=i+k;
                j=j-k;
                break;
            }
            else
            if(i-k>0&&j+k<size&&map[i-k][j+k]==empty)
            {
                i=i-k;
                j=j+k;
                break;
            }
            else if(i+k<size&&j+k<size&&map[i+k][j+k]==empty)
            {
                i=i+k;
                j=j+k;
                break;
            }
            else if (i+k<size&&map[i+k][j]==empty)
            {
                i=i+k;
                break;
            }

            else if (i-k>0&&map[i-k][j]==empty)
            {
                i=i-k;
                break;
            }

            else if (j+k<size&&map[i][j+k]==empty)
            {
                j+=k;
                break;
            }

            else if (j-k>0&&map[i][j-k]==empty)
            {
                j=j-k;
                break;
            }

            else k++;
            }
            map[i][j]=circle;
        }

    }
}