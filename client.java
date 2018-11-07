import java.net.*;

class client
{
    public static void main(String arg[]){
        try{
            Socket socket = new Socket("127.0.0.1",3000);
            System.out.println("Connected!");
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            while(true){
                String msg = input.readUTF(); 
                System.out.println("Recebido : " + msg);
            }

        } catch(Exception e){}
    }
}