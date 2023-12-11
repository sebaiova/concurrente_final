package src.resources;
import java.util.Random;
import java.util.concurrent.Semaphore;

import src.Config;

public class PuestoAtencion {
    
    private static int NEXT_ID = 0;

    private final int CAPACIDAD;
    private final int id = NEXT_ID++;
    private final Semaphore semGuardia;
    private final Semaphore semPasajeros;
    private final Semaphore semCapacidad;
    private final Random random;
    private final Shop[] shops;

    public PuestoAtencion(Shop[] shops)
    {
        this.shops = shops;
        random = new Random();
        CAPACIDAD = Config.CAPACIDAD_PUESTO_ATENCION;
        semGuardia = new Semaphore(0, true);
        semPasajeros = new Semaphore(0, true);
        semCapacidad = new Semaphore(CAPACIDAD, true);
    }

    public void entrar() throws InterruptedException
    {
        semGuardia.release();
        semPasajeros.acquire();
    }

    public Indicacion salirConCheckIn()
    {
        semCapacidad.release();

        int puertoEmbarque = random.nextInt(21);
        int terminal = puertoEmbarque/7;
        int horaEmbarque = random.nextInt(24);

        return new Indicacion(null, puertoEmbarque, horaEmbarque, shops[terminal]);
    }

    public void llamarSiguiente() throws InterruptedException
    {
        semGuardia.acquire();
        semCapacidad.acquire();
        semPasajeros.release();
    }

    public int getID()
    {
        return id;
    }
}
