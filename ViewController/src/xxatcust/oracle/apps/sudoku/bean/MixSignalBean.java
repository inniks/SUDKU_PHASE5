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
import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.MixedSignalGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ux.ShowDetailItemCollection;
import xxatcust.oracle.apps.sudoku.viewmodel.ux.UiField;
import xxatcust.oracle.apps.sudoku.viewmodel.ux.UxTreeNode;

public class MixSignalBean {
    public MixSignalBean() {
        super();
    }
    public static ArrayList<UiField> prepareMixSignalDataModel(V93kQuote v93k,
                                                             String uiGrpName,
                                                             ArrayList<UiField> uiFieldCollection) {
        //Based on a refresh condition,prepare the data model for testhead,dut etc.
        //For each ui subgroup,One UIField object is to be created

        uiFieldCollection = new ArrayList<UiField>();
        UiField uiField = null;
        String requiredFlag = "N";
        String groupName = null ;
        LinkedHashMap<String, ConfiguratorUiSubGroup> mapUiSubGrp = null;
        if (v93k != null && v93k.getUiRoot() != null &&
            v93k.getUiRoot().getMixedSignalGroup() != null) {
            LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap =
                v93k.getUiRoot().getMixedSignalGroup().getUiGroupMap();
            ConfiguratorUiGroup uiGroup = uiGroupMap.get(uiGrpName);
            groupName = v93k.getUiRoot().getMixedSignalGroup().getGroupDisplayName();
            if (uiGroup != null) {
                mapUiSubGrp = uiGroup.getSubGroups();
            }

            //Iterate for all subgroups here and place them in a list
            int index = 1;
            if (mapUiSubGrp != null && !mapUiSubGrp.isEmpty()) {
                Iterator it = mapUiSubGrp.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    ConfiguratorUiSubGroup subGroup =
                        (ConfiguratorUiSubGroup)pair.getValue();
                    List<ConfiguratorUiElement> listOfElements =
                        subGroup.getUiElements();
                    String subGrpName = subGroup.getSubGroupName();
                    requiredFlag = subGroup.isRequired() ? "Y" : "N";
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
                        uiField =
                                new UiField(listUiNodesBySubGrp, subGrpName, requiredFlag,
                                            groupName,
                                            Integer.toString(index));
                        index++;
                        uiFieldCollection.add(uiField);
                    }
                }

            }

        }

        return uiFieldCollection;
    }

    public static ChildPropertyTreeModel populateMixSignalParentModel(ChildPropertyTreeModel mixSignalTreeModel,
                                                                    ArrayList<UxTreeNode> rootMixSignal) {
        V93kQuote v93k =
            (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        rootMixSignal = new ArrayList<UxTreeNode>();
        LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap = null;
        if (v93k != null && v93k.getUiRoot() != null &&
            v93k.getUiRoot().getDCScaleDPSGroup() != null) {
            MixedSignalGroup mixSignalGrp = v93k.getUiRoot().getMixedSignalGroup();
            if (mixSignalGrp != null) {
                String groupName = mixSignalGrp.getGroupDisplayName() ;
                String refColor =
                    mixSignalGrp.isDisplayReferenceColor() ? SudokuUtils.REFERENCE_COLOR :
                    null;
                String tarColor =
                    mixSignalGrp.isDisplayTargetColor() ? SudokuUtils.TARGET_COLOR :
                    null;
                String requiredFlag = mixSignalGrp.isRequired() ? "Y":"N";
                UxTreeNode firstLevel =
                    new UxTreeNode("mixsignal", groupName,
                                   "Zero", null, null, refColor, tarColor,
                                   requiredFlag); //For top level color, code later
                rootMixSignal.add(firstLevel);
                uiGroupMap = mixSignalGrp.getUiGroupMap();
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
                    String requiredGrpFlag = null ;
                    if (isReferenceColor != null && isReferenceColor) {
                        nodeRefColor = SudokuUtils.REFERENCE_COLOR;
                    }
                    if (isTargetColor != null && isTargetColor) {
                        nodeTargetColor = SudokuUtils.TARGET_COLOR;
                    }
                    if(isRequired!=null && isRequired){
                        requiredGrpFlag = "Y";
                    }
                    if (uiGrpName != null) {
                        UxTreeNode childrenOfMixSignal =
                            new UxTreeNode(Integer.toString(index), uiGrpName,
                                           "First", null, null, nodeRefColor,
                                           nodeTargetColor, requiredGrpFlag);
                        firstLevel.addNodes(childrenOfMixSignal);
                        index = index + 1;
                    }
                }
            }
        }
        mixSignalTreeModel =
                new ChildPropertyTreeModel(rootMixSignal, "childNodeList");
        return mixSignalTreeModel;
    }

    public static ArrayList<ShowDetailItemCollection> populateMixSignalSubGroups(V93kQuote v93k,
                                                                               List<ShowDetailItemCollection> sdiCollection) {
        LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap = null;
        LinkedHashMap<String, ConfiguratorUiGroup> mapUiGrp =
            new LinkedHashMap<String, ConfiguratorUiGroup>();
        List<String> listOfSubGrpNames = new ArrayList<String>();
        if (v93k != null && v93k.getUiRoot() != null &&
            v93k.getUiRoot().getMixedSignalGroup() != null) {

            uiGroupMap = v93k.getUiRoot().getMixedSignalGroup().getUiGroupMap();


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
        LinkedHashMap testMap = new LinkedHashMap();
        if (listOfSubGrpNames != null && !listOfSubGrpNames.isEmpty()) {
            for (String key : listOfSubGrpNames) {
                uiFieldCollection =
                        prepareMixSignalDataModel(v93k, key, uiFieldCollection);
                //prepareSysInfraDataModel(v93k, key, uiFieldCollection);
                listofcollections.add(uiFieldCollection);
                testMap.put(key, uiFieldCollection);

            }
        }
        sdiCollection = new ArrayList<ShowDetailItemCollection>();
        if(testMap!=null && !testMap.isEmpty()){
            int counter = 1;
            Iterator it = testMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                String key = (String)pair.getKey();
                ArrayList<UiField> val = (ArrayList<UiField>)pair.getValue();
                ShowDetailItemCollection sdi =
                    new ShowDetailItemCollection(Integer.toString(counter),
                                                 val,
                                                 key);
                sdiCollection.add(sdi);
                counter++;
            }
        }        
        return (ArrayList<ShowDetailItemCollection>)sdiCollection;
    }
}
