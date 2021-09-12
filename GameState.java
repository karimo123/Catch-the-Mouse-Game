import java.util.Random;


public class GameState {


		//static final variables public
		public static final int FREE_CUBE = 0;
		public static final int SELECTED = 1;
		public static final int RED_CUBE = 2;
		public static final int MAX_SELECTED = 5;
		
		//non-final variables private
        
		private int boardSize;
		private Point redCube;


        private static Random aRandom = new Random();
        private int[][] cubes;
        private int amountSelected;
        private  int xCoordinateGreen, yCoordinateGreen;
        private  int xCoordinateRed, yCoordinateRed; //for even

    

  
		// YOUR INSTANCE VARIABLES HERE

    /**
     * Constructor 
	 * initializes the state to the size of board
     *  The parameter size is the size of the board
     */

    //if its an even board, the red cube is selected within the 4 central cubes in the center
    //if its an odd board, the red cude is selected within the 9 central cubes in the center
    // a new point is made with the red cubes coordinates
    //1-5 snakes are also made
    //the rest of the cubes are free cubes
     
    public GameState(int size) {
	// YOUR CODE HERE
        this.boardSize = size;
        this.amountSelected = aRandom.nextInt((MAX_SELECTED-2)+1)+2;
        cubes = new int[this.boardSize][this.boardSize];

        if (size % 2 == 0){
            xCoordinateRed = aRandom.nextInt((size/2)-((size/2)-1)+1) + ((size/2)-1);
            yCoordinateRed = aRandom.nextInt((size/2)-((size/2)-1)+1) + ((size/2)-1);
            redCube = new Point(xCoordinateRed,yCoordinateRed);
            cubes[xCoordinateRed][yCoordinateRed] = RED_CUBE;
        } else if (size % 2 == 1){
            xCoordinateRed = aRandom.nextInt(((size/2+1)-(size/2-1))+1)+size/2-1;
            yCoordinateRed = aRandom.nextInt(((size/2+1)-(size/2-1))+1)+size/2-1;
            redCube = new Point(xCoordinateRed,yCoordinateRed);
            cubes[xCoordinateRed][yCoordinateRed] = RED_CUBE;
        }

        for (int i = 0;i < amountSelected;i++){
            xCoordinateGreen = aRandom.nextInt((this.boardSize));;
            yCoordinateGreen = aRandom.nextInt((this.boardSize));;
            if (xCoordinateGreen == redCube.getX() && redCube.getY() == yCoordinateGreen){

            } else {
                cubes[xCoordinateGreen][yCoordinateGreen] = SELECTED;
            }
        }

        for (int i = 0;i < this.boardSize;i++){
            for (int j = 0;j < this.boardSize;j++){

                if (cubes[i][j] == RED_CUBE){

                } else if (cubes[i][j] == SELECTED) {
                    
                } else {
                    cubes[i][j] = FREE_CUBE;
                }
                
            }
            
        }


            
    }
        
 
    public  int getSize(){
	//YOUR CODE HERE
		return this.boardSize;
   }

    /**
     * returns the current status (FREE_CUBE, SELECTED or RED_CUBE) of a given cube in the game
     * 
     * i is the x coordinate of the cube
     * j is the y coordinate of the cube
     * return the status of the cube at location (i,j)
     */   
    public int getCurrentStatus(int i, int j){
        return cubes[i][j];
    }


    /**
     * Sets the status of the cube at coordinate (i,j) to SELECTED
     * i is the x coordinate of the cube
     * j is the y coordinate of the cube
     */   
    public void select(int i, int j){
	//YOUR CODE HERE
        cubes[i][j] = SELECTED;
    }

    /**
     * Puts the red cube at coordinate (i,j). Clears the previous location 
     * of the red cube.
     *
      * i is the x coordinate of the cube
     * j is the y coordinate of the cube
     */   
    public void setCube(int i, int j){

	   //YOUR CODE HERE 
       
        cubes[redCube.getX()][redCube.getY()] = FREE_CUBE;
        redCube.reset(i,j);
        cubes[i][j] = RED_CUBE;
   }

    /* Getter method for the current red cube
     * return the location of the curent red cube */   
    public Point getCurrentCube(){
		return redCube;//REPLACE THIS LINE WITH YOUR CODE 
    }

    //this method was created to give gamelogic a random number for the red cube
    public int getRandomNum(){
       int num =  aRandom.nextInt(getSize());
       return num;

    }

}
