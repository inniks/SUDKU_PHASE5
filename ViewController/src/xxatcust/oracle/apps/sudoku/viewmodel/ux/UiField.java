package xxatcust.oracle.apps.sudoku.viewmodel.ux;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

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
    private String uiField11;
    private String uiField12;
    private String uiField13;

    private String uiField1_refQty;
    private String uiField2_refQty;
    private String uiField3_refQty;
    private String uiField4_refQty;
    private String uiField5_refQty;
    private String uiField6_refQty;
    private String uiField7_refQty;
    private String uiField8_refQty;
    private String uiField9_refQty;
    private String uiField10_refQty;
    private String uiField11_refQty;
    private String uiField12_refQty;
    private String uiField13_refQty;
    private String uiField1_tarQty;

    private String uiField2_tarQty;
    private String uiField3_tarQty;
    private String uiField4_tarQty;
    private String uiField5_tarQty;
    private String uiField6_tarQty;
    private String uiField7_tarQty;
    private String uiField8_tarQty;
    private String uiField9_tarQty;
    private String uiField10_tarQty;
    private String uiField11_tarQty;
    private String uiField12_tarQty;
    private String uiField13_tarQty;

    private String uiField1_uiNodeName;
    private String uiField2_uiNodeName;
    private String uiField3_uiNodeName;
    private String uiField4_uiNodeName;
    private String uiField5_uiNodeName;
    private String uiField6_uiNodeName;
    private String uiField7_uiNodeName;
    private String uiField8_uiNodeName;
    private String uiField9_uiNodeName;
    private String uiField10_uiNodeName;
    private String uiField11_uiNodeName;
    private String uiField12_uiNodeName;
    private String uiField13_uiNodeName;

    private String uiField1_czModelName;
    private String uiField2_czModelName;
    private String uiField3_czModelName;
    private String uiField4_czModelName;
    private String uiField5_czModelName;
    private String uiField6_czModelName;
    private String uiField7_czModelName;
    private String uiField8_czModelName;
    private String uiField9_czModelName;
    private String uiField10_czModelName;
    private String uiField11_czModelName;
    private String uiField12_czModelName;
    private String uiField13_czModelName;

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
    private String uiField11_color;
    private String uiField12_color;
    private String uiField13_color;
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
    private String uiField11_code;
    private String uiField12_code;
    private String uiField13_code;
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
    private String uiField11_type;
    private String uiField12_type;
    private String uiField13_type;
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
    private String czNodeName11;
    private String czNodeName12;
    private String czNodeName13;
    private String uiField1_qty;
    private String uiField2_qty;
    private String uiField3_qty;
    private String uiField4_qty;
    private String uiField5_qty;
    private String uiField6_qty;
    private String uiField7_qty;
    private String uiField8_qty;
    private String uiField9_qty;
    private String uiField10_qty;
    private String uiField11_qty;
    private String uiField12_qty;
    private String uiField13_qty;
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
    private Boolean uiField11_dis;
    private Boolean uiField12_dis;
    private Boolean uiField13_dis;
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
    private Boolean uiField11_readOnly;
    private Boolean uiField12_readOnly;
    private Boolean uiField13_readOnly;
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
    private String uiField11_identifier;
    private String uiField12_identifier;
    private String uiField13_identifier;
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
    private String uiField11_bgColor;
    private String uiField12_bgColor;
    private String uiField13_bgColor;
    private List<ConfiguratorUiElement> listOfNodes;
    private String inputValue;
    private String selectedValue;
    private String parentGroupName;
    private String requiredFlagSubGrp;
    private String uiGrpIndex;
    private String digitsEntered;
    private List<SelectItem> listOfValueModel;
    private Boolean disableMultipleInputs = false;
    private String sumReference;
    private String sumTarget;
    private String sumInstaBaseRef;
    private String sumInstaBaseTar;
    private String identifyLicenceRow;
    private String selectedLovValue;
    private String licenceRowIndex;
    private Boolean disableEFCol;

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
                   String requireFlagSubGrp, String parentGroupName,
                   String uiGrpIndex) {
        super();
        this.listOfNodes = listOfNodes;
        this.uiGrpIndex = uiGrpIndex;
        int sumLicenceInputs = 0, sumRef = 0, sumTar = 0, sumInsRef =
            0, sumInsTar = 0;
        int numOfSelections = 0;
        int countLicensesEntered = 0;
        int countOfEFEntries = 0;
        boolean displayOnlyLogic = false;
        if (listOfNodes != null && !listOfNodes.isEmpty()) {

            ConfiguratorUiNode node = null;
            if (listOfNodes.get(0) != null) {
                if (listOfNodes.get(0).getConfigUiNodes() != null &&
                    listOfNodes.get(0).getConfigUiNodes().size() > 0) {
                    //Implementing LIST oF values for country
                    if (listOfNodes.get(0).getUiElementType() == 4) {
                        listOfValueModel = new ArrayList<SelectItem>();
                        List<ConfiguratorUiNode> list =
                            listOfNodes.get(0).getConfigUiNodes();
                        for (ConfiguratorUiNode uiNode : list) {
                            if (uiNode != null && uiNode.isIsSelected()) {
                                String czNodeName =
                                    uiNode.getCzNodeName() == null ? "-1" :
                                    uiNode.getCzNodeName();
                                String uiNodeName =
                                    uiNode.getUiNodeName() == null ? "-1" :
                                    uiNode.getUiNodeName();
                                String identifier =
                                    uiNode.getIdentifier() == null ? "-1" :
                                    uiNode.getIdentifier();
                                selectedLovValue =
                                        czNodeName.concat("-").concat(uiNodeName).concat("-").concat(identifier);
                                System.out.println("Selected LOV Value is " +
                                                   selectedLovValue);
                            }
                            String czNodeName =
                                uiNode.getCzNodeName() == null ? "-1" :
                                uiNode.getCzNodeName();
                            String uiNodeName =
                                uiNode.getUiNodeName() == null ? "-1" :
                                uiNode.getUiNodeName();
                            String identifier =
                                uiNode.getIdentifier() == null ? "-1" :
                                uiNode.getIdentifier();
                            SelectItem selectItem =
                                new SelectItem((czNodeName.concat("-").concat(uiNodeName).concat("-").concat(identifier)),
                                               uiNode.getUiNodeName());
                            selectItem.setDescription(uiNode.getCzNodeName());
                            listOfValueModel.add(selectItem);
                        }
                    }
                    ConfiguratorUiNode firstNode =
                        listOfNodes.get(0).getConfigUiNodes().get(0);
                    uiField8_code = firstNode.getCzNodeName();
                    //Uncomment this code once mapping for target and ref quantity is clear
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
            if (listOfNodes != null && listOfNodes.size() > 1) {
                //this identifies a license row
                identifyLicenceRow = "Y";
            }
            if (listOfNodes != null && listOfNodes.size() == 1) {
                identifyLicenceRow = "N";
            }
            for (int i = 0; i < listOfNodes.size(); i++) {

                this.lineNum = Integer.toString(i);
                switch (i) {
                case 0:
                    if (listOfNodes.get(i) != null &&
                        listOfNodes.get(i).getConfigUiNodes() != null &&
                        !listOfNodes.get(i).getConfigUiNodes().isEmpty()) {
                        node = listOfNodes.get(i).getConfigUiNodes().get(0);
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        if (node != null && node.getIdentifier() != null &&
                            node.getIdentifier().equals("DISPLAYONLY")) {
                            displayOnlyLogic = true;
                            uiField13_type = "DISPLAYONLY";
                        }
                        if (displayOnlyLogic) {
                            if (node.isIsSelected()) {
                                numOfSelections++;
                            }
                        }
                        int qty = node.getQuantity();
                        if (qty != -1) {
                            uiField1_qty = Integer.toString(qty);
                            sumLicenceInputs =
                                    sumLicenceInputs + node.getQuantity();
                        }
                        if (node.getReferenceQuantiy() != -1 &&
                            node.getUiNodeName() != null &&
                            (node.getUiNodeName().contains("Inst") ||
                             node.getUiNodeName().contains("Base"))) {
                            sumInsRef = sumInsRef + node.getReferenceQuantiy();

                        }
                        if (node.getReferenceQuantiy() != -1) {
                            sumRef = sumRef + node.getReferenceQuantiy();
                        }
                        if (node.getTargetQuantity() != -1 &&
                            node.getUiNodeName() != null &&
                            (node.getUiNodeName().contains("Inst") ||
                             node.getUiNodeName().contains("Base"))) {
                            sumInsTar = sumInsTar + node.getTargetQuantity();

                        }
                        if (node.getTargetQuantity() != -1) {
                            sumTar = sumTar + node.getTargetQuantity();
                        }
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
                            //If an input is there ,disable other inputs

                            uiField8_type = "NUMERIC";
                        }
                        if (node.getQuantity() != -1) {
                            //some value already exists
                            countLicensesEntered++;
                            //disableMultipleInputs = true ;
                        }
                        uiField1 = node.getUiNodeName();
                        selectedValue = subGrpName;
                        requiredFlagSubGrp = requireFlagSubGrp;
                        czNodeName1 = node.getCzNodeName();
                        uiField1_code = czNodeName1;
                        this.parentGroupName = parentGroupName;
                        if (node.isDisplayReferenceColor())
                            uiField1_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField1_color = SudokuUtils.TARGET_COLOR;
                        if (node.isDisplayReferenceColor() &&
                            node.isDisplayTargetColor()) {
                            uiField1_color = SudokuUtils.TARGET_COLOR;
                        }

                        uiField1_dis = node.isDisableNode();
                        uiField1_readOnly = node.isReadOnly();
                        uiField1_identifier = node.getIdentifier();
                        if (uiField1_dis) {
                            uiField1_bgColor = "color:Silver;";
                        }
                        if (uiField1_readOnly) {
                            uiField1_bgColor = "color:#d9b3ff;";
                        }

                        //                        if (uiField1_dis) {
                        //                            uiField1_color = "Yellow";
                        //                        }
                        if (uiField1_color != null) {
                            uiField1_bgColor = "color:InfoBackground";
                        }
                    }

                    if (uiField1_identifier != null &&
                        uiField1_identifier.equalsIgnoreCase("OBSOLETE")) {
                        uiField1_color = "Red";
                    }
//                    if (node != null) {
//                        int qty1 = node.getQuantity();
//                        if (qty1 != -1) {
//                            uiField1_qty = Integer.toString(qty1);
//                            sumLicenceInputs =
//                                    sumLicenceInputs + node.getQuantity();
//                            countOfEFEntries++;
//                        }
//                    }
                    if (node != null) {
                        uiField1_refQty =
                                node.getReferenceQuantiy() == -1 ? "-1" :
                                Integer.toString(node.getReferenceQuantiy());
                        uiField8_refQty =
                                node.getReferenceQuantiy() == -1 ? "-1" :
                                Integer.toString(node.getReferenceQuantiy());
                        uiField8_tarQty =
                                node.getTargetQuantity() == -1 ? "-1" :
                                Integer.toString(node.getTargetQuantity());
                        uiField1_tarQty =
                                node.getTargetQuantity() == -1 ? "-1" :
                                Integer.toString(node.getTargetQuantity());
                        uiField8_czModelName = node.getCzModelName();
                        uiField1_czModelName = node.getCzModelName();
                        uiField8_uiNodeName = node.getUiNodeName();
                        uiField1_uiNodeName = node.getUiNodeName();
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
                        uiField2_code = czNodeName2;
                        if (node != null && node.getIdentifier() != null &&
                            node.getIdentifier().equals("DISPLAYONLY")) {
                            displayOnlyLogic = true;
                            uiField13_type = "DISPLAYONLY";
                        }
                        if (displayOnlyLogic) {
                            if (node.isIsSelected()) {
                                numOfSelections++;
                            }
                        }
                        if (node.isDisplayReferenceColor())
                            uiField2_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField2_color = SudokuUtils.TARGET_COLOR;
                        if (node.isDisplayReferenceColor() &&
                            node.isDisplayTargetColor()) {
                            uiField2_color = SudokuUtils.TARGET_COLOR;
                        }
                        uiField2_dis = node.isDisableNode();
                        uiField2_readOnly = node.isReadOnly();
                        uiField2_identifier = node.getIdentifier();
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        if (node.getReferenceQuantiy() != -1 &&
                            node.getUiNodeName() != null &&
                            (node.getUiNodeName().contains("Inst") ||
                             node.getUiNodeName().contains("Base"))) {
                            sumInsRef = sumInsRef + node.getReferenceQuantiy();

                        }
                        if (node.getReferenceQuantiy() != -1) {
                            sumRef = sumRef + node.getReferenceQuantiy();
                        }
                        if (node.getTargetQuantity() != -1 &&
                            node.getUiNodeName() != null &&
                            (node.getUiNodeName().contains("Inst") ||
                             node.getUiNodeName().contains("Base"))) {
                            sumInsTar = sumInsTar + node.getTargetQuantity();

                        }
                        if (node.getTargetQuantity() != -1) {
                            sumTar = sumTar + node.getTargetQuantity();
                        }
                        int qty = node.getQuantity();
                        if (qty != -1) {
                            uiField2_qty = Integer.toString(qty);
                            sumLicenceInputs =
                                    sumLicenceInputs + node.getQuantity();
                        }
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
                        //If an input is there ,disable other inputs
                        if (node.getQuantity() != -1) {
                            //some value already exists
                            countLicensesEntered++;
                            //disableMultipleInputs = true ;
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
                        //                        if (uiField2_dis) {
                        //                            uiField2_color = "Yellow";
                        //                        }
                        if (qty != -1) {
                            uiField2_qty = Integer.toString(qty);
                            sumLicenceInputs =
                                    sumLicenceInputs + node.getQuantity();
                            countOfEFEntries++;
                        }
                        uiField2_refQty =
                                node.getReferenceQuantiy() == -1 ? "-1" :
                                Integer.toString(node.getReferenceQuantiy());
                        uiField2_tarQty =
                                node.getTargetQuantity() == -1 ? "-1" :
                                Integer.toString(node.getTargetQuantity());
                        uiField2_czModelName = node.getCzModelName();
                        uiField2_uiNodeName = node.getUiNodeName();
                    }
                    break;
                case 2:
                    if (listOfNodes.get(i) != null &&
                        listOfNodes.get(i).getConfigUiNodes() != null &&
                        !listOfNodes.get(i).getConfigUiNodes().isEmpty()) {
                        node = listOfNodes.get(i).getConfigUiNodes().get(0);
                        if (node.getReferenceQuantiy() != -1) {
                            sumRef = sumRef + node.getReferenceQuantiy();
                        }
                        if (node.getTargetQuantity() != -1) {
                            sumTar = sumTar + node.getTargetQuantity();
                        }
                        if (node != null && node.getIdentifier() != null &&
                            node.getIdentifier().equals("DISPLAYONLY")) {
                            displayOnlyLogic = true;
                            uiField13_type = "DISPLAYONLY";
                        }
                        if (displayOnlyLogic) {
                            if (node.isIsSelected()) {
                                numOfSelections++;
                            }
                        }
                        uiField3 = node.getUiNodeName();
                        selectedValue = subGrpName;
                        czNodeName3 = node.getCzNodeName();
                        uiField3_code = czNodeName3;
                        if (node.isDisplayReferenceColor())
                            uiField3_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField3_color = SudokuUtils.TARGET_COLOR;
                        if (node.isDisplayReferenceColor() &&
                            node.isDisplayTargetColor()) {
                            uiField3_color = SudokuUtils.TARGET_COLOR;
                        }
                        uiField3_dis = node.isDisableNode();
                        uiField3_readOnly = node.isReadOnly();
                        uiField3_identifier = node.getIdentifier();
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        int qty = node.getQuantity();
                        if (qty != -1) {
                            uiField3_qty = Integer.toString(qty);
                            sumLicenceInputs =
                                    sumLicenceInputs + node.getQuantity();
                            countOfEFEntries++;
                        }
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

                        //If an input is there ,disable other inputs
                        if (node.getQuantity() != -1) {
                            //some value already exists
                            countLicensesEntered++;
                            //disableMultipleInputs = true ;
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
                        uiField3_refQty =
                                node.getReferenceQuantiy() == -1 ? "-1" :
                                Integer.toString(node.getReferenceQuantiy());
                        uiField3_tarQty =
                                node.getTargetQuantity() == -1 ? "-1" :
                                Integer.toString(node.getTargetQuantity());
                        uiField3_czModelName = node.getCzModelName();
                        uiField3_uiNodeName = node.getUiNodeName();
                        //                        if (uiField3_dis) {
                        //                            uiField3_color = "Yellow";
                        //                        }
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
                        uiField4_code = czNodeName4;
                        if (node.getReferenceQuantiy() != -1) {
                            sumRef = sumRef + node.getReferenceQuantiy();
                        }
                        if (node.getTargetQuantity() != -1) {
                            sumTar = sumTar + node.getTargetQuantity();
                        }
                        if (node != null && node.getIdentifier() != null &&
                            node.getIdentifier().equals("DISPLAYONLY")) {
                            displayOnlyLogic = true;
                            uiField13_type = "DISPLAYONLY";
                        }
                        if (displayOnlyLogic) {
                            if (node.isIsSelected()) {
                                numOfSelections++;
                            }
                        }
                        if (node.isDisplayReferenceColor())
                            uiField4_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField4_color = SudokuUtils.TARGET_COLOR;
                        if (node.isDisplayReferenceColor() &&
                            node.isDisplayTargetColor()) {
                            uiField4_color = SudokuUtils.TARGET_COLOR;
                        }
                        uiField4_dis = node.isDisableNode();
                        uiField4_readOnly = node.isReadOnly();
                        uiField4_identifier = node.getIdentifier();
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        int qty = node.getQuantity();
                        if (qty != -1) {
                            uiField4_qty = Integer.toString(qty);
                            sumLicenceInputs =
                                    sumLicenceInputs + node.getQuantity();
                        }
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
                        //If an input is there ,disable other inputs
                        if (node.getQuantity() != -1) {
                            //some value already exists
                            sumLicenceInputs =
                                    sumLicenceInputs + node.getQuantity();
                            countLicensesEntered++;
                            countOfEFEntries++;
                            //disableMultipleInputs = true ;
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
                        uiField4_refQty =
                                node.getReferenceQuantiy() == -1 ? "-1" :
                                Integer.toString(node.getReferenceQuantiy());
                        uiField4_tarQty =
                                node.getTargetQuantity() == -1 ? "-1" :
                                Integer.toString(node.getTargetQuantity());
                        uiField4_czModelName = node.getCzModelName();
                        uiField4_uiNodeName = node.getUiNodeName();
                        //                        if (uiField4_dis) {
                        //                            uiField4_color = "Yellow";
                        //                        }
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
                        uiField5_code = czNodeName5;
                        if (node.getReferenceQuantiy() != -1) {
                            sumRef = sumRef + node.getReferenceQuantiy();
                        }
                        if (node.getTargetQuantity() != -1) {
                            sumTar = sumTar + node.getTargetQuantity();
                        }
                        if (node != null && node.getIdentifier() != null &&
                            node.getIdentifier().equals("DISPLAYONLY")) {
                            displayOnlyLogic = true;
                            uiField13_type = "DISPLAYONLY";
                        }
                        if (displayOnlyLogic) {
                            if (node.isIsSelected()) {
                                numOfSelections++;
                            }
                        }
                        if (node.isDisplayReferenceColor())
                            uiField5_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField5_color = SudokuUtils.TARGET_COLOR;
                        if (node.isDisplayReferenceColor() &&
                            node.isDisplayTargetColor()) {
                            uiField5_color = SudokuUtils.TARGET_COLOR;
                        }
                        uiField5_dis = node.isDisableNode();
                        uiField5_readOnly = node.isReadOnly();
                        uiField5_identifier = node.getIdentifier();
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        int qty = node.getQuantity();
                        if (qty != -1) {
                            uiField5_qty = Integer.toString(qty);
                            sumLicenceInputs =
                                    sumLicenceInputs + node.getQuantity();
                        }
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
                        //If an input is there ,disable other inputs
                        if (node.getQuantity() != -1) {
                            //some value already exists
                            countLicensesEntered++;
                            countOfEFEntries++;
                            //disableMultipleInputs = true ;
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
                        uiField5_refQty =
                                node.getReferenceQuantiy() == -1 ? "-1" :
                                Integer.toString(node.getReferenceQuantiy());
                        uiField5_tarQty =
                                node.getTargetQuantity() == -1 ? "-1" :
                                Integer.toString(node.getTargetQuantity());
                        uiField5_czModelName = node.getCzModelName();
                        uiField5_uiNodeName = node.getUiNodeName();
                        //                        if (uiField5_dis) {
                        //                            uiField5_color = "Yellow";
                        //                        }
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
                        uiField6_code = czNodeName6;
                        if (node.getReferenceQuantiy() != -1) {
                            sumRef = sumRef + node.getReferenceQuantiy();
                        }
                        if (node.getTargetQuantity() != -1) {
                            sumTar = sumTar + node.getTargetQuantity();
                        }
                        if (node.isDisplayReferenceColor())
                            uiField6_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField6_color = SudokuUtils.TARGET_COLOR;
                        if (node.isDisplayReferenceColor() &&
                            node.isDisplayTargetColor()) {
                            uiField6_color = SudokuUtils.TARGET_COLOR;
                        }
                        uiField6_dis = node.isDisableNode();
                        uiField6_readOnly = node.isReadOnly();
                        uiField6_identifier = node.getIdentifier();
                        if (node != null && node.getIdentifier() != null &&
                            node.getIdentifier().equals("DISPLAYONLY")) {
                            displayOnlyLogic = true;
                            uiField13_type = "DISPLAYONLY";
                        }
                        if (displayOnlyLogic) {
                            if (node.isIsSelected()) {
                                numOfSelections++;
                            }
                        }
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        int qty = node.getQuantity();
                        if (qty != -1) {
                            uiField6_qty = Integer.toString(qty);
                            sumLicenceInputs =
                                    sumLicenceInputs + node.getQuantity();
                        }
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
                        //If an input is there ,disable other inputs
                        if (node.getQuantity() != -1) {
                            //some value already exists
                            countLicensesEntered++;
                            countOfEFEntries++;
                            //disableMultipleInputs = true ;
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
                        uiField6_refQty =
                                node.getReferenceQuantiy() == -1 ? "-1" :
                                Integer.toString(node.getReferenceQuantiy());
                        uiField6_tarQty =
                                node.getTargetQuantity() == -1 ? "-1" :
                                Integer.toString(node.getTargetQuantity());
                        uiField6_czModelName = node.getCzModelName();
                        uiField6_uiNodeName = node.getUiNodeName();
                        //                        if (uiField6_dis) {
                        //                            uiField6_color = "Yellow";
                        //                        }
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
                        uiField7_code = czNodeName7;
                        if (node != null && node.getIdentifier() != null &&
                            node.getIdentifier().equals("DISPLAYONLY")) {
                            displayOnlyLogic = true;
                            uiField13_type = "DISPLAYONLY";
                        }
                        if (displayOnlyLogic) {
                            if (node.isIsSelected()) {
                                numOfSelections++;
                            }
                        }
                        if (node.getReferenceQuantiy() != -1) {
                            sumRef = sumRef + node.getReferenceQuantiy();
                        }
                        if (node.getTargetQuantity() != -1) {
                            sumTar = sumTar + node.getTargetQuantity();
                        }
                        if (node.isDisplayReferenceColor())
                            uiField7_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField7_color = SudokuUtils.TARGET_COLOR;
                        if (node.isDisplayReferenceColor() &&
                            node.isDisplayTargetColor()) {
                            uiField7_color = SudokuUtils.TARGET_COLOR;
                        }
                        uiField7_dis = node.isDisableNode();
                        uiField7_readOnly = node.isReadOnly();
                        uiField7_identifier = node.getIdentifier();
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        int qty = node.getQuantity();
                        if (qty != -1) {
                            uiField7_qty = Integer.toString(qty);
                            sumLicenceInputs =
                                    sumLicenceInputs + node.getQuantity();
                        }
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
                        //If an input is there ,disable other inputs
                        if (node.getQuantity() != -1) {
                            //some value already exists
                            countLicensesEntered++;
                            countOfEFEntries++;
                            //disableMultipleInputs = true ;
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
                        uiField7_refQty =
                                node.getReferenceQuantiy() == -1 ? "-1" :
                                Integer.toString(node.getReferenceQuantiy());
                        uiField7_tarQty =
                                node.getTargetQuantity() == -1 ? "-1" :
                                Integer.toString(node.getTargetQuantity());
                        uiField7_czModelName = node.getCzModelName();
                        uiField7_uiNodeName = node.getUiNodeName();
                        //                        if (uiField7_dis) {
                        //                            uiField7_color = "Yellow";
                        //                        }
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
                        uiField8_code = czNodeName8;
                        if (node != null && node.getIdentifier() != null &&
                            node.getIdentifier().equals("DISPLAYONLY")) {
                            displayOnlyLogic = true;
                            uiField13_type = "DISPLAYONLY";
                        }
                        if (displayOnlyLogic) {
                            if (node.isIsSelected()) {
                                numOfSelections++;
                            }
                        }
                        if (node.getReferenceQuantiy() != -1) {
                            sumRef = sumRef + node.getReferenceQuantiy();
                        }
                        if (node.getTargetQuantity() != -1) {
                            sumTar = sumTar + node.getTargetQuantity();
                        }
                        if (node.isDisplayReferenceColor())
                            uiField8_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField8_color = SudokuUtils.TARGET_COLOR;
                        if (node.isDisplayReferenceColor() &&
                            node.isDisplayTargetColor()) {
                            uiField8_color = SudokuUtils.TARGET_COLOR;
                        }
                        uiField8_dis = node.isDisableNode();
                        uiField8_readOnly = node.isReadOnly();
                        uiField8_identifier = node.getIdentifier();
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        int qty = node.getQuantity();
                        if (qty != -1) {
                            uiField8_qty = Integer.toString(qty);
                            sumLicenceInputs =
                                    sumLicenceInputs + node.getQuantity();
                        }
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
                        //If an input is there ,disable other inputs
                        if (node.getQuantity() != -1) {
                            //some value already exists
                            countLicensesEntered++;
                            countOfEFEntries++;
                            //disableMultipleInputs = true ;
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
                        uiField8_refQty =
                                node.getReferenceQuantiy() == -1 ? "-1" :
                                Integer.toString(node.getReferenceQuantiy());
                        uiField8_tarQty =
                                node.getTargetQuantity() == -1 ? "-1" :
                                Integer.toString(node.getTargetQuantity());
                        uiField8_czModelName = node.getCzModelName();
                        uiField8_uiNodeName = node.getUiNodeName();
                        //                        if (uiField8_dis) {
                        //                            uiField8_color = "Yellow";
                        //                        }
                    }
                    break;

                case 8:
                    if (listOfNodes.get(i) != null &&
                        listOfNodes.get(i).getConfigUiNodes() != null &&
                        !listOfNodes.get(i).getConfigUiNodes().isEmpty()) {
                        node = listOfNodes.get(i).getConfigUiNodes().get(0);
                        //Ui field 9 is to show the reference quantity
                        uiField9 = node.getUiNodeName();
                        selectedValue = subGrpName;
                        czNodeName9 = node.getCzNodeName();
                        if (node != null && node.getIdentifier() != null &&
                            node.getIdentifier().equals("DISPLAYONLY")) {
                            displayOnlyLogic = true;
                            uiField13_type = "DISPLAYONLY";
                        }
                        if (displayOnlyLogic) {
                            if (node.isIsSelected()) {
                                numOfSelections++;
                            }
                        }
                        uiField9_code = czNodeName9;
                        if (node.getReferenceQuantiy() != -1) {
                            sumRef = sumRef + node.getReferenceQuantiy();
                        }
                        if (node.getTargetQuantity() != -1) {
                            sumTar = sumTar + node.getTargetQuantity();
                        }
                        if (node.isDisplayReferenceColor())
                            uiField9_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField9_color = SudokuUtils.TARGET_COLOR;
                        if (node.isDisplayReferenceColor() &&
                            node.isDisplayTargetColor()) {
                            uiField9_color = SudokuUtils.TARGET_COLOR;
                        }
                        uiField9_dis = node.isDisableNode();
                        uiField9_readOnly = node.isReadOnly();
                        uiField9_identifier = node.getIdentifier();
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        int qty = node.getQuantity();
                        if (qty != -1) {
                            uiField9_qty = Integer.toString(qty);
                            sumLicenceInputs =
                                    sumLicenceInputs + node.getQuantity();
                        }
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
                        //If an input is there ,disable other inputs
                        if (node.getQuantity() != -1) {
                            //some value already exists
                            countLicensesEntered++;
                            countOfEFEntries++;
                            //disableMultipleInputs = true ;
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
                        uiField9_refQty =
                                node.getReferenceQuantiy() == -1 ? "-1" :
                                Integer.toString(node.getReferenceQuantiy());
                        uiField9_tarQty =
                                node.getTargetQuantity() == -1 ? "-1" :
                                Integer.toString(node.getTargetQuantity());
                        uiField9_czModelName = node.getCzModelName();
                        uiField9_uiNodeName = node.getUiNodeName();
                        //                        if (uiField9_dis) {
                        //                            uiField9_color = "Yellow";
                        //                        }
                    }
                    break;
                case 9:
                    if (listOfNodes.get(i) != null &&
                        listOfNodes.get(i).getConfigUiNodes() != null &&
                        !listOfNodes.get(i).getConfigUiNodes().isEmpty()) {
                        node = listOfNodes.get(i).getConfigUiNodes().get(0);
                        uiField10 = node.getUiNodeName();
                        selectedValue = subGrpName;
                        czNodeName10 = node.getCzNodeName();
                        uiField10_code = czNodeName10;

                        if (node.getReferenceQuantiy() != -1) {
                            sumRef = sumRef + node.getReferenceQuantiy();
                        }
                        if (node.getTargetQuantity() != -1) {
                            sumTar = sumTar + node.getTargetQuantity();
                        }
                        if (node.isDisplayReferenceColor())
                            uiField10_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField10_color = SudokuUtils.TARGET_COLOR;
                        if (node.isDisplayReferenceColor() &&
                            node.isDisplayTargetColor()) {
                            uiField10_color = SudokuUtils.TARGET_COLOR;
                        }
                        uiField10_dis = node.isDisableNode();
                        uiField10_readOnly = node.isReadOnly();
                        uiField10_identifier = node.getIdentifier();
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        int qty = node.getQuantity();
                        if (qty != -1) {
                            uiField10_qty = Integer.toString(qty);
                            sumLicenceInputs =
                                    sumLicenceInputs + node.getQuantity();
                        }
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
                        //If an input is there ,disable other inputs
                        if (node.getQuantity() != -1) {
                            //some value already exists
                            countLicensesEntered++;
                            countOfEFEntries++;
                            //disableMultipleInputs = true ;
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
                        uiField10_refQty =
                                node.getReferenceQuantiy() == -1 ? "-1" :
                                Integer.toString(node.getReferenceQuantiy());
                        uiField10_tarQty =
                                node.getTargetQuantity() == -1 ? "-1" :
                                Integer.toString(node.getTargetQuantity());
                        uiField10_czModelName = node.getCzModelName();
                        uiField10_uiNodeName = node.getUiNodeName();
                        //                        if (uiField10_dis) {
                        //                            uiField10_color = "Yellow";
                        //                        }
                    }
                    break;

                case 10:
                    if (listOfNodes.get(i) != null &&
                        listOfNodes.get(i).getConfigUiNodes() != null &&
                        !listOfNodes.get(i).getConfigUiNodes().isEmpty()) {
                        node = listOfNodes.get(i).getConfigUiNodes().get(0);
                        uiField11 = node.getUiNodeName();
                        selectedValue = subGrpName;
                        czNodeName11 = node.getCzNodeName();
                        uiField11_code = czNodeName11;

                        if (node.getReferenceQuantiy() != -1) {
                            sumRef = sumRef + node.getReferenceQuantiy();
                        }
                        if (node.getTargetQuantity() != -1) {
                            sumTar = sumTar + node.getTargetQuantity();
                        }
                        if (node.isDisplayReferenceColor())
                            uiField11_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField11_color = SudokuUtils.TARGET_COLOR;
                        if (node.isDisplayReferenceColor() &&
                            node.isDisplayTargetColor()) {
                            uiField11_color = SudokuUtils.TARGET_COLOR;
                        }
                        uiField11_dis = node.isDisableNode();
                        uiField11_readOnly = node.isReadOnly();
                        uiField11_identifier = node.getIdentifier();
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        int qty = node.getQuantity();
                        if (qty != -1) {
                            uiField11_qty = Integer.toString(qty);
                            sumLicenceInputs =
                                    sumLicenceInputs + node.getQuantity();
                        }
                        switch (uiElementType) {
                        case 1:
                            uiField11_type = "NUMERIC";
                        case 2:
                            uiField11_type = "TEXT";
                        case 3:
                            uiField11_type = "OPTION";
                        case 4:
                            uiField11_type = "LISTOFVALUES";

                        }
                        //If an input is there ,disable other inputs
                        if (node.getQuantity() != -1) {
                            //some value already exists
                            countLicensesEntered++;
                            countOfEFEntries++;
                            //disableMultipleInputs = true ;
                        }
                        if (uiField11_dis) {
                            uiField11_bgColor = "color:Silver;";
                        }
                        if (uiField11_readOnly) {
                            uiField11_bgColor = "color:#d9b3ff;";
                        }
                        if (uiField11_color != null) {
                            uiField11_bgColor = "color:InfoBackground";
                        }
                        uiField11_refQty =
                                node.getReferenceQuantiy() == -1 ? "-1" :
                                Integer.toString(node.getReferenceQuantiy());
                        uiField11_tarQty =
                                node.getTargetQuantity() == -1 ? "-1" :
                                Integer.toString(node.getTargetQuantity());
                        uiField11_czModelName = node.getCzModelName();
                        uiField11_uiNodeName = node.getUiNodeName();
                        //                            if (uiField11_dis) {
                        //                                uiField11_color = "Yellow";
                        //                            }
                    }
                    break;

                case 11:
                    if (listOfNodes.get(i) != null &&
                        listOfNodes.get(i).getConfigUiNodes() != null &&
                        !listOfNodes.get(i).getConfigUiNodes().isEmpty()) {
                        node = listOfNodes.get(i).getConfigUiNodes().get(0);
                        uiField12 = node.getUiNodeName();
                        System.out.println("Field 12 value " + uiField12);
                        selectedValue = subGrpName;
                        czNodeName12 = node.getCzNodeName();
                        uiField12_code = czNodeName12;

                        if (node.getReferenceQuantiy() != -1) {
                            sumRef = sumRef + node.getReferenceQuantiy();
                        }
                        if (node.getTargetQuantity() != -1) {
                            sumTar = sumTar + node.getTargetQuantity();
                        }
                        if (node.isDisplayReferenceColor())
                            uiField12_color = SudokuUtils.REFERENCE_COLOR;
                        else if (node.isDisplayTargetColor())
                            uiField12_color = SudokuUtils.TARGET_COLOR;
                        if (node.isDisplayReferenceColor() &&
                            node.isDisplayTargetColor()) {
                            uiField12_color = SudokuUtils.TARGET_COLOR;
                        }
                        uiField12_dis = node.isDisableNode();
                        uiField12_readOnly = node.isReadOnly();
                        uiField12_identifier = node.getIdentifier();
                        ConfiguratorUiElement uiElement = listOfNodes.get(i);
                        int uiElementType = uiElement.getUiElementType();
                        int qty = node.getQuantity();
                        if (qty != -1) {
                            uiField12_qty = Integer.toString(qty);
                            sumLicenceInputs =
                                    sumLicenceInputs + node.getQuantity();
                        }
                        switch (uiElementType) {
                        case 1:
                            uiField12_type = "NUMERIC";
                        case 2:
                            uiField12_type = "TEXT";
                        case 3:
                            uiField12_type = "OPTION";
                        case 4:
                            uiField12_type = "LISTOFVALUES";

                        }
                        //If an input is there ,disable other inputs
                        if (node.getQuantity() != -1) {
                            //some value already exists
                            countLicensesEntered++;
                            countOfEFEntries++;
                            //disableMultipleInputs = true ;
                        }
                        if (uiField12_dis) {
                            uiField12_bgColor = "color:Silver;";
                        }
                        if (uiField12_readOnly) {
                            uiField12_bgColor = "color:#d9b3ff;";
                        }
                        if (uiField12_color != null) {
                            uiField12_bgColor = "color:InfoBackground";
                        }
                        uiField12_refQty =
                                node.getReferenceQuantiy() == -1 ? "-1" :
                                Integer.toString(node.getReferenceQuantiy());
                        uiField12_tarQty =
                                node.getTargetQuantity() == -1 ? "-1" :
                                Integer.toString(node.getTargetQuantity());
                        uiField12_czModelName = node.getCzModelName();
                        uiField12_uiNodeName = node.getUiNodeName();
                        //                            if (uiField12_dis) {
                        //                                uiField12_color = "Yellow";
                        //                            }
                    }
                    break;

                }
            }
        }
        if (numOfSelections > 0 && displayOnlyLogic) {
            uiField13 = Integer.toString(1);
        }
        if (numOfSelections == 0 && displayOnlyLogic) {
            uiField13 = Integer.toString(0);
        }

        if (sumLicenceInputs != 0) {
            digitsEntered = Integer.toString(sumLicenceInputs);
        }
        if (sumTar != 0) {
            sumTarget = Integer.toString(sumTar);
        }
        if (sumRef != 0) {
            sumReference = Integer.toString(sumRef);
        }
        if (countLicensesEntered >= 2) {
            disableMultipleInputs = true;
        }
        if (sumInsRef != 0) {
            sumInstaBaseRef = Integer.toString(sumInsRef);
        }
        if (sumInsTar != 0) {
            sumInstaBaseTar = Integer.toString(sumInsTar);
        }
        if (countOfEFEntries >= 2) {
            disableEFCol = true;
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

    public void setUiGrpIndex(String uiGrpIndex) {
        this.uiGrpIndex = uiGrpIndex;
    }

    public String getUiGrpIndex() {
        return uiGrpIndex;
    }

    public void setDigitsEntered(String digitsEntered) {
        this.digitsEntered = digitsEntered;
    }

    public String getDigitsEntered() {
        return digitsEntered;
    }

    public void setUiField1_qty(String uiField1_qty) {
        this.uiField1_qty = uiField1_qty;
    }

    public String getUiField1_qty() {
        return uiField1_qty;
    }

    public void setUiField2_qty(String uiField2_qty) {
        this.uiField2_qty = uiField2_qty;
    }

    public String getUiField2_qty() {
        return uiField2_qty;
    }

    public void setUiField3_qty(String uiField3_qty) {
        this.uiField3_qty = uiField3_qty;
    }

    public String getUiField3_qty() {
        return uiField3_qty;
    }

    public void setUiField4_qty(String uiField4_qty) {
        this.uiField4_qty = uiField4_qty;
    }

    public String getUiField4_qty() {
        return uiField4_qty;
    }

    public void setUiField5_qty(String uiField5_qty) {
        this.uiField5_qty = uiField5_qty;
    }

    public String getUiField5_qty() {
        return uiField5_qty;
    }

    public void setUiField6_qty(String uiField6_qty) {
        this.uiField6_qty = uiField6_qty;
    }

    public String getUiField6_qty() {
        return uiField6_qty;
    }

    public void setUiField7_qty(String uiField7_qty) {
        this.uiField7_qty = uiField7_qty;
    }

    public String getUiField7_qty() {
        return uiField7_qty;
    }

    public void setUiField8_qty(String uiField8_qty) {
        this.uiField8_qty = uiField8_qty;
    }

    public String getUiField8_qty() {
        return uiField8_qty;
    }

    public void setUiField9_qty(String uiField9_qty) {
        this.uiField9_qty = uiField9_qty;
    }

    public String getUiField9_qty() {
        return uiField9_qty;
    }

    public void setUiField10_qty(String uiField10_qty) {
        this.uiField10_qty = uiField10_qty;
    }

    public String getUiField10_qty() {
        return uiField10_qty;
    }

    public void setListOfValueModel(List<SelectItem> listOfValueModel) {
        this.listOfValueModel = listOfValueModel;
    }

    public List<SelectItem> getListOfValueModel() {
        return listOfValueModel;
    }

    public void setUiField11(String uiField11) {
        this.uiField11 = uiField11;
    }

    public String getUiField11() {
        return uiField11;
    }

    public void setUiField12(String uiField12) {
        this.uiField12 = uiField12;
    }

    public String getUiField12() {
        return uiField12;
    }

    public void setUiField11_color(String uiField11_color) {
        this.uiField11_color = uiField11_color;
    }

    public String getUiField11_color() {
        return uiField11_color;
    }

    public void setUiField12_color(String uiField12_color) {
        this.uiField12_color = uiField12_color;
    }

    public String getUiField12_color() {
        return uiField12_color;
    }

    public void setUiField11_code(String uiField11_code) {
        this.uiField11_code = uiField11_code;
    }

    public String getUiField11_code() {
        return uiField11_code;
    }

    public void setUiField12_code(String uiField12_code) {
        this.uiField12_code = uiField12_code;
    }

    public String getUiField12_code() {
        return uiField12_code;
    }

    public void setUiField11_type(String uiField11_type) {
        this.uiField11_type = uiField11_type;
    }

    public String getUiField11_type() {
        return uiField11_type;
    }

    public void setUiField12_type(String uiField12_type) {
        this.uiField12_type = uiField12_type;
    }

    public String getUiField12_type() {
        return uiField12_type;
    }

    public void setCzNodeName11(String czNodeName11) {
        this.czNodeName11 = czNodeName11;
    }

    public String getCzNodeName11() {
        return czNodeName11;
    }

    public void setCzNodeName12(String czNodeName12) {
        this.czNodeName12 = czNodeName12;
    }

    public String getCzNodeName12() {
        return czNodeName12;
    }

    public void setUiField11_qty(String uiField11_qty) {
        this.uiField11_qty = uiField11_qty;
    }

    public String getUiField11_qty() {
        return uiField11_qty;
    }

    public void setUiField12_qty(String uiField12_qty) {
        this.uiField12_qty = uiField12_qty;
    }

    public String getUiField12_qty() {
        return uiField12_qty;
    }

    public void setUiField11_dis(Boolean uiField11_dis) {
        this.uiField11_dis = uiField11_dis;
    }

    public Boolean getUiField11_dis() {
        return uiField11_dis;
    }

    public void setUiField12_dis(Boolean uiField12_dis) {
        this.uiField12_dis = uiField12_dis;
    }

    public Boolean getUiField12_dis() {
        return uiField12_dis;
    }

    public void setUiField11_readOnly(Boolean uiField11_readOnly) {
        this.uiField11_readOnly = uiField11_readOnly;
    }

    public Boolean getUiField11_readOnly() {
        return uiField11_readOnly;
    }

    public void setUiField12_readOnly(Boolean uiField12_readOnly) {
        this.uiField12_readOnly = uiField12_readOnly;
    }

    public Boolean getUiField12_readOnly() {
        return uiField12_readOnly;
    }

    public void setUiField11_identifier(String uiField11_identifier) {
        this.uiField11_identifier = uiField11_identifier;
    }

    public String getUiField11_identifier() {
        return uiField11_identifier;
    }

    public void setUiField12_identifier(String uiField12_identifier) {
        this.uiField12_identifier = uiField12_identifier;
    }

    public String getUiField12_identifier() {
        return uiField12_identifier;
    }

    public void setUiField11_bgColor(String uiField11_bgColor) {
        this.uiField11_bgColor = uiField11_bgColor;
    }

    public String getUiField11_bgColor() {
        return uiField11_bgColor;
    }

    public void setUiField12_bgColor(String uiField12_bgColor) {
        this.uiField12_bgColor = uiField12_bgColor;
    }

    public String getUiField12_bgColor() {
        return uiField12_bgColor;
    }

    public void setDisableMultipleInputs(Boolean disableMultipleInputs) {
        this.disableMultipleInputs = disableMultipleInputs;
    }

    public Boolean getDisableMultipleInputs() {
        return disableMultipleInputs;
    }

    public void setSumReference(String sumReference) {
        this.sumReference = sumReference;
    }

    public String getSumReference() {
        return sumReference;
    }

    public void setSumTarget(String sumTarget) {
        this.sumTarget = sumTarget;
    }

    public String getSumTarget() {
        return sumTarget;
    }

    public void setSumInstaBaseRef(String sumInstaBaseRef) {
        this.sumInstaBaseRef = sumInstaBaseRef;
    }

    public String getSumInstaBaseRef() {
        return sumInstaBaseRef;
    }

    public void setSumInstaBaseTar(String sumInstaBaseTar) {
        this.sumInstaBaseTar = sumInstaBaseTar;
    }

    public String getSumInstaBaseTar() {
        return sumInstaBaseTar;
    }

    public void setUiField13(String uiField13) {
        this.uiField13 = uiField13;
    }

    public String getUiField13() {
        return uiField13;
    }

    public void setUiField13_color(String uiField13_color) {
        this.uiField13_color = uiField13_color;
    }

    public String getUiField13_color() {
        return uiField13_color;
    }

    public void setUiField13_code(String uiField13_code) {
        this.uiField13_code = uiField13_code;
    }

    public String getUiField13_code() {
        return uiField13_code;
    }

    public void setUiField13_type(String uiField13_type) {
        this.uiField13_type = uiField13_type;
    }

    public String getUiField13_type() {
        return uiField13_type;
    }

    public void setCzNodeName13(String czNodeName13) {
        this.czNodeName13 = czNodeName13;
    }

    public String getCzNodeName13() {
        return czNodeName13;
    }

    public void setUiField13_qty(String uiField13_qty) {
        this.uiField13_qty = uiField13_qty;
    }

    public String getUiField13_qty() {
        return uiField13_qty;
    }

    public void setUiField13_dis(Boolean uiField13_dis) {
        this.uiField13_dis = uiField13_dis;
    }

    public Boolean getUiField13_dis() {
        return uiField13_dis;
    }

    public void setUiField13_readOnly(Boolean uiField13_readOnly) {
        this.uiField13_readOnly = uiField13_readOnly;
    }

    public Boolean getUiField13_readOnly() {
        return uiField13_readOnly;
    }

    public void setUiField13_identifier(String uiField13_identifier) {
        this.uiField13_identifier = uiField13_identifier;
    }

    public String getUiField13_identifier() {
        return uiField13_identifier;
    }

    public void setUiField13_bgColor(String uiField13_bgColor) {
        this.uiField13_bgColor = uiField13_bgColor;
    }

    public String getUiField13_bgColor() {
        return uiField13_bgColor;
    }

    public void setIdentifyLicenceRow(String identifyLicenceRow) {
        this.identifyLicenceRow = identifyLicenceRow;
    }

    public String getIdentifyLicenceRow() {
        return identifyLicenceRow;
    }

    public void setUiField1_refQty(String uiField1_refQty) {
        this.uiField1_refQty = uiField1_refQty;
    }

    public String getUiField1_refQty() {
        return uiField1_refQty;
    }

    public void setUiField2_refQty(String uiField2_refQty) {
        this.uiField2_refQty = uiField2_refQty;
    }

    public String getUiField2_refQty() {
        return uiField2_refQty;
    }

    public void setUiField3_refQty(String uiField3_refQty) {
        this.uiField3_refQty = uiField3_refQty;
    }

    public String getUiField3_refQty() {
        return uiField3_refQty;
    }

    public void setUiField4_refQty(String uiField4_refQty) {
        this.uiField4_refQty = uiField4_refQty;
    }

    public String getUiField4_refQty() {
        return uiField4_refQty;
    }

    public void setUiField5_refQty(String uiField5_refQty) {
        this.uiField5_refQty = uiField5_refQty;
    }

    public String getUiField5_refQty() {
        return uiField5_refQty;
    }

    public void setUiField6_refQty(String uiField6_refQty) {
        this.uiField6_refQty = uiField6_refQty;
    }

    public String getUiField6_refQty() {
        return uiField6_refQty;
    }

    public void setUiField7_refQty(String uiField7_refQty) {
        this.uiField7_refQty = uiField7_refQty;
    }

    public String getUiField7_refQty() {
        return uiField7_refQty;
    }

    public void setUiField8_refQty(String uiField8_refQty) {
        this.uiField8_refQty = uiField8_refQty;
    }

    public String getUiField8_refQty() {
        return uiField8_refQty;
    }

    public void setUiField9_refQty(String uiField9_refQty) {
        this.uiField9_refQty = uiField9_refQty;
    }

    public String getUiField9_refQty() {
        return uiField9_refQty;
    }

    public void setUiField10_refQty(String uiField10_refQty) {
        this.uiField10_refQty = uiField10_refQty;
    }

    public String getUiField10_refQty() {
        return uiField10_refQty;
    }

    public void setUiField11_refQty(String uiField11_refQty) {
        this.uiField11_refQty = uiField11_refQty;
    }

    public String getUiField11_refQty() {
        return uiField11_refQty;
    }

    public void setUiField12_refQty(String uiField12_refQty) {
        this.uiField12_refQty = uiField12_refQty;
    }

    public String getUiField12_refQty() {
        return uiField12_refQty;
    }

    public void setUiField13_refQty(String uiField13_refQty) {
        this.uiField13_refQty = uiField13_refQty;
    }

    public String getUiField13_refQty() {
        return uiField13_refQty;
    }

    public void setUiField1_tarQty(String uiField1_tarQty) {
        this.uiField1_tarQty = uiField1_tarQty;
    }

    public String getUiField1_tarQty() {
        return uiField1_tarQty;
    }

    public void setUiField2_tarQty(String uiField2_tarQty) {
        this.uiField2_tarQty = uiField2_tarQty;
    }

    public String getUiField2_tarQty() {
        return uiField2_tarQty;
    }

    public void setUiField3_tarQty(String uiField3_tarQty) {
        this.uiField3_tarQty = uiField3_tarQty;
    }

    public String getUiField3_tarQty() {
        return uiField3_tarQty;
    }

    public void setUiField4_tarQty(String uiField4_tarQty) {
        this.uiField4_tarQty = uiField4_tarQty;
    }

    public String getUiField4_tarQty() {
        return uiField4_tarQty;
    }

    public void setUiField5_tarQty(String uiField5_tarQty) {
        this.uiField5_tarQty = uiField5_tarQty;
    }

    public String getUiField5_tarQty() {
        return uiField5_tarQty;
    }

    public void setUiField6_tarQty(String uiField6_tarQty) {
        this.uiField6_tarQty = uiField6_tarQty;
    }

    public String getUiField6_tarQty() {
        return uiField6_tarQty;
    }

    public void setUiField7_tarQty(String uiField7_tarQty) {
        this.uiField7_tarQty = uiField7_tarQty;
    }

    public String getUiField7_tarQty() {
        return uiField7_tarQty;
    }

    public void setUiField8_tarQty(String uiField8_tarQty) {
        this.uiField8_tarQty = uiField8_tarQty;
    }

    public String getUiField8_tarQty() {
        return uiField8_tarQty;
    }

    public void setUiField9_tarQty(String uiField9_tarQty) {
        this.uiField9_tarQty = uiField9_tarQty;
    }

    public String getUiField9_tarQty() {
        return uiField9_tarQty;
    }

    public void setUiField10_tarQty(String uiField10_tarQty) {
        this.uiField10_tarQty = uiField10_tarQty;
    }

    public String getUiField10_tarQty() {
        return uiField10_tarQty;
    }

    public void setUiField11_tarQty(String uiField11_tarQty) {
        this.uiField11_tarQty = uiField11_tarQty;
    }

    public String getUiField11_tarQty() {
        return uiField11_tarQty;
    }

    public void setUiField12_tarQty(String uiField12_tarQty) {
        this.uiField12_tarQty = uiField12_tarQty;
    }

    public String getUiField12_tarQty() {
        return uiField12_tarQty;
    }

    public void setUiField13_tarQty(String uiField13_tarQty) {
        this.uiField13_tarQty = uiField13_tarQty;
    }

    public String getUiField13_tarQty() {
        return uiField13_tarQty;
    }

    public void setUiField1_uiNodeName(String uiField1_uiNodeName) {
        this.uiField1_uiNodeName = uiField1_uiNodeName;
    }

    public String getUiField1_uiNodeName() {
        return uiField1_uiNodeName;
    }

    public void setUiField2_uiNodeName(String uiField2_uiNodeName) {
        this.uiField2_uiNodeName = uiField2_uiNodeName;
    }

    public String getUiField2_uiNodeName() {
        return uiField2_uiNodeName;
    }

    public void setUiField3_uiNodeName(String uiField3_uiNodeName) {
        this.uiField3_uiNodeName = uiField3_uiNodeName;
    }

    public String getUiField3_uiNodeName() {
        return uiField3_uiNodeName;
    }

    public void setUiField4_uiNodeName(String uiField4_uiNodeName) {
        this.uiField4_uiNodeName = uiField4_uiNodeName;
    }

    public String getUiField4_uiNodeName() {
        return uiField4_uiNodeName;
    }

    public void setUiField5_uiNodeName(String uiField5_uiNodeName) {
        this.uiField5_uiNodeName = uiField5_uiNodeName;
    }

    public String getUiField5_uiNodeName() {
        return uiField5_uiNodeName;
    }

    public void setUiField6_uiNodeName(String uiField6_uiNodeName) {
        this.uiField6_uiNodeName = uiField6_uiNodeName;
    }

    public String getUiField6_uiNodeName() {
        return uiField6_uiNodeName;
    }

    public void setUiField7_uiNodeName(String uiField7_uiNodeName) {
        this.uiField7_uiNodeName = uiField7_uiNodeName;
    }

    public String getUiField7_uiNodeName() {
        return uiField7_uiNodeName;
    }

    public void setUiField8_uiNodeName(String uiField8_uiNodeName) {
        this.uiField8_uiNodeName = uiField8_uiNodeName;
    }

    public String getUiField8_uiNodeName() {
        return uiField8_uiNodeName;
    }

    public void setUiField9_uiNodeName(String uiField9_uiNodeName) {
        this.uiField9_uiNodeName = uiField9_uiNodeName;
    }

    public String getUiField9_uiNodeName() {
        return uiField9_uiNodeName;
    }

    public void setUiField10_uiNodeName(String uiField10_uiNodeName) {
        this.uiField10_uiNodeName = uiField10_uiNodeName;
    }

    public String getUiField10_uiNodeName() {
        return uiField10_uiNodeName;
    }

    public void setUiField11_uiNodeName(String uiField11_uiNodeName) {
        this.uiField11_uiNodeName = uiField11_uiNodeName;
    }

    public String getUiField11_uiNodeName() {
        return uiField11_uiNodeName;
    }

    public void setUiField12_uiNodeName(String uiField12_uiNodeName) {
        this.uiField12_uiNodeName = uiField12_uiNodeName;
    }

    public String getUiField12_uiNodeName() {
        return uiField12_uiNodeName;
    }

    public void setUiField13_uiNodeName(String uiField13_uiNodeName) {
        this.uiField13_uiNodeName = uiField13_uiNodeName;
    }

    public String getUiField13_uiNodeName() {
        return uiField13_uiNodeName;
    }

    public void setUiField1_czModelName(String uiField1_czModelName) {
        this.uiField1_czModelName = uiField1_czModelName;
    }

    public String getUiField1_czModelName() {
        return uiField1_czModelName;
    }

    public void setUiField2_czModelName(String uiField2_czModelName) {
        this.uiField2_czModelName = uiField2_czModelName;
    }

    public String getUiField2_czModelName() {
        return uiField2_czModelName;
    }

    public void setUiField3_czModelName(String uiField3_czModelName) {
        this.uiField3_czModelName = uiField3_czModelName;
    }

    public String getUiField3_czModelName() {
        return uiField3_czModelName;
    }

    public void setUiField4_czModelName(String uiField4_czModelName) {
        this.uiField4_czModelName = uiField4_czModelName;
    }

    public String getUiField4_czModelName() {
        return uiField4_czModelName;
    }

    public void setUiField5_czModelName(String uiField5_czModelName) {
        this.uiField5_czModelName = uiField5_czModelName;
    }

    public String getUiField5_czModelName() {
        return uiField5_czModelName;
    }

    public void setUiField6_czModelName(String uiField6_czModelName) {
        this.uiField6_czModelName = uiField6_czModelName;
    }

    public String getUiField6_czModelName() {
        return uiField6_czModelName;
    }

    public void setUiField7_czModelName(String uiField7_czModelName) {
        this.uiField7_czModelName = uiField7_czModelName;
    }

    public String getUiField7_czModelName() {
        return uiField7_czModelName;
    }

    public void setUiField8_czModelName(String uiField8_czModelName) {
        this.uiField8_czModelName = uiField8_czModelName;
    }

    public String getUiField8_czModelName() {
        return uiField8_czModelName;
    }

    public void setUiField9_czModelName(String uiField9_czModelName) {
        this.uiField9_czModelName = uiField9_czModelName;
    }

    public String getUiField9_czModelName() {
        return uiField9_czModelName;
    }

    public void setUiField10_czModelName(String uiField10_czModelName) {
        this.uiField10_czModelName = uiField10_czModelName;
    }

    public String getUiField10_czModelName() {
        return uiField10_czModelName;
    }

    public void setUiField11_czModelName(String uiField11_czModelName) {
        this.uiField11_czModelName = uiField11_czModelName;
    }

    public String getUiField11_czModelName() {
        return uiField11_czModelName;
    }

    public void setUiField12_czModelName(String uiField12_czModelName) {
        this.uiField12_czModelName = uiField12_czModelName;
    }

    public String getUiField12_czModelName() {
        return uiField12_czModelName;
    }

    public void setUiField13_czModelName(String uiField13_czModelName) {
        this.uiField13_czModelName = uiField13_czModelName;
    }

    public String getUiField13_czModelName() {
        return uiField13_czModelName;
    }

    public void setSelectedLovValue(String selectedLovValue) {
        this.selectedLovValue = selectedLovValue;
    }

    public String getSelectedLovValue() {
        return selectedLovValue;
    }

    public void setLicenceRowIndex(String licenceRowIndex) {
        this.licenceRowIndex = licenceRowIndex;
    }

    public String getLicenceRowIndex() {
        return licenceRowIndex;
    }

    public void setDisableEFCol(Boolean disableEFCol) {
        this.disableEFCol = disableEFCol;
    }

    public Boolean getDisableEFCol() {
        return disableEFCol;
    }
}
