package src.threads;
import src.Output;
import src.events.EventSetHour;
import src.resources.Horario;

public class Reloj extends Thread {
    
    private final Horario horario;

    public Reloj(Horario horario)
    {
        this.horario = horario; 
    }

    @Override
    public void run()
    {
        while(true)
        {
            try { sleep(500); } catch (InterruptedException e) {}
            int hora = horario.aumentarHora();
            Output.pushEvent(new EventSetHour(hora));
        }
    }
}
