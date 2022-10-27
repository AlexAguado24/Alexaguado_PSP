public class Raton {

    private String nombre;
    private int tiempoAlimentacion;

    public Raton(String nombre, int tiempoAlimentacion) {
        this.nombre = nombre;
        this.tiempoAlimentacion = tiempoAlimentacion;
    }

    public void comer(){
        try {
            System.out.println("El raton come");
            Thread.sleep(tiempoAlimentacion*1000);
            System.out.println("El raton termina de comer");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Raton raton1 = new Raton("pepe", 4);
        Raton raton2 = new Raton("lucas", 5);
        Raton raton3 = new Raton("carlos", 6);
        raton1.comer();
        raton2.comer();
        raton3.comer();
    }
}
