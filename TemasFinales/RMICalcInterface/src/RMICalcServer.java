import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMICalcServer implements RMICalcInterface {
    @Override
    public int suma(int a, int b) throws RemoteException {
        return a+b;
    }

    public static void main(String[] args) {
        Registry reg = null;
        try {
            reg = LocateRegistry.createRegistry(5555);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        RMICalcServer serverObject = new RMICalcServer();

        try {
            reg.rebind("Calculadora",(RMICalcInterface) UnicastRemoteObject.exportObject(serverObject,0));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}