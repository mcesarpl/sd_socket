import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Locale;

class SensorHum
{
    private static double humidade = 14.5;
    
    public static void main(String arg[]){
        try{
            Socket socket = new Socket("127.0.0.1",3000);
            //System.out.println("Connected!");
            //ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            //ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
             BufferedReader inFromServer = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );
            DataOutputStream outToServer = new DataOutputStream(
                socket.getOutputStream()
            );
            while(true){
                outToServer.writeBytes(String.format(Locale.US, "HUM_%.2f\n", humidade));
                String value = inFromServer.readLine();
                System.out.println(value);
                Thread.sleep(700);
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }
}