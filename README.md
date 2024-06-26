
# Sudoku Game

Welcome to the Sudoku Game project! This is a Java-based implementation of the classic Sudoku puzzle game.

## Features

- Interactive Sudoku game board
- Ability to input and validate numbers
- Sudoku puzzle generation and solving algorithms
- User-friendly interface

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/swappy-2003/javaproject.git
   ```
2. Navigate to the project directory:
   ```sh
   cd javaproject
   ```
3. Compile the Java files:
   ```sh
   javac *.java
   ```

### Running the Game

To run the Sudoku game, execute:
```sh
java SudokuGame
```

## Classes and Logic

1. **SudokuGame**: 
   - This is the main class that initializes the game and handles the overall game logic.

2. **Board**: 
   - Manages the Sudoku board, including setting and getting values of cells, and checking if the board is solved.

3. **Cell**: 
   - Represents a single cell in the Sudoku grid, storing its value and state (e.g., if it is a fixed or user-input cell).

4. **SudokuSolver**: 
   - Implements algorithms to solve the Sudoku puzzle, ensuring that the board adheres to Sudoku rules.

5. **SudokuGenerator**: 
   - Generates new Sudoku puzzles by creating a fully solved board and then removing certain numbers to create a playable puzzle.

6. **GUI**: 

https://github.com/swappy-2003/javaproject/assets/122719184/928ad590-8ca8-45e7-a9d4-f68bc513edbc


   - Handles the graphical user interface, drawing the board and managing user interactions like input and validation.

7. **GameController**: 
   - Acts as an intermediary between the GUI and the game logic, updating the board based on user input and game state.

## Usage

- Use the mouse or keyboard to input numbers into the Sudoku grid.
- The game will highlight any errors in the input.
- Solve the puzzle by filling all the cells with the correct numbers.

## Video Demo

Watch the demo video to see the Sudoku game in action:

[Sudoku Game Demo](resources/Sudokugame demovideo(1).mp4)


## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests.

## License

This project is licensed under the MIT License.

## Contact

For any questions or feedback, please contact [swappy-2003](https://github.com/swappy-2003).
