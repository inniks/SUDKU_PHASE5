package xxatcust.oracle.apps.sudoku.viewmodel.ui.elements;

public class ConfiguratorUiNode {
    public ConfiguratorUiNode() {
        super();
    }
    
    String uiNodeName = "";
    int index = -1;
    String uiNodeType = null;
    
    boolean isSelected = false;
    
     boolean displayReferenceColor = false;
     
     boolean displayTargetColor = false;

    boolean disableNode = false;
    
    boolean readOnly = false;
    
    String identifier = null;
    
     String czNodeName = "";
     int czNodeType = -1;

    public void setUiNodeName(String uiNodeName) {
        this.uiNodeName = uiNodeName;
    }

    public String getUiNodeName() {
        return uiNodeName;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setUiNodeType(String uiNodeType) {
        this.uiNodeType = uiNodeType;
    }

    public String getUiNodeType() {
        return uiNodeType;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean isIsSelected() {
        return isSelected;
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

    public void setCzNodeName(String czNodeName) {
        this.czNodeName = czNodeName;
    }

    public String getCzNodeName() {
        return czNodeName;
    }

    public void setCzNodeType(int czNodeType) {
        this.czNodeType = czNodeType;
    }

    public int getCzNodeType() {
        return czNodeType;
    }

    public void setDisableNode(boolean disableNode) {
        this.disableNode = disableNode;
    }

    public boolean isDisableNode() {
        return disableNode;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isReadOnly() {
        return readOnly;
    }
}
