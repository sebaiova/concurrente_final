package src.threads;
import src.Output;
import src.events.EventTrenLlegaA;
import src.resources.Tren;
import java.util.concurrent.BrokenBarrierException;

public class Maquinista extends Thread {
    
    private final Tren tren;

    public Maquinista(Tren tren)
    {
        this.tren = tren;
    }

    @Override
    public void run()
    {
        try {
            while(true)
            {
                tren.iniciarViaje();
                while(!tren.estaEnEstacionFinal())
                {
                    int estacion = tren.conducirEstacionSiguiente();
                    Output.pushEvent(new EventTrenLlegaA(estacion));
                }

                while(!tren.estaEnEstacionInicial())
                {
                    int estacion = tren.conducirEstacionPrevia();
                    Output.pushEvent(new EventTrenLlegaA(estacion));
                }
            }

        } catch (InterruptedException | BrokenBarrierException e) {}
    }
}
