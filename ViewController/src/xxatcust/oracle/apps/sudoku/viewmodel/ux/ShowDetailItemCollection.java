package xxatcust.oracle.apps.sudoku.viewmodel.ux;

import java.util.ArrayList;

public class ShowDetailItemCollection {
    private String index;
    private ArrayList<UiField> uiFieldCollection ;
    private String subGrpName ;
    public ShowDetailItemCollection() {
        super();
    }

    public ShowDetailItemCollection(String index,
                                    ArrayList<UiField> uiFieldCollection,String subGrpName) {
        super();
        this.index = index;
        this.uiFieldCollection = uiFieldCollection;
        this.subGrpName = subGrpName;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getIndex() {
        return index;
    }

    public void setUiFieldCollection(ArrayList<UiField> uiFieldCollection) {
        this.uiFieldCollection = uiFieldCollection;
    }

    public ArrayList<UiField> getUiFieldCollection() {
        return uiFieldCollection;
    }

    public void setSubGrpName(String subGrpName) {
        this.subGrpName = subGrpName;
    }

    public String getSubGrpName() {
        return subGrpName;
    }
}
