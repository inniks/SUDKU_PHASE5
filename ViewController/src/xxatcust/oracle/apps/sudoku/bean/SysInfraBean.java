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

import org.apache.myfaces.trinidad.event.DisclosureEvent;
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
import xxatcust.oracle.apps.sudoku.viewmodel.ux.ShowDetailItemCollection;

public class SysInfraBean {
    private List<ShowDetailItemCollection> sdiCollection;
    private ChildPropertyTreeModel sysInfraTreeModel;
    private ArrayList<UxTreeNode> root;
    private RichPanelGroupLayout theadPanelGrp;
    private RichOutputText pageInitText;
    private RichListView sysInfraListView;
    private RichShowDetailItem sysInfraSdi;
    private RichInputText jsessionId;
    private RichPopup confirmPopup;
    private RichOutputFormatted warnText;
    private RichShowDetailHeader shwDetHdrBind;

    public SysInfraBean() {
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


    public RichOutputText getPageInitText() throws IOException,
                                                   JsonGenerationException,
                                                   JsonMappingException {
        System.out.println("Initializing Page.....");
        refreshView(null);
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

    public ArrayList<UiField> prepareDataModel(V93kQuote v93k,
                                               String uiGrpName,
                                               ArrayList<UiField> uiFieldCollection) {
        //Based on a refresh condition,prepare the data model for testhead,dut etc.
        //For each ui subgroup,One UIField object is to be created
        uiFieldCollection = new ArrayList<UiField>();
        UiField uiField = null;
        LinkedHashMap<String, ConfiguratorUiSubGroup> mapUiSubGrp = null;
        if (v93k != null && v93k.getUiRoot() != null &&
            v93k.getUiRoot().getSystemInfraGroup() != null) {
            LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap =
                v93k.getUiRoot().getSystemInfraGroup().getUiGroupMap();
            ConfiguratorUiGroup uiGroup = uiGroupMap.get(uiGrpName);
            if (uiGroup != null) {
                mapUiSubGrp = uiGroup.getSubGroups();
            }

            //Iterate for all subgroups here and place them in a list
            if (mapUiSubGrp != null && !mapUiSubGrp.isEmpty()) {
                Iterator it = mapUiSubGrp.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    ConfiguratorUiSubGroup subGroup =
                        (ConfiguratorUiSubGroup)pair.getValue();
                    List<ConfiguratorUiElement> listOfElements =
                        subGroup.getUiElements();
                    String subGrpName = subGroup.getSubGroupName();

                    List<ConfiguratorUiElement> listUiNodesBySubGrp =
                        new ArrayList<ConfiguratorUiElement>();
                    if (listOfElements != null && !listOfElements.isEmpty()) {
                        for (int i = 0; i < listOfElements.size(); i++) {
                            ConfiguratorUiElement element =
                                listOfElements.get(i);
                            listUiNodesBySubGrp.add(element);
                        }
                    }

                    if (listUiNodesBySubGrp != null &&
                        !listUiNodesBySubGrp.isEmpty()) {
                        uiField = new UiField(listUiNodesBySubGrp, subGrpName);

                        uiFieldCollection.add(uiField);
                    }
                }

            }

        }

        return uiFieldCollection;
    }

    public void refreshView(ActionEvent actionEvent) throws IOException,
                                                            JsonGenerationException,
                                                            JsonMappingException {

        sysInfraTreeModel = null;
        //Call configurator to load data
        V93kQuote v93k = (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        if (v93k==null) {
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


    public ChildPropertyTreeModel populateParentTreeModel(ChildPropertyTreeModel childModel) {
        V93kQuote v93k =
            (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        root = new ArrayList<UxTreeNode>();
        LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap = null;
        if (v93k != null && v93k.getUiRoot() != null &&
            v93k.getUiRoot().getSystemInfraGroup() != null) {
            SystemInfraGroup systemInfraGrp =
                v93k.getUiRoot().getSystemInfraGroup();
            String refColor =
                systemInfraGrp.isDisplayReferenceColor() ? SudokuUtils.REFERENCE_COLOR :
                null;
            String tarColor =
                systemInfraGrp.isDisplayTargetColor() ? SudokuUtils.TARGET_COLOR :
                null;
            UxTreeNode firstLevel =
                new UxTreeNode("sysInfra", "System Infrastructure", "Zero",
                               null, null, refColor,
                               tarColor); //For top level color, code later
            root.add(firstLevel);

            uiGroupMap =
                    v93k.getUiRoot().getSystemInfraGroup().getUiGroupMap();
            Iterator it = uiGroupMap.entrySet().iterator();
            int index = 1;
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                String uiGrpName = (String)pair.getKey();

                ConfiguratorUiGroup uiGrp =
                    (ConfiguratorUiGroup)pair.getValue();
                Boolean isReferenceColor = uiGrp.isDisplayReferenceColor();
                Boolean isTargetColor = uiGrp.isDisplayTargetColor();
                String nodeRefColor = null;
                String nodeTargetColor = null;
                if (isReferenceColor != null && isReferenceColor) {
                    nodeRefColor = SudokuUtils.REFERENCE_COLOR;
                }
                if (isTargetColor != null && isTargetColor) {
                    nodeTargetColor = SudokuUtils.TARGET_COLOR;
                }
                if (uiGrpName != null) {
                    UxTreeNode childNodeOfSysInfra =
                        new UxTreeNode(Integer.toString(index), uiGrpName,
                                       "First", null, null, nodeRefColor,
                                       nodeTargetColor);
                    firstLevel.addNodes(childNodeOfSysInfra);

                    index = index + 1;
                }
            }

        }
        sysInfraTreeModel = new ChildPropertyTreeModel(root, "childNodeList");
        return sysInfraTreeModel;
    }

    public void populateSubGroups(V93kQuote v93k) {
        LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap = null;
        LinkedHashMap<String, ConfiguratorUiGroup> mapUiGrp =
            new LinkedHashMap<String, ConfiguratorUiGroup>();
        List<String> listOfSubGrpNames = new ArrayList<String>();
        if (v93k != null && v93k.getUiRoot() != null &&
            v93k.getUiRoot().getSystemInfraGroup() != null) {

            uiGroupMap =
                    v93k.getUiRoot().getSystemInfraGroup().getUiGroupMap();


        }


        if (uiGroupMap != null && !uiGroupMap.isEmpty()) {
            Iterator it = uiGroupMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                mapUiGrp.put((String)pair.getKey(),
                             (ConfiguratorUiGroup)pair.getValue());
            }
        }
        if (mapUiGrp != null && !mapUiGrp.isEmpty()) {
            Iterator it = mapUiGrp.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                String key = (String)pair.getKey();
                listOfSubGrpNames.add(key);
            }

        }
        //Get Thead DAta
        ArrayList<ArrayList<UiField>> listofcollections =
            new ArrayList<ArrayList<UiField>>();
        ArrayList<UiField> uiFieldCollection = null;
        if (listOfSubGrpNames != null && !listOfSubGrpNames.isEmpty()) {
            for (String key : listOfSubGrpNames) {
                uiFieldCollection =
                        prepareDataModel(v93k, key, uiFieldCollection);
                listofcollections.add(uiFieldCollection);

            }
        }
        sdiCollection = new ArrayList<ShowDetailItemCollection>();
        if (listofcollections != null && !listofcollections.isEmpty()) {
            int counter = 1;
            for (int i = 0; i < listofcollections.size(); i++) {

                ShowDetailItemCollection sdi =
                    new ShowDetailItemCollection(Integer.toString(counter),
                                                 listofcollections.get(i),
                                                 listofcollections.get(i).get(0).getSelectedValue());
                sdiCollection.add(sdi);
                counter++;
            }
        }
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

        String identifier = null;
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
                    selectedNodeValueMap.put("identifier", identifier);
                    selectedNodeValueMap.put("czNodeName", czNodeName);
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
        
      
            sysInfraTreeModel = null;
            String jsonStr = JSONUtils.convertObjToJson(v93k);
            System.out.println("Json String build is" + jsonStr);
            //If config is live use this

            String jid = null;
            if (jsessionId != null) {
                jid = (String)jsessionId.getValue();
            }
            ADFUtils.setSessionScopeValue("jsessionId", jid);
            String responseJson =
                ConfiguratorUtils.callConfiguratorServlet(jsonStr);
            System.out.println("Response Json from Configurator : " +
                               responseJson);
            ObjectMapper mapper = new ObjectMapper();
            Object obj = mapper.readValue(responseJson, V93kQuote.class);
            v93k = (V93kQuote)obj;
            //else use this
            //v93k = (V93kQuote)convertJsonToObject(null);
            ADFUtils.setSessionScopeValue("parentObject", v93k);
            ADFUtils.setSessionScopeValue("refreshImport", "Y");
            if (sysInfraTreeModel == null) {
                populateParentTreeModel(sysInfraTreeModel);
            }
            populateSubGroups(v93k);
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
            UiSelection uiSelection = new UiSelection();
            uiSelection.setParentGroupName("System Infrastructure");
            uiSelection.setSubGroupName(uiSubGrpName);
            uiSelection.setValueSelected(selectedValue);
            uiSelection.setUniqueSessionId(uniqueSessionId);
            uiSelection.setCzNodeName(czNodeName);
            uiSelection.setIdentifier(identifier);
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
//            shwDetHdrBind.setDisclosed(false);
//            
//            shwDetHdrBind.setDisclosed(true);
//            ADFUtils.addPartialTarget(shwDetHdrBind);
            ADFUtils.addPartialTarget(ADFUtils.findComponentInRoot("confPGL"));
        }
    }

    public void setShwDetHdrBind(RichShowDetailHeader shwDetHdrBind) {
        this.shwDetHdrBind = shwDetHdrBind;
    }

    public RichShowDetailHeader getShwDetHdrBind() {
        return shwDetHdrBind;
    }
}
