package xxatcust.oracle.apps.sudoku.bean;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.share.logging.ADFLogger;

public class DynamicBinding {
    private String configuratorTFId =
        "/WEB-INF/xxatcust/oracle/apps/sudoku/pageFlows/ConfiguratorFlow.xml#ConfiguratorFlow";
    private String quoteTFId =
        "/WEB-INF/xxatcust/oracle/apps/sudoku/pageFlows/QuotingFlow.xml#QuotingFlow";
    private String viewReferenceTFId =
        "/WEB-INF/xxatcust/oracle/apps/sudoku/pageFlows/UploadXMLFlow.xml#UploadXMLFlow";
    private String quoteTFUpdateId =
        "/WEB-INF/xxatcust/oracle/apps/sudoku/pageFlows/QuoteUpdateFlow.xml#QuoteUpdateFlow";
    String currentTF = "configurator";
    private static ADFLogger _logger =
        ADFLogger.createADFLogger(DynamicBinding.class);
    public DynamicBinding() {
    }

    public TaskFlowId getDynamicTaskFlowId() {
        if (this.getCurrentTF().equalsIgnoreCase("configurator")) {
            _logger.info("Return config");
            return TaskFlowId.parse(configuratorTFId);
        } else if (this.getCurrentTF().equalsIgnoreCase("viewRef")) {
            _logger.info("Return View Ref");
            return TaskFlowId.parse(viewReferenceTFId);
        } else if(this.getCurrentTF().equalsIgnoreCase("quoteUpdate")){
                _logger.info("Quote Update");
                return TaskFlowId.parse(quoteTFUpdateId);
            }
       else{
            _logger.info("Return Quote");
            return TaskFlowId.parse(quoteTFId);
        }
    }

    public String getConfiguratorTFId() {
        return configuratorTFId;
    }

    public String getQuoteTFId() {
        return quoteTFId;
    }

    public void setViewReferenceTFId(String viewReferenceTFId) {
        this.viewReferenceTFId = viewReferenceTFId;
    }

    public String getViewReferenceTFId() {
        return viewReferenceTFId;
    }

    public String getCurrentTF() {
        return currentTF;
    }

    public void setCurrentTF(String currentTF) {
        this.currentTF = currentTF;
    }

    public void setQuoteTFUpdateId(String quoteTFUpdateId) {
        this.quoteTFUpdateId = quoteTFUpdateId;
    }

    public String getQuoteTFUpdateId() {
        return quoteTFUpdateId;
    }
}
