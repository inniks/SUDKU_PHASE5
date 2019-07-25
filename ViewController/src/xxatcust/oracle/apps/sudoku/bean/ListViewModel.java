package xxatcust.oracle.apps.sudoku.bean;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;

public class ListViewModel {
    private ChildPropertyTreeModel treeModel ;
    private String lineNum ;
    public ListViewModel() {
        super();
    }

    public ListViewModel(ChildPropertyTreeModel treeModel, String lineNum) {
        super();
        this.treeModel = treeModel;
        this.lineNum = lineNum;
    }

    public void setTreeModel(ChildPropertyTreeModel treeModel) {
        this.treeModel = treeModel;
    }

    public ChildPropertyTreeModel getTreeModel() {
        return treeModel;
    }

    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }

    public String getLineNum() {
        return lineNum;
    }
}
