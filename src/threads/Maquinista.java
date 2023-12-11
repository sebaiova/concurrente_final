package src.threads;
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
                    tren.conducirSiguienteEstacion();
                tren.conducirAlInicio();
            }

        } catch (InterruptedException | BrokenBarrierException e) {}
    }
}
