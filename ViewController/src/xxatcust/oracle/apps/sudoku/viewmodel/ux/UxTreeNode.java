package xxatcust.oracle.apps.sudoku.viewmodel.ux;

import java.util.ArrayList;
import java.util.List;


public class UxTreeNode {
    private String CodeName;
    private String nodeName;
    private String levelName ;
    private List<UxTablePojo> childTableList ;
    private String nodeRefColor;
    private String nodeTargetColor;
    List<UxTreeNode> childNodeList =new ArrayList<UxTreeNode>();

    public UxTreeNode() {
        super();
    }

    public UxTreeNode(String codeName,String nodeName, String levelName,List<UxTablePojo> childTableList, List<UxTreeNode> childNodeList,String nodeRefColor,String nodeTargetColor) {
        //super();
        this.CodeName = codeName;
        this.nodeName = nodeName;
        this.levelName = levelName ;
        childTableList = this.childTableList ;
        childNodeList = new ArrayList<UxTreeNode>();
        this.nodeRefColor = nodeRefColor ;
        this.nodeTargetColor = nodeTargetColor;
    }

    public void setChildNodeList(List<UxTreeNode> childNodeList) {
        this.childNodeList = childNodeList;
    }

    public List<UxTreeNode> getChildNodeList() {
        return childNodeList;
    }

    public void addNodes(UxTreeNode child) {
        childNodeList.add(child);
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setChildTableList(List<UxTablePojo> childTableList) {
        this.childTableList = childTableList;
    }

    public List<UxTablePojo> getChildTableList() {
        return childTableList;
    }


    public void setCodeName(String CodeName) {
        this.CodeName = CodeName;
    }

    public String getCodeName() {
        return CodeName;
    }


    public void setNodeRefColor(String nodeRefColor) {
        this.nodeRefColor = nodeRefColor;
    }

    public String getNodeRefColor() {
        return nodeRefColor;
    }

    public void setNodeTargetColor(String nodeTargetColor) {
        this.nodeTargetColor = nodeTargetColor;
    }

    public String getNodeTargetColor() {
        return nodeTargetColor;
    }
}
