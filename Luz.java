import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Locale;

class Luz
{
    private static boolean status = false;
    private static boolean presenca = false;
    private static double horario = 12;
    
    public static void changeStatus(boolean newStatus){
        status = newStatus;
    }

    public static void changePresenca(boolean pres){
        presenca = pres;
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
                    case "PRES": {
                        changePresenca(Boolean.parseBoolean(value[1]));
                        System.out.println("Changed to : " + value[1]);
                        if(horario>18 && presenca){
                            changeStatus(true);
                            System.out.println("Light is ON!");
                        }else{
                            changeStatus(false);
                            System.out.println("Light is OFF!");
                        }
                        break;
                    }
                    case "TIM": {
                        changeHorario((new Double(value[1])));
                        System.out.println("Changed to : " + value[1]);
                        if(horario>18 && presenca){
                            changeStatus(true);
                            System.out.println("Light is ON!");
                        }else{
                            changeStatus(false);
                            System.out.println("Light is OFF!");
                        }
                        break;
                    }
                    default:
                        System.out.println("Light is : " + status);
                }
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }
}
