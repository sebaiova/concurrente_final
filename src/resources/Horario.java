package src.resources;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Horario 
{ 
    private Lock lock;
    private Condition estaAbierto;
    private int horaActual;
    private final int horaApertura;
    private final int horaCierre;

    public Horario()
    {
        horaApertura = 6;
        horaCierre = 22;
        horaActual = 0;
        lock = new ReentrantLock(true);
        estaAbierto = lock.newCondition();
    }

    public void esperarHorario()
    {
        lock.lock();
        while(horaActual < horaApertura || horaActual >= horaCierre)
            try { estaAbierto.await(); } catch (InterruptedException e) {}
        lock.unlock();
    }

    public int aumentarHora()
    {
        lock.lock();
        horaActual = ++horaActual%24;
        estaAbierto.signalAll(); 
        lock.unlock();
        return horaActual;
    }
}
