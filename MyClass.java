import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

public class MyClass extends Thread {

    private ServerSocket server;
    private Socket socket;
    
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
            outToClient.writeBytes("Connected!\n");
            while(true){
                value = inFromClient.readLine().split("_");
                switch(value[0]){
                    case "TEMP": {
                        Server.sendValues(value[0] +"_"+ value[1]);
                        break;
                    }
                    case "HUM": {
                        Server.sendValues(value[0] +"_"+ value[1]);
                        break;
                    }
                    case "PRES": {
                        Server.sendValues(value[0] +"_"+ value[1]);
                        break;
                    }
                    default:
                        Server.sendValues("Invalid Request!");
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }

    } 
}
