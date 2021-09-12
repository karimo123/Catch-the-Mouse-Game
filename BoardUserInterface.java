import javax.swing.*;
import java.awt.*;


public class BoardUserInterface extends JPanel {

    //YOUR INSTANCE VARIABLES HERE
	//private ...
	//private ...
    private Cube[][] cubes;
    private JPanel[] panel;
    private int row;
    GameState gameState;
    GameLogic gameLogic;

    //the buttons are added on the panels
    //then the panels are added to the big BUI panel 
    public BoardUserInterface(GameState gameState, GameLogic gameLogic) {
		//Your code goes here
        this.gameState = gameState;
        this.gameLogic = gameLogic;


        panel = new JPanel[gameState.getSize()];
        this.setBounds(0,0,1000,700);
        this.setVisible(true);
        this.setLayout(new FlowLayout());

        cubes = new Cube[gameState.getSize()][gameState.getSize()];

        for (int i = 0;i < gameState.getSize();i++){
            //create a row as a JPanel in the outer loop
            panel[i] = new JPanel(new FlowLayout());
            if (i % 2 == 0){
                panel[i].setBorder(BorderFactory.createEmptyBorder(0,5,0,20));
            } else {
                panel[i].setBorder(BorderFactory.createEmptyBorder(0,40,0,5));
            }
            
            panel[i].setVisible(true);
            for (int j = 0;j < gameState.getSize();j++){
                cubes[i][j] = new Cube(i,j,0);
                cubes[i][j].setPreferredSize(new Dimension(40,40));
                cubes[i][j].addActionListener(gameLogic);
                panel[i].add(cubes[i][j]);    
            }
            this.add(panel[i]);   
        }


        update();
    }


    //updates the status of the board's cubes instances following the game state
	//calls setType() from the class Cube, as needed.
    public void update(){
		//Your code goes here
         for (int i = 0;i < gameState.getSize();i++){
            for (int j = 0;j < gameState.getSize();j++){
                if (gameState.getCurrentStatus(i,j) == 0){
                    cubes[i][j].setType(0);
                } else if (gameState.getCurrentStatus(i,j) == 1){
                    cubes[i][j].setType(1);
                } else if (gameState.getCurrentStatus(i,j) == 2){
                    cubes[i][j].setType(2);
                }
                
            }
            
        }

    
    }


}


