package src.resources;
public class Indicacion {
    
    private final int puestoAtencion;
    private final int estacion;
    private final int puestoEmbarque;

    public Indicacion(int puestoAtencion, int estacion, int puestoEmbarque)
    {
        this.puestoAtencion = puestoAtencion;
        this.estacion = estacion;
        this.puestoEmbarque = puestoEmbarque;
    }

    public int getPuestoAtencion()  { return puestoAtencion;    }
    public int getEstacion()        { return estacion;          }
    public int getPuestoEmbarque()  { return puestoEmbarque;    }
}