const express = require('express');
const cors = require('cors');
const { Pool } = require('pg');

const app = express();
const port = process.env.PORT || 8080;

// Middleware
app.use(cors());
app.use(express.json());

// Database connection
const pool = new Pool({
  connectionString: process.env.DATABASE_URL || 'postgresql://localhost/flappybird',
  ssl: process.env.DATABASE_URL ? { rejectUnauthorized: false } : false
});

// Initialize database table
const initDB = async () => {
  try {
    await pool.query(`
      CREATE TABLE IF NOT EXISTS scores (
        id SERIAL PRIMARY KEY,
        player_name VARCHAR(100) NOT NULL,
        score INTEGER NOT NULL,
        timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
      )
    `);
    console.log('Database initialized successfully');
  } catch (error) {
    console.error('Database initialization error:', error);
  }
};

initDB();

// Routes

// GET / - API Info
app.get('/api/', (req, res) => {
  res.json({
    message: 'Flappy Bird API',
    version: '1.0',
    endpoints: '/api/scores, /api/leaderboard, /api/player/:name, /api/stats'
  });
});

// POST /api/scores - Submit a new score
app.post('/api/scores', async (req, res) => {
  try {
    const { playerName, score } = req.body;
    
    if (!playerName || score === undefined) {
      return res.status(400).json({ error: 'playerName and score are required' });
    }
    
    const result = await pool.query(
      'INSERT INTO scores (player_name, score) VALUES ($1, $2) RETURNING *',
      [playerName, score]
    );
    
    res.status(201).json({
      id: result.rows[0].id,
      playerName: result.rows[0].player_name,
      score: result.rows[0].score,
      timestamp: result.rows[0].timestamp
    });
  } catch (error) {
    console.error('Error submitting score:', error);
    res.status(500).json({ error: 'Failed to submit score' });
  }
});

// GET /api/scores - Get all scores
app.get('/api/scores', async (req, res) => {
  try {
    const result = await pool.query(
      'SELECT id, player_name as "playerName", score, timestamp FROM scores ORDER BY timestamp DESC'
    );
    res.json(result.rows);
  } catch (error) {
    console.error('Error fetching scores:', error);
    res.status(500).json({ error: 'Failed to fetch scores' });
  }
});

// GET /api/leaderboard - Get top 10 scores
app.get('/api/leaderboard', async (req, res) => {
  try {
    const result = await pool.query(
      'SELECT id, player_name as "playerName", score, timestamp FROM scores ORDER BY score DESC LIMIT 10'
    );
    res.json(result.rows);
  } catch (error) {
    console.error('Error fetching leaderboard:', error);
    res.status(500).json({ error: 'Failed to fetch leaderboard' });
  }
});

// GET /api/player/:name - Get scores for a specific player
app.get('/api/player/:name', async (req, res) => {
  try {
    const { name } = req.params;
    const result = await pool.query(
      'SELECT id, player_name as "playerName", score, timestamp FROM scores WHERE player_name = $1 ORDER BY score DESC',
      [name]
    );
    res.json(result.rows);
  } catch (error) {
    console.error('Error fetching player scores:', error);
    res.status(500).json({ error: 'Failed to fetch player scores' });
  }
});

// GET /api/stats - Get game statistics
app.get('/api/stats', async (req, res) => {
  try {
    const totalGamesResult = await pool.query('SELECT COUNT(*) as count FROM scores');
    const highScoreResult = await pool.query('SELECT MAX(score) as high_score FROM scores');
    const topPlayersResult = await pool.query(
      'SELECT COUNT(DISTINCT player_name) as count FROM (SELECT player_name FROM scores ORDER BY score DESC LIMIT 10) as top'
    );
    
    res.json({
      totalGames: parseInt(totalGamesResult.rows[0].count),
      highScore: highScoreResult.rows[0].high_score || 0,
      topPlayers: parseInt(topPlayersResult.rows[0].count)
    });
  } catch (error) {
    console.error('Error fetching stats:', error);
    res.status(500).json({ error: 'Failed to fetch stats' });
  }
});

// Health check endpoint
app.get('/health', (req, res) => {
  res.json({ status: 'ok' });
});

// Start server
app.listen(port, () => {
  console.log(`Flappy Bird API running on port ${port}`);
  console.log(`Access the API at http://localhost:${port}/api/`);
});
