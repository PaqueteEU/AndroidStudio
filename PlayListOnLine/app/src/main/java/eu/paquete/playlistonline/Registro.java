package eu.paquete.playlistonline;

/**
 * @author Marco Rodriguez
 */
public class Registro {

    protected int id;
    protected String titulo;
    protected String url;


    public Registro(int id,String titulo, String url){
        this.id=id;
        this.titulo=titulo;
        this.url=url;

    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUrl() {
        return url;
    }


}
