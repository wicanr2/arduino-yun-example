import java.net._
import java.io._
import scala.io._
import java.util.Arrays;

object Client {
  def zero = 0x0.asInstanceOf[Byte]

  def main(args : Array[String]) : Unit = {
    println("Hello Scala")
    println("args length " + args.length )
    val ip = args(0)
    val message = args(1)
    println("ip " + ip + " message " + message )
    var isa = new InetSocketAddress( ip, 5566)
    var socket = new Socket()
    var r = 0
    var buf = new Array[Byte](128)
    println( "buf length " + buf.length )
    try {
        
        socket.connect( isa, 10000 );
        var out = new BufferedOutputStream(socket.getOutputStream());
        var in = new DataInputStream(socket.getInputStream());
        out.write(message.getBytes());
        out.flush();
        do {
            Arrays.fill(buf, zero);
            r = in.read(buf)
            val str = new String(buf)
            println( "length " + r + " recvieved data " + str)
        } while( r > 0 )

        socket.close();
    } catch {
        case e: Exception => e.printStackTrace()
    }
  }
}
