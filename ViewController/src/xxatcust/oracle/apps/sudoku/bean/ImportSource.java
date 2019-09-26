package xxatcust.oracle.apps.sudoku.bean;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import java.util.TreeMap;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.xml.bind.JAXBException;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;

import oracle.adf.view.rich.component.rich.nav.RichCommandImageLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandToolbarButton;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

import org.apache.commons.io.IOUtils;
import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.event.ReturnEvent;
import org.apache.myfaces.trinidad.model.UploadedFile;

import org.xml.sax.SAXException;

import xxatcust.oracle.apps.sudoku.util.ADFUtils;
import xxatcust.oracle.apps.sudoku.util.ConfiguratorUtils;
import xxatcust.oracle.apps.sudoku.util.JSONUtils;
import xxatcust.oracle.apps.sudoku.util.JaxbParser;
import xxatcust.oracle.apps.sudoku.util.SudokuUtils;
import xxatcust.oracle.apps.sudoku.util.XMLUtils;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.InputParams;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.SessionDetails;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.V93kQuote;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.UiSelection;


public class ImportSource {
    private String sourceSelected;
    private RichInputText budgetQuote;
    private RichInputText formalQuote;
    private RichInputFile inputFileBinding;
    V93kQuote v93kQuote;

    public ImportSource() {
    }
    private static ADFLogger _logger =
        ADFLogger.createADFLogger(XMLImportPageBean.class);
    private final String XSD_FILE =
        "xxatcust/oracle/apps/sudoku/view/V93000 C&Q 3.0 - XML File Schema.xsd";

    public void importSrcSelected(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            String selectedVal = null;
            UIComponent uiComp = (UIComponent)valueChangeEvent.getSource();
            uiComp.processUpdates(FacesContext.getCurrentInstance());
            DCIteratorBinding iter =
                ADFUtils.findIterator("ImportSourceVO1Iterator");
            if (iter != null) {
                Row currRw = iter.getCurrentRow();
                if (currRw != null) {
                    currRw.setAttribute("BudgetQuoteId", null);
                    currRw.setAttribute("FormalQuoteId", null);
                    currRw.setAttribute("CopyRefConfig", null);
                    currRw.setAttribute("ReuseQuote", "Y");
                    inputFileBinding.setValue(null);
                }
            }
            BindingContainer bindings =
                (BindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            JUCtrlListBinding listBinding =
                (JUCtrlListBinding)bindings.get("ImportSrcMeaning");
            Row selectedRow = (Row)listBinding.getSelectedValue();
            if (selectedRow != null) {
                selectedVal =
                        (String)selectedRow.getAttribute("ImportSrcCode");
                setSourceSelected(selectedVal);
                System.out.println("Selected Import Source " + selectedVal);
            }
        }

    }

    public void copyRefChanged(ValueChangeEvent valueChangeEvent) {
        UIComponent uiComp = (UIComponent)valueChangeEvent.getSource();
        uiComp.processUpdates(FacesContext.getCurrentInstance());
        BindingContainer bindings =
            (BindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        JUCtrlListBinding listBinding =
            (JUCtrlListBinding)bindings.get("CopyRefConfig");
        Object selectedValue = listBinding.getSelectedValue();
    }

    public void reuseQuoteChange(ValueChangeEvent valueChangeEvent) {
        String selectedVal = null;
        UIComponent uiComp = (UIComponent)valueChangeEvent.getSource();
        uiComp.processUpdates(FacesContext.getCurrentInstance());
        BindingContainer bindings =
            (BindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        JUCtrlListBinding listBinding =
            (JUCtrlListBinding)bindings.get("ReuseQuote");
        Row selectedRow = (Row)listBinding.getSelectedValue();
        if (selectedRow != null) {
            selectedVal = (String)selectedRow.getAttribute("ReuseCode");
        }
    }

    public void setSourceSelected(String sourceSelected) {
        this.sourceSelected = sourceSelected;
    }

    public String getSourceSelected() {
        return sourceSelected;
    }

    public void setBudgetQuote(RichInputText budgetQuote) {
        this.budgetQuote = budgetQuote;
    }

    public RichInputText getBudgetQuote() {
        return budgetQuote;
    }

    public void setFormalQuote(RichInputText formalQuote) {
        this.formalQuote = formalQuote;
    }

    public RichInputText getFormalQuote() {
        return formalQuote;
    }

    public void importConfiguration(ActionEvent actionEvent) {
        UIComponent uiComponent = (UIComponent)actionEvent.getSource();
        uiComponent.processUpdates(FacesContext.getCurrentInstance());
        ADFUtils.setSessionScopeValue("refreshImport", "Y");
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }


    public String getFndMessages(String msgName) {
        String msgTxt = null;
        OperationBinding op = getBindings().getOperationBinding("getFNDMsges");
        op.getParamsMap().put("msgName", msgName);
        if (op != null)
            msgTxt = (String)op.execute();
        return msgTxt;
    }


    public void dialogActionListener(ActionEvent actionEvent) {
        //        Boolean isDuplicateQuote = true;
        //        Boolean isUpdateQuote = false;
        //        String duplicateQuoteNum = null;
        int respid =
            Integer.parseInt((String)ADFUtils.getSessionScopeValue("RespId") ==
                             null ? "51156" :
                             (String)ADFUtils.getSessionScopeValue("RespId"));
        int usrId =
            Integer.parseInt((String)ADFUtils.getSessionScopeValue("UserId") ==
                             null ? "0" :
                             (String)ADFUtils.getSessionScopeValue("UserId"));
        String invalidUploadMsgg =
            getFndMessages(SudokuUtils.invalidUploadMsg);
        String newQtMsg = getFndMessages(SudokuUtils.newQteMsg);
        ADFContext af = ADFContext.getCurrent();
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent uiComponent = (UIComponent)actionEvent.getSource();
        uiComponent.processUpdates(FacesContext.getCurrentInstance());
        //Take Values from VO binding
        String budgetQuoteNum = null;
        String formalQuoteNum = null;
        String importSource = null;
        String quoteNumber = null;
        String reuseQuote =
            null; //true if "Re-use the existing Quote ID, if possible" is selected
        String copyReferenceConfiguration = null;
        boolean copyRefConf = false;
        DCIteratorBinding iter =
            ADFUtils.findIterator("ImportSourceVO1Iterator");
        if (iter != null) {
            Row currRow = iter.getCurrentRow();
            if (currRow != null) {
                importSource = (String)currRow.getAttribute("ImportSrcCode");
                budgetQuoteNum =
                        (String)currRow.getAttribute("BudgetQuoteId"); //can be formal quote id as well
                formalQuoteNum = (String)currRow.getAttribute("FormalQuoteId");
                // if(budgetQuoteNum!=null && b)
                System.out.println("Reuse quote value " +
                                   currRow.getAttribute("ReuseQuote"));
                reuseQuote = (String)currRow.getAttribute("ReuseQuote");
                Boolean reuseQuoteBool = true;
                if (reuseQuote != null && reuseQuote.equalsIgnoreCase("N")) {
                    reuseQuoteBool = false;
                }
                copyReferenceConfiguration =
                        (String)currRow.getAttribute("CopyRefConfig");
                if (copyReferenceConfiguration != null) {
                    if (copyReferenceConfiguration.equalsIgnoreCase("Y")) {
                        copyRefConf = true;
                    } else
                        copyRefConf = false;
                }
                if (importSource != null &&
                    importSource.equalsIgnoreCase("BUDGET_QUOTE")) {
                    quoteNumber = budgetQuoteNum;
                }
                if (importSource != null &&
                    importSource.equalsIgnoreCase("FORMAL_QUOTE")) {
                    quoteNumber = formalQuoteNum;
                }
                HashMap inputParamsMap =
                    (HashMap)ADFUtils.getSessionScopeValue("inputParamsMap"); //new HashMap();
                if (inputParamsMap == null) {
                    inputParamsMap = new HashMap();
                }
                inputParamsMap.put("importSource", importSource);
                inputParamsMap.put("quoteNumber", quoteNumber);

                inputParamsMap.put("reuseQuote", reuseQuoteBool);
                inputParamsMap.put("copyRefConf", copyRefConf);
                ADFUtils.setSessionScopeValue("inputParamsMap",
                                              inputParamsMap);
                if (quoteNumber != null) {
                    quoteNumber = quoteNumber.trim();
                }
                if (quoteNumber != null && !quoteNumber.equalsIgnoreCase("")) {
                    af.getSessionScope().put("quoteNumber", quoteNumber);
                }
            }
        }

        ADFUtils.setSessionScopeValue("targetQuoteNumber", null);
        ADFUtils.setSessionScopeValue("qheaderValidMap", null);
        String str = null;
        UploadedFile xmlFile = (UploadedFile)inputFileBinding.getValue();
        // if (xmlFile != null) {


        ADFUtils.setSessionScopeValue("refreshImport", "Y");
        ADFUtils.setSessionScopeValue("cancelAll", null);
        try {
            if (importSource != null &&
                importSource.equalsIgnoreCase("XML_FILE")) {

                af.getSessionScope().put("quoteNumber", null);
                parseXMLToPojo(xmlFile.getInputStream());
            } else {
                parseXMLToPojo(null);
            }
            V93kQuote v93kQuote =
                (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
            HashMap ruleSetMap = new HashMap();
            if (v93kQuote.getInputParams() != null) {
                ruleSetMap.put("topLevelCode",
                               v93kQuote.getInputParams().getRuleSetTopLevelChoice());
                ruleSetMap.put("secondLevelCode",
                               v93kQuote.getInputParams().getRuleSetSecondLevelChoice());
                TreeMap<String, ArrayList<String>> exceptionMap =
                    v93kQuote.getExceptionMap().getErrorList();
                List<String> errorMessages =
                    v93kQuote.getExceptionMap().getErrorsMessages();
                StringBuilder errMessage = new StringBuilder("ERROR");
                if (exceptionMap != null && exceptionMap.size() > 0) {

                    for (Map.Entry<String, ArrayList<String>> entry :
                         exceptionMap.entrySet()) {
                        String key = entry.getKey();
                        ArrayList<String> value = entry.getValue();
                        for (String s : value) {
                            errMessage.append(s);
                        }
                    }
                }
                if (errorMessages != null && errorMessages.size() > 0) {
                    for (String s : errorMessages) {
                        errMessage.append(s);
                    }
                }
                if (errMessage != null &&
                    errMessage.toString().equals("ERROR")) {
                    //no error
                    ruleSetMap.put("error", "N");
                } else {
                    ruleSetMap.put("error", "Y");

                }

                ADFUtils.setSessionScopeValue("ruleSetMap", ruleSetMap);
            }
            if (v93kQuote != null) {
                ADFUtils.setSessionScopeValue("isDuplicateQuote",
                                              v93kQuote.getSessionDetails().isDuplicateQuote());
                ADFUtils.setSessionScopeValue("isUpdateQuote",
                                              v93kQuote.getSessionDetails().isUpdateQuote());
                ADFUtils.setSessionScopeValue("isCreateQuote",
                                              v93kQuote.getSessionDetails().isCreateNewQuote());
                System.out.println("update status::::::::::" +
                                   v93kQuote.getSessionDetails().isUpdateQuote());
                System.out.println("Duplicate status::::::::::" +
                                   v93kQuote.getSessionDetails().isDuplicateQuote());
                System.out.println("Create status::::::::::" +
                                   v93kQuote.getSessionDetails().isCreateNewQuote());


                if (v93kQuote.getSessionDetails().isDuplicateQuote()) {
                    String msg = null;
                    StringBuilder msages = new StringBuilder("<html><body>");
                    String targetQuote =
                        (String)ADFUtils.getSessionScopeValue("quoteNumber");
                    if (targetQuote != null) {
                        OperationBinding ob =
                            getBindings().getOperationBinding("callDuplicateQuoteAPI");
                        ob.getParamsMap().put("quoteFromSesion", targetQuote);
                        ob.getParamsMap().put("respId", respid);
                        ob.getParamsMap().put("usrId", usrId);
                        if (ob != null) {
                            msg = (String)ob.execute();
                            if (msg != null && !("").equalsIgnoreCase(msg)) {
                                FacesMessage message = new FacesMessage(msg);
                                if (msg.contains("<html><body>")) {
                                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                                    fc.addMessage(null, message);
                                } else if (msg.contains("S-")) {
                                    String[] resultMsg = msg.split("-", 2);
                                    msg = resultMsg[1];
                                    message = new FacesMessage(msg);
                                    message.setSeverity(FacesMessage.SEVERITY_INFO);
                                    String[] arrOfStr = msg.split(":", 2);
                                    if (arrOfStr[1].toString() != null) {
                                        v93kQuote.getSessionDetails().setTargetQuoteNumber(arrOfStr[1].toString());
                                        ADFContext.getCurrent().getSessionScope().put("targetQuoteNumber",
                                                                                      arrOfStr[1].toString());

                                        if ((v93kQuote.getInputParams().getReuseQuote()) &&
                                            (v93kQuote.getSessionDetails().isDuplicateQuote() ||
                                             v93kQuote.getSessionDetails().isCreateNewQuote())) {
                                            msages.append("<p><b>");
                                            //                                            msages.append("New quote has been assigned" +
                                            //                                                          arrOfStr[1].toString() +
                                            //                                                          " as the existing cannot be used</b></p>");
                                            if (newQtMsg != null)
                                                msages.append(newQtMsg +
                                                              arrOfStr[1].toString());
                                            else
                                                msages.append(SudokuUtils.newQteMsg +
                                                              arrOfStr[1].toString());
                                            msages.append("</b></p>");
                                            msages.append("</body></html>");
                                            FacesMessage infoMsgs =
                                                new FacesMessage(msages.toString());
                                            infoMsgs.setSeverity(FacesMessage.SEVERITY_INFO);
                                            fc.addMessage(null, infoMsgs);

                                        }
                                    }
                                    //                                    fc.addMessage(null, message);
                                } else if (msg.contains("E-")) {
                                    String[] resultMsg = msg.split("-", 2);
                                    msg = resultMsg[1];
                                    message = new FacesMessage(msg);
                                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                                    fc.addMessage(null, message);
                                }
                            }
                        }
                    }
                }
                if (v93kQuote.getSessionDetails().isUpdateQuote()) {
                    if (ADFContext.getCurrent().getSessionScope().get("quoteNumber") !=
                        null)
                        v93kQuote.getSessionDetails().setSourceQuoteNumber(ADFContext.getCurrent().getSessionScope().get("quoteNumber").toString());
                }
            }
            //Get the quote related values based on uploaded quote number
            if (v93kQuote != null && v93kQuote.getQheaderObject() != null &&
                v93kQuote.getQheaderObject().getDealObject() != null &&
                v93kQuote.getQheaderObject().getDealObject().getQuoteid() !=
                null) {
                //Quote id exists in the xml,query other values from DB and attach to v93k object

            }

            //Trying to queue a button press event
            RichCommandImageLink button =
                (RichCommandImageLink)ADFUtils.findComponentInRoot("ctb1_vre"); // Navigate to view reference page
            ActionEvent acEvent = new ActionEvent(button);
            acEvent.queue();
            // }

        } catch (Exception jaxbe) {
            if (jaxbe instanceof IllegalArgumentException) {
                if (invalidUploadMsgg != null)
                    ADFUtils.showFacesMessage(invalidUploadMsgg,
                                              FacesMessage.SEVERITY_ERROR);
                else
                    ADFUtils.showFacesMessage(SudokuUtils.invalidUploadMsg,
                                              FacesMessage.SEVERITY_ERROR);

                //                ADFUtils.showFacesMessage("Invalid Upload/File Already uploaded,Unable to parse...",
                //                                          FacesMessage.SEVERITY_ERROR);
            }
            if (jaxbe instanceof IOException) {
                ADFUtils.showFacesMessage("EBS is not responding to the request,Please contact your system administrator : SERVER RESPONSE : 500",
                                          FacesMessage.SEVERITY_ERROR);
                jaxbe.printStackTrace();
            } else {
                str = jaxbe.getMessage();
                Throwable e = null;
                while (jaxbe.getCause() != null) {
                    jaxbe = (Exception)jaxbe.getCause();

                }
                str = jaxbe.getMessage();
                ADFUtils.addMessage(FacesMessage.SEVERITY_ERROR,
                                    jaxbe.getMessage());
                jaxbe.printStackTrace();
            }
        }


        if (str == null) {
            RichPopup impSrcPopup =
                (RichPopup)ADFUtils.findComponentInRoot("imSrcP1");
            if (impSrcPopup != null) {
                impSrcPopup.cancel();
            }
        }
        // RequestContext.getCurrentInstance().addPartialTarget(ADFUtils.findComponentInRoot("ps1imXML"));
    }


    public void setInputFileBinding(RichInputFile inputFileBinding) {
        this.inputFileBinding = inputFileBinding;
    }

    public RichInputFile getInputFileBinding() {
        return inputFileBinding;
    }

    public void parseXMLToPojo(InputStream inputStream) throws IOException,
                                                               JsonGenerationException,
                                                               JsonMappingException,
                                                               JAXBException,
                                                               SAXException {
        String importSource = null;
        Object obj = null;
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
        HashMap inputParamsMap =
            (HashMap)ADFUtils.getSessionScopeValue("inputParamsMap");
        if (inputParamsMap != null && !inputParamsMap.isEmpty()) {

            if (inputParamsMap.get("importSource") != null) {
                importSource = (String)inputParamsMap.get("importSource");
                inputParam.setImportSource((String)inputParamsMap.get("importSource"));
            }
            if (inputParamsMap.get("quoteNumber") != null) {
                inputParam.setQuoteNumber((String)inputParamsMap.get("quoteNumber"));
            }
            if (inputParamsMap.get("reuseQuote") != null) {
                inputParam.setReuseQuote((Boolean)inputParamsMap.get("reuseQuote"));
            }
            if (inputParamsMap.get("copyRefConf") != null) {
                inputParam.setCopyReferenceConfiguration((Boolean)inputParamsMap.get("copyRefConf"));
            }
            //Get values of rules set choices from session
            if (inputParamsMap.get("ruleSetTop") != null) {
                inputParam.setRuleSetTopLevelChoice((String)inputParamsMap.get("ruleSetTop"));
            }

            if (inputParamsMap.get("ruleSetSecond") != null) {
                inputParam.setRuleSetSecondLevelChoice((String)inputParamsMap.get("ruleSetSecond"));
            }
        }
        //Check what is the import source
        if (importSource != null &&
            importSource.equalsIgnoreCase("XML_FILE")) {
            System.out.println("Input Stream is " + inputStream);
            File xsdFile = readXsdResource();
            V93kQuote parent = null;
            parent = JaxbParser.jaxbXMLToObject(inputStream, xsdFile);
            parent.setSessionDetails(sessionDetails);
            parent.setInputParams(inputParam);
            parent.setUiSelection(uiSelection);
            //Check if xml file contains quote Numner
            String otherTemp = null;
            if (parent != null && parent.getQheaderObject() != null &&
                parent.getQheaderObject().getDealObject() != null) {
                String quoteNumFromXML =
                    parent.getQheaderObject().getDealObject().getQuoteid();
                String temp = null;
                if (quoteNumFromXML != null) {
                    temp = quoteNumFromXML.trim();
                }
                if (temp != null && !temp.equals("")) {
                    otherTemp = temp;

                }
            }
            ADFUtils.setSessionScopeValue("quoteNumber", otherTemp);
            String jsonStr = JSONUtils.convertObjToJson(parent);
            ObjectMapper mapper = new ObjectMapper();
            String responseJson =
                (String)ConfiguratorUtils.callConfiguratorServlet(jsonStr);
            obj = mapper.readValue(responseJson, V93kQuote.class);
        } else if (importSource != null) {
            V93kQuote v93k = new V93kQuote();
            v93k.setInputParams(inputParam);
            v93k.setSessionDetails(sessionDetails);
            v93k.setUiSelection(uiSelection);
            obj = v93k;
            String jsonStr = JSONUtils.convertObjToJson(obj);
            ObjectMapper mapper = new ObjectMapper();
            String responseJson =
                (String)ConfiguratorUtils.callConfiguratorServlet(jsonStr);
            obj = mapper.readValue(responseJson, V93kQuote.class);
        }
        ADFUtils.setSessionScopeValue("parentObject", obj);


    }

    public File readXsdResource() {
        File f = null;
        InputStream asStream =
            this.getClass().getClassLoader().getResourceAsStream(XSD_FILE);
        if (asStream == null) {
            // file not foun
            System.out.println("File Not found");
            _logger.info("File not found: '" + XSD_FILE + "'");
        } else {
            System.out.println("Stream found");

            try {
                f = stream2file(asStream, "V93", ".xsd");
                System.out.println("File Name " + f.getName());
                return f;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return f;
    }
    public static final String PREFIX = "stream2file";
    public static final String SUFFIX = ".tmp";

    public File stream2file(InputStream in, String PREFIX,
                            String SUFFIX) throws IOException {
        final File tempFile = File.createTempFile(PREFIX, SUFFIX);
        tempFile.deleteOnExit();
        try {
            FileOutputStream out = new FileOutputStream(tempFile);
            IOUtils.copy(in, out);
        } catch (Exception e) {
            ADFUtils.routeExceptions(e);
        }
        return tempFile;
    }


    public void cancelPopup(ActionEvent actionEvent) {
        RichPopup impSrcPopup =
            (RichPopup)ADFUtils.findComponentInRoot("imSrcP1");
        if (impSrcPopup != null) {
            //impSrcPopup.hide();
            impSrcPopup.cancel();
        }
    }

    public void openQuote(ActionEvent actionEvent) {
        RichPopup impSrcPopup =
            (RichPopup)ADFUtils.findComponentInRoot("imSrcP1");
        if (impSrcPopup != null) {
            //impSrcPopup.hide();
            impSrcPopup.cancel();
        }
        RichPanelGroupLayout pgl =
            (RichPanelGroupLayout)ADFUtils.findComponentInRoot("dashPGL");
        RequestContext.getCurrentInstance().addPartialTarget(pgl);
    }

    public void newFileUploaded(ValueChangeEvent valueChangeEvent) {

        UIComponent uiComp = (UIComponent)valueChangeEvent.getSource();
        uiComp.processUpdates(FacesContext.getCurrentInstance());
        System.out.println("Inside new File Uploaded");
        //dialogActionListener(null);
    }

    public void dialogReturnListener(ReturnEvent returnEvent) {
        RequestContext.getCurrentInstance().addPartialTarget(returnEvent.getComponent().getParent().getParent());

    }

    public String getRefreshToken() {
        return String.valueOf(System.currentTimeMillis());
    }

    public void valueChanged(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent != null &&
            valueChangeEvent.getOldValue() != valueChangeEvent.getNewValue()) {
            //Some Parameters have changed
            System.out.println("Value changed in popup");
            ADFUtils.setSessionScopeValue("ImpSrcChanged", "Y");
        }
    }

    public void setV93kQuote(V93kQuote v93kQuote) {
        this.v93kQuote = v93kQuote;
    }

    public V93kQuote getV93kQuote() {
        return v93kQuote;
    }
}
