package xxatcust.oracle.apps.sudoku.util;

import java.util.Comparator;

import xxatcust.oracle.apps.sudoku.viewmodel.pojo.NodeCategory;

public class NodeComparator implements Comparator<NodeCategory> {
    public NodeComparator() {
        super();
    }


    public int compare(NodeCategory o1, NodeCategory o2) {
        int flag = 0 ;
        if(o1.getPrintGroupLevel()!=null && o2.getPrintGroupLevel()!=null){
//            Integer int1 = Integer.parseInt(o1.getPrintGroupLevel());
//            Integer int2 = Integer.parseInt(o2.getPrintGroupLevel());
//            flag = int1.compareTo(int2);
            flag =  o1.getPrintGroupLevel().compareTo(o2.getPrintGroupLevel()) ;
        }
        return flag;
    }
}
