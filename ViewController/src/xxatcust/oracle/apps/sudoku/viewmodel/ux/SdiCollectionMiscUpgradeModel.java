package xxatcust.oracle.apps.sudoku.viewmodel.ux;

import java.util.ArrayList;

public class SdiCollectionMiscUpgradeModel {
    private String index;
    private ArrayList<ShowDetailItemCollection> sdiList ;
    private String groupName ;
    public SdiCollectionMiscUpgradeModel() {
        super();
    }

    public SdiCollectionMiscUpgradeModel(String index,
                                         ArrayList<ShowDetailItemCollection> sdiList,
                                         String groupName) {
        super();
        this.index = index;
        this.sdiList = sdiList;
        this.groupName = groupName;
    }


    public void setIndex(String index) {
        this.index = index;
    }

    public String getIndex() {
        return index;
    }

    public void setSdiList(ArrayList<ShowDetailItemCollection> sdiList) {
        this.sdiList = sdiList;
    }

    public ArrayList<ShowDetailItemCollection> getSdiList() {
        return sdiList;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }
}
