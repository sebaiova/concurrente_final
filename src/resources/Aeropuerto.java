package src.resources;

import src.threads.Guardia;

public class Aeropuerto {
    
    private final Horario horario;

    private final PuestoInformes puestoInformes;

    private final PuestoAtencion puestoAtencion;
    private final Guardia guardia;

    public Aeropuerto()
    {
        horario = new Horario();
    
        puestoAtencion = new PuestoAtencion();
        guardia = new Guardia(puestoAtencion);
        guardia.start();    

        puestoInformes = new PuestoInformes();
    }

    public void entrar() throws InterruptedException
    {
        System.out.println("Entro un pasajero al aeropuerto.");

        puestoAtencion.entrar();
        System.out.println("Entro un pasajero al puesto de Atencion.");
        puestoAtencion.salir();

        System.out.println("Salio un pasajero al puesto de Atencion.");
    }

    public Indicacion obtenerIndicacion() throws InterruptedException
    {
        return puestoInformes.recibirInforme();
    }

}
