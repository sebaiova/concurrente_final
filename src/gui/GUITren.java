package src.gui;


public class GUITren extends GUIQueue {
    
    private int terminal;

    public GUITren(String caption) 
    {
        super(caption);
        terminal = 0;
    }

    @Override
    public int getX()
    {
        return super.getX()+(getWidth()*terminal);
    }

    public void setTerminal(int terminal)
    {
        this.terminal = terminal;
    }
}
