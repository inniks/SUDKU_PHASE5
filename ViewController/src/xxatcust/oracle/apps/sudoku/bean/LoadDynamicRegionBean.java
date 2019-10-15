package xxatcust.oracle.apps.sudoku.bean;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import java.net.MalformedURLException;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.util.HashMap;

import java.util.Hashtable;
import java.util.List;

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
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.nav.RichCommandImageLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandToolbarButton;
import oracle.adf.view.rich.component.rich.output.RichOutputFormatted;

import oracle.adf.view.rich.context.AdfFacesContext;


import oracle.apps.fnd.ext.common.AppsRequestWrapper;
import oracle.apps.fnd.ext.common.AppsSessionHelper;
import oracle.apps.fnd.ext.common.EBiz;
import oracle.apps.fnd.ext.common.Session;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.JboException;
import oracle.jbo.Row;

import oracle.jbo.server.DBTransaction;

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
            String jsonStr = JSONUtils.convertObjToJson(v93k);
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
        //Set one session scope for cancelled
        ADFUtils.setSessionScopeValue("cancelAll", "Y");
        RichCommandImageLink button =
            (RichCommandImageLink)ADFUtils.findComponentInRoot("ctb1_vre"); // Navigate to view reference page
        ActionEvent acEvent = new ActionEvent(button);
        acEvent.queue();
        //Refresh the view
        //AdfFacesContext.getCurrentInstance().addPartialTarget(ADFUtils.findComponentInRoot("pt_pgl1"));

        //Close the popup
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

    public void processPDFOutput(FacesContext facesContext,
                                 OutputStream outputStream) {
//        try {
//            _logger.info("print report call start ");
//            //initializeAppsContext("0", "51157", "880");
//            int respid =
//                Integer.parseInt((String)ADFUtils.getSessionScopeValue("RespId") ==
//                                 null ? "51156" :
//                                 (String)ADFUtils.getSessionScopeValue("RespId"));
//            int usrId =
//                Integer.parseInt((String)ADFUtils.getSessionScopeValue("UserId") ==
//                                 null ? "0" :
//                                 (String)ADFUtils.getSessionScopeValue("UserId"));
//            String quoteNum = (String)ADFUtils.getSessionScopeValue("targetQuoteNumber");
//            initializeAppsContext(respid, usrId, "880");
//            _logger.info("print after apps intilization in bean ");
//            String template =
//                "XXATREP_PMF"; //"XXATRPT_PMF_V4_Y_mux_FRAMES_PDF";
//            String pconfighid = null;
//            String pconfigrevnum = null;
//            String porderhid = null;
//            String quotehid = "97416";
//            String ponumber = null;
//            //QUOTE HEADER ID -- 97416
//
//            ApplicationModule am = getAppModule();
//            DBTransaction dbTrans = (DBTransaction)am.getTransaction();
//            Connection conn =
//                dbTrans.createCallableStatement("select 1 from dual",
//                                                1).getConnection();
//            _logger.info("print connection " + conn);
//            DataProcessor dataProcessor = new DataProcessor();
//            //dataProcessor.setDataTemplate("C:\\Users\\vthommandru\\Downloads\\PMF_REPORT_pmux_frames_PDF.xml"); //local
//            _logger.info("print dataProcessor " + dataProcessor);
//            dataProcessor.setDataTemplate("/u01/app/oracle/Middleware/user_projects/domains/bifoundation_domain/servers/AdminServer/upload/ReportTemplates/PMF_REPORT_pmux_frames_PDF.xml"); //server
//            _logger.info("print dataProcessor location " + dataProcessor);
//            dataProcessor.setConnection(conn);
//            _logger.info("print dataProcessor set connection ");
//            Hashtable parameters = new Hashtable();
//            if (pconfighid != null) {
//                parameters.put("P_CONFIG_HEADER_ID", pconfighid);
//            }
//            if (pconfigrevnum != null) {
//                parameters.put("P_CONFIG_REV_NBR", pconfigrevnum);
//            }
//            if (porderhid != null) {
//                parameters.put("P_ORDER_HEADER_ID", porderhid);
//            }
//            if (quotehid != null) {
//                parameters.put("P_QUOTE_HEADER_ID", quotehid);
//            }
//
//            if (ponumber != null) {
//                parameters.put("P_PO_NUMBER", ponumber);
//            }
//            _logger.info("print quotehid " + quotehid);
//            dataProcessor.setParameters(parameters);
//            _logger.info("data processor set parameters");
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            _logger.info("ByteArrayOutputStream" + out);
//            dataProcessor.setOutput(out);
//            _logger.info("dataProcessor setoutput");
//            dataProcessor.processData();
//            _logger.info("dataProcessor.processData");
//            byte[] data = out.toByteArray();
//            _logger.info("byte " + data);
//            ByteArrayInputStream istream = new ByteArrayInputStream(data);
//            System.out.println("Done data template");
//
//            _logger.info("Done data template");
//
//            //            RTFProcessor rtf =
//            //                new RTFProcessor("C:\\Users\\vthommandru\\Downloads\\XXATRPT_PMF_V4_Y_mux_FRAMES_PDF.rtf"); // local
//
//
//            RTFProcessor rtf =
//                new RTFProcessor("/u01/app/oracle/Middleware/user_projects/domains/bifoundation_domain/servers/AdminServer/upload/ReportTemplates/XXATRPT_PMF_V4_Y_mux_FRAMES_PDF.rtf"); // server
//
//            _logger.info("print rtf directory " + rtf);
//            ByteArrayOutputStream outXslFo = new ByteArrayOutputStream();
//            _logger.info("outXslFo" + outXslFo);
//            rtf.setOutput(outXslFo);
//            _logger.info("outXslFo set output");
//            rtf.process();
//            _logger.info("set rtf process");
//            byte[] dataXslFo = outXslFo.toByteArray();
//            _logger.info("print" + dataXslFo);
//            ByteArrayInputStream inXslFo = new ByteArrayInputStream(dataXslFo);
//            _logger.info("print inXslFo" + inXslFo);
//            FOProcessor processor = new FOProcessor();
//            _logger.info("print processor" + processor);
//            processor.setData(istream);
//            _logger.info("processor.setData");
//            processor.setTemplate(inXslFo);
//            _logger.info("processor.setTemplate");
//            processor.setOutput(outputStream);
//            _logger.info("processor.setOutput");
//            processor.setOutputFormat(FOProcessor.FORMAT_PDF);
//            _logger.info("processorsetOutputFormat PDF" +
//                         FOProcessor.FORMAT_PDF);
//            processor.generate();
//            _logger.info("Done power point");
//            System.out.println("Done power point");
//            outputStream.flush();
//            _logger.info("Done flush");
//
//
//        } catch (Exception e) {
//            System.out.println("error::: " + e.getMessage());
//            _logger.info("error::: " + e.getMessage());
//        }
    }

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
}
