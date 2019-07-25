package xxatcust.oracle.apps.sudoku.viewmodel.ui.elements;


public class NumericUiNode {
    public NumericUiNode() {
        super();
    }
    
    ConfiguratorUiNode configuratorUiNode = null;

    public void setConfiguratorUiNode(ConfiguratorUiNode configuratorUiNode) {
        this.configuratorUiNode = configuratorUiNode;
    }

    public ConfiguratorUiNode getConfiguratorUiNode() {
        return configuratorUiNode;
    }
}
