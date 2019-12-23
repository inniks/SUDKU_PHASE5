package xxatcust.oracle.apps.sudoku.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;

import xxatcust.oracle.apps.sudoku.util.ADFUtils;
import xxatcust.oracle.apps.sudoku.util.SudokuUtils;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.V93kQuote;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.elements.ConfiguratorUiElement;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.elements.ConfiguratorUiGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.elements.ConfiguratorUiSubGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.DiagAndCalibEquipmentGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.SystemInfraGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ux.ShowDetailItemCollection;
import xxatcust.oracle.apps.sudoku.viewmodel.ux.UiField;
import xxatcust.oracle.apps.sudoku.viewmodel.ux.UxTreeNode;

public class DiagCalEquipmentBean {
    public DiagCalEquipmentBean() {
        super();
    }
    public static ArrayList<UiField> prepareCalDiagDataModel(V93kQuote v93k,
                                               String uiGrpName,
                                               ArrayList<UiField> uiFieldCollection) {
        //Based on a refresh condition,prepare the data model for testhead,dut etc.
        //For each ui subgroup,One UIField object is to be created
        uiFieldCollection = new ArrayList<UiField>();
        UiField uiField = null;
        String requiredFlag = "N" ;
        String groupName = null;
        LinkedHashMap<String, ConfiguratorUiSubGroup> mapUiSubGrp = null;
        if (v93k != null && v93k.getUiRoot() != null &&
            v93k.getUiRoot().getDiagAndCalibEquipmentGroup() != null) {
          groupName=  v93k.getUiRoot().getDiagAndCalibEquipmentGroup().getGroupDisplayName();
            LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap =
                v93k.getUiRoot().getDiagAndCalibEquipmentGroup().getUiGroupMap();
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
                    String secondName = subGroup.getSubGroupSecondName();
                    requiredFlag = subGroup.isRequired()? "Y" :"N" ;
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
                        uiField = new UiField(listUiNodesBySubGrp, subGrpName,requiredFlag,groupName,null,secondName,0);

                        uiFieldCollection.add(uiField);
                    }
                }

            }

        }

        return uiFieldCollection;
    }
    
    public static ChildPropertyTreeModel populateCalDiagParentTreeModel(ChildPropertyTreeModel calDiagTreeModel,ArrayList<UxTreeNode> rootCalDiag) {
        V93kQuote v93k =
            (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        rootCalDiag = new ArrayList<UxTreeNode>();
        LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap = null;
        if (v93k != null && v93k.getUiRoot() != null &&
            v93k.getUiRoot().getDiagAndCalibEquipmentGroup() != null) {
            DiagAndCalibEquipmentGroup calDiagGrp =
                v93k.getUiRoot().getDiagAndCalibEquipmentGroup();
            String groupName = calDiagGrp.getGroupDisplayName();
            String refColor =
                calDiagGrp.isDisplayReferenceColor() ? SudokuUtils.REFERENCE_COLOR :
                null;
            String tarColor =
                calDiagGrp.isDisplayTargetColor() ? SudokuUtils.TARGET_COLOR :
                null;
            String grpRequiredFlag = calDiagGrp.isRequired() ? "Y" : "N" ;
            UxTreeNode firstLevel =
                new UxTreeNode("sysInfra", groupName, "Zero",
                               null, null, refColor,
                               tarColor,grpRequiredFlag); //For top level color, code later
            rootCalDiag.add(firstLevel);

            uiGroupMap =
                    v93k.getUiRoot().getDiagAndCalibEquipmentGroup().getUiGroupMap();
            Iterator it = uiGroupMap.entrySet().iterator();
            int index = 1;
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                String uiGrpName = (String)pair.getKey();

                ConfiguratorUiGroup uiGrp =
                    (ConfiguratorUiGroup)pair.getValue();
                Boolean isReferenceColor = uiGrp.isDisplayReferenceColor();
                Boolean isTargetColor = uiGrp.isDisplayTargetColor();
                Boolean isRequired = uiGrp.isRequired();
                String nodeRefColor = null;
                String nodeTargetColor = null;
                String requiredFlag = "N" ;
                if (isReferenceColor != null && isReferenceColor) {
                    nodeRefColor = SudokuUtils.REFERENCE_COLOR;
                }
                if (isTargetColor != null && isTargetColor) {
                    nodeTargetColor = SudokuUtils.TARGET_COLOR;
                }
                if(isRequired!=null && isRequired){
                    requiredFlag = "Y";
                }
                if (uiGrpName != null) {
                    UxTreeNode childrenOfCalDiag =
                        new UxTreeNode(Integer.toString(index), uiGrpName,
                                       "First", null, null, nodeRefColor,
                                       nodeTargetColor,requiredFlag);
                    firstLevel.addNodes(childrenOfCalDiag);

                    index = index + 1;
                }
            }

        }
        calDiagTreeModel = new ChildPropertyTreeModel(rootCalDiag, "childNodeList");
        return calDiagTreeModel;
    }
    public static ArrayList<ShowDetailItemCollection> populateCalDiagSubGroups(V93kQuote v93k,List<ShowDetailItemCollection> sdiCollection) {
        LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap = null;
        LinkedHashMap<String, ConfiguratorUiGroup> mapUiGrp =
            new LinkedHashMap<String, ConfiguratorUiGroup>();
        List<String> listOfSubGrpNames = new ArrayList<String>();
        if (v93k != null && v93k.getUiRoot() != null &&
            v93k.getUiRoot().getDiagAndCalibEquipmentGroup() != null) {
            
            uiGroupMap =
                    v93k.getUiRoot().getDiagAndCalibEquipmentGroup().getUiGroupMap();


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
                        prepareCalDiagDataModel(v93k, key, uiFieldCollection);
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
        return (ArrayList<ShowDetailItemCollection>)sdiCollection;
    }

}
