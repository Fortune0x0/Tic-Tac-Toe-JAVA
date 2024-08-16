import java.util.Scanner;

class Pair {
    int first, second;
   
    Pair(int first, int second){
        this.first = first;
        this.second = second;
        
        
    }
    
    
}


class TicTacToe{
     char [][] board = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
     int [][][] Winner = {{{0, 0}, {0, 1}, {0, 2}},    {{1, 0}, {1, 1}, {1, 2}},    {{2, 0}, {2, 1}, {2, 2}},   {{0, 0}, {1, 1}, {2, 2}},     {{0, 2}, {1, 1}, {2, 0}},     {{0, 0}, {1, 0}, {2, 0}},     {{0, 1}, {1, 1}, {2, 1}},    {{0, 2}, {1, 2}, {2, 2}}};
     int [][] moves = {{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};

     void Screen(){
        for(int i =0; i < 3; ++i){
            for(int j =0; j < 3; ++j){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();

        }
    }
    void RestartGame(){
        for(int row =0; row < 3; ++row){
            for(int col =0; col < 3; ++col){
                board[row][col] =  (char)(((row * 3) + col + 1) + '0');
                
            }
        }
        Game();
    }
    char CheckWinner(){
       
        for(int [][] it : Winner){
            if(board[it[0][0]][it[0][1]] == board[it[1][0]][it[1][1]] && board[it[2][0]][it[2][1]] == board[it[1][0]][it[1][1]]){
                return board[it[0][0]][it[0][1]];

            }
        }
        for(int row =0; row < 3; ++row){
            for(int col  =0; col < 3; ++col){
                char num_2 = (char)(((row * 3) + col + 1) + '0');
                if(board[row][col] == num_2) return 'Q';


            }

        }
        return '\0';
    }
    Pair miniMax (char player, boolean isMaximazing){
        
        char winner = CheckWinner();
        if(winner == 'X')return new Pair (1, 0);
        if(winner == 'O') return new Pair (-1, 0);
        if(winner == '\0') return new Pair (0, 0);

        if(isMaximazing){
            int bestScore = -1000;
            int play = 0;
            for(int row = 0; row < 3; ++row){
                for(int col = 0; col < 3; ++col){
                    if(board[row][col] == (char)(((row * 3) + col + 1) + '0')){
                        board[row][col] = 'X';
                        Pair eval = miniMax('O', false);
                        board[row][col] = (char)(((row * 3) + col + 1) + '0');
                        if(eval.first > bestScore){
                            bestScore = eval.first;
                            play = (row * 3) + col + 1;


                        }

                    }

                }

            }
            Pair final_move = new Pair(bestScore, play);
            return final_move;

        }
        else{
            int bestScore = 1000;
            int play = 0;
            for(int row = 0; row < 3; ++row){
                for(int col = 0; col < 3; ++col){
                    if(board[row][col] == (char)(((row * 3) + col + 1) + '0')){
                        board[row][col] = 'O';
                        Pair eval = miniMax('X', true);
                        board[row][col] = (char)(((row * 3) + col + 1) + '0');
                        if(eval.first < bestScore){
                            bestScore = eval.first;
                            play = (row * 3) + col +1;


                        }

                    }

                }

            }
            Pair final_move = new Pair(bestScore, play);
            return final_move;

        }


    }
    void AiMove(){
        char state = CheckWinner();
        if(state != '\0'){
            Pair Ai = miniMax('O', false);
            for(int [][] it : Winner){
                if(board[it[0][0]][it[0][1]] == board[it[1][0]][it[1][1]] && board[it[0][0]][it[0][1]] == 'O' && board[it[2][0]][it[2][1]] != 'X') Ai.second = (it[2][0] * 3) + it[2][1] +1;                           
                if(board[it[2][0]][it[2][1]] == board[it[0][0]][it[0][1]] && board[it[2][0]][it[2][1]] == 'O' && board[it[1][0]][it[1][1]] != 'X') Ai.second = (it[1][0] * 3) + it[1][1] +1;                       
                if(board[it[2][0]][it[2][1]] == board[it[1][0]][it[1][1]] && board[it[2][0]][it[2][1]] == 'O' && board[it[0][0]][it[0][1]] != 'X') Ai.second = (it[0][0] * 3) + it[0][1] +1;  
            }
            System.out.println("(" + Ai.first + ", " +  Ai.second + ")");
            board[moves[Ai.second-1][0]][moves[Ai.second-1][1]] = 'O';

        }

       
       
        


    }
    void Game(){
        Screen();
        char state = CheckWinner();
        if(state != 'Q'){
            if(state == 'O') System.out.println(state + " is the winner");
            if(state  == '\0') System.out.println("It was a Draw");
            Scanner input = new Scanner(System.in);
            
            System.out.println( "\n" + "Would you like to play again? Y/N");
            char try_again= input.next().trim().charAt(0);
            if(try_again == 'Y' || try_again == 'y') RestartGame();
            else System.exit(0);

        }
        
        System.out.println("Make a play: ");
        Scanner input = new Scanner(System.in);
        int p = input.nextInt();
        board[moves[p-1][0]][moves[p-1][1]] = 'X';
        
        AiMove();
        
        Game();

        
    }
    
  
    
}
class Main{
    public static void main(String [] args){
        TicTacToe tic = new TicTacToe();
        tic.Game();
        
            

    }
}