package xxatcust.oracle.apps.sudoku.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.net.URLEncoder;

import java.util.Map;

import javax.faces.context.FacesContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.adf.share.ADFContext;
import oracle.adf.share.Environment;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.apps.fnd.ext.common.AppsRequestWrapper;

import xxatcust.oracle.apps.sudoku.bean.XMLImportPageBean;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.V93kQuote;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.UiSelection;

public class ConfiguratorUtils {
    public ConfiguratorUtils() {
        super();
    }
    private static ADFLogger _logger =
        ADFLogger.createADFLogger(ConfiguratorUtils.class);

    public static String callConfiguratorServlet(String jsonStr) throws MalformedURLException,
                                                                        IOException {
        StringBuffer strBuf = new StringBuffer();
        Cookie[] listOfCookies = (Cookie[])ADFUtils.getSessionScopeValue("listOfCookies");
        String cookieBuf = null;
        String str1 = null;
        String str2 = null;
        String jsessionId = (String)ADFUtils.getSessionScopeValue("jsessionId");
//        if(listOfCookies!=null && listOfCookies.length>1){
//            str1 = listOfCookies[0].getName()+"="+listOfCookies[0].getValue()+";";
//            //str2 = listOfCookies[1].getName()+"="+listOfCookies[1].getValue();
//        }
        if(jsessionId!=null){
            for(Cookie cookie :listOfCookies){
                if(cookie.getName().equalsIgnoreCase("SPT2")){
                    str1 = cookie.getName()+"="+cookie.getValue()+";";
                }
            }
           
            str2 = "JSESSIONID="+jsessionId;
        }
      
       
        cookieBuf = str1+str2;
        URL url =
            new URL("http://usdcnvspt2ap1.adv.advantest.com:8000/OA_HTML/configurator/XXATSudokuCzServletP5");
        _logger.info("Print url in callConfiguratorServlet " + url);
        System.out.println("Opening Connection");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();        
        // inform the connection that we will send output and accept input
        long start = System.nanoTime();
        //con.setReadTimeout(300000);
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setConnectTimeout(300000);
        con.setRequestProperty("Content-Type",
                               "application/json; charset=UTF-8");
        con.setRequestProperty("Cookie", cookieBuf);
        
        //con.setRequestProperty("Cookie", cookieReq2);
        _logger.info("Calling the Servlet with Cookie property: "+con.getRequestProperty("Cookie"));
        // Don't use a cached version of URL connection.
        con.setUseCaches(false);
        con.setDefaultUseCaches(false);
        con.setRequestMethod("POST");
        PrintWriter outWriter = new PrintWriter(con.getOutputStream());
        outWriter.close();
        OutputStream os = con.getOutputStream();
        os.write(jsonStr.getBytes("UTF-8"));
        InputStream input = con.getInputStream();
        _logger.info("Print input in callConfiguratorServlet " + input);
        BufferedReader in = null;
        in = new BufferedReader(new InputStreamReader(input));
        _logger.info("Print in in callConfiguratorServlet " + in);
        String inputLine = null;
        while ((inputLine = in.readLine()) != null) {
            strBuf.append("" + inputLine);
        }

        _logger.info("Response from servlet::: " + strBuf.toString());
        return strBuf.toString();
    }

    public static String callEBSServlet(String jsonStr) throws ServletException,
                                                               IOException {
        Environment env = ADFContext.getCurrent().getEnvironment();
        HttpServletRequest request =
            ((HttpServletRequest)env.getRequest());
        HttpServletResponse response =
            ((HttpServletResponse)env.getResponse());
        AppsRequestWrapper wrappedRequest = null;
        //configurator/XXATSudokuCzServletP5
        String servletUrl =
            "http://usdcnvspt2ap1.adv.advantest.com:8000/OA_HTML/configurator/XXATSudokuCzServletP5";
        FacesContext fctx = FacesContext.getCurrentInstance();
      
       
        


        return null;
    }


}
