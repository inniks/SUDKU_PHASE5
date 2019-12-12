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
     
    int quantity = -1;
    int referenceQuantiy = -1;
    int targetQuantity = -1;
    String czModelName = null;  
    
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
        
//        if(czNodeName !=null && czNodeName.contains("\"")) {
//            czNodeName = czNodeName.replace("\"","\\\"");
//        }
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

    public void setReferenceQuantiy(int referenceQuantiy) {
        this.referenceQuantiy = referenceQuantiy;
    }

    public int getReferenceQuantiy() {
        return referenceQuantiy;
    }

    public void setTargetQuantity(int targetQuantity) {
        this.targetQuantity = targetQuantity;
    }

    public int getTargetQuantity() {
        return targetQuantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCzModelName(String czModelName) {
        this.czModelName = czModelName;
    }

    public String getCzModelName() {
        return czModelName;
    }
}