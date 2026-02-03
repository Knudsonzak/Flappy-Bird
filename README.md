# Flappy Bird

A Java implementation of the classic Flappy Bird game using Swing for graphics and user interface.

## Description

This is a recreation of the popular Flappy Bird game built with Java. Navigate a bird through a series of green pipes by controlling its vertical position. The game features smooth animations, collision detection, and a scoring system.

## Features

- 🐦 Smooth bird physics with gravity and flapping mechanics
- 🎮 Simple one-button control scheme (Spacebar to flap)
- 🌳 Procedurally positioned obstacles (pipes)
- 💥 Collision detection with pipes and boundaries
- 📊 Score tracking system
- 🎨 Custom graphics and background

## How to Play

1. **Start the Game**: Run the application to launch the game window
2. **Press Spacebar**: Make the bird flap and gain altitude
3. **Navigate**: Guide the bird through the gaps between the pipes
4. **Avoid Obstacles**: Don't hit the pipes, ground, or ceiling
5. **Score Points**: Successfully pass through pipes to increase your score

## Controls

- `SPACEBAR` - Make the bird flap/jump

## Requirements

- Java Development Kit (JDK) 8 or higher
- The following image files in the project directory:
  - `FlappyBackDrop.jpeg` - Background image
  - `bird3.jpeg` - Bird sprite

## Project Structure

```
Flappy-Bird/
├── FlappyBird.java    # Main game class with game loop and rendering
├── Bird.java          # Bird class with physics and movement
├── Poles.java         # Obstacle class for pipes
└── README.md          # Project documentation
```

## How to Run

### Using Command Line

1. Compile the Java files:
   ```bash
   javac FlappyBird.java Bird.java Poles.java
   ```

2. Run the game:
   ```bash
   java FlappyBird
   ```

### Using VS Code

1. Open the project folder in VS Code
2. Press `F5` or click "Run" on the `FlappyBird.java` file
3. The game window will appear

## Technical Details

- **Game Window**: 850x460 pixels
- **Frame Rate**: ~67 FPS (15ms timer delay)
- **Bird Physics**: Gravity acceleration with upward velocity on flap
- **Obstacles**: 5 pipes cycling across the screen
- **Collision Detection**: Rectangle-based bounds checking

## Code Overview

### FlappyBird.java
The main game controller that handles:
- Window setup and rendering
- Game loop and timer management
- Collision detection
- Score tracking
- Keyboard input

### Bird.java
Manages the bird entity:
- Position and movement
- Gravity and flapping physics
- Sprite rendering
- Collision bounds

### Poles.java
Handles obstacle generation:
- Random pipe gap positioning
- Drawing top and bottom pipes
- Collision bounds calculation
- Horizontal movement

## Future Enhancements

- [ ] Add start menu and game over screen
- [ ] Implement high score persistence
- [ ] Add sound effects and background music
- [ ] Include difficulty levels
- [ ] Add visual effects for collisions
- [ ] Implement pause functionality

## License

This project is open source and available for educational purposes.

## Author

Created as a Java programming project demonstrating game development concepts with Swing.
