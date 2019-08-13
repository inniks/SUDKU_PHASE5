package xxatcust.oracle.apps.sudoku.model.module.client;

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

    public void defaultSalesRepOnCreateQuote() {
        Object _ret =
            this.riInvokeExportedMethod(this,"defaultSalesRepOnCreateQuote",null,null);
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

    public String callUpdateQuoteAPI(int respid, int usrId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"callUpdateQuoteAPI",new String [] {"int","int"},new Object[] {new Integer(respid), new Integer(usrId)});
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
}
