package xxatcust.oracle.apps.sudoku.viewmodel.ux;

import java.util.ArrayList;

public class ShowDetailItemCollection {
    private String index;
    private ArrayList<UiField> uiFieldCollection ;
    private String subGrpName ;
    private String superSubGrpName;
    private ArrayList<ArrayList<UiField>> blockOfGrps ;
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

    public ShowDetailItemCollection(String index,
                                    ArrayList<UiField> uiFieldCollection,
                                    String subGrpName,
                                    ArrayList<ArrayList<UiField>> blockOfGrps) {
        super();
        this.index = index;
        this.uiFieldCollection = uiFieldCollection;
        this.subGrpName = subGrpName;
        this.blockOfGrps = blockOfGrps;
    }

    public ShowDetailItemCollection(String index,
                                    ArrayList<UiField> uiFieldCollection,
                                    String subGrpName,
                                    String superSubGrpName) {
        super();
        this.index = index;
        this.uiFieldCollection = uiFieldCollection;
        this.subGrpName = subGrpName;
        this.superSubGrpName = superSubGrpName;
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

    public void setBlockOfGrps(ArrayList<ArrayList<UiField>> blockOfGrps) {
        this.blockOfGrps = blockOfGrps;
    }

    public ArrayList<ArrayList<UiField>> getBlockOfGrps() {
        return blockOfGrps;
    }

    public void setSuperSubGrpName(String superSubGrpName) {
        this.superSubGrpName = superSubGrpName;
    }

    public String getSuperSubGrpName() {
        return superSubGrpName;
    }
}
