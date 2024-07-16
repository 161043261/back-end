import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class Main {

    public static void main(String[] args) throws LifecycleException {
        final int port = 8080;
        String webapp = new File("./src/main/webapp").getAbsolutePath();
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector();
        Context context = tomcat.addWebapp("servlet", webapp); // contextPath = "servlet"
        WebResourceRoot root = new StandardRoot(context);
        root.addPreResources(new DirResourceSet(root, "/WEB-INF/classes", // the webAppMount must begin with "/"
                new File("./target/classes").getAbsolutePath(), "/"));
        context.setResources(root);
        tomcat.start();
        tomcat.getServer().await();
    }
}
