package src.threads;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

import src.Config;
import src.Output;
import src.events.EventEntrarShop;
import src.events.EventPasajeroEmbarca;
import src.events.EventPasajeroEntraAnden;
import src.events.EventPasajeroEntraHall;
import src.events.EventPasajeroEntraPuestoAtencion;
import src.events.EventPasajeroEntraPuestoInforme;
import src.events.EventPasajeroEntraTerminal;
import src.events.EventPasajeroEntraTren;
import src.events.EventSalirShop;
import src.resources.Caja;
import src.resources.Horario;
import src.resources.Indicacion;
import src.resources.PuestoAtencion;
import src.resources.PuestoInformes;
import src.resources.Shop;
import src.resources.Tren;

public class Pasajero extends Thread {
    
    private static int NEXT_ID = 0;
    private final int id; 
    private final Random random = new Random();

    private final Tren tren;
    private final PuestoInformes puestoInformes;
    private final Horario horario;
    private Indicacion indicacion;

    public Pasajero(PuestoInformes puestoInformes, Tren tren, Horario horario)
    {
        id = NEXT_ID++;
        this.tren = tren;
        this.puestoInformes = puestoInformes;
        this.horario = horario;
    }

    @Override
    public void run()
    {
        
        try { 
            /* Llega al aeropuerto */
            Output.pushEvent(new EventPasajeroEntraPuestoInforme(id));   

            /* Recibe informe en la sala de informes */
            indicacion = puestoInformes.recibirInforme();
            Output.pushEvent(new EventPasajeroEntraHall(id));

            /* Entra al puesto de atencion */
            PuestoAtencion puestoAtencion = indicacion.getPuestoAtencion();
            int puesto = puestoAtencion.getID();
            puestoAtencion.entrar();
            Output.pushEvent(new EventPasajeroEntraPuestoAtencion(id, puesto));

            /* Realiza checkin y sale del puesto de atención */
            sleep(Config.DELAY_CHECKIN);
            indicacion = puestoAtencion.salirConCheckIn();
            Output.pushEvent(new EventPasajeroEntraAnden(id, puesto));
        
            /* Se sube al tren para viajar a su estación */
            int destino = indicacion.getEstacion();
            tren.subir(id, destino);
            Output.pushEvent(new EventPasajeroEntraTren(id));

            /* Se baja del tren en su estación */
            tren.bajar(id, destino);
            Output.pushEvent(new EventPasajeroEntraTerminal(id, destino));

            int horaDeEmbarque = indicacion.getHoraDeEmbarque();

            //Si quiere ir al shop y tiene tiempo
            if(random.nextBoolean() && horario.tengoTiempo(horaDeEmbarque))
            {
                /* Entra al shop */
                Shop shop = indicacion.getShop();
                shop.entrar();
                Output.pushEvent(new EventEntrarShop(id, destino));

                //Si quiere comprar
                if(random.nextBoolean())
                {
                    Caja caja = shop.elegirCaja();
                    caja.hacerFila(id);
                    caja.comprar(id);
                    sleep(1000);
                    caja.pagar();
                }
                else
                {
                    sleep(500);
                }    
                /* Sale del shop */
                shop.salir();
                Output.pushEvent(new EventSalirShop(id, destino));
            }

            /* Espera la hora de embarque */
            horario.esperarHora(horaDeEmbarque);
            Output.pushEvent(new EventPasajeroEmbarca(id, destino));
        } 

        catch (InterruptedException | BrokenBarrierException e) {}
    }
}
