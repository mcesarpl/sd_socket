import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Locale;

class SensorPresence
{
    private static boolean presence = false;
    
    public static void changePresence(boolean newBool){
        presence = newBool;
    }
    public static void main(String arg[]){
        try{
            Socket socket = new Socket("127.0.0.1",3000);
            BufferedReader inFromServer = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );
            DataOutputStream outToServer = new DataOutputStream(
                socket.getOutputStream()
            );

            String value = "";
            while(true){
                outToServer.writeBytes("PRES_" + String.valueOf(presence) + "\n");
                value = inFromServer.readLine();
                System.out.println(value);
                Thread.sleep(1500);
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }
}
