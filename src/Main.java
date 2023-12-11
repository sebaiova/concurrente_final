package src;

import src.gui.GUI;
import src.resources.Horario;
import src.resources.PuestoAtencion;
import src.resources.PuestoInformes;
import src.resources.Shop;
import src.resources.Tren;
import src.threads.Guardia;
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
        
        /* Recursos */
        
        Horario horario = new Horario();
        PuestoInformes puestoInformes = new PuestoInformes();

        Shop[] shops = new Shop[Config.TERMINALES];
        for(int i=0; i<Config.TERMINALES; i++)
            shops[i] = new Shop();

        PuestoAtencion[] puestoAtencions = new PuestoAtencion[Config.PUESTOS_ATENCION];
        for(int i=0; i<Config.PUESTOS_ATENCION; i++)
            puestoAtencions[i] = new PuestoAtencion(shops);

        Tren tren = new Tren();

        /* Hilos */

        Informante informante = new Informante(horario, puestoInformes, puestoAtencions);
        informante.start();

        Maquinista maquinista = new Maquinista(tren);
        maquinista.start();

        Reloj reloj = new Reloj(horario);
        reloj.start();

        Guardia[] guardias= new Guardia[Config.PUESTOS_ATENCION];
        for(int i=0; i<Config.PUESTOS_ATENCION; i++)
        {
            guardias[i] = new Guardia(puestoAtencions[i], horario);
            guardias[i].start();
        }

        /*Los pasajeros llegan infinitamente */
        while(true)
        {
            Thread.sleep(Config.DELAY_PASAJERO_SPAWN);
            new Pasajero(puestoInformes, tren, horario).start();
        }
    }
}