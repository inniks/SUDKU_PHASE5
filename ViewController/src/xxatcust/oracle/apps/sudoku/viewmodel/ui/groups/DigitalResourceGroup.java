package xxatcust.oracle.apps.sudoku.viewmodel.ui.groups;

import java.util.ArrayList;

import java.util.LinkedHashMap;

import xxatcust.oracle.apps.sudoku.viewmodel.ui.elements.ConfiguratorUiGroup;

public class DigitalResourceGroup {
    public DigitalResourceGroup() {
        super();
    }
    LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap = new LinkedHashMap<String, ConfiguratorUiGroup>();  // this is tea Infrastructure
    //test head group, DUT interface, maniputalor

    boolean displayReferenceColor = false; // column H7
    
    boolean displayTargetColor = false; // column I7


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
}
