package src.threads;

import src.resources.Horario;
import src.resources.PuestoInformes;

public class Informante extends Thread {
    
    private final PuestoInformes puestoInformes;
    private final Horario horario;

    public Informante(Horario horario, PuestoInformes puestoInformes)
    {
        this.horario = horario;
        this.puestoInformes = puestoInformes;
    }

    @Override
    public void run()
    {
        try {
            while(true)
            {
                horario.esperarHorario();
                puestoInformes.darInforme();
                sleep(200);
            }  
        } catch (InterruptedException e) {}
    }
}


