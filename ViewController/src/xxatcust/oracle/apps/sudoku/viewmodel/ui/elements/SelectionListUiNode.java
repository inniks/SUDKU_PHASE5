package xxatcust.oracle.apps.sudoku.viewmodel.ui.elements;

import java.util.ArrayList;


public class SelectionListUiNode {
    public SelectionListUiNode() {
        super();
    }
    
    ArrayList<ConfiguratorUiNode> uiNodeList = null;

    public void setUiNodeList(ArrayList<ConfiguratorUiNode> uiNodeList) {
        this.uiNodeList = uiNodeList;
    }

    public ArrayList<ConfiguratorUiNode> getUiNodeList() {
        return uiNodeList;
    }
}
