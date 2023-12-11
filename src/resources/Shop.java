package src.resources;

//Mon
public class Shop {
    
    private final int CAPACIDAD;
    private int personas;

    private Caja caja_1;
    private Caja caja_2;

    public Shop()
    {
        CAPACIDAD = 4;
        personas = 0;

        caja_1 = new Caja();
        caja_2 = new Caja();
    }

    synchronized public void entrar() throws InterruptedException
    {
        while(!puedoEntrar())
            wait();
        personas++;
    }

    synchronized public void salir()
    {
        personas--;
        notify();
    }

    // retorna la caja con menos gente
    public Caja elegirCaja()
    {
        return caja_1.size() < caja_2.size() ? caja_1 : caja_2;
    }

    private boolean puedoEntrar()
    {
        return CAPACIDAD > personas;
    }
}