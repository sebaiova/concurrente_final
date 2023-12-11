package src.threads;
import java.util.concurrent.BrokenBarrierException;

import src.Output;
import src.events.EventPasajeroEntraAeropuerto;
import src.events.EventPasajeroLlegaAeropuerto;
import src.resources.Indicacion;
import src.resources.PuestoInformes;
import src.resources.Tren;

public class Pasajero extends Thread {
    
    private static int NEXT_ID = 0;
    private final int id; 
    private final Tren tren;

    private Indicacion indicacion;

    private final PuestoInformes puestoInformes;

    public Pasajero(PuestoInformes puestoInformes, Tren tren)
    {
        id = NEXT_ID++;
        this.tren = tren;
        this.puestoInformes = puestoInformes;
    }

    @Override
    public void run()
    {
        
        Output.pushEvent(new EventPasajeroLlegaAeropuerto(id));
        
        try { 
            puestoInformes.recibirInforme();
        } 
        catch (InterruptedException e) {}
        
        Output.pushEvent(new EventPasajeroEntraAeropuerto(id));

        //    aeropuerto.entrar();
          //  indicacion = aeropuerto.obtenerIndicacion();

            //tren.subir(id, indicacion.getEstacion());
            //tren.bajar(id, indicacion.getEstacion());

   
       // try {aeropuerto.entrar(); } catch (InterruptedException e) {}
    }
}
