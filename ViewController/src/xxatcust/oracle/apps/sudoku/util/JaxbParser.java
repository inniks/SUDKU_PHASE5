package xxatcust.oracle.apps.sudoku.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import java.nio.charset.Charset;

import javax.faces.application.FacesMessage;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.io.IOUtils;

import org.xml.sax.SAXException;

import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.Config;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.Contract;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.Cooling;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.Customer;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.Deal;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.Digital;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.Docking;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.Dps;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.DutifUtil;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.Infra;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.InfraUpgrade;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.Mani;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.ModelBom;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.Pmf;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.PogoMappingFile;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.QHeader;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.Ruleset;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.Salesteam;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.SwLicenses;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.Thead;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.V93kQuote;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.Wksta;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.WtySupport;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.XClass;


public class JaxbParser {
    public JaxbParser() {
        super();
    }


    public static V93kQuote jaxbXMLToObject(InputStream inputStream,
                                            File xsdFile) throws JAXBException,
                                                                 SAXException {
        V93kQuote parent = null;
        JAXBContext context =
            JAXBContext.newInstance(V93kQuote.class, Config.class,
                                    QHeader.class, Customer.class,
                                    ModelBom.class, Contract.class,
                                    Cooling.class, Deal.class, Digital.class,
                                    Docking.class, Dps.class, DutifUtil.class,
                                    Infra.class, Mani.class, Ruleset.class,
                                    Salesteam.class, SwLicenses.class,
                                    Thead.class, Wksta.class, WtySupport.class,
                                    XClass.class, Pmf.class,
                                    PogoMappingFile.class, InfraUpgrade.class);
        Unmarshaller un = context.createUnmarshaller();
        SchemaFactory sf =
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema v93Schema = sf.newSchema((xsdFile));
        un.setSchema(v93Schema);
        parent = (V93kQuote)un.unmarshal(inputStream);
        return parent;
    }

    public static void jaxbObjectToXML(V93kQuote v93k) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(V93kQuote.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                                       Boolean.TRUE);
            File file =
                new File("D://Projects//Advantest//JsonResponse/exportTarget.xml");
            jaxbMarshaller.marshal(v93k, file);
        } catch (JAXBException e) {
            ADFUtils.showFacesMessage("A file is already uploaded or an incorrect file has been uploaded",
                                      FacesMessage.SEVERITY_ERROR);
        }
    }

    public static InputStream trimWhiteSpaces(InputStream inputStream) {
        try {

            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer, "UTF-8");
            String theString = writer.toString();
            BufferedReader reader =
                new BufferedReader(new StringReader(theString));
            StringBuffer result = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {

                result.append(line.trim());
                System.out.println("Line " + line);
            }
            InputStream is =
                new ByteArrayInputStream(result.toString().getBytes(Charset.forName("UTF-8")));
            return is;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
