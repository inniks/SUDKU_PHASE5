package xxatcust.oracle.apps.sudoku.viewmodel.ux;

import java.util.List;

import oracle.adf.view.rich.component.rich.output.RichOutputFormatted;

import xxatcust.oracle.apps.sudoku.util.SudokuUtils;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.elements.ConfiguratorUiElement;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.elements.ConfiguratorUiNode;


public class UiField {
    private String lineNum;
    private String uiField1;
    private String uiField2;
    private String uiField3;
    private String uiField4;
    private String uiField5;
    private String uiField6;
    private String uiField7;
    private String uiField1_color;
    private String uiField2_color;
    private String uiField3_color;
    private String uiField4_color;
    private String uiField5_color;
    private String uiField6_color;
    private String uiField7_color;
    private String uiField1_code;
    private String uiField2_code;
    private String uiField3_code;
    private String uiField4_code;
    private String uiField5_code;
    private String uiField6_code;
    private String uiField7_code;
    private String inputValue;
    private String selectedValue;
    private String czNodeName1;
    private String czNodeName2;
    private String czNodeName3;
    private String czNodeName4;
    private String czNodeName5;
    private String czNodeName6;
    private String czNodeName7;
    private Boolean uiField1_dis;
    private Boolean uiField2_dis;
    private Boolean uiField3_dis;
    private Boolean uiField4_dis;
    private Boolean uiField5_dis;
    private Boolean uiField6_dis;
    private Boolean uiField7_dis;
    private Boolean uiField1_readOnly;
    private Boolean uiField2_readOnly;
    private Boolean uiField3_readOnly;
    private Boolean uiField4_readOnly;
    private Boolean uiField5_readOnly;
    private Boolean uiField6_readOnly;
    private Boolean uiField7_readOnly;
    private String uiField1_identifier;
    private String uiField2_identifier;
    private String uiField3_identifier;
    private String uiField4_identifier;
    private String uiField5_identifier;
    private String uiField6_identifier;
    private String uiField7_identifier;
    private String uiField1_bgColor;
    private String uiField2_bgColor;
    private String uiField3_bgColor;
    private String uiField4_bgColor;
    private String uiField5_bgColor;
    private String uiField6_bgColor;
    private String uiField7_bgColor;
    private List<ConfiguratorUiElement> listOfNodes;

    public UiField() {


        super();
    }

    public UiField(String lineNum, String uiField1, String uiField2,
                   String uiField3, String uiField4, String uiField5,
                   String uiField6, String uiField7, String uiField1_color,
                   String uiField2_color, String uiField3_color,
                   String uiField4_color, String uiField5_color,
                   String uiField6_color, String uiField7_color) {
        super();
        this.lineNum = lineNum;
        this.uiField1 = uiField1;
        this.uiField2 = uiField2;
        this.uiField3 = uiField3;
        this.uiField4 = uiField4;
        this.uiField5 = uiField5;
        this.uiField6 = uiField6;
        this.uiField7 = uiField7;
        this.uiField1_color = uiField1_color;
        this.uiField2_color = uiField2_color;
        this.uiField3_color = uiField3_color;
        this.uiField4_color = uiField4_color;
        this.uiField5_color = uiField5_color;
        this.uiField6_color = uiField6_color;
        this.uiField7_color = uiField7_color;
    }

    public UiField(List<ConfiguratorUiElement> listOfNodes,
                   String subGrpName) {
        super();
        this.listOfNodes = listOfNodes;
        if (listOfNodes != null && !listOfNodes.isEmpty()) {
            ConfiguratorUiNode node = null;
            for (int i = 0; i < listOfNodes.size(); i++) {
                switch (i) {
                case 0:
                    if (listOfNodes.get(i) != null &&
                        listOfNodes.get(i).getConfigUiNodes() != null &&
                        !listOfNodes.get(i).getConfigUiNodes().isEmpty()) {
                        node = listOfNodes.get(i).getConfigUiNodes().get(0);
                        uiField1 = node.getUiNodeName();
                        selectedValue = subGrpName;
                        czNodeName1 = node.getCzNodeName();
                        if (node.isDisplayReferenceColor())
                            uiField1_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField1_color = SudokuUtils.TARGET_COLOR;
                        
                        
                        uiField1_dis = node.isDisableNode();
                        uiField1_readOnly = node.isReadOnly();
                        uiField1_identifier = node.getIdentifier();
                        if (uiField1_dis) {
                            uiField1_bgColor = "color:Silver;";
                        }
                        if (uiField1_readOnly) {
                            uiField1_bgColor = "color:#d9b3ff;";
                        }
                        
                        if(uiField1_dis){
                            uiField1_color="Yellow";
                        }
                        if(uiField1_color!=null){
                            uiField1_bgColor = "color:InfoBackground";
                        }
                    }


                    break;

                case 1:
                    if (listOfNodes.get(i) != null &&
                        listOfNodes.get(i).getConfigUiNodes() != null &&
                        !listOfNodes.get(i).getConfigUiNodes().isEmpty()) {
                        node = listOfNodes.get(i).getConfigUiNodes().get(0);
                        uiField2 = node.getUiNodeName();
                        selectedValue = subGrpName;
                        czNodeName2 = node.getCzNodeName();
                        if (node.isDisplayReferenceColor())
                            uiField2_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField2_color = SudokuUtils.TARGET_COLOR;
                        uiField2_dis = node.isDisableNode();
                        uiField2_readOnly = node.isReadOnly();
                        uiField2_identifier = node.getIdentifier();
                        if (uiField2_dis) {
                            uiField2_bgColor = "color:Silver;";
                        }
                        if (uiField2_readOnly) {
                            uiField2_bgColor = "color:#d9b3ff;";
                        }
                        if(uiField2_color!=null){
                            uiField2_bgColor = "color:InfoBackground";
                        }
                        if(uiField2_dis){
                            uiField2_color="Yellow";
                        }
                    }
                    break;
                case 2:
                    if (listOfNodes.get(i) != null &&
                        listOfNodes.get(i).getConfigUiNodes() != null &&
                        !listOfNodes.get(i).getConfigUiNodes().isEmpty()) {
                        node = listOfNodes.get(i).getConfigUiNodes().get(0);
                        uiField3 = node.getUiNodeName();
                        selectedValue = subGrpName;
                        czNodeName3 = node.getCzNodeName();
                        if (node.isDisplayReferenceColor())
                            uiField3_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField3_color = SudokuUtils.TARGET_COLOR;
                        uiField3_dis = node.isDisableNode();
                        uiField3_readOnly = node.isReadOnly();
                        uiField3_identifier = node.getIdentifier();
                        if (uiField3_dis) {
                            uiField3_bgColor = "color:Silver;";
                        }
                        if (uiField3_readOnly) {
                            uiField3_bgColor = "color:#d9b3ff;";
                        }
                        if(uiField3_color!=null){
                            uiField3_bgColor = "color:InfoBackground";
                        }
                        if(uiField3_dis){
                            uiField3_color="Yellow";
                        }
                    }
                    break;
                case 3:
                    if (listOfNodes.get(i) != null &&
                        listOfNodes.get(i).getConfigUiNodes() != null &&
                        !listOfNodes.get(i).getConfigUiNodes().isEmpty()) {
                        node = listOfNodes.get(i).getConfigUiNodes().get(0);
                        uiField4 = node.getUiNodeName();
                        selectedValue = subGrpName;
                        czNodeName4 = node.getCzNodeName();
                        if (node.isDisplayReferenceColor())
                            uiField4_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField4_color = SudokuUtils.TARGET_COLOR;
                        uiField4_dis = node.isDisableNode();
                        uiField4_readOnly = node.isReadOnly();
                        uiField4_identifier = node.getIdentifier();
                        if (uiField4_dis) {
                            uiField4_bgColor = "color:Silver;";
                        }
                        if (uiField4_readOnly) {
                            uiField4_bgColor = "color:#d9b3ff;";
                        }
                        if(uiField4_color!=null){
                            uiField4_bgColor = "color:InfoBackground";
                        }
                        if(uiField4_dis){
                            uiField4_color="Yellow";
                        }
                    }
                    break;
                case 4:
                    if (listOfNodes.get(i) != null &&
                        listOfNodes.get(i).getConfigUiNodes() != null &&
                        !listOfNodes.get(i).getConfigUiNodes().isEmpty()) {
                        node = listOfNodes.get(i).getConfigUiNodes().get(0);
                        uiField5 = node.getUiNodeName();
                        selectedValue = subGrpName;
                        czNodeName5 = node.getCzNodeName();
                        if (node.isDisplayReferenceColor())
                            uiField5_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField5_color = SudokuUtils.TARGET_COLOR;
                        uiField5_dis = node.isDisableNode();
                        uiField5_readOnly = node.isReadOnly();
                        uiField5_identifier = node.getIdentifier();
                        if (uiField5_dis) {
                            uiField5_bgColor = "color:Silver;";
                        }
                        if (uiField5_readOnly) {
                            uiField5_bgColor = "color:#d9b3ff;";
                        }
                        if(uiField5_color!=null){
                            uiField5_bgColor = "color:InfoBackground";
                        }
                        if(uiField5_dis){
                            uiField5_color="Yellow";
                        }
                    }
                    break;
                case 5:
                    if (listOfNodes.get(i) != null &&
                        listOfNodes.get(i).getConfigUiNodes() != null &&
                        !listOfNodes.get(i).getConfigUiNodes().isEmpty()) {
                        node = listOfNodes.get(i).getConfigUiNodes().get(0);
                        uiField6 = node.getUiNodeName();
                        selectedValue = subGrpName;
                        czNodeName6 = node.getCzNodeName();
                        if (node.isDisplayReferenceColor())
                            uiField6_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField6_color = SudokuUtils.TARGET_COLOR;
                        uiField6_dis = node.isDisableNode();
                        uiField6_readOnly = node.isReadOnly();
                        uiField6_identifier = node.getIdentifier();
                        if (uiField6_dis) {
                            uiField6_bgColor = "color:Silver;";
                        }
                        if (uiField6_readOnly) {
                            uiField6_bgColor = "color:#d9b3ff;";
                        }
                        if(uiField6_color!=null){
                            uiField6_bgColor = "color:InfoBackground";
                        }
                        if(uiField6_dis){
                            uiField6_color="Yellow";
                        }
                    }
                    break;

                case 6:
                    if (listOfNodes.get(i) != null &&
                        listOfNodes.get(i).getConfigUiNodes() != null &&
                        !listOfNodes.get(i).getConfigUiNodes().isEmpty()) {
                        node = listOfNodes.get(i).getConfigUiNodes().get(0);
                        uiField7 = node.getUiNodeName();
                        selectedValue = subGrpName;
                        czNodeName7 = node.getCzNodeName();
                        if (node.isDisplayReferenceColor())
                            uiField7_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField7_color = SudokuUtils.TARGET_COLOR;
                        uiField7_dis = node.isDisableNode();
                        uiField7_readOnly = node.isReadOnly();
                        uiField7_identifier = node.getIdentifier();
                        if (uiField7_dis) {
                            uiField7_bgColor = "color:Silver;";
                        }
                        if (uiField7_readOnly) {
                            uiField7_bgColor = "color:#d9b3ff;";
                        }
                        if(uiField7_color!=null){
                            uiField7_bgColor = "color:InfoBackground";
                        }
                        if(uiField7_dis){
                            uiField7_color="Yellow";
                        }
                    }
                    break;
                }
            }
        }
    }


    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }

    public String getLineNum() {
        return lineNum;
    }


    public void setUiField1(String uiField1) {
        this.uiField1 = uiField1;
    }

    public String getUiField1() {
        return uiField1;
    }

    public void setUiField2(String uiField2) {
        this.uiField2 = uiField2;
    }

    public String getUiField2() {
        return uiField2;
    }

    public void setUiField3(String uiField3) {
        this.uiField3 = uiField3;
    }

    public String getUiField3() {
        return uiField3;
    }

    public void setUiField4(String uiField4) {
        this.uiField4 = uiField4;
    }

    public String getUiField4() {
        return uiField4;
    }

    public void setUiField5(String uiField5) {
        this.uiField5 = uiField5;
    }

    public String getUiField5() {
        return uiField5;
    }

    public void setUiField6(String uiField6) {
        this.uiField6 = uiField6;
    }

    public String getUiField6() {
        return uiField6;
    }

    public void setUiField7(String uiField7) {
        this.uiField7 = uiField7;
    }

    public String getUiField7() {
        return uiField7;
    }

    public void setUiField1_color(String uiField1_color) {
        this.uiField1_color = uiField1_color;
    }

    public String getUiField1_color() {
        return uiField1_color;
    }

    public void setUiField2_color(String uiField2_color) {
        this.uiField2_color = uiField2_color;
    }

    public String getUiField2_color() {
        return uiField2_color;
    }

    public void setUiField3_color(String uiField3_color) {
        this.uiField3_color = uiField3_color;
    }

    public String getUiField3_color() {
        return uiField3_color;
    }

    public void setUiField4_color(String uiField4_color) {
        this.uiField4_color = uiField4_color;
    }

    public String getUiField4_color() {
        return uiField4_color;
    }

    public void setUiField5_color(String uiField5_color) {
        this.uiField5_color = uiField5_color;
    }

    public String getUiField5_color() {
        return uiField5_color;
    }

    public void setUiField6_color(String uiField6_color) {
        this.uiField6_color = uiField6_color;
    }

    public String getUiField6_color() {
        return uiField6_color;
    }

    public void setUiField7_color(String uiField7_color) {
        this.uiField7_color = uiField7_color;
    }

    public String getUiField7_color() {
        return uiField7_color;
    }


    public void setUiField1_code(String uiField1_code) {
        this.uiField1_code = uiField1_code;
    }

    public String getUiField1_code() {
        return uiField1_code;
    }

    public void setUiField2_code(String uiField2_code) {
        this.uiField2_code = uiField2_code;
    }

    public String getUiField2_code() {
        return uiField2_code;
    }

    public void setUiField3_code(String uiField3_code) {
        this.uiField3_code = uiField3_code;
    }

    public String getUiField3_code() {
        return uiField3_code;
    }

    public void setUiField4_code(String uiField4_code) {
        this.uiField4_code = uiField4_code;
    }

    public String getUiField4_code() {
        return uiField4_code;
    }

    public void setUiField5_code(String uiField5_code) {
        this.uiField5_code = uiField5_code;
    }

    public String getUiField5_code() {
        return uiField5_code;
    }

    public void setUiField6_code(String uiField6_code) {
        this.uiField6_code = uiField6_code;
    }

    public String getUiField6_code() {
        return uiField6_code;
    }

    public void setUiField7_code(String uiField7_code) {
        this.uiField7_code = uiField7_code;
    }

    public String getUiField7_code() {
        return uiField7_code;
    }

    public void setInputTextValue(String inputTextValue) {
        this.inputValue = inputTextValue;
    }

    public String getInputTextValue() {
        return inputValue;
    }


    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }

    public String getSelectedValue() {
        return selectedValue;
    }

    public void setListOfNodes(List<ConfiguratorUiElement> listOfNodes) {
        this.listOfNodes = listOfNodes;
    }

    public List<ConfiguratorUiElement> getListOfNodes() {
        return listOfNodes;
    }

    public void setCzNodeName1(String czNodeName1) {
        this.czNodeName1 = czNodeName1;
    }

    public String getCzNodeName1() {
        return czNodeName1;
    }

    public void setCzNodeName2(String czNodeName2) {
        this.czNodeName2 = czNodeName2;
    }

    public String getCzNodeName2() {
        return czNodeName2;
    }

    public void setCzNodeName3(String czNodeName3) {
        this.czNodeName3 = czNodeName3;
    }

    public String getCzNodeName3() {
        return czNodeName3;
    }

    public void setCzNodeName4(String czNodeName4) {
        this.czNodeName4 = czNodeName4;
    }

    public String getCzNodeName4() {
        return czNodeName4;
    }

    public void setCzNodeName5(String czNodeName5) {
        this.czNodeName5 = czNodeName5;
    }

    public String getCzNodeName5() {
        return czNodeName5;
    }

    public void setCzNodeName6(String czNodeName6) {
        this.czNodeName6 = czNodeName6;
    }

    public String getCzNodeName6() {
        return czNodeName6;
    }

    public void setCzNodeName7(String czNodeName7) {
        this.czNodeName7 = czNodeName7;
    }

    public String getCzNodeName7() {
        return czNodeName7;
    }

    public void setUiField1_dis(Boolean uiField1_dis) {
        this.uiField1_dis = uiField1_dis;
    }

    public Boolean getUiField1_dis() {
        return uiField1_dis;
    }

    public void setUiField2_dis(Boolean uiField2_dis) {
        this.uiField2_dis = uiField2_dis;
    }

    public Boolean getUiField2_dis() {
        return uiField2_dis;
    }

    public void setUiField3_dis(Boolean uiField3_dis) {
        this.uiField3_dis = uiField3_dis;
    }

    public Boolean getUiField3_dis() {
        return uiField3_dis;
    }

    public void setUiField4_dis(Boolean uiField4_dis) {
        this.uiField4_dis = uiField4_dis;
    }

    public Boolean getUiField4_dis() {
        return uiField4_dis;
    }

    public void setUiField5_dis(Boolean uiField5_dis) {
        this.uiField5_dis = uiField5_dis;
    }

    public Boolean getUiField5_dis() {
        return uiField5_dis;
    }

    public void setUiField6_dis(Boolean uiField6_dis) {
        this.uiField6_dis = uiField6_dis;
    }

    public Boolean getUiField6_dis() {
        return uiField6_dis;
    }

    public void setUiField7_dis(Boolean uiField7_dis) {
        this.uiField7_dis = uiField7_dis;
    }

    public Boolean getUiField7_dis() {
        return uiField7_dis;
    }

    public void setUiField1_readOnly(Boolean uiField1_readOnly) {
        this.uiField1_readOnly = uiField1_readOnly;
    }

    public Boolean getUiField1_readOnly() {
        return uiField1_readOnly;
    }

    public void setUiField2_readOnly(Boolean uiField2_readOnly) {
        this.uiField2_readOnly = uiField2_readOnly;
    }

    public Boolean getUiField2_readOnly() {
        return uiField2_readOnly;
    }

    public void setUiField3_readOnly(Boolean uiField3_readOnly) {
        this.uiField3_readOnly = uiField3_readOnly;
    }

    public Boolean getUiField3_readOnly() {
        return uiField3_readOnly;
    }

    public void setUiField4_readOnly(Boolean uiField4_readOnly) {
        this.uiField4_readOnly = uiField4_readOnly;
    }

    public Boolean getUiField4_readOnly() {
        return uiField4_readOnly;
    }

    public void setUiField5_readOnly(Boolean uiField5_readOnly) {
        this.uiField5_readOnly = uiField5_readOnly;
    }

    public Boolean getUiField5_readOnly() {
        return uiField5_readOnly;
    }

    public void setUiField6_readOnly(Boolean uiField6_readOnly) {
        this.uiField6_readOnly = uiField6_readOnly;
    }

    public Boolean getUiField6_readOnly() {
        return uiField6_readOnly;
    }

    public void setUiField7_readOnly(Boolean uiField7_readOnly) {
        this.uiField7_readOnly = uiField7_readOnly;
    }

    public Boolean getUiField7_readOnly() {
        return uiField7_readOnly;
    }

    public void setUiField1_identifier(String uiField1_identifier) {
        this.uiField1_identifier = uiField1_identifier;
    }

    public String getUiField1_identifier() {
        return uiField1_identifier;
    }

    public void setUiField2_identifier(String uiField2_identifier) {
        this.uiField2_identifier = uiField2_identifier;
    }

    public String getUiField2_identifier() {
        return uiField2_identifier;
    }

    public void setUiField3_identifier(String uiField3_identifier) {
        this.uiField3_identifier = uiField3_identifier;
    }

    public String getUiField3_identifier() {
        return uiField3_identifier;
    }

    public void setUiField4_identifier(String uiField4_identifier) {
        this.uiField4_identifier = uiField4_identifier;
    }

    public String getUiField4_identifier() {
        return uiField4_identifier;
    }

    public void setUiField5_identifier(String uiField5_identifier) {
        this.uiField5_identifier = uiField5_identifier;
    }

    public String getUiField5_identifier() {
        return uiField5_identifier;
    }

    public void setUiField6_identifier(String uiField6_identifier) {
        this.uiField6_identifier = uiField6_identifier;
    }

    public String getUiField6_identifier() {
        return uiField6_identifier;
    }

    public void setUiField7_identifier(String uiField7_identifier) {
        this.uiField7_identifier = uiField7_identifier;
    }

    public String getUiField7_identifier() {
        return uiField7_identifier;
    }

    public void setUiField1_bgColor(String uiField1_bgColor) {
        this.uiField1_bgColor = uiField1_bgColor;
    }

    public String getUiField1_bgColor() {
        return uiField1_bgColor;
    }

    public void setUiField2_bgColor(String uiField2_bgColor) {
        this.uiField2_bgColor = uiField2_bgColor;
    }

    public String getUiField2_bgColor() {
        return uiField2_bgColor;
    }

    public void setUiField3_bgColor(String uiField3_bgColor) {
        this.uiField3_bgColor = uiField3_bgColor;
    }

    public String getUiField3_bgColor() {
        return uiField3_bgColor;
    }

    public void setUiField4_bgColor(String uiField4_bgColor) {
        this.uiField4_bgColor = uiField4_bgColor;
    }

    public String getUiField4_bgColor() {
        return uiField4_bgColor;
    }

    public void setUiField5_bgColor(String uiField5_bgColor) {
        this.uiField5_bgColor = uiField5_bgColor;
    }

    public String getUiField5_bgColor() {
        return uiField5_bgColor;
    }

    public void setUiField6_bgColor(String uiField6_bgColor) {
        this.uiField6_bgColor = uiField6_bgColor;
    }

    public String getUiField6_bgColor() {
        return uiField6_bgColor;
    }

    public void setUiField7_bgColor(String uiField7_bgColor) {
        this.uiField7_bgColor = uiField7_bgColor;
    }

    public String getUiField7_bgColor() {
        return uiField7_bgColor;
    }
}
