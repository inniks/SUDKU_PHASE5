package xxatcust.oracle.apps.sudoku.bean;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.xml.bind.JAXBException;

import oracle.adf.controller.ControllerContext;
import oracle.adf.controller.ViewPortContext;
import oracle.adf.model.BindingContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichListView;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.layout.RichPanelBorderLayout;
import oracle.adf.view.rich.component.rich.output.RichOutputFormatted;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.JboException;

import org.apache.commons.io.IOUtils;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.UploadedFile;

import org.xml.sax.SAXException;

import xxatcust.oracle.apps.sudoku.util.ADFUtils;
import xxatcust.oracle.apps.sudoku.util.ConfiguratorUtils;
import xxatcust.oracle.apps.sudoku.util.JSONUtils;
import xxatcust.oracle.apps.sudoku.util.JaxbParser;
import xxatcust.oracle.apps.sudoku.util.NodeComparator;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.ConfiguratorNodePOJO;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.NodeCategory;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.QuoteLinePOJO;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.SessionDetails;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.V93kQuote;


public class XMLImportPageBean {
    List<ConfiguratorNodePOJO> allNodes;
    V93kQuote v93Obj;
    private RichOutputFormatted fileNameBinding;
    private RichOutputFormatted timestampBinding;
    private RichOutputFormatted uploadedByBinding;
    ChildPropertyTreeModel categoryTree;
    ChildPropertyTreeModel categroryTreeLineTwo;
    private ArrayList<NodeCategory> root;
    private RichPopup warningPopup;
    private RichOutputFormatted warnText;
    private boolean showListHeader = false;
    private RichOutputFormatted dubugMsgBinding;
    private RichOutputFormatted debugMsgBind;
    private Boolean productsRendered = true;
    private Boolean spaceRendered = false;
    private final String XSD_FILE =
        "xxatcust/oracle/apps/sudoku/view/V93000 C&Q 3.0 - XML File Schema.xsd";
    private Properties mViewProperties = null;
    private RichOutputFormatted modelName;
    private RichInputFile uploadFileBinding;
    private RichOutputText modelNameBind;
    private RichOutputText modelDescBind;
    private RichOutputText modelQtyBind;
    private RichOutputText modelPriceBind;
    private RichOutputText extendedPriceBind;
    private RichOutputFormatted validationError;
    private RichPopup errorPopup;
    private RichOutputText quoteTotal;
    private RichPanelBorderLayout panelBorderBinding;
    private RichOutputText pageInitText;
    private RichPopup downloadPopup;
    private ArrayList<ListViewModel> listViewCollection;
    private RichOutputText pageInitOP;
    private RichOutputText expertMode;
    private RichPopup debugPopup;

    public XMLImportPageBean() {

        super();

    }
    private static ADFLogger _logger =
        ADFLogger.createADFLogger(XMLImportPageBean.class);

    public void setAllNodes(List<ConfiguratorNodePOJO> allNodes) {
        this.allNodes = allNodes;
    }

    public List<ConfiguratorNodePOJO> getAllNodes(int lineNum) {
        Object parentObj = ADFUtils.getSessionScopeValue("parentObject");
        if (parentObj != null) {
            v93Obj = (V93kQuote)parentObj;
            allNodes = parseAllNodes(v93Obj, lineNum);
        }
        return allNodes;
    }


    public void parseXMLToPojo(InputStream inputStream) throws IOException,
                                                               JsonGenerationException,
                                                               JsonMappingException,
                                                               JAXBException,
                                                               SAXException {
        File xsdFile = readXsdResource();
        V93kQuote parent = null;
        parent = JaxbParser.jaxbXMLToObject(inputStream, xsdFile);
        _logger.info("Print parent  parseXMLToPojo" + parent);
        //Add Session detail s on the parent object
        SessionDetails sessionDetails = new SessionDetails();
        sessionDetails.setApplicationId("880");
        sessionDetails.setRespId("51156");
        sessionDetails.setUserId("11639");
        parent.setSessionDetails(sessionDetails);
        String jsonStr = JSONUtils.convertObjToJson(parent);
        //V93kQuote obj = (V93kQuote)JSONUtils.convertJsonToObject(null);
        //ADFUtils.setSessionScopeValue("parentObject", obj);
        _logger.info("Print jsonStr  parseXMLToPojo" + jsonStr);
        Object obj = null;
        //Reading JSOn from File to POJO
        ObjectMapper mapper = new ObjectMapper();
        _logger.info("Print mapper  parseXMLToPojo" + mapper);
        //comment this to run locally
        String responseJson =
            (String)ConfiguratorUtils.callConfiguratorServlet(jsonStr);
        _logger.info("Print responseJson  parseXMLToPojo" + responseJson);
        //String responseJson = (String)JSONUtils.convertJsonToObject(null);
        obj = mapper.readValue(responseJson, V93kQuote.class);
        _logger.info("Print obj  parseXMLToPojo" + obj);
        ADFUtils.setSessionScopeValue("parentObject", obj);
        _logger.info("Print parentObject from session in parseXMLToPojo " +
                     ADFUtils.getSessionScopeValue("parentObject"));

    }


    private List<ConfiguratorNodePOJO> parseAllNodes(V93kQuote v93Obj,
                                                     int lineNum) {
        ArrayList<ConfiguratorNodePOJO> nodeList = null;
        ArrayList<QuoteLinePOJO> quoteLineListRef =
            v93Obj.getReferenceConfigurationLines();
        if (quoteLineListRef != null && !quoteLineListRef.isEmpty()) {
            nodeList = quoteLineListRef.get(lineNum).getItems();
        }
        return nodeList;
    }


    public void setFileNameBinding(RichOutputFormatted fileNameBinding) {
        this.fileNameBinding = fileNameBinding;
    }

    public RichOutputFormatted getFileNameBinding() {
        return fileNameBinding;
    }

    public void setTimestampBinding(RichOutputFormatted timestampBinding) {
        this.timestampBinding = timestampBinding;
    }


    public RichOutputFormatted getTimestampBinding() {
        return timestampBinding;
    }

    public void setUploadedByBinding(RichOutputFormatted uploadedByBinding) {
        this.uploadedByBinding = uploadedByBinding;
    }

    public RichOutputFormatted getUploadedByBinding() {
        return uploadedByBinding;
    }


    private List<String> removeDuplicatesFromList(List<String> inputList) {

        Set<String> set = new HashSet<String>(inputList);
        List<String> outputList = new ArrayList<String>();
        outputList.clear();
        outputList.addAll(set);
        return outputList;
    }


    public ChildPropertyTreeModel getCategoryTree() {
        return categoryTree;

    }

    public void controllerExceptionHandler() {
        ControllerContext context = ControllerContext.getInstance();
        ViewPortContext currentRootViewPort = context.getCurrentRootViewPort();
        Exception exceptionData = currentRootViewPort.getExceptionData();
        if (currentRootViewPort.isExceptionPresent()) {
            exceptionData.printStackTrace();
            currentRootViewPort.clearException();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null,
                                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                     exceptionData.getMessage(),
                                                     null));
        }
    }

    public void raiseException(ActionEvent actionEvent) {
        // Add event code here...
        JboException ex = new JboException("Custome Exveption");
        BindingContext bctx = BindingContext.getCurrent();
        ((DCBindingContainer)bctx.getCurrentBindingsEntry()).reportException(ex);
    }

    public void setWarningPopup(RichPopup warningPopup) {
        this.warningPopup = warningPopup;
    }

    public RichPopup getWarningPopup() {
        return warningPopup;
    }

    public void setWarnText(RichOutputFormatted warnText) {
        this.warnText = warnText;
    }

    public RichOutputFormatted getWarnText() {
        return warnText;
    }

    public void setShowListHeader(boolean showListHeader) {
        this.showListHeader = showListHeader;
    }

    public boolean isShowListHeader() {
        return showListHeader;
    }

    public void listViewRowSelection(SelectionEvent selectionEvent) {
        CollectionModel treeModel = null;
        RichListView listView = (RichListView)selectionEvent.getSource();
        treeModel = (CollectionModel)listView.getValue();
        RowKeySet selectedChildKeys = listView.getSelectedRowKeys();
        if (!selectedChildKeys.isEmpty()) {
            List<NodeCategory> nodeList =
                (List<NodeCategory>)treeModel.getWrappedData();
            Iterator selectedCharIter = selectedChildKeys.iterator();
            while (selectedCharIter.hasNext()) {
                List val = (List)selectedCharIter.next();
                NodeCategory nc =
                    nodeList.get(Integer.parseInt(val.get(0).toString()));
                List childNodes = nc.getChildNodes();
                if (val.size() > 0) {
                    NodeCategory nc1 =
                        (NodeCategory)childNodes.get(Integer.parseInt(val.get(1).toString()));
                    ADFUtils.showFacesMessage("Row Selected:: " +
                                              nc1.getNodeName() + " " +
                                              nc1.getNodeDescription(),
                                              FacesMessage.SEVERITY_INFO);
                }
            }
        }
    }

    public void setDubugMsgBinding(RichOutputFormatted dubugMsgBinding) {
        this.dubugMsgBinding = dubugMsgBinding;
    }

    public RichOutputFormatted getDubugMsgBinding() {
        return dubugMsgBinding;
    }

    public void setDebugMsgBind(RichOutputFormatted debugMsgBind) {
        this.debugMsgBind = debugMsgBind;
    }

    public RichOutputFormatted getDebugMsgBind() {
        return debugMsgBind;
    }

    public void setProductsRendered(Boolean productsRendered) {
        this.productsRendered = productsRendered;
    }

    public Boolean getProductsRendered() {
        return productsRendered;
    }


    public void hideProducts(ActionEvent actionEvent) {
        if (productsRendered) {
            setProductsRendered(false);
        }
        setSpaceRendered(true);
    }

    public void showProducts(ActionEvent actionEvent) {
        if (!productsRendered) {
            setProductsRendered(true);
        }
        setSpaceRendered(false);
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

    public void setModeName(RichOutputFormatted modelName) {
        this.modelName = modelName;
    }

    public RichOutputFormatted getModeName() {
        return modelName;
    }


    public void refreshViewReferenceTab() {

    }

    public void initViewReference(UploadedFile fileVal) {

    }

    public void setUploadFileBinding(RichInputFile uploadFileBinding) {
        this.uploadFileBinding = uploadFileBinding;
    }

    public RichInputFile getUploadFileBinding() {
        return uploadFileBinding;
    }

    public void setSpaceRendered(Boolean spaceRendered) {
        this.spaceRendered = spaceRendered;
    }

    public Boolean getSpaceRendered() {
        return spaceRendered;
    }


    public void setModelNameBind(RichOutputText modelNameBind) {
        this.modelNameBind = modelNameBind;
    }

    public RichOutputText getModelNameBind() {
        return modelNameBind;
    }

    public void setModelDescBind(RichOutputText modelDescBind) {
        this.modelDescBind = modelDescBind;
    }

    public RichOutputText getModelDescBind() {
        return modelDescBind;
    }

    public void setModelQtyBind(RichOutputText modelQtyBind) {
        this.modelQtyBind = modelQtyBind;
    }

    public RichOutputText getModelQtyBind() {
        return modelQtyBind;
    }

    public void setModelPriceBind(RichOutputText modelPriceBind) {
        this.modelPriceBind = modelPriceBind;
    }

    public RichOutputText getModelPriceBind() {
        return modelPriceBind;
    }

    public void setExtendedPriceBind(RichOutputText extendedPriceBind) {
        this.extendedPriceBind = extendedPriceBind;
    }

    public RichOutputText getExtendedPriceBind() {
        return extendedPriceBind;
    }

    private void resetAllBindings() {
        //        ResetUtils.reset(fileNameBinding);
        //        ResetUtils.reset(timestampBinding);
        //        ResetUtils.reset(uploadedByBinding);
        //        ResetUtils.reset(warnText);
        //        ResetUtils.reset(dubugMsgBinding);
        //        ResetUtils.reset(debugMsgBind);
        //        ResetUtils.reset(modelName);
        //        ResetUtils.reset(uploadFileBinding);
        //        ResetUtils.reset(modelNameBind);
        //        ResetUtils.reset(modelDescBind);
        //        ResetUtils.reset(modelQtyBind);
        //        ResetUtils.reset(modelPriceBind);
        //        ResetUtils.reset(extendedPriceBind);
        //        showListHeader = false;
        //        productsRendered = true;
        //        spaceRendered = false;
    }


    public void readRes(ActionEvent actionEvent) {
        InputStream asStream =
            this.getClass().getClassLoader().getResourceAsStream(XSD_FILE);
        if (asStream == null) {
            // file not foun
            _logger.info("File not found: '" + XSD_FILE + "'");
            return;
        } else {
            File f;
            try {
                f = stream2file(asStream, "V93", ".xsd");
            } catch (IOException e) {
                ADFUtils.routeExceptions(e);
            }
        }
    }

    public File readXsdResource() {
        File f = null;
        InputStream asStream =
            this.getClass().getClassLoader().getResourceAsStream(XSD_FILE);
        if (asStream == null) {
            // file not foun
            _logger.info("File not found: '" + XSD_FILE + "'");
        } else {

            try {
                f = stream2file(asStream, "V93", ".xsd");
                return f;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return f;
    }


    public void setValidationError(RichOutputFormatted validationError) {
        this.validationError = validationError;
    }

    public RichOutputFormatted getValidationError() {
        return validationError;
    }

    public void setErrorPopup(RichPopup errorPopup) {
        this.errorPopup = errorPopup;
    }

    public RichPopup getErrorPopup() {
        return errorPopup;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void initUploadXml() {
        System.out.println("Init Upload XML");
        UIComponent ui = ADFUtils.findComponentInRoot("pb2lim");
        System.out.println("uI Comp " + ui);
        System.out.println("Quote Total " + quoteTotal);
        if (quoteTotal != null && errorPopup != null) {
            getPageInitText();
        }

        if (ui != null) {
            System.out.println("REfreshing.....");
            ADFUtils.addPartialTarget(ui);
        }
    }

    public void setQuoteTotal(RichOutputText quoteTotal) {
        this.quoteTotal = quoteTotal;
    }

    public RichOutputText getQuoteTotal() {
        return quoteTotal;
    }

    public void refreshView(ActionEvent actionEvent) {
        ADFUtils.setSessionScopeValue("refreshImport", "Y");
        //Trying to clear previous values here
        warnText.setValue(null);
        showListHeader = false;
        debugMsgBind.setValue(null);
        productsRendered = true;
        spaceRendered = false;
        validationError.setValue(null);
        quoteTotal.setValue(null);
        showListHeader = true;
        categoryTree = null;
        allNodes = null;
        listViewCollection = null;
        if (expertMode != null) {
            expertMode.setValue(null);
        }
    }

    public void setPanelBorderBinding(RichPanelBorderLayout panelBorderBinding) {
        this.panelBorderBinding = panelBorderBinding;
    }

    public RichPanelBorderLayout getPanelBorderBinding() {
        return panelBorderBinding;
    }

    public void setPageInitText(RichOutputText pageInitText) {
        this.pageInitText = pageInitText;
    }

    public RichOutputText getPageInitText() {
        refreshView(null);
        getListViewCollection();
        exportDownload(null);
        UIComponent ui = ADFUtils.findComponentInRoot("pb2lim");

        if (ui != null) {
            System.out.println("UI Component is " + ui);
            if (listViewCollection != null) {
                System.out.println("Collection formed " +
                                   listViewCollection.size());
            }

            String cancelAll =
                (String)ADFUtils.getSessionScopeValue("cancelAll");
            if (cancelAll != null && cancelAll.equalsIgnoreCase("Y")) {
                if (quoteTotal != null) {
                    quoteTotal.setValue(null);
                }
            }
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            if (debugPopup != null) {
                debugPopup.show(hints);
                debugPopup.cancel();
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
            ADFUtils.addPartialTarget(ui);

        }

        return pageInitText;
    }

    public void exportDownload(ActionEvent actionEvent) {
        UIComponent ui = ADFUtils.findComponentInRoot("pb2lim");
        if (ui != null) {
            AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
        }
    }

    public void setDownloadPopup(RichPopup downloadPopup) {
        this.downloadPopup = downloadPopup;
    }

    public RichPopup getDownloadPopup() {
        return downloadPopup;
    }

    public void downloadFile(FacesContext facesContext,
                             OutputStream outputStream) {
        try {
            File file =
                new File("D://Projects//Advantest//JsonResponse/exportTarget.xml");
            FileInputStream fis;
            byte[] b;
            fis = new FileInputStream(file);

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
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            // TODO: Add catch code
            ioe.printStackTrace();
        }
    }

    public void downloadConfirmation(ActionEvent actionEvent) {
        downloadPopup.hide();
    }


    public ChildPropertyTreeModel getCategroryTreeLineTwo() {
        return categroryTreeLineTwo;
    }

    private List<ChildPropertyTreeModel> createChildrenTrees() {
        List<ChildPropertyTreeModel> listOfTrees =
            new ArrayList<ChildPropertyTreeModel>();
        try {
            StringBuilder errMessage = new StringBuilder("ERROR");
            V93kQuote parentObj =
                (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
            if (parentObj != null) {
                V93kQuote obj = (V93kQuote)parentObj;
                //Check if no exceptions from configurator
                if (obj.getExceptionMap() != null) {
                    TreeMap<String, ArrayList<String>> exceptionMap =
                        obj.getExceptionMap().getErrorList();
                    TreeMap<String, ArrayList<String>> notifications =
                        obj.getExceptionMap().getNotificationList();
                    TreeMap<String, ArrayList<String>> warnings =
                        obj.getExceptionMap().getWarningList();
                    TreeMap<String, ArrayList<String>> debugList =
                        obj.getExceptionMap().getDebugMessageList();
                    List<String> debugMessages =
                        obj.getExceptionMap().getDebugMessages();


                    //Check for warnings from configurator
                    StringBuilder warningMessage =
                        new StringBuilder("<html><body>");
                    if (warnings != null && warnings.size() > 0) {


                        for (Map.Entry<String, ArrayList<String>> entry :
                             warnings.entrySet()) {
                            String key = entry.getKey();
                            //iterate for each key
                            warningMessage.append("<p><b>" + key + " : " +
                                                  "</b></p>");
                            ArrayList<String> value = entry.getValue();
                            for (String str : value) {
                                warningMessage.append("<p><b>" + str +
                                                      "</b></p>");
                            }
                        }
                        warningMessage.append("</body></html>");
                    }
                    //Check for notification messages from configurator

                    if (notifications != null && notifications.size() > 0) {
                        for (Map.Entry<String, ArrayList<String>> entry :
                             notifications.entrySet()) {
                            String key = entry.getKey();
                            ArrayList<String> value = entry.getValue();
                            warningMessage.append("<p><b>" + key + " : " +
                                                  "</b></p>");
                            for (String str : value) {
                                warningMessage.append("<p><b>" + str +
                                                      "</b></p>");
                            }
                        }
                        warningMessage.append("</body></html>");

                        // debugMsgBind.setValue(debugStr.toString());
                    }
                    //warnText.setValue(warningMessage.toString()); // Probable change 1
                    StringBuilder debugMessage =
                        new StringBuilder("<html><body>");
                    if (debugList != null && debugList.size() > 0) {
                        for (Map.Entry<String, ArrayList<String>> entry :
                             debugList.entrySet()) {
                            String key = entry.getKey();
                            ArrayList<String> value = entry.getValue();
                            debugMessage.append("<p><b>" + key + " : " +
                                                "</b></p>");
                            for (String str : value) {
                                debugMessage.append("<p><b>" + str +
                                                    "</b></p>");
                            }
                        }
                        debugMessage.append("</body></html>");
                    }
                    if (debugMessages != null && debugMessages.size() > 0) {
                        for (String str : debugMessages) {
                            debugMessage.append("<p><b>" + str + "</b></p>");
                        }
                    }
                    if (debugMsgBind != null) {
                        debugMsgBind.setValue(debugMessage.toString()); // Probable change 2
                    }

                    List<String> errorMessages =
                        obj.getExceptionMap().getErrorsMessages();
                    StringBuilder formattedErrStr =
                        new StringBuilder("<html><body>");
                    if (exceptionMap != null && exceptionMap.size() > 0) {

                        for (Map.Entry<String, ArrayList<String>> entry :
                             exceptionMap.entrySet()) {
                            String key = entry.getKey();
                            ArrayList<String> value = entry.getValue();
                            for (String str : value) {
                                errMessage.append(str);
                                formattedErrStr.append("<p><b>" + str +
                                                       "</b></p>");
                            }
                        }
                    }
                    if (errorMessages != null && errorMessages.size() > 0) {
                        for (String str : errorMessages) {
                            errMessage.append(str);
                            formattedErrStr.append("<p><b>" + str +
                                                   "</b></p>");
                        }
                    }
                    formattedErrStr.append("<body><html>");

                    if (errMessage != null &&
                        errMessage.toString().equals("ERROR")) {
                        //Check for Expert Mode
                        boolean reloadInExpertMode = false;
                        if (obj.getSessionDetails() != null) {
                            reloadInExpertMode =
                                    obj.getSessionDetails().isReloadInExpertMode();
                        }
                        if (expertMode != null) {
                            if (reloadInExpertMode) {
                                expertMode.setValue("Configuration switched to 93k expert mode");
                            } else {
                                expertMode.setValue(null);
                            }
                        }

                        RichPopup.PopupHints hints =
                            new RichPopup.PopupHints();
                        if (warningMessage != null &&
                            !warningMessage.toString().equalsIgnoreCase("<html><body>") &&
                            warningPopup != null) {
                            warnText.setValue(warningMessage.toString());
                            warningPopup.show(hints);
                        }


                    } else {
                        String errTemp = null;
                        if (errMessage != null &&
                            !errMessage.toString().equals("ERROR")) {
                            errTemp = errMessage.toString().substring(5);

                        }
                        if (validationError != null) {
                            validationError.setValue(formattedErrStr.toString());
                        }
                        RichPopup.PopupHints hints1 =
                            new RichPopup.PopupHints();
                        errorPopup.show(hints1);
                        ADFUtils.setSessionScopeValue("quoteNumber",
                                                      null); //If exception occurs , Quoting should be loaded in create mode, Not in update mode
                        ADFUtils.setSessionScopeValue("targetQuoteNumber",
                                                      null);
                        parentObj.setConfigObject(null);
                        parentObj.setSessionDetails(null);
                        parentObj.setQheaderObject(null);
                        parentObj.setInputParams(null);
                        ADFUtils.setSessionScopeValue("parentObject",
                                                      parentObj);
                        //ADFUtils.setSessionScopeValue("parentObject", null);
                        root = new ArrayList();
                        categoryTree =
                                new ChildPropertyTreeModel(root, "childNodes");
                        listOfTrees.add(categoryTree);
                        return listOfTrees;

                    }
                }
                Double sumQuoteTotal = new Double(0);


                List refLines = obj.getReferenceConfigurationLines();
                if (refLines != null && !refLines.isEmpty()) {
                    //in a loop call the getAllNodes
                    for (int i = 0; i < refLines.size(); i++) {
                        List<String> catList = new ArrayList<String>();
                        List<String> distinctList = new ArrayList<String>();
                        List<ConfiguratorNodePOJO> allNodesList =
                            getAllNodes(i);

                        HashMap<String, List<ConfiguratorNodePOJO>> allNodesByCategoriesMap =
                            new HashMap<String, List<ConfiguratorNodePOJO>>();
                        if (allNodesList != null && !allNodesList.isEmpty()) {
                            for (ConfiguratorNodePOJO node : allNodesList) {
                                if (node.getPrintGroupLevel() != null &&
                                    node.getPrintGroupLevel().equalsIgnoreCase("1")) {
//                                    if (node.getExtendedPrice() != null) {
//                                        Double b =
//                                            new Double(node.getExtendedPrice());
//                                        sumQuoteTotal = sumQuoteTotal + b;
//                                    }
                                }
                                if (node.getNodeCategory() != null &&
                                    node.getPrintGroupLevel() != null) {
                                    catList.add(node.getNodeCategory() + "-" +
                                                (node.getPrintGroupLevel() !=
                                                 null ?
                                                 node.getPrintGroupLevel() :
                                                 "0"));
                                }
                            }
                        }
                        if (quoteTotal != null) {
                            quoteTotal.setValue(sumQuoteTotal);
                        }
                        distinctList = removeDuplicatesFromList(catList);
                        for (String distinctCategory : distinctList) {
                            List<ConfiguratorNodePOJO> temp =
                                new ArrayList<ConfiguratorNodePOJO>();
                            if (allNodesList != null &&
                                !allNodesList.isEmpty()) {
                                for (ConfiguratorNodePOJO node :
                                     allNodesList) {
                                    if (distinctCategory != null &&
                                        distinctCategory.equalsIgnoreCase(node.getNodeCategory() +
                                                                          "-" +
                                                                          node.getPrintGroupLevel())) {

                                        temp.add(node);
                                    }
                                }
                            }
                            allNodesByCategoriesMap.put(distinctCategory,
                                                        temp);
                        }
                        root = new ArrayList<NodeCategory>();
                        Iterator it =
                            allNodesByCategoriesMap.entrySet().iterator();
                        NodeCategory firstLevel = null;
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry)it.next();
                            String Key = (String)pair.getKey();
                            String[] arr = Key.split("-");
                            String category = arr[0];
                            String printGrpLevel = arr[1];
                            firstLevel =
                                    new NodeCategory(category, null, null, null,
                                                     null, null, null, null,
                                                     printGrpLevel, null);
                            root.add(firstLevel);
                            List<ConfiguratorNodePOJO> childList =
                                (List<ConfiguratorNodePOJO>)pair.getValue();

                            for (ConfiguratorNodePOJO node : childList) {
                                //System.out.println("Node is "+node.getNodeName());
                                String nodeDesig = null;
                                if (node.getPrintGroupLevel() != null &&
                                    node.getPrintGroupLevel().equalsIgnoreCase("1")) {
                                    nodeDesig = "header";
                                    System.out.println("Setting node Designation");
                                }
                                NodeCategory secondLevel =
                                    new NodeCategory(category,
                                                     node.getNodeName(),
                                                     node.getNodeDescription(),
                                                     node.getNodeQty(),
                                                     node.getNodeValue(),
                                                     node.getUnitPrice(),
                                                     node.getExtendedPrice(),
                                                     node.getNodeColor(),
                                                     node.getPrintGroupLevel(),
                                                     nodeDesig);
                                firstLevel.addNodes(secondLevel);
                            }

                        }
                        //Trying to sort root
                        NodeComparator comparator = new NodeComparator();
                        Collections.sort(root, comparator);

                        categoryTree =
                                new ChildPropertyTreeModel(root, "childNodes");
                        ADFUtils.setSessionScopeValue("categoryTree",
                                                      categoryTree);
                        listOfTrees.add(categoryTree);
                    }
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            validationError = null;
            
        }
        return listOfTrees;
    }


    public void setPageInitOP(RichOutputText pageInitOP) {
        this.pageInitOP = pageInitOP;
    }

    public RichOutputText getPageInitOP() {
        return pageInitOP;
    }


    public ArrayList<ListViewModel> getListViewCollection() {
        String refreshImport =
            (String)ADFUtils.getSessionScopeValue("refreshImport");
        String cancelAll = (String)ADFUtils.getSessionScopeValue("cancelAll");
        if (listViewCollection == null && refreshImport != null &&
            refreshImport.equalsIgnoreCase("Y")) {
            listViewCollection = new ArrayList<ListViewModel>();
            List<ChildPropertyTreeModel> listOftrees = createChildrenTrees();
            if (listOftrees != null && !listOftrees.isEmpty()) {
                for (int i = 0; i < listOftrees.size(); i++) {
                    ListViewModel listViewObj =
                        new ListViewModel(listOftrees.get(i),
                                          String.valueOf(i + 1));
                    listViewCollection.add(listViewObj);

                }
            }
        }
        System.out.println("List view coll " + listViewCollection);
        if (panelBorderBinding != null) {
            ADFUtils.addPartialTarget(panelBorderBinding);
        }
        System.out.println("Page init text is " + pageInitText);
        return listViewCollection;
    }

    public void setExpertMode(RichOutputText expertMode) {
        this.expertMode = expertMode;
    }

    public RichOutputText getExpertMode() {
        return expertMode;
    }

    public void setDebugPopup(RichPopup debugPopup) {
        this.debugPopup = debugPopup;
    }

    public RichPopup getDebugPopup() {
        return debugPopup;
    }
}
