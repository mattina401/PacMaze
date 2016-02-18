import java.awt.Graphics;
import java.util.Stack;

public class MazeGame {
	private Pacman pacman;
	private Level level;
	private Cherry cherry;
	private Stack<Integer> finalPath;
	private boolean isPlaying = true;

	public MazeGame(char[][] levelTiles) {
		level = new Level(levelTiles);

		pacman = new Pacman(level.getPacmanOrigin());
		cherry = new Cherry(level.getCherryOrigin());

		pacman.setFacingDirection(Movable.ORIGIN);

		finalPath = null;
	}

	public MazeGame(char[][] levelTiles, int flag) {
		// DFS
		switch (flag) {
		case 0:
			level = new Level(levelTiles);

			pacman = new Pacman(level.getPacmanOrigin());
			cherry = new Cherry(level.getCherryOrigin());

			pacman.move(getLevel());
			finalPath = pacman.getPath();
			break;

		case 1:
			level = new Level(levelTiles);

			pacman = new Pacman(level.getPacmanOrigin());
			cherry = new Cherry(level.getCherryOrigin());

			pacman.move2(getLevel());
			finalPath = pacman.getPath();
			break;

		case 2:
			level = new Level(levelTiles);

			pacman = new Pacman(level.getPacmanOrigin());
			cherry = new Cherry(level.getCherryOrigin());

			pacman.move3(getLevel());
			finalPath = pacman.getPath1();
			break;
			
		case 3:
			level = new Level(levelTiles);

			pacman = new Pacman(level.getPacmanOrigin());
			cherry = new Cherry(level.getCherryOrigin());

			pacman.move4(getLevel());
			finalPath = pacman.getPath1();
			break;
			
		}
		
	

	}

	public Level getLevel() {
		return level;
	}

	public boolean drawEverything(Graphics g) {
		boolean isPlaying = true;
		if (finalPath != null && !finalPath.empty()) {
			int path = finalPath.pop();
			if(finalPath.empty())
				isPlaying = false;
			pacman.setFacingDirection(path);
			pacman.getLocation().moveByDirection(path);
		}
		cherry.draw(g);
		pacman.draw(g);
		
		return isPlaying;
	}
	
	public void movePacman(int direction) {
		pacman.setFacingDirection(direction);
		pacman.move5(getLevel());

	}
	
	public boolean playing(){
		return this.isPlaying;
	}
	
	public boolean checkCollisions(){
		if(pacman.getLocation().getCol() == cherry.getLocation().getCol() && pacman.getLocation().getRow() == cherry.getLocation().getRow()){
			return true;
		}
		else return false;
	}




}
