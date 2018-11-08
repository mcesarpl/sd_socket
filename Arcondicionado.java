import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Locale;

public class Arcondicionado
{
    private static boolean status = false;
    private static double temperatura;
    private static boolean presenca = true;
    
    public static void changeStatus(boolean newStatus){
        status = newStatus;
    }

    public static void changeTemp(double newTemperatura){
        temperatura = newTemperatura;
    }

    public static void changePresenca(boolean pres){
        presenca = pres;
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
                value = inFromServer.readLine().split("_");
                switch(value[0]){
                    case "PRES": {
                        changePresenca(Boolean.parseBoolean(value[1]));
                        System.out.println("Changed to : " + value[1]);
                        if(presenca && temperatura>25f){
                            changeStatus(true);
                            System.out.println("Arcond is on!");
                        }else{
                            changeStatus(false);
                            System.out.println("Arcond is off!");
                        }
                        break;
                    }
                    case "TEMP": {
                        changeTemp(new Double(value[1]));
                        System.out.println("Changed to : " + value[1]);
                        if(presenca && temperatura>25f){
                            changeStatus(true);
                            System.out.println("Arcond is on!");
                        }else{
                            changeStatus(false);
                            System.out.println("Arcond is off!");
                        }
                        break;
                    }
                    default:
                        System.out.println("Arcond is : " + status);
                }
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }
}
