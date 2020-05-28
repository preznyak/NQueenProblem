import java.util.Arrays;

public class HengeresNVezerDimacsGenerator {
    private int N;
    private int Q;
    int board[][];
    private int counter = 0;

    public HengeresNVezerDimacsGenerator(int n, int q) {
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

    public void initBoard(){
        int num=1;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                board[i][j]=num;
                num+=1;
            }
        }
    }

    public void printColAndRowRules(){
        int i,j, k;
        for(i=0; i<N; i++){
            String simpleRowRule = "";
            String simpleColRule = "";
            for(j=0; j<N; j++){
                simpleRowRule += String.valueOf(board[i][j]);
                simpleRowRule += " ";
                simpleColRule += String.valueOf(board[j][i]);
                simpleColRule += " ";
            }
            simpleColRule += "0";
            simpleRowRule += "0";
            System.out.println(simpleRowRule);
            counter++;
            System.out.println(simpleColRule);
            counter++;
        }

        for(i=0; i<N; i++){
            for(j=0;j<N-1; j++)
                for (k=j+1;k<N; k++) {
                    String negationRowRule = String.valueOf(-1*(board[i][j]))+ " " + String.valueOf(-1*(board[i][k])) + " 0";
                    System.out.println(negationRowRule);
                    counter++;
            }
        }

        for(i=0; i<N; i++){
            for(j=0;j<N; j++)
                for (k=j+1;k<N; k++) {
                    String negationColRule = String.valueOf(-1*(board[j][i]))+ " " + String.valueOf(-1*(board[k][i])) + " 0";
                    System.out.println(negationColRule);
                    counter++;
                }
        }
//        System.out.println("THESE WERE THE ROW AND COL RULES");
    }

    public void printDiagRules(){
        int i,j,k;
        // Setting the rules for the left side diagonal
        for(i=0; i<N; i++){
            String leftSideDiagRule = "";  // \
            for(j=0; j<N; j++){
                if(j+i <= N-1) {
                    leftSideDiagRule += String.valueOf(board[j][j+i]) + " ";
                }else {
                    leftSideDiagRule += String.valueOf(board[j][(j + i)-N]) + " ";
                }
            }
            leftSideDiagRule += "0";
            System.out.println(leftSideDiagRule);
            counter++;
        }
        //// Setting the rules for the right side diagonal
        for(i=N-1; i>=0; i--){
            String rightSideDiagRule = "";  // \
            for(j=0; j<N; j++){
                if(i-j >= 0) {
                    rightSideDiagRule += String.valueOf(board[j][i-j]) + " ";
                }else {
                    rightSideDiagRule += String.valueOf(board[j][(N + (i-j))]) + " ";
                }
            }
            rightSideDiagRule += "0";
            System.out.println(rightSideDiagRule);
            counter++;
        }

//        System.out.println("THESE WERE THE LEFT AND RIGHT DIAG SIMPLE ONES");
        ///// LEFT SIDE
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
                    counter++;
                }
        }
//        System.out.println("THESE WERE THE LEFT SIDE COMPLEX ONES");
        /////// RIGHT SIDE
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
                    counter++;
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

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
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
        int N = 5;
        int Q = 2;
        HengeresNVezerDimacsGenerator generator = new HengeresNVezerDimacsGenerator(N, Q);
        System.out.println("c roller n queen problem");
        int nrOfRules = 4*N+4*N*generator.sumTilNMinusOne(N);
        System.out.println("p cnf " + nrOfRules);
        generator.printColAndRowRules();
        generator.printDiagRules();
//        generator.printBoard();
        System.out.println(16 / 3);
    }
}
