package src.threads;

import java.util.Random;

import src.Config;
import src.resources.Horario;
import src.resources.PuestoAtencion;
import src.resources.PuestoInformes;

public class Informante extends Thread {
    
    private final PuestoInformes puestoInformes;
    private final PuestoAtencion[] puestoAtencions;
    private final Horario horario;
    private final Random random;

    public Informante(Horario horario, PuestoInformes puestoInformes, PuestoAtencion[] puestoAtencions)
    {
        this.random = new Random();
        this.horario = horario;
        this.puestoInformes = puestoInformes;
        this.puestoAtencions = puestoAtencions;
    }

    @Override
    public void run()
    {
        try {
            while(true)
            {
                horario.esHorarioDeAtencion();
                puestoInformes.darInforme(puestoAtencions[random.nextInt(Config.PUESTOS_ATENCION)]);
                sleep(Config.DELAY_INFORMANTE);
            }
        } catch (InterruptedException e) {}
    }
}


