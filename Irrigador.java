import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Locale;

public class Irrigador
{
    private static boolean status = false;
    private static double umidade = 30;
    
    public static void changeStatus(boolean newStatus){
        status = newStatus;
    }

    public static void changeHumidade(double hum){
        umidade = hum;
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
                    case "HUM": {
                        changeHumidade(new Double(value[1]));
                        System.out.println("Changed to : " + value[1]);
                        if(umidade<30){
                            changeStatus(true);
                            System.out.println("Irrigator is ON!");
                        }else{
                            changeStatus(false);
                            System.out.println("Irrigator is OFF!");
                        }
                        break;
                    }
                    default:
                    {
                        if(status){
                            System.out.println("Irrigator is ON!");
                        }else{
                            System.out.println("Irrigator is OFF!");
                        }
                    }
                }
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }
}
