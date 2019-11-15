package xxatcust.oracle.apps.sudoku.bean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
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
//    private List<SelectItem> ouValues = new ArrayList<SelectItem>();
    private List<SelectItem> custNames = new ArrayList<SelectItem>();
    private List<SelectItem> custNumbers = new ArrayList<SelectItem>();
    private List<SelectItem> businessAgreemnt = new ArrayList<SelectItem>();
    private List<SelectItem> businessCenterCSR = new ArrayList<SelectItem>();
    private List<SelectItem> SalesRepValues = new ArrayList<SelectItem>();
    private List<SelectItem> paymentTermValues = new ArrayList<SelectItem>();
    private List<SelectItem> salesChannelSC = new ArrayList<SelectItem>();
    private List customerNums = new ArrayList();
    private List otValues = new ArrayList();
    private List selectedBusinessAgreementValues = new ArrayList();
    private List selectedOrderTypeValues = new ArrayList();
//    private List orglist = new ArrayList();
    private List salesRepIds = new ArrayList();
    private List selectedCurrencyValues = new ArrayList();
    private List selectedIncoTermValues = new ArrayList();
    private List selectedPaymentTermValues = new ArrayList();
//    private List selectedOUValues = new ArrayList();
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
    private RichSelectOneChoice bindNumFormat;
    private String userName =
        ((String)ADFUtils.getSessionScopeValue("UserName") == null ? "Admin" :
         (String)ADFUtils.getSessionScopeValue("UserName"));
    private boolean isOu = true;
    private boolean isGlobalChoiceVals = true;
    //    private RichSelectOneChoice bindCurrency;
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
    private String csrForSC;
    private String salesChannel;
    private boolean isGlobalChoiceEnable = true;
    private boolean isSalesChnnelEnable = true;
    private RichShowDetailHeader bindGlobalChoiceShowDetail;
    private RichShowDetailHeader bindSalesChannelShowDetail;
    private RichSelectOneChoice bindSalesChannel;
    private RichSelectOneChoice bindOU;
    private String orgId = null;
    public UserPreference() {

    }

    public void initUserPref() {
        OperationBinding ob =
            ADFUtils.getBindingContainer().getOperationBinding("initUserPref");
        if (ob != null)
            ob.execute();
        getAllCurrencyValues();
        getAllPaymentTermValues();
        getAllIncoTermValues();
        getAllCSRValues();
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
        if (orgId != null ) {
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
            RowSetIterator iter = vo.createRowSetIterator(null);
            while(iter.hasNext()){
                Row r = iter.next();
                        orderTypeValues.add(new SelectItem(r.getAttribute("Name"))); //TransactionTypeId
                        otValues.add(r.getAttribute("TransactionTypeId"));
                }
            if(iter!=null)
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
        if (orgId!=null) {
            String validateOUValues = null, validateCurrency =
                null, validateIncoTerm = null, validateOrderType =
                null, validateCustNum = null, validateSalesRep =
                null, validateCSR = null, validateStatic =
                null, validatePaymentTerm = null, validateSalesChannel = null;
            if ((selectedOrderTypeValues != null &&
                 selectedOrderTypeValues.size() > 0) || orderType != null) {
                OperationBinding orderTypeob =
                    ADFUtils.getBindingContainer().getOperationBinding("validateOrderTypeValues");
                orderTypeob.getParamsMap().put("usrId", usrId);
                orderTypeob.getParamsMap().put("orderTypeValues",
                                               selectedOrderTypeValues);

                if (orderType != null)
                    orderTypeob.getParamsMap().put("orderTypeDefaultval",
                                                   orderType);
                orderTypeob.getParamsMap().put("orgId", orgId);

                if (orderTypeob != null) {
                    validateOrderType = (String)orderTypeob.execute();
                }
            }
            if ((selectedCustNumValues != null &&
                 selectedCustNumValues.size() > 0) || custNum != null) {
                OperationBinding custNumob =
                    ADFUtils.getBindingContainer().getOperationBinding("validateCustValues");
                custNumob.getParamsMap().put("usrId", usrId);
                custNumob.getParamsMap().put("custValues",
                                             selectedCustNumValues);
                if (custNum != null)
                    custNumob.getParamsMap().put("custDefaultVal", custNum);
                custNumob.getParamsMap().put("orgId", orgId);
                if (custNumob != null)
                    validateCustNum = (String)custNumob.execute();
            }
            if ((selectedSalesRepValues != null &&
                 selectedSalesRepValues.size() > 0) || salesRep != null) {
                OperationBinding salesRepob =
                    ADFUtils.getBindingContainer().getOperationBinding("validateSalesRepValues");
                salesRepob.getParamsMap().put("usrId", usrId);
                salesRepob.getParamsMap().put("salesRepVals",
                                              selectedSalesRepValues);
                if (salesRep != null)
                    salesRepob.getParamsMap().put("defaultVal", salesRep);
                salesRepob.getParamsMap().put("orgId", orgId);
                if (salesRepob != null)
                    validateSalesRep = (String)salesRepob.execute();
            }
            //
            if ((selectedCSRValues != null && selectedCSRValues.size() > 0) ||
                csr != null) {
                OperationBinding csrob =
                    ADFUtils.getBindingContainer().getOperationBinding("validateCsrValues");
                csrob.getParamsMap().put("usrId", usrId);
                csrob.getParamsMap().put("csrValues", selectedCSRValues);
                if (csr != null)
                    csrob.getParamsMap().put("csr", csr);
                if (csrob != null)
                    validateCSR = (String)csrob.execute();
            }
            if ((selectedCurrencyValues != null &&
                 selectedCurrencyValues.size() > 0) || currency != null) {
                OperationBinding currencyob =
                    ADFUtils.getBindingContainer().getOperationBinding("validateCurrencyValues");
                currencyob.getParamsMap().put("usrId", usrId);
                currencyob.getParamsMap().put("currencyValues",
                                              selectedCurrencyValues);
                if (currency != null)
                    currencyob.getParamsMap().put("defaultName", currency);
                if (currencyob != null)
                    validateCurrency = (String)currencyob.execute();
            }

            if ((selectedIncoTermValues != null &&
                 selectedIncoTermValues.size() > 0) || incoTerm != null) {
                OperationBinding incoTermob =
                    ADFUtils.getBindingContainer().getOperationBinding("validateIncoTermValues");
                incoTermob.getParamsMap().put("usrId", usrId);
                incoTermob.getParamsMap().put("incoTermValues",
                                              selectedIncoTermValues);
                if (incoTerm != null)
                    incoTermob.getParamsMap().put("dafaultVal", incoTerm);
                if (incoTermob != null)
                    validateIncoTerm = (String)incoTermob.execute();
            }


            if (paymentTerm != null) {
                OperationBinding paymentTermsob =
                    ADFUtils.getBindingContainer().getOperationBinding("validatePaymentTermValues");
                paymentTermsob.getParamsMap().put("usrId", usrId);
                if (paymentTerm != null)
                    paymentTermsob.getParamsMap().put("defaultVal",
                                                      paymentTerm);
                if (paymentTermsob != null)
                    validatePaymentTerm = (String)paymentTermsob.execute();
            }

            if (selectedSalesChannel != null &&
                selectedSalesChannel.size() > 0) {
                OperationBinding salesChannelOB =
                    ADFUtils.getBindingContainer().getOperationBinding("validateSalesChannelForSC");
                salesChannelOB.getParamsMap().put("usrId", usrId);
                salesChannelOB.getParamsMap().put("salesChannel",
                                                  selectedSalesChannel);
                if (salesChannelOB != null) {
                    validateSalesChannel = (String)salesChannelOB.execute();
                    _logger.info("userPreference: OnCommitSalesChannel: Start: validateSalesChannelForSC status::" +
                                 validateSalesChannel);
                }
            }
            OperationBinding commitOperations =
                ADFUtils.findOperation("commitEntities");

            System.out.println(this.bindPrdNumRefConf.getValue());
            OperationBinding staticValob =
                ADFUtils.getBindingContainer().getOperationBinding("validatePrefStaticValues");
            staticValob.getParamsMap().put("usrId", usrId);
            if (staticValob != null) {
                staticValob.execute();
                validateStatic = "Y";
            }
            Boolean flag = (Boolean)commitOperations.execute();
            if (flag != null) {
                if (flag)
                    ADFUtils.addMessage(FacesMessage.SEVERITY_INFO,
                                        "Global Choice Data Saved Successfully");
            }
        }
        else{
                ADFUtils.addMessage(FacesMessage.SEVERITY_INFO,
                                    "Please Select Operating Unit before save");
            }
    }


    public void setCurrencyValues(List<SelectItem> currencyValues) {
        this.currencyValues = currencyValues;
    }


    public List<SelectItem> getAllCurrencyValues() {
        long startTime = System.currentTimeMillis();
        System.out.println("getAllCurrencyValues: Start " +
                           (System.currentTimeMillis() - startTime));
        if (currencyValues != null) {
            DCIteratorBinding iter =
                ADFUtils.findIterator("CurrencyVOIterator");
            RowSetIterator it =
                iter.getViewObject().createRowSetIterator(null);
            while (it.hasNext()) {
                Row row = it.next();
                currencyValues.add(new SelectItem(row.getAttribute("Name")));
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
            DCIteratorBinding iter =
                ADFUtils.findIterator("SalesChannelVOIterator");
            RowSetIterator it =
                iter.getViewObject().createRowSetIterator(null);
            while (it.hasNext()) {
                Row row = it.next();
                salesChannelSC.add(new SelectItem(row.getAttribute("Meaning")));
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
            DCIteratorBinding iter =
                ADFUtils.findIterator("IncoTermVOIterator");
            RowSetIterator it =
                iter.getViewObject().createRowSetIterator(null);
            while (it.hasNext()) {
                Row row = it.next();
                incoTermValues.add(new SelectItem(row.getAttribute("Description")));
            }
            it.closeRowSetIterator();
        }
        return incoTermValues;
    }

    public List<SelectItem> getAllPaymentTermValues() {
        if (paymentTermValues != null) {
            DCIteratorBinding iter =
                ADFUtils.findIterator("PaymentTermsVOIterator");
            RowSetIterator it =
                iter.getViewObject().createRowSetIterator(null);
            while (it.hasNext()) {
                Row row = it.next();
                paymentTermValues.add(new SelectItem(row.getAttribute("Description")));
            }
            it.closeRowSetIterator();
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
            Object obj[] = { usrId, colVal,orgId };
            Key key = new Key(obj);
            Row[] rows = vo.findByKey(key, 3);
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
            Object obj[] = { usrId, colVal,orgId };
            Key key = new Key(obj);
            Row[] rows = vo.findByKey(key, 3);
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

    public void getNumFormat() {
        String temp = null;
        String colVal = "Num_format";
        DCIteratorBinding iter =
            ADFUtils.findIterator("userPrefEntityVOIterator");
        ViewObject vo = iter.getViewObject();
        if (vo != null) {
            vo.clearCache();
            vo.setWhereClause(null);
            System.out.println("size of VO:" + vo.getEstimatedRowCount());
            Object obj[] = { usrId, colVal,orgId };
            Key key = new Key(obj);
            Row[] rows = vo.findByKey(key, 3);
            if (rows != null && rows.length > 0) {
                temp = (String)rows[0].getAttribute("ColumnVal");
            }
        }
        if (temp != null && !"".equalsIgnoreCase(temp)) {
            int i = Integer.parseInt(temp);
            bindNumFormat.setValue(i);
        } else {
            bindNumFormat.setValue(0);
        }
    }

    public void setBindNumFormat(RichSelectOneChoice bindNumFormat) {
        this.bindNumFormat = bindNumFormat;

    }

    public RichSelectOneChoice getBindNumFormat() {
        return bindNumFormat;
    }

//    public void setOuValues(List<SelectItem> ouValues) {
//        this.ouValues = ouValues;
//    }

//    public List<SelectItem> getAllOuValues() {
//        if (ouValues != null) {
//            DCIteratorBinding iter = ADFUtils.findIterator("OUVOIterator");
//            ViewObject vo =  iter.getViewObject();
//            if(vo!=null){
//                vo.clearCache();
//                vo.setWhereClause(null);
//                    RowSetIterator it =
//                        vo.createRowSetIterator(null);
//                    
//                    while (it.hasNext()) {
//                        Row row = it.next();
//                        ouValues.add(new SelectItem(row.getAttribute("OperatingUnit")));
//                        //                System.out.println("OU Value is:" +
//                        //                                   row.getAttribute("OperatingUnit"));
//                        //                orglist.add(row.getAttribute("OrgId"));
//                    }
//
//                    it.closeRowSetIterator();
//                }
//                          
//          
////            ADFUtils.setSessionScopeValue("ouGetters1", "N");
//        }
//        return ouValues;
//    }

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
            ob.getParamsMap().put("orgId", orgId);
            
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
                    Object orgValue = ADFUtils.findIterator("QuotesVOIterator").getCurrentRow().getAttribute("OperatingUnit");
                    System.out.println("OrgId idddds:"+orgValue);
                    ADFUtils.setSessionScopeValue("UserBasedOrgId", null);
                    getBindGlobalChoiceShowDetail().setDisclosed(false);
                    getBindSalesChannelShowDetail().setDisclosed(false);
                    isGlobalChoiceEnable = true;
                    isSalesChnnelEnable = true;
                    if(selectedCustNumValues!=null){
                            selectedCustNumValues.clear();
                        }
                    if(selectedOrderTypeValues!=null){
                            selectedCustNumValues.clear();
                        }
                    if(selectedSalesRepValues!=null){
                        selectedSalesRepValues.clear();
                        }
                    if(custNames!=null)
                        custNames.clear();
                    if(custNumbers!=null)
                        custNumbers.clear();
                    if(customerNums!=null)
                        customerNums.clear();
                    if(selectedCSRValues!=null)
                        selectedCSRValues.clear();
                    if(selectedCurrencyValues!=null)
                        selectedCurrencyValues.clear();
                    if(selectedIncoTermValues!=null)
                        selectedIncoTermValues.clear();
                    if(selectedPaymentTermValues!=null)
                        selectedPaymentTermValues.clear();
                    if(selectedSalesChannel==null)
                        selectedSalesChannel = new ArrayList();
                    if(selectedSalesChannel!=null)
                        selectedSalesChannel = new ArrayList();
                    if(selectedCustNameValues !=null)
                             selectedCustNameValues.clear();
                    
                        if (selectedCurrencyValsSC != null)
                            selectedCurrencyValsSC.clear();
                        if (selectedIncoTermSC != null)
                            selectedIncoTermSC.clear();
                        if (selectedCustNameValues != null)
                            selectedCustNameValues.clear();
                        if (selectedPaymentTermSC != null)
                            selectedPaymentTermSC.clear();
                        if (selectedBusinessAgreementValues != null)
                            selectedBusinessAgreementValues.clear();
                    getBindGlobalChoiceShowDetail().setDisclosed(false);
                    getBindSalesChannelShowDetail().setDisclosed(false);
                    System.out.println("Selected Value is:" + vce.getNewValue());
//                    System.out.println("OrgId:"+getBindOU().getValue());
//                    System.out.println("Orgid:"+vce.getNewValue());
                    orgId = orgValue.toString();
                    ADFUtils.setSessionScopeValue("UserBasedOrgId", orgId);                }
            }

    public void custNameVCE(ValueChangeEvent vce) {
        List<String> selectedValues = new ArrayList<String>();
        List selectedListFromUI = new ArrayList();
        if (vce.getNewValue() == null) {
            return;
        }
        //            Map<String, Object> pfs =
        //                AdfFacesContext.getCurrentInstance().getPageFlowScope();
        if(selectedCustNameValues ==null)
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
        
        DCIteratorBinding DCiter =
            ADFUtils.findIterator("CustomerNameVOIterator");
        ViewObjectImpl vo = (ViewObjectImpl)DCiter.getViewObject();
        if(vo!=null){
            vo.clearCache();
                vo.setNamedWhereClauseParam("p_orgId", null);
                vo.setWhereClause(null);
                vo.setNamedWhereClauseParam("p_orgId", new BigDecimal(orgId));
                vo.executeQuery();
                RowSetIterator iter = vo.createRowSetIterator(null);
                while(iter.hasNext()){
                    Row r = iter.next();
                        custNames.add(new SelectItem(r.getAttribute("Customername")));
                        custNumbers.add(new SelectItem(r.getAttribute("Accountnumber")));
                        customerNums.add(r.getAttribute("Accountnumber"));
                    }
                if(iter!=null){
                    iter.closeRowSetIterator();
                    }
            }
    return custNumbers;
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
            vce.getOldValue() != vce.getNewValue()) {
            if (selectedCurrencyValsSC != null)
                selectedCurrencyValsSC.clear();
            if (selectedIncoTermSC != null)
                selectedIncoTermSC.clear();
            if (selectedCustNameValues != null)
                selectedCustNameValues.clear();
            if (selectedPaymentTermSC != null)
                selectedPaymentTermSC.clear();
            if (selectedBusinessAgreementValues != null)
                selectedBusinessAgreementValues.clear();
            getBindSalesChannelShowDetail().setDisclosed(false);
            isSalesChnnelEnable = true;
            System.out.println("Selected Value is:" + vce.getNewValue());
            salesChannel = (String)vce.getNewValue();
            System.out.println(salesChannel);
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
                                       (System.currentTimeMillis() -
                                        startTime));
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
            DCIteratorBinding iter =
                ADFUtils.findIterator("CSRForGlobalChoiceVO1Iterator");
            RowSetIterator it =
                iter.getViewObject().createRowSetIterator(null);
            while (it.hasNext()) {
                Row row = it.next();
                businessCenterCSR.add(new SelectItem(row.getAttribute("CustomerName")));
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
           if(SalesRepValues!=null)
               SalesRepValues.clear();
            OperationBinding ob =
                ADFUtils.findOperation("getAllSalesRepValues");
            ob.getParamsMap().put("orgId", orgId);
            if (ob != null) {
                salesRepVal = (List)ob.execute();
            }
            if (salesRepVal != null ) {
                if(salesRepVal.size() > 0){
//                SalesRepValues.clear();
                for (int i = 0; i < salesRepVal.size(); i++) {
                    SalesRepValues.add(new SelectItem(salesRepVal.get(i)));
                }
            }
            }

            //            StringBuilder sb = new StringBuilder("(");
            //            sb.append(orglist.get(0));
            //            for (int i = 1; i < orglist.size(); i++) {
            //                sb.append(",").append(orglist.get(i));
            //                System.out.println("org Value is:" + orglist.get(i));
            //            }
            //            sb.append(")");
            //
            ////            if (SalesRepValues != null) {
            //                DCIteratorBinding iter =
            //                    ADFUtils.findIterator("SalesRepresentativeVOIterator");
            //                ViewObjectImpl vo = (ViewObjectImpl)iter.getViewObject();
            //                RowQualifier rq = new RowQualifier("Orgid in" + sb.toString());
            //                Row r[] = vo.getFilteredRows(rq);
            //                for (Row row : r) {
            //                    SalesRepValues.add(new SelectItem(row.getAttribute("ResourceName")));
            ////                    salesRepIds.add(row.getAttribute("ResourceId"));
            //                }
            //                                RowSetIterator it =
            //                                    iter.getViewObject().createRowSetIterator(null);
            //                if(SalesRepValues!=null)
            //                    SalesRepValues.clear();
            //                                ViewObjectImpl vo = (ViewObjectImpl)iter.getViewObject();
            //                                if(vo!=null){
            //                                    vo.setWhereClause("org_id in"+sb.toString());
            //                                    System.out.println("Query:"+vo.getQuery());
            //                                    vo.executeQuery();
            //                                        RowSetIterator it = vo.getRowSetIterator();
            //                                        while(it.hasNext()){
            //                                                Row row = it.next();
            //                                                SalesRepValues.add(new SelectItem(row.getAttribute("ResourceName")));
            //                                            }
            //                                        System.out.println("size"+SalesRepValues.size());
            //                                        it.closeRowSetIterator();
            //                                    }
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
            Object obj[] = { usrId, colType,orgId };
            Key key = new Key(obj);
            Row[] rows = vo.findByKey(key, 3);
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
        ob.getParamsMap().put("orgId",orgId);
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
                }
                else{
                        currencyForSC = null;
                    }
            }
            else
                currencyForSC = null;
            
            if (map.containsKey("customer")) {
                if (map.get("customer") != null) {
                    _logger.info("User Preferences: getDefaultValues: map values: orderType:" +
                                 map.get("customer"));
                        custNameForSC = (String)map.get("customer");
                }
                else
                custNameForSC = null;
            }
            else
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


    public void getDefaultValues() {
        _logger.info("User Preferences: getDefaultValues: Start:");
        Map map = new HashMap();
        OperationBinding ob = ADFUtils.findOperation("defaultVals");  //customerNums
        ob.getParamsMap().put("usrId", usrId);
        ob.getParamsMap().put("customerNums", customerNums);
         ob.getParamsMap().put("orgId",orgId);
        if (ob != null) {
            map = (Map)ob.execute();
        }
        if (map != null) {

            if (map.containsKey("currency")) {
                if (map.get("currency") != null) {
                    _logger.info("User Preferences: getDefaultValues: map values: currency:" +
                                 map.get("currency"));
                    if ((String)map.get("currency") != null)
                        currency = (String)map.get("currency");
                    
                }
                currency = null;
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
        if(vce.getOldValue()!= vce.getNewValue() &&vce.getNewValue()!=null ){
            if(selectedCustNameForSC!=null)
                selectedCustNameForSC.clear();
        selectedListFromUI = (List)vce.getNewValue();
        if (selectedListFromUI.size() > 0) {
            for (int i = 0; i < selectedListFromUI.size(); i++) {
                System.out.println("Selected values are from loop:" +
                                   selectedListFromUI.get(i) + "id value is:" +
                                   i);
                selectedValues.add(selectedListFromUI.get(i).toString());
            }
            selectedCustNameForSC = selectedValues;
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
        if (salesChannel!=null && orgId!=null) {
            String validateCurrency = null, validateIncoTerm =
                null, validatePaymentTerm = null, validateCustName =
                null, validateBA = null, validateSalesChannel = null;

            if (selectedCustNameForSC == null)
                selectedCustNameForSC = new ArrayList();

            if ((selectedCustNameForSC!=null &&selectedCustNameForSC.size()>0)||custNameForSC!=null) {
                OperationBinding custNameob =
                    ADFUtils.getBindingContainer().getOperationBinding("validateCustValuesForSC");
                custNameob.getParamsMap().put("usrId", usrId);
                custNameob.getParamsMap().put("custValues",
                                              selectedCustNameForSC);
                if (custNameForSC != null)
                    custNameob.getParamsMap().put("custDefaultVal",
                                                  custNameForSC);
                custNameob.getParamsMap().put("salesChannel", salesChannel);
                custNameob.getParamsMap().put("orgId", orgId);
                if (custNameob != null) {
                    validateCustName = (String)custNameob.execute();
                    _logger.info("userPreference: OnCommitSalesChannel: Start: validateCustName status::" +
                                 validateCustName);
                }
            }

            if ((selectedCurrencyValsSC!=null &&selectedCurrencyValsSC.size()>0)||currencyForSC!=null) {
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

            if ((selectedIncoTermSC!=null && selectedIncoTermSC.size()>0)|| incoTermForSC!=null) {
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

            if ((selectedPaymentTermSC!=null && selectedPaymentTermSC.size()>0)||paymentTemForSC!=null) {
                OperationBinding paymentTermob =
                    ADFUtils.getBindingContainer().getOperationBinding("validatePaymentTermValuesforSC");
                paymentTermob.getParamsMap().put("usrId", usrId);
                paymentTermob.getParamsMap().put("PaymentTermValues",
                                                 selectedPaymentTermSC);
                paymentTermob.getParamsMap().put("defaultVal",
                                                 paymentTemForSC);
                paymentTermob.getParamsMap().put("salesChannel", salesChannel);
                if (paymentTermob != null) {
                    validatePaymentTerm = (String)paymentTermob.execute();
                    _logger.info("userPreference: OnCommitSalesChannel: Start: validatePaymentTerm status::" +
                                 validatePaymentTerm);
                }
            }

            if ((selectedBusinessAgreementValues!=null &&selectedBusinessAgreementValues.size()>0)||baForSC!=null) {
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
            Boolean flag = (Boolean)commitOperations.execute();
            if (flag)
                ADFUtils.addMessage(FacesMessage.SEVERITY_INFO,
                                    "Sales Channel Data Saved Successfully");
        }
        else{
                ADFUtils.addMessage(FacesMessage.SEVERITY_INFO,
                                    "Please Select Operating Unit and Sales Channel Value before Save");
            }
    }

    public void getGlobalChoiceDetails(DisclosureEvent disclosureEvent) {
        if (disclosureEvent.isExpanded()) {
            RichShowDetailHeader choiceShowDetail =
                getBindSalesChannelShowDetail();
            choiceShowDetail.setDisclosed(false);
        }

        if (isGlobalChoiceEnable) {
            if (getBindOU().getValue()!=null) {
                if (disclosureEvent.isExpanded()) {
                    RichShowDetailHeader choiceShowDetail =
                        getBindSalesChannelShowDetail();
                    choiceShowDetail.setDisclosed(false);
                    getAllCustNums();
                    getSelectedIncoTermVals();
                    getDefaultValues();
                    getSelectedCurrencyVals();
                    getPrdNumRefConf();
                    getPrdNumTConf();
                    getRefPriceRefConf();
                    getRefPriceTConfig();
                    getNumFormat();
                    getAllOrderTypeValues();
                    getSelctedOTValues();
                    getSelectedCustNumVals();
                    getSelectedCSRVals();
                    getAllSalesRepValues();
                    getSelectedSalesRepVals();
                    getSelectedSalesChannelSC();
                    isGlobalChoiceEnable = false;
                }
            } else {
                getBindGlobalChoiceShowDetail().setDisclosed(false);
                FacesContext ctx = FacesContext.getCurrentInstance();
                FacesMessage fm =
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Selected Operating unit should not be empty!.",
                                     "");
                ctx.addMessage(null, fm);
                System.out.println("Selected Operating unit should not be empty");

            }
        }
    }

    public void getSalesChannelDetails(DisclosureEvent disclosureEvent) {
        if (getBindSalesChannel().getValue() != null &&
            (getBindOU().getValue() != null )) {
            if (disclosureEvent.isExpanded()) {
                RichShowDetailHeader choiceShowDetail =
                    getBindGlobalChoiceShowDetail();
                choiceShowDetail.setDisclosed(false);
            }
            if (isSalesChnnelEnable) {
                if (disclosureEvent.isExpanded()) {
                    getAllCustNums();
                    getDefaultValuesForSC();
                    getSelectedCurrencyValuesForSC();
                    getSelectedIncoTermValForSC();
                    getAllBAgreement();
                    getSelectedBusinessAgreementForSC();
                    getSelectedPaymentTermForSC();
                    getSlectedCustNamesForSC();
                    isSalesChnnelEnable = false;
                }
            }
        } else{
            getBindSalesChannelShowDetail().setDisclosed(false);
            ADFUtils.addMessage(FacesMessage.SEVERITY_INFO,
                                "Please Select Sales Channel");
        }

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

    public void setCsrForSC(String csrForSC) {
        this.csrForSC = csrForSC;
    }

    public String getCsrForSC() {
        return csrForSC;
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

    public void setBindOU(RichSelectOneChoice bindOU) {
        this.bindOU = bindOU;
    }

    public RichSelectOneChoice getBindOU() {
        return bindOU;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgId() {
        return orgId;
    }
}
