package src.resources;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Tren {
    
    private final int CAPACIDAD;
    
    private final CyclicBarrier barrier;
    private final Semaphore semEntrada;

    private final CountDownLatch[] latchEstacion;
    private final int CANTIDAD_ESTACIONES;
    private int estacionActual;

    public Tren()
    {
        CAPACIDAD = 10;
        CANTIDAD_ESTACIONES = 4;

        estacionActual = 0;
        latchEstacion = new CountDownLatch[4];

        semEntrada = new Semaphore(0, true);
        
        barrier = new CyclicBarrier(CAPACIDAD+1, ()->{ 
            for(int i=0; i<4; i++)
                latchEstacion[i] = new CountDownLatch(1);
            });   
    }

    public void subir(int pasajero, int estacionDestino) throws InterruptedException, BrokenBarrierException
    {
        semEntrada.acquire();
        System.out.printf("Pasajero %d se sube al tren. Destino: %d.\n", pasajero, estacionDestino);
        barrier.await();
    }

    public void bajar(int pasajero, int estacionDestino) throws InterruptedException, BrokenBarrierException
    {
        latchEstacion[estacionDestino].await();
        System.out.printf("Pasajero %d se baja en destino %d.\n", pasajero, estacionDestino);
    }

    public void conducirSiguienteEstacion() throws InterruptedException, BrokenBarrierException
    {
        Thread.sleep(500);
        latchEstacion[estacionActual].countDown();
        estacionActual++;
    }

    public void conducirAlInicio() throws InterruptedException
    {
        Thread.sleep(500*CANTIDAD_ESTACIONES);
        System.out.println("El tren vuelve al inicio.");
        estacionActual=0;
    }

    public boolean estaEnEstacionFinal()
    {
        return estacionActual==CANTIDAD_ESTACIONES;
    }

    public void iniciarViaje() throws InterruptedException, BrokenBarrierException
    {
        semEntrada.release(CAPACIDAD);
        barrier.await();
    }
}
