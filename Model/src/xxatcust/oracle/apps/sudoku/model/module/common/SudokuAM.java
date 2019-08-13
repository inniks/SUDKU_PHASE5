package xxatcust.oracle.apps.sudoku.model.module.common;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Mar 13 21:53:45 IST 2019
// ---------------------------------------------------------------------
public interface SudokuAM extends ApplicationModule {
   

    void clearQuoteFields();


    void searchQuote();

    void initQuoteSearch();


    void getUpdateQuote();


    void initRuleSet();


    String getQuoteNum(String headerid);


    String callConfigLineToUpdateQuoteAPI(String quoteNum, String quoteLineNum,
                                          int itemQty, String ConfighdrId,
                                          String configRevNum, int respId,
                                          int usrId);

    String callConfigLineToAddQuoteAPI(String quoteNum, String itemNumber,
                                       int itemQty, String orgNum,
                                       String ConfighdrId, String configRevNum,
                                       int respId, int usrId);


    void getQuoteCustmerAddress(Row curRow);

    void defaultSalesRepOnCreateQuote();


    String clearQuoteFieldwithParams(String ou, String orderType,
                                     String priceList, String businessCSR,
                                     String incoTerm, String quoteDesc,
                                     String salesCh, String payTerm,
                                     String currency, String custNumber,
                                     String custName, String businessAggrement,
                                     String custEmail, String custPhone,
                                     String ccontact);

    String getFNDMsges(String msgName);

    String callQuoteAPI(int respid, int usrId);

    String callWarrentyAPI(String quoteNum, String prodName, int respId,
                           int usrId);

    String callUpdateQuoteAPI(int respid, int usrId);

    String callDuplicateQuoteAPI(String quoteFromSesion, int respId,
                                 int usrId);

    String callUpdateDiscountAPI(String quoteNum, String discount, int respId,
                                 int usrId);

}
