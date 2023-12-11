package src.resources;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

import src.Config;

public class Tren {
    
    private final int CAPACIDAD;
    
    private final CyclicBarrier barrier;
    private final Semaphore semEntrada;

    private final CountDownLatch[] latchEstacion;
    private final int CANTIDAD_ESTACIONES;
    private int estacionActual;

    public Tren()
    {
        CAPACIDAD = Config.CAPACIDAD_TREN;
        CANTIDAD_ESTACIONES = Config.TERMINALES;

        estacionActual = 0;
        latchEstacion = new CountDownLatch[Config.TERMINALES+1];

        semEntrada = new Semaphore(0, true);
        
        barrier = new CyclicBarrier(CAPACIDAD+1, ()->{ 
            for(int i=0; i<4; i++)
                latchEstacion[i] = new CountDownLatch(1);
            });   
    }

    public void subir(int pasajero, int estacionDestino) throws InterruptedException, BrokenBarrierException
    {
        semEntrada.acquire();
        barrier.await();
    }

    public void bajar(int pasajero, int estacionDestino) throws InterruptedException, BrokenBarrierException
    {
        latchEstacion[estacionDestino].await();
    }

    public int conducirEstacionSiguiente() throws InterruptedException, BrokenBarrierException
    {
        Thread.sleep(Config.DELAY_TREN);
        latchEstacion[estacionActual].countDown();
        estacionActual++;
        return estacionActual;
    }

    public int conducirEstacionPrevia() throws InterruptedException
    {
        Thread.sleep(Config.DELAY_TREN);
        estacionActual--;
        return estacionActual;
    }

    public boolean estaEnEstacionFinal()
    {
        return estacionActual==CANTIDAD_ESTACIONES;
    }

    public boolean estaEnEstacionInicial()
    {
        return estacionActual==0;
    }

    public void iniciarViaje() throws InterruptedException, BrokenBarrierException
    {
        semEntrada.release(CAPACIDAD);
        barrier.await();
    }
}
