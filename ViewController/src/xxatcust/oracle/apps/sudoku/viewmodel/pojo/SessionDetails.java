package xxatcust.oracle.apps.sudoku.viewmodel.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import xxatcust.oracle.apps.sudoku.viewmodel.ui.elements.ConfiguratorUiNode;

public class SessionDetails {
    private String userId;
    private String applicationId;
    private String respId;
    private String databaseId;
    private String modelId;
    private String topItemId;
    private String publicationId;
    private String publicationUsage;
    private String masterOrgID;
    private String modelName;
    private String priceListID;
    private Double conversionRateToUSD;
    private String configHdrId;
    private String configRevNbr;
    private String configurationMode;
    private ArrayList<String> nonComplaintsItems = new ArrayList<String>(); //TODO remove this before UAT
    private String servletMode; // value is EBS when using from EBS side manually testing
    private String icxSessionTokenValue;
    private HashMap<String, String> fndMessages = new HashMap<String, String>(); 
    private HashMap<String, String> categoryToXMLTagMappings = new HashMap<String, String>(); 
    private boolean reloadInExpertMode = false;
    
    private boolean formalQuote = false;
    private boolean updateQuote = false;
    private boolean duplicateQuote = false;
    private boolean createNewQuote = false;
    private String sourceQuoteNumber;
    private String targetQuoteNumber;
    
    private boolean upgradeOnSourceConfiguration = false;
    private boolean upgradefromScratch = false;
    private boolean systemConfigurationFromUI = false;
    private boolean systemConfigurationFromImport = false;
    
    private String decimalSeperator = ".";// . is default decimal seperator
    private String groupSeperator = ",";  // , is default group seperator
    
    private boolean isQuoteSaved = false;
    
    
    // Non bom items from the source configuration before doing upgrade 
    HashSet<ConfiguratorUiNode> sourceConfigNonBomItems = new HashSet<ConfiguratorUiNode>();

    // Bom items from the source configuration before doing upgrade 
    HashMap<String, Integer> sourceConfigBomItems = new HashMap<String, Integer>();

    HashMap<String, HashMap<String,Integer>> miscUpgradeItems = new HashMap<String, HashMap<String,Integer>>();
    HashMap<String, HashMap<String,Integer>> infraStructureUpgradeItems = new HashMap<String, HashMap<String,Integer>>();
    
    public SessionDetails() {
        super();
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setRespId(String respId) {
        this.respId = respId;
    }

    public String getRespId() {
        return respId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }

    public String getPublicationId() {
        return publicationId;
    }

    public void setMasterOrgID(String masterOrgID) {
        this.masterOrgID = masterOrgID;
    }

    public String getMasterOrgID() {
        return masterOrgID;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setPriceListID(String priceListID) {
        this.priceListID = priceListID;
    }

    public String getPriceListID() {
        return priceListID;
    }

    public void setConversionRateToUSD(Double conversionRateToUSD) {
        this.conversionRateToUSD = conversionRateToUSD;
    }

    public Double getConversionRateToUSD() {
        return conversionRateToUSD;
    }

    public void setConfigHdrId(String configHdrId) {
        this.configHdrId = configHdrId;
    }

    public String getConfigHdrId() {
        return configHdrId;
    }

    public void setConfigRevNbr(String configRevNbr) {
        this.configRevNbr = configRevNbr;
    }

    public String getConfigRevNbr() {
        return configRevNbr;
    }

    public void setPublicationUsage(String publicationUsage) {
        this.publicationUsage = publicationUsage;
    }

    public String getPublicationUsage() {
        return publicationUsage;
    }

    public void setTopItemId(String topItemId) {
        this.topItemId = topItemId;
    }

    public String getTopItemId() {
        return topItemId;
    }

    public void setConfigurationMode(String configurationMode) {
        this.configurationMode = configurationMode;
    }

    public String getConfigurationMode() {
        return configurationMode;
    }

    public void setNonComplaintsItems(ArrayList<String> nonComplaintsItems) {
        this.nonComplaintsItems = nonComplaintsItems;
    }

    public ArrayList<String> getNonComplaintsItems() {
        return nonComplaintsItems;
    }

    public void addNonComplaintsItems(String nonComplaintsItems) {
        this.nonComplaintsItems.add(nonComplaintsItems);
    }

    public void setServletMode(String servletMode) {
        this.servletMode = servletMode;
    }

    public String getServletMode() {
        return servletMode;
    }

    public void setFndMessages(HashMap<String, String> fndMessages) {
        this.fndMessages = fndMessages;
    }

    public HashMap<String, String> getFndMessages() {
        return fndMessages;
    }

    public void setReloadInExpertMode(boolean reloadInExpertMode) {
        this.reloadInExpertMode = reloadInExpertMode;
    }

    public boolean isReloadInExpertMode() {
        return reloadInExpertMode;
    }

    public void setFormalQuote(boolean isFormalQuote) {
        this.formalQuote = isFormalQuote;
    }

    public boolean isFormalQuote() {
        return formalQuote;
    }

    public void setUpdateQuote(boolean useExistingQuote) {
        this.updateQuote = useExistingQuote;
    }

    public boolean isUpdateQuote() {
        return updateQuote;
    }

    public void setDuplicateQuote(boolean duplicateQuote) {
        this.duplicateQuote = duplicateQuote;
    }

    public boolean isDuplicateQuote() {
        return duplicateQuote;
    }

    public void setCreateNewQuote(boolean createNewQuote) {
        this.createNewQuote = createNewQuote;
    }

    public boolean isCreateNewQuote() {
        return createNewQuote;
    }

    public void setCategoryToXMLTagMappings(HashMap<String, String> categoryToXMLTagMappings) {
        this.categoryToXMLTagMappings = categoryToXMLTagMappings;
    }

    public HashMap<String, String> getCategoryToXMLTagMappings() {
        return categoryToXMLTagMappings;
    }

    public void setSourceQuoteNumber(String sourceQuoteNumber) {
        this.sourceQuoteNumber = sourceQuoteNumber;
    }

    public String getSourceQuoteNumber() {
        return sourceQuoteNumber;
    }

    public void setTargetQuoteNumber(String targetQuoteNumber) {
        this.targetQuoteNumber = targetQuoteNumber;
    }

    public String getTargetQuoteNumber() {
        return targetQuoteNumber;
    }

    public void setUpgradeOnSourceConfiguration(boolean upgradeOnSourceConfiguration) {
        this.upgradeOnSourceConfiguration = upgradeOnSourceConfiguration;
    }

    public boolean isUpgradeOnSourceConfiguration() {
        return upgradeOnSourceConfiguration;
    }

    public void setUpgradefromScratch(boolean upgradefromScratch) {
        this.upgradefromScratch = upgradefromScratch;
    }

    public boolean isUpgradefromScratch() {
        return upgradefromScratch;
    }

    public void setSystemConfigurationFromUI(boolean systemConfigurationFromUI) {
        this.systemConfigurationFromUI = systemConfigurationFromUI;
    }

    public boolean isSystemConfigurationFromUI() {
        return systemConfigurationFromUI;
    }

    public void setSystemConfigurationFromImport(boolean systemConfigurationFromImport) {
        this.systemConfigurationFromImport = systemConfigurationFromImport;
    }

    public boolean isSystemConfigurationFromImport() {
        return systemConfigurationFromImport;
    }

    public void setSourceConfigNonBomItems(HashSet<ConfiguratorUiNode> sourceConfigNonBomItems) {
        this.sourceConfigNonBomItems = sourceConfigNonBomItems;
    }

    public HashSet<ConfiguratorUiNode> getSourceConfigNonBomItems() {
        return sourceConfigNonBomItems;
    }

    public void setSourceConfigBomItems(HashMap<String, Integer> sourceConfigBomItems) {
        this.sourceConfigBomItems = sourceConfigBomItems;
    }

    public HashMap<String, Integer> getSourceConfigBomItems() {
        return sourceConfigBomItems;
    }

    public void setMiscUpgradeItems(HashMap<String, HashMap<String, Integer>> miscUpgradeItems) {
        this.miscUpgradeItems = miscUpgradeItems;
    }

    public HashMap<String, HashMap<String, Integer>> getMiscUpgradeItems() {
        return miscUpgradeItems;
    }

    public void setInfraStructureUpgradeItems(HashMap<String, HashMap<String, Integer>> infraStructureUpgradeItems) {
        this.infraStructureUpgradeItems = infraStructureUpgradeItems;
    }

    public HashMap<String, HashMap<String, Integer>> getInfraStructureUpgradeItems() {
        return infraStructureUpgradeItems;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public void setIcxSessionTokenValue(String icxSessionTokenValue) {
        this.icxSessionTokenValue = icxSessionTokenValue;
    }

    public String getIcxSessionTokenValue() {
        return icxSessionTokenValue;
    }

    public void setDecimalSeperator(String decimalSeperator) {
        this.decimalSeperator = decimalSeperator;
    }

    public String getDecimalSeperator() {
        return decimalSeperator;
    }

    public void setGroupSeperator(String groupSeperator) {
        this.groupSeperator = groupSeperator;
    }

    public String getGroupSeperator() {
        return groupSeperator;
    }

    public void setQuoteSaved(boolean isQuoteSaved) {
        this.isQuoteSaved = isQuoteSaved;
    }

    public boolean isQuoteSaved() {
        return isQuoteSaved;
    }
}
