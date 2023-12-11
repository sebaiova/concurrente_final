package src.threads;

import src.resources.PuestoAtencion;

public class Guardia extends Thread {
    
    private final PuestoAtencion puestoAtencion;

    public Guardia(PuestoAtencion puestoAtencion)
    {
        this.puestoAtencion = puestoAtencion;
    }

    @Override
    public void run()
    {
        try {
            while(true)
            {
                sleep(200);
                puestoAtencion.llamarSiguiente();
            }  
        } catch (InterruptedException e) {}
    }
}
