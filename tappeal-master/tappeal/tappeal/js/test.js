//サーバー側の設定
  const http = require("http");
  const port = 3000;
  //本文
  const server = http.createServer(function(request,response) {
    response.writeHead(200, { "Content-Type": "application/json" });

    response.write('20');
    response.end();
  });

  server.listen(port, '172.25.15.100');