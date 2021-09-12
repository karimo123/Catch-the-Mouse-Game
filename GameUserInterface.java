import javax.swing.*;
import java.awt.*;

 /*the class GameUserInterface provides the user interface of the Game. It extends
 JFrame and lays out an instance of BoardUserInterface and  two instances of JButton: one to reset and the second the quit the game at any time.*/

public class GameUserInterface extends JFrame {

	private JButton reset;
	private JButton quit;
	// ADD YOUR INSTANCE VARIABLES HERE
	//ALL PRIVATE
	private BoardUserInterface bUI;
	private GameState state;
	private GameLogic gameLogic;
 
    /* Constructor 
	 * initializes the board
     * takes as parameters the state of the game and the gameLogic */

    //a frame is made, the reset and quit buttons are made, and the BoardUserInterface is added to the frame
    public GameUserInterface(GameState state, GameLogic gameLogic) {
		//Your code here
    	this.state = state;
    	this.gameLogic = gameLogic;

    	
        //Create a panel here and add the panel from BoardUserInterface to this panel,
        //then add this panel to the frame in this class
    	this.setTitle("Game - Catch the Mouse!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(state.getSize()*55,state.getSize()*75);
        this.setLayout(new BorderLayout(20,20));

		this.bUI = new BoardUserInterface(this.state, this.gameLogic);

        JPanel panelForQuitandRest = new JPanel();

        panelForQuitandRest.setVisible(true);



    	this.reset = new JButton();
    	this.reset.setText("Reset");
    	this.reset.setFocusable(false);
        this.reset.addActionListener(gameLogic);
    	validate();


    	this.quit = new JButton();
    	this.quit.setText("Quit");
    	this.quit.setFocusable(false);
        this.quit.addActionListener(gameLogic);
    	validate();

        panelForQuitandRest.add(this.reset);
        panelForQuitandRest.add(this.quit);

        this.getContentPane().add(panelForQuitandRest, BorderLayout.SOUTH);
        this.add(this.bUI);
		this.setVisible(true);


    }


	public JButton getReset(){
		return this.reset;
	}
	public JButton getQuit(){
		return this.quit;
	}
    public BoardUserInterface getBoardUserInterface(){
		//YOUR CODE HERE
		return this.bUI;
   }

}
