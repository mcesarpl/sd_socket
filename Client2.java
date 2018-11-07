import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Locale;

class Client2
{
    
    public static void main(String arg[]){
        try{
            Socket socket = new Socket("127.0.0.1",3000);
             BufferedReader inFromServer = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );
            while(true){
                String value = inFromServer.readLine();
                System.out.println(value);
            }

        } catch(Exception e){
             System.out.println(e);
        }
    }
}
