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

import javax.faces.component.UIComponent;

import javax.faces.component.UIForm;
import javax.faces.event.ActionEvent;

import javax.servlet.ServletException;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.data.RichListView;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.component.rich.output.RichOutputFormatted;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
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
    private List<ShowDetailItemCollection> sdiCollection ;
    private ChildPropertyTreeModel sysInfraTreeModel;
    private ArrayList<UxTreeNode> root;
    private RichPanelGroupLayout theadPanelGrp;
    private RichOutputFormatted uiBind1;
    private RichOutputText pageInitText;
    private RichPanelGroupLayout dutPanelGrp;
    private RichPanelGroupLayout utilityPanelGrp;
    private ArrayList<UiField> theadUiFieldCollection;
    private ArrayList<UiField> dutUiFieldCollection;
    private ArrayList<UiField> utilityUiFieldCollection;
    private ArrayList<UiField> manipulatorUiFieldCollection;
    private ArrayList<UiField> coolingUnitFieldCollection;
    private ArrayList<UiField> miscUiFieldCollection;
    private RichListView sysInfraListView;
    private RichShowDetailItem sysInfraSdi;
    private RichInputText jsessionId;

    public SysInfraBean() {
        super();
    }

    public ChildPropertyTreeModel getSysInfraTreeModel() {
        return sysInfraTreeModel;
    }

    private List<UxTablePojo> addTableData(String parent,
                                           List<UxTablePojo> list) {
        list.add(new UxTablePojo("Compact Test Head", "sA-Class", "A-Class",
                                 "C-Class", "S-Class", "L-Class", null));
        return list;
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

    public void theadSelection(ActionEvent actionEvent) {
        UIComponent component = actionEvent.getComponent();
        //UIComponent parent = component.getParent();
        List<UIComponent> children = component.getChildren();
        System.out.println("Children " + children);
        //System.out.println("Value "+ADFUtils.evaluateEL("#{feach.uiField7}"));
        for (UIComponent comp : children) {
            if (comp instanceof RichOutputFormatted) {
                RichOutputFormatted of = (RichOutputFormatted)comp;
                System.out.println("Value returned " + of.getValue());
            }
        }
    }


    public void setTheadPanelGrp(RichPanelGroupLayout theadPanelGrp) {
        this.theadPanelGrp = theadPanelGrp;
    }

    public RichPanelGroupLayout getTheadPanelGrp() {
        return theadPanelGrp;
    }

    public void setUiBind1(RichOutputFormatted uiBind1) {
        this.uiBind1 = uiBind1;
    }

    public RichOutputFormatted getUiBind1() {
        return uiBind1;
    }

    public RichOutputText getPageInitText() {
        System.out.println("Initializing Page.....");
        return pageInitText;
    }

    public void setPageInitText(RichOutputText pageInitText) {
        this.pageInitText = pageInitText;
    }

    public void setDutPanelGrp(RichPanelGroupLayout dutPanelGrp) {
        this.dutPanelGrp = dutPanelGrp;
    }

    public RichPanelGroupLayout getDutPanelGrp() {
        return dutPanelGrp;
    }

    public void setUtilityPanelGrp(RichPanelGroupLayout utilityPanelGrp) {
        this.utilityPanelGrp = utilityPanelGrp;
    }

    public RichPanelGroupLayout getUtilityPanelGrp() {
        return utilityPanelGrp;
    }


    public void utilityLineSelection(ActionEvent actionEvent) {
        UIComponent component = actionEvent.getComponent();
        //UIComponent parent = component.getParent();
        List<UIComponent> children = component.getChildren();
        System.out.println("Children " + children);
        //System.out.println("Value "+ADFUtils.evaluateEL("#{feach.uiField7}"));
        for (UIComponent comp : children) {
            if (comp instanceof RichOutputFormatted) {
                RichOutputFormatted of = (RichOutputFormatted)comp;
                System.out.println("Value returned " + of.getValue());
            }
        }
    }

    public void setTheadUiFieldCollection(ArrayList<UiField> theadUiFieldCollection) {
        this.theadUiFieldCollection = theadUiFieldCollection;
    }

    public ArrayList<UiField> getTheadUiFieldCollection() {
        return theadUiFieldCollection;
    }

    public void setDutUiFieldCollection(ArrayList<UiField> dutUiFieldCollection) {
        this.dutUiFieldCollection = dutUiFieldCollection;
    }

    public ArrayList<UiField> getDutUiFieldCollection() {
        return dutUiFieldCollection;
    }

    public void setUtilityUiFieldCollection(ArrayList<UiField> utilityUiFieldCollection) {
        this.utilityUiFieldCollection = utilityUiFieldCollection;
    }

    public ArrayList<UiField> getUtilityUiFieldCollection() {
        return utilityUiFieldCollection;
    }

    public void dutLineSelection(ActionEvent actionEvent) {
        UIComponent component = actionEvent.getComponent();
        //UIComponent parent = component.getParent();
        List<UIComponent> children = component.getChildren();
        System.out.println("Children " + children);
        //System.out.println("Value "+ADFUtils.evaluateEL("#{feach.uiField7}"));
        for (UIComponent comp : children) {
            if (comp instanceof RichOutputFormatted) {
                RichOutputFormatted of = (RichOutputFormatted)comp;
                System.out.println("Value returned " + of.getValue());
            }
        }
        if (sysInfraSdi != null) {
            sysInfraSdi.setDisclosed(false);
            //sysInfraSdi.setDisclosed(true);
        }
    }

    public void setManipulatorUiFieldCollection(ArrayList<UiField> manipulatorUiFieldCollection) {
        this.manipulatorUiFieldCollection = manipulatorUiFieldCollection;
    }

    public ArrayList<UiField> getManipulatorUiFieldCollection() {
        return manipulatorUiFieldCollection;
    }

    public void manipulatorLineSelection(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void setCoolingUnitFieldCollection(ArrayList<UiField> coolingUnitFieldCollection) {
        this.coolingUnitFieldCollection = coolingUnitFieldCollection;
    }

    public ArrayList<UiField> getCoolingUnitFieldCollection() {
        return coolingUnitFieldCollection;
    }

    public void coolingLineSelection(ActionEvent actionEvent) {
        // Add event code here...
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
        //Call configurator to load data
        V93kQuote v93k = new V93kQuote();
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
        String userId = (String)ADFUtils.getSessionScopeValue("UserId") ==
                                 null ? "0" :
                                 (String)ADFUtils.getSessionScopeValue("UserId");
        String timestamp =  Long.toString( System.currentTimeMillis());
        String uniqueSessionId = userId.concat(timestamp);
        ADFUtils.setSessionScopeValue("uniqueSessionId", uniqueSessionId);
        UiSelection uiSelection = new UiSelection();
        uiSelection.setUniqueSessionId(uniqueSessionId);
        v93k.setSessionDetails(sessionDetails);
        v93k.setUiSelection(uiSelection);
        v93k.setInputParams(inputParam);
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

    public void setMiscUiFieldCollection(ArrayList<UiField> miscUiFieldCollection) {
        this.miscUiFieldCollection = miscUiFieldCollection;
    }

    public ArrayList<UiField> getMiscUiFieldCollection() {
        return miscUiFieldCollection;
    }

    public ChildPropertyTreeModel populateParentTreeModel(ChildPropertyTreeModel childModel) {
        V93kQuote v93k =
            (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        root = new ArrayList<UxTreeNode>();
        LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap = null;
        if (v93k != null && v93k.getUiRoot() != null &&
            v93k.getUiRoot().getSystemInfraGroup() != null) {
            UxTreeNode firstLevel =
                new UxTreeNode("sysInfra", "System Infrastructure", "Zero",
                               null, null,null,null);//For top level color, code later
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
                if(isReferenceColor!=null && isReferenceColor){
                    nodeRefColor = SudokuUtils.REFERENCE_COLOR;
                }
                if(isTargetColor!=null && isTargetColor){
                    nodeTargetColor = SudokuUtils.TARGET_COLOR;
                }
                if (uiGrpName != null) {
                    UxTreeNode childNodeOfSysInfra =
                        new UxTreeNode(Integer.toString(index), uiGrpName,
                                       "First", null, null,nodeRefColor,nodeTargetColor);
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
            for(int i=0;i<listofcollections.size();i++){
                
                ShowDetailItemCollection sdi = new ShowDetailItemCollection(Integer.toString(counter),listofcollections.get(i),listofcollections.get(i).get(0).getSelectedValue());
                sdiCollection.add(sdi);
                counter++;
            }
        }
    }

    public void handleNodeSelection(ActionEvent actionEvent) throws IOException,
                                                                    JsonGenerationException,
                                                                    JsonMappingException {
        System.out.println("Evaluated EL " +
                           ADFUtils.evaluateEL("#{node.nodeName}"));
        System.out.println(ADFUtils.evaluateEL("#{theadIter.selectedValue}"));
        UIComponent component = actionEvent.getComponent();
        List<UIComponent> children = component.getChildren();
        String selectedValue = null;
        String uiSubGrpName = (String)ADFUtils.evaluateEL("#{node.nodeName}");
        String czNodeName = null;//(String)ADFUtils.evaluateEL("#{theadIter.uiField1}");
        String uniqueSessionId =
            (String)ADFUtils.getSessionScopeValue("uniqueSessionId");
        V93kQuote v93k = new V93kQuote();
        String identifier = null;
            //(V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        for (UIComponent comp : children) {
            if (comp instanceof RichOutputFormatted) {
                RichOutputFormatted of = (RichOutputFormatted)comp;
                selectedValue = (String)of.getValue();
                //czNodeName = of.getShortDesc() ;
            }
            if(comp instanceof RichInputText){
                RichInputText it = (RichInputText)comp;
                if(it!=null){
                    identifier = it.getShortDesc();
                    czNodeName = (String)it.getValue();
                }
            }
        }
        if (v93k != null) {
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
            buildConfiguratorUI(v93k);
        }
    }
    
    public void buildConfiguratorUI(V93kQuote v93k) throws IOException,
                                                           JsonGenerationException,
                                                           JsonMappingException {
        String jsonStr = JSONUtils.convertObjToJson(v93k);
        System.out.println("Json String build is" +
                           jsonStr);
        //If config is live use this
        String jid = (String)jsessionId.getValue();
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
       // ADFUtils.setSessionScopeValue("refreshImport", "Y");
        if (sysInfraTreeModel == null) {
            populateParentTreeModel(sysInfraTreeModel);
        }
        populateSubGroups(v93k);
    }

    public void callEBSServlet(ActionEvent actionEvent) throws ServletException,
                                                               IOException {
        ConfiguratorUtils.callEBSServlet(null);
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
}
