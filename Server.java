import java.awt.List;
import java.io.ObjectOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.ArrayList;

class Server
{
    private static double temperatura;
    private static boolean presenca;
    private static double umidade;
    private static double relogio;
    private static ArrayList<Socket> sockets = new ArrayList<Socket>();

    public static void changeTemp(double temp){
        temperatura = temp;
    }

    public static void changePresenca(boolean pres){
        presenca = pres;
    }

    public static void changeHumidade(double hum){
        umidade = hum;
    }

    public static void sendValues(String msg){
        for(Socket socket : sockets){
        	try{
        		DataOutputStream outToClient = new DataOutputStream(
                    socket.getOutputStream()
                );
                outToClient.writeBytes(msg+"\n");
        	} catch(Exception e){
        		System.out.println("Exception: " + e);
        	}
    	}
    }

    public static void addClient(Socket socket){
        sockets.add(socket);
    }
    
    
    public static void showClients(){
    	System.out.println("List :");
    	for(Socket socket : sockets) {
    		System.out.println(socket);
    	}
    }

    public static void main(String arg[])
    {
        try{
            ServerSocket server = new ServerSocket(3000);
            System.out.println("Server is Online on port : 3000 ");
            
            
            while(true){
                 Socket client = server.accept();
                 addClient(client);
                 System.out.println("New Clien : " + client);
                 (new MyClass(server, client)).start();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
