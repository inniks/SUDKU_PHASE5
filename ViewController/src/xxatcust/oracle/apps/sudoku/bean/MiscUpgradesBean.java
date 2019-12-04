package xxatcust.oracle.apps.sudoku.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import java.util.Set;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;

import xxatcust.oracle.apps.sudoku.util.ADFUtils;
import xxatcust.oracle.apps.sudoku.util.SudokuUtils;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.V93kQuote;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.elements.ConfiguratorUiElement;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.elements.ConfiguratorUiGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.elements.ConfiguratorUiSubGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.MiscUpgradeGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ux.SdiCollectionMiscUpgradeModel;
import xxatcust.oracle.apps.sudoku.viewmodel.ux.ShowDetailItemCollection;
import xxatcust.oracle.apps.sudoku.viewmodel.ux.UiField;
import xxatcust.oracle.apps.sudoku.viewmodel.ux.UxTreeNode;

public class MiscUpgradesBean {
    public MiscUpgradesBean() {
        super();
    }

    public static ArrayList<UiField> prepareMiscUpgradesDataModel(V93kQuote v93k,
                                                                  String uiGrpName,
                                                                  ArrayList<UiField> uiFieldCollection) {
        //Based on a refresh condition,prepare the data model for testhead,dut etc.
        //For each ui subgroup,One UIField object is to be created

        uiFieldCollection = new ArrayList<UiField>();
        UiField uiField = null;
        String requiredFlag = "N";
        String groupName = null;
        LinkedHashMap<String, ConfiguratorUiSubGroup> mapUiSubGrp = null;
        if (v93k != null && v93k.getUiRoot() != null &&
            v93k.getUiRoot().getMiscUpgradeGroup() != null) {
            LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap =
                v93k.getUiRoot().getMiscUpgradeGroup().getUiGroupMap();
            ConfiguratorUiGroup uiGroup = uiGroupMap.get(uiGrpName);
            groupName =
                    v93k.getUiRoot().getMiscUpgradeGroup().getGroupDisplayName();
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

    public static ChildPropertyTreeModel populateMiscUpgradesParentModel(ChildPropertyTreeModel miscUpgTreeModel,
                                                                         ArrayList<UxTreeNode> rootMiscUpg) {

        V93kQuote v93k =
            (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        rootMiscUpg = new ArrayList<UxTreeNode>();
        LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap = null;
        if (v93k != null && v93k.getUiRoot() != null &&
            v93k.getUiRoot().getMiscUpgradeGroup() != null) {
            MiscUpgradeGroup miscUpgGrp =
                v93k.getUiRoot().getMiscUpgradeGroup();
            if (miscUpgGrp != null) {
                String groupName = miscUpgGrp.getGroupDisplayName();
                String refColor =
                    miscUpgGrp.isDisplayReferenceColor() ? SudokuUtils.REFERENCE_COLOR :
                    null;
                String tarColor =
                    miscUpgGrp.isDisplayTargetColor() ? SudokuUtils.TARGET_COLOR :
                    null;
                String requiredFlag = miscUpgGrp.isRequired() ? "Y" : "N";
                UxTreeNode firstLevel =
                    new UxTreeNode("miscUpg", groupName, "Zero", null, null,
                                   refColor, tarColor,
                                   requiredFlag); //For top level color, code later
                rootMiscUpg.add(firstLevel);
                uiGroupMap = miscUpgGrp.getUiGroupMap();
                Iterator it = uiGroupMap.entrySet().iterator();
                LinkedHashMap<String, ConfiguratorUiSubGroup> mapOfSubGrps =
                    new LinkedHashMap<String, ConfiguratorUiSubGroup>();
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
                    String requiredGrpFlag = null;
                    if (isReferenceColor != null && isReferenceColor) {
                        nodeRefColor = SudokuUtils.REFERENCE_COLOR;
                    }
                    if (isTargetColor != null && isTargetColor) {
                        nodeTargetColor = SudokuUtils.TARGET_COLOR;
                    }
                    if (isRequired != null && isRequired) {
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
        miscUpgTreeModel =
                new ChildPropertyTreeModel(rootMiscUpg, "childNodeList");
        return miscUpgTreeModel;
    }

    public static ArrayList<SdiCollectionMiscUpgradeModel> populateMiscUpgradesSubGroups(V93kQuote v93k,
                                                                                         ArrayList<SdiCollectionMiscUpgradeModel> sdiCollection) {
        sdiCollection = new ArrayList<SdiCollectionMiscUpgradeModel>();
        LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap = null;
        LinkedHashMap<String, ConfiguratorUiGroup> mapUiGrp =
            new LinkedHashMap<String, ConfiguratorUiGroup>();
        List<String> listOfSubGrpNames = new ArrayList<String>();
        if (v93k != null && v93k.getUiRoot() != null &&
            v93k.getUiRoot().getMiscUpgradeGroup() != null) {

            uiGroupMap =
                    v93k.getUiRoot().getMiscUpgradeGroup().getUiGroupMap();


        }

        LinkedHashMap<String, ConfiguratorUiSubGroup> uiSuperSubGrpMap =
            new LinkedHashMap();

        if (uiGroupMap != null && !uiGroupMap.isEmpty()) {
            Iterator it = uiGroupMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                mapUiGrp.put((String)pair.getKey(),
                             (ConfiguratorUiGroup)pair.getValue());
            }
        }
        //For each configurator Ui grp , we have to form one instance of SdiCollectionMiscUpgradeModel
        int indexer = 1;
        if (mapUiGrp != null && !mapUiGrp.isEmpty()) {
            Iterator iter = mapUiGrp.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry pair = (Map.Entry)iter.next();
                ConfiguratorUiGroup grp =
                    (ConfiguratorUiGroup)pair.getValue(); // this will be digital , dps etc

                SdiCollectionMiscUpgradeModel sdiInstance =
                    createSuperSubGroups(v93k, grp, indexer);
                indexer++;
                sdiCollection.add(sdiInstance);
            }
            if (mapUiGrp != null && !mapUiGrp.isEmpty()) {
                Iterator it = mapUiGrp.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    String key = (String)pair.getKey();
                    listOfSubGrpNames.add(key);
                }

            }
        }
        return sdiCollection;
    }

    public static SdiCollectionMiscUpgradeModel createSuperSubGroups(V93kQuote v93k,
                                                                     ConfiguratorUiGroup uiGroup,
                                                                     int indexer) {
        String groupName = null;
        if (v93k != null && v93k.getUiRoot() != null &&
            v93k.getUiRoot().getMiscUpgradeGroup() != null) {
            groupName =
                    v93k.getUiRoot().getMiscUpgradeGroup().getGroupDisplayName();
        }
        SdiCollectionMiscUpgradeModel miscUpgSdi = null;
        List<String> allSuperSubGroupList = new ArrayList<String>();
        Set setOfSuperSubGrpNames = new LinkedHashSet();
        if (uiGroup != null) {
            LinkedHashMap<String, ConfiguratorUiSubGroup> subGroup =
                uiGroup.getSubGroups();

            if (subGroup != null) {

                // each sub grp will have an identifier like PS1600 , form a list of those
                Iterator sgIter = subGroup.entrySet().iterator();
                while (sgIter.hasNext()) {
                    Map.Entry subGrppair = (Map.Entry)sgIter.next();
                    ConfiguratorUiSubGroup subGrp =
                        (ConfiguratorUiSubGroup)subGrppair.getValue();
                    if(subGrp!=null && subGrp.getSubGroupIdentifier()==null){
                        subGrp.setSubGroupIdentifier("TEST");
                        //subGrp.setSubGroupIdentifier(groupName);
                    }
                    if (subGrp != null &&
                        subGrp.getSubGroupIdentifier() != null &&
                        !subGrp.getSubGroupIdentifier().equals("")) {
                        allSuperSubGroupList.add(subGrp.getSubGroupIdentifier());
                    }
                    setOfSuperSubGrpNames.addAll(allSuperSubGroupList);
                    allSuperSubGroupList.clear();
                    allSuperSubGroupList.addAll(setOfSuperSubGrpNames);
                }

            }
        }
        LinkedHashMap<String, List> testMap = new LinkedHashMap();
        for (String uiSubGrpName : allSuperSubGroupList) {

            LinkedHashMap<String, ConfiguratorUiSubGroup> subGroup =
                uiGroup.getSubGroups();
            ArrayList<ArrayList<UiField>> listofcollections =
                new ArrayList<ArrayList<UiField>>();
            ArrayList testList = new ArrayList();
            Iterator sgIter = subGroup.entrySet().iterator();
            int index = 1;

            while (sgIter.hasNext()) {
                Map.Entry subGrppair = (Map.Entry)sgIter.next();
                ConfiguratorUiSubGroup subGrp =
                    (ConfiguratorUiSubGroup)subGrppair.getValue();

                if (subGrp != null && subGrp.getSubGroupIdentifier() != null &&
                    subGrp.getSubGroupIdentifier().equalsIgnoreCase(uiSubGrpName)) {

                    //for ex:PS1600
                    ArrayList<UiField> uiFieldCollection = null;
                    uiFieldCollection =
                            prepareMiscUpgradesSubGrpDataModel(subGrp, groupName,
                                                               uiFieldCollection,
                                                               index);
                    listofcollections.add(uiFieldCollection);
                    testList.add(uiFieldCollection);
                    index++;

                }
            }
            testMap.put(uiSubGrpName, testList);
        }
        ArrayList<ShowDetailItemCollection> sdiList =
            new ArrayList<ShowDetailItemCollection>();
        LinkedHashMap<String, ShowDetailItemCollection> sdiMap =
            new LinkedHashMap<String, ShowDetailItemCollection>();
        String subGrpName = null;
        if (testMap != null && !testMap.isEmpty()) {
            int counter = 1;
            Iterator it = testMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                String key = (String)pair.getKey();
                subGrpName = key;
                ArrayList<ArrayList<UiField>> val =
                    (ArrayList<ArrayList<UiField>>)pair.getValue();
                ArrayList<UiField> test1 = new ArrayList<UiField>();
                if (val != null && val.size() > 0) {
                    for (int i = 0; i < val.size(); i++) {
                        test1.add(val.get(i).get(0));
                    }
                    System.out.println("Index being passed is "+counter);
                    ShowDetailItemCollection showDetItem =
                        new ShowDetailItemCollection(Integer.toString(counter),
                                                     test1, key, key);

                    sdiList.add(showDetItem);
                    sdiMap.put(key, showDetItem);
                    counter++;

                }

            }
            miscUpgSdi =
                    new SdiCollectionMiscUpgradeModel(Integer.toString(indexer),
                                                      sdiList, "X");
        }

        return miscUpgSdi;
    }

    public static ArrayList<UiField> prepareMiscUpgradesSubGrpDataModel(ConfiguratorUiSubGroup uiSbGrp,
                                                                        String uiGrpName,
                                                                        ArrayList<UiField> uiFieldCollection,
                                                                        int index) {
        //Based on a refresh condition,prepare the data model for testhead,dut etc.
        //For each ui subgroup,One UIField object is to be created

        uiFieldCollection = new ArrayList<UiField>();
        UiField uiField = null;
        String requiredFlag = "N";
        //int ind = 0;

        LinkedHashMap<String, ConfiguratorUiSubGroup> mapUiSubGrp = null;
        if (uiSbGrp != null) {
            //Iterate for all subgroups here and place them in a list
            List<ConfiguratorUiElement> listOfElements =
                uiSbGrp.getUiElements();
            String subGrpName = uiSbGrp.getSubGroupName();
            requiredFlag = uiSbGrp.isRequired() ? "Y" : "N";
            List<ConfiguratorUiElement> listUiNodesBySubGrp =
                new ArrayList<ConfiguratorUiElement>();
            if (listOfElements != null && !listOfElements.isEmpty()) {
                for (int i = 0; i < listOfElements.size(); i++) {
                    ConfiguratorUiElement element = listOfElements.get(i);
                    listUiNodesBySubGrp.add(element);
                }
            }

            if (listUiNodesBySubGrp != null &&
                !listUiNodesBySubGrp.isEmpty()) {
                uiField =
                        new UiField(listUiNodesBySubGrp, subGrpName, requiredFlag,
                                    uiGrpName, Integer.toString(index));
                index++;
                uiFieldCollection.add(uiField);
            }


        }

        return uiFieldCollection;
    }


}
