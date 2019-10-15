package xxatcust.oracle.apps.sudoku.viewmodel.ui;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class UiSelection {
    private String uniqueSessionId;
    private String parentGroupName;
    private String subGroupName ;
    private String uiComponent = "";
    private String uiNodeName = "";
    private String valueSelected;
    private String uiType;
    @JsonRawValue
    private String czNodeName;
    private String identifier;
    private int quantity;
    private int referenceQuantity;
    private int targetQuantity;
    private String selectionState;// TRUE if value is selected, FALSE if value is de-selected.
    String czModelName = null;
    
    public UiSelection() {
        super();
    }

    public void setUniqueSessionId(String uniqueSessionId) {
        this.uniqueSessionId = uniqueSessionId;
    }

    public String getUniqueSessionId() {
        return uniqueSessionId;
    }

    public void setParentGroupName(String parentGroupName) {
        this.parentGroupName = parentGroupName;
    }

    public String getParentGroupName() {
        return parentGroupName;
    }

    public void setSubGroupName(String subGroupName) {
        this.subGroupName = subGroupName;
    }

    public String getSubGroupName() {
        return subGroupName;
    }

    public void setValueSelected(String valueSelected) {
        this.valueSelected = valueSelected;
    }

    public String getValueSelected() {
        return valueSelected;
    }

    public void setUiType(String uiType) {
        this.uiType = uiType;
    }

    public String getUiType() {
        return uiType;
    }

    public void setCzNodeName(String czNodeName) {
        this.czNodeName = czNodeName;
    }

    public String getCzNodeName() {
        if(czNodeName!=null && czNodeName.equalsIgnoreCase("\"")){
            System.out.println("CZNODE NAME SELECTED IS "+czNodeName);
        }
        return czNodeName;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setReferenceQuantity(int referenceQuantity) {
        this.referenceQuantity = referenceQuantity;
    }

    public int getReferenceQuantity() {
        return referenceQuantity;
    }

    public void setTargetQuantity(int targetQuantity) {
        this.targetQuantity = targetQuantity;
    }

    public int getTargetQuantity() {
        return targetQuantity;
    }

    public void setSelectionState(String selectionState) {
        this.selectionState = selectionState;
    }

    public String getSelectionState() {
        return selectionState;
    }

    public void setUiComponent(String uiComponent) {
        this.uiComponent = uiComponent;
    }

    public String getUiComponent() {
        return uiComponent;
    }

    public void setUiNodeName(String uiNodeName) {
        this.uiNodeName = uiNodeName;
    }

    public String getUiNodeName() {
        return uiNodeName;
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
