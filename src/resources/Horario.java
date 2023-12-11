package src.resources;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import src.Config;

public class Horario 
{ 
    private Lock lock;
    private final Condition estaAbierto;
    private final Condition cambioHora;

    private int horaActual;
    private final int horaApertura;
    private final int horaCierre;

    public Horario()
    {
        horaApertura = Config.HORA_APERTURA;
        horaCierre = Config.HORA_CIERRE;
        horaActual = 0;
        
        lock = new ReentrantLock(true);
        estaAbierto = lock.newCondition();
        cambioHora = lock.newCondition();
    }

    public void esHorarioDeAtencion() throws InterruptedException
    {
        lock.lock();
        while(horaActual < horaApertura || horaActual >= horaCierre)
            estaAbierto.await();
        lock.unlock();
    }

    public void esperarHora(int hora) throws InterruptedException
    {
        lock.lock();
        while(hora != horaActual)
            cambioHora.await();
        lock.unlock();
    }

    public int aumentarHora()
    {
        lock.lock();
        horaActual = ++horaActual%24;

        if(horaActual >= horaApertura && horaActual < horaCierre)
            estaAbierto.signalAll(); 
        
        cambioHora.signalAll();

        lock.unlock();
        return horaActual;
    }

    // si faltan 4 horas o mas para el embarque
    public boolean tengoTiempo(int horaEmbarque)
    {
        int delta = ((horaActual+24) - (horaEmbarque+24));
        return delta < 0 || delta > 3; 
    }
}
