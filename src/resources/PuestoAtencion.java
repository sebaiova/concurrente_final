package src.resources;
import java.util.concurrent.Semaphore;

public class PuestoAtencion {
    
    private final int CAPACIDAD;

    private final Semaphore semGuardia;
    private final Semaphore semPasajeros;
    private final Semaphore semCapacidad;

    public PuestoAtencion()
    {
        CAPACIDAD = 5;
        semGuardia = new Semaphore(0, true);
        semPasajeros = new Semaphore(0, true);
        semCapacidad = new Semaphore(CAPACIDAD, true);
    }

    public void entrar() throws InterruptedException
    {
        semGuardia.release();
        semPasajeros.acquire();
    }

    public void salir()
    {
        semCapacidad.release();
        semGuardia.release();
    }

    public void llamarSiguiente() throws InterruptedException
    {
        semGuardia.acquire();
        semCapacidad.acquire();
        semPasajeros.release();
    }
}
