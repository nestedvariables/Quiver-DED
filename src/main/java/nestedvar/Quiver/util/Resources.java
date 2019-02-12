package nestedvar.Quiver.util;

import java.lang.management.ManagementFactory;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public class Resources {
    public double getCPULoad()  {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = ObjectName.getInstance("java.lang:type=OperatingSystem");
            AttributeList list = mbs.getAttributes(name, new String[]{"ProcessCpuLoad"});
            if (list.isEmpty()) return Double.NaN;
            Attribute att = (Attribute)list.get(0);
            Double value  = (Double)att.getValue();
            if (value == -1.0) return Double.NaN;
            return ((int)(value * 1000) / 10.0);
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