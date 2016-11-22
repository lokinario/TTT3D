/**
 * Assignment #4.
 * This program is a 4x4x4 Tic Tac Toe game
 * Player v.s. Computer
 *
 * Author: Robert Loquinario (rloquina@ucsc.edu) 
 */



import java.util.*;

public class TTT3D {
  static int board[][][] = new int[4][4][4];
  static int[] sums = new int[76];
    static final int[][][] lines = {
  {{0,0,0},{0,0,1},{0,0,2},{0,0,3}},  //lev 0; row 0   rows in each level     
  {{0,1,0},{0,1,1},{0,1,2},{0,1,3}},  //       row 1     
  {{0,2,0},{0,2,1},{0,2,2},{0,2,3}},  //       row 2     
  {{0,3,0},{0,3,1},{0,3,2},{0,3,3}},  //       row 3     
  {{1,0,0},{1,0,1},{1,0,2},{1,0,3}},  //lev 1; row 0     
  {{1,1,0},{1,1,1},{1,1,2},{1,1,3}},  //       row 1     
  {{1,2,0},{1,2,1},{1,2,2},{1,2,3}},  //       row 2     
  {{1,3,0},{1,3,1},{1,3,2},{1,3,3}},  //       row 3     
  {{2,0,0},{2,0,1},{2,0,2},{2,0,3}},  //lev 2; row 0     
  {{2,1,0},{2,1,1},{2,1,2},{2,1,3}},  //       row 1     
  {{2,2,0},{2,2,1},{2,2,2},{2,2,3}},  //       row 2       
  {{2,3,0},{2,3,1},{2,3,2},{2,3,3}},  //       row 3     
  {{3,0,0},{3,0,1},{3,0,2},{3,0,3}},  //lev 3; row 0     
  {{3,1,0},{3,1,1},{3,1,2},{3,1,3}},  //       row 1 
  {{3,2,0},{3,2,1},{3,2,2},{3,2,3}},  //       row 2       
  {{3,3,0},{3,3,1},{3,3,2},{3,3,3}},  //       row 3           
  {{0,0,0},{0,1,0},{0,2,0},{0,3,0}},  //lev 0; col 0   columns in each level  
  {{0,0,1},{0,1,1},{0,2,1},{0,3,1}},  //       col 1    
  {{0,0,2},{0,1,2},{0,2,2},{0,3,2}},  //       col 2    
  {{0,0,3},{0,1,3},{0,2,3},{0,3,3}},  //       col 3    
  {{1,0,0},{1,1,0},{1,2,0},{1,3,0}},  //lev 1; col 0     
  {{1,0,1},{1,1,1},{1,2,1},{1,3,1}},  //       col 1    
  {{1,0,2},{1,1,2},{1,2,2},{1,3,2}},  //       col 2    
  {{1,0,3},{1,1,3},{1,2,3},{1,3,3}},  //       col 3    
  {{2,0,0},{2,1,0},{2,2,0},{2,3,0}},  //lev 2; col 0     
  {{2,0,1},{2,1,1},{2,2,1},{2,3,1}},  //       col 1    
  {{2,0,2},{2,1,2},{2,2,2},{2,3,2}},  //       col 2    
  {{2,0,3},{2,1,3},{2,2,3},{2,3,3}},  //       col 3    
  {{3,0,0},{3,1,0},{3,2,0},{3,3,0}},  //lev 3; col 0     
  {{3,0,1},{3,1,1},{3,2,1},{3,3,1}},  //       col 1
  {{3,0,2},{3,1,2},{3,2,2},{3,3,2}},  //       col 2
  {{3,0,3},{3,1,3},{3,2,3},{3,3,3}},  //       col 3
  {{0,0,0},{1,0,0},{2,0,0},{3,0,0}},  //cols in vert plane in front
  {{0,0,1},{1,0,1},{2,0,1},{3,0,1}},
  {{0,0,2},{1,0,2},{2,0,2},{3,0,2}},
  {{0,0,3},{1,0,3},{2,0,3},{3,0,3}},
  {{0,1,0},{1,1,0},{2,1,0},{3,1,0}},  //cols in vert plane one back
  {{0,1,1},{1,1,1},{2,1,1},{3,1,1}},
  {{0,1,2},{1,1,2},{2,1,2},{3,1,2}},
  {{0,1,3},{1,1,3},{2,1,3},{3,1,3}},
  {{0,2,0},{1,2,0},{2,2,0},{3,2,0}},  //cols in vert plane two back
  {{0,2,1},{1,2,1},{2,2,1},{3,2,1}},
  {{0,2,2},{1,2,2},{2,2,2},{3,2,2}},
  {{0,2,3},{1,2,3},{2,2,3},{3,2,3}},
  {{0,3,0},{1,3,0},{2,3,0},{3,3,0}},  //cols in vert plane in rear
  {{0,3,1},{1,3,1},{2,3,1},{3,3,1}},
  {{0,3,2},{1,3,2},{2,3,2},{3,3,2}},
  {{0,3,3},{1,3,3},{2,3,3},{3,3,3}},
  {{0,0,0},{0,1,1},{0,2,2},{0,3,3}},  //diags in lev 0
  {{0,3,0},{0,2,1},{0,1,2},{0,0,3}},
  {{1,0,0},{1,1,1},{1,2,2},{1,3,3}},  //diags in lev 1
  {{1,3,0},{1,2,1},{1,1,2},{1,0,3}},
  {{2,0,0},{2,1,1},{2,2,2},{2,3,3}},  //diags in lev 2
  {{2,3,0},{2,2,1},{2,1,2},{2,0,3}},
  {{3,0,0},{3,1,1},{3,2,2},{3,3,3}},  //diags in lev 3
  {{3,3,0},{3,2,1},{3,1,2},{3,0,3}},
  {{0,0,0},{1,0,1},{2,0,2},{3,0,3}},  //diags in vert plane in front
  {{3,0,0},{2,0,1},{1,0,2},{0,0,3}},
  {{0,1,0},{1,1,1},{2,1,2},{3,1,3}},  //diags in vert plane one back
  {{3,1,0},{2,1,1},{1,1,2},{0,1,3}},
  {{0,2,0},{1,2,1},{2,2,2},{3,2,3}},  //diags in vert plane two back
  {{3,2,0},{2,2,1},{1,2,2},{0,2,3}},
  {{0,3,0},{1,3,1},{2,3,2},{3,3,3}},  //diags in vert plane in rear
  {{3,3,0},{2,3,1},{1,3,2},{0,3,3}},
  {{0,0,0},{1,1,0},{2,2,0},{3,3,0}},  //diags left slice      
  {{3,0,0},{2,1,0},{1,2,0},{0,3,0}},        
  {{0,0,1},{1,1,1},{2,2,1},{3,3,1}},  //diags slice one to right
  {{3,0,1},{2,1,1},{1,2,1},{0,3,1}},        
  {{0,0,2},{1,1,2},{2,2,2},{3,3,2}},  //diags slice two to right      
  {{3,0,2},{2,1,2},{1,2,2},{0,3,2}},        
  {{0,0,3},{1,1,3},{2,2,3},{3,3,3}},  //diags right slice      
  {{3,0,3},{2,1,3},{1,2,3},{0,3,3}},        
  {{0,0,0},{1,1,1},{2,2,2},{3,3,3}},  //cube vertex diags
  {{3,0,0},{2,1,1},{1,2,2},{0,3,3}},
  {{0,3,0},{1,2,1},{2,1,2},{3,0,3}},
  {{3,3,0},{2,2,1},{1,1,2},{0,0,3}} };       
  
  public static void main(String[] args){

  for(int i = 0; i < 4; i++){
    for(int j = 0; j < 4; j++){
      for(int k = 0 ; k < 4; k++){
        board[i][j][k] = 0;
      }//end k loop
    }// end j loop
  }//  end i loop
  boolean yes = true;
  while(yes){
    getBoard();
    getPlayerMove();
    compLineSums();
    if(checkPlayerWin()){
      getBoard();
      System.out.println("Congrats you won!");
      yes = false;
    }

    else if(checkCompWin()){
      getBoard();
      System.out.println("Computer wins! Try again next time.");
      yes = false;
    }
    if(isBoardFull()){
      System.out.println("Board full");
      yes = false;
    }
  }

  }//end main

  static boolean isEmpty(int[] celAdr){
    return (board[celAdr[0]][celAdr[1]][celAdr[2]] == 0);
  }

  static void getBoard(){
    for(int a = 3; a>=0;a--){//level for
      for(int b = 3; b>=0; b--){//column
        for(int i = 0; i < b; i++){
          System.out.print(" ");
          }
    System.out.print(a);
    System.out.print(b + "  ");
    for(int c = 0; c<4; c++){//row
      if(board[a][b][c]==0){
        System.out.print("_ ");
      }
      else if(board[a][b][c]==1){
        System.out.print("O ");
      }
      else
        System.out.print("X ");
      }
      System.out.println();
      }
      System.out.println();
    }
    System.out.println("  0 1 2 3");
  }//end getBoard

  static boolean isBoardFull(){
   boolean isFull = true;

    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
        for(int k = 0; k < 4; k++){
          if(board[i][j][k] == 0){
            return false;
          }
        }
      }
    } return isFull;
  }

  static void compLineSums(){
    for (int i=0; i<sums.length; i++){
      sums[i] = 0;
      for (int j=0; j<4; j++){
        sums[i] += board[lines[i][j][0]]
                        [lines[i][j][1]]
                        [lines[i][j][2]];
      }
    }
  } 

  static int findLineSum(int sum){
    for (int i=0; i<sums.length; i++){
      if (sums[i] == sum) return i; // line i has the sum
      }
    return -1; 
  }

  static boolean checkPlayerWin(){

    for(int i=0; i <sums.length; i++){
      if(sums[i] == 20){
        return true;
      }
      }
    return false;
  }

  static boolean checkCompWin(){
    for(int i=0; i <sums.length; i++){
      if(sums[i] == 4){
        return true;
      }
    }
    return false;
  }

  static void getPlayerMove(){
    Scanner scan = new Scanner(System.in); 
    System.out.println("Type your move as one three digit number (lrc)");
    String r =scan.nextLine();
    
    if(r.length() < 4 ){
      int a = Integer.parseInt(r.substring(0,1));
      int b = Integer.parseInt(r.substring(1,2));
      int c = Integer.parseInt(r.substring(2));
      playerMove(a,b,c); 
    }
    else{
      System.out.println("Illegal Move, try again");
      getPlayerMove();
    }

  }//end getPlayerMove

  static void computerMove(){ 
    compLineSums();
    if(isThereWin()){
      for(int cell = 0; cell < 4; cell++){
        if(canMove(lines[findLineSum(3)][cell],0)){
          move(lines[findLineSum(3)][cell],1);
        }
      }
    }
    
    else if(isThereLose()){
      for(int cell = 0; cell < 4; cell++){
        if(canMove(lines[findLineSum(15)][cell],0)){
          move(lines[findLineSum(15)][cell],1);
        }
      }
    }
    else if(isThereFork());
    else if(playLive());
    else 
    randomMove();
  }

  static boolean isThereWin(){
    int a = findLineSum(3);
      if( a > -1){
        return true;
      }
    return false;
  }

  static boolean isThereLose(){
    int b = findLineSum(15);
    
    if( b > -1){
      return true;
    }
    
   return false;
  }

  static boolean isThereFork(){
    for(int a = 0; a < sums.length; a++)
      if(sums[a]==10){
        for(int b = 0; b < sums.length; b++){
          if( a != b && isComMtCel(lines[a],lines[b]) && sums[b]== sums[a]){
            stopPlayerFork();
            return true;
          }
        }
      }
    else if(sums[a]==2){
      for(int b = 0; b < sums.length; b++){
        if( a != b && isComMtCel(lines[a],lines[b]) && sums[b]== sums[a]){
          playFork();
          return true;
        }
      }
    }

   return false;
  }

  static boolean stopPlayerFork(){
    for(int a = 0; a < sums.length; a++)
      if(sums[a]==10){
        for(int b = 0; b < sums.length; b++){
          if( a != b && isComMtCel(lines[a],lines[b]) && sums[b]== sums[a]){
            move(lines[a][findComMtCel(lines[a],lines[b])], 1);
            return true;
          }
        }
      }

    return false;
  }

  static boolean playFork(){
    for(int a = 0; a < sums.length; a++)
      if(sums[a]==2){
        for(int b = 0; b < sums.length; b++){
          if( a != b && isComMtCel(lines[a],lines[b]) && sums[b]== sums[a]){
            move(lines[a][findComMtCel(lines[a],lines[b])], 1);
            return true;
          }
        }
      }
    return false;
  }
  static boolean playLive(){
    for(int i = 0; i < sums.length; i++){
      if(sums[i] < 5 && sums[i] >0){
        move(lines[i][findEmptyCel(lines[i])],1);
        return true;
      }
    }
    return false;
  }

  static int findEmptyCel(int[][]line){
    for (int i=0; i<4; i++){
      if ( isEmpty(line[i]) ) return i;
    }
    return -1;
  }
  
  static boolean isEqual(int[] a, int[] b){
    for (int i=0; i<a.length; i++){
      if (a[i] != b[i]){
        return false;
      }
    }
    return true;
  }
  
  static int findComMtCel(int[][]line1, int[][]line2){
    for (int i=0; i<line1.length; i++ ){
      for (int j=0; j<line1.length; j++ ){
        if (isEqual(line1[i], line2[j]) &&
            isEmpty(line1[i]) &&
            isEmpty(line2[j])) {
            return i;
        }
      }
    }
    return -1;
  }

  static boolean isComMtCel(int[][]line1, int[][]line2){
    for (int i=0; i<line1.length; i++ ){
      for (int j=0; j<line1.length; j++ ){
        if (isEqual(line1[i], line2[j]) && isEmpty(line1[i]) && isEmpty(line2[j])) {
          return true;
        }
      }
    }
    return false;
  }

  static void move(int[] celAdr, int val){
    move(celAdr[0], celAdr[1], celAdr[2], val);
  }

  static void move(int lev, int row, int col, int val){
    board[lev][row][col] = val;
  }

  static boolean canMove(int[]celAdr, int val){
    return canMove(celAdr[0], celAdr[1], celAdr[2],val);
  }

  static boolean canMove(int lev, int row, int col, int val){
    return(board[lev][row][col] == 0);

  }

  static void randomMove(){
    Random random = new Random();
    int a = random.nextInt(4);
    int b = random.nextInt(4);
    int c = random.nextInt(4);
    
    if(board[a][b][c]==0){
      board[a][b][c] = 1;
    }
    else
    randomMove();
  }

  static void playerMove(int a, int b, int c){
    if(board[a][b][c] == 0){
      board[a][b][c] = 5;
      computerMove();
    }
    else{
      System.out.println("Unable to mark that spot, try again");
      getPlayerMove();
    }
  }
  
}//end TTT3D
