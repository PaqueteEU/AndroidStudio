package eu.paquete.notas;

public class Nota {

    protected int id;
    protected String title;
    protected int telefono;
    protected String lugar;
    protected String cliente;
    protected String url;

    public Nota(int id, String title, int telefono, String lugar, String cliente, String url){
        this.id=id;
        this.title=title;
        this.telefono=telefono;
        this.lugar=lugar;
        this.cliente=cliente;
        this.url=url;
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
