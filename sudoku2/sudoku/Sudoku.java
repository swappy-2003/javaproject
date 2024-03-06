

package sudoku2.sudoku;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;


/**
 * The Sudoku class is the center of the Sudoku game
 * 
 * @author Eric Smyth
 */
public class Sudoku extends JPanel implements ActionListener
{
	//Private Static Final Fields
	private static final int N = 9;
	private static final int HARD = N, MED = 2 * N, EASY = 4*N;
	private static final long serialVersionUID = 1;
	
	//Private Fields
	private Move[][] board = new Move[N][N];
	private JFrame f;
	private Container cp;
	private Menu m;
	private int clickCount = 0;
    
	/**
	 * Creates a new Sudoku object
	 * @param args Ignored
	 */
    public static void main(String[] args) {
        // Start the game by displaying the start screen
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartScreen();
            }
        });
    }
	
	/**
	 * Constructs a new Sudoku game
	 */
	public Sudoku()
	{
		init();
		
		f = new JFrame("Sudoku");
		cp = f.getContentPane();
		m = new Menu(this);
		f.setJMenuBar(m.createMenuBar());
		cp.add(this);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1000, 1000);
		f.setResizable(true);
		f.setVisible(true);	
		
		GridLayout grid = new GridLayout(N, N); 
		grid.setVgap(2);
		grid.setHgap(2);

		this.setLayout(grid);

	}
	
	//Implemented Methods
	
	/**
	 * The actionPerformed method is required by implementation of
	 * the ActionListener interface. The method is invoked whenever
	 * an action is performed
	 */
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() instanceof Move)
		{
			Move temp = (Move)e.getSource();
			String in = JOptionPane.showInputDialog(null);
		
			clickCount++;
		
			try
			{
				temp.setValue(Integer.parseInt(in));
			}
			catch (NumberFormatException err)
			{
			
			}
		}
	}
	
	//Public Methods
	
	/**
	 * The about method displays an about dialog to the user
	 */
	public void about()
	{
		String str = "Author: Swapnilkumar dwivedi\nClass: SYBSC - IT\n";
		String title = "About - Sudoku v" + serialVersionUID;
		
		JOptionPane.showMessageDialog(this, str, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * The clear method clears all user specified entries from the board
	 */
	public void clear()
	{
		for(int row = 0; row < 9; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				board[row][col].clear();
			}
		}
	}
	
	/**
	 * The help method displays a help dialog for the user
	 */
	public void help()
	{
		String str = "Fill in the grid so that\n     every row,\n" +
				"     every column, and\n     every 3 x 3 box\ncontains" +
				" the digits 1 through 9.\n\nTo create custom game\n    1) " +
				"Reset the Board\n    2) Set values\n    3) Select custom from the new game menu";
		String title = "Sudoku v" + serialVersionUID + " - Help";
		
		JOptionPane.showMessageDialog(this, str, title, JOptionPane.INFORMATION_MESSAGE);		
	}
	
	/**
	 * The new custom game allows the user to specify a number of
	 * elements to be non modifiable. Allows recreation of Sudoku
	 * puzzles seen elsewhere
	 */
	public void newCustom()
	{
		for(int row = 0; row < 9; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				if(board[row][col].getValue() != 0)
				{
					board[row][col].setUnMod();
					board[row][col].setForeground(Color.black);
				}
				board[row][col].clear();
			}
		}		
	}
	
	/**
	 * Creates a new game with the number of pre filled squares
	 * determined by the difficulty. 1 - Easy, 2 - Medium, 3 - Hard
	 * 
	 * @param diff The specified difficulty level
	 */
	public void newGame(int diff)
	{
		reset();
		
		ArrayList<Integer> valid = new ArrayList<Integer>(spaceCollection());
		int randRow = 0, randCol = 0, randVal = 0, count = 0;
		
		switch(diff)
		{
			case 1:
				count = EASY;
				break;
			
			case 2:
				count = MED;
				break;
			
			case 3:
				count = HARD;
				break;
		}
		
		while(count > 0)
		{
			randRow = valid.get((int)(Math.random() * N));
			randCol = valid.get((int)(Math.random() * N));
			randVal = 1 + (int)(Math.random() * N);
			
			if(validMove(randRow, randCol, randVal))
			{
				board[randRow][randCol].setForeground(Color.black);
				board[randRow][randCol].setValue(randVal);
				board[randRow][randCol].setUnMod();
				count--;
			}
		}
	}
	
	/**
	 * The quit method exits the game
	 */
	public void quit()
	{
		f.dispose();
	}

	/**
	 * The reset method is used to clear all squares on the board
	 * including the unchangable game squares
	 */
	public void reset()
	{
		for(int row = 0; row < 9; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				board[row][col].reset();
			}
		}
	}
	
	
	/**
	 * The verifySudoku verifies that a solution is correct and 
	 * displays a message to the user in addition to returning a
	 * boolean indicating valid or invalid
	 * 
	 * @return True if the solution is valid
	 */
	public boolean verifySudoku()
	{
		boolean result = true;
		
		for(int i = 0; i < N; i++)
		{
			if(!validRow(i) || !validCol(i) || !validCube(i))
			{
				result = false;
				break;
			}
		}
		
		if (checkEmpty())
		{
			result = false;
		}
		
		if(result)
		{
			JOptionPane.showMessageDialog(this, "Congratulations\nYou took " + clickCount + " moves", "", JOptionPane.INFORMATION_MESSAGE);			
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Not Quite There Yet", "", JOptionPane.ERROR_MESSAGE);
		}
		
		return result;
	}
	
	//Private methods
	
	/**
	 * The spaceCollection method returns an Integer ArrayList containing
	 * the values 0 through N - 1 corresponding to indecies for the rows
	 * columns and cubes
	 */
	private ArrayList<Integer> spaceCollection ()
	{
		ArrayList<Integer> spaceList = new ArrayList<Integer>();
		for(int i = 0; i < N; i++)
		{
			spaceList.add(i);
		}
		
		return spaceList;
	}
	
	/**
	 * The validMove method is used to check if a certian move is valid
	 *
	 * @param row The row on the board
	 * @param col The column on the board
	 * @param move The value to place at the position
	 * @return True if valid
	 */
	private boolean validMove(int row, int col, int move)
	{
		int orig = 0;
		boolean result = false;
		
		if(board[row][col].isModifiable())
		{
			orig = board[row][col].getValue();
					
			board[row][col].setValue(move);
		
			if(validRow(row) && validCol(col) && validCube(findCube(row, col)))
			{
				result = true;
			}
		
			board[row][col].setValue(orig);
		}
		
		return result;
	}
	
	/**
	 * The validRow method is used to determine if the current row is
	 * valid, ignoring empty squares
	 * 
	 * @param row The row index
	 * @return True if valid
	 */
	private boolean validRow(int row)
	{
		ArrayList<Integer> num = new ArrayList<Integer> (Move.initValid());
		boolean valid = true;
		
		for(int i = 0; i < 9; i++)
		{
			Integer temp = board[row][i].getValue();
			if(num.contains(temp))
			{
				num.remove(temp);
			}
			else if (temp != 0)
			{
				valid = false;
				break;
			}
		}
		
		return valid;
	}
	
	/**
	 * The validCol method is used to determine if the current column
	 * is valid, ignoring empty squares
	 * 
	 * @param col The column index
	 * @return True if valid
	 */	
	private boolean validCol(int col)
	{
		ArrayList<Integer> num = new ArrayList<Integer> (Move.initValid());
		boolean valid = true;
		
		for(int i = 0; i < 9; i++)
		{
			Integer temp = board[i][col].getValue();
			if(num.contains(temp))
			{
				num.remove(temp);
			}
			else if (temp != 0)
			{
				valid = false;
				break;
			}
		}
		
		return valid;
	}
	
	/**
	 * The validCube method is used to determine if the current cube is
	 * valid, ignoring empty squares
	 * 
	 * @param cube The cube index
	 * @return True if valid
	 */
	private boolean validCube(int cube)
	{
		ArrayList<Integer> num = new ArrayList<Integer> (Move.initValid());
		int enterRow = 0, enterCol = 0;
		boolean valid = true;
		
		switch(cube)
		{
			case 1:
				enterCol = 3;
				break;
				
			case 2: 
				enterCol = 6;
				break;
				
			case 3:
				enterRow = 3;
				break;
				
			case 4:
				enterRow = 3;
				enterCol = 3;
				break;
				
			case 5:
				enterRow = 3;
				enterCol = 6;
				break;
				
			case 6:
				enterRow = 6;
				break;
				
			case 7: 
				enterRow = 6;
				enterCol = 3;
				break;
				
			case 8:
				enterRow = 6;
				enterCol = 6;
				break;
		}
		
		for(int i = enterRow; i < enterRow + 3 ; i++)
		{
			for(int j = enterCol; j < enterCol + 3 ; j++)
			{
				Integer temp = board[i][j].getValue();
				if(temp != 0)
				{
					if(num.contains(temp))
					{
						num.remove(temp);
					}
					else
					{
						valid = false;
						break;
					}
				}
			}
		}
		
		return valid;
	}
	
	/**
	 * The find cube method is used to determine the cube based on 
	 * the row and column
	 * @param r The row
	 * @param c The column
	 * @return The cube number
	 */
	private int findCube(int r, int c)
	{
		int cube = 0;
		
		if(r < 3 && c < 3)
		{
			cube = 0;
		}
		else if (r < 3 && c < 6)
		{
			cube = 1;
		}
		else if (r < 3)
		{
			cube = 2;
		}
		else if(r < 6 && c < 3)
		{
			cube = 3;
		}
		else if (r < 6 && c < 6)
		{
			cube = 4;
		}
		else if (r < 6)
		{
			cube = 5;
		}
		else if(c < 3)
		{
			cube = 6;
		}
		else if (c < 6)
		{
			cube = 7;
		}
		else
		{
			cube = 8;
		}
		
		return cube;
	}



	public void solve() {
        int[][] puzzle = new int[N][N];
        
        // Populate the puzzle with current values from the board
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                puzzle[row][col] = board[row][col].getValue();
            }
        }
        
        // Create a SudokuSolver instance and solve the puzzle
        SudokuSolver solver = new SudokuSolver();
        if (solver.solveSudoku(puzzle)) {
            // Update the board with the solved puzzle
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    board[row][col].setValue(puzzle[row][col]);
                }
            }
            JOptionPane.showMessageDialog(this, "Sudoku puzzle solved successfully!", "Solved", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No solution exists for the Sudoku puzzle.", "Unsolvable", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	/**
	 * The checkEmpty() is used to determine if there are any empty
	 * squares on the board
	 * @return True if empty squares exist
	 */
	private boolean checkEmpty()
	{
		boolean result = false;
		int row, col;
		
		for(row = 0; row < 9; row++)
		{
			for(col = 0; col < 9; col++)
			{
				if(board[row][col].getValue() == 0)
				{
					result = true;
					break;
				}
			}
			if(col < 9)
			{
				break;
			}
		}
		
		return result;
	}
	
	/**
	 * The init method is called once to set up the board
	 */
	private void init()
	{
		Font font = new Font("Ariel", Font.PLAIN, 45);
		Move m;
		
		for(int row = 0; row < 9; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				m = new Move(row, col);
				m.setBackground(Color.blue);
				
				if (row <= 2 && (col <= 2 || col > 5)){
					m.setBackground (Color.red);
				}
				if (row >2 && row <= 5 &&  col > 2 && col  <= 5){
					m.setBackground (Color.red);
				}
				if (row > 5 && ((col <= 2) || (col > 5))){
					m.setBackground (Color.red);
				}
				
				m.setValue(0);
				m.addActionListener(this);
				m.setFont(font);
				m.setForeground(Color.white);
				m.setFocusable(false);
				
				add(m);
				board[row][col] = m;
			}
		}
	}
}
