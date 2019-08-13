package xxatcust.oracle.apps.sudoku.bean;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.output.RichOutputFormatted;
import oracle.adf.view.rich.event.ReturnPopupEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import xxatcust.oracle.apps.sudoku.util.ADFUtils;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.SessionDetails;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.V93kQuote;


public class QuotingBean {
    private RichInputListOfValues bindOrderType;
    private RichInputListOfValues bindCustomerName;
    private RichInputListOfValues bindCustNumber;
    private boolean isCustEnable = false;
    private boolean businessAgrement = false;
    private String currency1 = "USD";
    private RichOutputFormatted bindErrForCreate;
    private RichPopup bindCreateQuotePopup;
    private RichInputText bindDiscount;

    public QuotingBean() {
        super();
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }


    public void saveQuoteDetails(ActionEvent actionEvent) {
        String msg = "";
        FacesContext fc = FacesContext.getCurrentInstance();
        int respid =
            Integer.parseInt((String)ADFUtils.getSessionScopeValue("RespId") ==
                             null ? "51156" :
                             (String)ADFUtils.getSessionScopeValue("RespId"));
        int usrId =
            Integer.parseInt((String)ADFUtils.getSessionScopeValue("UserId") ==
                             null ? "0" :
                             (String)ADFUtils.getSessionScopeValue("UserId"));
        OperationBinding operationBinding =
            getBindings().getOperationBinding("callQuoteAPI");
        operationBinding.getParamsMap().put("respid", respid);
        operationBinding.getParamsMap().put("usrId", usrId);
        if (operationBinding.getErrors().size() == 0 &&
            operationBinding != null)
            msg = (String)operationBinding.execute();
        FacesMessage message = new FacesMessage(msg);
        if (msg.contains("<html><body>")) {
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(null, message);
        } else if (msg.contains("S-")) {

            String[] resultMsg = msg.split("-", 2);
            msg = resultMsg[1];
            message = new FacesMessage(msg);
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            fc.addMessage(null, message);
            V93kQuote v93k =
                (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
            if (v93k != null) {
                if (v93k.getSessionDetails() != null) {
                    String[] arrOfStr = msg.split(":", 2);

                    if (arrOfStr[1] != null)
                        v93k.getSessionDetails().setTargetQuoteNumber(arrOfStr[1].toString());
                    ADFUtils.setSessionScopeValue("parentObject", v93k);
                    ADFUtils.setSessionScopeValue("targetQuoteNumber",
                                                  arrOfStr[1]);

                    if (getBindDiscount().getValue() != null) {
                        if (v93k.getQheaderObject() != null) {
                            if (v93k.getQheaderObject().getDealObject() !=
                                null) {
                                v93k.getQheaderObject().getDealObject().setDdiscount(getBindDiscount().getValue().toString());
                            }
                        }
                    }

                }
            } else {
                V93kQuote v93kNew = new V93kQuote();
                SessionDetails sesDet = new SessionDetails();
                String[] arrOfStr = msg.split(":", 2);

                if (arrOfStr[1] != null)
                    sesDet.setTargetQuoteNumber(arrOfStr[1].toString());
                ADFUtils.setSessionScopeValue("targetQuoteNumber",
                                              arrOfStr[1]);
                v93kNew.setSessionDetails(sesDet);
                ADFUtils.setSessionScopeValue("parentObject", v93kNew);
            }
        } else {
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(null, message);
        }
    }

    public String initQuotePage() {
        isCustEnable = false;
        businessAgrement = false;
        String msg = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        V93kQuote v93k =
            (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
        if (v93k != null) {
            if (v93k.getSessionDetails() != null) {
                if (v93k.getSessionDetails().isCreateNewQuote() ||
                    v93k.getSessionDetails().isDuplicateQuote()) {
                    OperationBinding ob =
                        getBindings().getOperationBinding("clearQuoteFieldwithParams");

                    //This is for Price ListValue
                    if ((String)v93k.getSessionDetails().getPriceListID() !=
                        null &&
                        !"".equalsIgnoreCase((String)v93k.getSessionDetails().getPriceListID())) {
                        System.out.println("priceList id::" +
                                           (String)v93k.getSessionDetails().getPriceListID());
                        ob.getParamsMap().put("priceList",
                                              v93k.getSessionDetails().getPriceListID().toString());
                    } else {
                        ob.getParamsMap().put("priceList", null);
                    }

                    if (v93k.getQheaderObject() != null) {
                        if (v93k.getQheaderObject().getSalesteamObject() !=
                            null) {

                            if ((String)v93k.getQheaderObject().getSalesteamObject().getCsrrespo() !=
                                null &&
                                !"".equalsIgnoreCase((String)v93k.getQheaderObject().getSalesteamObject().getCsrrespo())) {
                                System.out.println("CSR name:" +
                                                   v93k.getQheaderObject().getSalesteamObject().getCsrrespo().toString());
                                ob.getParamsMap().put("businessCSR",
                                                      v93k.getQheaderObject().getSalesteamObject().getCsrrespo().toString());
                            } else
                                ob.getParamsMap().put("businessCSR", null);


                            if (v93k.getQheaderObject().getSalesteamObject() !=
                                null &&
                                v93k.getQheaderObject().getSalesteamObject().getOu() !=
                                null &&
                                v93k.getQheaderObject().getSalesteamObject().getOu().toString() !=
                                null &&
                                !"".equalsIgnoreCase((String)v93k.getQheaderObject().getSalesteamObject().getOu())) {
                                System.out.println("Operating Unit:" +
                                                   v93k.getQheaderObject().getSalesteamObject().getOu().toString());
                                ob.getParamsMap().put("ou",
                                                      v93k.getQheaderObject().getSalesteamObject().getOu().toString());
                                if (v93k.getQheaderObject().getDealObject() !=
                                    null) {
                                    ob.getParamsMap().put("orderType",
                                                          v93k.getQheaderObject().getDealObject().getDordertype());
                                }

                                isCustEnable = true;
                                if (v93k.getQheaderObject().getCustomerObject() !=
                                    null) {
                                    if (v93k.getQheaderObject().getCustomerObject().getCnumber() !=
                                        null &&
                                        !"".equalsIgnoreCase((String)v93k.getQheaderObject().getCustomerObject().getCnumber()))
                                        ob.getParamsMap().put("custNumber",
                                                              v93k.getQheaderObject().getCustomerObject().getCnumber().toString());
                                    else
                                        ob.getParamsMap().put("custNumber",
                                                              null);
                                    if (v93k.getQheaderObject().getCustomerObject().getCname() !=
                                        null &&
                                        !"".equalsIgnoreCase((String)v93k.getQheaderObject().getCustomerObject().getCname()))
                                        ob.getParamsMap().put("custName",
                                                              v93k.getQheaderObject().getCustomerObject().getCname().toString());
                                    else
                                        ob.getParamsMap().put("custName",
                                                              null);

                                    if (v93k.getQheaderObject().getCustomerObject().getCnumber() !=
                                        null ||
                                        v93k.getQheaderObject().getCustomerObject().getCname() !=
                                        null) {
                                        businessAgrement = true;
                                        if (v93k.getQheaderObject().getContractObject() !=
                                            null) {
                                            if (v93k.getQheaderObject().getContractObject().getConid() !=
                                                null &&
                                                v93k.getQheaderObject().getContractObject().getConid().toString() !=
                                                null &&
                                                !"".equalsIgnoreCase(v93k.getQheaderObject().getContractObject().getConid())) {
                                                ob.getParamsMap().put("businessAggrement",
                                                                      v93k.getQheaderObject().getContractObject().getConid().toString());
                                            } else
                                                ob.getParamsMap().put("businessAggrement",
                                                                      null);
                                        }
                                    }
                                    //adding code to pass email,phone and contact to amimpl
                                    if (v93k.getQheaderObject().getCustomerObject().getCemail() !=
                                        null &&
                                        !"".equalsIgnoreCase(v93k.getQheaderObject().getCustomerObject().getCemail().toString())) {
                                        ob.getParamsMap().put("custEmail",
                                                              v93k.getQheaderObject().getCustomerObject().getCemail().toString());
                                    } else {
                                        ob.getParamsMap().put("custEmail",
                                                              null);
                                    }
                                    if (v93k.getQheaderObject().getCustomerObject().getCphone() !=
                                        null &&
                                        !"".equalsIgnoreCase(v93k.getQheaderObject().getCustomerObject().getCphone().toString())) {
                                        ob.getParamsMap().put("custPhone",
                                                              v93k.getQheaderObject().getCustomerObject().getCphone().toString());
                                    } else {
                                        ob.getParamsMap().put("custPhone",
                                                              null);
                                    }
                                    if (v93k.getQheaderObject().getCustomerObject().getCcontact() !=
                                        null &&
                                        !"".equalsIgnoreCase(v93k.getQheaderObject().getCustomerObject().getCcontact().toString())) {
                                        ob.getParamsMap().put("ccontact",
                                                              v93k.getQheaderObject().getCustomerObject().getCcontact().toString());
                                    } else {
                                        ob.getParamsMap().put("ccontact",
                                                              null);
                                    }


                                }
                            }
                        }
                        if (v93k.getQheaderObject().getQtitle() != null &&
                            !"".equalsIgnoreCase(v93k.getQheaderObject().getQtitle()))
                            ob.getParamsMap().put("quoteDesc",
                                                  v93k.getQheaderObject().getQtitle().toString());
                        else
                            ob.getParamsMap().put("quoteDesc", null);

                        if (v93k.getQheaderObject().getDealObject() != null) {
                            if (v93k.getQheaderObject().getDealObject().getDincoterm() !=
                                null &&
                                !"".equalsIgnoreCase(v93k.getQheaderObject().getDealObject().getDincoterm()))
                                ob.getParamsMap().put("incoTerm",
                                                      v93k.getQheaderObject().getDealObject().getDincoterm().toString());
                            else
                                ob.getParamsMap().put("incoTerm", null);
                            if (v93k.getQheaderObject().getDealObject().getSalesch() !=
                                null &&
                                !"".equalsIgnoreCase(v93k.getQheaderObject().getDealObject().getSalesch()))
                                ob.getParamsMap().put("salesCh",
                                                      v93k.getQheaderObject().getDealObject().getSalesch().toString());
                            else
                                ob.getParamsMap().put("salesCh", null);

                            if (v93k.getQheaderObject().getDealObject().getDpayterm() !=
                                null &&
                                !"".equalsIgnoreCase(v93k.getQheaderObject().getDealObject().getDpayterm()))
                                ob.getParamsMap().put("payTerm",
                                                      v93k.getQheaderObject().getDealObject().getDpayterm().toString());
                            else
                                ob.getParamsMap().put("payTerm", null);
                            if (v93k.getQheaderObject().getDealObject().getCurrency() !=
                                null &&
                                !"".equalsIgnoreCase(v93k.getQheaderObject().getDealObject().getCurrency()))
                                ob.getParamsMap().put("currency",
                                                      v93k.getQheaderObject().getDealObject().getCurrency().toString());
                            else
                                ob.getParamsMap().put("currency", null);
                        }

                        if (ob.getErrors().size() == 0 && ob != null) {
                            msg = (String)ob.execute();
                            if (msg != null && msg.toString() != null) {
                                FacesMessage message = new FacesMessage(msg);
                                message.setSeverity(FacesMessage.SEVERITY_WARN);
                                fc.addMessage(null, message);
                            }
                            //                                bindErrForCreate.setValue(msg);
                        }
                        //                                if(!"<html><body></body></html>".equalsIgnoreCase(msg)){
                        //                                        RichPopup.PopupHints hints = new RichPopup.PopupHints();
                        //                                                  this.getBindCreateQuotePopup().show(hints);
                        //                                    }
                    }

                    else {
                        OperationBinding ob1 =
                            getBindings().getOperationBinding("clearQuoteFields");
                        if (ob1.getErrors().size() == 0)
                            ob1.execute();
                    }
                } else {
                    OperationBinding ob1 =
                        getBindings().getOperationBinding("clearQuoteFields");
                    if (ob1.getErrors().size() == 0)
                        ob1.execute();
                }
            } else {
                OperationBinding ob =
                    getBindings().getOperationBinding("clearQuoteFields");
                if (ob.getErrors().size() == 0)
                    ob.execute();
            }
        } else {
            OperationBinding ob =
                getBindings().getOperationBinding("clearQuoteFields");
            if (ob.getErrors().size() == 0)
                ob.execute();
        }
        return "initQuote";
    }


    public String clearFields() {
        isCustEnable = false;
        businessAgrement = false;
        /*  For CreateMode Opeartion */
        OperationBinding ob =
            getBindings().getOperationBinding("clearQuoteFields");
        if (ob.getErrors().size() == 0) {
            ob.execute();
        }

        return "clear";
    }

    public void searchQuote(ActionEvent actionEvent) {
        OperationBinding ob = getBindings().getOperationBinding("searchQuote");
        //        if(ob.getErrors().size()==0){
        ob.execute();
    }

    public void custmoerSupportVCE(ValueChangeEvent valueChangeEvent) {
        System.out.println(valueChangeEvent.getNewValue());
        if (valueChangeEvent.getNewValue() != valueChangeEvent.getOldValue() &&
            valueChangeEvent.getNewValue() != null) {
            OperationBinding ob =
                getBindings().getOperationBinding("getFaxNum");
            //        if(ob.getErrors().size()==0){
            ob.execute();
        }
    }

    public void custNameVCE(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != valueChangeEvent.getOldValue() &&
            valueChangeEvent.getNewValue() != null) {
            businessAgrement = true;
            //        OperationBinding ob = getBindings().getOperationBinding("getQuoteCustmerAddress");
            ////                if(ob.getErrors().size()==0){
            //            ob.execute();
        } else {
            businessAgrement = false;
        }
    }

    public void custNameRPL(ReturnPopupEvent returnPopupEvent) {
        if (returnPopupEvent != null) {
            System.out.println("rop:" +
                               returnPopupEvent.getReturnValue().toString());
            OperationBinding ob =
                getBindings().getOperationBinding("getQuoteCustmerAddress");
            ob.getParamsMap().put("curRow", null);
            //                if(ob.getErrors().size()==0){
            ob.execute();
        }
    }

    public void ouVCE(ValueChangeEvent vce) {
        if (vce.getOldValue() != vce.getNewValue() &&
            vce.getNewValue() != null) {
            isCustEnable = true;
            OperationBinding binding =
                getBindings().getOperationBinding("defaultSalesRepOnCreateQuote");
            if (binding != null && binding.getErrors().size() == 0)
                binding.execute();
            getBindOrderType().setValue(null);
            getBindCustomerName().setValue(null);
            getBindCustNumber().setValue(null);

        } else {
            isCustEnable = false;
        }
    }

    public void OURPE(ReturnPopupEvent returnPopupEvent) {
        if (returnPopupEvent != null) {
            isCustEnable = true;
            OperationBinding binding =
                getBindings().getOperationBinding("defaultSalesRepOnCreateQuote");
            if (binding != null && binding.getErrors().size() == 0)
                binding.execute();
            getBindOrderType().setValue(null);
            getBindCustomerName().setValue(null);
            getBindCustNumber().setValue(null);

        } else {
            isCustEnable = false;
        }
    }


    public String updateQuote() {
        //getUpdateQuote
        OperationBinding ob =
            getBindings().getOperationBinding("getUpdateQuote");
        if (ob.getErrors().size() == 0) {
            ob.execute();
        }
        return "update";
    }


    public void setBindOrderType(RichInputListOfValues bindOrderType) {
        this.bindOrderType = bindOrderType;
    }

    public RichInputListOfValues getBindOrderType() {
        return bindOrderType;
    }

    public void setBindCustomerName(RichInputListOfValues bindCustomerName) {
        this.bindCustomerName = bindCustomerName;
    }

    public RichInputListOfValues getBindCustomerName() {
        return bindCustomerName;
    }

    public void setBindCustNumber(RichInputListOfValues bindCustNumber) {
        this.bindCustNumber = bindCustNumber;
    }

    public RichInputListOfValues getBindCustNumber() {
        return bindCustNumber;
    }

    public void setIsCustEnable(boolean isCustEnable) {
        this.isCustEnable = isCustEnable;
    }

    public boolean isIsCustEnable() {
        return isCustEnable;
    }

    public void setBusinessAgrement(boolean businessAgrement) {
        this.businessAgrement = businessAgrement;
    }

    public boolean isBusinessAgrement() {
        return businessAgrement;
    }


    public void updateQuote(ActionEvent actionEvent) {
        String msg = "";
        int respid =
            Integer.parseInt((String)ADFUtils.getSessionScopeValue("RespId") ==
                             null ? "51156" :
                             (String)ADFUtils.getSessionScopeValue("RespId"));
        int usrId =
            Integer.parseInt((String)ADFUtils.getSessionScopeValue("UserId") ==
                             null ? "0" :
                             (String)ADFUtils.getSessionScopeValue("UserId"));
        FacesContext fc = FacesContext.getCurrentInstance();
        OperationBinding operationBinding =
            getBindings().getOperationBinding("callUpdateQuoteAPI");
        operationBinding.getParamsMap().put("respid", respid);
        operationBinding.getParamsMap().put("usrId", usrId);
        if (operationBinding.getErrors().size() == 0 &
            operationBinding != null)
            msg = (String)operationBinding.execute();
        FacesMessage message = new FacesMessage(msg);
        if (msg.contains("<html><body>")) {
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(null, message);
        } else if (msg.contains("S-")) {


            if (getBindDiscount().getValue() != null) {
                V93kQuote v93k =
                    (V93kQuote)ADFUtils.getSessionScopeValue("parentObject");
                if (v93k != null) {
                    if (v93k.getQheaderObject() != null) {
                        if (v93k.getQheaderObject().getDealObject() != null) {
                            v93k.getQheaderObject().getDealObject().setDdiscount(getBindDiscount().getValue().toString());
                        }
                    }
                }
            }


            String[] resultMsg = msg.split("-", 2);
            msg = resultMsg[1];
            message = new FacesMessage(msg);
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            fc.addMessage(null, message);
        } else if (msg.contains("E-")) {
            String[] resultMsg = msg.split("-", 2);
            msg = resultMsg[1];
            message = new FacesMessage(msg);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(null, message);
        } else {
        }


    }

    public void setCurrency1(String currency1) {
        this.currency1 = currency1;
    }

    public String getCurrency1() {
        return currency1;
    }

    public void setBindErrForCreate(RichOutputFormatted bindErrForCreate) {
        this.bindErrForCreate = bindErrForCreate;
    }

    public RichOutputFormatted getBindErrForCreate() {
        return bindErrForCreate;
    }

    public void setBindCreateQuotePopup(RichPopup bindCreateQuotePopup) {
        this.bindCreateQuotePopup = bindCreateQuotePopup;
    }

    public RichPopup getBindCreateQuotePopup() {
        return bindCreateQuotePopup;
    }

    public void setBindDiscount(RichInputText bindDiscount) {
        this.bindDiscount = bindDiscount;
    }

    public RichInputText getBindDiscount() {
        return bindDiscount;
    }
}
