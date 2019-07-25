package xxatcust.oracle.apps.sudoku.util;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.util.HashMap;

import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.io.IOUtils;
import org.apache.myfaces.trinidad.model.UploadedFile;

import org.xml.sax.SAXException;

public class XMLUtils {
    private static final String FILE_LOC =
        "xxatcust/oracle/apps/sudoku/view/CREATED_FILE";
    public XMLUtils() {
        super();
    }


    public static String validateXMLSchema(File xsdFile, InputStream fileIs) {
        boolean validated = true ;
        File xmlFile = createFile(fileIs) ;
        SchemaFactory factory =
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = null;
        //File xsdFile = new File(xsdPath) ;
        try {
            schema = factory.newSchema(xsdFile);
        } catch (SAXException e) {
            e.printStackTrace();
        }
        Validator validator = schema.newValidator();
        try {
            validator.validate(new StreamSource(xmlFile));
        } catch (SAXException e) {
           ADFUtils.routeExceptions(e);
        } catch (IOException e) {
            ADFUtils.routeExceptions(e);
        }
        return "Y";
    }

    public static File createFile(InputStream is){
        File f = new File(FILE_LOC) ;
        
        try{
            OutputStream outputStream = new FileOutputStream(f);
            IOUtils.copy(is, outputStream);
        } catch (FileNotFoundException e) {
            // handle exception here
        } catch (IOException e) {
            // handle exception here
        }
        return f ;
    }

}
