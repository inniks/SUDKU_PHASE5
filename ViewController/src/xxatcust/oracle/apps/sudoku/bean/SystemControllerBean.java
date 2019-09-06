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
import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.SystemControllerGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.WtyTrainingSAndSGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ux.ShowDetailItemCollection;
import xxatcust.oracle.apps.sudoku.viewmodel.ux.UiField;
import xxatcust.oracle.apps.sudoku.viewmodel.ux.UxTreeNode;

public class SystemControllerBean {
    public SystemControllerBean() {
        super();
    }
    
    public static ArrayList<UiField> prepareSysControllerDataModel(V93kQuote v93k,
                                                              String uiGrpName,
                                                              ArrayList<UiField> uiFieldCollection) {
        //Based on a refresh condition,prepare the data model for testhead,dut etc.
        //For each ui subgroup,One UIField object is to be created
        uiFieldCollection = new ArrayList<UiField>();
        UiField uiField = null;
        String requiredFlag = "N";
        LinkedHashMap<String, ConfiguratorUiSubGroup> mapUiSubGrp = null;
        if (v93k != null && v93k.getUiRoot() != null &&
            v93k.getUiRoot().getSystemControllerGroup() != null) {
            LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap =
                v93k.getUiRoot().getSystemControllerGroup().getUiGroupMap();
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
                    requiredFlag = subGroup.isRequired()?"Y":"N";
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
                        uiField = new UiField(listUiNodesBySubGrp, subGrpName,requiredFlag,"System Controller",null);

                        uiFieldCollection.add(uiField);
                    }
                }

            }

        }

        return uiFieldCollection;
    }

    public static ChildPropertyTreeModel populateSysControllerParentModel(ChildPropertyTreeModel sysControllerTreeModel,
                                                                     ArrayList<UxTreeNode> rootSysController) {
        V93kQuote v93k =
            (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        rootSysController = new ArrayList<UxTreeNode>();
        LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap = null;
        if (v93k != null && v93k.getUiRoot() != null &&
            v93k.getUiRoot().getSystemControllerGroup() != null) {
            SystemControllerGroup sysControllerGrp =
                v93k.getUiRoot().getSystemControllerGroup();
            if (sysControllerGrp != null) {
                String refColor =
                    sysControllerGrp.isDisplayReferenceColor() ? SudokuUtils.REFERENCE_COLOR :
                    null;
                String tarColor =
                    sysControllerGrp.isDisplayTargetColor() ? SudokuUtils.TARGET_COLOR :
                    null;
                UxTreeNode firstLevel =
                    new UxTreeNode("warranty", "System Controller",
                                   "Zero", null, null, refColor,
                                   tarColor,null); //For top level color, code later
                rootSysController.add(firstLevel);
                uiGroupMap = sysControllerGrp.getUiGroupMap();
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
                        UxTreeNode childrenOfSysController =
                            new UxTreeNode(Integer.toString(index), uiGrpName,
                                           "First", null, null, nodeRefColor,
                                           nodeTargetColor,null);
                        firstLevel.addNodes(childrenOfSysController);
                        index = index + 1;
                    }
                }
            }
        }
        sysControllerTreeModel =
                new ChildPropertyTreeModel(rootSysController, "childNodeList");
        return sysControllerTreeModel;
    }

    public static ArrayList<ShowDetailItemCollection> populateSysContSubGroups(V93kQuote v93k,List<ShowDetailItemCollection> sdiCollection) {
        LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap = null;
        LinkedHashMap<String, ConfiguratorUiGroup> mapUiGrp =
            new LinkedHashMap<String, ConfiguratorUiGroup>();
        List<String> listOfSubGrpNames = new ArrayList<String>();
        if (v93k != null && v93k.getUiRoot() != null &&
            v93k.getUiRoot().getSystemControllerGroup() != null) {

            uiGroupMap =
                    v93k.getUiRoot().getSystemControllerGroup().getUiGroupMap();


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
                uiFieldCollection = prepareSysControllerDataModel(v93k, key, uiFieldCollection);
                        //prepareSysInfraDataModel(v93k, key, uiFieldCollection);
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
