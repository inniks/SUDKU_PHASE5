package xxatcust.oracle.apps.sudoku.bean;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sun.java.util.collections.Hashtable;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import java.net.MalformedURLException;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.SQLException;

import java.util.HashMap;

//import java.util.Hashtable;
import java.util.List;

import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.nav.RichCommandImageLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandToolbarButton;
import oracle.adf.view.rich.component.rich.output.RichOutputFormatted;

import oracle.adf.view.rich.context.AdfFacesContext;


import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.apps.fnd.ext.common.AppsRequestWrapper;
import oracle.apps.fnd.ext.common.AppsSessionHelper;
import oracle.apps.fnd.ext.common.EBiz;
import oracle.apps.fnd.ext.common.Session;

import oracle.apps.xdo.dataengine.DataProcessor;
import oracle.apps.xdo.template.FOProcessor;
import oracle.apps.xdo.template.RTFProcessor;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.JboException;
import oracle.jbo.Row;

import oracle.jbo.ViewObject;
import oracle.jbo.domain.BlobDomain;
import oracle.jbo.server.DBTransaction;

import xxatcust.oracle.apps.sudoku.model.module.SudokuAMImpl;
import xxatcust.oracle.apps.sudoku.util.ADFUtils;
import xxatcust.oracle.apps.sudoku.util.ConfiguratorUtils;
import xxatcust.oracle.apps.sudoku.util.DOMParser;
import xxatcust.oracle.apps.sudoku.util.JSONUtils;
import xxatcust.oracle.apps.sudoku.util.SudokuUtils;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.ConfiguratorNodePOJO;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.InputParams;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.QuoteLinePOJO;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.SessionDetails;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.V93kQuote;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.UiSelection;


public class LoadDynamicRegionBean {
    private static ADFLogger _logger =
        ADFLogger.createADFLogger(LoadDynamicRegionBean.class);
    private String taskFlowId =
        "/WEB-INF/xxatcust/oracle/apps/sudoku/pageFlows/ConfiguratorFlow.xml#ConfiguratorFlow";
    private String quoteTFId =
        "/WEB-INF/xxatcust/oracle/apps/sudoku/pageFlows/QuotingFlow.xml#QuotingFlow";
    private String quoteTFUpdateId =
        "/WEB-INF/xxatcust/oracle/apps/sudoku/pageFlows/QuoteUpdateFlow.xml#QuoteUpdateFlow";
    private String viewReferenceTFId =
        "/WEB-INF/xxatcust/oracle/apps/sudoku/pageFlows/UploadXMLFlow.xml#UploadXMLFlow";
    private String targetRefTF =
        "/WEB-INF/xxatcust/oracle/apps/sudoku/pageFlows/TargetConfigFlow.xml#TargetConfigFlow";
    private String currentTF = "configurator";
    private RichPopup expConfigPopup;
    private RichInputText fileNameBinding;
    private RichOutputFormatted exportException;
    V93kQuote v93kQuote;
    private RichOutputFormatted sysToOrclResultMsg;
    private RichPopup bindpopupDebug;
    private RichPopup cancelPop;
    private RichOutputFormatted successMsgOnSaveToOrcl;
    private RichOutputFormatted errorMsgOnSaveToOrcl;
    private RichPopup bindSaveToOrclPopup;
    private String errorFromPopup;
    private String infoFromPopup;
    private RichOutputFormatted setMOFop;

    public LoadDynamicRegionBean() {
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public TaskFlowId getDynamicTaskFlowId() {
        if (this.getCurrentTF().equalsIgnoreCase("configurator")) {
            return TaskFlowId.parse(taskFlowId);
        } else if (this.getCurrentTF().equalsIgnoreCase("viewRef")) {
            return TaskFlowId.parse(viewReferenceTFId);
        } else if (this.getCurrentTF().equalsIgnoreCase("quoteUpdate")) {
            return TaskFlowId.parse(quoteTFUpdateId);
        } else if (this.getCurrentTF().equalsIgnoreCase("targetRef")) {
            return TaskFlowId.parse(targetRefTF);
        } else {
            return TaskFlowId.parse(quoteTFId);
        }

    }

    public void setTaskFlowId(String taskFlowId) {
        this.taskFlowId = taskFlowId;
    }

    public String getTaskFlowId() {
        return taskFlowId;
    }

    public void setQuoteTFId(String quoteTFId) {
        this.quoteTFId = quoteTFId;
    }

    public String getQuoteTFId() {
        return quoteTFId;
    }

    public void setViewReferenceTFId(String viewReferenceTFId) {
        this.viewReferenceTFId = viewReferenceTFId;
    }

    public String getViewReferenceTFId() {
        return viewReferenceTFId;
    }

    public void setCurrentTF(String currentTF) {
        this.currentTF = currentTF;
    }

    public String getCurrentTF() {
        return currentTF;
    }

    public void viewReference(ActionEvent actionEvent) {
        // Add event code here...
    }

    public String getNavString() throws Exception {
        String importSource = null, quoteNumber = null, quoteNumFromSession =
            null;
        HashMap inputParamsMap =
            (HashMap)ADFUtils.getSessionScopeValue("inputParamsMap");
        if (inputParamsMap != null && !inputParamsMap.isEmpty()) {
            if (inputParamsMap.get("importSource") != null) {
                importSource = (String)inputParamsMap.get("importSource");
            }
            if (inputParamsMap.get("quoteNumber") != null) {
                quoteNumber = (String)inputParamsMap.get("quoteNumber");
            }
        }
        if (importSource != null &&
            (String)ADFContext.getCurrent().getSessionScope().get("quoteNumFromXML") !=
            null) {
            System.out.println("LOAD PAGE ::UPDATE QUOTE");
            return "quote";
        }
        quoteNumFromSession =
                (String)ADFUtils.getSessionScopeValue("quoteNumber");
        if (importSource != null &&
            (quoteNumFromSession != null || ADFUtils.getSessionScopeValue("targetQuoteNumber") !=
             null)) {
            System.out.println("LOAD PAGE ::UPDATE QUOTE");
            return "quoteUpdate";
        }
        if (importSource == null || quoteNumFromSession == null) {
            System.out.println("LOAD PAGE ::CREATE QUOTE");
            return "quote";
        } else
            return "configurator";

    }


    public void cancelPopup(ActionEvent actionEvent) {
        expConfigPopup.cancel();
    }

    public void setQuoteTFUpdateId(String quoteTFUpdateId) {
        this.quoteTFUpdateId = quoteTFUpdateId;
    }

    public String getQuoteTFUpdateId() {
        return quoteTFUpdateId;
    }

    public void export(ActionEvent actionEvent) {
        // For now create the xml file and display in console
        //        System.out.println("Export button pressed");
        //        V93kQuote parentObj =
        //            (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        //        if (parentObj == null) {
        //            exportException.setValue("No configuration exists.");
        //        } else {
        //            File createdXml = DOMParser.XMLWriterDOM(parentObj);
        //            ADFUtils.setPageFlowScopeValue("createdXML", createdXml);
        //        }
    }

    public void setExpConfigPopup(RichPopup expConfigPopup) {
        this.expConfigPopup = expConfigPopup;
    }

    public RichPopup getExpConfigPopup() {
        return expConfigPopup;
    }

    public void setFileNameBinding(RichInputText fileNameBinding) {
        this.fileNameBinding = fileNameBinding;
    }

    public RichInputText getFileNameBinding() {
        return fileNameBinding;
    }

    public void setExportException(RichOutputFormatted exportException) {
        this.exportException = exportException;
    }

    public RichOutputFormatted getExportException() {
        return exportException;
    }

    public void fileDownloadListener(FacesContext facesContext,
                                     OutputStream outputStream) {
        System.out.println("Download Listener Fired");
        Row row = null;
        V93kQuote parentObj =
            (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        String quoteNum =
            (String)ADFUtils.getSessionScopeValue("targetQuoteNumber");
        System.out.println("Target Quote Number " +
                           ADFUtils.getSessionScopeValue("targetQuoteNumber"));
        if (parentObj != null && parentObj.getQheaderObject() != null &&
            parentObj.getQheaderObject().getDealObject() != null &&
            parentObj.getQheaderObject().getDealObject().getQuoteid() !=
            null) {
            if (quoteNum == null) {
                quoteNum =
                        parentObj.getQheaderObject().getDealObject().getQuoteid();
            }
            System.out.println("QUOTE NUMBER :" + quoteNum);
            if (quoteNum != null) {
                quoteNum = quoteNum.trim();
            }
            OperationBinding op =
                ADFUtils.findOperation("getQuoteExportValues");
            op.getParamsMap().put("quoteNumber", quoteNum);
            if (op != null) {
                row = (Row)op.execute();
            }
        } else {
            quoteNum =
                    (String)ADFUtils.getSessionScopeValue("targetQuoteNumber");

            if (quoteNum == null) {
                quoteNum =
                        (String)ADFUtils.getSessionScopeValue("quoteNumber");

            }
            if (quoteNum != null) {
                quoteNum = quoteNum.trim();
                OperationBinding op =
                    ADFUtils.findOperation("getQuoteExportValues");
                System.out.println("QUOTE NUMBER : " + quoteNum);
                op.getParamsMap().put("quoteNumber", quoteNum);
                if (op != null) {
                    row = (Row)op.execute();
                }
            }
        }
        File createdXml = null;
        if (parentObj == null) {
            exportException.setValue("No configuration exists.");
        } else {
            createdXml = DOMParser.XMLWriterDOM(parentObj, row);
            ADFUtils.setPageFlowScopeValue("createdXML", createdXml);
        }
        try {

            FileInputStream fis;
            byte[] b;
            fis = new FileInputStream(createdXml);

            int n;
            while ((n = fis.available()) > 0) {
                b = new byte[n];
                int result = fis.read(b);
                outputStream.write(b, 0, b.length);
                if (result == -1)
                    break;
            }
            outputStream.flush();
        } catch (FileNotFoundException fnfe) {
            // TODO: Add catch code
            ADFUtils.routeExceptions(fnfe);

        } catch (IOException ioe) {
            // TODO: Add catch code
            ADFUtils.routeExceptions(ioe);
        } finally {
            expConfigPopup.hide();
        }
    }


    public String updateDiscount(String quoteNum, String discount, int respId,
                                 int usrId) {
        String returnMsg = null;
        if ((quoteNum != null && !quoteNum.equalsIgnoreCase("")) &&
            (discount != null && !discount.equalsIgnoreCase(""))) {

            OperationBinding ob =
                getBindings().getOperationBinding("callUpdateDiscountAPI");
            ob.getParamsMap().put("quoteNum", quoteNum);
            ob.getParamsMap().put("discount", discount);
            ob.getParamsMap().put("respId", respId);
            ob.getParamsMap().put("usrId", usrId);
            if (ob != null) {
                returnMsg = (String)ob.execute();
            }
        }
        return returnMsg;
    }


    public void saveQuoteFromSysToOrcl(ActionEvent actionEvent) throws MalformedURLException,
                                                                       IOException {
        //Before calling all the API's,Pass import source as "SAVE_CONFIG_TO_QUOTE"
        V93kQuote v93k =
            (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        if (v93k != null && v93k.getInputParams() != null) {
            v93k.getInputParams().setImportSource("SAVE_CONFIG_TO_QUOTE");
            String selectedCzNode = null;
            if(v93k.getUiSelection()!=null){
                selectedCzNode = v93k.getUiSelection().getCzNodeName();
                if(selectedCzNode!=null){
                    selectedCzNode = "\""+selectedCzNode+"\"";
                    v93k.getUiSelection().setCzNodeName(selectedCzNode);
                }
            }
             
            String jsonStr = JSONUtils.convertObjToJson(v93k);
            System.out.println("Json being sent "+jsonStr);
            String responseJson =
                ConfiguratorUtils.callConfiguratorServlet(jsonStr);
            System.out.println("Response Json from Configurator : " +
                               responseJson);
            ObjectMapper mapper = new ObjectMapper();
            //mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
            //mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            Object obj = mapper.readValue(responseJson, V93kQuote.class);
            v93k = (V93kQuote)obj;
            ADFUtils.setSessionScopeValue("parentObject", v93k);
        }


        String createQtMsg = getFndMessages(SudokuUtils.createQteMsg);
        String discount = null;
        int respid =
            Integer.parseInt((String)ADFUtils.getSessionScopeValue("RespId") ==
                             null ? "51156" :
                             (String)ADFUtils.getSessionScopeValue("RespId"));
        int usrId =
            Integer.parseInt((String)ADFUtils.getSessionScopeValue("UserId") ==
                             null ? "0" :
                             (String)ADFUtils.getSessionScopeValue("UserId"));

        //        FacesContext fc = FacesContext.getCurrentInstance();
        BindingContainer bindings = getBindings();
        StringBuilder resultMsg = new StringBuilder("<html><body>");
        StringBuilder resultErrMsg = new StringBuilder("<html><body>");
        if (v93k != null) {
            if (v93k.getQheaderObject() != null) {
                if (v93k.getQheaderObject().getDealObject() != null) {
                    discount =
                            (String)v93k.getQheaderObject().getDealObject().getDdiscount();
                }
            }
            if (v93k.getSessionDetails() != null) {
                if (v93k.getSessionDetails().isCreateNewQuote()) {
                    if (v93k.getSessionDetails().getTargetQuoteNumber() !=
                        null) {
                        for (QuoteLinePOJO list :
                             v93k.getTargetConfigurationLines()) {
                            if (list != null) {
                                String operationMode = list.getOperationCode();
                                if (operationMode != null &&
                                    operationMode.equalsIgnoreCase("CREATE")) {
                                    OperationBinding createOb =
                                        bindings.getOperationBinding("callConfigLineToAddQuoteAPI");
                                    _logger.info("Calling callConfigLineToAddQuoteAPI....QUOTE NUM " +
                                                 v93k.getSessionDetails().getTargetQuoteNumber());
                                    _logger.info("Calling callConfigLineToAddQuoteAPI....OPERATION CODE " +
                                                 list.getOperationCode());
                                    _logger.info("Calling callConfigLineToAddQuoteAPI....ConfigHdrId " +
                                                 list.getConfigHrdId());
                                    _logger.info("Calling callConfigLineToAddQuoteAPI....ConfigRevNum  " +
                                                 list.getConfigRevNum());
                                    _logger.info("Calling callConfigLineToAddQuoteAPI....ITEM NUM " +
                                                 list.getItemName());
                                    createOb.getParamsMap().put("quoteNum",
                                                                v93k.getSessionDetails().getTargetQuoteNumber());

                                    createOb.getParamsMap().put("itemNumber",
                                                                list.getItemName());
                                    createOb.getParamsMap().put("itemQty", 1);
                                    createOb.getParamsMap().put("orgNum",
                                                                list.getOperationCode() ==
                                                                null ? "GDO" :
                                                                list.getOperationCode());
                                    createOb.getParamsMap().put("ConfighdrId",
                                                                list.getConfigHrdId());
                                    createOb.getParamsMap().put("configRevNum",
                                                                list.getConfigRevNum());
                                    createOb.getParamsMap().put("respId",
                                                                respid);
                                    createOb.getParamsMap().put("usrId",
                                                                usrId);
                                    String createMsg =
                                        (String)createOb.execute();
                                    if (createMsg != null) {
                                        if (createMsg.contains("<html><body>")) {
                                            resultErrMsg.append("<p><b>" +
                                                                createMsg +
                                                                "</b></p>");
                                        } else if (createMsg.contains("S-")) {


                                            if (discount != null) {
                                                String discountMsg =
                                                    updateDiscount(v93k.getSessionDetails().getTargetQuoteNumber(),
                                                                   discount,
                                                                   respid,
                                                                   usrId);

                                                if (discountMsg != null) {
                                                    if (discountMsg.contains("<html><body>")) {
                                                        resultErrMsg.append("<p><b>" +
                                                                            discountMsg +
                                                                            "</b></p>");
                                                    } else if (discountMsg.contains("S-")) {
                                                        String[] resMsg =
                                                            discountMsg.split("-",
                                                                              2);
                                                        if (resMsg[1] !=
                                                            null) {
                                                            resultMsg.append("<p><b>" +
                                                                             resMsg[1] +
                                                                             "</b></p>");
                                                        }
                                                    } else if (discountMsg.contains("E-")) {
                                                        String[] resMsg =
                                                            discountMsg.split("-",
                                                                              2);
                                                        if (resMsg[1] !=
                                                            null) {
                                                            resultErrMsg.append("<p><b>" +
                                                                                resMsg[1] +
                                                                                "</b></p>");
                                                        }
                                                    } else
                                                        resultErrMsg.append(discountMsg.toString());
                                                }
                                            }


                                            String[] resMsg =
                                                createMsg.split("-", 2);
                                            if (resMsg[1] != null) {
                                                resultMsg.append("<p><b>" +
                                                                 resMsg[1] +
                                                                 "</b></p>");
                                            }
                                            
                                            //Save to oracle success , set param here
                                        } else if (createMsg.contains("E-")) {
                                            String[] resMsg =
                                                createMsg.split("-", 2);
                                            if (resMsg[1] != null) {
                                                resultErrMsg.append("<p><b>" +
                                                                    resMsg[1] +
                                                                    "</b></p>");
                                            }
                                        } else
                                            resultErrMsg.append(createMsg.toString());
                                    }
                                }
                            }
                        }
                    } else {
                        if (createQtMsg != null)
                            resultErrMsg.append("<p><b>" + createQtMsg +
                                                "</b></p>");
                        else
                            resultErrMsg.append("<p><b>" +
                                                SudokuUtils.createQteMsg +
                                                "</b></p>");

                        //                        resultErrMsg.append("<p><b>Please verify and create quote before saving</b></p>");
                        //                        resultMsg.append("\n");
                        System.out.println("Please create Quote before save");
                    }
                } else if (v93k.getSessionDetails().isDuplicateQuote()) {
                    if (v93k.getSessionDetails().getTargetQuoteNumber() !=
                        null) {
                        ADFContext.getCurrent().getSessionScope().put("targetQuoteNumber",
                                                                      v93k.getSessionDetails().getTargetQuoteNumber());
                        String targetQuote =
                            v93k.getSessionDetails().getTargetQuoteNumber().toString();
                        for (QuoteLinePOJO list :
                             v93k.getTargetConfigurationLines()) {
                            if (list != null) {
                                String operationMode = list.getOperationCode();
                                if (operationMode != null &&
                                    operationMode.equalsIgnoreCase("CREATE")) {
                                    OperationBinding dCreateOb =
                                        bindings.getOperationBinding("callConfigLineToAddQuoteAPI");
                                    _logger.info("Calling callConfigLineToAddQuoteAPI....QUOTE NUM " +
                                                 v93k.getSessionDetails().getTargetQuoteNumber());
                                    _logger.info("Calling callConfigLineToAddQuoteAPI....OPERATION CODE " +
                                                 list.getOperationCode());
                                    _logger.info("Calling callConfigLineToAddQuoteAPI....ConfigHdrId " +
                                                 list.getConfigHrdId());
                                    _logger.info("Calling callConfigLineToAddQuoteAPI....ConfigRevNum  " +
                                                 list.getConfigRevNum());
                                    _logger.info("Calling callConfigLineToAddQuoteAPI....ITEM NUM " +
                                                 list.getItemName());
                                    dCreateOb.getParamsMap().put("quoteNum",
                                                                 targetQuote);
                                    dCreateOb.getParamsMap().put("itemNumber",
                                                                 list.getItemName());
                                    //                        ob.getParamsMap().put("itemQty",list.getItems().size());
                                    dCreateOb.getParamsMap().put("itemQty", 1);
                                    //                                    dCreateOb.getParamsMap().put("OrgNum",
                                    //                                                                 list.getOperationCode());
                                    dCreateOb.getParamsMap().put("orgNum",
                                                                 list.getOperationCode() ==
                                                                 null ? "GDO" :
                                                                 list.getOperationCode());
                                    dCreateOb.getParamsMap().put("ConfighdrId",
                                                                 list.getConfigHrdId());
                                    dCreateOb.getParamsMap().put("configRevNum",
                                                                 list.getConfigRevNum());
                                    dCreateOb.getParamsMap().put("respId",
                                                                 respid);
                                    dCreateOb.getParamsMap().put("usrId",
                                                                 usrId);
                                    String createMsg =
                                        (String)dCreateOb.execute();
                                    if (createMsg != null) {
                                        if (createMsg.contains("<html><body>")) {
                                            resultErrMsg.append("<p><b>" +
                                                                createMsg +
                                                                "</b></p>");
                                        } else if (createMsg.contains("S-")) {


                                            if (discount != null) {
                                                String discountMsg =
                                                    updateDiscount(targetQuote,
                                                                   discount,
                                                                   respid,
                                                                   usrId);

                                                if (discountMsg != null) {
                                                    if (discountMsg.contains("<html><body>")) {
                                                        resultErrMsg.append("<p><b>" +
                                                                            discountMsg +
                                                                            "</b></p>");
                                                    } else if (discountMsg.contains("S-")) {
                                                        String[] resMsg =
                                                            discountMsg.split("-",
                                                                              2);
                                                        if (resMsg[1] !=
                                                            null) {
                                                            resultMsg.append("<p><b>" +
                                                                             resMsg[1] +
                                                                             "</b></p>");
                                                        }
                                                    } else if (discountMsg.contains("E-")) {
                                                        String[] resMsg =
                                                            discountMsg.split("-",
                                                                              2);
                                                        if (resMsg[1] !=
                                                            null) {
                                                            resultErrMsg.append("<p><b>" +
                                                                                resMsg[1] +
                                                                                "</b></p>");
                                                        }
                                                    } else
                                                        resultErrMsg.append(discountMsg.toString());
                                                }
                                            }


                                            String[] resMsg =
                                                createMsg.split("-", 2);
                                            if (resMsg[1] != null) {
                                                resultMsg.append("<p><b>" +
                                                                 resMsg[1] +
                                                                 "</b></p>");
                                            }
                                            //save too oracle success
                                        } else if (createMsg.contains("E-")) {
                                            String[] resMsg =
                                                createMsg.split("-", 2);
                                            if (resMsg[1] != null) {
                                                resultErrMsg.append("<p><b>" +
                                                                    resMsg[1] +
                                                                    "</b></p>");
                                            }
                                        } else
                                            resultErrMsg.append(createMsg.toString());
                                    }

                                }
                            }
                        }
                    } else {
                        if (createQtMsg != null)
                            resultErrMsg.append("<p><b>" + createQtMsg +
                                                "</b></p>");
                        else
                            resultErrMsg.append("<p><b>" +
                                                SudokuUtils.createQteMsg +
                                                "</b></p>");
                        //                        resultErrMsg.append("<p><b>Please verify and create quote before saving</b></p>");
                        //                        resultMsg.append("\n");
                        System.out.println("Please craete Quote before Save");
                    }
                } else if (v93k.getSessionDetails().isUpdateQuote()) {
                    if (v93k.getSessionDetails().getSourceQuoteNumber() !=
                        null) {
                        for (QuoteLinePOJO list :
                             v93k.getTargetConfigurationLines()) {
                            if (list != null) {
                                String operationMode = list.getOperationCode();
                                if (operationMode != null &&
                                    operationMode.equalsIgnoreCase("UPDATE")) {
                                    OperationBinding ob =
                                        bindings.getOperationBinding("callConfigLineToUpdateQuoteAPI");
                                    _logger.info("Calling callConfigLineToUpdateQuoteAPI....QUOTE NUM " +
                                                 v93k.getSessionDetails().getSourceQuoteNumber());
                                    _logger.info("Calling callConfigLineToUpdateQuoteAPI....OPERATION CODE " +
                                                 list.getOperationCode());
                                    _logger.info("Calling callConfigLineToUpdateQuoteAPI....ConfigHdrId " +
                                                 list.getConfigHrdId());
                                    _logger.info("Calling callConfigLineToUpdateQuoteAPI....ConfigRevNum  " +
                                                 list.getConfigRevNum());
                                    _logger.info("Calling callConfigLineToUpdateQuoteAPI....ITEM NUM " +
                                                 list.getItemName());
                                    _logger.info("Calling callConfigLineToUpdateQuoteAPI....QUOTE LINE NUM " +
                                                 list.getQuoteLineId());
                                    ob.getParamsMap().put("quoteNum",
                                                          v93k.getSessionDetails().getSourceQuoteNumber());
                                    ob.getParamsMap().put("quoteLineNum",
                                                          list.getQuoteLineId());
                                    ob.getParamsMap().put("itemQty", 1);
                                    ob.getParamsMap().put("ConfighdrId",
                                                          list.getConfigHrdId());
                                    ob.getParamsMap().put("configRevNum",
                                                          list.getConfigRevNum());
                                    ob.getParamsMap().put("respId", respid);
                                    ob.getParamsMap().put("usrId", usrId);
                                    String updateMsg = (String)ob.execute();
                                    if (updateMsg != null) {
                                        if (updateMsg.contains("<html><body>")) {
                                            resultErrMsg.append("<p><b>" +
                                                                updateMsg +
                                                                "</b></p>");
                                        } else if (updateMsg.contains("S-")) {

                                            if (discount != null) {
                                                String discountMsg =
                                                    updateDiscount(v93k.getSessionDetails().getSourceQuoteNumber(),
                                                                   discount,
                                                                   respid,
                                                                   usrId);

                                                if (discountMsg != null) {
                                                    if (discountMsg.contains("<html><body>")) {
                                                        resultErrMsg.append("<p><b>" +
                                                                            discountMsg +
                                                                            "</b></p>");
                                                    } else if (discountMsg.contains("S-")) {
                                                        String[] resMsg =
                                                            discountMsg.split("-",
                                                                              2);
                                                        if (resMsg[1] !=
                                                            null) {
                                                            resultMsg.append("<p><b>" +
                                                                             resMsg[1] +
                                                                             "</b></p>");
                                                        }
                                                    } else if (discountMsg.contains("E-")) {
                                                        String[] resMsg =
                                                            discountMsg.split("-",
                                                                              2);
                                                        if (resMsg[1] !=
                                                            null) {
                                                            resultErrMsg.append("<p><b>" +
                                                                                resMsg[1] +
                                                                                "</b></p>");
                                                        }
                                                    } else
                                                        resultErrMsg.append(discountMsg.toString());
                                                }
                                            }


                                            String[] resMsg =
                                                updateMsg.split("-", 2);
                                            if (resMsg[1] != null) {
                                                resultMsg.append("<p><b>" +
                                                                 resMsg[1] +
                                                                 "</b></p>");
                                            }
                                            //save to oracle success
                                        } else if (updateMsg.contains("E-")) {
                                            String[] resMsg =
                                                updateMsg.split("-", 2);
                                            if (resMsg[1] != null) {
                                                resultErrMsg.append("<p><b>" +
                                                                    resMsg[1] +
                                                                    "</b></p>");
                                            }
                                        } else
                                            resultErrMsg.append(updateMsg.toString());
                                    }
                                } else if (operationMode != null &&
                                           operationMode.equalsIgnoreCase("CREATE")) {
                                    OperationBinding ob =
                                        bindings.getOperationBinding("callConfigLineToAddQuoteAPI");
                                    ob.getParamsMap().put("quoteNum",
                                                          v93k.getSessionDetails().getSourceQuoteNumber().toString());
                                    ob.getParamsMap().put("itemNumber",
                                                          list.getItemName());
                                    ob.getParamsMap().put("itemQty", 1);
                                    ob.getParamsMap().put("orgNum",
                                                          list.getOperationCode() ==
                                                          null ? "GDO" :
                                                          list.getOperationCode());
                                    ob.getParamsMap().put("ConfighdrId",
                                                          list.getConfigHrdId());
                                    ob.getParamsMap().put("configRevNum",
                                                          list.getConfigRevNum());
                                    ob.getParamsMap().put("respId", respid);
                                    ob.getParamsMap().put("usrId", usrId);
                                    String createMsg = (String)ob.execute();
                                    if (createMsg != null) {
                                        if (createMsg.contains("<html><body>")) {
                                            resultErrMsg.append("<p><b>" +
                                                                createMsg +
                                                                "</b></p>");
                                        } else if (createMsg.contains("S-")) {


                                            if (discount != null) {
                                                String discountMsg =
                                                    updateDiscount(v93k.getSessionDetails().getSourceQuoteNumber(),
                                                                   discount,
                                                                   respid,
                                                                   usrId);

                                                if (discountMsg != null) {
                                                    if (discountMsg.contains("<html><body>")) {
                                                        resultErrMsg.append("<p><b>" +
                                                                            discountMsg +
                                                                            "</b></p>");
                                                    } else if (discountMsg.contains("S-")) {
                                                        String[] resMsg =
                                                            discountMsg.split("-",
                                                                              2);
                                                        if (resMsg[1] !=
                                                            null) {
                                                            resultMsg.append("<p><b>" +
                                                                             resMsg[1] +
                                                                             "</b></p>");
                                                        }
                                                    } else if (discountMsg.contains("E-")) {
                                                        String[] resMsg =
                                                            discountMsg.split("-",
                                                                              2);
                                                        if (resMsg[1] !=
                                                            null) {
                                                            resultErrMsg.append("<p><b>" +
                                                                                resMsg[1] +
                                                                                "</b></p>");
                                                        }
                                                    } else
                                                        resultErrMsg.append(discountMsg.toString());
                                                }
                                            }


                                            String[] resMsg =
                                                createMsg.split("-", 2);
                                            if (resMsg[1] != null) {
                                                resultMsg.append("<p><b>" +
                                                                 resMsg[1] +
                                                                 "</b></p>");
                                            }
                                        } else if (createMsg.contains("E-")) {
                                            String[] resMsg =
                                                createMsg.split("-", 2);
                                            if (resMsg[1] != null) {
                                                resultErrMsg.append("<p><b>" +
                                                                    resMsg[1] +
                                                                    "</b></p>");
                                            }
                                        } else
                                            resultErrMsg.append(createMsg.toString());
                                    }
                                } else {
                                }
                            }

                        }
                    } else {
                        if (createQtMsg != null)
                            resultErrMsg.append("<p><b>" + createQtMsg +
                                                "</b></p>");
                        else
                            resultErrMsg.append("<p><b>" +
                                                SudokuUtils.createQteMsg +
                                                "</b></p>");
                        //                        resultErrMsg.append("<p><b>Please verify and create quote before saving</b></p>");
                        //                        resultMsg.append("\n");
                        System.out.println("Please create Quote before Save");
                    }
                } else {
                    if (createQtMsg != null)
                        resultErrMsg.append("<p><b>" + createQtMsg +
                                            "</b></p>");
                    else
                        resultErrMsg.append("<p><b>" +
                                            SudokuUtils.createQteMsg +
                                            "</b></p>");
                    //                    resultErrMsg.append("<p><b>Please verify and create quote before saving</b></p>");
                    //                    resultMsg.append("\n");
                }


                if (v93k.getSessionDetails().getSourceQuoteNumber() != null ||
                    v93k.getSessionDetails().getTargetQuoteNumber() != null) {
                    OperationBinding warrantyOb =
                        getBindings().getOperationBinding("callWarrentyAPI");
                    String quoteForWarranty = null;
                    if (v93k.getSessionDetails().isDuplicateQuote() ||
                        v93k.getSessionDetails().isCreateNewQuote()) {
                        quoteForWarranty =
                                v93k.getSessionDetails().getTargetQuoteNumber();
                    } else if (quoteForWarranty == null &&
                               v93k.getSessionDetails().isUpdateQuote()) {
                        warrantyOb.getParamsMap().put("quoteNum",
                                                      v93k.getSessionDetails().getSourceQuoteNumber());
                        quoteForWarranty =
                                v93k.getSessionDetails().getSourceQuoteNumber();
                    }
                    if (v93k.getTargetConfigurationLines() != null &&
                        !v93k.getTargetConfigurationLines().isEmpty()) {
                        for (QuoteLinePOJO list :
                             v93k.getTargetConfigurationLines()) {
                            List<ConfiguratorNodePOJO> warrantyList =
                                list.getWarrantyItems();
                            if (warrantyList != null &&
                                !warrantyList.isEmpty()) {
                                for (ConfiguratorNodePOJO node :
                                     warrantyList) {
                                    //call the warranty api
                                    String item = node.getNodeName();
                                    warrantyOb.getParamsMap().put("quoteNum",
                                                                  quoteForWarranty);
                                    warrantyOb.getParamsMap().put("prodName",
                                                                  item);
                                    warrantyOb.getParamsMap().put("respId",
                                                                  respid);
                                    warrantyOb.getParamsMap().put("usrId",
                                                                  usrId);
                                    String retMsg = null;
                                    if (warrantyOb != null) {
                                        retMsg = (String)warrantyOb.execute();
                                        if (retMsg != null) {
                                            if (retMsg.contains("<html><body>")) {
                                                resultErrMsg.append(retMsg.toString());
                                            } else if (retMsg.contains("S-")) {
                                                String[] resMsg =
                                                    retMsg.split("-", 2);
                                                retMsg = resMsg[1];
                                                resultMsg.append(retMsg);
                                            } else if (retMsg.contains("E-")) {
                                                String[] resMsg =
                                                    retMsg.split("-", 2);
                                                retMsg = resMsg[1];
                                                resultErrMsg.append(retMsg);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (createQtMsg != null)
                    resultErrMsg.append("<p><b>" + createQtMsg + "</b></p>");
                else
                    resultErrMsg.append("<p><b>" + SudokuUtils.createQteMsg +
                                        "</b></p>");
                //                resultErrMsg.append("<p><b>Please verify and create quote before saving</b></p>");
                //                resultMsg.append("\n");
            }
        } else {
            if (createQtMsg != null)
                resultErrMsg.append("<p><b>" + createQtMsg + "</b></p>");
            else
                resultErrMsg.append("<p><b>" + SudokuUtils.createQteMsg +
                                    "</b></p>");
            //            resultErrMsg.append("<p><b>Please verify and create quote before saving</b></p>");
            //            resultMsg.append("\n");
        }
        String msg = resultMsg.append("</body></html>").toString();
        String errMsg = resultErrMsg.append("</body></html>").toString();
        if (msg != null || errMsg != null) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            if (msg != null &&
                !"<html><body></body></html>".equalsIgnoreCase(msg)) {
                this.setInfoFromPopup(msg);
            } else
                this.setInfoFromPopup(null);
            if (errMsg != null &&
                !"<html><body></body></html>".equalsIgnoreCase(errMsg))
                this.setErrorFromPopup(errMsg);
            else
                this.setErrorFromPopup(null);

            successMsgOnSaveToOrcl.setValue(msg);
            errorMsgOnSaveToOrcl.setValue(errMsg);
            this.getBindSaveToOrclPopup().show(hints);
            //                bindSaveToOrclPopup.show(hints);
            //                bindPopup1.show(hints);
        }
    }


    public String getFndMessages(String msgName) {
        String msgTxt = null;
        OperationBinding op = getBindings().getOperationBinding("getFNDMsges");
        op.getParamsMap().put("msgName", msgName);
        if (op != null)
            msgTxt = (String)op.execute();
        return msgTxt;
    }


    public void setV93kQuote(V93kQuote v93kQuote) {
        this.v93kQuote = v93kQuote;
    }

    public V93kQuote getV93kQuote() {
        return v93kQuote;
    }

    public void setSysToOrclResultMsg(RichOutputFormatted sysToOrclResultMsg) {
        this.sysToOrclResultMsg = sysToOrclResultMsg;
    }

    public RichOutputFormatted getSysToOrclResultMsg() {
        return sysToOrclResultMsg;
    }

    public void setBindpopupDebug(RichPopup bindpopupDebug) {
        this.bindpopupDebug = bindpopupDebug;
    }

    public RichPopup getBindpopupDebug() {
        return bindpopupDebug;
    }

    public void navToConfigurator(ActionEvent actionEvent) {
        ADFUtils.setSessionScopeValue("currView", "config");
    }

    public void navToViewRef(ActionEvent actionEvent) {
        ADFUtils.setSessionScopeValue("currView", "viewRef");
    }

    public void navToTargetConfig(ActionEvent actionEvent) {
        ADFUtils.setSessionScopeValue("currView", "targetRef");
    }

    public void navToQuote(ActionEvent actionEvent) {
        ADFUtils.setSessionScopeValue("currView", "quote");
    }

    public void cancelAllConfigurations(ActionEvent actionEvent) throws IOException,
                                                                        JsonGenerationException,
                                                                        JsonMappingException {
        
        //Call the CIO servlet with unique identifier , before setting all session values to null
        V93kQuote v93k = (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        if(v93k!=null){
            InputParams inputParams = v93k.getInputParams();
            if(inputParams==null){
                inputParams = new InputParams();
            }
            inputParams.setImportSource("CANCEL_CONFIG");
            v93k.setInputParams(inputParams);
            String jsonStr = JSONUtils.convertObjToJson(v93k);
            ObjectMapper mapper = new ObjectMapper();
            String responseJson =
                ConfiguratorUtils.callConfiguratorServlet(jsonStr);
            System.out.println("Response Json from Configurator : " +
                               responseJson);
            Object obj = mapper.readValue(responseJson, V93kQuote.class);
            v93k = (V93kQuote)obj;
            ADFUtils.setSessionScopeValue("parentObject", v93k);
        }
        
       
        ADFUtils.setSessionScopeValue("cancelAll", "Y");
       
        ADFUtils.addPartialTarget(ADFUtils.findComponentInRoot("config_Phd"));
        //This is to cancel all configs and reset all session variables
        ADFUtils.setSessionScopeValue("parentObject", null);
        ADFUtils.setSessionScopeValue("refreshImport", null);
        ADFUtils.setSessionScopeValue("isDuplicateQuote", null);
        ADFUtils.setSessionScopeValue("isUpdateQuote", null);
        ADFUtils.setSessionScopeValue("isCreateQuote", null);
        ADFUtils.setSessionScopeValue("targetQuoteNumber", null);
        ADFUtils.setSessionScopeValue("isDuplicateQuote", null);
        ADFUtils.setSessionScopeValue("isDuplicateQuote", null);
        ADFUtils.setSessionScopeValue("quoteNumber", null);
        ADFUtils.setSessionScopeValue("ImpSrcChanged", null);
        ADFUtils.setSessionScopeValue("currView", null);
        ADFUtils.setSessionScopeValue("inputParamsMap", null);
        ADFUtils.setSessionScopeValue("ruleSetMap", null);
        ADFUtils.setSessionScopeValue("qheaderValidMap", null);
//        
//        
//        //Set one session scope for cancelled
        ADFUtils.setSessionScopeValue("cancelAll", "Y");
        ADFUtils.setSessionScopeValue("uniqueSessionId", null);
        ADFUtils.setSessionScopeValue("selectedNodeValueMap", null);
        ADFUtils.setSessionScopeValue("inputNodeValueMap", null);
        ADFUtils.setSessionScopeValue("inputLOVMap", null);
        //ADFUtils.setSessionScopeValue("currView", "config");
        RichCommandImageLink button =
            (RichCommandImageLink)ADFUtils.findComponentInRoot("ctb1_vre"); // Navigate to view reference page
        ActionEvent acEvent = new ActionEvent(button);
        acEvent.queue();
        cancelPop.cancel();
    }

    public String getRefreshToken() {
        return String.valueOf(System.currentTimeMillis());
    }

    public void closeCancelPopup(ActionEvent actionEvent) {
        cancelPop.cancel();
    }

    public void setCancelPop(RichPopup cancelPop) {
        this.cancelPop = cancelPop;
    }

    public RichPopup getCancelPop() {
        return cancelPop;
    }

    public void launchCancelPopup(ActionEvent actionEvent) {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        cancelPop.show(hints);
    }

    public String getDisableCurrentButton() {
        String disableString = null;
        String currView = (String)ADFUtils.getSessionScopeValue("currView");
        if (currView != null) {
            if (currView.equalsIgnoreCase("quote"))
                disableString = "quote";
            if (currView.equalsIgnoreCase("viewRef"))
                disableString = "ref";
            if (currView.equalsIgnoreCase("targetRef"))
                disableString = "target";
            if (currView.equalsIgnoreCase("config"))
                disableString = "config";
        }
        return disableString;
    }

    public void exportTargetConfig(ActionEvent actionEvent) {
        //Check if target lines exist , If yes open the popup or show error
        String noTargetConfig =
            getFndMessages((String)SudokuUtils.noTargetConfigMsg);
        V93kQuote v93 =
            (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        if (v93 != null && v93.getTargetConfigurationLines() != null &&
            !v93.getTargetConfigurationLines().isEmpty()) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            expConfigPopup.show(hints);
        } else {
            if (noTargetConfig != null)
                ADFUtils.showFacesMessage(noTargetConfig,
                                          FacesMessage.SEVERITY_WARN);
            else
                ADFUtils.showFacesMessage(SudokuUtils.noTargetConfigMsg,
                                          FacesMessage.SEVERITY_WARN);
            //            ADFUtils.showFacesMessage("No target configuration is found in the session, please create target configuration.",
            //                                      FacesMessage.SEVERITY_WARN);
        }
    }

    public void setSuccessMsgOnSaveToOrcl(RichOutputFormatted successMsgOnSaveToOrcl) {
        this.successMsgOnSaveToOrcl = successMsgOnSaveToOrcl;
    }

    public RichOutputFormatted getSuccessMsgOnSaveToOrcl() {
        return successMsgOnSaveToOrcl;
    }

    public void setErrorMsgOnSaveToOrcl(RichOutputFormatted errorMsgOnSaveToOrcl) {
        this.errorMsgOnSaveToOrcl = errorMsgOnSaveToOrcl;
    }

    public RichOutputFormatted getErrorMsgOnSaveToOrcl() {
        return errorMsgOnSaveToOrcl;
    }

    public void setBindSaveToOrclPopup(RichPopup bindSaveToOrclPopup) {
        this.bindSaveToOrclPopup = bindSaveToOrclPopup;
    }

    public RichPopup getBindSaveToOrclPopup() {
        return bindSaveToOrclPopup;
    }

    public void setErrorFromPopup(String errorFromPopup) {
        this.errorFromPopup = errorFromPopup;
    }

    public String getErrorFromPopup() {
        return errorFromPopup;
    }

    public void setInfoFromPopup(String infoFromPopup) {
        this.infoFromPopup = infoFromPopup;
    }

    public String getInfoFromPopup() {
        return infoFromPopup;
    }

    public void logoutEBS(ActionEvent actionEvent) {
        _logger.info("Logging out the user from EBS");
        //  ADFUtils.setSessionScopeValue("parentObject", null);
        FacesContext fctx = FacesContext.getCurrentInstance();
        HttpServletRequest request =
            (HttpServletRequest)fctx.getExternalContext().getRequest();
        HttpServletResponse response =
            (HttpServletResponse)fctx.getExternalContext().getResponse();
        //invalidate ICX session & HTTP session
        AppsRequestWrapper wrappedRequest = null;
        String logoutEbsUrl = null;
        try {
            //    ApplicationModule am = getAppModule();

            //  _logger.info("am==>" + am);
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


    public void homeEBS(ActionEvent actionEvent) {
        _logger.info("home out the user from EBS");
        //  ADFUtils.setSessionScopeValue("parentObject", null);
        FacesContext fctx = FacesContext.getCurrentInstance();
        HttpServletRequest request =
            (HttpServletRequest)fctx.getExternalContext().getRequest();
        HttpServletResponse response =
            (HttpServletResponse)fctx.getExternalContext().getResponse();
        //invalidate ICX session & HTTP session
        AppsRequestWrapper wrappedRequest = null;
        String homeUrl = null;
        try {
            ApplicationModule am = getAppModule();

            BindingContext bctx = BindingContext.getCurrent();
            DCDataControl dc = bctx.findDataControl("SudokuAMDataControl");
            dc.rollbackTransaction();

            //  _logger.info("am==>" + am);
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

            homeUrl = wrappedRequest.getEbizInstance().getAppsServletAgent();
            homeUrl =
                    homeUrl + "OA.jsp?page=/oracle/apps/fnd/framework/navigate/webui/HomePG&homePage=Y";

            //            OA.jsp?OAFunc=OAHOMEPAGE
            //
            //            _home_url = currentUrlName + "OA.jsp?OAFunc=OAHOMEPAGE#";
            //            _logout_url = currentUrlName + "OALogout.jsp?menu=Y";

            _logger.info("homeUrl = " + homeUrl);
            Session sessionEBS = wrappedRequest.getAppsSession();

            //logout only if it is present
            /*if (sessionEBS != null) {
                AppsSessionHelper helper =
                    new AppsSessionHelper(wrappedRequest.getEbizInstance());
                helper.destroyAppsSession(wrappedRequest.getAppsSession(),
                                          wrappedRequest, response);


            }*/
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
            _logger.info("before redirect homeurl " + homeUrl);
            response.sendRedirect(homeUrl);
            _logger.info("after redirect homeurl " + homeUrl);
            fctx.responseComplete();
            _logger.info("after response complete " + homeUrl);
        } catch (Exception ex) {
            _logger.severe("Error , ", ex);
            throw (new JboException(ex));
        }
    }

    public static ApplicationModule getAppModule() {
        BindingContext bctx = BindingContext.getCurrent();
        DCDataControl dc = bctx.findDataControl("SudokuAMDataControl");
        ApplicationModule am = (ApplicationModule)dc.getDataProvider();

        return am;
    }

    public SudokuAMImpl getSudokuAMImpl() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExp =
            elFactory.createValueExpression(elContext, "#{data.SudokuAMDataControl.dataProvider}",
                                            Object.class);

        return (SudokuAMImpl)valueExp.getValue(elContext);
    }

    public void processPDFOutput(FacesContext facesContext,
                                 OutputStream outputStream) {
        try {
            _logger.info("print report call start ");
            
            String quoteNum =  (String)ADFUtils.getSessionScopeValue("targetQuoteNumber");
            Object quotehid = null;
            String orgId=null;
            OperationBinding ob1 =
                getBindings().getOperationBinding("getQuoteHdrOrgID");
            ob1.getParamsMap().put("pquoteNo", quoteNum);
            if (ob1 != null) {
    
             //   quotehid = ob1.execute();
                
                Map quoteDetailsMap = (Map)ob1.execute();
                
                quotehid=quoteDetailsMap.get("vQuoteHid");
                _logger.info("print quotehid"+quotehid);
                orgId=(String)quoteDetailsMap.get("vOrgId");
                _logger.info("print orgId"+orgId);
            }
    
            String squotehid = String.valueOf(quotehid);
            System.out.println("print quotehid" + squotehid);
            
            int respid =
                Integer.parseInt((String)ADFUtils.getSessionScopeValue("RespId") ==
                                 null ? "51156" :
                                 (String)ADFUtils.getSessionScopeValue("RespId"));
    
            int usrId =
                Integer.parseInt((String)ADFUtils.getSessionScopeValue("UserId") ==
                                 null ? "0" :
                                 (String)ADFUtils.getSessionScopeValue("UserId"));
           
            
            
            String srespid = String.valueOf(respid);
            String susrId = String.valueOf(usrId);
           
    
            initializeAppsContext(susrId, srespid, "880",orgId);
            _logger.info("print after apps intilization in bean ");
            String template =
                "XXATREP_PMF"; //"XXATRPT_PMF_V4_Y_mux_FRAMES_PDF";
            String pconfighid = null;
            String pconfigrevnum = null;
            String porderhid = null;
            //    String quotehid = "97416";
            String ponumber = null;
    
            SudokuAMImpl am = this.getSudokuAMImpl();
            Connection conn =
                am.getDBTransaction().createCallableStatement("select 1 from dual",
                                                              1).getConnection();
            _logger.info("print connection " + conn);
            DataProcessor dataProcessor = new DataProcessor();
    
         //   String kpath = "C:\\Users\\vthommandru\\Downloads\\";
    
          //  dataProcessor.setDataTemplate("" + spath +
                                 //         "PMF_REPORT_pmux_frames_PDF.xml"); //local
            _logger.info("print dataProcessor " + dataProcessor);
            Object path = null;
            OperationBinding ob = getBindings().getOperationBinding("getPath");
            if (ob != null) {
                path = ob.execute();
            }
    
            String spath = String.valueOf(path);
            System.out.println("print serverpath" + spath);
           
    
    
            dataProcessor.setDataTemplate("" + spath +
                                          "PMF_REPORT_pmux_frames_PDF.xml"); //server
    
            _logger.info("print dataProcessor location " + dataProcessor);
    
    
            //  dataProcessor.setDataTemplate("/public_html/xmlreports/PMF_REPORT_pmux_frames_PDF.xml");
    
            // dataProcessor.setDataTemplate("/tmp/PMF_REPORT_pmux_frames_PDF.xml");  //server
    
            dataProcessor.setConnection(conn);
            _logger.info("print dataProcessor set connection ");
            Hashtable parameters = new Hashtable();
            if (pconfighid != null) {
                parameters.put("P_CONFIG_HEADER_ID", pconfighid);
            }
            if (pconfigrevnum != null) {
                parameters.put("P_CONFIG_REV_NBR", pconfigrevnum);
            }
            if (porderhid != null) {
                parameters.put("P_ORDER_HEADER_ID", porderhid);
            }
            if (quotehid != null) {
                parameters.put("P_QUOTE_HEADER_ID", squotehid);
            }
    
            if (ponumber != null) {
                parameters.put("P_PO_NUMBER", ponumber);
            }
            _logger.info("print quotehid " + quotehid);
            dataProcessor.setParameters(parameters);
            _logger.info("data processor set parameters");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            _logger.info("ByteArrayOutputStream" + out);
            dataProcessor.setOutput(out);
            _logger.info("dataProcessor setoutput");
            dataProcessor.processData();
            _logger.info("dataProcessor.processData");
            byte[] data = out.toByteArray();
            _logger.info("byte " + data);
            ByteArrayInputStream istream = new ByteArrayInputStream(data);
            System.out.println("Done data template");
    
            _logger.info("Done data template");
    
            //  RTFProcessor rtf = new RTFProcessor("C:\\Users\\vthommandru\\Downloads\\XXATRPT_PMF_V4_Y_mux_FRAMES_PDF.rtf");  // local
    
    
            RTFProcessor rtf =
                new RTFProcessor("" + spath + "XXATRPT_PMF_V4_Y_mux_FRAMES_PDF.rtf"); // server
    
            _logger.info("print rtf directory " + rtf);
            //   RTFProcessor rtf = new RTFProcessor("/public_html/xmlreports/XXATRPT_PMF_V4_Y_mux_FRAMES_PDF.rtf");
            //  RTFProcessor rtf = new RTFProcessor("/tmp/XXATRPT_PMF_V4_Y_mux_FRAMES_PDF.rtf");  // xml
            ///    /u01/app/apnac03r12/PRajkumar
            ByteArrayOutputStream outXslFo = new ByteArrayOutputStream();
            _logger.info("outXslFo" + outXslFo);
            rtf.setOutput(outXslFo);
            _logger.info("outXslFo set output");
            rtf.process();
            _logger.info("set rtf process");
            byte[] dataXslFo = outXslFo.toByteArray();
            _logger.info("print" + dataXslFo);
            ByteArrayInputStream inXslFo = new ByteArrayInputStream(dataXslFo);
            _logger.info("print inXslFo" + inXslFo);
            FOProcessor processor = new FOProcessor();
            _logger.info("print processor" + processor);
            processor.setData(istream);
            _logger.info("processor.setData");
            processor.setTemplate(inXslFo);
            _logger.info("processor.setTemplate");
            processor.setOutput(outputStream);
            _logger.info("processor.setOutput");
            processor.setOutputFormat(FOProcessor.FORMAT_PDF);
            _logger.info("processorsetOutputFormat PDF" +
                         FOProcessor.FORMAT_PDF);
            processor.generate();
            _logger.info("Done power point");
            System.out.println("Done power point");
            outputStream.flush();
            _logger.info("Done flush");
    
    
        } catch (Exception e) {
            System.out.println("error::: " + e.getMessage());
            _logger.info("error::: " + e.getMessage());
        }
    }
    
    private void initializeAppsContext(String respId, String userId,
                                       String applicationId,String orgId) {
    
        SudokuAMImpl am = this.getSudokuAMImpl();
        DBTransaction txn = (DBTransaction)am.getTransaction();
        CallableStatement st = null;
        try {
    
            st =
    txn.createCallableStatement("BEGIN fnd_global.apps_initialize(:1, :2, :3); mo_global.set_policy_context ('S', :4); END;",
                             0);
            st.setString(1, userId);
            st.setString(2, respId);
            st.setString(3, applicationId);
            st.setString(4, orgId );
            _logger.info("print  before apps intialize execute ");
            st.execute();
            _logger.info("print  after apps intialize execute successfully ");
            //            st.close();
    
        } catch (Exception e) {
            _logger.info("Error in apps intialization and orgcontext" +
                         e.getMessage());
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException sqle) {
                    // TODO: Add catch code
                    _logger.info("Error in apps intialization and orgcontext1" +
                                 sqle.getMessage());
                    sqle.printStackTrace();
    
                }
            }
        }
    }
    
    public void processExcelOutput(FacesContext facesContext,
                                   OutputStream outputStream) {
        try {
            _logger.info("print excelreport call start ");
            String quoteNum = // "81779" ;
                (String)ADFUtils.getSessionScopeValue("targetQuoteNumber");
          
            String orgId=null;
            OperationBinding ob2 =getBindings().getOperationBinding("getQuoteHdrOrgID");
            ob2.getParamsMap().put("pquoteNo", quoteNum);
            if (ob2 != null) {
    
             //   quotehid = ob1.execute();
                
                Map quoteDetailsMap = (Map)ob2.execute();
                
             
                orgId=(String)quoteDetailsMap.get("vOrgId");
                _logger.info("print excelreport  orgId"+orgId); 
            }
    
            Object reqid = null;
          
            int respid =
                Integer.parseInt((String)ADFUtils.getSessionScopeValue("RespId") ==
                                 null ? "51156" :
                                 (String)ADFUtils.getSessionScopeValue("RespId"));
    
            int usrId =
                Integer.parseInt((String)ADFUtils.getSessionScopeValue("UserId") ==
                                 null ? "0" :
                                 (String)ADFUtils.getSessionScopeValue("UserId"));
            String srespid = String.valueOf(respid);
            String susrId = String.valueOf(usrId);
          
          
            
          
          
    
            initializeAppsContext(susrId, srespid, "880",orgId);
            _logger.info("print after apps intilization in bean ");
    
            OperationBinding ob =
                getBindings().getOperationBinding("callDUTReport");
            ob.getParamsMap().put("confighid", null);
    
            ob.getParamsMap().put("configrevno", null);
            ob.getParamsMap().put("orderhid", null);
            ob.getParamsMap().put("quoteno", quoteNum);
            ob.getParamsMap().put("ponum", null);
            ob.getParamsMap().put("respId", respid);
            ob.getParamsMap().put("usrId", usrId);
    
            if (ob != null) {
                System.out.println("print operation binding start to execute");
                reqid = ob.execute();
                //   System.out.println("print req id"+reqid);
                System.out.println("print operation binding end to execute" +
                                   reqid);
            }
    
    
            Object path = null;
            OperationBinding ob1 =
                getBindings().getOperationBinding("getPath");
            if (ob1 != null) {
                path = ob1.execute();
            }
    
            String spath = String.valueOf(path);
    
    
            SudokuAMImpl am = this.getSudokuAMImpl();
            Connection conn =
                am.getDBTransaction().createCallableStatement("select 1 from dual",
                                                              1).getConnection();
            _logger.info("print connection " + conn);
            DataProcessor dataProcessor = new DataProcessor();
            //  dataProcessor.setDataTemplate("C:\\Users\\vthommandru\\Desktop\\reportswork\\XXAT_RDV_REP_OUTPUT.xml"); //local
    
            _logger.info("print dataProcessor " + dataProcessor);
            dataProcessor.setDataTemplate("" + spath +
                                          "XXAT_RDV_REP_OUTPUT.xml"); //server
    
            _logger.info("print dataProcessor location " + dataProcessor);
    
    
            dataProcessor.setConnection(conn);
            _logger.info("print dataProcessor set connection ");
            //  com.sun.java.util.collections.Hashtable parameters = new com.sun.java.util.collections.Hashtable();
            Hashtable parameters = new Hashtable();
            if (reqid != null) {
                parameters.put("P_REQUEST_ID", reqid);
            }
    
            dataProcessor.setParameters(parameters);
            _logger.info("data processor set parameters");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            _logger.info("ByteArrayOutputStream" + out);
            dataProcessor.setOutput(out);
            _logger.info("dataProcessor setoutput");
            dataProcessor.processData();
            _logger.info("dataProcessor.processData");
            byte[] data = out.toByteArray();
            _logger.info("byte " + data);
            ByteArrayInputStream istream = new ByteArrayInputStream(data);
            System.out.println("Done data template");
    
            _logger.info("Done data template");
    
            //  RTFProcessor rtf = new RTFProcessor("C:\\Users\\vthommandru\\Desktop\\reportswork\\XXAT_RDV_REP_CTH_OUT.rtf");  // local
    
    
            RTFProcessor rtf =
                new RTFProcessor("" + spath + "XXAT_RDV_REP_CTH_OUT.rtf"); // server
    
            _logger.info("print rtf directory " + rtf);
    
            ByteArrayOutputStream outXslFo = new ByteArrayOutputStream();
            _logger.info("outXslFo" + outXslFo);
            rtf.setOutput(outXslFo);
            _logger.info("outXslFo set output");
            rtf.process();
            _logger.info("set rtf process");
            byte[] dataXslFo = outXslFo.toByteArray();
            _logger.info("print" + dataXslFo);
            ByteArrayInputStream inXslFo = new ByteArrayInputStream(dataXslFo);
            _logger.info("print inXslFo" + inXslFo);
            FOProcessor processor = new FOProcessor();
            _logger.info("print processor" + processor);
            processor.setData(istream);
            _logger.info("processor.setData");
            processor.setTemplate(inXslFo);
            _logger.info("processor.setTemplate");
            processor.setOutput(outputStream);
            _logger.info("processor.setOutput");
            processor.setOutputFormat(FOProcessor.FORMAT_EXCEL);
            _logger.info("processorsetOutputFormat PDF" +
                         FOProcessor.FORMAT_EXCEL);
    
            processor.generate();
            _logger.info("Done power point");
            System.out.println("Done power point");
            outputStream.flush();
            _logger.info("Done flush");
    
    
        } catch (Exception e) {
            System.out.println("error::: " + e.getMessage());
            _logger.info("error::: " + e.getMessage());
        }
    
    }
    
    public void onMOFreportFetch(PopupFetchEvent popupFetchEvent) {
        try {
            
            
            String quoteNum = // "81779" ;
                (String)ADFUtils.getSessionScopeValue("targetQuoteNumber");
            Object quotehid = null;
            String orgId=null;
            OperationBinding ob1 =
                getBindings().getOperationBinding("getQuoteHdrOrgID");
            ob1.getParamsMap().put("pquoteNo", quoteNum);
            if (ob1 != null) {
    
             //   quotehid = ob1.execute();
                
                Map quoteDetailsMap = (Map)ob1.execute();
                
                quotehid=(String)quoteDetailsMap.get("vQuoteHid");
                orgId=(String)quoteDetailsMap.get("vOrgId");
            }
    
            String squotehid = String.valueOf(quotehid);
            System.out.println("print quotehid" + squotehid);
            
            int respid =
                Integer.parseInt((String)ADFUtils.getSessionScopeValue("RespId") ==
                                 null ? "51156" :
                                 (String)ADFUtils.getSessionScopeValue("RespId"));
    
            int usrId =
                Integer.parseInt((String)ADFUtils.getSessionScopeValue("UserId") ==
                                 null ? "0" :
                                 (String)ADFUtils.getSessionScopeValue("UserId"));
         
            String srespid = String.valueOf(respid);
            String susrId = String.valueOf(usrId);
            
            Object output = null;
            _logger.info("print MOF call start ");
            initializeAppsContext(srespid, susrId, "880",orgId);
            _logger.info("print after apps intilization in bean ");
    
           
    
            OperationBinding ob =
                getBindings().getOperationBinding("callMOFReport");
    
    
            ob.getParamsMap().put("confighid", null);
    
            ob.getParamsMap().put("configrevno", null);
            ob.getParamsMap().put("orderhid", null);
            ob.getParamsMap().put("quoteno", squotehid);
            ob.getParamsMap().put("ponum", null);
            //                        ob.getParamsMap().put("respId", 51157);
            //                        ob.getParamsMap().put("usrId", 0);
    
            if (ob != null) {
                System.out.println("print operation binding start to execute");
                output = ob.execute();
                //   System.out.println("print req id"+reqid);
                System.out.println("print operation binding end to execute" +
                                   output);
                setMOFop.setValue(output);
                //         if(output!=null)
                //      setResponse(output.toString());
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setSetMOFop(RichOutputFormatted setMOFop) {
        this.setMOFop = setMOFop;
    }
    
    public RichOutputFormatted getSetMOFop() {
        return setMOFop;
    }
    
    public void printCFDReport(FacesContext facesContext,
                               OutputStream outputStream) {
        BindingContainer bc =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iter =
            (DCIteratorBinding)bc.get("CFDReportVO1Iterator");
        ViewObject vo = iter.getViewObject();
        int respid =
            Integer.parseInt((String)ADFUtils.getSessionScopeValue("RespId") ==
                             null ? "51156" :
                             (String)ADFUtils.getSessionScopeValue("RespId"));
    
        int usrId =
            Integer.parseInt((String)ADFUtils.getSessionScopeValue("UserId") ==
                             null ? "0" :
                             (String)ADFUtils.getSessionScopeValue("UserId"));
    
        SudokuAMImpl am = this.getSudokuAMImpl();
        DBTransaction txn = (DBTransaction)am.getTransaction();
        String query1 = "";
        query1 = "DELETE FROM  xxat_file_details";
    
    
        CallableStatement cst = null;
        try {
            //Creating sql statement
    
    
            cst = am.getDBTransaction().createCallableStatement(query1, 0);
    
            System.out.println("Query ::: " + query1);
            cst.executeUpdate();
            am.getDBTransaction().commit();
            //Finally get returned value
        } catch (SQLException e) {
            System.out.println("SQL Exception ::: " + e.getMessage());
        } finally {
            if (cst != null) {
                try {
                    cst.close();
                } catch (SQLException e) {
                    System.out.println("Exception ::: " + e.getMessage());
                }
            }
        }
        if (vo != null) {
            vo.clearCache();
            vo.executeEmptyRowSet();
            System.out.println("Row  Count:" + vo.getEstimatedRowCount());
    
        }
    
    
        try {
            String reqid = null;
            _logger.info("print excelreport call start ");
            //  initializeAppsContext("0", "51157", "880");
            _logger.info("print after apps intilization in bean ");
            String quoteNum = // "81779" ;
                (String)ADFUtils.getSessionScopeValue("targetQuoteNumber");
            OperationBinding ob =
                getBindings().getOperationBinding("callCFDReport");
            ob.getParamsMap().put("quoteNum", quoteNum);
    
    
            ob.getParamsMap().put("respId", respid);
            ob.getParamsMap().put("usrId", usrId);
    
            if (ob != null) {
                System.out.println("print operation binding start to execute");
                reqid = (String)ob.execute();
                //   System.out.println("print req id"+reqid);
                System.out.println("print operation binding end to execute" +
                                   reqid);
            }
    
    
            // Row row=vo.getCurrentRow();
    
            BlobDomain blobDomain = null;
            // String requestId="255665340";
            if (vo != null) {
    
                String x = "REQUEST_ID=" + reqid;
                vo.setWhereClause(x);
                vo.executeQuery();
                //     vo.clearCache();
    
                Row row = vo.getCurrentRow();
    
    
                blobDomain = (BlobDomain)row.getAttribute("FileData");
                System.out.println("blobDomain ::: " + blobDomain);
                BufferedInputStream bin =
                    new BufferedInputStream(blobDomain.getBinaryStream());
    
                int b;
                byte[] buffer = new byte[10240];
                while ((b = bin.read(buffer, 0, 10240)) != -1) {
                    outputStream.write(buffer, 0, b);
                }
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("exception ::: " + e.getMessage());
        }
    }

}
