package src.resources;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

public class PuestoInformes 
{
    private final Exchanger<Indicacion> exchanger;
    private final Semaphore semEntrada;

    public PuestoInformes()
    {
        semEntrada = new Semaphore(0, true);
        exchanger = new Exchanger<Indicacion>();
    }

    public void darInforme(PuestoAtencion puestoAtencion) throws InterruptedException
    {
        semEntrada.release();
        exchanger.exchange(new Indicacion(puestoAtencion, 0, 0, null));
    }

    public Indicacion recibirInforme() throws InterruptedException
    {
        semEntrada.acquire();
        return exchanger.exchange(null);
    }
}