import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.*;

//This class implements the interface ActionListener so that it is called when the player makes a move. 
//It calculates the next step of the game
//It updates state and userInterface.


public class GameLogic implements ActionListener {

 // ADD YOUR INSTANCE VARIABLES HERE
    private GameState gameState;
    private GameUserInterface gUI;
    private int size;
    private BoardUserInterface bUI;




    public GameLogic(int size) { //The parameter size is the size of the board on which the game will be played
	// YOUR CODE HERE
	// It creates the game's userInterface and the game's state instances
        this.size = size;
        this.gameState = new GameState(size);
        this.gUI = new GameUserInterface(this.gameState, this);
        this.bUI = gUI.getBoardUserInterface();

        
        
    }

  
    /**
     * Starts the game
     */
    public void start(){
	// YOUR CODE HERE
        bUI.update();

    }

 
    /**
     * resets the game
     */
    public void reset(){
	// YOUR CODE HERE
        this.gameState = new GameState(size);
        this.gUI = new GameUserInterface(this.gameState, this);
        this.bUI = gUI.getBoardUserInterface(); 


    }


    //in the action performed method, it first checks if its the quit or reset button.
    //if its the quit or reset then it will either quit or reset the game.
    //after that it checks if the button clicked is next to a neighbour.
    //it does this by going thru the clickedOnNeighbour method.
    //if it was clicked next to a neighbour, then that free cube becomes a snake and the red cube moves
    //it the button clicked was the red cube, then the user wins
    //if the redcube moves to the edge then the user loses
    public void actionPerformed(ActionEvent e) {
	   //the logic of the game goes in this method        
	   // YOUR CODE HERE
        int redCubeRandomX;
        int redCubeRandomY;

        int rowOfClickedCube = 0;
        int colOfClickCube = 0;
        boolean clickedOnNeighbour;

       
        Cube clicked;

        
        if (e.getSource() == gUI.getReset()){
            gUI.dispose();
            reset();
        } else if (e.getSource() == gUI.getQuit()){
            gUI.dispose();
            System.exit(0);
        } else if (e.getSource() instanceof Cube){
            clicked = (Cube) e.getSource();
            rowOfClickedCube = clicked.getRow();
            colOfClickCube = clicked.getColumn();
            clickedOnNeighbour = clickedOnNeighbour(rowOfClickedCube, colOfClickCube);
            if (clickedOnNeighbour){
                if (gameState.getCurrentStatus(rowOfClickedCube, colOfClickCube) == 2){
                    JOptionPane.showMessageDialog(new JFrame(), "You Win");
                    gUI.dispose();
                    reset();
                } else if (gameState.getCurrentStatus(rowOfClickedCube, colOfClickCube) == 0) {
                    gameState.select(rowOfClickedCube, colOfClickCube);
                    
                    while(true){
                        redCubeRandomX = gameState.getRandomNum();
                        redCubeRandomY = gameState.getRandomNum();
                        if (gameState.getCurrentStatus(redCubeRandomX,redCubeRandomY) == 0){
                            break;
                        }
                    }   

                    gameState.setCube(redCubeRandomX, redCubeRandomY);

                    bUI.update();
                    if(gameState.getCurrentCube().getX()==0 ||gameState.getCurrentCube().getX()== gameState.getSize()-1 || gameState.getCurrentCube().getY()== 0 || gameState.getCurrentCube().getY() == gameState.getSize()-1 ){
                        JOptionPane.showMessageDialog(new JFrame(), "You lost the game. Nice try though");
                        gUI.dispose();
                        reset();
                    }

                }
            }
        }    


    }

    //I created this
    private boolean clickedOnNeighbour(int row, int col){   


            //************************************ //
            //          Testing bottom row        //
            //***********************************//

            //testing bottom row without  left and right corners
            //if the size of the board is even or odd matters for the bottom row
            if (row == gameState.getSize()-1 && col > 0 && col < gameState.getSize()-1){
                if (gameState.getSize() % 2 == 0){
                    if (gameState.getCurrentStatus(row, col + 1) == 1 || (gameState.getCurrentStatus(row, col -1)) == 1 || gameState.getCurrentStatus(row-1, col) == 1 || gameState.getCurrentStatus(row-1, col+1) == 1){
                        return true;
                    } 
                }  else {
                    if (row == gameState.getSize()-1 && col > 0 && col < gameState.getSize()-1){
                        if (gameState.getCurrentStatus(row, col + 1) == 1 || (gameState.getCurrentStatus(row, col -1)) == 1 || gameState.getCurrentStatus(row-1, col) == 1 || gameState.getCurrentStatus(row-1, col-1) == 1){
                        return true;
                        }     
                    } 

                }
            } 
            //testing bottom left corner
            else if (row == gameState.getSize()-1 && col == 0){
                if (gameState.getSize() % 2 == 0){
                    if (gameState.getCurrentStatus(row, col + 1) == 1 || gameState.getCurrentStatus(row -1,col ) == 1 || gameState.getCurrentStatus(row -1,col+1 ) == 1 ){
                        return true;
                    }
                }
                else {
                    if (gameState.getCurrentStatus(row, col + 1) == 1 || gameState.getCurrentStatus(row -1,col ) == 1){
                        return true;
                    }
                }
            } 

            //testing bottom right corner
            else if (row == gameState.getSize()-1 && col == gameState.getSize()-1){
                if (gameState.getSize() % 2 == 0){
                    if (gameState.getCurrentStatus(row, col -1) == 1 || gameState.getCurrentStatus(row -1,col ) == 1 ){
                        return true;
                    } 
                } else {
                    if (gameState.getCurrentStatus(row, col -1) == 1 || gameState.getCurrentStatus(row -1,col ) == 1 || gameState.getCurrentStatus(row -1,col-1 ) == 1){
                        return true;
                    }

                }
            }

            //************************************ //
            //          Testing top row           //
            //***********************************//

            //testing the top row without left and right corners
            if (row == 0 && col > 0 && col < gameState.getSize() - 1){
                if (gameState.getCurrentStatus(row, col + 1) == 1 || (gameState.getCurrentStatus(row, col -1)) == 1 || gameState.getCurrentStatus(row+1, col) == 1 || gameState.getCurrentStatus(row+1, col-1) == 1){
                    return true;
                } 
            } 
            //testing top left corner
            else if (row == 0 && col == 0){
                if (gameState.getCurrentStatus(row, col + 1) == 1 ||gameState.getCurrentStatus(row +1,col) == 1 ){
                    return true;
                }
            }

            //testing top right corner
            else if (row == 0 && col == gameState.getSize()-1){
                if (gameState.getCurrentStatus(row, col -1) == 1 || gameState.getCurrentStatus(row +1,col ) == 1 ||gameState.getCurrentStatus(row +1,col -1 ) == 1 ){
                    return true;
                }

            }

        

         //************************************ //
         //          Testing left column        //
         //***********************************//

         //testing left column without top left and bottom left corner
         //rows differ if they are even or odd 
            if (col == 0 && row > 0 && row < gameState.getSize()-1){
                if (row % 2 == 1){
                    if (gameState.getCurrentStatus(row, col + 1) == 1 || gameState.getCurrentStatus(row +1 ,col ) == 1 || gameState.getCurrentStatus(row -1, col) == 1 || gameState.getCurrentStatus(row - 1, col +1 ) == 1 || gameState.getCurrentStatus(row + 1, col +1) == 1){
                        return true;
                    }
                } else {
                    if (gameState.getCurrentStatus(row, col + 1) == 1 || gameState.getCurrentStatus(row +1 ,col ) == 1 || gameState.getCurrentStatus(row -1, col) == 1){
                        return true;
                    }
                }
            }

             //************************************ //
            //          Testing right column       //
            //***********************************//


            if (col == gameState.getSize()-1 && row > 0 && row < gameState.getSize()-1){
                if (row % 2 == 1){
                    if (gameState.getCurrentStatus(row, col - 1) == 1 || gameState.getCurrentStatus(row +1 ,col ) == 1 || gameState.getCurrentStatus(row -1, col) == 1){
                        return true;
                    }
                } else {
                    if (gameState.getCurrentStatus(row, col - 1) == 1 || gameState.getCurrentStatus(row +1 ,col ) == 1 || gameState.getCurrentStatus(row -1, col) == 1 || gameState.getCurrentStatus(row - 1, col -1 ) == 1 || gameState.getCurrentStatus(row + 1, col - 1) == 1){
                        return true;
                    }

                }

            }

            //*************************************************  //
            //     Testing  rows and columns in the middle      //
            //*************************************************//

            if (row > 0 && row < gameState.getSize()-1 && col > 0 && col < gameState.getSize()-1){
                //there is a difference in the pattern if row is even or odd
                //this one is even  
                if (row % 2 == 0){
                    if (gameState.getCurrentStatus(row-1, col) == 1|| gameState.getCurrentStatus(row-1, col-1) == 1|| gameState.getCurrentStatus(row, col+1) == 1||gameState.getCurrentStatus(row+1, col) == 1|| gameState.getCurrentStatus(row+1, col-1) == 1||gameState.getCurrentStatus(row, col-1) == 1){
                        return true;
                    }

                } else {
                    if (gameState.getCurrentStatus(row-1, col) == 1 ||gameState.getCurrentStatus(row-1, col+1) == 1||gameState.getCurrentStatus(row, col+1) == 1||gameState.getCurrentStatus(row+1, col+1) == 1||gameState.getCurrentStatus(row+1, col) == 1||gameState.getCurrentStatus(row, col-1) == 1){
                         return true;
                    }
                }
            }


        return false;
    }

 
}

