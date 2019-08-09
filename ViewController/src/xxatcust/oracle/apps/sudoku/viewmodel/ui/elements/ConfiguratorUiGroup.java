package xxatcust.oracle.apps.sudoku.viewmodel.ui.elements;

import java.util.ArrayList;

import java.util.LinkedHashMap;

public class ConfiguratorUiGroup {
    public ConfiguratorUiGroup() {
        super();
    }
    String uiGroupName = ""; //Test Head Class
    //TreeMap<String, ArrayList<ConfiguratorUiElement>> uiSubGroupsToElementsMap = new TreeMap<String, ArrayList<ConfiguratorUiElement>>(); // this is for childeren of Test Head Class
    
    LinkedHashMap<String, ConfiguratorUiSubGroup> subGroups = new LinkedHashMap<String, ConfiguratorUiSubGroup>();
    
    boolean displayReferenceColor = false; // column H7
    
    boolean displayTargetColor = false; // column I7
    
    boolean required = false;
    
    ProductSpecfications productSpecs = null; //Product Description, Specifications, Drawings and Pictures

    public void setUiGroupName(String uiGroupName) {
        this.uiGroupName = uiGroupName;
    }

    public String getUiGroupName() {
        return uiGroupName;
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

    public void setProductSpecs(ProductSpecfications productSpecs) {
        this.productSpecs = productSpecs;
    }

    public ProductSpecfications getProductSpecs() {
        return productSpecs;
    }

    public void setSubGroups(LinkedHashMap<String, ConfiguratorUiSubGroup> subGroups) {
        this.subGroups = subGroups;
    }

    public LinkedHashMap<String, ConfiguratorUiSubGroup> getSubGroups() {
        return subGroups;
    }
    
    public void addSubGroup(String subGroupName, ConfiguratorUiSubGroup subGrp) {
        this.subGroups.put(subGroupName, subGrp);
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isRequired() {
        return required;
    }
}
