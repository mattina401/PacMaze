
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * 
 * @author Gu Young Chung(gchung8)
 * 		   Bongsu Kim(bkim343)
 *		   Jongyeon Kim(jkim989)
 */
public class Maze {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setTitle("MAZE GAME - made by Gu Young Chung,  Bongsu Kim,  Jongyeon Kim");

		frame.getContentPane().add(new MakePanel());

		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JFrame dialogFrame = new JFrame();
		JOptionPane.showMessageDialog(dialogFrame,
				" Welcome to the PacMaze world! ");
		frame.setVisible(true);

	}

}
