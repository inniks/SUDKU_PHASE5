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
import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.AdditionalSoftToolsGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.DigitalResourceGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ux.ShowDetailItemCollection;
import xxatcust.oracle.apps.sudoku.viewmodel.ux.UiField;
import xxatcust.oracle.apps.sudoku.viewmodel.ux.UxTreeNode;

public class DigitalResourcesBean {
    public DigitalResourcesBean() {
        super();
    }

    public static ArrayList<UiField> prepareDigitalResourcesModel(V93kQuote v93k,
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
            v93k.getUiRoot().getDigitalResourceGroup() != null) {
            LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap =
                v93k.getUiRoot().getDigitalResourceGroup().getUiGroupMap();
            
            groupName =
                    v93k.getUiRoot().getDigitalResourceGroup().getGroupDisplayName();
            ConfiguratorUiGroup uiGroup = uiGroupMap.get(uiGrpName);
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

    public static ChildPropertyTreeModel populateDigitalResParentTreeModel(ChildPropertyTreeModel digitalChildModel,
                                                                           ArrayList<UxTreeNode> rootDigital) {
        V93kQuote v93k =
            (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        rootDigital = new ArrayList<UxTreeNode>();
        LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap = null;
        if (v93k != null && v93k.getUiRoot() != null &&
            v93k.getUiRoot().getDigitalResourceGroup() != null) {
            
            DigitalResourceGroup digitalGrp =
                v93k.getUiRoot().getDigitalResourceGroup();
           
            String groupName = digitalGrp.getGroupDisplayName();
            String refColor =
                digitalGrp.isDisplayReferenceColor() ? SudokuUtils.REFERENCE_COLOR :
                null;
            String tarColor =
                digitalGrp.isDisplayTargetColor() ? SudokuUtils.TARGET_COLOR :
                null;
            String grpRequiredFlag = digitalGrp.isRequired() ? "Y" : "N";
            UxTreeNode firstLevel =
                new UxTreeNode("addSwTools", groupName, "Zero", null, null,
                               refColor, tarColor,
                               grpRequiredFlag); //For top level color, code later
            rootDigital.add(firstLevel);

            uiGroupMap =
                    v93k.getUiRoot().getDigitalResourceGroup().getUiGroupMap();
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
                String requiredFlag = "N";
                if (isReferenceColor != null && isReferenceColor) {
                    nodeRefColor = SudokuUtils.REFERENCE_COLOR;
                }
                if (isTargetColor != null && isTargetColor) {
                    nodeTargetColor = SudokuUtils.TARGET_COLOR;
                }
                if (isRequired != null && isRequired) {
                    requiredFlag = "Y";
                }
                if (uiGrpName != null) {
                    UxTreeNode childrenOfDigital =
                        new UxTreeNode(Integer.toString(index), uiGrpName,
                                       "First", null, null, nodeRefColor,
                                       nodeTargetColor, requiredFlag);
                    firstLevel.addNodes(childrenOfDigital);

                    index = index + 1;
                }
            }

        }
        digitalChildModel =
                new ChildPropertyTreeModel(rootDigital, "childNodeList");
        return digitalChildModel;
    }

    public static ArrayList<ShowDetailItemCollection> populateDigitalResourcesSubGroups(V93kQuote v93k,
                                                                                        List<ShowDetailItemCollection> sdiCollection) {
        int licenseRowIndex = 0;
        LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap = null;
        LinkedHashMap<String, ConfiguratorUiGroup> mapUiGrp =
            new LinkedHashMap<String, ConfiguratorUiGroup>();
        List<String> listOfSubGrpNames = new ArrayList<String>();
        if (v93k != null && v93k.getUiRoot() != null &&
            v93k.getUiRoot().getDigitalResourceGroup() != null) {

            uiGroupMap =
                    v93k.getUiRoot().getDigitalResourceGroup().getUiGroupMap();


        }


        if (uiGroupMap != null && !uiGroupMap.isEmpty()) {
            Iterator it = uiGroupMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                mapUiGrp.put((String)pair.getKey(),
                             (ConfiguratorUiGroup)pair.getValue());
                licenseRowIndex = findLicenceRowIndex((ConfiguratorUiGroup)pair.getValue());
                System.out.println("License Row Index for "+pair.getKey()+" Is "+licenseRowIndex);
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
                        prepareDigitalResourcesModel(v93k, key, uiFieldCollection);
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

    private static int findLicenceRowIndex(ConfiguratorUiGroup uiGroup) {
        int licenseRowIndex=0;
        if (uiGroup != null) {
            LinkedHashMap<String, ConfiguratorUiSubGroup> subGrpMap =
                uiGroup.getSubGroups();
            if(subGrpMap!=null && !subGrpMap.isEmpty()){
                Iterator it = subGrpMap.entrySet().iterator();
                int index = 1;
                while (it.hasNext()) {
                    index++;
                    Map.Entry pair = (Map.Entry)it.next();
                    String uiGrpName = (String)pair.getKey();
                    ConfiguratorUiSubGroup uiSubGroup = (ConfiguratorUiSubGroup)pair.getValue();
                    if(uiSubGroup!=null && uiSubGroup.getSubGroupIdentifier()!=null && uiSubGroup.getSubGroupIdentifier().equalsIgnoreCase("Licensed Features")){
                        licenseRowIndex = index ;
                        break;
                    }
                }
            }
        }
        return licenseRowIndex;
    }
}
