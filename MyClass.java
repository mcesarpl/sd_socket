import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

public class MyClass extends Thread {

    private ServerSocket server;
    private Socket socket;
    //private static ArrayList<Socket> sockets = new ArrayList<Socket>();
    
    public MyClass(ServerSocket server, Socket socket){
        this.server = server;
        this.socket = socket;
    }

    public void run(){
    
        try{
            BufferedReader inFromClient = new BufferedReader(
                new InputStreamReader(this.socket.getInputStream())
            );
            DataOutputStream outToClient = new DataOutputStream(
                this.socket.getOutputStream()
            );
        
            String[] value;
            outToClient.writeBytes("Connected!");
            while(true){
                value = inFromClient.readLine().split("_");
                outToClient.writeBytes("Mensagem recebida!"+"\n");
                switch(value[0]){
                    case "TEMP": {
                        Server.changeTemp(new Double(value[1]));
                        Server.sendValues(value[0] +"_"+ value[1]);
                        break;
                    }
                    case "HUM": {
                        Server.changeTemp(new Double(value[1]));
                        Server.sendValues(value[0] +"_"+ value[1]);
                        break;
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }

    } 
}
