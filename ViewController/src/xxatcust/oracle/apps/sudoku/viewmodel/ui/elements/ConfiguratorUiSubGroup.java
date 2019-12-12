package xxatcust.oracle.apps.sudoku.viewmodel.ui.elements;

import java.util.ArrayList;

public class ConfiguratorUiSubGroup {
    public ConfiguratorUiSubGroup() {
        super();
    }
    
    String subGroupName = null;
    
    String subGroupIdentifier = null;
    
   // String subGroupSecondName = null;
    
    boolean required = false;
    
    ArrayList<ConfiguratorUiElement> uiElements = new ArrayList<ConfiguratorUiElement>();

    public void setSubGroupName(String subGroupName) {
        this.subGroupName = subGroupName;
    }

    public String getSubGroupName() {
        return subGroupName;
    }

    public void setUiElements(ArrayList<ConfiguratorUiElement> uiElements) {
        this.uiElements = uiElements;
    }

    public ArrayList<ConfiguratorUiElement> getUiElements() {
        return uiElements;
    }
    
    public void addUiElement(ConfiguratorUiElement uiElement) {
        uiElements.add(uiElement);
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isRequired() {
        return required;
    }

    public void setSubGroupIdentifier(String subGroupIdentifier) {
        this.subGroupIdentifier = subGroupIdentifier;
    }

    public String getSubGroupIdentifier() {
        return subGroupIdentifier;
    }
}
