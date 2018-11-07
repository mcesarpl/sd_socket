import java.awt.List;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;

class server
{
    public float temperatura;
    public boolean presenca;
    public int umidade;
    public double relogio;

    public server(){
        this.temperatura = 25;
        this.presenca = false;
        this.umidade = 20;
        this.relogio = 12.0;
    }

    private static ArrayList<Socket> sockets = new ArrayList<Socket>();

    public void changeTemperatua(Float temperatura){
        this.temperatura = temperatura;
    }

    public void changePresenca(Boolean presenca){
        this.presenca = presenca;
    }

    public void sendTemperatura(String msg){
        for(Socket socket : sockets){
        	try{
        		ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                output.writeUTF(msg);
        	} catch(Exception e){
        		System.out.println("Exception: " + e);
        	}
    	}
    }

    public static void addClient(Socket socket){
        sockets.add(socket);
    }

    public static void main(String arg[])
    {
        try{
            ServerSocket server = new ServerSocket(3000);
            System.out.println("Server is Online on port : 3000 ");
            while(true){
                Socket socket = server.accept();
                addClient(socket);
                System.out.println("Connected : "+ socket);
            }
        }catch(Exception e){}
    }
}
