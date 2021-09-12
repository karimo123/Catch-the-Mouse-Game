import javax.swing.JButton;
import javax.swing.ImageIcon;


/********************************************************************
 * The Cube is a type of JButton that represents a cube in the game 
*********************************************************************/

public class Cube extends JButton {

    // ADD YOUR INSTANCE VARIABLES HERE
    private int row;
    private int column;
    private int type;
    /**
     * Constructor*/

    public Cube(int row, int column, int type) {
		//YOUR CODE HERE
        this.row = row;
        this.column = column;
        this.type = type;
        this.setFocusable(false);
        validate();

        
    }


     //Sets the type of a square. 
	 //sets the icon of the square.
	
    public void setType(int type) {
		//Your code here
        if (type == 0){
            this.setIcon(new ImageIcon("square-0.png"));
            validate();
        } else if (type == 1){
            this.setIcon(new ImageIcon("square-1.png"));
            validate();
        } else if (type == 2){
            this.setIcon(new ImageIcon("square-2.png"));
            validate();
        }

    }

 
    // Getter method for the attribute row.
    public int getRow() {
		return this.row;//REPLACE THIS LINE WITH YOUR CODE 
    }

    //Getter method for the attribute column.
    public int getColumn() {
		return this.column;//REPLACE THIS LINE WITH YOUR CODE 
    }
}
