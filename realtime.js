const fs = require('fs');
const net = require('net');

const handler = (req, res) => {
    fs.readFile(__dirname + '/public/realtime.html', (err, data) => {
        if (err) {
            res.writeHead(500).end('Error');
        }
        res.writeHead(200);
        res.end(data);
    });
};

const app = require('http').createServer(handler);
const io = require('socket.io')(app); //create socket connection to front end

app.listen(8080);


//create socket server and listen port 9999 to receive data
const server = net.createServer((c) => {
    console.log('client connected');
    c.setEncoding('utf-8');
    c.on('end', () => {
    console.log('client disconnected');
    });
    c.on('data', (chunk) => {
        io.emit('newData', chunk); //emit data to front end, data name is newData
    });
});

server.on('error', (err) => {
    throw err;
});

server.listen(9999, () => {
    console.log('server bound');
});
