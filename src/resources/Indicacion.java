package src.resources;
public class Indicacion {
    
    private final PuestoAtencion puestoAtencion;
    private final Shop shop;
    private final int puestoEmbarque;
    private final int horaEmbarque;

    public Indicacion(PuestoAtencion puestoAtencion, int puestoEmbarque, int horaEmbarque, Shop shop)
    {
        this.puestoAtencion = puestoAtencion;
        this.puestoEmbarque = puestoEmbarque;
        this.horaEmbarque = horaEmbarque;
        this.shop = shop;
    }

    public PuestoAtencion getPuestoAtencion()   { return puestoAtencion;    }
    public Shop getShop()                       { return shop;              }
    public int getEstacion()                    { return puestoEmbarque/7;  }
    public int getPuestoEmbarque()              { return puestoEmbarque;    }
    public int getHoraDeEmbarque()              { return horaEmbarque;      }
}