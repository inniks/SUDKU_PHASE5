package xxatcust.oracle.apps.sudoku.viewmodel.ui.groups;

import java.util.LinkedHashMap;

import xxatcust.oracle.apps.sudoku.viewmodel.ui.elements.ConfiguratorUiGroup;


//Docking, DUT Board, and Probe Card Accessories
public class DockingGroup {
    public DockingGroup() {
        super();
    }
    
    LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap = new LinkedHashMap<String, ConfiguratorUiGroup>();  
    
    boolean displayReferenceColor = false; // column H7
    
    boolean displayTargetColor = false; // column I7

    boolean required = false;

    String groupDisplayName = null;

    public void addUiGroup(String uiGroupName, ConfiguratorUiGroup uiGroup) {
        this.uiGroupMap.put(uiGroupName, uiGroup);
    }

    public void setUiGroupMap(LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap) {
        this.uiGroupMap = uiGroupMap;
    }

    public LinkedHashMap<String, ConfiguratorUiGroup> getUiGroupMap() {
        return uiGroupMap;
    }

    public void setDisplayReferenceColor(boolean displayReferenceColor) {
        this.displayReferenceColor = displayReferenceColor;
    }

    public boolean isDisplayReferenceColor() {
        return displayReferenceColor;
    }

    public void setDisplayTargetColor(boolean displayTargetColor) {
        this.displayTargetColor = displayTargetColor;
    }

    public boolean isDisplayTargetColor() {
        return displayTargetColor;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isRequired() {
        return required;
    }

    public void setGroupDisplayName(String groupDisplayName) {
        this.groupDisplayName = groupDisplayName;
    }

    public String getGroupDisplayName() {
        return groupDisplayName;
    }
}
