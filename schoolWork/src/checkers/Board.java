package checkers;
/**
 * @author Tom Haumersen
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Board extends JPanel {
	private boolean redTurn = true;
	private boolean chaining = false;

	private Piece[] checkers;
	// private Point highlight = new Point(-1, -1);
	private Piece selected;
	private Piece chainer = null;

	Board() {
		this.setSize(100, 100);
		this.setBackground(Color.YELLOW);
		this.setOpaque(true);
		addMouseListener(new MListener());
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i % 2 == 0) {
					if (j % 2 == 0)
						g2.setColor(Color.BLUE);
					else
						g2.setColor(Color.GREEN);
				} else {
					if (j % 2 == 0)
						g2.setColor(Color.GREEN);
					else
						g2.setColor(Color.BLUE);
				}
				g2.fillRect(i * 50, j * 50, 50, 50);
			}
		}
		for (Piece p : checkers) {
			drawPiece(p, g2);
		}
		if (selected != null) {
			g2.setColor(Color.RED);
			g2.drawRect((int) selected.getX() * 50 + 1,
					(int) selected.getY() * 50 + 1, 47, 47);
		}
		g2.setColor(Color.MAGENTA);
		g2.fillRect(400, 0, 50, 400);
		g2.fillRect(0, 400, 450, 50);
		String turn = (redTurn) ? "Red's Turn" : "Black's Turn";
		g2.setColor((redTurn) ? Color.RED : Color.BLACK);
		g2.setFont(new Font("Serif", Font.BOLD, 20));
		g2.drawString(turn, 150, 420);

	}

	void drawPiece(Piece p, Graphics2D g) {
		if (p == null)
			return;
		int x = p.getX() * 50 + 13;
		int y = p.getY() * 50 + 13;
		if (p.isRed) {
			g.setColor(Color.RED);
			if (p.getY() == 7)
				p.king();
		} else {
			g.setColor(Color.BLACK);
			if (p.getY() == 0)
				p.king();
		}
		g.fillOval(x, y, 25, 25);
		if (p.isKing) {
			if (p.isRed)
				g.setColor(Color.BLACK);
			else
				g.setColor(Color.RED);
			g.drawString("K", x + 10, y + 15);
		}
	}

	void setCheckers(Piece[] p) {
		this.checkers = p;
	}

	private class MListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			repaint();
			//if there are no pieces for a player and it is there turn, they lose
			/*
			for(Piece p: checkers){
				if(p != null){
					if(p.isRed == redTurn);
				}
			}
			*/
			int x = e.getX() / 50;
			int y = e.getY() / 50;
			// moving/jumping

			movePiece(selected, x, y);

			// selecting a piece
			if (hasPiece(x, y)) {
				if (pieceAt(x, y).isRed == redTurn) {

					selectPiece(x, y);
				}
			} else {
				deselect();
			}

			System.out.println(x + " " + y + (pieceAt(x, y) == null));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}

	public void movePiece(Piece p, int x, int y) {
		boolean validMove = false;
		boolean jumped = false;
		if (p == null)
			return;
		if (chainer != null) {
			if (!p.equalz(chainer)) {
				System.out.println("not the chainer, selecte the chaining piece");
				return;
			}
		}

		if (!hasPiece(x, y)) {
			// for a move
			if (!canJump(p)) {
				if (Math.abs(p.getX() - x) == 1) {
					if (p.isRed || p.isKing) {
						if (p.getY() - y == -1) {
							validMove = true;
							System.out.println("Valid move");
						}
					}
					if (!p.isRed || p.isKing) {
						if (p.getY() - y == 1) {
							validMove = true;
							System.out.println("valid move");
						}
					}

				}
			}

			if (canJump(redTurn)) {
				validMove = false;
				System.out.println("had to jump");
			}
			// for a jump
			if(canJump(p)){
				for(int i = 0; i < 4; i++){
					int xShift = (i == 1 || i == 3)? 1 : -1;
					int yShift = (i == 0 || i == 1)? 1 : -1;
					System.out.println(xShift +" " + yShift);
					Piece temp = pieceAt(p.getX() + xShift, p.getY() + yShift);
					if (temp != null) {
						if (temp.isRed != p.isRed) {
							if (p.getX() + (2*xShift) > 7 || p.getY() + (2*yShift) > 7 ||
								p.getX() + (2*xShift) < 0 || p.getY() + (2*yShift) < 0)return;
							
							temp = pieceAt(p.getX() + (2*xShift), p.getY() + (2*yShift));
							if (temp == null && p.getX()+ (2*xShift) == x
									&& p.getY() + (2*yShift) == y) {
								if(((p.isRed || p.isKing) && yShift == 1) ||
										((!p.isRed || p.isKing) && yShift == -1)){
									validMove = true;
									jumped = true;
									System.out.println("JUMP");
									removePiece(p.getX() + xShift, p.getY() + yShift);
									break;
								}
							}
						}
					}
				}
			}
			
		}

		if (x > 7 || x < 0 || y < 0 || y > 7) {
			validMove = false;
		}
		System.out.println("This move was valid:" + validMove);
		//
		if (validMove) {
			for (int i = 0; i < checkers.length; i++) {
				// System.out.println("Looking for piece");
				if (p != null && checkers[i] != null) {
					if (checkers[i].equalz(p)) {
						// System.out.println("Found Piece");
						checkers[i] = p.move(x, y);
						if (checkers[i].isRed) {
							if (checkers[i].getY() == 7)
								checkers[i].king();

						} else {
							if (checkers[i].getY() == 0)
								checkers[i].king();
						}
						if (canJump(checkers[i])) {
							if (jumped) {
								System.out.println("Chaining");
								chainer = checkers[i];
								return;
							}
						}
						deselect();
						redTurn = !redTurn;
						chainer = null;

					}
				}
			}
		}
	}

	private void removePiece(int x, int y) {
		for (int i = 0; i < checkers.length; i++) {
			if (checkers[i] != null) {
				Piece temp = pieceAt(x, y);
				if (temp != null) {
					if (checkers[i].equalz(temp)) {
						checkers[i] = null;
						System.out.println("removed piece at " + x + " " + y);
					}
				}
			}
		}

	}

	boolean canJump(Piece p) {
		int x = p.getX();
		int y = p.getY();
		for(int i = 0; i < 4; i++){
			int xShift = (i == 1 || i == 3)? 1 : -1;
			int yShift = (i == 0 || i == 1)? 1 : -1;
			System.out.println(xShift +" " + yShift);
			Piece temp = pieceAt(p.getX() + xShift, p.getY() + yShift);
			if (temp != null) {
				System.out.println("not null");
				if (temp.isRed != p.isRed) {
					System.out.println("middle is not the same color");
					if (x + (2*xShift) > 7 || y + (2*yShift) > 7 || x + (2*xShift) < 0 || y + (2*yShift) < 0){
						System.out.println("guess off board");
						continue;
					}
					temp = pieceAt(p.getX() + (2*xShift), p.getY() + (2*yShift));
					if (temp == null){
						System.out.println("no piece in guess spot and this where i want to move");
						if(((p.isRed || p.isKing) && yShift == 1) ||
								((!p.isRed || p.isKing) && yShift == -1)){
							
							System.out.println("Can Jump");
							return true;
						}
					}
				}
			}
		}
		System.out.println("cant jump");
		return false;
	}

	boolean canJump(boolean isRed) {
		for (Piece p : checkers) {
			if (p != null) {
				if (p.isRed == isRed) {
					if (canJump(p)) {
						return true;
					}
				}
			}
		}
		return false;
		
	}

	boolean canMove(Piece p) {
		int x = p.getX();
		int y = p.getY();
		if (p.isRed || p.isKing) {
			if (pieceAt(x - 1, y + 1) == null || pieceAt(x + 1, y + 1) == null) {
				return true;
			}
		}
		if (!p.isRed || p.isKing) {
			if (pieceAt(x - 1, y - 1) == null || pieceAt(x + 1, y - 1) == null) {
				return true;
			}
		}
		return false;
	}

	public void deselect() {
		selected = null;
		repaint();
	}

	public Piece pieceAt(int x, int y) {
		for (Piece p : checkers) {
			if (p != null) {
				if (p.getX() == x && p.getY() == y) {
					return p;
				}
			}
		}
		return null;
	}

	public void selectPiece(int x, int y) {
		// highlight = new Point(x, y);
		if (chaining)
			return;
		selected = pieceAt(x, y);
		System.out.println("this piece can jump = " + canJump(selected));
		System.out.println(canMove(selected) + " " + canJump(selected));
		repaint();
	}

	public boolean hasPiece(int x, int y) {
		return pieceAt(x, y) != null;
	}
}