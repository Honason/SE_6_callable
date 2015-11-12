package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import controllers.DataService;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 * REST Web Service
 *
 * @author honason
 */
@Path("/group")
public class API {
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new instance of API
     */
    public API() {
    }
    
    //@Path("{count}/{args}")
    @GET
    @Produces("application/json")
    public String getJson() throws InterruptedException, ExecutionException, MalformedURLException, IOException, TimeoutException {
        ArrayList<String> urls = new ArrayList<String>();

        //Class A
        urls.add("http://cphbusinessjb.cloudapp.net/CA2/");
        urls.add("http://ca2-ebski.rhcloud.com/CA2New/");
        urls.add("http://ca2-chrislind.rhcloud.com/CA2Final/");
        urls.add("http://ca2-pernille.rhcloud.com/NYCA2/");
        urls.add("https://ca2-jonasrafn.rhcloud.com:8443/company.jsp");
        urls.add("http://ca2javathehutt-smcphbusiness.rhcloud.com/ca2/index.jsp");

        //Class B
        urls.add("https://ca2-ssteinaa.rhcloud.com/CA2/");
        urls.add("http://tomcat-nharbo.rhcloud.com/CA2/");
        urls.add("https://ca2-cphol24.rhcloud.com/3.semCa.2/");
        urls.add("https://ca2-ksw.rhcloud.com/DeGuleSider/");
        urls.add("http://ca2-ab207.rhcloud.com/CA2/index.html");
        urls.add("http://ca2-sindt.rhcloud.com/CA2/index.jsp");
        urls.add("http://ca2gruppe8-tocvfan.rhcloud.com/");
        urls.add("https://ca-ichti.rhcloud.com/CA2/");

        //Class COS
        urls.add("https://ca2-9fitteen.rhcloud.com:8443/CA2/");
        urls.add("https://cagroup04-coolnerds.rhcloud.com/CA_v1/index.html");
        urls.add("http://catwo-2ndsemester.rhcloud.com/CA2/");
        
        DataService dataService = new DataService();
        return dataService.getGroups(urls);
    }

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}