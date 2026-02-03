# Flappy Bird API

A REST API for tracking scores and leaderboard for the Flappy Bird game.

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
  "endpoints": "/api/scores, /api/leaderboard, /api/player/{name}"
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
  "timestamp": "2026-02-03T10:30:00"
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
    "timestamp": "2026-02-03T11:20:00"
  },
  {
    "id": 3,
    "playerName": "Bob",
    "score": 142,
    "timestamp": "2026-02-03T10:45:00"
  }
]
```

#### 5. Get Player Scores
```
GET /api/player/{name}
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
- Java 17 or higher
- Maven 3.6+

### Steps

1. Navigate to the API directory:
   ```bash
   cd api
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. The API will be available at `http://localhost:8080`

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

## Database

- **Development:** Uses H2 in-memory database
- **Production:** Uses PostgreSQL (configured for Render)

## Technologies Used

- Spring Boot 3.2.2
- Spring Data JPA
- H2 Database (dev)
- PostgreSQL (prod)
- Maven
