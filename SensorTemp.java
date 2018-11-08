import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Locale;

class SensorTemp
{
    private static double temp = 35.9;
    
    public static void changeTemp(double newTemp){
        temp = newTemp;
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
                outToServer.writeBytes(String.format(Locale.US, "TEMP_%.2f\n", temp));
                value = inFromServer.readLine().split("_");
                switch(value[0]){
                    case "CTEMP": {
                        changeTemp(new Double(value[1]));
                        System.out.println("Changed to : " + value[1]);
                        break;
                    }
                    default:
                        System.out.println("Temp : " + temp);
                }
                Thread.sleep(1000);
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }
}
