'use strict'

var net = require('net');

try {
    if ( process.argv.length < 4 ) {
        console.log("node " + process.argv[1] + " [destIP] [message]");
        process.exit(1);
    }
    var destIP = process.argv[2];
    var message = process.argv[3];
    var client = new net.Socket();

    client.connect(5566, destIP, function() {
        console.log('Connected');
        client.write(message);
    });

    client.on('data', function(data){
        console.log('Received data ' + data );

    });

    client.on('close', function() {
        console.log("socket close");
    });

} catch ( err ) {
    console.log('err ' + err );
}
