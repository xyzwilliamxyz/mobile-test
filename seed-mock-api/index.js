const express = require('express');
const crypto = require('crypto');

const app = express();
const PORT = 3000;

app.get('/seed', (req, res) => {
  const seed = crypto.randomBytes(16).toString('hex');
  const expiresAt = new Date(Date.now() + 15 * 1000).toISOString(); // Expires in 15 seconds

  res.json({
    seed: seed,
    expires_at: expiresAt
  });
});

app.listen(PORT, () => {
  console.log(`Seed API running at http://localhost:${PORT}/seed`);
});