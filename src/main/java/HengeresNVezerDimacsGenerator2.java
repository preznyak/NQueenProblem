import java.util.Arrays;

public class HengeresNVezerDimacsGenerator2 {
    private int N;
    private int Q;
    int board[][];

    public HengeresNVezerDimacsGenerator2(int n, int q) {
        board = new int[n][n];
        N = n;
        Q = q;
        initBoard();
    }

    void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print("|" + board[i][j] + "|");
            }
            System.out.println();
        }
    }

    //A board mátrixot használom a sakktábla mezőinek megszámozására, melyek majd a szabályokat adják
    public void initBoard(){
        int num=1;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                board[i][j]=num;
                num+=1;
            }
        }
    }

    //A sorokra és az oszlopokra vonatkozó szabályok generálása
    public void generateAndPrintColAndRowRules(){
        int i,j, k;
        //A sakktábla mezőinek száma
        int nn = N*N;
        //A mezők száma osztva a felrakni kívánt vezérek számával
        int nnpq = nn/Q;
        String simpleRowsRule = "";
        for(i = 1; i<=(N*N); i++){

            simpleRowsRule += String.valueOf(i) + " ";
            if((i%(nnpq)==0 && (nn-i)>=Q) || i==nn){
                simpleRowsRule += " 0\n";
            }
        }
        System.out.println(simpleRowsRule);

        //A sorokra vonatkozó szabályok, miszerint egy sorban csak 1 vezér lehet
        for(i=0; i<N; i++){
            for(j=0;j<N-1; j++)
                for (k=j+1;k<N; k++) {
                    System.out.println(String.valueOf(-1*(board[i][j]))+ " " + String.valueOf(-1*(board[i][k])) + " 0");
                }
        }
        //Az oszlopokra vonatkozó szabályok, miszerint egy oszlopban csak 1 vezér lehet
        for(i=0; i<N; i++){
            for(j=0;j<N; j++)
                for (k=j+1;k<N; k++) {
                    System.out.println(String.valueOf(-1*(board[j][i]))+ " " + String.valueOf(-1*(board[k][i])) + " 0");
                }
        }
    }

    public void generateAndPrintDiagRules(){
        int i,j,k;

        //A bal oldali átlókra vonatkozó szabály hengeres értelemben. Egy átlóban csak 1 vezér lehet.
        // LEFT SIDE
        for(i=0; i<N; i++){
            for(j=0;j<N-1; j++)
                for (k=j+1;k<N; k++) {
                    String negationleftSideDiagRule = "";
                    if(i+j < N) {
                        negationleftSideDiagRule += String.valueOf(-1*(board[j][i+j]))+ " ";
                    } else {
                        negationleftSideDiagRule += String.valueOf(-1*(board[j][(i+j)-N]))+ " ";
                    }
                    if(i+k < N) {
                        negationleftSideDiagRule += String.valueOf(-1*(board[k][i+k]));
                    } else {
                        negationleftSideDiagRule += String.valueOf(-1*(board[k][(i+k)-N]));
                    }
                    negationleftSideDiagRule += " 0";
                    System.out.println(negationleftSideDiagRule);
                }
        }
        // RIGHT SIDE
        //A jobb oldali átlókra vonatkozó szabály hengeres értelemben. Egy átlóban csak 1 vezér lehet.
        for(i=N-1; i>=0; i--){
            for(j=0;j<N-1; j++)
                for (k=j+1;k<N; k++) {
                    String negationrightSideDiagRule = "";
                    if(i-j >= 0) {
                        negationrightSideDiagRule += String.valueOf(-1*(board[j][i-j]))+ " ";
                    } else {
                        negationrightSideDiagRule += String.valueOf(-1*(board[j][N+(i-j)]))+ " ";
                    }
                    if(i-k >= 0) {
                        negationrightSideDiagRule += String.valueOf(-1*(board[k][i-k]));
                    } else {
                        negationrightSideDiagRule += String.valueOf(-1*(board[k][N+(i-k)]));
                    }
                    negationrightSideDiagRule += " 0";
                    System.out.println(negationrightSideDiagRule);
                }
        }
//        System.out.println("THESE WERE THE RIGHT SIDE COMPLEX ONES");



    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public int getQ() {
        return Q;
    }

    public void setQ(int q) {
        Q = q;
    }

    public int sumTilNMinusOne(int number){
        int sum = 0;
        for(int i = 0; i< number; i++){
            sum += i;
        }
        return sum;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public static void main(String[] args){
        int N = 8;
        int Q = 6;
        HengeresNVezerDimacsGenerator2 generator = new HengeresNVezerDimacsGenerator2(N, Q);
        System.out.println("c roller n queen problem");
        int nrOfVariables = N*N;
        //A szabályok számának kiszámítása
        int nrOfRules = nrOfVariables/Q+4*N*generator.sumTilNMinusOne(N);
        System.out.println("p cnf " + nrOfVariables + " " + nrOfRules);

        //A sorokra és oszlopokra vonatkozó szabályok kiíratása
        generator.generateAndPrintColAndRowRules();
        //Az átlókra vonatkozó szabályok kiiratása
        generator.generateAndPrintDiagRules();
    }
}
