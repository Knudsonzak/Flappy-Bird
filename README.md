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
├── api/               # REST API for scores and leaderboard
│   ├── src/
│   │   └── main/
│   │       ├── java/com/flappybird/api/
│   │       │   ├── FlappyBirdApiApplication.java
│   │       │   ├── controller/ScoreController.java
│   │       │   ├── model/Score.java
│   │       │   └── repository/ScoreRepository.java
│   │       └── resources/
│   │           └── application.properties
│   ├── pom.xml
│   └── README.md      # API documentation
├── render.yaml        # Render deployment configuration
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
- [x] Implement high score persistence with REST API
- [ ] Add sound effects and background music
- [ ] Include difficulty levels
- [ ] Add visual effects for collisions
- [ ] Implement pause functionality
- [ ] Integrate game client with REST API for online leaderboard

## REST API

This project includes a Spring Boot REST API for managing scores and leaderboards. The API can be deployed to Render or any cloud platform.

### API Features

- 📊 Submit and retrieve game scores
- 🏆 Global leaderboard (top 10 scores)
- 👤 Player-specific score history
- 📈 Game statistics

### API Endpoints

- `GET /api/` - API information
- `POST /api/scores` - Submit a new score
- `GET /api/scores` - Get all scores
- `GET /api/leaderboard` - Get top 10 scores
- `GET /api/player/{name}` - Get scores for a specific player
- `GET /api/stats` - Get game statistics

### Running the API Locally

```bash
cd api
mvn clean install
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

For detailed API documentation, see [api/README.md](api/README.md)

## Deploying to Render

### Prerequisites
- GitHub account
- Render account (free tier available)
- Git repository with your code

### Deployment Steps

1. **Push your code to GitHub:**
   ```bash
   git add .
   git commit -m "Add Flappy Bird API"
   git push origin main
   ```

2. **Create a New Web Service on Render:**
   - Go to [render.com](https://render.com)
   - Click "New" → "Blueprint"
   - Connect your GitHub repository
   - Render will automatically detect the `render.yaml` file
   - Click "Apply" to create the service and database

3. **Configure Environment Variables:**
   The `render.yaml` file automatically configures:
   - PostgreSQL database
   - Database connection string
   - Port configuration

4. **Deploy:**
   - Render will automatically build and deploy your API
   - Your API will be live at: `https://your-service-name.onrender.com`

### Alternative: Manual Deployment

1. **Create a PostgreSQL Database:**
   - In Render dashboard, click "New" → "PostgreSQL"
   - Name it `flappybird-db`
   - Copy the Internal Database URL

2. **Create a Web Service:**
   - Click "New" → "Web Service"
   - Connect your repository
   - Configure:
     - **Name:** `flappy-bird-api`
     - **Runtime:** Java
     - **Build Command:** `cd api && mvn clean install`
     - **Start Command:** `cd api && java -jar target/flappy-bird-api-1.0.0.jar`
     - **Environment Variables:**
       - `DATABASE_URL` = (your database internal URL)
       - `DATABASE_DRIVER` = `org.postgresql.Driver`
       - `DATABASE_DIALECT` = `org.hibernate.dialect.PostgreSQLDialect`

3. **Deploy and Test:**
   - Click "Create Web Service"
   - Once deployed, test: `https://your-api.onrender.com/api/leaderboard`

### Testing Your Deployed API

```bash
# Get leaderboard
curl https://your-service-name.onrender.com/api/leaderboard

# Submit a score
curl -X POST https://your-service-name.onrender.com/api/scores \
  -H "Content-Type: application/json" \
  -d '{"playerName":"Player1","score":50}'
```
- [ ] Add sound effects and background music
- [ ] Include difficulty levels
- [ ] Add visual effects for collisions
- [ ] Implement pause functionality

## License

This project is open source and available for educational purposes.

## Author

Created as a Java programming project demonstrating game development concepts with Swing.
