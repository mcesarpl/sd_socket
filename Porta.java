import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Locale;

class Porta
{
    private static boolean status = false;
    private static double horario = 12;
    
    public static void changeStatus(boolean newStatus){
        status = newStatus;
    }

    public static void changeHorario(double newTime){
        horario = newTime;
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
                    case "TIM": {
                        changeHorario((new Double(value[1])));
                        System.out.println("Changed to : " + value[1]);
                        if(horario>24 || horario<5){
                            changeStatus(true);
                            System.out.println("Door Locked!");
                        }else{
                            changeStatus(false);
                            System.out.println("Door Opened!");
                        }
                        break;
                    }
                    default:{
                        if(status){
                            System.out.println("Door is LOCKED!");
                        }else{
                            System.out.println("Door is OPENED!");
                        }
                    }
                        
                }
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }
}
