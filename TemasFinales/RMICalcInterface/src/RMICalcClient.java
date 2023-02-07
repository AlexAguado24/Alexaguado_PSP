import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMICalcClient {
    public static void main(String[] args) {

        RMICalcInterface calcu = null;

        try {
            Registry registry = LocateRegistry.getRegistry("localhost",5555);
            calcu = (RMICalcInterface)registry.lookup("Calculadora");

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }

        if(calcu != null) {
            try {
                System.out.println("1+5 = " + calcu.suma(1, 5));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
