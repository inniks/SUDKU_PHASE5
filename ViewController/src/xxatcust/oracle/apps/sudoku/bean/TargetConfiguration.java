package xxatcust.oracle.apps.sudoku.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.output.RichOutputFormatted;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;

import xxatcust.oracle.apps.sudoku.util.ADFUtils;
import xxatcust.oracle.apps.sudoku.util.JaxbParser;
import xxatcust.oracle.apps.sudoku.util.NodeComparator;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.ConfiguratorNodePOJO;
import xxatcust.oracle.apps.sudoku.viewmodelp4.pojo.NodeCategory;
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

    public TargetConfiguration() {
    }

    public void setPageInitText(RichOutputText pageInitText) {
        this.pageInitText = pageInitText;
    }
    
    public void refreshView(ActionEvent actionEvent) {
        ADFUtils.setSessionScopeValue("refreshImport", "Y");
        //Trying to clear previous values here
        quoteTotal.setValue(null);
        categoryTree = null;
        allNodes = null;
        listViewCollection = null;
        if(expertMode!=null){
            expertMode.setValue(null);
        }
    }

    public RichOutputText getPageInitText() {
        refreshView(null);
        getListViewCollection();
        // RequestContext.getCurrentInstance().addPartialTarget(ADFUtils.findComponentInRoot("pb2lim"));
        exportDownload(null);
        UIComponent ui = ADFUtils.findComponentInRoot("psexconfig");
        if (ui != null) {
            String cancelAll = (String)ADFUtils.getSessionScopeValue("cancelAll");
            if(cancelAll!=null && cancelAll.equalsIgnoreCase("Y")){
                quoteTotal.setValue(null);
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
        }
        //ADFUtils.refreshPage();
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
        System.out.println("Initializing target configuration....");
        if (quoteTotal != null) {
            getPageInitText();
        }
    }

    public void setUserInputFileName(RichInputText userInputFileName) {
        this.userInputFileName = userInputFileName;
    }

    public RichInputText getUserInputFileName() {
        return userInputFileName;
    }


    private List<ChildPropertyTreeModel> createChildrenTrees() {
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
                        for (int i = 0; i < targetLines.size(); i++) {
                            List<String> catList = new ArrayList<String>();
                            List<String> distinctList =
                                new ArrayList<String>();
                            List<ConfiguratorNodePOJO> allNodesList =
                                getAllNodes(i);
                            HashMap<String, List<ConfiguratorNodePOJO>> allNodesByCategoriesMap =
                                new HashMap<String, List<ConfiguratorNodePOJO>>();
                            if (allNodesList != null &&
                                !allNodesList.isEmpty()) {
                                for (ConfiguratorNodePOJO node :
                                     allNodesList) {
                                    if (node.getPrintGroupLevel() != null &&
                                        node.getPrintGroupLevel().equalsIgnoreCase("1")) {
                                        if (node.getExtendedPrice() != null) {
                                            Double b =
                                                new Double(node.getExtendedPrice());
                                            sumQuoteTotal = sumQuoteTotal + b;
                                        }
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
                            if (quoteTotal != null) {
                                quoteTotal.setValue(sumQuoteTotal);
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
                                                         printGrpLevel,null);
                                root.add(firstLevel);
                                List<ConfiguratorNodePOJO> childList =
                                    (List<ConfiguratorNodePOJO>)pair.getValue();
                                for (ConfiguratorNodePOJO node : childList) {
                                    String nodeDesig = null ;
                                     if(node.getPrintGroupLevel()!=null && node.getPrintGroupLevel().equalsIgnoreCase("1")){
                                         nodeDesig = "header" ;
                                         System.out.println("Setting node Designation");
                                     }
                                    NodeCategory secondLevel =
                                        new NodeCategory(category,
                                                         node.getNodeName(),
                                                         node.getNodeDescription(),
                                                         node.getNodeQty(),
                                                         node.getNodeValue(),
                                                         node.getUnitPrice(),
                                                         node.getExtendedPrice(),
                                                         node.getNodeColor(),
                                                         node.getPrintGroupLevel(),nodeDesig);
                                    firstLevel.addNodes(secondLevel);
                                }

                            }
                            //Trying to sort root
                            NodeComparator comparator = new NodeComparator();
                            Collections.sort(root, comparator);

                            categoryTree =
                                    new ChildPropertyTreeModel(root, "childNodes");
                            ADFUtils.setSessionScopeValue("categoryTree",
                                                          categoryTree);
                            listOfTrees.add(categoryTree);
                        }
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
        return listOfTrees;
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
        Set<String> set = new HashSet<String>(inputList);
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
            List<ChildPropertyTreeModel> listOftrees = createChildrenTrees();
            if (listOftrees != null && !listOftrees.isEmpty()) {
                for (int i = 0; i < listOftrees.size(); i++) {
                    ListViewModel listViewObj =
                        new ListViewModel(listOftrees.get(i),
                                          String.valueOf(i + 1));
                    listViewCollection.add(listViewObj);

                }
            }
        }
        return listViewCollection;
    }

    public void setExpertMode(RichOutputText expertMode) {
        this.expertMode = expertMode;
    }

    public RichOutputText getExpertMode() {
        return expertMode;
    }
}
