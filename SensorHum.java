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
    
    public static void changeHumidade(double newHum){
        humidade = newHum;
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
            String[] value;
            while(true){
                outToServer.writeBytes(String.format(Locale.US, "HUM_%.2f\n", humidade));
                value = inFromServer.readLine().split("_");
                switch(value[0]){
                    case "HUM": {
                        changeHumidade(new Double(value[1]));
                        System.out.println("Changed to : " + value[1]);
                        break;
                    }
                    default:
                        System.out.println("No change value.");
                }
                Thread.sleep(1200);
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }
}
