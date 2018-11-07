import java.net.*;

class server
{
    public Float temperatura;
    public Boolean presenca;
    public Int umidade;
    public Double relogio;

    public server(){
        this.temperatura = 25.0;
        this.presenca = false;
        this.umidade = 20;
        this.relogio = 12.0;
    }

    private List<Socket> sockets = new ArrayList<Socket>();

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

    public void addClient(Socket socket){
        this.sockets.add(socket);
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