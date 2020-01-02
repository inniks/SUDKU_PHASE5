package xxatcust.oracle.apps.sudoku.model.module.client;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import java.util.Map;

import oracle.jbo.Row;
import oracle.jbo.client.remote.ApplicationModuleImpl;

import xxatcust.oracle.apps.sudoku.model.module.common.SudokuAM;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Mar 28 16:56:55 IST 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SudokuAMClient extends ApplicationModuleImpl implements SudokuAM {
    /**
     * This is the default constructor (do not remove).
     */
    public SudokuAMClient() {
    }


    


    public void initImportSource() {
        Object _ret =
            this.riInvokeExportedMethod(this,"initImportSource",null,null);
        return;
    }


    public void getQuoteCustmerAddress() {
    }


    public String clearQuoteFieldwithParams(String ou, String businessCSR,
                                            String incoTerm, String quoteDesc,
                                            String salesCh, String payTerm,
                                            String currency, String custNumber,
                                            String custName,
                                            String businessAggrement) {
        Object _ret =
            this.riInvokeExportedMethod(this,"clearQuoteFieldwithParams",new String [] {"java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String"},new Object[] {ou, businessCSR, incoTerm, quoteDesc, salesCh, payTerm, currency, custNumber, custName, businessAggrement});
        return (String)_ret;
    }


    public String callQuoteAPI() {
        Object _ret = this.riInvokeExportedMethod(this,"callQuoteAPI",null,null);
        return (String)_ret;
    }


   
    public List<String> getSelectedCustomerNameValues(int usrId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getSelectedCustomerNameValues",new String [] {"int"},new Object[] {new Integer(usrId)});
        return (List<String>)_ret;
    }

   
    public List<String> getSelectedPaymentTermValues(int usrId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getSelectedPaymentTermValues",new String [] {"int"},new Object[] {new Integer(usrId)});
        return (List<String>)_ret;
    }


    public Map getQuoteHdrOrgID(String pquoteNo) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getQuoteHdrOrgID",new String [] {"java.lang.String"},new Object[] {pquoteNo});
        return (Map)_ret;
    }


    public Map getSelectedOUValues(int usrId, List ouList) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getSelectedOUValues",new String [] {"int","java.util.List"},new Object[] {new Integer(usrId), ouList});
        return (Map)_ret;
    }


    public void clearQuoteFields() {
        Object _ret =
            this.riInvokeExportedMethod(this,"clearQuoteFields",null,null);
        return;
    }

    public void searchQuote() {
        Object _ret = this.riInvokeExportedMethod(this,"searchQuote",null,null);
        return;
    }

    public void initQuoteSearch() {
        Object _ret =
            this.riInvokeExportedMethod(this,"initQuoteSearch",null,null);
        return;
    }

    public void getUpdateQuote() {
        Object _ret = this.riInvokeExportedMethod(this,"getUpdateQuote",null,null);
        return;
    }

    public void initRuleSet() {
        Object _ret = this.riInvokeExportedMethod(this,"initRuleSet",null,null);
        return;
    }

    public String getQuoteNum(String headerid) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getQuoteNum",new String [] {"java.lang.String"},new Object[] {headerid});
        return (String)_ret;
    }

    public String callConfigLineToUpdateQuoteAPI(String quoteNum,
                                                 String quoteLineNum,
                                                 int itemQty,
                                                 String ConfighdrId,
                                                 String configRevNum,
                                                 int respId, int usrId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"callConfigLineToUpdateQuoteAPI",new String [] {"java.lang.String","java.lang.String","int","java.lang.String","java.lang.String","int","int"},new Object[] {quoteNum, quoteLineNum, new Integer(itemQty), ConfighdrId, configRevNum, new Integer(respId), new Integer(usrId)});
        return (String)_ret;
    }

    public String callConfigLineToAddQuoteAPI(String quoteNum,
                                              String itemNumber, int itemQty,
                                              String orgNum,
                                              String ConfighdrId,
                                              String configRevNum, int respId,
                                              int usrId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"callConfigLineToAddQuoteAPI",new String [] {"java.lang.String","java.lang.String","int","java.lang.String","java.lang.String","java.lang.String","int","int"},new Object[] {quoteNum, itemNumber, new Integer(itemQty), orgNum, ConfighdrId, configRevNum, new Integer(respId), new Integer(usrId)});
        return (String)_ret;
    }

    public void getQuoteCustmerAddress(Row curRow) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getQuoteCustmerAddress",new String [] {"oracle.jbo.Row"},new Object[] {curRow});
        return;
    }

    public String clearQuoteFieldwithParams(String ou, String orderType,
                                            String priceList,
                                            String businessCSR,
                                            String incoTerm, String quoteDesc,
                                            String salesCh, String payTerm,
                                            String currency, String custNumber,
                                            String custName,
                                            String businessAggrement,
                                            String custEmail, String custPhone,
                                            String ccontact) {
        Object _ret =
            this.riInvokeExportedMethod(this,"clearQuoteFieldwithParams",new String [] {"java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String"},new Object[] {ou, orderType, priceList, businessCSR, incoTerm, quoteDesc, salesCh, payTerm, currency, custNumber, custName, businessAggrement, custEmail, custPhone, ccontact});
        return (String)_ret;
    }

    public String getFNDMsges(String msgName) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getFNDMsges",new String [] {"java.lang.String"},new Object[] {msgName});
        return (String)_ret;
    }

    public String callQuoteAPI(int respid, int usrId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"callQuoteAPI",new String [] {"int","int"},new Object[] {new Integer(respid), new Integer(usrId)});
        return (String)_ret;
    }

    public String callWarrentyAPI(String quoteNum, String prodName, int respId,
                                  int usrId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"callWarrentyAPI",new String [] {"java.lang.String","java.lang.String","int","int"},new Object[] {quoteNum, prodName, new Integer(respId), new Integer(usrId)});
        return (String)_ret;
    }

    public String callDuplicateQuoteAPI(String quoteFromSesion, int respId,
                                        int usrId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"callDuplicateQuoteAPI",new String [] {"java.lang.String","int","int"},new Object[] {quoteFromSesion, new Integer(respId), new Integer(usrId)});
        return (String)_ret;
    }

    public String callUpdateDiscountAPI(String quoteNum, String discount,
                                        int respId, int usrId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"callUpdateDiscountAPI",new String [] {"java.lang.String","java.lang.String","int","int"},new Object[] {quoteNum, discount, new Integer(respId), new Integer(usrId)});
        return (String)_ret;
    }

    public void initRuleSetForRef() {
        Object _ret =
            this.riInvokeExportedMethod(this,"initRuleSetForRef",null,null);
        return;
    }

    public void initUserPreference() {
        Object _ret =
            this.riInvokeExportedMethod(this,"initUserPreference",null,null);
        return;
    }

    public void filterOrderTypeRecords(int usrId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"filterOrderTypeRecords",new String [] {"int"},new Object[] {new Integer(usrId)});
        return;
    }

    public void getViewAccessors(int usrId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getViewAccessors",new String [] {"int"},new Object[] {new Integer(usrId)});
        return;
    }

    public String validateOUValues(int usrId, List ouValues) {
        Object _ret =
            this.riInvokeExportedMethod(this,"validateOUValues",new String [] {"int","java.util.List"},new Object[] {new Integer(usrId), ouValues});
        return (String)_ret;
    }

    public void initUserPref() {
        Object _ret = this.riInvokeExportedMethod(this,"initUserPref",null,null);
        return;
    }

    public List getSelectedCurrencyValues(int usrId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getSelectedCurrencyValues",new String [] {"int"},new Object[] {new Integer(usrId)});
        return (List)_ret;
    }

    public List getSelectedCSRValues(int usrId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getSelectedCSRValues",new String [] {"int"},new Object[] {new Integer(usrId)});
        return (List)_ret;
    }

    public List getSelectedIncoTermValues(int usrId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getSelectedIncoTermValues",new String [] {"int"},new Object[] {new Integer(usrId)});
        return (List)_ret;
    }

    public String validateCsrValues(int usrId, List csrValues, String csr) {
        Object _ret =
            this.riInvokeExportedMethod(this,"validateCsrValues",new String [] {"int","java.util.List","java.lang.String"},new Object[] {new Integer(usrId), csrValues, csr});
        return (String)_ret;
    }

    public String validateCurrencyValues(int usrId, List currencyValues,
                                         String defaultName) {
        Object _ret =
            this.riInvokeExportedMethod(this,"validateCurrencyValues",new String [] {"int","java.util.List","java.lang.String"},new Object[] {new Integer(usrId), currencyValues, defaultName});
        return (String)_ret;
    }

    public String validateIncoTermValues(int usrId, List incoTermValues,
                                         String dafaultVal) {
        Object _ret =
            this.riInvokeExportedMethod(this,"validateIncoTermValues",new String [] {"int","java.util.List","java.lang.String"},new Object[] {new Integer(usrId), incoTermValues, dafaultVal});
        return (String)_ret;
    }

    public String validatePaymentTermValues(int usrId, String defaultVal) {
        Object _ret =
            this.riInvokeExportedMethod(this,"validatePaymentTermValues",new String [] {"int","java.lang.String"},new Object[] {new Integer(usrId), defaultVal});
        return (String)_ret;
    }

    public List getSelectedBusinessAgreementForSC(int usrId,
                                                  String salesChannel) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getSelectedBusinessAgreementForSC",new String [] {"int","java.lang.String"},new Object[] {new Integer(usrId), salesChannel});
        return (List)_ret;
    }

    public List getSelectedCurrencyValuesForSC(int usrId,
                                               String salesChannel) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getSelectedCurrencyValuesForSC",new String [] {"int","java.lang.String"},new Object[] {new Integer(usrId), salesChannel});
        return (List)_ret;
    }

    public List getSelectedIncoTermValuesForSC(int usrId,
                                               String salesChannel) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getSelectedIncoTermValuesForSC",new String [] {"int","java.lang.String"},new Object[] {new Integer(usrId), salesChannel});
        return (List)_ret;
    }

    public List getSelectedPaymentTermValuesForSC(int usrId,
                                                  String salesChannel) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getSelectedPaymentTermValuesForSC",new String [] {"int","java.lang.String"},new Object[] {new Integer(usrId), salesChannel});
        return (List)_ret;
    }

    public String validateBAForSC(int usrId, List baValues, String defaultVal,
                                  String salesChannel) {
        Object _ret =
            this.riInvokeExportedMethod(this,"validateBAForSC",new String [] {"int","java.util.List","java.lang.String","java.lang.String"},new Object[] {new Integer(usrId), baValues, defaultVal, salesChannel});
        return (String)_ret;
    }

    public String validateCurrencyValuesForSC(int usrId, List currencyValues,
                                              String defaultVal,
                                              String salesChannel) {
        Object _ret =
            this.riInvokeExportedMethod(this,"validateCurrencyValuesForSC",new String [] {"int","java.util.List","java.lang.String","java.lang.String"},new Object[] {new Integer(usrId), currencyValues, defaultVal, salesChannel});
        return (String)_ret;
    }

    public String validatePaymentTermValuesforSC(int usrId,
                                                 List PaymentTermValues,
                                                 String defaultVal,
                                                 String salesChannel) {
        Object _ret =
            this.riInvokeExportedMethod(this,"validatePaymentTermValuesforSC",new String [] {"int","java.util.List","java.lang.String","java.lang.String"},new Object[] {new Integer(usrId), PaymentTermValues, defaultVal, salesChannel});
        return (String)_ret;
    }

    public String validateIncoTermValuesForSC(int usrId, List incoTermValues,
                                              String defaultVal,
                                              String salesChannel) {
        Object _ret =
            this.riInvokeExportedMethod(this,"validateIncoTermValuesForSC",new String [] {"int","java.util.List","java.lang.String","java.lang.String"},new Object[] {new Integer(usrId), incoTermValues, defaultVal, salesChannel});
        return (String)_ret;
    }

    public String validateSalesChannelForSC(int usrId, List salesChannel) {
        Object _ret =
            this.riInvokeExportedMethod(this,"validateSalesChannelForSC",new String [] {"int","java.util.List"},new Object[] {new Integer(usrId), salesChannel});
        return (String)_ret;
    }

    public List getSelectedSalesChannelValues(int usrId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getSelectedSalesChannelValues",new String [] {"int"},new Object[] {new Integer(usrId)});
        return (List)_ret;
    }

    public void initQuoteBasedOnUserPref(boolean isUserDefault) {
        Object _ret =
            this.riInvokeExportedMethod(this,"initQuoteBasedOnUserPref",new String [] {"boolean"},new Object[] {new Boolean(isUserDefault)});
        return;
    }

    public boolean commitEntities() {
        Object _ret = this.riInvokeExportedMethod(this,"commitEntities",null,null);
        return ((Boolean)_ret).booleanValue();
    }

    public void getSalesChannelBasedUserPref(String salesChannel) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getSalesChannelBasedUserPref",new String [] {"java.lang.String"},new Object[] {salesChannel});
        return;
    }

    public boolean getSalesChannelBasedUserPrefForUpdateQuote(String salesChannel) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getSalesChannelBasedUserPrefForUpdateQuote",new String [] {"java.lang.String"},new Object[] {salesChannel});
        return ((Boolean)_ret).booleanValue();
    }

    public String getPath() {
        Object _ret = this.riInvokeExportedMethod(this,"getPath",null,null);
        return (String)_ret;
    }

    public int callDUTReport(String confighid, String configrevno,
                             String orderhid, String quoteno, String ponum,
                             int respId, int usrId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"callDUTReport",new String [] {"java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String","int","int"},new Object[] {confighid, configrevno, orderhid, quoteno, ponum, new Integer(respId), new Integer(usrId)});
        return ((Integer)_ret).intValue();
    }

    public String callMOFReport(String confighid, String configrevno,
                                String orderhid, String quoteno,
                                String ponum) {
        Object _ret =
            this.riInvokeExportedMethod(this,"callMOFReport",new String [] {"java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String"},new Object[] {confighid, configrevno, orderhid, quoteno, ponum});
        return (String)_ret;
    }

    public String callCFDReport(String quoteNum, int respId, int usrId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"callCFDReport",new String [] {"java.lang.String","int","int"},new Object[] {quoteNum, new Integer(respId), new Integer(usrId)});
        return (String)_ret;
    }

    public Map defaultValsForSC(int usrId, String salesChannel,
                                List customerNums, String orgId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"defaultValsForSC",new String [] {"int","java.lang.String","java.util.List","java.lang.String"},new Object[] {new Integer(usrId), salesChannel, customerNums, orgId});
        return (Map)_ret;
    }

    public List getAllCustNumValues(BigDecimal orgId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getAllCustNumValues",new String [] {"java.math.BigDecimal"},new Object[] {orgId});
        return (List)_ret;
    }

    public List getAllSalesRepValues(String orgId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getAllSalesRepValues",new String [] {"java.lang.String"},new Object[] {orgId});
        return (List)_ret;
    }

    public List getSelectedCustNumValues(int usrId, String orgId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getSelectedCustNumValues",new String [] {"int","java.lang.String"},new Object[] {new Integer(usrId), orgId});
        return (List)_ret;
    }

    public List getSelectedCustomerNameValues(int usrId, String salesChannel,
                                              String orgId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getSelectedCustomerNameValues",new String [] {"int","java.lang.String","java.lang.String"},new Object[] {new Integer(usrId), salesChannel, orgId});
        return (List)_ret;
    }

    public List getSelectedOrderTypeValues(int usrId, String orgId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getSelectedOrderTypeValues",new String [] {"int","java.lang.String"},new Object[] {new Integer(usrId), orgId});
        return (List)_ret;
    }

    public List getSelectedSalesRepValues(int usrId, String orgId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getSelectedSalesRepValues",new String [] {"int","java.lang.String"},new Object[] {new Integer(usrId), orgId});
        return (List)_ret;
    }

    public String validateCustValues(int usrId, List custValues,
                                     String custDefaultVal, String orgId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"validateCustValues",new String [] {"int","java.util.List","java.lang.String","java.lang.String"},new Object[] {new Integer(usrId), custValues, custDefaultVal, orgId});
        return (String)_ret;
    }

    public String validateCustValuesForSC(int usrId, List custValues,
                                          String custDefaultVal,
                                          String salesChannel, String orgId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"validateCustValuesForSC",new String [] {"int","java.util.List","java.lang.String","java.lang.String","java.lang.String"},new Object[] {new Integer(usrId), custValues, custDefaultVal, salesChannel, orgId});
        return (String)_ret;
    }

    public String validateOrderTypeValues(int usrId, List orderTypeValues,
                                          String orderTypeDefaultval,
                                          String orgId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"validateOrderTypeValues",new String [] {"int","java.util.List","java.lang.String","java.lang.String"},new Object[] {new Integer(usrId), orderTypeValues, orderTypeDefaultval, orgId});
        return (String)_ret;
    }

    public String validateSalesRepValues(int usrId, List salesRepVals,
                                         String defaultVal, String orgId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"validateSalesRepValues",new String [] {"int","java.util.List","java.lang.String","java.lang.String"},new Object[] {new Integer(usrId), salesRepVals, defaultVal, orgId});
        return (String)_ret;
    }

    public String callUpdateQuoteAPI(int respid, int usrId,
                                     boolean isCustEditable) {
        Object _ret =
            this.riInvokeExportedMethod(this,"callUpdateQuoteAPI",new String [] {"int","int","boolean"},new Object[] {new Integer(respid), new Integer(usrId), new Boolean(isCustEditable)});
        return (String)_ret;
    }

    public void getQuoteCustmerAddressOnCustNumForUpdateQuoteChange(String custNum) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getQuoteCustmerAddressOnCustNumForUpdateQuoteChange",new String [] {"java.lang.String"},new Object[] {custNum});
        return;
    }

    public void getQuoteCustmerAddressOnCustNumChange(String custNum) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getQuoteCustmerAddressOnCustNumChange",new String [] {"java.lang.String"},new Object[] {custNum});
        return;
    }

    public void getQuoteCustmerAddressOnCustNameForUpdateQuoteChange(String custName) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getQuoteCustmerAddressOnCustNameForUpdateQuoteChange",new String [] {"java.lang.String"},new Object[] {custName});
        return;
    }

    public void getQuoteCustmerAddressOnCustNameChange(String custName) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getQuoteCustmerAddressOnCustNameChange",new String [] {"java.lang.String"},new Object[] {custName});
        return;
    }

    public void clearUserPrefVO(int usrId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"clearUserPrefVO",new String [] {"int"},new Object[] {new Integer(usrId)});
        return;
    }

    public Map defaultValsOUDependencies(int usrId, String orgId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"defaultValsOUDependencies",new String [] {"int","java.lang.String"},new Object[] {new Integer(usrId), orgId});
        return (Map)_ret;
    }

    public Map defaultVals(int usrId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"defaultVals",new String [] {"int"},new Object[] {new Integer(usrId)});
        return (Map)_ret;
    }

    public String getCurrencyFormat(String userId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getCurrencyFormat",new String [] {"java.lang.String"},new Object[] {userId});
        return (String)_ret;
    }

    public void validatePrefStaticValues(int usrId, String numberFormat) {
        Object _ret =
            this.riInvokeExportedMethod(this,"validatePrefStaticValues",new String [] {"int","java.lang.String"},new Object[] {new Integer(usrId), numberFormat});
        return;
    }

    public void clearUserPrefSCVO(int usrId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"clearUserPrefSCVO",new String [] {"int"},new Object[] {new Integer(usrId)});
        return;
    }

    public HashMap getPriceProductChoices() {
        Object _ret =
            this.riInvokeExportedMethod(this,"getPriceProductChoices",null,null);
        return (HashMap)_ret;
    }

    public String deleteProductLine(String lineId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"deleteProductLine",new String [] {"java.lang.String"},new Object[] {lineId});
        return (String)_ret;
    }
}
