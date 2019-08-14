package xxatcust.oracle.apps.sudoku.viewmodel.ui.groups;

import java.util.LinkedHashMap;

import xxatcust.oracle.apps.sudoku.viewmodel.ui.elements.ConfiguratorUiGroup;

public class DiagAndCalibEquipmentGroup {
    public DiagAndCalibEquipmentGroup() {
        super();
    }
    
    LinkedHashMap<String, ConfiguratorUiGroup> uiGroupMap = new LinkedHashMap<String, ConfiguratorUiGroup>();  
    
    boolean displayReferenceColor = false; // column H7
    
    boolean displayTargetColor = false; // column I7

    boolean required = false;

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
}
