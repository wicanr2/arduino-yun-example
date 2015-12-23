import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.util.Arrays;
 
public class SocketClient {
    private String address = "";
    private String message = "";
    private int port = 5566;
 
    public SocketClient(String dest, String _message) {
        message = _message;
        startSocket(dest, 5566);
    }
    
    private void startSocket(String dest, int _port) {
 
        address = dest ;
        port = _port;
        Socket client = new Socket();
        InetSocketAddress isa = new InetSocketAddress(this.address, this.port);
        byte[] buf = new byte[128];
        int r = 0;
        try {

            client.connect(isa, 10000);
            BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());

            out.write(message.getBytes());
            out.flush();

            do { 
                Arrays.fill(buf, (byte)0);
                r = in.read(buf);
                String recv_message = new String(buf);
                System.out.println(" recv string : " + recv_message );
            } while( r>0 );

            out.close();
            out = null;
            client.close();
            client = null;
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String args[]) {
        if ( args.length < 2 ) {
            System.out.println("java SocketClient [destIP] [message]");
            System.exit(1);
        }
        System.out.println("args[0] = " + args[0] );
        System.out.println("args[1] = " + args[1] );
        new SocketClient(args[0], args[1]);
    }
}
