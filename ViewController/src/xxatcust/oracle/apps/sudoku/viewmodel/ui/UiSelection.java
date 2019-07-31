package xxatcust.oracle.apps.sudoku.viewmodel.ui;

public class UiSelection {
    private String uniqueSessionId;
    private String parentGroupName;
    private String subGroupName ;
    private String valueSelected;
    private String uiType;
    private String czNodeName;
    private String identifier;
    private int referenceQuantity;
    private int targetQuantity;
    private String selectionState;// TRUE if value is selected, FALSE if value is de-selected.
    
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
}
