package xxatcust.oracle.apps.sudoku.bean;

import java.util.HashMap;

import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.jbo.Row;

import xxatcust.oracle.apps.sudoku.util.ADFUtils;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.V93kQuote;

public class RuleSet {
    private String otsDisplay;
    private Boolean isOts=false;
    public RuleSet() {
    }

    public void topLevelChanged(ValueChangeEvent valueChangeEvent) {
        UIComponent ui = (UIComponent)valueChangeEvent.getSource();
        ui.processUpdates(FacesContext.getCurrentInstance());
        DCIteratorBinding iter = ADFUtils.findIterator("RuleSetVO1Iterator");
        if (iter != null) {
            Row currRow = iter.getCurrentRow();
            if (currRow != null) {
                currRow.setAttribute("SecondLevelMeaning",
                                     null); // reset the second level choice
                String topLevelCode =
                    (String)currRow.getAttribute("TopLevelCode");
                System.out.println("Top Level Code " + topLevelCode);
                HashMap inputParamsMap =
                    (HashMap)ADFUtils.getSessionScopeValue("inputParamsMap");
                if (inputParamsMap == null) {
                    inputParamsMap = new HashMap();
                }
                inputParamsMap.put("ruleSetTop", topLevelCode);
                ADFUtils.setSessionScopeValue("inputParamsMap",
                                              inputParamsMap);
            }
        }
    }

    public void secLevelValSelected(ValueChangeEvent valueChangeEvent) {
        UIComponent ui = (UIComponent)valueChangeEvent.getSource();
        ui.processUpdates(FacesContext.getCurrentInstance());
        DCIteratorBinding iter = ADFUtils.findIterator("RuleSetVO1Iterator");
        if (iter != null) {
            Row currRw = iter.getCurrentRow();
            if (currRw != null) {
                String secondLevelCode =
                    (String)currRw.getAttribute("SecondLevelCode");
                System.out.println("Second Level Code " + secondLevelCode);
                HashMap inputParamsMap =
                    (HashMap)ADFUtils.getSessionScopeValue("inputParamsMap");
                if (inputParamsMap == null) {
                    inputParamsMap = new HashMap();
                }
                inputParamsMap.put("ruleSetSecond", secondLevelCode);
                ADFUtils.setSessionScopeValue("inputParamsMap",
                                              inputParamsMap);

            }
        }
    }

    public void topLevelChangeConfig(ValueChangeEvent valueChangeEvent) {
            System.out.println("from Rule Set Toplevel");
            UIComponent ui = (UIComponent)valueChangeEvent.getSource();
            ui.processUpdates(FacesContext.getCurrentInstance());
            DCIteratorBinding iter = ADFUtils.findIterator("RuleSetVO1Iterator");
            String meaning = "Standard";
            if (iter != null) {
                Row currRow = iter.getCurrentRow();
                if (currRow != null) {
                    currRow.setAttribute("SecondLevelMeaning",
                                         null); // reset the second level choice
                    String topLevelCode =
                        (String)currRow.getAttribute("TopLevelCode");
                    
                    System.out.println("Top Level Code " + topLevelCode);
                    HashMap inputParamsMap =
                        (HashMap)ADFUtils.getSessionScopeValue("inputParamsMapFromConfig");
                    HashMap inputParamsMapForRef =
                        (HashMap)ADFUtils.getSessionScopeValue("inputParamsMap");
                    if (inputParamsMap == null) {
                        inputParamsMap = new HashMap();
                    }
                    else{
                        if(inputParamsMapForRef!=null)  
                            inputParamsMapForRef.clear();
                    }
                    inputParamsMap.put("ruleSetTop", topLevelCode);
                    ADFUtils.setSessionScopeValue("inputParamsMapFromConfig",
                                                  inputParamsMap);
                    currRow.setAttribute("SecondLevelCode",
                                         meaning);
    //                iter.executeQuery();
                }
            }
        }
     
        public void secondlevelVCEConfig(ValueChangeEvent valueChangeEvent) {
            System.out.println("from Rule Set secondLevel");
            UIComponent ui = (UIComponent)valueChangeEvent.getSource();
            ui.processUpdates(FacesContext.getCurrentInstance());
            DCIteratorBinding iter = ADFUtils.findIterator("RuleSetVO1Iterator");
            if (iter != null) {
                Row currRw = iter.getCurrentRow();
                if (currRw != null) {
                    String secondLevelCode =
                        (String)currRw.getAttribute("SecondLevelCode");
                    System.out.println("Second Level Code " + secondLevelCode);
                    HashMap inputParamsMap =
                        (HashMap)ADFUtils.getSessionScopeValue("inputParamsMapFromConfig");
                    if (inputParamsMap == null) {
                        inputParamsMap = new HashMap();
                    }
                    if(inputParamsMap.get("ruleSetTop")==null){
                        inputParamsMap.put("ruleSetTop", (String)currRw.getAttribute("TopLevelCode"));
                        }
                    inputParamsMap.put("ruleSetSecond", secondLevelCode);
                    inputParamsMap.put("error", "N");
                    ADFUtils.setSessionScopeValue("inputParamsMapFromConfig",
                                                  inputParamsMap);
     
                }
            }
        }

    public void setOtsDisplay(String otsDisplay) {
        this.otsDisplay = otsDisplay;
    }

    public String getOtsDisplay() {
        String otsDisplay = null;
        V93kQuote v93k = (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        if(v93k!=null && v93k.getInputParams()!=null){
            String secondLevelRuleSet = v93k.getInputParams().getRuleSetSecondLevelChoice();
            if(secondLevelRuleSet!=null && secondLevelRuleSet.equalsIgnoreCase("OTS")){
                otsDisplay = "Advantest Expert";
            }
        }
        return otsDisplay;
    }

    public void setIsOts(Boolean isOts) {
        this.isOts = isOts;
    }

    public Boolean getIsOts() {
        boolean isOts = false;
        V93kQuote v93k = (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        if(v93k!=null && v93k.getInputParams()!=null){
            String secondLevelRuleSet = v93k.getInputParams().getRuleSetSecondLevelChoice();
            if(secondLevelRuleSet!=null && secondLevelRuleSet.equalsIgnoreCase("OTS")){
                isOts = true;
            }
        }
        return isOts;
    }
}
