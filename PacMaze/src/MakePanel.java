import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MakePanel extends JPanel {

	public static final ImageIcon PATH_IMAGE = new ImageIcon("pellet.png");
	public static final ImageIcon START_IMAGE = new ImageIcon("pacman1.png");
	public static final ImageIcon GOAL_IMAGE = new ImageIcon("cherry1.png");

	public static final int IMAGE_HEIGHT = 30;
	public static final int IMAGE_WIDTH = 30;

	private MazeGame maze;
	private Timer updater;

	public MakePanel() {

		maze = new MazeGame(Level.parseLevel("maze.txt"));
		updater = new Timer(100, new Updater());
		setPreferredSize(new Dimension(maze.getLevel().getNumCols()
				* IMAGE_WIDTH, maze.getLevel().getNumRows() * IMAGE_HEIGHT + 40));
		// pacmac movement
		setFocusable(true);
		addKeyListener(new PacmanControls());

		setLayout(new BorderLayout());

		JPanel buttonPanel = new JPanel();

		JButton DFS = new JButton("Depth First Search");
		DFS.setPreferredSize(new Dimension(204, 30));
		DFS.addActionListener(new DFSUpdater());

		JButton BFS = new JButton("Breadth First Search");
		BFS.setPreferredSize(new Dimension(204, 30));
		BFS.addActionListener(new BFSUpdater());

		JButton RHR = new JButton("Right Hand Rule");
		RHR.setPreferredSize(new Dimension(204, 30));
		RHR.addActionListener(new RHRUpdater());

		JButton LHR = new JButton("Left Hand Rule");
		LHR.setPreferredSize(new Dimension(204, 30));
		LHR.addActionListener(new LHRUpdater());

		JButton CLEAR = new JButton("CLEAR");
		CLEAR.setPreferredSize(new Dimension(204, 30));
		CLEAR.addActionListener(new CLEARUpdater());
		CLEAR.addKeyListener(new PacmanControls());

		buttonPanel.add(DFS);
		buttonPanel.add(BFS);
		buttonPanel.add(RHR);
		buttonPanel.add(LHR);
		buttonPanel.add(CLEAR);

		add(buttonPanel, BorderLayout.SOUTH);
	}

	public void paint(Graphics g) {
		super.paint(g);

		Level level = maze.getLevel();
		int numRows = level.getNumRows();
		int numCols = level.getNumCols();

		g.setColor(Color.black);
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {

				if (!level.isValidLocation(new Location(row, col))) {

					g.fillRect(col * IMAGE_HEIGHT, row * IMAGE_WIDTH,
							IMAGE_HEIGHT, IMAGE_WIDTH);
				}
			}
		}
		if (!maze.drawEverything(g)) {
			final JFrame dialogFrame = new JFrame();
			JOptionPane.showMessageDialog(dialogFrame,
					" You just solved the maze ! ");

		}
	}

	private class Updater implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			repaint();
			updater.start();

		}
	}

	private class DFSUpdater implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			maze = new MazeGame(Level.parseLevel("maze.txt"), 0);
			repaint();
			updater.start();

		}
	}

	private class BFSUpdater implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			maze = new MazeGame(Level.parseLevel("maze.txt"), 1);
			repaint();
			updater.start();
		}
	}

	private class RHRUpdater implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			maze = new MazeGame(Level.parseLevel("maze.txt"), 2);
			repaint();
			updater.start();
		}
	}

	private class LHRUpdater implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			maze = new MazeGame(Level.parseLevel("maze.txt"), 3);
			repaint();
			updater.start();
		}
	}

	private class CLEARUpdater implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			maze = new MazeGame(Level.parseLevel("maze.txt"));

		}
	}

	private class PacmanControls implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				maze.movePacman(Movable.NORTH);
				break;
			case KeyEvent.VK_DOWN:
				maze.movePacman(Movable.SOUTH);
				break;
			case KeyEvent.VK_RIGHT:
				maze.movePacman(Movable.EAST);
				break;
			case KeyEvent.VK_LEFT:
				maze.movePacman(Movable.WEST);
				break;
			}
			if (maze.checkCollisions()) {
				final JFrame dialogFrame = new JFrame();
				JOptionPane.showMessageDialog(dialogFrame,
						" Congrate ! You found a cherry !");
				updater.stop();
			}

			repaint();

			updater.start();

		}

		// method in KeyListener that we do not want to use,
		// so we just override it with an empty method body
		@Override
		public void keyReleased(KeyEvent e) {
		}

		// method in KeyListener that we do not want to use,
		// so we just override it with an empty method body
		@Override
		public void keyTyped(KeyEvent e) {
		}

	}

}
