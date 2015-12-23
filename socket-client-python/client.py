#!/usr/bin/python

import socket
import sys

print '\nPython Socket Client'

if len(sys.argv)< 3:
    print 'insufficient parameters'
    print sys.argv[0] + " [destIP] [message] "
    print ' '
    sys.exit(1);


sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

server = (sys.argv[1],5566);
print >>sys.stderr, 'connecting to %s port %s' % server
sock.connect(server)
try:
    message = sys.argv[2]
    print >>sys.stderr, 'sending "%s"' % message
    sock.sendall(message);
    
    data = sock.recv(128);
    while len(data) > 0:
        print 'Received data ', data
        data = sock.recv(128);

        

finally:
    sock.close();

