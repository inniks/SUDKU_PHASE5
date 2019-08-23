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
    private String uiField8;
    private String uiField9;
    private String uiField10;
    private String uiField1_color;
    private String uiField2_color;
    private String uiField3_color;
    private String uiField4_color;
    private String uiField5_color;
    private String uiField6_color;
    private String uiField7_color;
    private String uiField8_color;
    private String uiField9_color;
    private String uiField10_color;
    private String uiField1_code;
    private String uiField2_code;
    private String uiField3_code;
    private String uiField4_code;
    private String uiField5_code;
    private String uiField6_code;
    private String uiField7_code;
    private String uiField8_code;
    private String uiField9_code;
    private String uiField10_code;
    private String uiField1_type;
    private String uiField2_type;
    private String uiField3_type;
    private String uiField4_type;
    private String uiField5_type;
    private String uiField6_type;
    private String uiField7_type;
    private String uiField8_type;
    private String uiField9_type;
    private String uiField10_type;
    private String czNodeName1;
    private String czNodeName2;
    private String czNodeName3;
    private String czNodeName4;
    private String czNodeName5;
    private String czNodeName6;
    private String czNodeName7;
    private String czNodeName8;
    private String czNodeName9;
    private String czNodeName10;
    private Boolean uiField1_dis;
    private Boolean uiField2_dis;
    private Boolean uiField3_dis;
    private Boolean uiField4_dis;
    private Boolean uiField5_dis;
    private Boolean uiField6_dis;
    private Boolean uiField7_dis;
    private Boolean uiField8_dis;
    private Boolean uiField9_dis;
    private Boolean uiField10_dis;
    private Boolean uiField1_readOnly;
    private Boolean uiField2_readOnly;
    private Boolean uiField3_readOnly;
    private Boolean uiField4_readOnly;
    private Boolean uiField5_readOnly;
    private Boolean uiField6_readOnly;
    private Boolean uiField7_readOnly;
    private Boolean uiField8_readOnly;
    private Boolean uiField9_readOnly;
    private Boolean uiField10_readOnly;
    private String uiField1_identifier;
    private String uiField2_identifier;
    private String uiField3_identifier;
    private String uiField4_identifier;
    private String uiField5_identifier;
    private String uiField6_identifier;
    private String uiField7_identifier;
    private String uiField8_identifier;
    private String uiField9_identifier;
    private String uiField10_identifier;
    private String uiField1_bgColor;
    private String uiField2_bgColor;
    private String uiField3_bgColor;
    private String uiField4_bgColor;
    private String uiField5_bgColor;
    private String uiField6_bgColor;
    private String uiField7_bgColor;
    private String uiField8_bgColor;
    private String uiField9_bgColor;
    private String uiField10_bgColor;
    private List<ConfiguratorUiElement> listOfNodes;
    private String inputValue;
    private String selectedValue;
    private String parentGroupName;
    private String requiredFlagSubGrp;

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

    public UiField(List<ConfiguratorUiElement> listOfNodes, String subGrpName,
                   String requireFlagSubGrp, String parentGroupName) {
        super();
        this.listOfNodes = listOfNodes;
        String czNodeNameUiField8 = null;

        if (listOfNodes != null && !listOfNodes.isEmpty()) {
            ConfiguratorUiNode node = null;
            if (listOfNodes.get(0) != null) {
                if (listOfNodes.get(0).getConfigUiNodes() != null &&
                    listOfNodes.get(0).getConfigUiNodes().size() > 0) {
                    ConfiguratorUiNode firstNode =
                        listOfNodes.get(0).getConfigUiNodes().get(0);
                    uiField8_code = firstNode.getCzNodeName();
                    int referenceQuantity = firstNode.getReferenceQuantiy();
                    if (referenceQuantity != -1) {
                        uiField9 = Integer.toString(referenceQuantity);
                    }
                    int targetQuantity = firstNode.getTargetQuantity();
                    if (targetQuantity != -1) {
                        uiField10 = Integer.toString(targetQuantity);
                    }

                }
            }
            System.out.println("czNodeNameUiField8 " + czNodeNameUiField8);
            for (int i = 0; i < listOfNodes.size(); i++) {
                switch (i) {
                case 0:
                    if (listOfNodes.get(i) != null &&
                        listOfNodes.get(i).getConfigUiNodes() != null &&
                        !listOfNodes.get(i).getConfigUiNodes().isEmpty()) {
                        node = listOfNodes.get(i).getConfigUiNodes().get(0);
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        switch (uiElementType) {
                        case 1:
                            uiField1_type = "NUMERIC";
                        case 2:
                            uiField1_type = "TEXT";
                        case 3:
                            uiField1_type = "OPTION";
                        case 4:
                            uiField1_type = "LISTOFVALUES";

                        }
                        if (uiElementType == 1) {
                            uiField8_type = "NUMERIC";
                        }
                        uiField1 = node.getUiNodeName();
                        selectedValue = subGrpName;
                        requiredFlagSubGrp = requireFlagSubGrp;
                        czNodeName1 = node.getCzNodeName();
                        this.parentGroupName = parentGroupName;
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

                        if (uiField1_dis) {
                            uiField1_color = "Yellow";
                        }
                        if (uiField1_color != null) {
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
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        switch (uiElementType) {
                        case 1:
                            uiField2_type = "NUMERIC";
                        case 2:
                            uiField2_type = "TEXT";
                        case 3:
                            uiField2_type = "OPTION";
                        case 4:
                            uiField2_type = "LISTOFVALUES";

                        }
                        if (uiField2_dis) {
                            uiField2_bgColor = "color:Silver;";
                        }
                        if (uiField2_readOnly) {
                            uiField2_bgColor = "color:#d9b3ff;";
                        }
                        if (uiField2_color != null) {
                            uiField2_bgColor = "color:InfoBackground";
                        }
                        if (uiField2_dis) {
                            uiField2_color = "Yellow";
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
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        switch (uiElementType) {
                        case 1:
                            uiField3_type = "NUMERIC";
                        case 2:
                            uiField3_type = "TEXT";
                        case 3:
                            uiField3_type = "OPTION";
                        case 4:
                            uiField3_type = "LISTOFVALUES";

                        }
                        if (uiField3_dis) {
                            uiField3_bgColor = "color:Silver;";
                        }
                        if (uiField3_readOnly) {
                            uiField3_bgColor = "color:#d9b3ff;";
                        }
                        if (uiField3_color != null) {
                            uiField3_bgColor = "color:InfoBackground";
                        }
                        if (uiField3_dis) {
                            uiField3_color = "Yellow";
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
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        switch (uiElementType) {
                        case 1:
                            uiField4_type = "NUMERIC";
                        case 2:
                            uiField4_type = "TEXT";
                        case 3:
                            uiField4_type = "OPTION";
                        case 4:
                            uiField4_type = "LISTOFVALUES";

                        }
                        if (uiField4_dis) {
                            uiField4_bgColor = "color:Silver;";
                        }
                        if (uiField4_readOnly) {
                            uiField4_bgColor = "color:#d9b3ff;";
                        }
                        if (uiField4_color != null) {
                            uiField4_bgColor = "color:InfoBackground";
                        }
                        if (uiField4_dis) {
                            uiField4_color = "Yellow";
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
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        switch (uiElementType) {
                        case 1:
                            uiField5_type = "NUMERIC";
                        case 2:
                            uiField5_type = "TEXT";
                        case 3:
                            uiField5_type = "OPTION";
                        case 4:
                            uiField5_type = "LISTOFVALUES";

                        }
                        if (uiField5_dis) {
                            uiField5_bgColor = "color:Silver;";
                        }
                        if (uiField5_readOnly) {
                            uiField5_bgColor = "color:#d9b3ff;";
                        }
                        if (uiField5_color != null) {
                            uiField5_bgColor = "color:InfoBackground";
                        }
                        if (uiField5_dis) {
                            uiField5_color = "Yellow";
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
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        switch (uiElementType) {
                        case 1:
                            uiField6_type = "NUMERIC";
                        case 2:
                            uiField6_type = "TEXT";
                        case 3:
                            uiField6_type = "OPTION";
                        case 4:
                            uiField6_type = "LISTOFVALUES";

                        }
                        if (uiField6_dis) {
                            uiField6_bgColor = "color:Silver;";
                        }
                        if (uiField6_readOnly) {
                            uiField6_bgColor = "color:#d9b3ff;";
                        }
                        if (uiField6_color != null) {
                            uiField6_bgColor = "color:InfoBackground";
                        }
                        if (uiField6_dis) {
                            uiField6_color = "Yellow";
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
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        switch (uiElementType) {
                        case 1:
                            uiField7_type = "NUMERIC";
                        case 2:
                            uiField7_type = "TEXT";
                        case 3:
                            uiField7_type = "OPTION";
                        case 4:
                            uiField7_type = "LISTOFVALUES";

                        }
                        if (uiField7_dis) {
                            uiField7_bgColor = "color:Silver;";
                        }
                        if (uiField7_readOnly) {
                            uiField7_bgColor = "color:#d9b3ff;";
                        }
                        if (uiField7_color != null) {
                            uiField7_bgColor = "color:InfoBackground";
                        }
                        if (uiField7_dis) {
                            uiField7_color = "Yellow";
                        }
                    }
                    break;
                case 7:

                    if (listOfNodes.get(i) != null &&
                        listOfNodes.get(i).getConfigUiNodes() != null &&
                        !listOfNodes.get(i).getConfigUiNodes().isEmpty()) {
                        node = listOfNodes.get(i).getConfigUiNodes().get(0);
                        uiField8 = node.getUiNodeName();
                        selectedValue = subGrpName;
                        czNodeName8 = node.getCzNodeName();
                        if (node.isDisplayReferenceColor())
                            uiField8_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField8_color = SudokuUtils.TARGET_COLOR;
                        uiField8_dis = node.isDisableNode();
                        uiField8_readOnly = node.isReadOnly();
                        uiField8_identifier = node.getIdentifier();
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        System.out.println("Element Type for 8th field " +
                                           uiElementType);
                        switch (uiElementType) {
                        case 1:
                            uiField8_type = "NUMERIC";
                        case 2:
                            uiField8_type = "TEXT";
                        case 3:
                            uiField8_type = "OPTION";
                        case 4:
                            uiField8_type = "LISTOFVALUES";

                        }
                        if (uiField8_dis) {
                            uiField8_bgColor = "color:Silver;";
                        }
                        if (uiField8_readOnly) {
                            uiField8_bgColor = "color:#d9b3ff;";
                        }
                        if (uiField8_color != null) {
                            uiField8_bgColor = "color:InfoBackground";
                        }
                        if (uiField8_dis) {
                            uiField8_color = "Yellow";
                        }
                    }
                    break;

                case 8:
                    if (listOfNodes.get(i) != null &&
                        listOfNodes.get(i).getConfigUiNodes() != null &&
                        !listOfNodes.get(i).getConfigUiNodes().isEmpty()) {
                        node = listOfNodes.get(i).getConfigUiNodes().get(0);
                        //Ui field 9 is to show the reference quantity
                        uiField9 =
                                node.getReferenceQuantiy() == -1 ? null : Integer.toString(node.getReferenceQuantiy()); //node.getUiNodeName();
                        System.out.println("UiField9 Value " + uiField9);
                        selectedValue = subGrpName;
                        czNodeName9 = node.getCzNodeName();
                        if (node.isDisplayReferenceColor())
                            uiField9_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField9_color = SudokuUtils.TARGET_COLOR;
                        uiField9_dis = node.isDisableNode();
                        uiField9_readOnly = node.isReadOnly();
                        uiField9_identifier = node.getIdentifier();
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        switch (uiElementType) {
                        case 1:
                            uiField9_type = "NUMERIC";
                        case 2:
                            uiField9_type = "TEXT";
                        case 3:
                            uiField9_type = "OPTION";
                        case 4:
                            uiField9_type = "LISTOFVALUES";

                        }
                        if (uiField9_dis) {
                            uiField9_bgColor = "color:Silver;";
                        }
                        if (uiField9_readOnly) {
                            uiField9_bgColor = "color:#d9b3ff;";
                        }
                        if (uiField9_color != null) {
                            uiField9_bgColor = "color:InfoBackground";
                        }
                        if (uiField9_dis) {
                            uiField9_color = "Yellow";
                        }
                    }
                    break;
                case 9:
                    if (listOfNodes.get(i) != null &&
                        listOfNodes.get(i).getConfigUiNodes() != null &&
                        !listOfNodes.get(i).getConfigUiNodes().isEmpty()) {
                        node = listOfNodes.get(i).getConfigUiNodes().get(0);
                        uiField10 =
                                node.getTargetQuantity() == -1 ? null : Integer.toString(node.getTargetQuantity()); //node.getUiNodeName();
                        System.out.println("Field 10 value " + uiField10);
                        selectedValue = subGrpName;
                        czNodeName10 = node.getCzNodeName();
                        if (node.isDisplayReferenceColor())
                            uiField10_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField10_color = SudokuUtils.TARGET_COLOR;
                        uiField10_dis = node.isDisableNode();
                        uiField10_readOnly = node.isReadOnly();
                        uiField10_identifier = node.getIdentifier();
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        switch (uiElementType) {
                        case 1:
                            uiField10_type = "NUMERIC";
                        case 2:
                            uiField10_type = "TEXT";
                        case 3:
                            uiField10_type = "OPTION";
                        case 4:
                            uiField10_type = "LISTOFVALUES";

                        }
                        if (uiField10_dis) {
                            uiField10_bgColor = "color:Silver;";
                        }
                        if (uiField10_readOnly) {
                            uiField10_bgColor = "color:#d9b3ff;";
                        }
                        if (uiField10_color != null) {
                            uiField10_bgColor = "color:InfoBackground";
                        }
                        if (uiField10_dis) {
                            uiField10_color = "Yellow";
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

    public void setRequiredFlagSubGrp(String requiredFlagSubGrp) {
        this.requiredFlagSubGrp = requiredFlagSubGrp;
    }

    public String getRequiredFlagSubGrp() {
        return requiredFlagSubGrp;
    }

    public void setParentGroupName(String parentGroupName) {
        this.parentGroupName = parentGroupName;
    }

    public String getParentGroupName() {
        return parentGroupName;
    }

    public void setUiField8(String uiField8) {
        this.uiField8 = uiField8;
    }

    public String getUiField8() {
        return uiField8;
    }

    public void setUiField9(String uiField9) {
        this.uiField9 = uiField9;
    }

    public String getUiField9() {
        return uiField9;
    }

    public void setUiField10(String uiField10) {
        this.uiField10 = uiField10;
    }

    public String getUiField10() {
        return uiField10;
    }

    public void setUiField8_color(String uiField8_color) {
        this.uiField8_color = uiField8_color;
    }

    public String getUiField8_color() {
        return uiField8_color;
    }

    public void setUiField9_color(String uiField9_color) {
        this.uiField9_color = uiField9_color;
    }

    public String getUiField9_color() {
        return uiField9_color;
    }

    public void setUiField10_color(String uiField10_color) {
        this.uiField10_color = uiField10_color;
    }

    public String getUiField10_color() {
        return uiField10_color;
    }

    public void setUiField8_code(String uiField8_code) {
        this.uiField8_code = uiField8_code;
    }

    public String getUiField8_code() {
        return uiField8_code;
    }

    public void setUiField9_code(String uiField9_code) {
        this.uiField9_code = uiField9_code;
    }

    public String getUiField9_code() {
        return uiField9_code;
    }

    public void setUiField10_code(String uiField10_code) {
        this.uiField10_code = uiField10_code;
    }

    public String getUiField10_code() {
        return uiField10_code;
    }

    public void setCzNodeName8(String czNodeName8) {
        this.czNodeName8 = czNodeName8;
    }

    public String getCzNodeName8() {
        return czNodeName8;
    }

    public void setCzNodeName9(String czNodeName9) {
        this.czNodeName9 = czNodeName9;
    }

    public String getCzNodeName9() {
        return czNodeName9;
    }

    public void setCzNodeName10(String czNodeName10) {
        this.czNodeName10 = czNodeName10;
    }

    public String getCzNodeName10() {
        return czNodeName10;
    }

    public void setUiField8_dis(Boolean uiField8_dis) {
        this.uiField8_dis = uiField8_dis;
    }

    public Boolean getUiField8_dis() {
        return uiField8_dis;
    }

    public void setUiField9_dis(Boolean uiField9_dis) {
        this.uiField9_dis = uiField9_dis;
    }

    public Boolean getUiField9_dis() {
        return uiField9_dis;
    }

    public void setUiField10_dis(Boolean uiField10_dis) {
        this.uiField10_dis = uiField10_dis;
    }

    public Boolean getUiField10_dis() {
        return uiField10_dis;
    }

    public void setUiField8_readOnly(Boolean uiField8_readOnly) {
        this.uiField8_readOnly = uiField8_readOnly;
    }

    public Boolean getUiField8_readOnly() {
        return uiField8_readOnly;
    }

    public void setUiField9_readOnly(Boolean uiField9_readOnly) {
        this.uiField9_readOnly = uiField9_readOnly;
    }

    public Boolean getUiField9_readOnly() {
        return uiField9_readOnly;
    }

    public void setUiField10_readOnly(Boolean uiField10_readOnly) {
        this.uiField10_readOnly = uiField10_readOnly;
    }

    public Boolean getUiField10_readOnly() {
        return uiField10_readOnly;
    }

    public void setUiField8_identifier(String uiField8_identifier) {
        this.uiField8_identifier = uiField8_identifier;
    }

    public String getUiField8_identifier() {
        return uiField8_identifier;
    }

    public void setUiField9_identifier(String uiField9_identifier) {
        this.uiField9_identifier = uiField9_identifier;
    }

    public String getUiField9_identifier() {
        return uiField9_identifier;
    }

    public void setUiField10_identifier(String uiField10_identifier) {
        this.uiField10_identifier = uiField10_identifier;
    }

    public String getUiField10_identifier() {
        return uiField10_identifier;
    }

    public void setUiField8_bgColor(String uiField8_bgColor) {
        this.uiField8_bgColor = uiField8_bgColor;
    }

    public String getUiField8_bgColor() {
        return uiField8_bgColor;
    }

    public void setUiField9_bgColor(String uiField9_bgColor) {
        this.uiField9_bgColor = uiField9_bgColor;
    }

    public String getUiField9_bgColor() {
        return uiField9_bgColor;
    }

    public void setUiField10_bgColor(String uiField10_bgColor) {
        this.uiField10_bgColor = uiField10_bgColor;
    }

    public String getUiField10_bgColor() {
        return uiField10_bgColor;
    }

    public void setUiField1_type(String uiField1_type) {
        this.uiField1_type = uiField1_type;
    }

    public String getUiField1_type() {
        return uiField1_type;
    }

    public void setUiField2_type(String uiField2_type) {
        this.uiField2_type = uiField2_type;
    }

    public String getUiField2_type() {
        return uiField2_type;
    }

    public void setUiField3_type(String uiField3_type) {
        this.uiField3_type = uiField3_type;
    }

    public String getUiField3_type() {
        return uiField3_type;
    }

    public void setUiField4_type(String uiField4_type) {
        this.uiField4_type = uiField4_type;
    }

    public String getUiField4_type() {
        return uiField4_type;
    }

    public void setUiField5_type(String uiField5_type) {
        this.uiField5_type = uiField5_type;
    }

    public String getUiField5_type() {
        return uiField5_type;
    }

    public void setUiField6_type(String uiField6_type) {
        this.uiField6_type = uiField6_type;
    }

    public String getUiField6_type() {
        return uiField6_type;
    }

    public void setUiField7_type(String uiField7_type) {
        this.uiField7_type = uiField7_type;
    }

    public String getUiField7_type() {
        return uiField7_type;
    }

    public void setUiField8_type(String uiField8_type) {
        this.uiField8_type = uiField8_type;
    }

    public String getUiField8_type() {
        return uiField8_type;
    }

    public void setUiField9_type(String uiField9_type) {
        this.uiField9_type = uiField9_type;
    }

    public String getUiField9_type() {
        return uiField9_type;
    }

    public void setUiField10_type(String uiField10_type) {
        this.uiField10_type = uiField10_type;
    }

    public String getUiField10_type() {
        return uiField10_type;
    }
}
