var express = require('express');

var app = express();

app.use(express.static('../webapp'));

app.get('/', (req, res) => {
  res.send(`
    <!doctype html>
    <html>
      <head>
        <meta charset="utf-8" />
        <title>java web integration demo1</title>
      </head>
      <body>
        <div id="content"></div>
        <script src="/bundle.js"></script>
      </body>
    </html>
  `)
});

var PORT = 3000;

app.listen(PORT, () => {
  console.log(`App now serving on http://localhost:${PORT}`); 
});
