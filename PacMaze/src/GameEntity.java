import javax.swing.ImageIcon;

/**
 * A Movable GameObject
 * 
 * @author Gu Young Chung
 * 
 */
public abstract class GameEntity extends GameObject implements Movable {
	// the initial location of the GameEntity
	private Location origin;
	// the direction the entity is facing
	private int facingDirection;

	/**
	 * Creates a GameEntity at the given location with the given image
	 * 
	 * @param location
	 *            the initial location of the entity
	 * @param image
	 *            the image of the entity to be drawn
	 */
	public GameEntity(Location location, ImageIcon image) {
		super(location, image);
		origin = location.clone();
	}

	// move the Game enity by 1 unit in the direction it
	// is facing - remember, only move it if it results in
	// a valid location (ie, not a wall)

	@Override
	public void move(Level level) {
		Location nextLocation = location.clone();

		nextLocation.moveByDirection(facingDirection);
		if (level.isValidLocation(nextLocation))
			location.moveByDirection(facingDirection);

	}

	@Override
	public boolean collide(GameObject object) {
		if (this.location.equals(object.getLocation()))
			return true;
		else
			return false;
	}

	@Override
	public int getFacingDirection() {
		return facingDirection;

	}

	@Override
	public void setFacingDirection(int direction) {
		facingDirection = direction;

	}

	/**
	 * Moves the GameEntity back to where it initially started
	 */
	public void moveToOrigin() {
		location = origin.clone();
	}

	/**
	 * Turns the GameEntity. A turn can be right or left, and results in a
	 * change of the Entity's facing direction. For example, if the GameEntity
	 * was facing North, and it executed a right turn, it should now be facing
	 * East.
	 * 
	 * @param right
	 */
	public void turn(boolean right) {
		if (right)
			turnRight();
		else {
			for (int i = 0; i < 3; i++)
				turnRight();
		}

	}

	/*
	 * A helper methods for turning right - think about how you can use this
	 * method to execute both right and left turns
	 */
	private void turnRight() {
		if (this.getFacingDirection() == NORTH)
			this.setFacingDirection(EAST);
		else if (this.getFacingDirection() == SOUTH)
			this.setFacingDirection(WEST);
		else if (this.getFacingDirection() == EAST)
			this.setFacingDirection(SOUTH);
		else
			this.setFacingDirection(NORTH);

	}
}
