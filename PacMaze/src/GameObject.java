import java.awt.Graphics;
import javax.swing.ImageIcon;

public class GameObject implements Drawable {

	protected Location location;

	private ImageIcon image;

	public GameObject(Location location, ImageIcon image) {

		this.location = location;
		this.image = image;
	}

	public Location getLocation() {
		return location;

	}

	public void draw(Graphics g) {

		g.drawImage(image.getImage(),
				location.getCol() * MakePanel.IMAGE_WIDTH, location.getRow()
						* MakePanel.IMAGE_HEIGHT, image.getImageObserver());

	}
}
