package xxatcust.oracle.apps.sudoku.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.output.RichOutputFormatted;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.server.DBTransaction;

import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;

import xxatcust.oracle.apps.sudoku.model.module.SudokuAMImpl;
import xxatcust.oracle.apps.sudoku.util.ADFUtils;
import xxatcust.oracle.apps.sudoku.util.JaxbParser;
import xxatcust.oracle.apps.sudoku.util.NodeComparator;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.ConfiguratorNodePOJO;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.NodeCategory;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.QuoteLinePOJO;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.V93kQuote;


public class TargetConfiguration {
    private RichOutputText pageInitText;
    ChildPropertyTreeModel categoryTree;
    private RichInputText userInputFileName;
    private RichPopup downloadPopup;
    List<ConfiguratorNodePOJO> allNodes;
    V93kQuote v93Obj;
    private ArrayList<NodeCategory> root;
    ChildPropertyTreeModel categroryTreeLineTwo;
    private RichPopup errPopup;
    private RichOutputFormatted errorText;
    private RichOutputText quoteTotal;
    private RichPanelSplitter panelSplitterBind;
    private ArrayList<ListViewModel> listViewCollection;
    private RichOutputText expertMode;
    private RichOutputFormatted refText;
    private RichPopup refreshPopup;
    private Boolean readOnlyUI = true;
    public TargetConfiguration() {
    }

    public void setPageInitText(RichOutputText pageInitText) {
        this.pageInitText = pageInitText;
    }

    public void refreshView(ActionEvent actionEvent) {
        ADFUtils.setSessionScopeValue("refreshImport", "Y");
        //Trying to clear previous values here
        //quoteTotal.setValue(null);
        categoryTree = null;
        allNodes = null;
        listViewCollection = null;
        if (expertMode != null) {
            expertMode.setValue(null);
        }
        if(refreshPopup!=null){
            refreshPopup.cancel();
        }
    }

    public RichOutputText getPageInitText() {
        refreshView(null);
        getListViewCollection();
        exportDownload(null);
        UIComponent ui = ADFUtils.findComponentInRoot("psexconfig");
        if (ui != null) {
            String cancelAll =
                (String)ADFUtils.getSessionScopeValue("cancelAll");
            if (cancelAll != null && cancelAll.equalsIgnoreCase("Y")) {
                // quoteTotal.setValue(null);
            }
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            if (refreshPopup != null) {
                
                refreshPopup.show(hints);
                refreshPopup.hide();
                refreshPopup.cancel();
                ADFUtils.addPartialTarget(refreshPopup.getParent());
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
            ADFUtils.addPartialTarget(ui);
        }
        return pageInitText;
    }

    public void exportDownload(ActionEvent actionEvent) {
        UIComponent ui = ADFUtils.findComponentInRoot("psexconfig");
        if (ui != null) {
            AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
        }
    }

    public ChildPropertyTreeModel getCategoryTree() {

        return categoryTree;

    }

    public void initTargetConfiguration() {
        UIComponent ui = ADFUtils.findComponentInRoot("psexconfig");
        if (quoteTotal != null) {
            getPageInitText();
        }
        if (ui != null) {
            ADFUtils.addPartialTarget(ui);
        }
    }

    public void setUserInputFileName(RichInputText userInputFileName) {
        this.userInputFileName = userInputFileName;
    }

    public RichInputText getUserInputFileName() {
        return userInputFileName;
    }


    private LinkedHashMap<ChildPropertyTreeModel, Double> createChildrenTrees() {
        LinkedHashMap<ChildPropertyTreeModel, Double> mapOfChildren =
            new LinkedHashMap<ChildPropertyTreeModel, Double>();
        List<ChildPropertyTreeModel> listOfTrees =
            new ArrayList<ChildPropertyTreeModel>();
        try {
            StringBuilder errMessage = new StringBuilder();
            V93kQuote parentObj =
                (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
            if (parentObj != null) {
                V93kQuote obj = (V93kQuote)parentObj;
                //Check if no exceptions from configurator
                if (obj.getExceptionMap() != null) {
                    TreeMap<String, ArrayList<String>> exceptionMap =
                        obj.getExceptionMap().getErrorList();


                    List<String> errorMessages =
                        obj.getExceptionMap().getErrorsMessages();

                    if (exceptionMap != null && exceptionMap.size() > 0) {

                        for (Map.Entry<String, ArrayList<String>> entry :
                             exceptionMap.entrySet()) {
                            String key = entry.getKey();
                            ArrayList<String> value = entry.getValue();
                            for (String str : value) {
                                errMessage.append(str);
                            }
                        }
                    }
                    if (errorMessages != null && errorMessages.size() > 0) {
                        for (String str : errorMessages) {
                            errMessage.append(str);
                        }
                    }

                }
                //Check for Expert Mode
                boolean reloadInExpertMode = false;
                if (obj.getSessionDetails() != null) {
                    reloadInExpertMode =
                            obj.getSessionDetails().isReloadInExpertMode();
                }
                if (expertMode != null) {
                    if (reloadInExpertMode) {
                        expertMode.setValue("Configuration switched to 93k expert mode");
                    } else {
                        expertMode.setValue(null);
                    }
                }

                if (errMessage != null) {
                    Double sumQuoteTotal = new Double(0);


                    List targetLines = obj.getTargetConfigurationLines();
                    if (targetLines != null && !targetLines.isEmpty()) {
                        //in a loop call the getAllNodes
                        double lineQuoteAmount = new Double(0);
                        for (int i = 0; i < targetLines.size(); i++) {
                            List<String> catList = new ArrayList<String>();
                            List<String> distinctList =
                                new ArrayList<String>();
                            List<ConfiguratorNodePOJO> allNodesList =
                                getAllNodes(i);
                            LinkedHashMap<String, List<ConfiguratorNodePOJO>> allNodesByCategoriesMap =
                                new LinkedHashMap<String, List<ConfiguratorNodePOJO>>();
                            if (allNodesList != null &&
                                !allNodesList.isEmpty()) {
                                int index = 0;
                                for (ConfiguratorNodePOJO node :
                                     allNodesList) {

                                    if (node.getPrintGroupLevel() != null &&
                                        (node.getPrintGroupLevel().equalsIgnoreCase("1") ||
                                         node.getPrintGroupLevel().equalsIgnoreCase("2") ||
                                         node.getPrintGroupLevel().equalsIgnoreCase("3") ||
                                         node.getPrintGroupLevel().equalsIgnoreCase("4"))) {
                                        //                                        if (node.getExtendedPrice() != null) {
                                        //                                            lineQuoteAmount =
                                        //                                                new Double(node.getExtendedPrice());
                                        //                                            //                                            sumQuoteTotal = sumQuoteTotal + b;
                                        //                                            //                                            lineQuoteAmount = new Double( node.getExtendedPrice());
                                        //                                            //                                            System.out.println("Sum Total "+sumQuoteTotal);
                                        //                                        }
                                    }
                                    if (node.getNodeCategory() != null &&
                                        node.getPrintGroupLevel() != null) {
                                        catList.add(node.getNodeCategory() +
                                                    "-" +
                                                    (node.getPrintGroupLevel() !=
                                                     null ?
                                                     node.getPrintGroupLevel() :
                                                     "0"));
                                    }
                                }
                            }

                            //quoteTotal.setValue(sumQuoteTotal);
                            distinctList = removeDuplicatesFromList(catList);
                            for (String distinctCategory : distinctList) {
                                List<ConfiguratorNodePOJO> temp =
                                    new ArrayList<ConfiguratorNodePOJO>();
                                if (allNodesList != null &&
                                    !allNodesList.isEmpty()) {
                                    for (ConfiguratorNodePOJO node :
                                         allNodesList) {
                                        if (distinctCategory != null &&
                                            distinctCategory.equalsIgnoreCase(node.getNodeCategory() +
                                                                              "-" +
                                                                              node.getPrintGroupLevel())) {
                                            temp.add(node);
                                        }
                                    }
                                }
                                allNodesByCategoriesMap.put(distinctCategory,
                                                            temp);
                            }
                            root = new ArrayList<NodeCategory>();
                            Iterator it =
                                allNodesByCategoriesMap.entrySet().iterator();
                            NodeCategory firstLevel = null;
                            int index = 0;
                            while (it.hasNext()) {
                                Map.Entry pair = (Map.Entry)it.next();
                                String Key = (String)pair.getKey();
                                String[] arr = Key.split("-");
                                String category = arr[0];
                                String printGrpLevel = arr[1];
                                firstLevel =
                                        new NodeCategory(category, null, null,
                                                         null, null, null,
                                                         null, null,
                                                         printGrpLevel, null);

                                root.add(firstLevel);
                                List<ConfiguratorNodePOJO> childList =
                                    (List<ConfiguratorNodePOJO>)pair.getValue();

                                for (ConfiguratorNodePOJO node : childList) {
                                    String nodeDesig = null;
                                    if (i == 0 && index == 0) {
                                        nodeDesig = "header";
                                    }
                                    if (node.getPrintGroupLevel() != null &&
                                        node.getPrintGroupLevel().equalsIgnoreCase("1")) {
                                        nodeDesig = "header";
                                    }
                                    if (node.getNodeCategory() != null &&
                                        (node.getNodeCategory().equalsIgnoreCase("1") ||
                                         node.getNodeCategory().equalsIgnoreCase("2") ||
                                         node.getNodeCategory().equalsIgnoreCase("3") ||
                                         node.getNodeCategory().equalsIgnoreCase("4") ||
                                         node.getNodeCategory().equalsIgnoreCase("5"))) {
                                        node.setPrintGroupLevel("0");
                                    }
                                    //if(node.getPrintGroupLevel()!=null && node.getPrintGroupLevel().eq)

                                    NodeCategory secondLevel =
                                        new NodeCategory(category,
                                                         node.getNodeName(),
                                                         node.getNodeDescription(),
                                                         node.getNodeQty(),
                                                         node.getNodeValue(),
                                                         node.getUnitPrice(),
                                                         node.getExtendedPrice(),
                                                         node.getNodeColor(),
                                                         node.getPrintGroupLevel(),
                                                         nodeDesig);
                                    firstLevel.addNodes(secondLevel);
                                }
                                index++;
                            }
                            //Trying to sort root
                            NodeComparator comparator = new NodeComparator();
                            Collections.sort(root, comparator);

                            categoryTree =
                                    new ChildPropertyTreeModel(root, "childNodes");
                            ADFUtils.setSessionScopeValue("categoryTree",
                                                          categoryTree);
                            mapOfChildren.put(categoryTree, lineQuoteAmount);
                            listOfTrees.add(categoryTree);
                        }
                        //                        if (quoteTotal != null) {
                        //                            quoteTotal.setValue(sumQuoteTotal);
                        //                        }
                    }
                } else {
                    ADFUtils.setSessionScopeValue("quoteNumber",
                                                  null); //If exception occurs , Quoting should be loaded in create mode, Not in update mode
                    root = new ArrayList();
                    categoryTree =
                            new ChildPropertyTreeModel(root, "childNodes");
                    listOfTrees.add(categoryTree);

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return mapOfChildren;
    }


    public void cancelDownloadPopup(ActionEvent actionEvent) {
        downloadPopup.cancel();
    }

    public void setDownloadPopup(RichPopup downloadPopup) {
        this.downloadPopup = downloadPopup;
    }

    public RichPopup getDownloadPopup() {
        return downloadPopup;
    }


    public String getUserFileName() {
        if (userInputFileName != null) {
            return (String)userInputFileName.getValue();
        } else
            return "ExportedXML";
    }

    //helper methods

    public List<ConfiguratorNodePOJO> getAllNodes(int lineNum) {
        Object parentObj = ADFUtils.getSessionScopeValue("parentObject");
        if (parentObj != null) {
            v93Obj = (V93kQuote)parentObj;
            allNodes = parseAllNodes(v93Obj, lineNum);
        }
        return allNodes;
    }

    private List<ConfiguratorNodePOJO> parseAllNodes(V93kQuote v93Obj,
                                                     int lineNum) {
        ArrayList<ConfiguratorNodePOJO> nodeList = null;
        ArrayList<QuoteLinePOJO> quoteLineTarget =
            v93Obj.getTargetConfigurationLines();
        if (quoteLineTarget != null && !quoteLineTarget.isEmpty()) {
            nodeList = quoteLineTarget.get(lineNum).getItems();
        }
        return nodeList;
    }


    private List<String> removeDuplicatesFromList(List<String> inputList) {
        Set<String> set = new LinkedHashSet<String>(inputList);
        List<String> outputList = new ArrayList<String>();
        outputList.clear();
        outputList.addAll(set);
        return outputList;
    }

    public ChildPropertyTreeModel getCategroryTreeLineTwo() {

        return categroryTreeLineTwo;
    }

    public void setErrPopup(RichPopup errPopup) {
        this.errPopup = errPopup;
    }

    public RichPopup getErrPopup() {
        return errPopup;
    }

    public void setErrorText(RichOutputFormatted errorText) {
        this.errorText = errorText;
    }

    public RichOutputFormatted getErrorText() {
        return errorText;
    }

    public void setQuoteTotal(RichOutputText quoteTotal) {
        this.quoteTotal = quoteTotal;
    }

    public RichOutputText getQuoteTotal() {
        return quoteTotal;
    }

    public void refresh(ActionEvent actionEvent) {
        RequestContext.getCurrentInstance().addPartialTarget(panelSplitterBind);
    }

    public void setPanelSplitterBind(RichPanelSplitter panelSplitterBind) {
        this.panelSplitterBind = panelSplitterBind;
    }

    public RichPanelSplitter getPanelSplitterBind() {
        return panelSplitterBind;
    }

    public ArrayList<ListViewModel> getListViewCollection() {
       
        
        String refreshImport =
            (String)ADFUtils.getSessionScopeValue("refreshImport");
        if (listViewCollection == null && refreshImport != null &&
            refreshImport.equalsIgnoreCase("Y")) {
            listViewCollection = new ArrayList<ListViewModel>();
            LinkedHashMap<ChildPropertyTreeModel, Double> mapOfTrees =
                createChildrenTrees();

            List<ChildPropertyTreeModel> listOftrees =
                new ArrayList<ChildPropertyTreeModel>();
            if (mapOfTrees != null && !mapOfTrees.isEmpty()) {
                Iterator it = mapOfTrees.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    ChildPropertyTreeModel child =
                        (ChildPropertyTreeModel)pair.getKey();
                    //Double lineTotal = (Double)pair.getValue();
                    listOftrees.add(child);
                    //netQuoteTotal = netQuoteTotal + lineTotal;
                }
            }
            if (quoteTotal != null) {
                //quoteTotal.setValue(netQuoteTotal);
            }
            if (listOftrees != null && !listOftrees.isEmpty()) {
                for (int i = 0; i < listOftrees.size(); i++) {
                    ListViewModel listViewObj =
                        new ListViewModel(listOftrees.get(i),
                                          String.valueOf(i + 1));
                    listViewCollection.add(listViewObj);

                }
            }
        }
        if(refreshPopup!=null){
            
            refreshPopup.hide();
            refreshPopup.cancel();
        }
        if(panelSplitterBind!=null){
            //refreshPopup.addPartialTarget(FacesContext.getCurrentInstance(), null, panelSplitterBind);
            ADFUtils.addPartialTarget(panelSplitterBind);
        }
     
        return listViewCollection;
    }

    public void setExpertMode(RichOutputText expertMode) {
        this.expertMode = expertMode;
    }

    public RichOutputText getExpertMode() {
        return expertMode;
    }

    public String getQuoteNetTotal() {
        String refQuoteNetTotal = null;
        boolean hasErrors = false;

        V93kQuote v93k =
            (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        hasErrors = configHasErrors(v93k);
        if (v93k != null) {
            refQuoteNetTotal = v93k.getTargetQuoteNetPrice();
        }
        if (hasErrors) {
            refQuoteNetTotal = null;
        }
        return refQuoteNetTotal;
    }

    public boolean configHasErrors(V93kQuote v93k) {
        boolean hasErrors = false;
        if (v93k != null) {
            //Check if no exceptions from configurator
            if (v93k.getExceptionMap() != null) {
                TreeMap<String, ArrayList<String>> exceptionMap =
                    v93k.getExceptionMap().getErrorList();
                List<String> errorMessages =
                    v93k.getExceptionMap().getErrorsMessages();
                if (exceptionMap != null && exceptionMap.size() > 0) {
                    hasErrors = true;
                }
                if (errorMessages != null && errorMessages.size() > 0) {
                    hasErrors = true;
                }
            }

        }
        return hasErrors;
    }

    public Boolean getProductsRendered() {
        boolean prodRend = true;
        HashMap userPrefMap = getProductPriceUserPref();
        if (userPrefMap != null && !userPrefMap.isEmpty()) {
            if (userPrefMap.containsKey("Prd_num_target_config")) {
                String Prd_num_target_config =
                    (String)userPrefMap.get("Prd_num_target_config");
                if (Prd_num_target_config != null &&
                    Prd_num_target_config.equalsIgnoreCase("N")) {
                    prodRend = false;
                }
            }
        }
        return prodRend;
    }

    public Boolean getPriceRendered() {
        boolean priceRendered = true;
        HashMap userPrefMap = getProductPriceUserPref();
        if (userPrefMap != null && !userPrefMap.isEmpty()) {
            if (userPrefMap.containsKey("Ref_price_target_config")) {
                String Ref_price_target_config =
                    (String)userPrefMap.get("Ref_price_target_config");
                if (Ref_price_target_config != null &&
                    Ref_price_target_config.equalsIgnoreCase("N")) {
                    priceRendered = false;
                }
            }
        }
        return priceRendered;
    }

    public HashMap getProductPriceUserPref() {

        HashMap choiceHashMap =
            (HashMap)ADFUtils.getSessionScopeValue("userPrefMap");
        if (choiceHashMap == null) {
            choiceHashMap = new HashMap();
            String userId = (String)ADFUtils.getSessionScopeValue("UserId");
            SudokuAMImpl am = (SudokuAMImpl)getAm();
            if (am != null) {
                String query =
                    "select * from xxat_userpref_globalchoice where user_id=:1 and column_type IN ('Prd_num_ref_config','Prd_num_target_config','Ref_price_ref_config','Ref_price_target_config')";
                DBTransaction dbTrans = (DBTransaction)am.getTransaction();
                PreparedStatement ps =
                    dbTrans.createPreparedStatement(query, 0);
                try {
                    ps.setString(1, userId == null ? "0" : userId);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        choiceHashMap.put(rs.getString(2), rs.getString(3));
                    }
                    ADFUtils.setSessionScopeValue("userPrefMap",
                                                  choiceHashMap);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }

            }
        }
        return choiceHashMap;
    }


    private SudokuAMImpl getAm() {

        FacesContext context = FacesContext.getCurrentInstance();
        BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContext.findDataControl("SudokuAMDataControl"); // Name of application module in datacontrolBinding.cpx
        SudokuAMImpl appM = (SudokuAMImpl)dc.getDataProvider();
        return appM;
    }

    public void setRefText(RichOutputFormatted refText) {
        this.refText = refText;
    }

    public RichOutputFormatted getRefText() {
        return refText;
    }

    public void setRefreshPopup(RichPopup refreshPopup) {
        this.refreshPopup = refreshPopup;
    }

    public RichPopup getRefreshPopup() {
        return refreshPopup;
    }

    public void setReadOnlyUI(Boolean readOnlyUI) {
        this.readOnlyUI = readOnlyUI;
    }

    public Boolean getReadOnlyUI() {
        boolean readOnly = true ;
        V93kQuote v93 = (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        if(v93!=null && v93.getTargetConfigurationLines()!=null && !v93.getTargetConfigurationLines().isEmpty()){
            readOnly = false ;
        }
        String configCancelled =
            (String)ADFUtils.getSessionScopeValue("cancelAll");
        HashMap ruleSetMap =
            (HashMap)ADFUtils.getSessionScopeValue("ruleSetMap");
        if (ruleSetMap != null && !ruleSetMap.isEmpty()) {
            if (ruleSetMap.containsKey("error")) {
                String error = (String)ruleSetMap.get("error");
                if (error != null && error.equalsIgnoreCase("Y")) {
                    readOnly = true;
                }
            }
        }
        if(configCancelled!=null && configCancelled.equals("Y")){
            readOnly = true ;
        }
        return readOnly;
    }
}
