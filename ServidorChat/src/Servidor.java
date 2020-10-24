
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dario
 */
public class Servidor extends Observable implements Runnable {
    private int puerto;
    
    public Servidor(int puerto){
        this.puerto = puerto;
    }
    
    @Override
    public void run(){
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;
        
        try{
            
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor Iniciado");
            
            while(true){
                //esperamos a que los clientes
                sc = servidor.accept();
                
                System.out.println("Cliente Conectado");
                
                in = new DataInputStream(sc.getInputStream());
                
                //leo el mensaje
                String mensaje = in.readUTF();
                
                System.out.println("Mensaje: " + mensaje);
                
                this.setChanged();
                this.notifyObservers(mensaje);
                
                this.clearChanged();
                
                sc.close();
                System.out.println("Cliente Desconectado");
                
                
            }
        
        }catch(Exception ex){
             System.out.println(ex.getMessage()); 
        }
        
    }
    
    
}
