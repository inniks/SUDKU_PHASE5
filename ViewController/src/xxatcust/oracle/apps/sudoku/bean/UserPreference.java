package xxatcust.oracle.apps.sudoku.bean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;

import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.input.RichSelectManyShuttle;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailHeader;

import oracle.binding.OperationBinding;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.server.ViewObjectImpl;

import org.apache.myfaces.trinidad.event.DisclosureEvent;

import xxatcust.oracle.apps.sudoku.util.ADFUtils;


public class UserPreference {
    private static ADFLogger _logger =
        ADFLogger.createADFLogger(UserPreference.class);
    private int usrId =
        Integer.parseInt((String)ADFUtils.getSessionScopeValue("UserId") ==
                         null ? "0" :
                         (String)ADFUtils.getSessionScopeValue("UserId"));
    private List<SelectItem> orderTypeValues = new ArrayList<SelectItem>();
    private List<SelectItem> currencyValues = new ArrayList<SelectItem>();
    private List<SelectItem> incoTermValues = new ArrayList<SelectItem>();
    private List<SelectItem> custNames = new ArrayList<SelectItem>();
    private List<SelectItem> custNumbers = new ArrayList<SelectItem>();
    private List<SelectItem> businessAgreemnt = new ArrayList<SelectItem>();
    private List<SelectItem> businessCenterCSR = new ArrayList<SelectItem>();
    private List<SelectItem> SalesRepValues = new ArrayList<SelectItem>();
    private List<SelectItem> paymentTermValues = new ArrayList<SelectItem>();
    private List<SelectItem> salesChannelSC = new ArrayList<SelectItem>();


    private List<SelectItem> orderTypeValuesForDefault =
        new ArrayList<SelectItem>();
    private List<SelectItem> currencyValuesForDefault =
        new ArrayList<SelectItem>();
    private List<SelectItem> incoTermValuesForDefault =
        new ArrayList<SelectItem>();
    private List<SelectItem> custNamesForDefault = new ArrayList<SelectItem>();
    private List<SelectItem> custNumbersForDefault =
        new ArrayList<SelectItem>();
    private List<SelectItem> businessAgreemnForDefaultForDefault =
        new ArrayList<SelectItem>();
    private List<SelectItem> businessCenterCSRForDefault =
        new ArrayList<SelectItem>();
    private List<SelectItem> SalesRepValuesForDefault =
        new ArrayList<SelectItem>();
    private List<SelectItem> paymentTermValuesForDefault =
        new ArrayList<SelectItem>();
    private List<SelectItem> salesChannelSCForDefault =
        new ArrayList<SelectItem>();
    private List<SelectItem> numberFormatList = new ArrayList<SelectItem>();


    private List customerNums = new ArrayList();
    private List<SelectItem> ouValues = new ArrayList<SelectItem>();
    private List otValues = new ArrayList();
    private List selectedBusinessAgreementValues = new ArrayList();
    private List selectedOrderTypeValues = new ArrayList();
    private List salesRepIds = new ArrayList();
    private List selectedCurrencyValues = new ArrayList();
    private List selectedIncoTermValues = new ArrayList();
    private List selectedPaymentTermValues = new ArrayList();
    private List selectedCustNameValues = new ArrayList();
    private List selectedCustNumValues = new ArrayList();
    private List selectedCSRValues = new ArrayList();
    private List selectedSalesRepValues = new ArrayList();
    private List selectedCustNameForSC = new ArrayList();
    private List selectedCurrencyValsSC = new ArrayList();
    private List selectedIncoTermSC = new ArrayList();
    private List selectedPaymentTermSC = new ArrayList();
    private List selectBusinessAgrementSC = new ArrayList();
    private List selectedSalesChannel = new ArrayList();

    private RichSelectOneRadio bindPrdNumRefConf;
    private RichSelectOneRadio bindPrdNumTConf;
    private RichSelectOneRadio bindRefPriceRefConf;
    private RichSelectOneRadio bindRefPriceTConfig;
//    private RichSelectOneChoice bindNumFormat;
    private String userName =
        ((String)ADFUtils.getSessionScopeValue("UserName") == null ? "Admin" :
         (String)ADFUtils.getSessionScopeValue("UserName"));
    private boolean isOu = true;
    private boolean isGlobalChoiceVals = true;
    //    private RichSelectOneChoice bindCurrency;
    private String gsOU;
    private String scOU;
    private String orderType;
    private String ouGetters;
    private String custNum;
    private String paymentTerm;
    private String currency;
    private String salesRep;
    private String incoTerm;
    private String csr;
    private String custNameForSC;
    private String orderTypeForSC;
    private String currencyForSC;
    private String incoTermForSC;
    private String paymentTemForSC;
    private String baForSC;
    private String salesChannel;
    private String scOrgId;

    private boolean isGlobalChoiceEnable = true;
    private boolean isSalesChnnelEnable = true;
    private RichShowDetailHeader bindGlobalChoiceShowDetail;
    private RichShowDetailHeader bindSalesChannelShowDetail;
    private RichSelectOneChoice bindSalesChannel;
//    private RichSelectOneChoice bindOU;
    private String orgId = null;
    private String numFormat = null;
    private RichSelectOneChoice bindGlobalOUItem;
    private RichSelectOneChoice bindSaesRep;
    private RichSelectOneChoice bindOrderType;
    private RichSelectOneChoice bindCustNum;
    private RichSelectManyShuttle bindSelectManySalesRep;
    private RichSelectManyShuttle bindSelectmanyOrderType;
    private RichSelectManyShuttle bindSelectManyCustNumValues;
    private RichSelectOneChoice bindCustName;
    private RichSelectOneChoice bindCurrency;
    private RichSelectOneChoice bindBA;
    private RichSelectOneChoice bindIncoTerms;
    private RichSelectOneChoice bindPaymentTerms;
    private RichSelectManyShuttle bindSelectManyCustNames;
    private RichSelectManyShuttle bindSelectManyCurrencies;
    private RichSelectManyShuttle bindSelectManyIncoTerms;
    private RichSelectManyShuttle bindSelectManyPaymentTerms;
    private RichSelectManyShuttle bindSelectManyBA;

    public UserPreference() {

    }

    public void initUserPref() {
        OperationBinding ob =
            ADFUtils.getBindingContainer().getOperationBinding("initUserPref");
        if (ob != null)
            ob.execute();
          getAllNumFormatVals();
        getAllCurrencyValues();
        getAllPaymentTermValues();
        getAllIncoTermValues();
        getAllCSRValues();
        getAllBAgreement();
        getAllSalesChannel();
        ADFUtils.setEL("#{pageFlowScope.ouGetters}", "Y");
    }

    public void orderTypeVCE(ValueChangeEvent vce) {

        if (vce.getNewValue() == null) {
            return;
        }
        if (vce.getOldValue() != vce.getNewValue()) {
            if (selectedOrderTypeValues != null &&
                selectedOrderTypeValues.size() > 0)
                selectedOrderTypeValues.clear();
            selectedOrderTypeValues = (List)vce.getNewValue();
        }
        //            List selectedListFromUI = (List)vce.getNewValue();
        //            if (selectedListFromUI.size() > 0) {
        //                if (selectedOrderTypeValues != null &&
        //                    selectedOrderTypeValues.size() > 0)
        //                    selectedOrderTypeValues.clear();
        //                for (int i = 0; i < selectedListFromUI.size(); i++) {
        //                    System.out.println("Selected values are from loop:" +
        //                                       selectedListFromUI.get(i) +
        //                                       "id value is:" + i);
        //                    selectedOrderTypeValues = selectedListFromUI;
        //                }
        //            }
    }

    public void setOrderTypeValues(List<SelectItem> orderTypeValues) {
        this.orderTypeValues = orderTypeValues;
    }


    public List<SelectItem> getAllOrderTypeValues() {
        long startTime = System.currentTimeMillis();
        System.out.println("getAllOrderTypeValues:Start" +
                           (System.currentTimeMillis() - startTime));
        if (orgId != null) {
            DCIteratorBinding DCiter =
                ADFUtils.findIterator("OrderTypeVOIterator");
            ViewObjectImpl vo = (ViewObjectImpl)DCiter.getViewObject();
            vo.clearCache();
            vo.setWhereClause(null);
            vo.setNamedWhereClauseParam("p_orgId", new BigDecimal(orgId));
            vo.executeQuery();
            if (orderTypeValues != null && orderTypeValues.size() > 0)
                orderTypeValues.clear();
            if (otValues != null && otValues.size() > 0)
                otValues.clear();
            if (orderTypeValuesForDefault != null)
                orderTypeValuesForDefault.add(new SelectItem(null));
            //                orderTypeValues.add(new SelectItem(null)); //TransactionTypeId
            //                otValues.add(null);
            RowSetIterator iter = vo.createRowSetIterator(null);
            while (iter.hasNext()) {
                Row r = iter.next();
                orderTypeValues.add(new SelectItem(r.getAttribute("Name")));
                orderTypeValuesForDefault.add(new SelectItem(r.getAttribute("Name")));
                otValues.add(r.getAttribute("TransactionTypeId"));
            }
            if (iter != null)
                iter.closeRowSetIterator();
        }
        System.out.println("getAllOrderTypeValues:Close Rowset " +
                           (System.currentTimeMillis() - startTime));
        return orderTypeValues;
    }

    public List<SelectItem> getOrderTypeValues() {
        return orderTypeValues;
    }


    public void setSelectedOrderTypeValues(List selectedOrderTypeValues) {
        this.selectedOrderTypeValues = selectedOrderTypeValues;
    }

    public List getSelctedOTValues() {
        long startTime = System.currentTimeMillis();
        //        List otValuesList = new ArrayList();
        System.out.println("getSelctedOTValues:Start" +
                           (System.currentTimeMillis() - startTime));
        if (selectedOrderTypeValues == null)
            selectedOrderTypeValues = new ArrayList();
        if (selectedOrderTypeValues != null &&
            selectedOrderTypeValues.size() == 0) {
            OperationBinding ob =
                ADFUtils.getBindingContainer().getOperationBinding("getSelectedOrderTypeValues");
            ob.getParamsMap().put("usrId", usrId);
            ob.getParamsMap().put("orgId", orgId);

            if (ob != null) {
                selectedOrderTypeValues = (List<String>)ob.execute();
                //                otValuesList = (List<String>)ob.execute();
                //                if (otValuesList != null && otValuesList.size() > 0) {
                //                    for (int i = 0; i < otValuesList.size(); i++) {
                //                        if (orderTypeValues.contains(otValuesList.get(i))) {
                //                            selectedOrderTypeValues.add(otValuesList.get(i));
                //                        }
                //
                //                    }
                //                }


            }
            System.out.println("getSelctedOTValues:after ob call" +
                               (System.currentTimeMillis() - startTime));
            //            }
        }
        return selectedOrderTypeValues;
    }


    public List getSelectedOrderTypeValues() {
        return selectedOrderTypeValues;
    }

    public void currencyVCE(ValueChangeEvent vce) {
        if (vce.getNewValue() == null) {
            if (selectedCurrencyValues != null &&
                selectedCurrencyValues.size() > 0)
                selectedCurrencyValues.clear();
            return;
        }
        List selectedListFromUI = (List)vce.getNewValue();
        if (selectedListFromUI.size() > 0) {
            if (selectedCurrencyValues != null &&
                selectedCurrencyValues.size() > 0)
                selectedCurrencyValues.clear();
            for (int i = 0; i < selectedListFromUI.size(); i++) {
                System.out.println("Selected values are from loop:" +
                                   selectedListFromUI.get(i) + "id value is:" +
                                   i);
                selectedCurrencyValues = selectedListFromUI;
            }
        }
    }

    public void incoTermVCE(ValueChangeEvent vce) {
        List selectedListFromUI = new ArrayList();
        if (vce.getNewValue() == null) {
            return;
        }
        selectedListFromUI = (List)vce.getNewValue();
        if (selectedListFromUI.size() > 0) {

            for (int i = 0; i < selectedListFromUI.size(); i++) {
                System.out.println("Selected values are from loop:" +
                                   selectedListFromUI.get(i) + "id value is:" +
                                   i);
                selectedIncoTermValues.add(selectedListFromUI.get(i).toString());
            }
        }
    }

    public void onCommit(ActionEvent actionEvent) {
        //nsrivastava Added this
        ADFUtils.setSessionScopeValue("userPrefMap", null);
        
//        if(orgId==null){
//            ADFUtils.addMessage(FacesMessage.SEVERITY_INFO,
//                                "Please Select operating Unit");
//        }
//        else{
        OperationBinding clearDataOB =
            ADFUtils.getBindingContainer().getOperationBinding("clearUserPrefVO");
        clearDataOB.getParamsMap().put("usrId", usrId);
        if(clearDataOB!=null)
            clearDataOB.execute();
        boolean isOT = false,isCust = false,isSalesRep = false,isCSR =false,isIncoTerm = false,isCurrency = false,isPaymentTerm = false,isSC = false,isRadioChoice= true;
        if(selectedOrderTypeValues==null)
            selectedOrderTypeValues= new ArrayList();
                            if ((selectedOrderTypeValues != null && selectedOrderTypeValues.size() > 0) || orderType != null) {  //selectedOrderTypeValues.size() > 0
                                isOT = true;
            OperationBinding orderTypeob =
                ADFUtils.getBindingContainer().getOperationBinding("validateOrderTypeValues");
            orderTypeob.getParamsMap().put("usrId", usrId);
            orderTypeob.getParamsMap().put("orderTypeValues",
                                           selectedOrderTypeValues);

            //                if (orderType != null)
            orderTypeob.getParamsMap().put("orderTypeDefaultval", orderType);
            orderTypeob.getParamsMap().put("orgId", orgId);

            if (orderTypeob != null) {
                orderTypeob.execute();
            }
                        }
                            if(selectedCustNumValues ==null)
                                selectedCustNumValues = new ArrayList();
                        if ((selectedCustNumValues != null && selectedCustNumValues.size()>0 ) || custNum != null) {  //                selectedCustNumValues.size() > 0
                            isCust  = true;
            OperationBinding custNumob =
                ADFUtils.getBindingContainer().getOperationBinding("validateCustValues");
            custNumob.getParamsMap().put("usrId", usrId);
            custNumob.getParamsMap().put("custValues", selectedCustNumValues);
            //                if (custNum != null)
            custNumob.getParamsMap().put("custDefaultVal", custNum);
            custNumob.getParamsMap().put("orgId", orgId);
            if (custNumob != null)
                custNumob.execute();
                        }
                       if(selectedSalesRepValues == null)
                           selectedSalesRepValues= new ArrayList();
                        if ((selectedSalesRepValues != null && selectedSalesRepValues.size() > 0 ) || salesRep != null) {  //           selectedSalesRepValues.size() > 0
                            isSalesRep = true;
            OperationBinding salesRepob =
                ADFUtils.getBindingContainer().getOperationBinding("validateSalesRepValues");
            salesRepob.getParamsMap().put("usrId", usrId);
            salesRepob.getParamsMap().put("salesRepVals",
                                          selectedSalesRepValues);
            //                if (salesRep != null)
            salesRepob.getParamsMap().put("defaultVal", salesRep);
            salesRepob.getParamsMap().put("orgId", orgId);
            if (salesRepob != null)
                salesRepob.execute();
                        }
        if (selectedCSRValues == null )
            selectedCSRValues= new ArrayList();
                        if ((selectedCSRValues != null && selectedCSRValues.size() > 0) ||csr != null) {  //&& selectedCSRValues.size() > 0
                            isCSR = true;
            OperationBinding csrob =
                ADFUtils.getBindingContainer().getOperationBinding("validateCsrValues");
            csrob.getParamsMap().put("usrId", usrId);
            csrob.getParamsMap().put("csrValues", selectedCSRValues);
            //                if (csr != null)
            csrob.getParamsMap().put("csr", csr);
            if (csrob != null)
                csrob.execute();
                        }
        if (selectedCurrencyValues == null )
            selectedCurrencyValues = new ArrayList();
                        if ((selectedCurrencyValues != null &&  selectedCurrencyValues.size() > 0) || currency != null) {  //              &&  selectedCurrencyValues.size() > 0
                            isCurrency = true;
            OperationBinding currencyob =
                ADFUtils.getBindingContainer().getOperationBinding("validateCurrencyValues");
            currencyob.getParamsMap().put("usrId", usrId);
            currencyob.getParamsMap().put("currencyValues",
                                          selectedCurrencyValues);
            //                if (currency != null)
            currencyob.getParamsMap().put("defaultName", currency);
            if (currencyob != null)
                currencyob.execute();
                        }
                        if(selectedIncoTermValues==null)
                            selectedIncoTermValues=new ArrayList();
                        if ((selectedIncoTermValues != null && selectedIncoTermValues.size() > 0) || incoTerm != null) { //selectedIncoTermValues.size() > 0
                            isIncoTerm = true;
            OperationBinding incoTermob =
                ADFUtils.getBindingContainer().getOperationBinding("validateIncoTermValues");
            incoTermob.getParamsMap().put("usrId", usrId);
            incoTermob.getParamsMap().put("incoTermValues",
                                          selectedIncoTermValues);
            //                if (incoTerm != null)
            incoTermob.getParamsMap().put("dafaultVal", incoTerm);
            if (incoTermob != null)
                incoTermob.execute();
                        }
                        if (paymentTerm != null) {
                            isPaymentTerm= true;
            OperationBinding paymentTermsob =
                ADFUtils.getBindingContainer().getOperationBinding("validatePaymentTermValues");
            paymentTermsob.getParamsMap().put("usrId", usrId);
            //                if (paymentTerm != null)
            paymentTermsob.getParamsMap().put("defaultVal", paymentTerm);
            if (paymentTermsob != null)
                paymentTermsob.execute();
                        }

                        if (selectedSalesChannel != null && selectedSalesChannel.size() > 0) {  //   selectedSalesChannel.size() > 0
                            isSC = true;
            OperationBinding salesChannelOB =
                ADFUtils.getBindingContainer().getOperationBinding("validateSalesChannelForSC");
            salesChannelOB.getParamsMap().put("usrId", usrId);
            salesChannelOB.getParamsMap().put("salesChannel",
                                              selectedSalesChannel);
            if (salesChannelOB != null) {
                salesChannelOB.execute();

            }
                        }
            OperationBinding commitOperations =
                ADFUtils.findOperation("commitEntities");
            
            System.out.println(this.bindPrdNumRefConf.getValue());
            OperationBinding staticValob =
                ADFUtils.getBindingContainer().getOperationBinding("validatePrefStaticValues");
            isRadioChoice = true;
            staticValob.getParamsMap().put("usrId", usrId);
        staticValob.getParamsMap().put("numberFormat", numFormat);
            if (staticValob != null) {
                staticValob.execute();
            }
            
            if (isOT == true || isCust == true || isSalesRep == true || isCSR ==true || isIncoTerm == true || isCurrency == true || isPaymentTerm ==true || isSC == true || isRadioChoice == true) {
            Boolean flag = (Boolean)commitOperations.execute();
            if (flag != null) {
                if (flag)
                    ADFUtils.addMessage(FacesMessage.SEVERITY_INFO,
                                        "Global Choices Saved Successfully");
            }
            isOT = false;isCust = false;isSalesRep = false;isCSR =false;isIncoTerm = false;isCurrency = false;isPaymentTerm = false;isSC = false;
        }
        else {
            ADFUtils.addMessage(FacesMessage.SEVERITY_INFO,
                                "Please Select any of value before save");
        }
//        }
    }


    public void setCurrencyValues(List<SelectItem> currencyValues) {
        this.currencyValues = currencyValues;
    }


    public List<SelectItem> getAllCurrencyValues() {
        long startTime = System.currentTimeMillis();
        System.out.println("getAllCurrencyValues: Start " +
                           (System.currentTimeMillis() - startTime));
        if (currencyValues != null) {
            //            currencyValues.add(new SelectItem(null));
            if (currencyValuesForDefault != null) {
                currencyValuesForDefault.add(new SelectItem(null));
            }
            DCIteratorBinding iter =
                ADFUtils.findIterator("CurrencyVOIterator");
            RowSetIterator it =
                iter.getViewObject().createRowSetIterator(null);
            while (it.hasNext()) {
                Row row = it.next();
                currencyValues.add(new SelectItem(row.getAttribute("Name")));
                currencyValuesForDefault.add(new SelectItem(row.getAttribute("Name")));
            }
            it.closeRowSetIterator();
            System.out.println("getAllCurrencyValues: after close Rowset: " +
                               (System.currentTimeMillis() - startTime));
        }

        return currencyValues;
    }


    public List<SelectItem> getAllSalesChannel() {
        long startTime = System.currentTimeMillis();
        System.out.println("getAllSalesChannel: Start " +
                           (System.currentTimeMillis() - startTime));
        if (salesChannelSC != null) {
            //            salesChannelSC.add(new SelectItem(null));
            if (salesChannelSCForDefault != null)
                salesChannelSCForDefault.add(new SelectItem(null));
            DCIteratorBinding iter =
                ADFUtils.findIterator("SalesChannelVOIterator");
            RowSetIterator it =
                iter.getViewObject().createRowSetIterator(null);
            while (it.hasNext()) {
                Row row = it.next();
                salesChannelSC.add(new SelectItem(row.getAttribute("Meaning")));
                salesChannelSCForDefault.add(new SelectItem(row.getAttribute("Meaning")));
            }
            it.closeRowSetIterator();
            System.out.println("getAllSalesChannelValues: after close Rowset: " +
                               (System.currentTimeMillis() - startTime));
        }

        return salesChannelSC;
    }


    public List<SelectItem> getCurrencyValues() {
        return currencyValues;
    }

    public void setIncoTermValues(List<SelectItem> incoTermValues) {
        this.incoTermValues = incoTermValues;
    }

    public List<SelectItem> getAllIncoTermValues() {
        if (incoTermValues != null) {
            //            incoTermValues.add(new SelectItem(null));
            if (incoTermValuesForDefault != null)
                incoTermValuesForDefault.add(new SelectItem(null));
            DCIteratorBinding iter =
                ADFUtils.findIterator("IncoTermVOIterator");
            RowSetIterator it =
                iter.getViewObject().createRowSetIterator(null);
            while (it.hasNext()) {
                Row row = it.next();
                incoTermValues.add(new SelectItem(row.getAttribute("Description")));
                incoTermValuesForDefault.add(new SelectItem(row.getAttribute("Description")));
            }
            it.closeRowSetIterator();
        }
        return incoTermValues;
    }

    public List<SelectItem> getAllPaymentTermValues() {
        if (paymentTermValues != null) {
            //            paymentTermValues.add(new SelectItem(null));
            if (paymentTermValuesForDefault != null)
                paymentTermValuesForDefault.add(new SelectItem(null));
            DCIteratorBinding iter =
                ADFUtils.findIterator("PaymentTermsVOIterator");
            ViewObjectImpl vo = (ViewObjectImpl)iter.getViewObject();
            if (vo != null) {
                vo.clearCache();
                vo.setWhereClause(null);
                vo.executeQuery();
                RowSetIterator it =
                    iter.getViewObject().createRowSetIterator(null);
                while (it.hasNext()) {
                    Row row = it.next();
                    paymentTermValues.add(new SelectItem(row.getAttribute("Description")));
                    paymentTermValuesForDefault.add(new SelectItem(row.getAttribute("Description")));
                }
                it.closeRowSetIterator();
            }

        }
        return paymentTermValues;
    }


    public List<SelectItem> getIncoTermValues() {
        return incoTermValues;
    }

    //    public void setPaymentTermValues(List<SelectItem> paymentTermValues) {
    //        this.paymentTermValues = paymentTermValues;
    //    }

    //    public List<SelectItem> getPaymentTermValues() {
    //        if (paymentTermValues != null) {
    //            DCIteratorBinding iter =
    //                ADFUtils.findIterator("PaymentTermsVOIterator");
    //            RowSetIterator it =
    //                iter.getViewObject().createRowSetIterator(null);
    //            while (it.hasNext()) {
    //                Row row = it.next();
    //                paymentTermValues.add(new SelectItem(row.getAttribute("Description")));
    //            }
    //        }
    //        return paymentTermValues;
    //    }

    public void setSelectedCurrencyValues(List selectedCurrencyValues) {
        this.selectedCurrencyValues = selectedCurrencyValues;
    }


    public List getSelectedCurrencyVals() {
        long startTime = System.currentTimeMillis();
        System.out.println("getSelectedCurrencyVals: Start " +
                           (System.currentTimeMillis() - startTime));
        if (selectedCurrencyValues != null &&
            selectedCurrencyValues.size() == 0) {

            OperationBinding ob =
                ADFUtils.getBindingContainer().getOperationBinding("getSelectedCurrencyValues");
            ob.getParamsMap().put("usrId", usrId);
            if (ob != null) {
                selectedCurrencyValues = (List<String>)ob.execute();
            }
            System.out.println("getSelectedCurrencyVals: closing " +
                               (System.currentTimeMillis() - startTime));
        }
        return selectedCurrencyValues;
    }

    public List getSelectedCurrencyValues() {
        return selectedCurrencyValues;
    }

    public void setSelectedIncoTermValues(List selectedIncoTermValues) {
        this.selectedIncoTermValues = selectedIncoTermValues;
    }

    public List getSelectedIncoTermVals() {
        long startTime = System.currentTimeMillis();
        System.out.println("getSelctedIncoTermValues:Start" +
                           (System.currentTimeMillis() - startTime));
        if (selectedIncoTermValues != null &&
            selectedIncoTermValues.size() == 0) {
            OperationBinding ob =
                ADFUtils.getBindingContainer().getOperationBinding("getSelectedIncoTermValues");
            ob.getParamsMap().put("usrId", usrId);
            if (ob != null) {
                selectedIncoTermValues = (List<String>)ob.execute();
                System.out.println("getSelctedIncTermValues:End" +
                                   (System.currentTimeMillis() - startTime));
            }
        }
        return selectedIncoTermValues;
    }

    public List getSelectedIncoTermValues() {
        return selectedIncoTermValues;
    }

    public void setSelectedPaymentTermValues(List selectedPaymentTermValues) {
        this.selectedPaymentTermValues = selectedPaymentTermValues;
    }

    public List getSelectedPaymentTermValues() {
        return selectedPaymentTermValues;
    }

    public void booleanVCE(ValueChangeEvent vce) {
        System.out.println("old value:" + vce.getOldValue());
        System.out.println("New value:" + vce.getNewValue());
    }

    public void getPrdNumRefConf() {
        String colVal = getSelectedDefaultValue("Prd_num_ref_config");
        //        String temp = null;
        //        String colVal = "Prd_num_ref_config";
        //        DCIteratorBinding iter =
        //            ADFUtils.findIterator("userPrefEntityVOIterator");
        //        ViewObject vo = iter.getViewObject();
        //        if (vo != null) {
        //            vo.clearCache();
        //            vo.setWhereClause(null);
        //            Object obj[] = { usrId, colVal };
        //            Key key = new Key(obj);
        //            Row[] rows = vo.findByKey(key, 2);
        //            if (rows != null && rows.length > 0) {
        //                temp = (String)rows[0].getAttribute("ColumnVal");
        //            }
        //        }
        if (colVal != null) {
            if (colVal.equalsIgnoreCase("Y"))
                this.bindPrdNumRefConf.setValue(0);
            else
                this.bindPrdNumRefConf.setValue(1);
        } else
            this.bindPrdNumRefConf.setValue(1);

    }

    public void setBindPrdNumRefConf(RichSelectOneRadio bindPrdNumRefConf) {
        this.bindPrdNumRefConf = bindPrdNumRefConf;

    }

    public RichSelectOneRadio getBindPrdNumRefConf() {
        return bindPrdNumRefConf;
    }

    public void getPrdNumTConf() {
        String colVal = getSelectedDefaultValue("Prd_num_target_config");
        //        String temp = null;
        //        String colVal = "Prd_num_target_config";
        //        DCIteratorBinding iter =
        //            ADFUtils.findIterator("userPrefEntityVOIterator");
        //        ViewObject vo = iter.getViewObject();
        //        if (vo != null) {
        //            vo.clearCache();
        //            vo.setWhereClause(null);
        //            System.out.println("size of VO:" + vo.getEstimatedRowCount());
        //            Object obj[] = { usrId, colVal };
        //            Key key = new Key(obj);
        //            Row[] rows = vo.findByKey(key, 2);
        //            if (rows != null && rows.length > 0) {
        //                temp = (String)rows[0].getAttribute("ColumnVal");
        //            }
        //        }
        if (colVal != null) {
            if (colVal.equalsIgnoreCase("Y"))
                this.bindPrdNumTConf.setValue(0);
            else
                this.bindPrdNumTConf.setValue(1);
        } else
            this.bindPrdNumTConf.setValue(1);

    }

    public void setBindPrdNumTConf(RichSelectOneRadio bindPrdNumTConf) {
        this.bindPrdNumTConf = bindPrdNumTConf;
    }

    public RichSelectOneRadio getBindPrdNumTConf() {
        return bindPrdNumTConf;
    }


    public void getRefPriceRefConf() {
        String temp = null;
        String colVal = "Ref_price_ref_config";
        DCIteratorBinding iter =
            ADFUtils.findIterator("userPrefEntityVOIterator");
        ViewObject vo = iter.getViewObject();
        if (vo != null) {
            vo.clearCache();
            vo.setWhereClause(null);
            Object obj[] = { usrId, colVal };
            Key key = new Key(obj);
            Row[] rows = vo.findByKey(key, 2);
            if (rows != null && rows.length > 0) {
                temp = (String)rows[0].getAttribute("ColumnVal");
            }
        }
        if (temp != null) {
            if (temp.equalsIgnoreCase("Y"))
                bindRefPriceRefConf.setValue(0);
            else
                bindRefPriceRefConf.setValue(1);
        } else
            bindRefPriceRefConf.setValue(1);

    }

    public void setBindRefPriceRefConf(RichSelectOneRadio bindRefPriceRefConf) {
        this.bindRefPriceRefConf = bindRefPriceRefConf;

    }

    public RichSelectOneRadio getBindRefPriceRefConf() {
        return bindRefPriceRefConf;
    }

    public void getRefPriceTConfig() {
        String temp = null;
        String colVal = "Ref_price_target_config";
        DCIteratorBinding iter =
            ADFUtils.findIterator("userPrefEntityVOIterator");
        ViewObject vo = iter.getViewObject();
        if (vo != null) {
            vo.clearCache();
            vo.setWhereClause(null);
            Object obj[] = { usrId, colVal };
            Key key = new Key(obj);
            Row[] rows = vo.findByKey(key, 2);
            if (rows != null && rows.length > 0) {
                temp = (String)rows[0].getAttribute("ColumnVal");
            }
        }
        if (temp != null) {
            if (temp.equalsIgnoreCase("Y"))
                bindRefPriceTConfig.setValue(0);
            else
                bindRefPriceTConfig.setValue(1);
        } else
            bindRefPriceTConfig.setValue(1);
    }

    public void setBindRefPriceTConfig(RichSelectOneRadio bindRefPriceTConfig) {
        this.bindRefPriceTConfig = bindRefPriceTConfig;

    }

    public RichSelectOneRadio getBindRefPriceTConfig() {
        return bindRefPriceTConfig;
    }

    public void getNumFormatValue() {
        String temp = null;
        String colVal = "Num_format";
        DCIteratorBinding iter =
            ADFUtils.findIterator("userPrefEntityVOIterator");
        
        ViewObject vo = iter.getViewObject();
        if (vo != null) {
            vo.clearCache();
            vo.setWhereClause(null);
            System.out.println("size of VO:" + vo.getEstimatedRowCount());
            Object obj[] = { usrId, colVal };
            Key key = new Key(obj);
            Row[] rows = vo.findByKey(key, 2);
            if (rows != null && rows.length > 0) {
                temp = (String)rows[0].getAttribute("ColumnVal");
            }
        }
        if (temp != null && !"".equalsIgnoreCase(temp)) {
            numFormat = temp;
        }
    }

//    public void setBindNumFormat(RichSelectOneChoice bindNumFormat) {
//        this.bindNumFormat = bindNumFormat;
//
//    }
//
//    public RichSelectOneChoice getBindNumFormat() {
//        return bindNumFormat;
//    }

    //    public void setOuValues(List<SelectItem> ouValues) {
    //        this.ouValues = ouValues;
    //    }

        public List<SelectItem> getAllOuValues() {
            if (ouValues != null) {
                ouValues.add(new SelectItem(null));
                DCIteratorBinding iter = ADFUtils.findIterator("OUVOIterator");
                ViewObject vo =  iter.getViewObject();
                if(vo!=null){
                    vo.clearCache();
                    vo.setWhereClause(null);
                        RowSetIterator it =
                            vo.createRowSetIterator(null);
    
                        while (it.hasNext()) {
                            Row row = it.next();
                            ouValues.add(new SelectItem(row.getAttribute("OperatingUnit").toString()));
                            //                System.out.println("OU Value is:" +
                            //                                   row.getAttribute("OperatingUnit"));
                            //                orglist.add(row.getAttribute("OrgId"));
                        }
    
                        it.closeRowSetIterator();
                    }
    
    
    //            ADFUtils.setSessionScopeValue("ouGetters1", "N");
            }
            return ouValues;
        }

    //    public List<SelectItem> getOuValues() {
    //        //        HashMap refreshGetters =
    //        //            (HashMap)ADFContext.getCurrent().getSessionScope().get("refreshGetters");
    //        //        if (refreshGetters != null) {
    //        //            String ouGetters = (String)refreshGetters.get("ouGetter");
    //        //            System.out.println("from getOUvalues:"+ouGetters);
    //        //            if (ouGetters != null && ouGetters.equalsIgnoreCase("Y")) {
    //
    //        String ouGetters = (String)ADFUtils.getSessionScopeValue("ouGetters1");
    //        if (ouGetters != null && ouGetters.equalsIgnoreCase("Y")) {
    //            if (ouValues != null) {
    //                DCIteratorBinding iter = ADFUtils.findIterator("OUVOIterator");
    //                RowSetIterator it =
    //                    iter.getViewObject().createRowSetIterator(null);
    //                while (it.hasNext()) {
    //                    Row row = it.next();
    //                    ouValues.add(new SelectItem(row.getAttribute("OperatingUnit")));
    //                    //                System.out.println("OU Value is:" +
    //                    //                                   row.getAttribute("OperatingUnit"));
    //                    orglist.add(row.getAttribute("OrgId"));
    //                }
    //                it.closeRowSetIterator();
    //                ADFUtils.setSessionScopeValue("ouGetters1","N");
    //            }
    //        }

    //        return ouValues;
    //    }

    public void setCustNames(List<SelectItem> custNames) {
        this.custNames = custNames;
    }

    public List<SelectItem> getCustNames() {
        //        if (isGlobalChoiceVals) {
        //            if (orglist != null && orglist.size() > 0) {
        //                StringBuilder sb = new StringBuilder();
        //                String s = " AND EXISTS (\n" +
        //                    "          SELECT 1\n" +
        //                    "            FROM hz_cust_site_uses_all hzsu,\n" +
        //                    "                 hz_cust_acct_sites_all hzas,\n" +
        //                    "                 hz_party_sites hzps\n" +
        //                    "           WHERE hzsu.primary_flag = 'Y'\n" +
        //                    "             AND hzsu.cust_acct_site_id = hzas.cust_acct_site_id\n" +
        //                    "             AND hzsu.org_id = hzas.org_id\n" +
        //                    "             AND hzas.cust_account_id = ca.cust_account_id\n" +
        //                    "             AND hzps.party_site_id = hzas.party_site_id" +
        //                    "             AND hzsu.org_id in(";
        //                if (custNames != null) {
        //                    DCIteratorBinding iter =
        //                        ADFUtils.findIterator("CustomerNameFOrPrefVOIterator");
        //                    ViewObjectImpl vo = (ViewObjectImpl)iter.getViewObject();
        //                    sb.append(vo.getQuery());
        //                    if (vo != null) {
        //
        //                        sb.append(s);
        //                        sb.append(orglist.get(0));
        //                        for (int i = 1; i < orglist.size(); i++) {
        //                            sb.append(",").append(orglist.get(i));
        //                            System.out.println("org Value is:" +
        //                                               orglist.get(i));
        //                        }
        //                        sb.append("))");
        //                        System.out.println("set of OrgList:" + sb.toString());
        //                        vo.setQuery(sb.toString());
        //                        //                    vo.setWhereClause(sb.toString());
        //                        System.out.println("get Customer Query:" +
        //                                           vo.getQuery());
        //                        vo.executeQuery();
        //                        RowSetIterator custIter =
        //                            vo.createRowSetIterator(null);
        //                        while (custIter.hasNext()) {
        //                            Row row = custIter.next();
        //                            custNames.add(new SelectItem(row.getAttribute("Customername")));
        //                            //                        custNumbers.add(row.getAttribute("Accountnumber"));
        //                            custNumbers.add(new SelectItem(row.getAttribute("Accountnumber")));
        //                        }
        //                    }
        //                }
        //            }
        //        }
        return custNames;
    }

    //    public void setSelectedOUValues(List selectedOUValues) {
    //        this.selectedOUValues = selectedOUValues;
    //    }

    //    public List getSelectedOUValues() {
    //        String ouGetters = (String)ADFUtils.getSessionScopeValue("ouGetters2");
    ////        if (ouGetters != null && ouGetters.equalsIgnoreCase("Y")) {
    //            Map<String, List> map = new HashMap<String, List>();
    //            if(selectedOUValues==null)
    //                selectedOUValues = new ArrayList();
    //            OperationBinding ob =
    //                ADFUtils.getBindingContainer().getOperationBinding("getSelectedOUValues");
    //            ob.getParamsMap().put("usrId", usrId);
    //            ob.getParamsMap().put("ouList", selectedOUValues);
    //            if (ob != null) {
    //                map = (Map<String, List>)ob.execute();
    //            }
    //            if (map != null) {
    //                if (map.containsKey("ouValues")) {
    //                    selectedOUValues = map.get("ouValues");
    //                }
    //                if (map.containsKey("orgList")) {
    //                    orglist = map.get("orgList");
    //                }
    //                ADFUtils.setPageFlowScopeValue("orglist", orglist);
    //                ADFUtils.setSessionScopeValue("ouGetters2", "N");
    //            }
    ////        }
    //        return selectedOUValues;
    //    }

    public void setSelectedCustNameValues(List selectedCustNameValues) {
        this.selectedCustNameValues = selectedCustNameValues;
    }


    public List getSlectedCustNamesForSC() {
        List custNameList = new ArrayList();
        if (selectedCustNameValues == null)
            selectedCustNameValues = new ArrayList();
        if (selectedCustNameValues != null &&
            selectedCustNameValues.size() == 0) {
            OperationBinding ob =
                ADFUtils.getBindingContainer().getOperationBinding("getSelectedCustomerNameValues");
            ob.getParamsMap().put("usrId", usrId);
            ob.getParamsMap().put("salesChannel", salesChannel);
            ob.getParamsMap().put("orgId", scOrgId);

            if (ob != null) {
                custNameList = (List<String>)ob.execute();
                if (custNameList != null && custNameList.size() > 0) {
                    for (int i = 0; i < custNameList.size(); i++) {
                        selectedCustNameValues.add(custNameList.get(i));
                    }

                }
            }
        }
        return selectedCustNameValues;


    }

    public List getSelectedCustNameValues() {
        return selectedCustNameValues;
    }

    //    public void setOrglist(List orglist) {
    //        this.orglist = orglist;
    //    }
    //
    //    public List getOrglist() {
    //        return orglist;
    //    }


    public void ouVCE(ValueChangeEvent vce) {
        if (vce.getNewValue() != null &&
                    vce.getOldValue() != vce.getNewValue()) {
                    //                    RichInputListOfValues soc = (RichInputListOfValues)vce.getComponent();
                    RichSelectOneChoice soc = (RichSelectOneChoice)vce.getComponent();
                    System.out.println("Index: " + soc.getValue().toString());
                    vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
                    Object orgValue =
                        ADFUtils.findIterator("QuotesVOIterator").getCurrentRow().getAttribute("OperatingUnit");
                    System.out.println("OrgId idddds:" + orgValue);
            ADFUtils.setSessionScopeValue("UserBasedOrgId", null);
            if (orgValue != null)
                orgId = orgValue.toString();
                        else
                            orgId = null;
                        ADFUtils.setSessionScopeValue("UserBasedOrgId", orgId);
//                        if(orgId!=null){
//            getBindGlobalChoiceShowDetail().setDisclosed(false);
//            getBindSalesChannelShowDetail().setDisclosed(false);
//            isGlobalChoiceEnable = true;
//            isSalesChnnelEnable = true;
//            getBindGlobalChoiceShowDetail().setDisclosed(false);
//            getBindSalesChannelShowDetail().setDisclosed(false);
//            System.out.println("Selected Value is:" + vce.getNewValue());
            
//            if (orgValue != null)
//                orgId = orgValue.toString();
//            else
//                orgId = null;
            ADFUtils.setSessionScopeValue("UserBasedOrgId", orgId);
            if(orgId!=null){
                selectedCustNumValues = new ArrayList();
                selectedOrderTypeValues = new ArrayList();
                selectedSalesRepValues = new ArrayList();
                SalesRepValues = new ArrayList<SelectItem>();
                custNumbers = new ArrayList<SelectItem>();
                custNumbersForDefault = new ArrayList<SelectItem>();
                orderTypeValuesForDefault = new ArrayList<SelectItem>();
                SalesRepValuesForDefault = new ArrayList<SelectItem>();
                orderTypeValues = new ArrayList<SelectItem>();
                bindSaesRep.setDisabled(false);
                bindOrderType.setDisabled(false);
                bindCustNum.setDisabled(false);
                bindSelectManySalesRep.setDisabled(false);
                bindSelectmanyOrderType.setDisabled(false);
                bindSelectManyCustNumValues.setDisabled(false);
            getDefaultValsForOUDependencies(orgId);
            getAllCustNums();
            getSelectedCustNumVals();
            getAllOrderTypeValues();
            getSelctedOTValues();
            getAllSalesRepValues();
            getSelectedSalesRepVals();
            }
            else{
                    selectedCustNumValues = new ArrayList();
                    selectedOrderTypeValues = new ArrayList();
                    selectedSalesRepValues = new ArrayList();
                    SalesRepValues = new ArrayList<SelectItem>();
                    custNumbers = new ArrayList<SelectItem>();
                    custNumbersForDefault = new ArrayList<SelectItem>();
                    orderTypeValuesForDefault = new ArrayList<SelectItem>();
                    SalesRepValuesForDefault = new ArrayList<SelectItem>();
                    orderTypeValues = new ArrayList<SelectItem>();
                        bindSaesRep.setDisabled(true);
                        bindOrderType.setDisabled(true);
                        bindCustNum.setDisabled(true);
                        bindSelectManySalesRep.setDisabled(true);
                        bindSelectmanyOrderType.setDisabled(true);
                        bindSelectManyCustNumValues.setDisabled(true);
                
                }
        }
    }


    public void ouForSalesChannelVCE(ValueChangeEvent vce) {
        if ((vce.getNewValue() != null &&
            vce.getOldValue() != vce.getNewValue())) {
            scOrgId = null;
            RichSelectOneChoice soc = (RichSelectOneChoice)vce.getComponent();
            System.out.println("Index: " + soc.getValue().toString());
            vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
            Object orgValue =
                ADFUtils.findIterator("QuotesVOIterator").getCurrentRow().getAttribute("OperatingUnitForSC");
            ADFUtils.setSessionScopeValue("SCBasedOrgId", null);
//            scOrgId = (String)vce.getNewValue();
            if (orgValue != null)
                scOrgId = orgValue.toString();
            else
                scOrgId = null;
            ADFUtils.setSessionScopeValue("SCBasedOrgId", scOrgId);
            if(scOrgId!=null){
//                    getAllCustNames();
                }
            else{
                    custNameForSC = null;
                    custNames = new ArrayList<SelectItem>();
                    currencyForSC = null;   
                    selectedCurrencyValsSC = new ArrayList();
                    baForSC = null;
                    selectedCustNameForSC = new ArrayList();
                    selectedCustNameValues = new ArrayList();
                    custNames = new ArrayList<SelectItem>();
                    incoTermForSC = null;
                    selectedIncoTermSC = new ArrayList();
                    paymentTemForSC = null;
                    selectedPaymentTermSC = new ArrayList();
                    bindCustName.setDisabled(true);
                    bindSelectManyCustNames.setDisabled(true);
                    bindCurrency.setDisabled(true);
                    bindSelectManyCurrencies.setDisabled(true);
                    bindIncoTerms.setDisabled(true);
                    bindSelectManyIncoTerms.setDisabled(true);
                    bindPaymentTerms.setDisabled(true);
                    bindSelectManyPaymentTerms.setDisabled(true);
                    bindBA.setDisabled(true);
                    bindSelectManyBA.setDisabled(true);
                }
//            getBindSalesChannelShowDetail().setDisclosed(false);
            if(scOrgId!=null && salesChannel!=null){
                custNameForSC = null;
                custNames = new ArrayList<SelectItem>();
                currencyForSC = null;   
                selectedCurrencyValsSC = new ArrayList();
                baForSC = null;
                selectedCustNameForSC = new ArrayList();
                selectedCustNameValues = new ArrayList();
                custNames = new ArrayList<SelectItem>();
                incoTermForSC = null;
                selectedIncoTermSC = new ArrayList();
                paymentTemForSC = null;
                selectedPaymentTermSC = new ArrayList();
                bindCustName.setDisabled(false);
                bindSelectManyCustNames.setDisabled(false);
                bindCurrency.setDisabled(false);
                bindSelectManyCurrencies.setDisabled(false);
                bindIncoTerms.setDisabled(false);
                bindSelectManyIncoTerms.setDisabled(false);
                bindPaymentTerms.setDisabled(false);
                bindSelectManyPaymentTerms.setDisabled(false);
                bindBA.setDisabled(false);
                bindSelectManyBA.setDisabled(false);
                getAllCustNames();
                getDefaultValuesForSC();
                getSelectedCurrencyValuesForSC();
                getSelectedIncoTermValForSC();
                getSelectedBusinessAgreementForSC();
                getSelectedPaymentTermForSC();
                getSlectedCustNamesForSC();
            }
        }
    }








    public void custNameVCE(ValueChangeEvent vce) {
        List<String> selectedValues = new ArrayList<String>();
        List selectedListFromUI = new ArrayList();
        if (vce.getNewValue() == null) {
            return;
        }
        //            Map<String, Object> pfs =
        //                AdfFacesContext.getCurrentInstance().getPageFlowScope();
        if (selectedCustNameValues == null)
            selectedCustNameValues = new ArrayList();

        selectedListFromUI = (List)vce.getNewValue();
        if (selectedListFromUI.size() > 0) {
            for (int i = 0; i < selectedListFromUI.size(); i++) {
                System.out.println("Selected values are from loop:" +
                                   selectedListFromUI.get(i) + "id value is:" +
                                   i);
                selectedValues.add(selectedListFromUI.get(i).toString());
            }
            selectedCustNameValues = selectedValues;
            //            pfs.put("selectedCustList", selectedValues);
        }
    }


    public List<SelectItem> getAllBAgreement() {
        long startTime = System.currentTimeMillis();
        System.out.println("getAllBA: Start " +
                           (System.currentTimeMillis() - startTime));
        if (businessAgreemnt != null) {
            if (businessCenterCSRForDefault != null)
                businessCenterCSRForDefault.add(new SelectItem(null));
            DCIteratorBinding iter =
                ADFUtils.findIterator("businessAgreementVoIterator");
            RowSetIterator it =
                iter.getViewObject().createRowSetIterator(null);
            while (it.hasNext()) {
                Row row = it.next();
                businessAgreemnt.add(new SelectItem(row.getAttribute("Name")));
            }
            it.closeRowSetIterator();
            System.out.println("getAllBAValues: after close Rowset: " +
                               (System.currentTimeMillis() - startTime));
        }

        return businessAgreemnt;

    }


    public void setBusinessAgreemnt(List<SelectItem> businessAgreemnt) {
        this.businessAgreemnt = businessAgreemnt;
    }

    public List<SelectItem> getBusinessAgreemnt() {

        return businessAgreemnt;
    }

    public void setSelectedBusinessAgreementValues(List selectedBusinessAgreementValues) {
        this.selectedBusinessAgreementValues = selectedBusinessAgreementValues;
    }


    public List getSelectedBusinessAgreementForSC() {
        long startTime = System.currentTimeMillis();
        System.out.println("getSelctedBAValues:Start" +
                           (System.currentTimeMillis() - startTime));
        if (selectedBusinessAgreementValues != null &&
            selectedBusinessAgreementValues.size() == 0) {
            selectedBusinessAgreementValues.add(new SelectItem(null));
            OperationBinding ob =
                ADFUtils.getBindingContainer().getOperationBinding("getSelectedBusinessAgreementForSC");
            ob.getParamsMap().put("usrId", usrId);
            ob.getParamsMap().put("salesChannel", salesChannel);
            if (ob != null) {
                selectedBusinessAgreementValues = (List<String>)ob.execute();
                System.out.println("getSelctedBAValues:End" +
                                   (System.currentTimeMillis() - startTime));
            }
        }
        return selectedBusinessAgreementValues;

    }


    public List getSelectedBusinessAgreementValues() {
        return selectedBusinessAgreementValues;
    }

    public void businessAgreementVCE(ValueChangeEvent vce) {
        List<String> selectedValues = new ArrayList<String>();
        List selectedListFromUI = new ArrayList();
        if (vce.getNewValue() == null) {
            return;
        }
        //        Map<String, Object> pfs =
        //            AdfFacesContext.getCurrentInstance().getPageFlowScope();
        selectedListFromUI = (List)vce.getNewValue();
        if (selectedListFromUI.size() > 0) {
            for (int i = 0; i < selectedListFromUI.size(); i++) {
                System.out.println("Selected values are from loop:" +
                                   selectedListFromUI.get(i) + "id value is:" +
                                   i);
                selectedValues.add(selectedListFromUI.get(i).toString());
            }
            selectedBusinessAgreementValues = selectedValues;
            //            pfs.put("selectedBusinessAgreementList", selectedValues);
        }
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }


    public void setCustNumbers(List<SelectItem> custNumbers) {
        this.custNumbers = custNumbers;
    }


    public List<SelectItem> getAllCustNums() {
        long startTime = System.currentTimeMillis();
        System.out.println("getAllCustNumValues:Start" +
                           (System.currentTimeMillis() - startTime));
//        if (custNames == null)
//            custNames = new ArrayList<SelectItem>();
        if (custNumbers == null)
            custNumbers = new ArrayList<SelectItem>();
        //            custNames.add(new SelectItem(null));
        //            custNumbers.add(new SelectItem(null));
//        if (custNamesForDefault == null)
//            custNamesForDefault = new ArrayList<SelectItem>();
        if (custNumbersForDefault == null)
            custNumbersForDefault = new ArrayList<SelectItem>();
//        custNamesForDefault.add(new SelectItem(null));
        custNumbersForDefault.add(new SelectItem(null));

        if (orgId != null) {
            DCIteratorBinding DCiter =
                ADFUtils.findIterator("CustomerNameVOIterator");
            ViewObjectImpl vo = (ViewObjectImpl)DCiter.getViewObject();
            if (vo != null) {
                vo.clearCache();
                vo.setNamedWhereClauseParam("p_orgId", null);
                vo.setWhereClause(null);
                vo.setNamedWhereClauseParam("p_orgId", new BigDecimal(orgId));
                vo.executeQuery();
                RowSetIterator iter = vo.createRowSetIterator(null);
                while (iter.hasNext()) {
                    Row r = iter.next();
//                    custNames.add(new SelectItem(r.getAttribute("Customername")));
                    custNumbers.add(new SelectItem(r.getAttribute("Accountnumber")));
                    customerNums.add(r.getAttribute("Accountnumber"));
//                    custNamesForDefault.add(new SelectItem(r.getAttribute("Customername")));
                    custNumbersForDefault.add(new SelectItem(r.getAttribute("Accountnumber")));
                }
                if (iter != null) {
                    iter.closeRowSetIterator();
                }
            }
        }
        return custNumbers;
    }

   
   
   
    public List<SelectItem> getAllCustNames() {
        long startTime = System.currentTimeMillis();
        System.out.println("getAllCustNumForSCValues:Start" +
                           (System.currentTimeMillis() - startTime));
            if (custNames == null)
                custNames = new ArrayList<SelectItem>();
      
            if (custNamesForDefault == null)
                custNamesForDefault = new ArrayList<SelectItem>();
                custNamesForDefault.add(new SelectItem(null));

        if (scOrgId != null) {
            DCIteratorBinding DCiter =
                ADFUtils.findIterator("CustomerNameVOIterator");
            ViewObjectImpl vo = (ViewObjectImpl)DCiter.getViewObject();
            if (vo != null) {
                vo.clearCache();
                vo.setNamedWhereClauseParam("p_orgId", null);
                vo.setWhereClause(null);
                vo.setNamedWhereClauseParam("p_orgId", new BigDecimal(scOrgId));
                vo.executeQuery();
                RowSetIterator iter = vo.createRowSetIterator(null);
                while (iter.hasNext()) {
                    Row r = iter.next();
                        custNames.add(new SelectItem(r.getAttribute("Customername")));
                        custNamesForDefault.add(new SelectItem(r.getAttribute("Customername")));
                }
                if (iter != null) {
                    iter.closeRowSetIterator();
                }
            }
        }
        return custNames;
    }

  




    //        if (orglist != null && orglist.size() > 0) {
    //            StringBuilder sb = new StringBuilder();
    //            String query1 = "SELECT p.party_name customername, p.party_number registryid,\n" +
    //            "       ca.account_number accountnumber, ca.account_name accountdescription,\n" +
    //            "       ca.cust_account_id, p.party_type customertype, p.party_id partyid,\n" +
    //            "       NULL salesforceid\n" +
    //            "  FROM hz_parties p, hz_cust_accounts ca\n" +
    //            "WHERE ca.status(+) = 'A'\n" +
    //            "   AND ca.party_id = p.party_id\n" +
    //            "   AND NVL (ca.account_activation_date, SYSDATE) <= SYSDATE\n" +
    //            "   AND NVL (ca.account_termination_date, SYSDATE) >= SYSDATE\n" +
    //            "   AND p.status = 'A'\n" +
    //            "   AND p.party_id = ca.party_id";
    //
    //            String query2 = " AND EXISTS (\n" +
    //                "          SELECT 1\n" +
    //                "            FROM hz_cust_site_uses_all hzsu,\n" +
    //                "                 hz_cust_acct_sites_all hzas,\n" +
    //                "                 hz_party_sites hzps\n" +
    //                "           WHERE hzsu.primary_flag = 'Y'\n" +
    //                "             AND hzsu.cust_acct_site_id = hzas.cust_acct_site_id\n" +
    //                "             AND hzsu.org_id = hzas.org_id\n" +
    //                "             AND hzas.cust_account_id = ca.cust_account_id\n" +
    //                "             AND hzps.party_site_id = hzas.party_site_id" +
    //                "             AND hzsu.org_id in(";
    //            if (custNumbers != null) {
    //                DCIteratorBinding iter =
    //                    ADFUtils.findIterator("CustomerNameFOrPrefVOIterator");
    //                ViewObjectImpl vo = (ViewObjectImpl)iter.getViewObject();
    //
    //                if (vo != null) {
    //                    vo.clearCache();
    //                    vo.setWhereClause(null);
    //                    sb.append(query1);
    //                    sb.append(query2);
    //                    sb.append(orglist.get(0));
    //                    for (int i = 1; i < orglist.size(); i++) {
    //                        sb.append(",").append(orglist.get(i));
    //                        System.out.println("org Value is:" + orglist.get(i));
    //                    }
    //                    sb.append("))");
    //                    // System.out.println("set of OrgList:" + sb.toString());
    //                    vo.setQuery(null);
    //                    vo.setQuery(sb.toString());
    //                    //                    vo.setWhereClause(sb.toString());
    //                    System.out.println("get Customer Query:" + vo.getQuery());
    //                    vo.executeQuery();
    //                    RowQualifier rq = new RowQualifier(vo);
    //                    Row filteredRows[] = vo.getFilteredRows(rq);
    //                    if (custNumbers != null && custNumbers.size() > 0) {
    //                        custNames.clear();
    //                        custNumbers.clear();
    //                        customerNums.clear();
    //                    }
    //                    for (Row r : filteredRows) {
    //                        custNames.add(new SelectItem(r.getAttribute("Customername")));
    //                        custNumbers.add(new SelectItem(r.getAttribute("Accountnumber")));
    //                        customerNums.add(r.getAttribute("Accountnumber"));
    //                    }
    //
    //                    //                                        RowSetIterator custIter = vo.createRowSetIterator(null);
    //                    //                                        while (custIter.hasNext()) {
    //                    //                                            Row row = custIter.next();
    //                    //                                            custNames.add(new SelectItem(row.getAttribute("Customername")));
    //                    //                                            //                        custNumbers.add(row.getAttribute("Accountnumber"));
    //                    //                                            custNumbers.add(new SelectItem(row.getAttribute("Accountnumber")));
    //                    //                                        }
    //                    System.out.println("getAllCustNumalues:Start" +
    //                                       (System.currentTimeMillis() -
    //                                        startTime));
    //                }
    //            }
    //        }
    //        return custNumbers;
    //    }

    public List<SelectItem> getCustNumbers() {
        return custNumbers;
    }

    //    public void setBindOrderType(RichInputListOfValues bindOrderType) {
    //        this.bindOrderType = bindOrderType;
    //    }
    //
    //    public RichInputListOfValues getBindOrderType() {
    //        return bindOrderType;
    //    }

    public void orderTypeForGCVCE(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            String orderValue = valueChangeEvent.getNewValue().toString();
            System.out.println("Old value is:" +
                               valueChangeEvent.getOldValue());
            System.out.println("Changed value is:" +
                               valueChangeEvent.getNewValue());
            System.out.println("orderType value is:" + orderValue);
        }
    }

    //    public void setvalueToExpression(String el, Object val) {
    //            FacesContext facesContext = FacesContext.getCurrentInstance();
    //            ELContext elContext = facesContext.getELContext();
    //            ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
    //            ValueExpression exp = expressionFactory.createValueExpression(elContext, el, Object.class);
    //            exp.setValue(elContext, val);
    //        }
    //
    //        /**Method to get value from Expression (EL)
    //     * @param data
    //     * @return
    //     */
    //        public String getValueFrmExpression(String data) {
    //    FacesContext fc = FacesContext.getCurrentInstance();
    //    Application app = fc.getApplication();
    //    ExpressionFactory elFactory = app.getExpressionFactory();
    //    ELContext elContext = fc.getELContext();
    //    ValueExpression valueExp = elFactory.createValueExpression(elContext, data, Object.class);
    //    String Message = null;
    //    Object obj = valueExp.getValue(elContext);
    //            if (obj != null) {
    //    Message = obj.toString();
    //            }
    //            return Message;
    //        }
    //


    public void currencyForGlobalChoiceVCE(ValueChangeEvent vce) {
        System.out.println("Selected Value is:" + vce.getNewValue());
        currency = (String)vce.getNewValue();
        System.out.println(currency);
    }

    public void incoTermForGlobalChoiceVCE(ValueChangeEvent vce) {
        System.out.println("Selected Value is:" + vce.getNewValue());
        incoTerm = (String)vce.getNewValue();
        System.out.println(incoTerm);
    }

    public void paymentTermForGlobalChoiceVCE(ValueChangeEvent vce) {
        System.out.println("Selected Value is:" + vce.getNewValue());
        paymentTerm = (String)vce.getNewValue();
        System.out.println(paymentTerm);
    }

    public void csrForGlobalChoiceVCE(ValueChangeEvent vce) {
        System.out.println("Selected Value is:" + vce.getNewValue());
        csr = (String)vce.getNewValue();
        System.out.println(csr);
    }

    public void salesRepForGlobalChoiceVCE(ValueChangeEvent vce) {
        System.out.println("Selected Value is:" + vce.getNewValue());
        salesRep = (String)vce.getNewValue();
        System.out.println(salesRep);
    }

    public void custNumForDefaultVCE(ValueChangeEvent vce) {
        System.out.println("Selected Value is:" + vce.getNewValue());
        custNum = (String)vce.getNewValue();
        System.out.println(custNum);
    }

    public void salesChannelForDefaultVCE(ValueChangeEvent vce) {
        
        if (vce.getNewValue() != null &&
            vce.getOldValue() != vce.getNewValue() && scOrgId!=null) {
            custNameForSC = null;
            custNames = new ArrayList<SelectItem>();
            currencyForSC = null;   
            selectedCurrencyValsSC = new ArrayList();
            baForSC = null;
            selectedCustNameValues = new ArrayList();
            custNames = new ArrayList<SelectItem>();
            incoTermForSC = null;
            selectedIncoTermSC = new ArrayList();
            paymentTemForSC = null;
            selectedPaymentTermSC = new ArrayList();
            bindCustName.setDisabled(false);
            bindSelectManyCustNames.setDisabled(false);
            bindCurrency.setDisabled(false);
            bindSelectManyCurrencies.setDisabled(false);
            bindIncoTerms.setDisabled(false);
            bindSelectManyIncoTerms.setDisabled(false);
            bindPaymentTerms.setDisabled(false);
            bindSelectManyPaymentTerms.setDisabled(false);
            bindBA.setDisabled(false);
            bindSelectManyBA.setDisabled(false);
            isSalesChnnelEnable = true;
            salesChannel = (String)vce.getNewValue();
                getAllCustNames();
                getDefaultValuesForSC();
                getSelectedCurrencyValuesForSC();
                getSelectedIncoTermValForSC();
                getSelectedBusinessAgreementForSC();
                getSelectedPaymentTermForSC();
                getSlectedCustNamesForSC();
        }
        else {
                custNameForSC = null;
                custNames = new ArrayList<SelectItem>();
                currencyForSC = null;   
                selectedCurrencyValsSC = new ArrayList();
                baForSC = null;
                selectedCustNameValues = new ArrayList();
                custNames = new ArrayList<SelectItem>();
                incoTermForSC = null;
                selectedIncoTermSC = new ArrayList();
                paymentTemForSC = null;
                selectedPaymentTermSC = new ArrayList();
                bindCustName.setDisabled(true);
                bindSelectManyCustNames.setDisabled(true);
                bindCurrency.setDisabled(true);
                bindSelectManyCurrencies.setDisabled(true);
                bindIncoTerms.setDisabled(true);
                bindSelectManyIncoTerms.setDisabled(true);
                bindPaymentTerms.setDisabled(true);
                bindSelectManyPaymentTerms.setDisabled(true);
                bindBA.setDisabled(true);
                bindSelectManyBA.setDisabled(true);
            }

    }


    public void custNameForSCDefaultVCE(ValueChangeEvent vce) {
        System.out.println("Selected Value is:" + vce.getNewValue());
        custNameForSC = (String)vce.getNewValue();
        System.out.println(custNameForSC);
    }

    public void currencyForSCDefaultVCE(ValueChangeEvent vce) {
        System.out.println("Selected Value is:" + vce.getNewValue());
        currencyForSC = (String)vce.getNewValue();
        System.out.println(currencyForSC);
    }

    public void baForSCDefaultVCE(ValueChangeEvent vce) {
        System.out.println("Selected Value is:" + vce.getNewValue());
        baForSC = (String)vce.getNewValue();
        System.out.println(baForSC);
    }

    public void incoTermForSCDefaultVCE(ValueChangeEvent vce) {
        System.out.println("Selected Value is:" + vce.getNewValue());
        incoTermForSC = (String)vce.getNewValue();
        System.out.println(incoTermForSC);
    }

    public void paymentTErmForSCDefaultVCE(ValueChangeEvent vce) {
        System.out.println("Selected Value is:" + vce.getNewValue());
        paymentTemForSC = (String)vce.getNewValue();
        System.out.println(paymentTemForSC);
    }

    public void OrderTypeForChoiceVCE(ValueChangeEvent vce) {
        System.out.println("Selected Value is:" + vce.getNewValue());
        orderType = (String)vce.getNewValue();
        System.out.println(orderType);
    }

    //    public void getRadioButtonValues(ActionEvent actionEvent) {
    //
    //        System.out.println("value is:" + getBindPrdNumTConf().getValue());
    //        String temp = null;
    //        DCIteratorBinding iter = ADFUtils.findIterator("QuotesVOIterator");
    //        ViewObject vo = iter.getViewObject();
    //        if (vo != null) {
    //            Row row = vo.getCurrentRow();
    //            temp = (String)row.getAttribute("PrdNumTargetConfig");
    //            System.out.println(temp);
    //        }
    //    }

    public void setSelectedCustNumValues(List selectedCustNumValues) {
        this.selectedCustNumValues = selectedCustNumValues;
    }

    public List getSelectedCustNumVals() {
        long startTime = System.currentTimeMillis();
        System.out.println("getSelctedCustNumValues:Start" +
                           (System.currentTimeMillis() - startTime));
        List custNumList = new ArrayList();
        if (selectedCustNumValues == null)
            selectedCustNumValues = new ArrayList();
        if (selectedCustNumValues != null &&
            selectedCustNumValues.size() == 0) {
            //            if (this.customerNums != null && this.customerNums.size() > 0) {
            OperationBinding ob =
                ADFUtils.getBindingContainer().getOperationBinding("getSelectedCustNumValues");
            ob.getParamsMap().put("usrId", usrId);
            ob.getParamsMap().put("orgId", orgId);
            if (ob != null) {
                custNumList = (List)ob.execute();
                if (custNumList != null && custNumList.size() > 0) {
                    for (int i = 0; i < custNumList.size(); i++) {
                        if (custNumList.contains(custNumList.get(i))) {
                            selectedCustNumValues.add(custNumList.get(i));
                        }
                    }
                }
                //                    selectedCustNumValues = (List)ob.execute();
                System.out.println("getSelctedCustNumValues:End" +
                                   (System.currentTimeMillis() - startTime));
            }
            //            }
        }
        return selectedCustNumValues;
    }

    public List getSelectedCustNumValues() {
        return selectedCustNumValues;
    }

    public void custNumVCE(ValueChangeEvent vce) {
        if (vce.getNewValue() == null) {
            return;
        }
        List selectedListFromUI = (List)vce.getNewValue();
        if (selectedListFromUI.size() > 0) {
            if (selectedCustNumValues != null &&
                selectedCustNumValues.size() > 0)
                selectedCustNumValues.clear();
            for (int i = 0; i < selectedListFromUI.size(); i++) {
                System.out.println("Selected values are from loop:" +
                                   selectedListFromUI.get(i) + "id value is:" +
                                   i);
                selectedCustNumValues = selectedListFromUI;
            }
        }
    }

    public void setIsOu(boolean isOu) {
        this.isOu = isOu;
    }

    public boolean isIsOu() {
        return isOu;
    }

    public void setIsGlobalChoiceVals(boolean isGlobalChoiceVals) {
        this.isGlobalChoiceVals = isGlobalChoiceVals;
    }

    public boolean isIsGlobalChoiceVals() {
        return isGlobalChoiceVals;
    }

    public void businessCenterCSRVCE(ValueChangeEvent vce) {
        if (vce.getNewValue() == null) {
            return;
        }
        List selectedListFromUI = (List)vce.getNewValue();
        if (selectedListFromUI.size() > 0) {
            if (selectedCSRValues != null && selectedCSRValues.size() > 0)
                selectedCSRValues.clear();
            for (int i = 0; i < selectedListFromUI.size(); i++) {
                System.out.println("Selected values are from loop:" +
                                   selectedListFromUI.get(i) + "id value is:" +
                                   i);
                selectedCSRValues = selectedListFromUI;
            }
        }

    }

    public void salesRepVCE(ValueChangeEvent vce) {
        if (vce.getNewValue() == null) {
            return;
        }
        List selectedListFromUI = (List)vce.getNewValue();
        if (selectedListFromUI.size() > 0) {
            for (int i = 0; i < selectedListFromUI.size(); i++) {
                System.out.println("Selected values are from loop:" +
                                   selectedListFromUI.get(i) + "id value is:" +
                                   i);
            }

            selectedSalesRepValues = selectedListFromUI;
        }
    }

    public void setBusinessCenterCSR(List<SelectItem> businessCenterCSR) {
        this.businessCenterCSR = businessCenterCSR;
    }

    public List<SelectItem> getAllCSRValues() {
        long startTime = System.currentTimeMillis();
        System.out.println("getAllCSRValues:Start" +
                           (System.currentTimeMillis() - startTime));
        if (businessCenterCSR != null) {
            //            businessCenterCSR.add(new SelectItem(null));
            if (businessCenterCSRForDefault != null)
                businessCenterCSRForDefault.add(new SelectItem(null));
            DCIteratorBinding iter =
                ADFUtils.findIterator("CSRForGlobalChoiceVO1Iterator");
            RowSetIterator it =
                iter.getViewObject().createRowSetIterator(null);
            while (it.hasNext()) {
                Row row = it.next();
                businessCenterCSR.add(new SelectItem(row.getAttribute("CustomerName")));
                businessCenterCSRForDefault.add(new SelectItem(row.getAttribute("CustomerName")));
            }
            it.closeRowSetIterator();
            System.out.println("getAllCSRValues:Start" +
                               (System.currentTimeMillis() - startTime));
        }
        return businessCenterCSR;
    }

    public List<SelectItem> getBusinessCenterCSR() {
        return businessCenterCSR;
    }

    public void setSelectedCSRValues(List selectedCSRValues) {
        this.selectedCSRValues = selectedCSRValues;
    }

    public List getSelectedCSRVals() {
        long startTime = System.currentTimeMillis();
        System.out.println("getSelectedCSRVals: Start " +
                           (System.currentTimeMillis() - startTime));
        if (selectedCSRValues != null && selectedCSRValues.size() == 0) {

            OperationBinding ob =
                ADFUtils.getBindingContainer().getOperationBinding("getSelectedCSRValues");
            ob.getParamsMap().put("usrId", usrId);
            if (ob != null) {
                selectedCSRValues = (List<String>)ob.execute();
            }
            System.out.println("getSelectedCSRVals: closing " +
                               (System.currentTimeMillis() - startTime));
        }
        return selectedCSRValues;
    }

    public List getSelectedCSRValues() {
        return selectedCSRValues;
    }

    public void setSalesRepValues(List<SelectItem> SalesRepValues) {
        this.SalesRepValues = SalesRepValues;
    }

    public List<SelectItem> getAllSalesRepValues() {
        List salesRepVal = new ArrayList();
        long startTime = System.currentTimeMillis();
        System.out.println("getSalesRepValues:Start" +
                           (System.currentTimeMillis() - startTime));
        if (orgId != null) {
            if (SalesRepValues != null)
                SalesRepValues.clear();
            if (SalesRepValuesForDefault != null)
                SalesRepValuesForDefault.add(new SelectItem(null));
            //            SalesRepValues.add(null);
            OperationBinding ob =
                ADFUtils.findOperation("getAllSalesRepValues");
            ob.getParamsMap().put("orgId", orgId);
            if (ob != null) {
                salesRepVal = (List)ob.execute();
            }
            if (salesRepVal != null) {
                if (salesRepVal.size() > 0) {
                    for (int i = 0; i < salesRepVal.size(); i++) {
                        SalesRepValues.add(new SelectItem(salesRepVal.get(i)));
                        SalesRepValuesForDefault.add(new SelectItem(salesRepVal.get(i)));
                    }
                }
            }
            System.out.println("getSalesRepValues:Start" +
                               (System.currentTimeMillis() - startTime));
        }
        //        }
        return SalesRepValues;
    }

    public List<SelectItem> getSalesRepValues() {
        return SalesRepValues;
    }

    public void setSelectedSalesRepValues(List selectedSalesRepValues) {
        this.selectedSalesRepValues = selectedSalesRepValues;
    }

    public List getSelectedSalesRepVals() {
        long startTime = System.currentTimeMillis();
        System.out.println("getSelcteSalesRepValues:Start" +
                           (System.currentTimeMillis() - startTime));
        if (selectedSalesRepValues != null &&
            selectedSalesRepValues.size() == 0) {
            OperationBinding ob =
                ADFUtils.getBindingContainer().getOperationBinding("getSelectedSalesRepValues");
            ob.getParamsMap().put("usrId", usrId);
            ob.getParamsMap().put("orgId", orgId);
            if (ob != null) {
                selectedSalesRepValues = (List)ob.execute();
                System.out.println("getSelctedSalesRepValues:End" +
                                   (System.currentTimeMillis() - startTime));
            }
        }
        return selectedSalesRepValues;
    }

    public List getSelectedSalesRepValues() {
        return selectedSalesRepValues;
    }

    public void setSalesRepIds(List salesRepIds) {
        this.salesRepIds = salesRepIds;
    }

    public List getSalesRepIds() {
        return salesRepIds;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setCustNum(String custNum) {
        this.custNum = custNum;
    }

    public String getCustNum() {
        return custNum;
    }


    public void setOuGetters(String ouGetters) {
        this.ouGetters = ouGetters;
    }

    public String getOuGetters() {
        return ouGetters;
    }

    public void setCustomerNums(List customerNums) {
        this.customerNums = customerNums;
    }

    public List getCustomerNums() {
        return customerNums;
    }

    public void setOtValues(List otValues) {
        this.otValues = otValues;
    }

    public List getOtValues() {
        return otValues;
    }

    public void setIncoTerm(String incoTerm) {
        this.incoTerm = incoTerm;
    }

    public String getIncoTerm() {
        return incoTerm;
    }

    public void setCsr(String csr) {
        this.csr = csr;
    }

    public String getCsr() {
        return csr;
    }

    public void salesRepForDefaultVCE(ValueChangeEvent vce) {
        if (vce.getNewValue() != null &&
            vce.getOldValue() != vce.getNewValue()) {
            salesRep = (String)vce.getNewValue();

        }
    }

    public void setSalesRep(String salesRep) {
        this.salesRep = salesRep;
    }

    public String getSalesRep() {
        return salesRep;
    }

    public String getSelectedDefaultValue(String colType) {
        String colValues = null;
        //            String colType = "Order_type";
        DCIteratorBinding iter =
            ADFUtils.findIterator("userPrefEntityVOIterator");
        ViewObject vo = iter.getViewObject();
        if (vo != null) {
            vo.clearCache();
            vo.setWhereClause(null);
            System.out.println("size of VO:" + vo.getEstimatedRowCount());
            Object obj[] = { usrId, colType };
            Key key = new Key(obj);
            Row[] rows = vo.findByKey(key, 2);
            if (rows != null && rows.length > 0) {
                colValues = (String)rows[0].getAttribute("ColumnVal");
            }
        }
        return colValues;
    }


    public void getDefaultValuesForSC() {
        Map map = new HashMap();
        OperationBinding ob = ADFUtils.findOperation("defaultValsForSC");
        ob.getParamsMap().put("usrId", usrId);
        ob.getParamsMap().put("salesChannel", salesChannel);
        ob.getParamsMap().put("customerNums", customerNums);
        ob.getParamsMap().put("orgId", scOrgId);
        //        ob.getParamsMap().put("orgList", orglist);
        //        ob.getParamsMap().put("custList",custNumbers);
        if (ob != null) {
            map = (Map)ob.execute();
        }
        if (map != null) {

            if (map.containsKey("currency")) {
                if (map.get("currency") != null) {
                    _logger.info("User Preferences: getDefaultValues: map values: currency:" +
                                 map.get("currency"));
                    currencyForSC = (String)map.get("currency");
                } else {
                    currencyForSC = null;
                }
            }
            if (map.containsKey("customer")) {
                if (map.get("customer") != null) {
                    _logger.info("User Preferences: getDefaultValues: map values: orderType:" +
                                 map.get("customer"));
                    custNameForSC = (String)map.get("customer");
                } else
                    custNameForSC = null;
            } else
                custNameForSC = null;
            if (map.containsKey("incoTerm")) {
                incoTermForSC = (String)map.get("incoTerm");
            }
            if (map.containsKey("ba")) {
                _logger.info("User Preferences: getDefaultValues: map values: csr:" +
                             map.get("ba"));
                baForSC = (String)map.get("ba");
            }
            if (map.containsKey("paymentTerm")) {
                _logger.info("User Preferences: getDefaultValues: map values: paymentTerm:" +
                             map.get("paymentTerm"));
                paymentTemForSC = (String)map.get("paymentTerm");
            }
        }
    }

    public void getDefaultValsForOUDependencies(String orgId){
            Map map = new HashMap();
            OperationBinding ob =
                ADFUtils.findOperation("defaultValsOUDependencies"); //customerNums
            ob.getParamsMap().put("usrId", usrId);
            ob.getParamsMap().put("orgId", orgId);
            if (ob != null) 
                map = (Map)ob.execute();
            
            if (map != null) {
                if (map.containsKey("orderType")) {
                    if (map.get("orderType") != null) {
                        _logger.info("User Preferences: getDefaultValues: map values: orderType:" +
                                     map.get("orderType"));
                        if ((String)map.get("orderType") != null) {
                            orderType = (String)map.get("orderType");
                        } else
                            orderType = null;
                    }
                }
                if (map.containsKey("custNum")) {
                    if (map.get("custNum") != null) {
                        custNum = (String)map.get("custNum");
                    } else
                        custNum = null;
                }
               
                if (map.containsKey("salesRep")) {
                    _logger.info("User Preferences: getDefaultValues: map values: salesRep:" +
                                 map.get("salesRep"));
                    if (map.get("salesRep") != null) {
                        String sp = (String)map.get("salesRep");
                        if (SalesRepValues.contains(sp)) {
                            salesRep = sp;
                        }
                    }
                    salesRep = (String)map.get("salesRep");
                    //                    bindSalesRep.setValue(map.get("salesRep"));
                }
            }
        }


    public void getDefaultValues() {
        _logger.info("User Preferences: getDefaultValues: Start:");
        Map map = new HashMap();
        OperationBinding ob =
            ADFUtils.findOperation("defaultVals"); //customerNums
        ob.getParamsMap().put("usrId", usrId);
        if (ob != null) {
            map = (Map)ob.execute();
        }
        if (map != null) {

            if (map.containsKey("currency")) {
                if (map.get("currency") != null) {
                    _logger.info("User Preferences: getDefaultValues: map values: currency:" +
                                 map.get("currency"));
                    currency = (String)map.get("currency");
                } else {
                    currency = null;
                }
            }
            if (map.containsKey("orderType")) {
                if (map.get("orderType") != null) {
                    _logger.info("User Preferences: getDefaultValues: map values: orderType:" +
                                 map.get("orderType"));
                    if ((String)map.get("orderType") != null) {
                        orderType = (String)map.get("orderType");
                        //                        String ot = (String)map.get("orderType");
                        //                        if (orderTypeValues.contains(ot)) {
                        //                            orderType = ot;
                        //                        }
                        //                        orderType = (String)map.get("orderType");
                    } else
                        orderType = null;
                }
            }
            if (map.containsKey("custNum")) {
                if (map.get("custNum") != null) {
                    custNum = (String)map.get("custNum");
                    //                    String num = (String)map.get("custNum");
                    //                    if (customerNums.contains(num)) {
                    //                        custNum = num;
                    //                    }
                } else
                    custNum = null;
            }
            if (map.containsKey("incoTerm")) {
                if ((String)map.get("incoTerm") != null)
                    incoTerm = (String)map.get("incoTerm");
                else
                    incoTerm = null;
                //                    bindIncoTerm.setValue(map.get("incoTerm"));
            }
            if (map.containsKey("csr")) {
                _logger.info("User Preferences: getDefaultValues: map values: csr:" +
                             map.get("csr"));
                csr = (String)map.get("csr");
                //                    bindCSR.setValue(map.get("csr"));
            }
            if (map.containsKey("paymentTerm")) {
                _logger.info("User Preferences: getDefaultValues: map values: paymentTerm:" +
                             map.get("paymentTerm"));
                paymentTerm = (String)map.get("paymentTerm");
                //                    bindPaymentTerm.setValue(map.get("paymentTerm"));  //salesRep
            }
            if (map.containsKey("salesRep")) {
                _logger.info("User Preferences: getDefaultValues: map values: salesRep:" +
                             map.get("salesRep"));
                if (map.get("salesRep") != null) {
                    String sp = (String)map.get("salesRep");
                    if (SalesRepValues.contains(sp)) {
                        salesRep = sp;
                    }
                }
                salesRep = (String)map.get("salesRep");
                //                    bindSalesRep.setValue(map.get("salesRep"));
            }

        }
        //        String colVal = getSelectedDefaultValue("Order_type");
        //        if(colVal !=null){
        //        setOrderType(colVal);
        //            }
    }

    public void getDefaultCustNumVal() {
        String colVal = getSelectedDefaultValue("Currency");
        if (colVal != null) {
            setCustNum(colVal);
        }
    }


    public void setSelectedCustNameForSC(List selectedCustNameForSC) {
        this.selectedCustNameForSC = selectedCustNameForSC;
    }

    public List getSelectedCustNameForSC() {
        return selectedCustNameForSC;
    }


    public void salesChannelVCE(ValueChangeEvent vce) {
        if (vce.getNewValue() == null)
            return;

        //            List<String> selectedValues = new ArrayList<String>();
        List selectedListFromUI = new ArrayList();
        selectedListFromUI = (List)vce.getNewValue();
        if (selectedListFromUI.size() > 0) {
            for (int i = 0; i < selectedListFromUI.size(); i++) {
                System.out.println("Selected values are from loop:" +
                                   selectedListFromUI.get(i) + "id value is:" +
                                   i);
                //                    selectedValues.add(selectedListFromUI.get(i).toString());
            }
            selectedSalesChannel = selectedListFromUI;
        }
    }


    public void custNameForSCVCE(ValueChangeEvent vce) {
        List<String> selectedValues = new ArrayList<String>();
        List selectedListFromUI = new ArrayList();
        if (vce.getNewValue() == null) {
            return;
        }
        if (vce.getOldValue() != vce.getNewValue() &&
            vce.getNewValue() != null) {
            if (selectedCustNameValues != null)
                selectedCustNameValues.clear();
            selectedListFromUI = (List)vce.getNewValue();
            if (selectedListFromUI.size() > 0) {
                for (int i = 0; i < selectedListFromUI.size(); i++) {
                    System.out.println("Selected values are from loop:" +
                                       selectedListFromUI.get(i) +
                                       "id value is:" + i);
                    selectedValues.add(selectedListFromUI.get(i).toString());
                }
                selectedCustNameValues = selectedValues;
            }
        }
    }

    public void currencySCVCE(ValueChangeEvent vce) {
        if (vce.getNewValue() != null &&
            vce.getOldValue() != vce.getNewValue()) {
            System.out.println("vce Values:" + vce.getNewValue().toString());
            List<String> selectedValues = new ArrayList<String>();
            List selectedListFromUI = new ArrayList();
            selectedListFromUI = (List)vce.getNewValue();
            if (selectedListFromUI.size() > 0) {
                for (int i = 0; i < selectedListFromUI.size(); i++) {
                    System.out.println("Selected values are from loop:" +
                                       selectedListFromUI.get(i) +
                                       "id value is:" + i);
                    selectedValues.add(selectedListFromUI.get(i).toString());
                }
                selectedCurrencyValsSC = selectedValues;
            }

        }
    }


    public void incoTermForSCVCE(ValueChangeEvent vce) { //selectedIncoTermSC
        List<String> selectedValues = new ArrayList<String>();
        List selectedListFromUI = new ArrayList();
        if (vce.getNewValue() == null) {
            return;
        }
        selectedListFromUI = (List)vce.getNewValue();
        if (selectedListFromUI.size() > 0) {
            for (int i = 0; i < selectedListFromUI.size(); i++) {
                System.out.println("Selected values are from loop:" +
                                   selectedListFromUI.get(i) + "id value is:" +
                                   i);
                selectedValues.add(selectedListFromUI.get(i).toString());
            }
            selectedIncoTermSC = selectedValues;
        }

    }


    public void paymentTermVCE(ValueChangeEvent vce) { // selectedPaymentTermSC
        List<String> selectedValues = new ArrayList<String>();
        List selectedListFromUI = new ArrayList();
        if (vce.getNewValue() == null) {
            return;
        }
        selectedListFromUI = (List)vce.getNewValue();
        if (selectedListFromUI.size() > 0) {
            for (int i = 0; i < selectedListFromUI.size(); i++) {
                System.out.println("Selected values are from loop:" +
                                   selectedListFromUI.get(i) + "id value is:" +
                                   i);
                selectedValues.add(selectedListFromUI.get(i).toString());
            }
            selectedPaymentTermSC = selectedValues;
        }

    }

    public List getSelectedPaymentTermForSC() {
        long startTime = System.currentTimeMillis();
        System.out.println("getSelectedCPaymentTerermForSC: Start " +
                           (System.currentTimeMillis() - startTime));
        if (selectedPaymentTermSC != null &&
            selectedPaymentTermSC.size() == 0) {

            OperationBinding ob =
                ADFUtils.getBindingContainer().getOperationBinding("getSelectedPaymentTermValuesForSC");
            ob.getParamsMap().put("usrId", usrId);
            ob.getParamsMap().put("salesChannel", salesChannel);
            if (ob != null) {
                selectedPaymentTermSC = (List<String>)ob.execute();
            }
            System.out.println("getSelectedCPaymentTerermForSC: closing " +
                               (System.currentTimeMillis() - startTime));
        }

        return selectedPaymentTermSC;
    }

    public void setSelectedCurrencyValsSC(List selectedCurrencyValsSC) {
        this.selectedCurrencyValsSC = selectedCurrencyValsSC;
    }

    public List getSelectedCurrencyValuesForSC() {
        long startTime = System.currentTimeMillis();
        System.out.println("getSelectedCurrencyValuesForSC: Start " +
                           (System.currentTimeMillis() - startTime));
        if (selectedCurrencyValsSC == null)
            selectedCurrencyValsSC = new ArrayList();
        if (selectedCurrencyValsSC != null &&
            selectedCurrencyValsSC.size() == 0) {

            OperationBinding ob =
                ADFUtils.getBindingContainer().getOperationBinding("getSelectedCurrencyValuesForSC");
            ob.getParamsMap().put("usrId", usrId);
            ob.getParamsMap().put("salesChannel", salesChannel);
            if (ob != null)
                selectedCurrencyValsSC = (List<String>)ob.execute();

            System.out.println("getSelectedCurrencyValuesForSC: closing " +
                               (System.currentTimeMillis() - startTime));
        }
        return selectedCurrencyValsSC;
    }


    public List getSelectedCurrencyValsSC() {
        return selectedCurrencyValsSC;
    }

    public void setSelectedIncoTermSC(List selectedIncoTermSC) {
        this.selectedIncoTermSC = selectedIncoTermSC;
    }

    public List getSelectedIncoTermValForSC() {
        long startTime = System.currentTimeMillis();
        _logger.info("getSelctedIncoTermValues:Start" +
                     (System.currentTimeMillis() - startTime));
        if (selectedIncoTermSC != null && selectedIncoTermSC.size() == 0) {
            OperationBinding ob =
                ADFUtils.getBindingContainer().getOperationBinding("getSelectedIncoTermValuesForSC");
            ob.getParamsMap().put("usrId", usrId);
            ob.getParamsMap().put("salesChannel", salesChannel);
            if (ob != null) {
                selectedIncoTermSC = (List<String>)ob.execute();
                System.out.println("getSelctedIncTermValues:End" +
                                   (System.currentTimeMillis() - startTime));
            }
        }
        return selectedIncoTermSC;
    }


    public List getSelectedSalesChannelSC() {
        long startTime = System.currentTimeMillis();
        System.out.println("getselectedSalesChannel:Start" +
                           (System.currentTimeMillis() - startTime));
        if (selectedSalesChannel != null && selectedSalesChannel.size() == 0) {
            OperationBinding ob =
                ADFUtils.getBindingContainer().getOperationBinding("getSelectedSalesChannelValues");
            ob.getParamsMap().put("usrId", usrId);
            if (ob != null) {
                selectedSalesChannel = (List<String>)ob.execute();
                System.out.println("getselectedSalesChannel:End" +
                                   (System.currentTimeMillis() - startTime));
            }
        }
        return selectedSalesChannel;
    }

    public List getSelectedIncoTermSC() {
        return selectedIncoTermSC;
    }

    public void setSelectedPaymentTermSC(List selectedPaymentTermSC) {
        this.selectedPaymentTermSC = selectedPaymentTermSC;
    }

    public List getSelectedPaymentTermValuesForSC() {

        return selectedPaymentTermSC;
    }

    public List getSelectedPaymentTermSC() {
        return selectedPaymentTermSC;
    }

    public void setSelectBusinessAgrementSC(List selectBusinessAgrementSC) {
        this.selectBusinessAgrementSC = selectBusinessAgrementSC;
    }

    public List getSelectBusinessAgrementSC() {
        return selectBusinessAgrementSC;
    }

    public void setPaymentTermValues(List<SelectItem> paymentTermValues) {
        this.paymentTermValues = paymentTermValues;
    }

    public List<SelectItem> getPaymentTermValues() {
        return paymentTermValues;
    }

    public void onCommitSalesChannel(ActionEvent ae) {       
        if (salesChannel != null && scOrgId != null) {
            String validateCurrency = null, validateIncoTerm =
                null, validatePaymentTerm = null, validateCustName =
                null, validateBA = null;
            OperationBinding clearDataOB =
                ADFUtils.getBindingContainer().getOperationBinding("clearUserPrefSCVO");
            clearDataOB.getParamsMap().put("usrId", usrId);
            if(clearDataOB!=null)
                clearDataOB.execute();
            boolean isBA = false,isCust = false,isIncoTerm = false,isCurrency = false,isPaymentTerm = false;

            if (selectedCustNameValues == null)
                selectedCustNameValues = new ArrayList();

                        if ((selectedCustNameValues != null &&
                             selectedCustNameValues.size() > 0) || custNameForSC != null) {
                            isCust = true;
            OperationBinding custNameob =
                ADFUtils.getBindingContainer().getOperationBinding("validateCustValuesForSC");
            custNameob.getParamsMap().put("usrId", usrId);
            custNameob.getParamsMap().put("custValues", selectedCustNameValues);
            //                if (custNameForSC != null)
            custNameob.getParamsMap().put("custDefaultVal", custNameForSC);
            custNameob.getParamsMap().put("salesChannel", salesChannel);
            custNameob.getParamsMap().put("orgId", scOrgId);
            if (custNameob != null) {
                validateCustName = (String)custNameob.execute();
                _logger.info("userPreference: OnCommitSalesChannel: Start: validateCustName status::" +
                             validateCustName);
            }
                        }

                        if ((selectedCurrencyValsSC != null &&
                             selectedCurrencyValsSC.size() > 0) || currencyForSC != null) {
                            isCurrency = true;
            OperationBinding currencyob =
                ADFUtils.getBindingContainer().getOperationBinding("validateCurrencyValuesForSC");
            currencyob.getParamsMap().put("usrId", usrId);
            currencyob.getParamsMap().put("currencyValues",
                                          selectedCurrencyValsSC);
            currencyob.getParamsMap().put("defaultVal", currencyForSC);
            currencyob.getParamsMap().put("salesChannel", salesChannel);
            if (currencyob != null) {
                validateCurrency = (String)currencyob.execute();
                _logger.info("userPreference: OnCommitSalesChannel: Start: validateCurrency status::" +
                             validateCurrency);
            }
                        }

                        if ((selectedIncoTermSC != null &&
                             selectedIncoTermSC.size() > 0) || incoTermForSC != null) {
                            isIncoTerm= true;
            OperationBinding incoTermob =
                ADFUtils.getBindingContainer().getOperationBinding("validateIncoTermValuesForSC");
            incoTermob.getParamsMap().put("usrId", usrId);
            incoTermob.getParamsMap().put("incoTermValues",
                                          selectedIncoTermSC);
            incoTermob.getParamsMap().put("defaultVal", incoTermForSC);
            incoTermob.getParamsMap().put("salesChannel", salesChannel);

            if (incoTermob != null) {
                validateIncoTerm = (String)incoTermob.execute();
                _logger.info("userPreference: OnCommitSalesChannel: Start: validateIncoTerm status::" +
                             validateIncoTerm);
            }
                        }

                        if ((selectedPaymentTermSC != null &&
                             selectedPaymentTermSC.size() > 0) ||
                            paymentTemForSC != null) {
                            isPaymentTerm= true;
            OperationBinding paymentTermob =
                ADFUtils.getBindingContainer().getOperationBinding("validatePaymentTermValuesforSC");
            paymentTermob.getParamsMap().put("usrId", usrId);
            paymentTermob.getParamsMap().put("PaymentTermValues",
                                             selectedPaymentTermSC);
            paymentTermob.getParamsMap().put("defaultVal", paymentTemForSC);
            paymentTermob.getParamsMap().put("salesChannel", salesChannel);
            if (paymentTermob != null) {
                validatePaymentTerm = (String)paymentTermob.execute();
                _logger.info("userPreference: OnCommitSalesChannel: Start: validatePaymentTerm status::" +
                             validatePaymentTerm);
            }
                        }

                        if ((selectedBusinessAgreementValues != null &&
                             selectedBusinessAgreementValues.size() > 0) ||
                            baForSC != null) {
                            isBA= true;
            OperationBinding baob =
                ADFUtils.getBindingContainer().getOperationBinding("validateBAForSC");
            baob.getParamsMap().put("usrId", usrId);
            baob.getParamsMap().put("baValues",
                                    selectedBusinessAgreementValues);
            baob.getParamsMap().put("defaultVal", baForSC);
            baob.getParamsMap().put("salesChannel", salesChannel);
            if (baob != null) {
                validateBA = (String)baob.execute();
                _logger.info("userPreference: OnCommitSalesChannel: Start: validateBA status::" +
                             validateBA);
            }
                        }
            OperationBinding commitOperations =
                ADFUtils.findOperation("commitEntities");
            if(isCust||isCurrency||isBA||isIncoTerm||isPaymentTerm){
            Boolean flag = (Boolean)commitOperations.execute();
            isBA = false;
            isCust = false;
            isIncoTerm = false;
            isCurrency = false;
            isPaymentTerm = false;
            if (flag)
                ADFUtils.addMessage(FacesMessage.SEVERITY_INFO,
                                    "Sales Channel Choices Saved Successfully");
            
            }
            else{
                    ADFUtils.addMessage(FacesMessage.SEVERITY_INFO,
                                        "Please Select respective values before save");
                }
        } else {
            ADFUtils.addMessage(FacesMessage.SEVERITY_INFO,
                                "Please Select Operating Unit and Sales Channel Value before Save");
        }
    }
    
    
    public static void setEL(String el, Object val) {
          FacesContext facesContext = FacesContext.getCurrentInstance();
          ELContext elContext = facesContext.getELContext();
          ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
          ValueExpression exp = expressionFactory.createValueExpression(elContext, el, Object.class);

          exp.setValue(elContext, val);
      }
    
    
    

    public void getGlobalChoiceDetails(DisclosureEvent disclosureEvent) {

        if (disclosureEvent.isExpanded()) {
            RichShowDetailHeader choiceShowDetail =
                getBindSalesChannelShowDetail();
            choiceShowDetail.setDisclosed(false);
        }
        //Updated On 18-11-2019
//        if (isGlobalChoiceEnable) {
//            if (orgId != null) {
                if (disclosureEvent.isExpanded()) {
                    RichShowDetailHeader choiceShowDetail =
                        getBindSalesChannelShowDetail();
                    choiceShowDetail.setDisclosed(false);
                    setEL("#{bindings.OperatingUnit.inputValue}",null);
                    bindSaesRep.setDisabled(true);
                    bindOrderType.setDisabled(true);
                    bindCustNum.setDisabled(true);
                    bindSelectManySalesRep.setDisabled(true);
                    bindSelectmanyOrderType.setDisabled(true);
                    bindSelectManyCustNumValues.setDisabled(true);
//                    bindGlobalOUItem.setValue(null);
//                    getBindGlobalOUItem().setValue(null);
//                    gsOU= null;
                    SalesRepValues = new ArrayList<SelectItem>();
                    selectedSalesRepValues = new ArrayList();
                    custNum =null;
                    custNumbers = new ArrayList<SelectItem>();
                    selectedCustNumValues = new ArrayList();
                    orderType = null;
                    orderTypeValues = new ArrayList<SelectItem>();
                    selectedOrderTypeValues= new ArrayList();
                    selectedCurrencyValues = new ArrayList();
                    selectedIncoTermValues = new ArrayList();
                    selectedCSRValues = new ArrayList();
                    selectedSalesChannel = new ArrayList();
                    
                    getDefaultValues();
                    getPrdNumRefConf();
                    getPrdNumTConf();
                    getRefPriceRefConf();
                    getRefPriceTConfig();
                    getNumFormatValue();
                    getSelectedIncoTermVals();
                    getSelectedCurrencyVals();
                    getSelectedCSRVals();
                    getSelectedSalesChannelSC();
                    
//                    getSelectedIncoTermVals();
//                    getDefaultValues();
//                    getSelectedCurrencyVals();
//                    getPrdNumRefConf();
//                    getPrdNumTConf();
//                    getRefPriceRefConf();
//                    getRefPriceTConfig();
//                    getNumFormatValue();
//                    getSelectedCSRVals();
//                    getSelectedSalesChannelSC();
                    isGlobalChoiceEnable = false;
                }
//            } else {
//                getBindGlobalChoiceShowDetail().setDisclosed(false);
//                FacesContext ctx = FacesContext.getCurrentInstance();
//                FacesMessage fm =
//                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Please Select Operating unit",
//                                     "");
//                ctx.addMessage(null, fm);
//            }
//        }
    }

    public void getSalesChannelDetails(DisclosureEvent disclosureEvent) {
        //Updated On 18-11-2019
//        if (getBindSalesChannel().getValue() != null && orgId != null) {
            if (disclosureEvent.isExpanded()) {
                RichShowDetailHeader choiceShowDetail =
                    getBindGlobalChoiceShowDetail();
                choiceShowDetail.setDisclosed(false);
            }
//            if (isSalesChnnelEnable) {
                if (disclosureEvent.isExpanded()) {
                    setEL("#{bindings.OperatingUnitForSC.inputValue}",null);
                    scOU = null;
                    salesChannel = null;
                    custNameForSC = null;
                    custNames = new ArrayList<SelectItem>();
                    currencyForSC = null;   
                    selectedCurrencyValsSC = new ArrayList();
                    baForSC = null;
                    selectedCustNameValues = new ArrayList();
                    custNames = new ArrayList<SelectItem>();
                    incoTermForSC = null;
                    selectedIncoTermSC = new ArrayList();
                    paymentTemForSC = null;
                    selectedPaymentTermSC = new ArrayList();
                    bindCustName.setDisabled(true);
                    bindSelectManyCustNames.setDisabled(true);
                    bindCurrency.setDisabled(true);
                    bindSelectManyCurrencies.setDisabled(true);
                    bindIncoTerms.setDisabled(true);
                    bindSelectManyIncoTerms.setDisabled(true);
                    bindPaymentTerms.setDisabled(true);
                    bindSelectManyPaymentTerms.setDisabled(true);
                    bindBA.setDisabled(true);
                    bindSelectManyBA.setDisabled(true);
                    
                    
                    
//                    getAllCustNums();
//                    getDefaultValuesForSC();
//                    getSelectedCurrencyValuesForSC();
//                    getSelectedIncoTermValForSC();
//                    getAllBAgreement();
//                    getSelectedBusinessAgreementForSC();
//                    getSelectedPaymentTermForSC();
//                    getSlectedCustNamesForSC();
                    isSalesChnnelEnable = false;
                }
//            }
//        } else {
//            getBindSalesChannelShowDetail().setDisclosed(false);
//            ADFUtils.addMessage(FacesMessage.SEVERITY_INFO,
//                                "Please select Sales Channel and Operating Unit");
//        }

    }

    public void setIsGlobalChoiceEnable(boolean isGlobalChoiceEnable) {
        this.isGlobalChoiceEnable = isGlobalChoiceEnable;
    }

    public boolean isIsGlobalChoiceEnable() {
        return isGlobalChoiceEnable;
    }

    public void setIsSalesChnnelEnable(boolean isSalesChnnelEnable) {
        this.isSalesChnnelEnable = isSalesChnnelEnable;
    }

    public boolean isIsSalesChnnelEnable() {
        return isSalesChnnelEnable;
    }


    public void setCustNameForSC(String custNameForSC) {
        this.custNameForSC = custNameForSC;
    }

    public String getCustNameForSC() {
        return custNameForSC;
    }

    public void setOrderTypeForSC(String orderTypeForSC) {
        this.orderTypeForSC = orderTypeForSC;
    }

    public String getOrderTypeForSC() {
        return orderTypeForSC;
    }

    public void setCurrencyForSC(String currencyForSC) {
        this.currencyForSC = currencyForSC;
    }

    public String getCurrencyForSC() {
        return currencyForSC;
    }

    public void setIncoTermForSC(String incoTermForSC) {
        this.incoTermForSC = incoTermForSC;
    }

    public String getIncoTermForSC() {
        return incoTermForSC;
    }

    public void setPaymentTemForSC(String paymentTemForSC) {
        this.paymentTemForSC = paymentTemForSC;
    }

    public String getPaymentTemForSC() {
        return paymentTemForSC;
    }

    public void setBaForSC(String baForSC) {
        this.baForSC = baForSC;
    }

    public String getBaForSC() {
        return baForSC;
    }


    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setSalesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
    }

    public String getSalesChannel() {
        return salesChannel;
    }

    public void setSalesChannelSC(List<SelectItem> salesChannelSC) {
        this.salesChannelSC = salesChannelSC;
    }

    public List<SelectItem> getSalesChannelSC() {
        return salesChannelSC;
    }

    public void setSelectedSalesChannel(List selectedSalesChannel) {
        this.selectedSalesChannel = selectedSalesChannel;
    }

    public List getSelectedSalesChannel() {
        return selectedSalesChannel;
    }

    public void setBindGlobalChoiceShowDetail(RichShowDetailHeader bindGlobalChoiceShowDetail) {
        this.bindGlobalChoiceShowDetail = bindGlobalChoiceShowDetail;
    }

    public RichShowDetailHeader getBindGlobalChoiceShowDetail() {
        return bindGlobalChoiceShowDetail;
    }

    public void setBindSalesChannelShowDetail(RichShowDetailHeader bindSalesChannelShowDetail) {
        this.bindSalesChannelShowDetail = bindSalesChannelShowDetail;
    }

    public RichShowDetailHeader getBindSalesChannelShowDetail() {
        return bindSalesChannelShowDetail;
    }

    public void setBindSalesChannel(RichSelectOneChoice bindSalesChannel) {
        this.bindSalesChannel = bindSalesChannel;
    }

    public RichSelectOneChoice getBindSalesChannel() {
        return bindSalesChannel;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrderTypeValuesForDefault(List<SelectItem> orderTypeValuesForDefault) {
        this.orderTypeValuesForDefault = orderTypeValuesForDefault;
    }

    public List<SelectItem> getOrderTypeValuesForDefault() {
        return orderTypeValuesForDefault;
    }

    public void setCurrencyValuesForDefault(List<SelectItem> currencyValuesForDefault) {
        this.currencyValuesForDefault = currencyValuesForDefault;
    }

    public List<SelectItem> getCurrencyValuesForDefault() {
        return currencyValuesForDefault;
    }

    public void setIncoTermValuesForDefault(List<SelectItem> incoTermValuesForDefault) {
        this.incoTermValuesForDefault = incoTermValuesForDefault;
    }

    public List<SelectItem> getIncoTermValuesForDefault() {
        return incoTermValuesForDefault;
    }

    public void setCustNamesForDefault(List<SelectItem> custNamesForDefault) {
        this.custNamesForDefault = custNamesForDefault;
    }

    public List<SelectItem> getCustNamesForDefault() {
        return custNamesForDefault;
    }

    public void setCustNumbersForDefault(List<SelectItem> custNumbersForDefault) {
        this.custNumbersForDefault = custNumbersForDefault;
    }

    public List<SelectItem> getCustNumbersForDefault() {
        return custNumbersForDefault;
    }

    public void setBusinessAgreemnForDefaultForDefault(List<SelectItem> businessAgreemnForDefaultForDefault) {
        this.businessAgreemnForDefaultForDefault =
                businessAgreemnForDefaultForDefault;
    }

    public List<SelectItem> getBusinessAgreemnForDefaultForDefault() {
        return businessAgreemnForDefaultForDefault;
    }

    public void setBusinessCenterCSRForDefault(List<SelectItem> businessCenterCSRForDefault) {
        this.businessCenterCSRForDefault = businessCenterCSRForDefault;
    }

    public List<SelectItem> getBusinessCenterCSRForDefault() {
        return businessCenterCSRForDefault;
    }

    public void setSalesRepValuesForDefault(List<SelectItem> SalesRepValuesForDefault) {
        this.SalesRepValuesForDefault = SalesRepValuesForDefault;
    }

    public List<SelectItem> getSalesRepValuesForDefault() {
        return SalesRepValuesForDefault;
    }

    public void setPaymentTermValuesForDefault(List<SelectItem> paymentTermValuesForDefault) {
        this.paymentTermValuesForDefault = paymentTermValuesForDefault;
    }

    public List<SelectItem> getPaymentTermValuesForDefault() {
        return paymentTermValuesForDefault;
    }

    public void setSalesChannelSCForDefault(List<SelectItem> salesChannelSCForDefault) {
        this.salesChannelSCForDefault = salesChannelSCForDefault;
    }

    public List<SelectItem> getSalesChannelSCForDefault() {
        return salesChannelSCForDefault;
    }


    public List<SelectItem> getAllNumFormatVals(){
            numberFormatList = new ArrayList<SelectItem>();
                numberFormatList.add(new SelectItem("10 000.00"));
                numberFormatList.add(new SelectItem("10 000,00"));
                numberFormatList.add(new SelectItem("10'000.00"));
                numberFormatList.add(new SelectItem("10'000,00"));
                numberFormatList.add(new SelectItem("10,000.00"));
                numberFormatList.add(new SelectItem("10.000,00"));
//            }
         return numberFormatList;
        }

    public void numFormatVCE(ValueChangeEvent vce) {
        System.out.println("value is:"+vce.getNewValue());
        numFormat = vce.getNewValue().toString();
//    String s = (String)ADFUtils.invokeEL("#{bindings.NumberFormat.selectedValue}");
        
        
//                    RichSelectOneChoice soc = (RichSelectOneChoice)vce.getComponent();
//            System.out.println("Index: " + soc.getValue().toString());
//            vce.getComponent().processUpdates(FacesContext.getCurrentInstance());    
//            Object numFormat =
//                ADFUtils.findIterator("QuotesVOIterator").getCurrentRow().getAttribute("NumberFormat");
//            System.out.println("Number valuee:" + numFormat);
        }

    public void setNumFormat(String numFormat) {
        this.numFormat = numFormat;
    }

    public String getNumFormat() {
        return numFormat;
    }

    public void setNumberFormatList(List<SelectItem> numberFormatList) {
        this.numberFormatList = numberFormatList;
    }

    public List<SelectItem> getNumberFormatList() {
        return numberFormatList;
    }

    public void setScOrgId(String scOrgId) {
        this.scOrgId = scOrgId;
    }

    public String getScOrgId() {
        return scOrgId;
    }

    public void setBindGlobalOUItem(RichSelectOneChoice bindGlobalOUItem) {
        this.bindGlobalOUItem = bindGlobalOUItem;
    }

    public RichSelectOneChoice getBindGlobalOUItem() {
        return bindGlobalOUItem;
    }

    public void setOuValues(List<SelectItem> ouValues) {
        this.ouValues = ouValues;
    }

    public void setGsOU(String gsOU) {
        this.gsOU = gsOU;
    }

    public String getGsOU() {
        return gsOU;
    }

    public void setScOU(String scOU) {
        this.scOU = scOU;
    }

    public String getScOU() {
        return scOU;
    }

    public void setBindSaesRep(RichSelectOneChoice bindSaesRep) {
        this.bindSaesRep = bindSaesRep;
    }

    public RichSelectOneChoice getBindSaesRep() {
        return bindSaesRep;
    }

    public void setBindOrderType(RichSelectOneChoice bindOrderType) {
        this.bindOrderType = bindOrderType;
    }

    public RichSelectOneChoice getBindOrderType() {
        return bindOrderType;
    }

    public void setBindCustNum(RichSelectOneChoice bindCustNum) {
        this.bindCustNum = bindCustNum;
    }

    public RichSelectOneChoice getBindCustNum() {
        return bindCustNum;
    }

    public void setBindSelectManySalesRep(RichSelectManyShuttle bindSelectManySalesRep) {
        this.bindSelectManySalesRep = bindSelectManySalesRep;
    }

    public RichSelectManyShuttle getBindSelectManySalesRep() {
        return bindSelectManySalesRep;
    }

    public void setBindSelectmanyOrderType(RichSelectManyShuttle bindSelectmanyOrderType) {
        this.bindSelectmanyOrderType = bindSelectmanyOrderType;
    }

    public RichSelectManyShuttle getBindSelectmanyOrderType() {
        return bindSelectmanyOrderType;
    }

    public void setBindSelectManyCustNumValues(RichSelectManyShuttle bindSelectManyCustNumValues) {
        this.bindSelectManyCustNumValues = bindSelectManyCustNumValues;
    }

    public RichSelectManyShuttle getBindSelectManyCustNumValues() {
        return bindSelectManyCustNumValues;
    }

    public void setBindCustName(RichSelectOneChoice bindCustName) {
        this.bindCustName = bindCustName;
    }

    public RichSelectOneChoice getBindCustName() {
        return bindCustName;
    }

    public void setBindCurrency(RichSelectOneChoice bindCurrency) {
        this.bindCurrency = bindCurrency;
    }

    public RichSelectOneChoice getBindCurrency() {
        return bindCurrency;
    }

    public void setBindBA(RichSelectOneChoice bindBA) {
        this.bindBA = bindBA;
    }

    public RichSelectOneChoice getBindBA() {
        return bindBA;
    }

    public void setBindIncoTerms(RichSelectOneChoice bindIncoTerms) {
        this.bindIncoTerms = bindIncoTerms;
    }

    public RichSelectOneChoice getBindIncoTerms() {
        return bindIncoTerms;
    }

    public void setBindPaymentTerms(RichSelectOneChoice bindPaymentTerms) {
        this.bindPaymentTerms = bindPaymentTerms;
    }

    public RichSelectOneChoice getBindPaymentTerms() {
        return bindPaymentTerms;
    }

    public void setBindSelectManyCustNames(RichSelectManyShuttle bindSelectManyCustNames) {
        this.bindSelectManyCustNames = bindSelectManyCustNames;
    }

    public RichSelectManyShuttle getBindSelectManyCustNames() {
        return bindSelectManyCustNames;
    }

    public void setBindSelectManyCurrencies(RichSelectManyShuttle bindSelectManyCurrencies) {
        this.bindSelectManyCurrencies = bindSelectManyCurrencies;
    }

    public RichSelectManyShuttle getBindSelectManyCurrencies() {
        return bindSelectManyCurrencies;
    }

    public void setBindSelectManyIncoTerms(RichSelectManyShuttle bindSelectManyIncoTerms) {
        this.bindSelectManyIncoTerms = bindSelectManyIncoTerms;
    }

    public RichSelectManyShuttle getBindSelectManyIncoTerms() {
        return bindSelectManyIncoTerms;
    }

    public void setBindSelectManyPaymentTerms(RichSelectManyShuttle bindSelectManyPaymentTerms) {
        this.bindSelectManyPaymentTerms = bindSelectManyPaymentTerms;
    }

    public RichSelectManyShuttle getBindSelectManyPaymentTerms() {
        return bindSelectManyPaymentTerms;
    }

    public void setBindSelectManyBA(RichSelectManyShuttle bindSelectManyBA) {
        this.bindSelectManyBA = bindSelectManyBA;
    }

    public RichSelectManyShuttle getBindSelectManyBA() {
        return bindSelectManyBA;
    }

    public void showOpUnitHelpTip(ActionEvent actionEvent) {
        String helpMsg = "Please select Operating Unit value to enable Sales Representative, Customer Number and Order Type choices";
        ADFUtils.showFacesMessage(helpMsg, FacesMessage.SEVERITY_INFO);
    }

    public void showShuttleHelpTip(ActionEvent actionEvent) {
        String helpMsg = "Values selected in below multi-selection choices will be preferred global choices for user in quote creation.";
        ADFUtils.showFacesMessage(helpMsg, FacesMessage.SEVERITY_INFO);
    }
}
