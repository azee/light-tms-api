package ru.lighttms.tms.api;

import java.io.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import com.sun.jersey.spi.resource.Singleton;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 */

@Path("/doc")
public class DocService {
     /**
     * Get API documentation
     */
    @GET
    @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_XML})
    public String getDoc() throws IOException {
        StringWriter writer = new StringWriter();

        //Transforming wadl
        try {
            InputStream xslIs = getClass().getResourceAsStream(
                    "/wadl.xsl");

            InputStream result = getClass().getResourceAsStream(
                    "/application.wadl");

            //StringReader reader = new StringReader(result);
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer(
            new javax.xml.transform.stream.StreamSource(xslIs));
            transformer.transform(
                //new javax.xml.transform.stream.StreamSource(reader),
                new javax.xml.transform.stream.StreamSource(result),
                new javax.xml.transform.stream.StreamResult(writer));

            //result = writer.toString();
        } catch (Exception e) {return "";}


        return writer.toString();
    }



}
