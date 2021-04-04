package eu.paquete.notas;

public class Nota {

    protected int id;
    protected String title;
    protected int priority;

    public Nota(int id, String title, int priority){
        this.id=id;
        this.title=title;
        this.priority=priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
