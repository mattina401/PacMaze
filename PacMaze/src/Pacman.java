import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Pacman extends GameEntity implements Movable {
	Stack<Integer> path = new Stack<Integer>();
	Stack<Integer> path1 = new Stack<Integer>();


	public Pacman(Location location) {
		super(location, MakePanel.START_IMAGE);
	}

	public void move(Level level) {
		Location N, S, E, W;

		Stack<Location> Q = new Stack<Location>();

		Location start = location.clone();
		start.Mark();

		Q.push(start);

		while (!Q.isEmpty()) {
			Location poll = Q.pop();
			if (poll.getCol() == level.getCherryOrigin().getCol()
					&& poll.getRow() == level.getCherryOrigin().getRow()) {
				printMark(poll, level);
				for (int i = 0; i < level.getNumRows(); i++) {
					for (int j = 0; j < level.getNumCols(); j++) {
						System.out.print(level.getTiles()[i][j].getCont());
					}
					System.out.println();
				}
				return;
			}

			if (poll.getRow() - 1 > 0) {
				N = level.getTiles()[poll.getRow() - 1][poll.getCol()];

				if (N.getCont() != 'x' && !N.isMarked()) {
					N.Mark();
					N.setDirection(NORTH);
					poll.changeParent(N);
					Q.push(N);
				}
			}

			if (poll.getRow() + 1 < level.getNumRows()) {
				S = level.getTiles()[poll.getRow() + 1][poll.getCol()];

				if (S.getCont() != 'x' && !S.isMarked()) {
					S.Mark();
					S.setDirection(SOUTH);
					poll.changeParent(S);
					Q.push(S);
				}
			}

			if (poll.getCol() + 1 < level.getNumCols()) {
				E = level.getTiles()[poll.getRow()][poll.getCol() + 1];

				if (E.getCont() != 'x' && !E.isMarked()) {
					E.Mark();
					E.setDirection(EAST);
					poll.changeParent(E);
					Q.push(E);
				}
			}

			if (poll.getCol() - 1 > 0) {
				W = level.getTiles()[poll.getRow()][poll.getCol() - 1];

				if (W.getCont() != 'x' && !W.isMarked()) {
					W.Mark();
					W.setDirection(WEST);
					poll.changeParent(W);
					Q.push(W);
				}
			}

		}
	}

	public void move2(Level level) {
		Location N, S, E, W;

		Queue<Location> Q = new LinkedList<Location>();

		Location start = location.clone();
		start.Mark();

		Q.add(start);

		while (!Q.isEmpty()) {
			Location poll = Q.poll();
			if (poll.getCol() == level.getCherryOrigin().getCol()
					&& poll.getRow() == level.getCherryOrigin().getRow()) {
				printMark(poll, level);
				for (int i = 0; i < level.getNumRows(); i++) {
					for (int j = 0; j < level.getNumCols(); j++) {
						System.out.print(level.getTiles()[i][j].getCont());
					}
					System.out.println();
				}
				return;
			}

			if (poll.getRow() - 1 > 0) {
				N = level.getTiles()[poll.getRow() - 1][poll.getCol()];

				if (N.getCont() != 'x' && !N.isMarked()) {
					N.Mark();
					N.setDirection(NORTH);
					poll.changeParent(N);
					Q.add(N);
				}
			}

			if (poll.getRow() + 1 < level.getNumRows()) {
				S = level.getTiles()[poll.getRow() + 1][poll.getCol()];

				if (S.getCont() != 'x' && !S.isMarked()) {
					S.Mark();
					S.setDirection(SOUTH);
					poll.changeParent(S);
					Q.add(S);
				}
			}

			if (poll.getCol() + 1 < level.getNumCols()) {
				E = level.getTiles()[poll.getRow()][poll.getCol() + 1];

				if (E.getCont() != 'x' && !E.isMarked()) {
					E.Mark();
					E.setDirection(EAST);
					poll.changeParent(E);
					Q.add(E);
				}
			}

			if (poll.getCol() - 1 > 0) {
				W = level.getTiles()[poll.getRow()][poll.getCol() - 1];

				if (W.getCont() != 'x' && !W.isMarked()) {
					W.Mark();
					W.setDirection(WEST);
					poll.changeParent(W);
					Q.add(W);
				}
			}

		}
	}
	//RH
	public void move3(Level level) {
		Queue<Location> q = new LinkedList<Location>();
		Stack<Integer> stk = new Stack<Integer>();
		Location N, S, E, W;
		Location start = location;
		start.setDirection(3);
		q.add(start);
		

		while (!q.isEmpty()) {
			
			Location current = q.poll();
			
			Location cloneS = current.clone();
			Location cloneE = current.clone();
			Location cloneN = current.clone();
			Location cloneW = current.clone();
			cloneS.moveByDirection(SOUTH);
			cloneE.moveByDirection(EAST);
			cloneN.moveByDirection(NORTH);
			cloneW.moveByDirection(WEST);
			
			
			N = level.getTiles()[current.getRow() - 1][current.getCol()];
			S = level.getTiles()[current.getRow() + 1][current.getCol()];
			E = level.getTiles()[current.getRow()][current.getCol() + 1];
			W = level.getTiles()[current.getRow()][current.getCol() - 1];
			
			
			if (current.getCol() == level.getCherryOrigin().getCol() && current.getRow() == level.getCherryOrigin().getRow()) {
				while (!stk.isEmpty()) {
					int i = stk.pop();
					path1.push(i);
				}
			}
			
			/*
			   xxxxx	
			   x   x
			xxxx   xxxx	
			x         x
			xxxx   xxxx
			   x   x
			   xxxxx	
			 */
			
			if (W.getCont() != 'x' && N.getCont() != 'x' && S.getCont() != 'x' && E.getCont() != 'x'){
				if (current.getDirection() == 1) {
					W.setDirection(3);
					stk.push(3);
					q.add(W);
				}
				if (current.getDirection() == 0) {
					E.setDirection(2);
					stk.push(2);
					q.add(E);
				}
				if (current.getDirection() == 3) {
					N.setDirection(0);
					stk.push(0);
					q.add(N);
				}
				if (current.getDirection() == 2) {
					S.setDirection(1);
					stk.push(1);
					q.add(S);
				}
			}
			
			/*
			   xxxxx	
			   x # x
			xxxx # xxxx	
			x         x
			xxxx   xxxx
			   x   x
			   xxxxx	
			 */
			if (W.getCont() != 'x' && N.getCont() == 'x' && S.getCont() != 'x' && E.getCont() != 'x') {
				if (current.getDirection() == 2) {
					S.setDirection(1);
					stk.push(1);
					q.add(S);
				}
				if (current.getDirection() == 3) {
					W.setDirection(3);
					stk.push(3);
					q.add(W);
				}
				if (current.getDirection() == 0) {
					E.setDirection(2);
					stk.push(2);
					q.add(E);
				}
			}
			
			/*
			   xxxxx	
			   x   x
			xxxx   xxxx	
			x##       x
			xxxx   xxxx
			   x   x
			   xxxxx	
			 */
			if (W.getCont() == 'x' && N.getCont() != 'x' && S.getCont() != 'x' && E.getCont() != 'x') {
				if (current.getDirection() == 1) {
					S.setDirection(1);
					stk.push(1);
					q.add(S);
					
				}
				if (current.getDirection() == 3) {
					N.setDirection(0);
					stk.push(0);
					q.add(N);
					
				}
				if (current.getDirection() == 0) {
					E.setDirection(2);
					stk.push(2);
					q.add(E);
					
				}
			}
			
			/*
			   xxxxx	
			   x   x
			xxxx   xxxx	
			x      ###x
			xxxx   xxxx
			   x   x
			   xxxxx	
			 */
			if (W.getCont() != 'x' && N.getCont() != 'x' && S.getCont() != 'x' && E.getCont() == 'x') {
				if (current.getDirection() == 1) {
					W.setDirection(3);
					stk.push(3);
					q.add(W);
				}
				if (current.getDirection() == 2) {
					S.setDirection(1);
					stk.push(1);
					q.add(S);
				}
				if (current.getDirection() == 0) {
					N.setDirection(0);
					stk.push(0);
					q.add(N);
				}
			}
			
			/*
			   xxxxx	
			   x   x
			xxxx   xxxx	
			x         x
			xxxx # xxxx
			   x # x
			   xxxxx	
			 */
			if (W.getCont() != 'x' && N.getCont() != 'x' && S.getCont() == 'x' && E.getCont() != 'x')  {
				if (current.getDirection() == 1) {
					W.setDirection(3);
					stk.push(3);
					q.add(W);
				}
				if (current.getDirection() == 2) {
					E.setDirection(2);
					stk.push(2);
					q.add(E);
				}
				if (current.getDirection() == 3) {
					N.setDirection(0);
					stk.push(0);
					q.add(N);
				}
			}
			
			/*
			   xxxxx	
			   x # x
			xxxx # xxxx	
			x##       x
			xxxx   xxxx
			   x   x
			   xxxxx	
			 */
			if (W.getCont() == 'x' && N.getCont() == 'x' && S.getCont() != 'x' && E.getCont() != 'x') {
				if (current.getDirection() == 3) {
					S.setDirection(1);
					stk.push(1);
					q.add(S);
				}
				if (current.getDirection() == 0) {
					E.setDirection(2);
					stk.push(2);
					q.add(E);
				}
			}
			
			/*
			   xxxxx	
			   x   x
			xxxx   xxxx	
			x##     ##x
			xxxx   xxxx
			   x   x
			   xxxxx	
			 */
			if (W.getCont() == 'x' && N.getCont() != 'x' && S.getCont() != 'x' && E.getCont() == 'x') {
				if (current.getDirection() == 1) {
					S.setDirection(1);
					stk.push(1);
					q.add(S);
				}
				if (current.getDirection() == 0) {
					N.setDirection(0);
					stk.push(0);
					q.add(N);
				}
			}
			
			/*
			   xxxxx	
			   x   x
			xxxx   xxxx	
			x##       x
			xxxx # xxxx
			   x # x
			   xxxxx	
			 */
			if (W.getCont() == 'x' && N.getCont() != 'x' && S.getCont() == 'x' && E.getCont() != 'x') {
				if (current.getDirection() == 1) {
					E.setDirection(2);
					stk.push(2);
					q.add(E);
				}
				if (current.getDirection() == 3) {
					N.setDirection(0);
					stk.push(0);
					q.add(N);
				}
			}
			
			/*
			   xxxxx	
			   x # x
			xxxx # xxxx	
			x      ###x
			xxxx   xxxx
			   x   x
			   xxxxx	
			 */
			if (W.getCont() != 'x' && N.getCont() == 'x' && S.getCont() != 'x' && E.getCont() == 'x') {
				if (current.getDirection() == 2) {
					S.setDirection(1);
					stk.push(1);
					q.add(S);
				}
				if (current.getDirection() == 0) {
					W.setDirection(3);
					stk.push(3);
					q.add(W);
				}
			}
			
			/*
			   xxxxx	
			   x # x
			xxxx # xxxx	
			x         x
			xxxx # xxxx
			   x # x
			   xxxxx	
			 */
			if (W.getCont() != 'x' && N.getCont() == 'x' && S.getCont() == 'x' && E.getCont() != 'x') {
				if (current.getDirection() == 2) {
					E.setDirection(2);
					stk.push(2);
					q.add(E);
				}
				if (current.getDirection() == 3) {
					W.setDirection(3);
					stk.push(3);
					q.add(W);
				}
			}
			
			
			/*
			   xxxxx	
			   x   x
			xxxx   xxxx	
			x       ##x
			xxxx # xxxx
			   x # x
			   xxxxx	
			 */
			if (W.getCont() != 'x' && N.getCont() != 'x' && S.getCont() == 'x' && E.getCont() == 'x') {
				if (current.getDirection() == 2) {
					N.setDirection(0);
					stk.push(0);
					q.add(N);
				}
				if (current.getDirection() == 1) {
					W.setDirection(3);
					stk.push(3);
					q.add(W);
				}
			}
			
			/*
			   xxxxx	
			   x # x
			xxxx # xxxx	
			x##       x
			xxxx # xxxx
			   x # x
			   xxxxx	
			 */
			if (W.getCont() == 'x' && N.getCont() == 'x' && S.getCont() == 'x' && E.getCont() != 'x') {
				if (current.getDirection() == 3) {
					E.setDirection(2);
					stk.push(2);
					q.add(E);
				}
			}
			
			/*
			   xxxxx	
			   x # x
			xxxx # xxxx	
			x       ##x
			xxxx # xxxx
			   x # x
			   xxxxx	
			 */
			if (W.getCont() != 'x' && N.getCont() == 'x' && S.getCont() == 'x' && E.getCont() == 'x') {
				if (current.getDirection() == 2) {
					W.setDirection(3);
					stk.push(3);
					q.add(W);
				}
			}
			
			/*
			   xxxxx	
			   x # x
			xxxx # xxxx	
			x##     ##x
			xxxx   xxxx
			   x   x
			   xxxxx	
			 */
			if (W.getCont() == 'x' && N.getCont() == 'x' && S.getCont() != 'x' && E.getCont() == 'x') {
				if (current.getDirection() == 0) {
					S.setDirection(1);
					stk.push(1);
					q.add(S);
				}
			}
			

			/*
			   xxxxx	
			   x   x
			xxxx   xxxx	
			x##     ##x
			xxxx # xxxx
			   x # x
			   xxxxx	
			 */
			//if (W.getCont() == 'x' && N.getCont() != 'x' && S.getCont() == 'x' && E.getCont() == 'x') {
				//if (current.getDirection() == 1) {
					//N.setDirection(0);
					//stk.push(0);
					//q.add(N);
				//}
			//}
			

	
			
		}
	}
	
	public void move4(Level level) {
		Queue<Location> q = new LinkedList<Location>();
		Stack<Integer> stk = new Stack<Integer>();
		Location N, S, E, W;
		Location start = location;
		start.setDirection(0);
		q.add(start);
		

		while (!q.isEmpty()) {
			
			Location current = q.poll();
			
			Location cloneS = current.clone();
			Location cloneE = current.clone();
			Location cloneN = current.clone();
			Location cloneW = current.clone();
			cloneS.moveByDirection(SOUTH);
			cloneE.moveByDirection(EAST);
			cloneN.moveByDirection(NORTH);
			cloneW.moveByDirection(WEST);
			
			
			N = level.getTiles()[current.getRow() - 1][current.getCol()];
			S = level.getTiles()[current.getRow() + 1][current.getCol()];
			E = level.getTiles()[current.getRow()][current.getCol() + 1];
			W = level.getTiles()[current.getRow()][current.getCol() - 1];
			
			if (current.getCol() == level.getCherryOrigin().getCol() && current.getRow() == level.getCherryOrigin().getRow()) {
				while (!stk.isEmpty()) {
					int i = stk.pop();
					path1.push(i);
				}
			}
			
			/*
			   xxxxx	
			   x   x
			xxxx   xxxx	
			x         x
			xxxx   xxxx
			   x   x
			   xxxxx	
			 */
			
			if (W.getCont() != 'x' && N.getCont() != 'x' && S.getCont() != 'x' && E.getCont() != 'x'){
				if (current.getDirection() == 1) {
					E.setDirection(2);
					stk.push(2);
					q.add(E);
				}
				if (current.getDirection() == 0) {
					W.setDirection(3);
					stk.push(3);
					q.add(W);
				}
				if (current.getDirection() == 3) {
					S.setDirection(1);
					stk.push(1);
					q.add(S);
				}
				if (current.getDirection() == 2) {
					N.setDirection(0);
					stk.push(0);
					q.add(N);
				}
			}

			/*
			   xxxxx	
			   x # x
			xxxx # xxxx	
			x         x
			xxxx   xxxx
			   x   x
			   xxxxx	
			 */
			if (W.getCont() != 'x' && N.getCont() == 'x' && S.getCont() != 'x' && E.getCont() != 'x') {
				if (current.getDirection() == 2) {
					E.setDirection(2);
					stk.push(2);
					q.add(E);
				}
				if (current.getDirection() == 3) {
					S.setDirection(1);
					stk.push(1);
					q.add(S);
				}
				if (current.getDirection() == 0) {
					W.setDirection(3);
					stk.push(3);
					q.add(W);
				}
			}
			
			/*
			   xxxxx	
			   x   x
			xxxx   xxxx	
			x      ###x
			xxxx   xxxx
			   x   x
			   xxxxx	
			 */
			if (W.getCont() != 'x' && N.getCont() != 'x' && S.getCont() != 'x' && E.getCont() == 'x') {
				if (current.getDirection() == 1) {
					S.setDirection(1);
					stk.push(1);
					q.add(S);
				}
				if (current.getDirection() == 2) {
					N.setDirection(0);
					stk.push(0);
					q.add(N);
				}
				if (current.getDirection() == 0) {
					W.setDirection(3);
					stk.push(3);
					q.add(W);
				}
			}
			
			/*
			   xxxxx	
			   x   x
			xxxx   xxxx	
			x         x
			xxxx # xxxx
			   x # x
			   xxxxx	
			 */
			if (W.getCont() != 'x' && N.getCont() != 'x' && S.getCont() == 'x' && E.getCont() != 'x')  {
				if (current.getDirection() == 1) {
					E.setDirection(2);
					stk.push(2);
					q.add(E);
				}
				if (current.getDirection() == 2) {
					N.setDirection(0);
					stk.push(0);
					q.add(N);
				}
				if (current.getDirection() == 3) {
					W.setDirection(3);
					stk.push(3);
					q.add(W);
				}
			}
			
			/*
			   xxxxx	
			   x   x
			xxxx   xxxx	
			x##       x
			xxxx   xxxx
			   x   x
			   xxxxx	
			 */
			if (W.getCont() == 'x' && N.getCont() != 'x' && S.getCont() != 'x' && E.getCont() != 'x') {
				if (current.getDirection() == 1) {
					E.setDirection(2);
					stk.push(2);
					q.add(E);
					
				}
				if (current.getDirection() == 3) {
					S.setDirection(1);
					stk.push(1);
					q.add(S);
					
				}
				if (current.getDirection() == 0) {
					N.setDirection(0);
					stk.push(0);
					q.add(N);
					
				}
			}
			
			/*
			   xxxxx	
			   x # x
			xxxx # xxxx	
			x##       x
			xxxx   xxxx
			   x   x
			   xxxxx	
			 */
			if (W.getCont() == 'x' && N.getCont() == 'x' && S.getCont() != 'x' && E.getCont() != 'x') {
				if (current.getDirection() == 3) {
					S.setDirection(1);
					stk.push(1);
					q.add(S);
				}
				if (current.getDirection() == 0) {
					E.setDirection(2);
					stk.push(2);
					q.add(E);
				}
			}
			
			/*
			   xxxxx	
			   x # x
			xxxx # xxxx	
			x      ###x
			xxxx   xxxx
			   x   x
			   xxxxx	
			 */
			if (W.getCont() != 'x' && N.getCont() == 'x' && S.getCont() != 'x' && E.getCont() == 'x') {
				if (current.getDirection() == 2) {
					S.setDirection(1);
					stk.push(1);
					q.add(S);
				}
				if (current.getDirection() == 0) {
					W.setDirection(3);
					stk.push(3);
					q.add(W);
				}
			}
			
			/*
			   xxxxx	
			   x   x
			xxxx   xxxx	
			x       ##x
			xxxx # xxxx
			   x # x
			   xxxxx	
			 */
			if (W.getCont() != 'x' && N.getCont() != 'x' && S.getCont() == 'x' && E.getCont() == 'x') {
				if (current.getDirection() == 2) {
					N.setDirection(0);
					stk.push(0);
					q.add(N);
				}
				if (current.getDirection() == 1) {
					W.setDirection(3);
					stk.push(3);
					q.add(W);
				}
			}
			
			/*
			   xxxxx	
			   x   x
			xxxx   xxxx	
			x##       x
			xxxx # xxxx
			   x # x
			   xxxxx	
			 */
			if (W.getCont() == 'x' && N.getCont() != 'x' && S.getCont() == 'x' && E.getCont() != 'x') {
				if (current.getDirection() == 1) {
					E.setDirection(2);
					stk.push(2);
					q.add(E);
				}
				if (current.getDirection() == 3) {
					N.setDirection(0);
					stk.push(0);
					q.add(N);
				}
			}
			
			/*
			   xxxxx	
			   x # x
			xxxx # xxxx	
			x         x
			xxxx # xxxx
			   x # x
			   xxxxx	
			 */
			if (W.getCont() != 'x' && N.getCont() == 'x' && S.getCont() == 'x' && E.getCont() != 'x') {
				if (current.getDirection() == 2) {
					E.setDirection(2);
					stk.push(2);
					q.add(E);
				}
				if (current.getDirection() == 3) {
					W.setDirection(3);
					stk.push(3);
					q.add(W);
				}
			}
			
			/*
			   xxxxx	
			   x   x
			xxxx   xxxx	
			x##     ##x
			xxxx   xxxx
			   x   x
			   xxxxx	
			 */
			if (W.getCont() == 'x' && N.getCont() != 'x' && S.getCont() != 'x' && E.getCont() == 'x') {
				if (current.getDirection() == 1) {
					S.setDirection(1);
					stk.push(1);
					q.add(S);
				}
				if (current.getDirection() == 0) {
					N.setDirection(0);
					stk.push(0);
					q.add(N);
				}
			}
			
			/*
			   xxxxx	
			   x # x
			xxxx # xxxx	
			x       ##x
			xxxx # xxxx
			   x # x
			   xxxxx	
			 */
			if (W.getCont() != 'x' && N.getCont() == 'x' && S.getCont() == 'x' && E.getCont() == 'x') {
				if (current.getDirection() == 2) {
					W.setDirection(3);
					stk.push(3);
					q.add(W);
				}
			}
			

			/*
			   xxxxx	
			   x # x
			xxxx # xxxx	
			x##       x
			xxxx # xxxx
			   x # x
			   xxxxx	
			 */
			if (W.getCont() == 'x' && N.getCont() == 'x' && S.getCont() == 'x' && E.getCont() != 'x') {
				if (current.getDirection() == 3) {
					E.setDirection(2);
					stk.push(2);
					q.add(E);
				}
			}
			
			/*
			   xxxxx	
			   x   x
			xxxx   xxxx	
			x##     ##x
			xxxx # xxxx
			   x # x
			   xxxxx	
			 */
			if (W.getCont() == 'x' && N.getCont() != 'x' && S.getCont() == 'x' && E.getCont() == 'x') {
				if (current.getDirection() == 1) {
					N.setDirection(0);
					stk.push(0);
					q.add(N);
				}
			}
			
			/*
			   xxxxx	
			   x # x
			xxxx # xxxx	
			x##     ##x
			xxxx   xxxx
			   x   x
			   xxxxx	
			 */
			//if (W.getCont() == 'x' && N.getCont() == 'x' && S.getCont() != 'x' && E.getCont() == 'x') {
				//if (current.getDirection() == 0) {
					//S.setDirection(1);
					//stk.push(1);
					//q.add(S);
				//}
			//}
			
			
			
			
		}	

	}
	
	public void move5(Level level) {
		Location nextLocation = location.clone();
		
		nextLocation.moveByDirection(this.getFacingDirection());
		if(level.isValidLocation(nextLocation))
			location.moveByDirection(this.getFacingDirection());
	}

	

	@Override
	public boolean collide(GameObject object) {
		if (getLocation().equals(object.getLocation())) {

			return true;
		} else
			return false;
	}

	@Override
	public int getFacingDirection() {
		// TODO Auto-generated method stub
		return super.getFacingDirection();
	}

	@Override
	public void setFacingDirection(int direction) {
		// TODO Auto-generated method stub
		super.setFacingDirection(direction);
	}

	public void printMark(Location Perfect, Level lev) {
		while (Perfect.getParent() != null) { // changes contents until it meets
												// null pointer.
			path.push(Perfect.getDirection());
			Perfect = Perfect.getParent();
			lev.getTiles()[Perfect.getRow()][Perfect.getCol()].setCont('*');

		}
		path.push(ORIGIN);

	}

	public Stack<Integer> getPath() {
		return this.path;
	}
	
	public Stack<Integer> getPath1() {
		return this.path1;
	}

	public Location getLocation() {
		return location;
	}

	@Override
	public void draw(Graphics g) {
		g.translate(location.getCol() * MakePanel.IMAGE_WIDTH
				+ MakePanel.IMAGE_WIDTH / 2, location.getRow()
				* MakePanel.IMAGE_HEIGHT + MakePanel.IMAGE_HEIGHT / 2);

		switch (getFacingDirection()) {
		case Movable.NORTH:
			((Graphics2D) g).rotate(-Math.PI / 2);
			break;
		case Movable.SOUTH:
			((Graphics2D) g).rotate(Math.PI / 2);
			break;
		case Movable.WEST:
			((Graphics2D) g).rotate(Math.PI);
			break;

		}

		g.translate(-location.getCol() * MakePanel.IMAGE_WIDTH
				- MakePanel.IMAGE_WIDTH / 2, -location.getRow()
				* MakePanel.IMAGE_HEIGHT - MakePanel.IMAGE_HEIGHT / 2);
		super.draw(g);
	}

}
