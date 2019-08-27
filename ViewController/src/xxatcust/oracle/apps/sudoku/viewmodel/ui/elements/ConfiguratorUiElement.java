package xxatcust.oracle.apps.sudoku.viewmodel.ui.elements;

import java.util.ArrayList;


public class ConfiguratorUiElement {
    public ConfiguratorUiElement() {
        super();
    }
    
    
    String uiElementName = ""; //display name for the component
    //this component can be either LOV or numeric node
    
    int uiElementType = -1; // "LOV"  or "NUM" or "TEXT" or "OPTION"
    /*
     *   1 -> NUMERIC
     *   2 -> TEXT
     *   3 -> OPTION/SELECTION
     *   4 -> LISTOFVALUES
     */
    
    boolean required = false;
    
//    SelectionListUiNode selectionListUiNode = null;
//    
//    NumericUiNode numericUiNode = null;
//    
//    OptionUiNode optionUiNode = null;
//    
//    BooleanFeatureUiNode booleanUiNode = null;
    
    //ConfiguratorUiElement uiElement = null
    
    ArrayList<ConfiguratorUiNode> configUiNodes = new ArrayList<ConfiguratorUiNode>();
    
    boolean isReadOnly = false;

    int columnIndex = -1;

    public void setUiElementName(String uiComponentName) {
        this.uiElementName = uiComponentName;
    }

    public String getUiElementName() {
        return uiElementName;
    }

//    public void setUiElementType(String uiComponentType) {
//        this.uiElementType = uiComponentType;
//    }
//
//    public String getUiElementType() {
//        return uiElementType;
//    }

//    public void setSelectionListUiNode(SelectionListUiNode selectionListUiNode) {
//        this.selectionListUiNode = selectionListUiNode;
//    }
//
//    public SelectionListUiNode getSelectionListUiNode() {
//        return selectionListUiNode;
//    }
//
//    public void setNumericUiNode(NumericUiNode numericUiNode) {
//        this.numericUiNode = numericUiNode;
//    }
//
//    public NumericUiNode getNumericUiNode() {
//        return numericUiNode;
//    }

    public void setIsReadOnly(boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
    }

    public boolean isIsReadOnly() {
        return isReadOnly;
    }

//    public void setOptionUiNode(OptionUiNode optionUiNode) {
//        this.optionUiNode = optionUiNode;
//    }
//
//    public OptionUiNode getOptionUiNode() {
//        return optionUiNode;
//    }
//
//    public void setBooleanUiNode(BooleanFeatureUiNode booleanUiNode) {
//        this.booleanUiNode = booleanUiNode;
//    }
//
//    public BooleanFeatureUiNode getBooleanUiNode() {
//        return booleanUiNode;
//    }


    public void setUiElementType(int uiElementType) {
        this.uiElementType = uiElementType;
    }

    public int getUiElementType() {
        return uiElementType;
    }

    public void setConfigUiNodes(ArrayList<ConfiguratorUiNode> configUiNodes) {
        this.configUiNodes = configUiNodes;
    }

    public ArrayList<ConfiguratorUiNode> getConfigUiNodes() {
        return configUiNodes;
    }

    public void addConfigUiNode(ConfiguratorUiNode configUiNode) {
        if(configUiNodes == null)
            configUiNodes = new ArrayList<ConfiguratorUiNode>();
        
            configUiNodes.add(configUiNode);
        
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isRequired() {
        return required;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }
}
