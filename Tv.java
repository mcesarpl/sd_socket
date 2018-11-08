import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Locale;

class Tv
{
    private static boolean status = false;
    private static boolean presenca = false;
    
    public static void changeStatus(boolean newStatus){
        status = newStatus;
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
                        changePresenca(Boolean.getBoolean(value[1]));
                        System.out.println("Changed to : " + value[1]);
                        if(presenca){
                            changeStatus(true);
                            System.out.println("Tv is on!");
                        }else{
                            changeStatus(false);
                            System.out.println("Tv is off!");
                        }
                        break;
                    }
                    default:
                        System.out.println("No change value.");
                }
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }
}
