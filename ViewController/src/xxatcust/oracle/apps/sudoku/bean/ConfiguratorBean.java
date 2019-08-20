package xxatcust.oracle.apps.sudoku.bean;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.Map;

import java.util.Set;

import java.util.TreeMap;

import javax.faces.component.UIComponent;

import javax.faces.component.UIForm;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.servlet.ServletException;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichListView;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailHeader;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.component.rich.output.RichOutputFormatted;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.event.DisclosureEvent;
import org.apache.myfaces.trinidad.event.RowDisclosureEvent;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;

import xxatcust.oracle.apps.sudoku.util.ADFUtils;
import xxatcust.oracle.apps.sudoku.util.ConfiguratorUtils;
import xxatcust.oracle.apps.sudoku.util.JSONUtils;
import xxatcust.oracle.apps.sudoku.util.SudokuUtils;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.V93kQuote;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.elements.ConfiguratorUiElement;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.elements.ConfiguratorUiGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.elements.ConfiguratorUiNode;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.elements.ConfiguratorUiSubGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ux.UiField;
import xxatcust.oracle.apps.sudoku.viewmodel.ux.UxTablePojo;
import xxatcust.oracle.apps.sudoku.viewmodel.ux.UxTreeNode;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.InputParams;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.SessionDetails;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.SystemInfraGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.UiSelection;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.WtyTrainingSAndSGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ux.ShowDetailItemCollection;

public class ConfiguratorBean {
    private List<ShowDetailItemCollection> sdiCollection;
    private List<ShowDetailItemCollection> sysConSdiCollection;
    private List<ShowDetailItemCollection> addSwToolsSdiCollection;
    private List<ShowDetailItemCollection> calDiagSdiCollection;
    private ChildPropertyTreeModel addToolsTreeModel;
    private ChildPropertyTreeModel sysInfraTreeModel;
    private ChildPropertyTreeModel calDiagTreeModel;
    private ArrayList<UxTreeNode> sysInfraroot;
    private ArrayList<UxTreeNode> rootWarranty;
    private ArrayList<UxTreeNode> rootCalDiag;
    private ArrayList<UxTreeNode> rootSysController;
     private ArrayList<UxTreeNode> rootAddSwTools;;
    private RichPanelGroupLayout theadPanelGrp;
    private RichOutputText pageInitText;
    private RichListView sysInfraListView;
    private RichShowDetailItem sysInfraSdi;
    private RichInputText jsessionId;
    private RichPopup confirmPopup;
    private RichOutputFormatted warnText;
    private RichShowDetailHeader shwDetHdrBind;
    private RichListView warrantyListView;
    private ChildPropertyTreeModel warrantyTreeModel;
    private ArrayList<UiField> warrantyUiCollection;
    private ArrayList<UiField> sysControllerUiCollection;
    private String groupDiscPolicy;
    private RichListView sysControllerListViewBinding;
    private ChildPropertyTreeModel sysControllerTreeModel;

    public ConfiguratorBean() {
        super();
    }

    public ChildPropertyTreeModel getSysInfraTreeModel() {
        return sysInfraTreeModel;
    }

    public void handleCellSelection(ClientEvent clientEvent) {
        UIComponent ui =
            (UIComponent)clientEvent.getParameters().get("payload");
        // get the column from the event which is sent too
        String column = (String)clientEvent.getParameters().get("column");
        RichOutputFormatted rt = (RichOutputFormatted)ui;
        String inlineStyle = "color:Teal; font-weight:bolder;";
        rt.setInlineStyle(inlineStyle);
        ADFUtils.addPartialTarget(rt);
        String out = "Payload:" + ui + " " + column + " Val: " + rt.getValue();
        System.out.println("Out Value " + out);

    }


    public void setTheadPanelGrp(RichPanelGroupLayout theadPanelGrp) {
        this.theadPanelGrp = theadPanelGrp;
    }

    public RichPanelGroupLayout getTheadPanelGrp() {
        return theadPanelGrp;
    }

    public void initConfigurator() throws IOException, JsonGenerationException,
                                          JsonMappingException {
        //The refresh should happen only if there is a v93k object available
        V93kQuote v93k =
            (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        if (v93k != null && v93k.getInputParams() != null &&
            v93k.getInputParams().getImportSource() != null) {

            refreshView(null);
        }
    }

    public RichOutputText getPageInitText() throws IOException,
                                                   JsonGenerationException,
                                                   JsonMappingException {
        System.out.println("Initializing Page.....");

        return pageInitText;
    }

    public void setPageInitText(RichOutputText pageInitText) {
        this.pageInitText = pageInitText;
    }

    public V93kQuote callConfigurator(V93kQuote v93k) {
        V93kQuote v93kobj = (V93kQuote)JSONUtils.convertJsonToObject(null);
        return v93kobj;
    }

    public Object convertJsonToObject(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        Object obj = null;
        try {
            obj =
mapper.readValue(new File("D://Projects//Advantest//JsonResponse/UIRoot.json"),
                 xxatcust.oracle.apps.sudoku.viewmodel.pojo.V93kQuote.class);

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }


    public void refreshView(ActionEvent actionEvent) throws IOException,
                                                            JsonGenerationException,
                                                            JsonMappingException {
        System.out.println("RECREATING THE UI TREE......");
        //Call configurator to load data
        sysInfraTreeModel = null;
        warrantyTreeModel = null;
        sysControllerTreeModel=null;
        addToolsTreeModel = null;
        calDiagTreeModel = null;
        V93kQuote v93k =
            (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        if (v93k == null) {
            v93k = new V93kQuote();
            SessionDetails sessionDetails = new SessionDetails();
            InputParams inputParam = new InputParams();
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
            inputParam.setImportSource("LOAD_CONFIG_UI");
            String userId =
                (String)ADFUtils.getSessionScopeValue("UserId") == null ? "0" :
                (String)ADFUtils.getSessionScopeValue("UserId");
            String timestamp = Long.toString(System.currentTimeMillis());
            String uniqueSessionId = userId.concat(timestamp);
            ADFUtils.setSessionScopeValue("uniqueSessionId", uniqueSessionId);
            UiSelection uiSelection = new UiSelection();
            uiSelection.setUniqueSessionId(uniqueSessionId);
            v93k.setSessionDetails(sessionDetails);
            v93k.setUiSelection(uiSelection);
            v93k.setInputParams(inputParam);
        }
        buildConfiguratorUI(v93k);

        //try


    }

    public void setSysInfraListView(RichListView sysInfraListView) {
        this.sysInfraListView = sysInfraListView;
    }

    public RichListView getSysInfraListView() {
        return sysInfraListView;
    }

    public void sysInfraDisclosure(DisclosureEvent disclosureEvent) {
        System.out.println("Disclosure Event Fired");
    }

    public void setSysInfraSdi(RichShowDetailItem sysInfraSdi) {
        this.sysInfraSdi = sysInfraSdi;
    }

    public RichShowDetailItem getSysInfraSdi() {
        return sysInfraSdi;
    }


    public void handleNodeSelection(ActionEvent actionEvent) throws IOException,
                                                                    JsonGenerationException,
                                                                    JsonMappingException {
        UIComponent component = actionEvent.getComponent();
        List<UIComponent> children = component.getChildren();
        String selectedValue = null;
        HashMap<String, String> selectedNodeValueMap =
            new HashMap<String, String>();
        String uiSubGrpName = (String)ADFUtils.evaluateEL("#{node.nodeName}");
        selectedNodeValueMap.put("uiSubGrpName", uiSubGrpName);
        String czNodeName = null;
        String nodeColor = null;
        String identifier = null;
        String parentGroupName = null;
        //(V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        for (UIComponent comp : children) {
            if (comp instanceof RichOutputFormatted) {
                RichOutputFormatted of = (RichOutputFormatted)comp;
                selectedValue = (String)of.getValue();
                selectedNodeValueMap.put("selectedValue", selectedValue);
                //czNodeName = of.getShortDesc() ;
            }
            if (comp instanceof RichInputText) {
                RichInputText it = (RichInputText)comp;
                if (it != null) {
                    identifier = it.getShortDesc();
                    czNodeName = (String)it.getValue();
                    nodeColor = it.getLabel();
                    parentGroupName = it.getPlaceholder();
                    selectedNodeValueMap.put("identifier", identifier);
                    selectedNodeValueMap.put("czNodeName", czNodeName);
                    selectedNodeValueMap.put("nodeColor", nodeColor);
                    selectedNodeValueMap.put("parentGroupName",
                                             parentGroupName);
                }
            }
        }
        V93kQuote v93 =
            (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        ADFUtils.setSessionScopeValue("selectedNodeValueMap",
                                      selectedNodeValueMap);

        if (v93 != null && v93.getExceptionMap() != null) {
            TreeMap<String, ArrayList<String>> warnings =
                v93.getExceptionMap().getWarningList();
            TreeMap<String, ArrayList<String>> notifications =
                v93.getExceptionMap().getNotificationList();
            StringBuilder warningMessage = new StringBuilder("<html><body>");
            if (warnings != null && warnings.size() > 0) {


                for (Map.Entry<String, ArrayList<String>> entry :
                     warnings.entrySet()) {
                    String key = entry.getKey();
                    //iterate for each key
                    warningMessage.append("<p><b>" + key + " : " + "</b></p>");
                    ArrayList<String> value = entry.getValue();
                    for (String str : value) {
                        warningMessage.append("<p><b>" + str + "</b></p>");
                    }
                }
                warningMessage.append("</body></html>");
            }
            if (notifications != null && notifications.size() > 0) {
                for (Map.Entry<String, ArrayList<String>> entry :
                     notifications.entrySet()) {
                    String key = entry.getKey();
                    ArrayList<String> value = entry.getValue();
                    warningMessage.append("<p><b>" + key + " : " + "</b></p>");
                    for (String str : value) {
                        warningMessage.append("<p><b>" + str + "</b></p>");
                    }
                }
                warningMessage.append("</body></html>");


            }
            if (warningMessage != null &&
                !warningMessage.toString().equalsIgnoreCase("<html><body>") &&
                confirmPopup != null) {
                warnText.setValue(warningMessage.toString());
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                confirmPopup.show(hints);
            } else {
                continueWithSelection();
            }
        }


    }

    public void buildConfiguratorUI(V93kQuote v93k) throws IOException,
                                                           JsonGenerationException,
                                                           JsonMappingException {

        String jsenId = null;
        if (jsessionId != null) {
            jsenId = (String)jsessionId.getValue();
        }
        ADFUtils.setSessionScopeValue("jsenid", jsenId);
        
        String jsonStr = JSONUtils.convertObjToJson(v93k);
        System.out.println("Json String build is" + jsonStr);
        //If config is live use this

//        String responseJson =
//            ConfiguratorUtils.callConfiguratorServlet(jsonStr);
//        System.out.println("Response Json from Configurator : " +
//                           responseJson);
//        ObjectMapper mapper = new ObjectMapper();
//        Object obj = mapper.readValue(responseJson, V93kQuote.class);
//        v93k = (V93kQuote)obj;
        //else use this
        v93k = (V93kQuote)convertJsonToObject(null);
        ADFUtils.setSessionScopeValue("parentObject", v93k);
        ADFUtils.setSessionScopeValue("refreshImport", "Y");
        if (sysInfraTreeModel == null) {
            sysInfraTreeModel =
                    SystemInfraBean.populateSysInfraParentTreeModel(sysInfraTreeModel,
                                                                    sysInfraroot);
            sdiCollection =
                    SystemInfraBean.populateSysInfraSubGroups(v93k, sdiCollection);
        }
        if (warrantyTreeModel == null) {
            warrantyTreeModel =
                    WtyTrainingAndSupportBean.populateWarrantyParentModel(warrantyTreeModel,
                                                                          rootWarranty);
            warrantyUiCollection =
                    WtyTrainingAndSupportBean.populateWarrantySubGrps(v93k,
                                                                      warrantyUiCollection);
        }
        if(sysControllerTreeModel==null){
            sysControllerTreeModel = SystemControllerBean.populateSysControllerParentModel(sysControllerTreeModel, rootSysController);
            //sysControllerUiCollection = SystemControllerBean.populateSysControllerSubGrps(v93k, sysControllerUiCollection);
            sysConSdiCollection = SystemControllerBean.populateSysContSubGroups(v93k, sysConSdiCollection);
            
        }
        if(addToolsTreeModel==null){
            addToolsTreeModel = AdditionalSfwToolsBean.populateAddSwToolsParentTreeModel(addToolsTreeModel, rootAddSwTools);
            addSwToolsSdiCollection = AdditionalSfwToolsBean.populateAddSwToolsSubGroups(v93k, addSwToolsSdiCollection);
            
        }
        if(calDiagTreeModel==null){
            calDiagTreeModel = DiagCalEquipmentBean.populateCalDiagParentTreeModel(calDiagTreeModel, rootCalDiag);
            calDiagSdiCollection = DiagCalEquipmentBean.populateCalDiagSubGroups(v93k, calDiagSdiCollection);
        }

        ADFUtils.setSessionScopeValue("rebuildUI", null);

    }

    public void setJsessionId(RichInputText jsessionId) {
        this.jsessionId = jsessionId;
    }

    public RichInputText getJsessionId() {
        return jsessionId;
    }

    public void setSdiCollection(List<ShowDetailItemCollection> sdiCollection) {
        this.sdiCollection = sdiCollection;
    }

    public List<ShowDetailItemCollection> getSdiCollection() {
        return sdiCollection;
    }

    public void configDialogListener(DialogEvent dialogEvent) {
        // Add event code here...
    }

    public void setConfirmPopup(RichPopup confirmPopup) {
        this.confirmPopup = confirmPopup;
    }

    public RichPopup getConfirmPopup() {
        return confirmPopup;
    }

    public void setWarnText(RichOutputFormatted warnText) {
        this.warnText = warnText;
    }

    public RichOutputFormatted getWarnText() {
        return warnText;
    }

    public void warnPopupDialogListener(DialogEvent dialogEvent) throws IOException,
                                                                        JsonGenerationException,
                                                                        JsonMappingException {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            continueWithSelection();
        }

        if (dialogEvent.getOutcome() == DialogEvent.Outcome.cancel) {
            confirmPopup.cancel();
        }
    }

    public void continueWithSelection() throws IOException,
                                               JsonGenerationException,
                                               JsonMappingException {
        String uniqueSessionId =
            (String)ADFUtils.getSessionScopeValue("uniqueSessionId");

        V93kQuote v93k =
            (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        if (v93k == null) {
            v93k = new V93kQuote();
        }
        HashMap selectedNodeValueMap =
            (HashMap)ADFUtils.getSessionScopeValue("selectedNodeValueMap");
        if (v93k != null && selectedNodeValueMap != null &&
            !selectedNodeValueMap.isEmpty()) {
            String uiSubGrpName =
                (String)selectedNodeValueMap.get("uiSubGrpName");
            String selectedValue =
                (String)selectedNodeValueMap.get("selectedValue");
            String czNodeName = (String)selectedNodeValueMap.get("czNodeName");
            String identifier = (String)selectedNodeValueMap.get("identifier");
            String nodeColor = (String)selectedNodeValueMap.get("nodeColor");
            String parentGroupName =
                (String)selectedNodeValueMap.get("parentGroupName");
            String selectionState = null;
            if (nodeColor != null &&
                nodeColor.equalsIgnoreCase(SudokuUtils.TARGET_COLOR)) {
                //means node is already selected,set selecteion state as false
                selectionState = "FALSE";
            }
            if (nodeColor != null &&
                nodeColor.equalsIgnoreCase(SudokuUtils.REFERENCE_COLOR)) {
                //means node is not selected
                selectionState = "TRUE";
            }
            if (nodeColor == null) {
                selectionState = "TRUE";
            }
            UiSelection uiSelection = new UiSelection();
            if (uiSelection == null) {
                uiSelection = new UiSelection();
            }
            uiSelection.setParentGroupName(parentGroupName);
            uiSelection.setSubGroupName(uiSubGrpName);
            uiSelection.setValueSelected(selectedValue);
            uiSelection.setUniqueSessionId(uniqueSessionId);
            uiSelection.setCzNodeName(czNodeName);
            uiSelection.setIdentifier(identifier);
            uiSelection.setSelectionState(selectionState);
            v93k.setUiSelection(uiSelection);

            SessionDetails sessionDetails = new SessionDetails();
            InputParams inputParam = new InputParams();
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
            inputParam.setImportSource("REFRESH_CONFIG_UI");
            v93k.setSessionDetails(sessionDetails);
            v93k.setInputParams(inputParam);
            ADFUtils.setSessionScopeValue("parentObject", v93k);

            buildConfiguratorUI(v93k);
            ADFUtils.setSessionScopeValue("selectedNodeValueMap", null);
            confirmPopup.hide();
            ADFUtils.addPartialTarget(ADFUtils.findComponentInRoot("confPGL"));
        }
    }

    public void setShwDetHdrBind(RichShowDetailHeader shwDetHdrBind) {
        this.shwDetHdrBind = shwDetHdrBind;
    }

    public RichShowDetailHeader getShwDetHdrBind() {
        return shwDetHdrBind;
    }

    public void setWarrantyListView(RichListView warrantyListView) {
        this.warrantyListView = warrantyListView;
    }

    public RichListView getWarrantyListView() {
        return warrantyListView;
    }

    public void setWarrantyTreeModel(ChildPropertyTreeModel warrantyTreeModel) {
        this.warrantyTreeModel = warrantyTreeModel;
    }

    public ChildPropertyTreeModel getWarrantyTreeModel() {
        return warrantyTreeModel;
    }

    public void setWarrantyUiCollection(ArrayList<UiField> warrantyUiCollection) {
        this.warrantyUiCollection = warrantyUiCollection;
    }

    public ArrayList<UiField> getWarrantyUiCollection() {
        return warrantyUiCollection;
    }

    public void setGroupDiscPolicy(String groupDiscPolicy) {
        this.groupDiscPolicy = groupDiscPolicy;
    }

    public String getGroupDiscPolicy() {
        return groupDiscPolicy;
    }

    public void groupDisclosureListener(RowDisclosureEvent rowDisclosureEvent) {
        //        RichListView listView = (RichListView)rowDisclosureEvent.getSource();
        //        groupDiscPolicy=null;
        //        ADFUtils.addPartialTarget(ADFUtils.findComponentInRoot("confPGL"));
    }

    public void setSysControllerListViewBinding(RichListView sysControllerListViewBinding) {
        this.sysControllerListViewBinding = sysControllerListViewBinding;
    }

    public RichListView getSysControllerListViewBinding() {
        return sysControllerListViewBinding;
    }

    public void setSysControllerTreeModel(ChildPropertyTreeModel sysControllerTreeModel) {
        this.sysControllerTreeModel = sysControllerTreeModel;
    }

    public ChildPropertyTreeModel getSysControllerTreeModel() {
        return sysControllerTreeModel;
    }

    public void setSysControllerUiCollection(ArrayList<UiField> sysControllerUiCollection) {
        this.sysControllerUiCollection = sysControllerUiCollection;
    }

    public ArrayList<UiField> getSysControllerUiCollection() {
        return sysControllerUiCollection;
    }

    public void setSysConSdiCollection(List<ShowDetailItemCollection> sysConSdiCollection) {
        this.sysConSdiCollection = sysConSdiCollection;
    }

    public List<ShowDetailItemCollection> getSysConSdiCollection() {
        return sysConSdiCollection;
    }

    public void handleInput(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
    }

    public void setAddSwToolsSdiCollection(List<ShowDetailItemCollection> addSwToolsSdiCollection) {
        this.addSwToolsSdiCollection = addSwToolsSdiCollection;
    }

    public List<ShowDetailItemCollection> getAddSwToolsSdiCollection() {
        return addSwToolsSdiCollection;
    }

    public void setRootAddSwTools(  ArrayList<UxTreeNode> rootAddSwTools) {
        this.rootAddSwTools = rootAddSwTools;
    }

    public   ArrayList<UxTreeNode> getRootAddSwTools() {
        return rootAddSwTools;
    }

    public void setAddToolsTreeModel(ChildPropertyTreeModel addToolsTreeModel) {
        this.addToolsTreeModel = addToolsTreeModel;
    }

    public ChildPropertyTreeModel getAddToolsTreeModel() {
        return addToolsTreeModel;
    }

    public void setCalDiagSdiCollection(List<ShowDetailItemCollection> calDiagSdiCollection) {
        this.calDiagSdiCollection = calDiagSdiCollection;
    }

    public List<ShowDetailItemCollection> getCalDiagSdiCollection() {
        return calDiagSdiCollection;
    }

    public void setCalDiagTreeModel(ChildPropertyTreeModel calDiagTreeModel) {
        this.calDiagTreeModel = calDiagTreeModel;
    }

    public ChildPropertyTreeModel getCalDiagTreeModel() {
        return calDiagTreeModel;
    }
}
