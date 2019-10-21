package xxatcust.oracle.apps.sudoku.bean;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.adf.controller.v2.lifecycle.ADFLifecycle;
import oracle.adf.controller.v2.lifecycle.PagePhaseEvent;
import oracle.adf.controller.v2.lifecycle.PagePhaseListener;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.share.ADFContext;
import oracle.adf.share.Environment;
import oracle.adf.share.logging.ADFLogger;

import oracle.apps.fnd.ext.common.AppsRequestWrapper;
import oracle.apps.fnd.ext.common.AppsSessionHelper;
import oracle.apps.fnd.ext.common.CookieStatus;
import oracle.apps.fnd.ext.common.EBiz;
import oracle.apps.fnd.ext.common.Session;
import oracle.apps.fnd.ext.common.State;

import oracle.jbo.ApplicationModule;
import oracle.jbo.JboException;
import oracle.jbo.server.DBTransaction;

import xxatcust.oracle.apps.sudoku.model.module.SudokuAMImpl;
import xxatcust.oracle.apps.sudoku.util.ADFUtils;
import xxatcust.oracle.apps.sudoku.util.ConfiguratorUtils;
import xxatcust.oracle.apps.sudoku.util.JSONUtils;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.InputParams;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.SessionDetails;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.V93kQuote;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.UiSelection;


public class SudokuPagePhaseListener implements PagePhaseListener {
    private static ADFLogger _logger =
        ADFLogger.createADFLogger(SudokuPagePhaseListener.class);
    String _home_url = null;
    String currentUrlName = null;
    String _logout_url = null;

    public SudokuPagePhaseListener() {
        super();
    }

    public void manageAttributes(SudokuAMImpl amClient) {
        ADFUtils.setSessionScopeValue("parentObject", null);
        FacesContext fctx = FacesContext.getCurrentInstance();
        Session session = amClient.getAppsSession();
        //   Map ebsAttribs = session.getInfo();
        currentUrlName = amClient.getCurrentUrl();
        _home_url = currentUrlName + "OA.jsp?OAFunc=OAHOMEPAGE#";
        _logout_url = currentUrlName + "OALogout.jsp?menu=Y";
        ExternalContext ectx = fctx.getExternalContext();
        HttpSession httpSession = (HttpSession)ectx.getSession(false);
        httpSession.setAttribute("_home_url", _home_url);
        httpSession.setAttribute("_logout_url", _logout_url);
    }

    public void afterPhase(PagePhaseEvent pagePhaseEvent) {
    }

    public void beforePhase(PagePhaseEvent pagePhaseEvent) {

        //validateEBSSession(pagePhaseEvent);
    }

    public static ApplicationModule getAppModule() {
        BindingContext bctx = BindingContext.getCurrent();
        DCDataControl dc = bctx.findDataControl("SudokuAMDataControl");
        ApplicationModule am = (ApplicationModule)dc.getDataProvider();

        return am;
    }

    //    public Connection getConnFromDS(ApplicationModuleImpl am) {
    //        Statement st = am.getDBTransaction().createStatement(0);
    //        Connection conn = null;
    //        try {
    //            conn = st.getConnection();
    //            st.close();
    //        } catch (SQLException ex) {
    //            _logger.severe("Error , ", ex);
    //            throw (new JboException(ex));
    //        }
    //        return conn;
    //    }
    //

    private void initializeAppsContext(String respId, String userId,
                                       String applicationId) {
        ApplicationModule am = getAppModule();
        DBTransaction txn = (DBTransaction)am.getTransaction();
        CallableStatement st = null;
        try {

            st =
 txn.createCallableStatement("BEGIN fnd_global.apps_initialize(:1, :2, :3); END;",
                             0);
            st.setString(1, userId);
            st.setString(2, respId);
            st.setString(3, applicationId);
            st.execute();
            st.close();

        } catch (Exception e) {
            _logger.info("Main Exception2===>" + e.getMessage());
        }
    }

    public void logoutEBS() {
        _logger.info("Logging out the user from EBS");
        ADFUtils.setSessionScopeValue("parentObject", null);
        FacesContext fctx = FacesContext.getCurrentInstance();
        HttpServletRequest request =
            (HttpServletRequest)fctx.getExternalContext().getRequest();
        HttpServletResponse response =
            (HttpServletResponse)fctx.getExternalContext().getResponse();
        //invalidate ICX session & HTTP session
        AppsRequestWrapper wrappedRequest = null;
        String logoutEbsUrl = null;
        try {
            ApplicationModule am = getAppModule();

            _logger.info("am==>" + am);
            javax.naming.Context initialContext =
                new javax.naming.InitialContext();
            javax.sql.DataSource dataSource =
                (javax.sql.DataSource)initialContext.lookup("jdbc/myDS1");

            Connection EBSconn = dataSource.getConnection();

            //Connection EBSconn = getConnFromDS((ApplicationModuleImpl)am);
            ServletContext servContext =
                (ServletContext)ADFContext.getCurrent().getEnvironment().getContext();
            String applServerID =
                servContext.getInitParameter("APPL_SERVER_ID");
            _logger.info("applServerID==>" + applServerID);
            EBiz instance = new EBiz(EBSconn, applServerID);
            wrappedRequest =
                    new AppsRequestWrapper(request, response, EBSconn, instance);

            logoutEbsUrl =
                    wrappedRequest.getEbizInstance().getAppsServletAgent();
            logoutEbsUrl = logoutEbsUrl + "OALogout.jsp?menu=Y";
            _logger.info("logoutEbsUrl = " + logoutEbsUrl);
            Session sessionEBS = wrappedRequest.getAppsSession();

            //logout only if it is present
            if (sessionEBS != null) {
                AppsSessionHelper helper =
                    new AppsSessionHelper(wrappedRequest.getEbizInstance());
                helper.destroyAppsSession(wrappedRequest.getAppsSession(),
                                          wrappedRequest, response);


            }
            ExternalContext ectx =
                FacesContext.getCurrentInstance().getExternalContext();
            HttpSession sessionHttp = (HttpSession)ectx.getSession(false);
            if (sessionHttp != null) {
                try {
                    sessionHttp.invalidate();
                } catch (IllegalStateException ex) {
                    _logger.severe("Error - HttpSession already invalidated,",
                                   ex);
                }
            }
            response.sendRedirect(logoutEbsUrl);
            fctx.responseComplete();
        } catch (Exception ex) {
            _logger.severe("Error , ", ex);
            throw (new JboException(ex));
        }
    }

    public void validateEBSSession(PagePhaseEvent pagePhaseEvent) {
        if (ADFLifecycle.INIT_CONTEXT_ID == pagePhaseEvent.getPhaseId()) {
            if (ADFUtils.getSessionScopeValue("JSESSIONID") == null) {
                EBiz INSTANCE = null;
                Environment env = ADFContext.getCurrent().getEnvironment();
                HttpServletRequest request =
                    ((HttpServletRequest)env.getRequest());
                HttpServletResponse response =
                    ((HttpServletResponse)env.getResponse());
                HttpSession sessionADF = request.getSession();
                AppsRequestWrapper wrappedRequest = null;
                String applServerID = null;
                Connection EBSconn = null;
                String jSession = (String)request.getAttribute("JSESSION");
                String quoteNumber =
                    (String)request.getAttribute("pQuoteNumber");
                _logger.info("quoteNumbervalue from pQuoteNumber: " +
                             quoteNumber);
                Map map1 =
                    FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
                if (jSession == null) {
                    jSession =
                            map1.get("JSESSION") == null ? "" : map1.get("JSESSION").toString();
                }
                sessionADF.setAttribute("JSESSIONID", jSession);
                _logger.info("********JSSION********** " +
                             ADFUtils.getSessionScopeValue("JSESSIONID"));

                if (quoteNumber == null) {
                    quoteNumber =
                            map1.get("pQuoteNumber") == null ? "" : map1.get("pQuoteNumber").toString();
                    _logger.info("2nd quoteNumber: " + quoteNumber);
                }
                ADFUtils.setSessionScopeValue("targetQuoteNumber",
                                              quoteNumber);
                _logger.info("ValidateEBSSession:Quote value from session" +
                             ADFUtils.getSessionScopeValue("targetQuoteNumber"));
                if(quoteNumber!=null && !quoteNumber.equals("")){
                    _logger.info("Setting input map params......**");
                    HashMap inputMapParams = (HashMap)ADFUtils.getSessionScopeValue("inputParamsMap");
                    if(inputMapParams==null){
                        inputMapParams = new HashMap();
                    }
                    inputMapParams.put("importSource", "quoteFromSearch");
                    ADFUtils.setSessionScopeValue("inputParamsMap", inputMapParams);
                }
                //Test Code to call Servlet on page load from search quotes


                try {
                    ApplicationModule am = getAppModule();
                    javax.naming.Context initialContext =
                        new javax.naming.InitialContext();
                    javax.sql.DataSource dataSource =
                        (javax.sql.DataSource)initialContext.lookup("jdbc/myDS1");

                    EBSconn = dataSource.getConnection();
                    SudokuAMImpl amClient = (SudokuAMImpl)am;
                    manageAttributes(amClient);
                    ServletContext servContext =
                        (ServletContext)ADFContext.getCurrent().getEnvironment().getContext();
                    _logger.info("servLetContext : " + servContext);
                    applServerID =
                            servContext.getInitParameter("APPL_SERVER_ID");
                    _logger.info("applServerID: " + applServerID);
                    INSTANCE = new EBiz(EBSconn, applServerID);
                    wrappedRequest =
                            new AppsRequestWrapper(request, response, EBSconn,
                                                   INSTANCE);
                    oracle.apps.fnd.ext.common.Session sessionEBS =
                        wrappedRequest.getAppsSession(true);
                    _logger.info("sessionEBS : " + sessionEBS);
                    if (sessionEBS != null) {
                        if (!isEBSSessionValid(sessionEBS)) {
                            _logger.info("EBS Session Not valid ,Logging out ");
                            logoutEBS();
                            return;
                        } else {
                            String userId = sessionEBS.getUserId();
                            _logger.info("UserId : " + userId);
                            String userName = sessionEBS.getUserName();
                            _logger.info("userName : " + userName);
                            String language = wrappedRequest.getLangCode();
                            _logger.info("language : " + language);
                            Map<String, String> info = sessionEBS.getInfo();
                            String respId = info.get("RESPONSIBILITY_ID");
                            _logger.info("respId : " + respId);
                            String respApplId =
                                info.get("RESPONSIBILITY_APPLICATION_ID");
                            _logger.info("respApplId : " + respApplId);
                            String applicationId = info.get("RESP_APPL_ID");
                            _logger.info("applicationId : " + applicationId);
                            String secGrpId = info.get("SECURITY_GROUP_ID");
                            _logger.info("secGrpId : " + secGrpId);
                            String orgId = info.get("ORG_ID");
                            _logger.info("orgId : " + orgId);
                            //Initialize appsContext
                            _logger.info("Initializing Apps Context.....");
                            initializeAppsContext(respId, userId,
                                                  applicationId);
                            _logger.info("Apps Context Initialized.....");
                            String sessionCookieValue =
                                wrappedRequest.getICXCookieValue();
                            Cookie[] listOfCookies =
                                wrappedRequest.getCookies();

                            for (Cookie cookie : listOfCookies) {
                                _logger.info("Cookie value " +
                                             cookie.getName() + " " +
                                             cookie.getValue());
                            }
                            _logger.info("Session Cookie Value " +
                                         sessionCookieValue);
                            _logger.info("Session ID " +
                                         sessionEBS.getSessionId());
                            _logger.info("Raw Info " +
                                         sessionEBS.getRawInfo());
                            sessionADF.setAttribute("EBS_SESSION_INSTANCE",
                                                    INSTANCE);
                            sessionADF.setAttribute("EBS_APPS_SESSION_INSTANCE",
                                                    sessionEBS);
                            sessionADF.setAttribute("listOfCookies",
                                                    listOfCookies);
                            sessionADF.setAttribute("SessionCookieValue",
                                                    sessionCookieValue);
                            sessionADF.setAttribute("UserId", userId);
                            sessionADF.setAttribute("RespId", respId);
                            sessionADF.setAttribute("ApplId", respApplId);
                            sessionADF.setAttribute("UserName", userName);
                            sessionADF.setAttribute("Language", language);
                            sessionADF.setAttribute("SecGrpId", secGrpId);
                            sessionADF.setAttribute("OrgId", orgId);
                            if (quoteNumber != null &&
                                !quoteNumber.equals("")) {
                                callCIOServletOnLoad(quoteNumber);

                            }
                            javax.servlet.http.Cookie cookie =
                                wrappedRequest.getICXCookie();
                            String icxcookieName = cookie.getName();
                            String icxcookieValue = cookie.getValue();
                            _logger.info("Cookie Name " + icxcookieName);
                            _logger.info("Cookie Value " + icxcookieValue);
                            sessionADF.setAttribute("cookie", cookie);
                            sessionADF.setAttribute("icxcookieName",
                                                    icxcookieName);
                            sessionADF.setAttribute("icxcookieValue",
                                                    icxcookieValue);
                        }
                    }

                } catch (SQLException se) {

                    _logger.info("Exception in " + this.getClass().getName() +
                                 se.getMessage());
                    se.printStackTrace();
                } catch (Exception e) {
                    _logger.info("Exception in " + this.getClass().getName() +
                                 e.getMessage());
                    e.printStackTrace();
                } finally {
                    try {
                        if (EBSconn != null) {
                            EBSconn.close();
                        }
                    } catch (SQLException se) {
                        _logger.info("Exception in " +
                                     this.getClass().getName() +
                                     se.getMessage());
                    }
                }
            }
        }
    }

    public boolean isEBSSessionValid(oracle.apps.fnd.ext.common.Session sessionEBS) {
        State state = sessionEBS.getCurrentState();
        CookieStatus icxSessionStatus = state.getIcxCookieStatus();
        _logger.info("Cookie Status : " + icxSessionStatus);
        if (!icxSessionStatus.equals(CookieStatus.VALID)) {
            return false;
        }
        return true;
    }

    public void callCIOServletOnLoad(String targetQuoteNum) throws IOException,
                                                                   JsonGenerationException,
                                                                   JsonMappingException {
        SessionDetails sessionDetails = new SessionDetails();
        String userId =
            (String)ADFUtils.getSessionScopeValue("UserId") == null ? "0" :
            (String)ADFUtils.getSessionScopeValue("UserId");
        String timestamp = Long.toString(System.currentTimeMillis());
        String uniqueSessionId = userId.concat(timestamp);
        InputParams inputParam = new InputParams();
        UiSelection uiSelection = new UiSelection();
        uiSelection.setUniqueSessionId(uniqueSessionId);
        ADFUtils.setSessionScopeValue("uniqueSessionId", uniqueSessionId);
        //Get Session details added to the POJO object
        sessionDetails.setApplicationId((String)ADFUtils.getSessionScopeValue("ApplId") ==
                                        null ? "880" :
                                        (String)ADFUtils.getSessionScopeValue("ApplId"));
        sessionDetails.setRespId((String)ADFUtils.getSessionScopeValue("RespId") ==
                                 null ? "51156" :
                                 (String)ADFUtils.getSessionScopeValue("RespId"));
        sessionDetails.setUserId((String)ADFUtils.getSessionScopeValue("UserId") ==
                                 null ? "0" :
                                 (String)ADFUtils.getSessionScopeValue("UserId"));
        sessionDetails.setTargetQuoteNumber(targetQuoteNum);
        //Add input params
        inputParam.setCopyReferenceConfiguration(true); //Passing copy ref value as true
        inputParam.setImportSource("LOAD_QUOTE_FROM_SEARCH");
        V93kQuote v93k = new V93kQuote();
        v93k.setInputParams(inputParam);
        v93k.setSessionDetails(sessionDetails);
        v93k.setUiSelection(uiSelection);
        String jsonStr = JSONUtils.convertObjToJson(v93k);
        ObjectMapper mapper = new ObjectMapper();
        String responseJson =
            (String)ConfiguratorUtils.callConfiguratorServlet(jsonStr);
        _logger.info("Response JSON " + responseJson);
        v93k = mapper.readValue(responseJson, V93kQuote.class);
        ADFUtils.setSessionScopeValue("parentObject", v93k);
    }

}
