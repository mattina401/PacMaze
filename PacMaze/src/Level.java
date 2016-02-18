import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Level
{
  private final char PACMAN = 'p';
  private final char WALL = 'x';
  private final char CHERRY = 'c';
  private final char PELLET = '.';
  private Location[][] tiles;
  private Location pacmanOrigin;
  private Location cherryOrigin;

  public Level(char[][] paramArrayOfChar)
  {
	tiles = new Location[paramArrayOfChar.length][paramArrayOfChar[0].length];
	  
    for (int i = 0; i < paramArrayOfChar.length; i++){
      for (int j = 0; j < paramArrayOfChar[i].length; j++){
        if (paramArrayOfChar[i][j] == PACMAN) {
          this.pacmanOrigin = new Location(i, j);
        }
        else if (paramArrayOfChar[i][j] == CHERRY)
          this.cherryOrigin = new Location(i, j);
        
        tiles[i][j] = new Location(i,j);
        this.tiles[i][j].setCont(paramArrayOfChar[i][j]);
      }
    }
  }

  public boolean isValidLocation(Location paramLocation)
  {
    if ((paramLocation.getRow() < this.tiles.length) && (paramLocation.getCol() < this.tiles[0].length) && (paramLocation.getRow() > -1) && (paramLocation.getCol() > -1))
    {
      return this.tiles[paramLocation.getRow()][paramLocation.getCol()].getCont() != WALL;
    }
    return false;
  }

  public int getNumRows()
  {
    return this.tiles.length;
  }

  public int getNumCols()
  {
    return this.tiles[0].length;
  }

  public Location getPacmanOrigin()
  {
    return this.pacmanOrigin;
  }

  public Location getCherryOrigin()
  {
    return this.cherryOrigin;
  }

  public Location[][] getTiles(){
	  return this.tiles;
  }
  public static char[][] parseLevel(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Scanner localScanner = null;
    try {
      localScanner = new Scanner(new FileInputStream(paramString));
    }
    catch (FileNotFoundException localFileNotFoundException) {
      localFileNotFoundException.printStackTrace();
    }

    while (localScanner.hasNextLine()) {
      String localObject = localScanner.nextLine();
      System.out.println(localObject);
      localArrayList.add((localObject).toCharArray());
    }

    char[][] localObject = new char[localArrayList.size()][];
    for (int i = 0; i < localArrayList.size(); i++) {
      localObject[i] = ((char[])localArrayList.get(i));
    }
    return localObject;
  }
}