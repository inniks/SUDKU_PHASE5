package xxatcust.oracle.apps.sudoku.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import xxatcust.oracle.apps.sudoku.viewmodel.pojo.V93kQuote;

public class JSONUtils {
    public JSONUtils() {
        super();
    }

    public static Object convertJsonToObject(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
        //mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        Object obj = null;
        try {
            obj =
mapper.readValue(new File("D://Projects//Advantest//JsonResponse/UIRoot.json"),
                 V93kQuote.class);

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static String convertObjToJson(Object obj) throws IOException,
                                                             JsonGenerationException,
                                                             JsonMappingException {
        ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
        //mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        String jsonInString = mapper.writeValueAsString(obj);
        return jsonInString;
    }

    public static void prettyPrintJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        try {
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
