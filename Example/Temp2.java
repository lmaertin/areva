import org.jdom.*; 
import java.util.*; 
public class Temp2 {
public static void main(String[] args) {
ARXMLLoader neu = new ARXMLLoader();
AutosarExport a = neu.loadArchitecture("/Users/maertin/Documents/dev/workspaces/runtime-EclipseApplication/komfortsystem_sysarch_260110.arxml");
Temp tmp = new Temp(a);
System.out.println("result:;" + tmp.calcRating());
}
}
