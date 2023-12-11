package src.threads;

import src.Config;
import src.resources.Horario;
import src.resources.PuestoAtencion;

public class Guardia extends Thread {
    
    private final PuestoAtencion puestoAtencion;
    private final Horario horario;

    public Guardia(PuestoAtencion puestoAtencion, Horario horario)
    {
        this.puestoAtencion = puestoAtencion;
        this.horario = horario;
    }

    @Override
    public void run()
    {
        try {
            while(true)
            {
                sleep(Config.DELAY_GUARDIA);
                horario.esHorarioDeAtencion();
                puestoAtencion.llamarSiguiente();
            }  
        } catch (InterruptedException e) {}
    }
}
