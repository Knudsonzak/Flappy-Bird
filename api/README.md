# Flappy Bird API

A REST API for tracking scores and leaderboard for the Flappy Bird game, built with Node.js and Express.

## API Endpoints

### Base URL
```
http://localhost:8080/api
```

### Endpoints

#### 1. Get API Info
```
GET /api/
```
Returns basic API information.

**Response:**
```json
{
  "message": "Flappy Bird API",
  "version": "1.0",
  "endpoints": "/api/scores, /api/leaderboard, /api/player/:name, /api/stats"
}
```

#### 2. Submit Score
```
POST /api/scores
```
Submit a new game score.

**Request Body:**
```json
{
  "playerName": "John",
  "score": 42
}
```

**Response:**
```json
{
  "id": 1,
  "playerName": "John",
  "score": 42,
  "timestamp": "2026-02-03T10:30:00.000Z"
}
```

#### 3. Get All Scores
```
GET /api/scores
```
Retrieve all submitted scores.

#### 4. Get Leaderboard
```
GET /api/leaderboard
```
Get top 10 high scores.

**Response:**
```json
[
  {
    "id": 5,
    "playerName": "Alice",
    "score": 156,
    "timestamp": "2026-02-03T11:20:00.000Z"
  },
  {
    "id": 3,
    "playerName": "Bob",
    "score": 142,
    "timestamp": "2026-02-03T10:45:00.000Z"
  }
]
```

#### 5. Get Player Scores
```
GET /api/player/:name
```
Get all scores for a specific player.

**Example:** `GET /api/player/John`

#### 6. Get Statistics
```
GET /api/stats
```
Get game statistics.

**Response:**
```json
{
  "totalGames": 150,
  "highScore": 156,
  "topPlayers": 10
}
```

## Running Locally

### Prerequisites
- Node.js 18 or higher
- npm or yarn
- PostgreSQL (optional - can use Render's database)

### Steps

1. Navigate to the API directory:
   ```bash
   cd api
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Set up environment variables (optional for local development):
   ```bash
   # Create a .env file
   DATABASE_URL=postgresql://localhost/flappybird
   PORT=8080
   ```

4. Run the application:
   ```bash
   npm start
   ```
   
   Or for development with auto-reload:
   ```bash
   npm run dev
   ```

5. The API will be available at `http://localhost:8080`

## Testing the API

### Using curl

Submit a score:
```bash
curl -X POST http://localhost:8080/api/scores \
  -H "Content-Type: application/json" \
  -d '{"playerName":"John","score":42}'
```

Get leaderboard:
```bash
curl http://localhost:8080/api/leaderboard
```

### Using a Browser
Simply open `http://localhost:8080/api/leaderboard` in your browser.

### Using Node.js fetch (for integration with your game)
```javascript
// Submit a score
const submitScore = async (playerName, score) => {
  const response = await fetch('http://localhost:8080/api/scores', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ playerName, score })
  });
  return response.json();
};

// Get leaderboard
const getLeaderboard = async () => {
  const response = await fetch('http://localhost:8080/api/leaderboard');
  return response.json();
};
```

## Database

- **Production:** Uses PostgreSQL (configured automatically on Render)
- The database table is created automatically on first run
- Table schema:
  ```sql
  CREATE TABLE scores (
    id SERIAL PRIMARY KEY,
    player_name VARCHAR(100) NOT NULL,
    score INTEGER NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  );
  ```

## Technologies Used

- Node.js 18+
- Express.js
- PostgreSQL (pg driver)
- CORS middleware

## Project Structure

```
api/
├── server.js          # Main application file
├── package.json       # Dependencies and scripts
├── .gitignore        # Git ignore rules
└── README.md         # This file
```
