package xxatcust.oracle.apps.sudoku.viewmodel.ui.elements;

public class OptionUiNode {
    public OptionUiNode() {
        super();
    }
    
    ConfiguratorUiNode uiNode = null;

    public void setUiNode(ConfiguratorUiNode uiNode) {
        this.uiNode = uiNode;
    }

    public ConfiguratorUiNode getUiNode() {
        return uiNode;
    }
}
