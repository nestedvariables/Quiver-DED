package nestedvar.Quiver.util;

public class Resources {
    public double getCPULoad()  {
        try {
            // TODO fix this
            return 9000.0;
        }
        catch (Exception e) {
            return 0.0;
        }
    }

    public String getRAMUsage() {
        try {
            double freeRAM = Runtime.getRuntime().freeMemory() / 1024 / 1024 / 1024;
            double totalRAM = Runtime.getRuntime().totalMemory() / 1024 / 1024 / 1024;
            return freeRAM + "/" + totalRAM;
        } 
        catch (Exception e) {
            return "0.0";
        }
    }
}