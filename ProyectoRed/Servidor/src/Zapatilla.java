import java.io.Serializable;

public class Zapatilla implements Serializable {
    String marca;

    public Zapatilla(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
