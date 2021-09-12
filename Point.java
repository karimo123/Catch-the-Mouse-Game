
public class Point {


   // YOUR INSTANCE VARIABLES HERE
   //private ...
   //private ...
  private int x;
  private int y;


    /*Constructor 
     * The parameters x and y are the coordinates
     */
    public Point(int x, int y){
      this.x = x;
      this.y = y;
        // REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION

    }

    //Getter method, return the value of instance variable x
    public int getX(){
      return this.x;
      //REPLACE THIS LINE WITH YOUR CODE 
    }
    
    //Getter method, return the value of instance variable y
    public int getY(){
      return this.y;
      //REPLACE THIS LINE WITH YOUR CODE 
    }
    

    //Setter method, sets the values of instance variables x and y
    public void reset(int x, int y){
	//YOUR CODE HERE
      this.x = x;
      this.y = y;
      
  }

 }
