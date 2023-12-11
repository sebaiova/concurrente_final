package src;

import src.gui.GUI;
import src.resources.Aeropuerto;
import src.resources.Horario;
import src.resources.PuestoInformes;
import src.resources.Tren;
import src.threads.Informante;
import src.threads.Maquinista;
import src.threads.Pasajero;
import src.threads.Reloj;

public class Main
{
    static public void main(String args[]) throws InterruptedException
    {
        GUI gui = new GUI("Concurrente TP Final");
        Output.start(gui);
        
        Aeropuerto aeropuerto = new Aeropuerto();
        Horario horario = new Horario();
        PuestoInformes puestoInformes = new PuestoInformes();
        Tren tren = new Tren();

        
        Informante informante = new Informante(horario, puestoInformes);
        informante.start();

        Maquinista maquinista = new Maquinista(tren);
        maquinista.start();

        Reloj reloj = new Reloj(horario);
        reloj.start();


        Pasajero[] pasajero = new Pasajero[33];
        for(int i=0; i<33; i++)
        {
            pasajero[i] = new Pasajero(puestoInformes, tren);
            pasajero[i].start();
        }


    //  for(int i=0; i<100; i++)
      //  {
      //      Pasajero pasajero = new Pasajero(aeropuerto);
      //      pasajero.start();
      //  }   
    }
}