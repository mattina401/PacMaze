public class Location {

	private int row;
	private int col;
	private boolean marked;
	private Location parent;
	private char content;
	private int facingDirection;
	
	public Location(int paramInt1, int paramInt2) {
		this.row = paramInt1;
		this.col = paramInt2;
		marked = false;
		parent = null;
		facingDirection = -1;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getCol(){
		return this.col;
	}
	
	public char getCont(){
		return this.content;
	}
	
	public void setRow(int r){
		this.row = r;
	}
	
	public void setCol(int c){
		this.col = c;
	}
	
	public void setCont(char c){
		this.content = c;
	}
	
	  public void moveByDirection(int paramInt)
	  {
	    switch (paramInt) {
	    //NORTH
	    case 0:
	      this.row -= 1;
	      break;
	      //SOUTH
	    case 1:
	      this.row += 1;
	      break;
	    case 2:
	    	//EAST
	      this.col += 1;
	      break;
	      //WEST
	    case 3:
	      this.col -= 1;
	    }
	  }

	  public Location clone()
	  {
	    return new Location(this.row, this.col);
	  }
	  
	  public void Mark(){
		  marked = true;
	  }
	  
	  public boolean isMarked(){
		  return this.marked;
	  }
	  
	  public Location getParent(){
		  return this.parent;
	  }
	  
	  public void changeParent(Location child){
		  child.parent = this;
	  }

	public void setDirection(int direction) {
		// TODO Auto-generated method stub
		this.facingDirection = direction;
		
	}
	
	public int getDirection(){
		return this.facingDirection;
	}
	  
	  
	  
	  


}
