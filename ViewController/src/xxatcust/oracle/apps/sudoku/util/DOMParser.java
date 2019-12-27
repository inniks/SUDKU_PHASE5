package xxatcust.oracle.apps.sudoku.util;

import java.io.File;

import java.math.BigDecimal;

import java.sql.Date;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.Map;
import java.util.Set;


import javax.faces.application.FacesMessage;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import oracle.jbo.Row;

import org.w3c.dom.Document;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

import org.w3c.dom.Node;

import xxatcust.oracle.apps.sudoku.viewmodel.pojo.Config;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.ConfiguratorNodePOJO;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.Contract;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.Customer;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.Deal;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.ModelBom;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.PogoMappingFile;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.QHeader;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.QuoteLinePOJO;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.Ruleset;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.Salesteam;
import xxatcust.oracle.apps.sudoku.viewmodel.pojo.V93kQuote;

public class DOMParser {
    public DOMParser() {
        super();
    }

    public static File XMLWriterDOM(V93kQuote v93kObj, Row r) {
        //Fire the quoteexportvo , Populate all values in v93k object
        if (r != null) {
            //populate the v93obj with these value
            v93kObj = populateQuoteDataOnObject(v93kObj, r);

        }
        if (r == null) {
            if (v93kObj.getConfigObject() != null) {
                //Create a model bom and add it here
                if (v93kObj.getConfigObject().getModelbomObjectList() ==
                    null) {
                    String ruleSetVal = null;
                    if (v93kObj.getInputParams() != null) {
                        ruleSetVal =
                                v93kObj.getInputParams().getRuleSetTopLevelChoice();
                    }
                    ModelBom mb = new ModelBom();
                    // mb.setPricelist((String)r.getAttribute("Pricelistname"));
                    mb.setId(ruleSetVal);
                    Ruleset rf = new Ruleset();
                    rf.setId(v93kObj.getInputParams().getRuleSetSecondLevelChoice());
                    mb.setRulesetObject(rf);

                    ArrayList<ModelBom> mbList = new ArrayList<ModelBom>();
                    mbList.add(mb);
                    v93kObj.getConfigObject().setModelbomObjectList(mbList);
                }
            }
        }
        File f = null;
        DocumentBuilderFactory dbFactory =
            DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            TransformerFactory transformerFactory =
                TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //for pretty print
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            DOMSource source = new DOMSource(doc);
            //This is the Root element v93000-Quote
            Element rootElement = doc.createElementNS(null, "V93000-Quote");
            //Attach the root to document
            doc.appendChild(rootElement);


            if (v93kObj.getQheaderObject() != null) {
                //First create qheader if it exists
                rootElement.appendChild(qHeaderNode(v93kObj.getQheaderObject(),
                                                    doc));
            }


            //Now create ModelBom objects based on the print sequence logic ,
            List<String> catList = new ArrayList<String>();
            List<String> distinctList = new ArrayList<String>();
            List listOfCategoriesMap = new ArrayList();
            List<String> modelNameList = new ArrayList<String>();
            List<List<ConfiguratorNodePOJO>> listOfLists =
                new ArrayList<List<ConfiguratorNodePOJO>>();
            if (v93kObj != null &&
                v93kObj.getTargetConfigurationLines() != null &&
                !v93kObj.getTargetConfigurationLines().isEmpty()) {
                List<QuoteLinePOJO> listOfLines =
                    v93kObj.getTargetConfigurationLines();
                for (QuoteLinePOJO qlp : listOfLines) {
                    modelNameList.add(qlp.getItemName());
                    List<ConfiguratorNodePOJO> listNodes =
                        getAllNodes(v93kObj, qlp);
                    listOfLists.add(listNodes);
                }
                //Now the list of lines is prepared
                if (!listOfLines.isEmpty()) {
                    for (List<ConfiguratorNodePOJO> list : listOfLists) {
                        LinkedHashMap<String, List<ConfiguratorNodePOJO>> allNodesByCategoriesMap =
                            new LinkedHashMap<String, List<ConfiguratorNodePOJO>>();
                        if (list != null && !list.isEmpty()) {
                            for (ConfiguratorNodePOJO node : list) {
                                if (node.getNodeCategory() != null &&
                                    node.getNodeCategory().equalsIgnoreCase("1")) {
                                    node.setXmlTag("HEADER");
                                }
                                if (node.getNodeCategory() != null &&
                                    (node.getNodeCategory().equalsIgnoreCase("2") ||
                                     node.getNodeCategory().equalsIgnoreCase("3") ||
                                     node.getNodeCategory().equalsIgnoreCase("4") ||
                                     node.getNodeCategory().equalsIgnoreCase("5"))) {
                                    node.setXmlTag("HEADER");
                                }
                                if (node.getXmlTag() != null) {
                                    catList.add(node.getXmlTag());
                                }
                            }
                            distinctList = removeDuplicatesFromList(catList);

                            for (String distinctCategory : distinctList) {
                                List<ConfiguratorNodePOJO> temp =
                                    new ArrayList<ConfiguratorNodePOJO>();
                                for (ConfiguratorNodePOJO node : list) {
                                    if (distinctCategory != null &&
                                        distinctCategory.equalsIgnoreCase(node.getXmlTag())) {
                                        temp.add(node);
                                    }
                                }
                                allNodesByCategoriesMap.put(distinctCategory,
                                                            temp);
                            }
                        }
                        listOfCategoriesMap.add(allNodesByCategoriesMap);
                    }

                }
            }
            // Now we have the allNodes by category map with us ,Iterate through each category and create nodes based on the node category
            if (v93kObj.getConfigObject() != null &&
                v93kObj.getConfigObject().getModelbomObjectList() != null) {
                //List<Node> modelBomNodes = modelBomNodes(v93kObj.getConfigObject().getModelbomObjectList(),doc);
                //rootElement.appendChild(modelBomNode(v93kObj.getConfigObject().getModelbomObject(), doc)) ;
            }

            rootElement.appendChild(configPmfNode(v93kObj, doc,
                                                  listOfCategoriesMap,
                                                  modelNameList));

            StreamResult console = new StreamResult(System.out);
            f = new File("DomExport.xml");
            StreamResult file = new StreamResult(f);
            transformer.transform(source, console);
            transformer.transform(source, file);
        } catch (Exception pce) {
            // TODO: Add catch code
            pce.printStackTrace();
        }
        return f;
    }

    private static Node getNode(Document doc, String id, String name,
                                String age, String role, String gender) {
        Element employee = doc.createElement("Employee");

        //set id attribute
        employee.setAttribute("id", id);

        //create name element
        employee.appendChild(getNodeElements(doc, employee, "name", name));

        //create age element
        employee.appendChild(getNodeElements(doc, employee, "age", age));

        //create role element
        employee.appendChild(getNodeElements(doc, employee, "role", role));

        //create gender element
        employee.appendChild(getNodeElements(doc, employee, "gender", gender));

        return employee;
    }

    private static Node getNodeElements(Document doc, Element element,
                                        String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    private static ArrayList<String> removeDuplicatesFromList(List<String> inputList) {

        Set<String> set = new HashSet<String>(inputList);
        List<String> outputList = new ArrayList<String>();
        outputList.clear();
        outputList.addAll(set);
        return (ArrayList<String>)outputList;
    }

    private static Node qHeaderNode(QHeader qheaderObj, Document doc) {
        //parse the qheader object and create nodes accordingly
        LinkedHashMap<String, Boolean> qheaderValidMap =
            (LinkedHashMap<String, Boolean>)ADFUtils.getSessionScopeValue("qheaderValidMap");

        String targetQuoteNumber =
            (String)ADFUtils.getSessionScopeValue("targetQuoteNumber");
        if (targetQuoteNumber != null) {
            qheaderValidMap = new LinkedHashMap<String, Boolean>();
        }

        if (qheaderValidMap == null) {
            qheaderValidMap = new LinkedHashMap<String, Boolean>();
        }
        Element qheadernode = doc.createElement("qheader");
        if (qheaderObj.getQtitle() != null) {
            Node qTitleNode =
                getNodeElements(doc, null, "qtitle", qheaderObj.getQtitle());
            qheadernode.appendChild(qTitleNode);
        }
        //We will check if qheader in xml are validated only in case a target quote does not exist,

        //create customer node
        if (qheaderObj.getCustomerObject() != null) {
            Element customerNode = doc.createElement("customer");
            if (qheaderObj.getCustomerObject().getCnumber() != null) {
                Node cnumNode =
                    getNodeElements(doc, null, "cnumber", qheaderObj.getCustomerObject().getCnumber());
                if (qheaderValidMap.size() > 0) {
                    if (qheaderValidMap.get("custNum") != null &&
                        qheaderValidMap.get("custNum")) {
                        customerNode.appendChild(cnumNode);
                    }
                } else
                    customerNode.appendChild(cnumNode);
            }
            if (qheaderObj.getCustomerObject().getCaddr1() != null) {
                Node caddr1 =
                    getNodeElements(doc, null, "caddr1", qheaderObj.getCustomerObject().getCaddr1());
                customerNode.appendChild(caddr1);
            }
            if (qheaderObj.getCustomerObject().getCaddr2() != null) {
                Node caddr2 =
                    getNodeElements(doc, null, "caddr2", qheaderObj.getCustomerObject().getCaddr2());
                customerNode.appendChild(caddr2);
            }
            if (qheaderObj.getCustomerObject().getCphone() != null) {
                Node cphone =
                    getNodeElements(doc, null, "cphone", qheaderObj.getCustomerObject().getCphone());
                customerNode.appendChild(cphone);
            }
            if (qheaderObj.getCustomerObject().getCname() != null) {
                Node cname =
                    getNodeElements(doc, null, "cname", qheaderObj.getCustomerObject().getCname());
                if (!qheaderValidMap.isEmpty()) {
                    if (qheaderValidMap.get("custName") != null &&
                        qheaderValidMap.get("custName")) {
                        customerNode.appendChild(cname);
                    }
                } else
                    //customerNode.appendChild(cnumNode);
                    customerNode.appendChild(cname);
            }
            if (qheaderObj.getCustomerObject().getCcontact() != null) {
                Node ccontact =
                    getNodeElements(doc, null, "ccontact", qheaderObj.getCustomerObject().getCcontact());
                customerNode.appendChild(ccontact);
            }
            if (qheaderObj.getCustomerObject().getCemail() != null) {
                Node cemail =
                    getNodeElements(doc, null, "cemail", qheaderObj.getCustomerObject().getCemail());
                customerNode.appendChild(cemail);
            }
            if (qheaderObj.getCustomerObject().getGlobalcmpy() != null) {
                Node globalcmpy =
                    getNodeElements(doc, null, "globalcmpy", qheaderObj.getCustomerObject().getGlobalcmpy());
                if (!qheaderValidMap.isEmpty()) {
                    if (qheaderValidMap.get("salesChannel") != null &&
                        qheaderValidMap.get("salesChannel")) {
                        customerNode.appendChild(globalcmpy);
                    }
                } else
                    customerNode.appendChild(globalcmpy);
            }

            //Add customer node to quoteheadernode once all attributes are taken care of
            qheadernode.appendChild(customerNode);
        }
        //Customer done,Now start contract object
        if (qheaderObj.getContractObject() != null) {
            Element contractNode = doc.createElement("contract");
            if (qheaderObj.getContractObject().getConid() != null) {
                Node conid =
                    getNodeElements(doc, null, "conid", qheaderObj.getContractObject().getConid());
                contractNode.appendChild(conid);
            }
            qheadernode.appendChild(contractNode);
        }

        //Contract Node done , Now created deal node

        if (qheaderObj.getDealObject() != null) {
            Element dealNode = doc.createElement("deal");
            if (qheaderObj.getDealObject().getDealid() != null) {
                Node dealid =
                    getNodeElements(doc, null, "dealid", qheaderObj.getDealObject().getDealid());
                dealNode.appendChild(dealid);
            }
            if (qheaderObj.getDealObject().getSalesch() != null) {
                Node salesch =
                    getNodeElements(doc, null, "salesch", qheaderObj.getDealObject().getSalesch());
                dealNode.appendChild(salesch);
            }
            if (qheaderObj.getDealObject().getStatus() != null) {
                Node status =
                    getNodeElements(doc, null, "status", qheaderObj.getDealObject().getStatus());
                dealNode.appendChild(status);
            }
            if (qheaderObj.getDealObject().getQuoteid() != null) {
                Node quoteid =
                    getNodeElements(doc, null, "quoteid", qheaderObj.getDealObject().getQuoteid());
                dealNode.appendChild(quoteid);
            }
            if (qheaderObj.getDealObject().getSoNumber() != null) {
                Node sonumber =
                    getNodeElements(doc, null, "so-number", qheaderObj.getDealObject().getSoNumber());
                dealNode.appendChild(sonumber);
            }
            if (qheaderObj.getDealObject().getSysid() != null) {
                Node sysid =
                    getNodeElements(doc, null, "sysid", qheaderObj.getDealObject().getSysid());
                dealNode.appendChild(sysid);
            }
            if (qheaderObj.getDealObject().getCurrency() != null) {
                Node currency =
                    getNodeElements(doc, null, "currency", qheaderObj.getDealObject().getCurrency());
                if (!qheaderValidMap.isEmpty()) {
                    if (qheaderValidMap.get("currency") != null &&
                        qheaderValidMap.get("currency")) {
                        dealNode.appendChild(currency);
                    }
                } else
                    dealNode.appendChild(currency);
            }
            if (qheaderObj.getDealObject().getDdiscount() != null) {
                Node ddiscount =
                    getNodeElements(doc, null, "ddiscount", qheaderObj.getDealObject().getDdiscount());
                dealNode.appendChild(ddiscount);
            }
            if (qheaderObj.getDealObject().getDincoterm() != null) {
                Node dincoterm =
                    getNodeElements(doc, null, "dincoterm", qheaderObj.getDealObject().getDincoterm());
                if (!qheaderValidMap.isEmpty()) {
                    if (qheaderValidMap.get("incoTerm") != null &&
                        qheaderValidMap.get("incoTerm")) {
                        dealNode.appendChild(dincoterm);
                    }
                } else
                    dealNode.appendChild(dincoterm);
            }
            if (qheaderObj.getDealObject().getDpayterm() != null) {
                Node dpayterm =
                    getNodeElements(doc, null, "dpayterm", qheaderObj.getDealObject().getDpayterm());
                if (!qheaderValidMap.isEmpty()) {
                    if (qheaderValidMap.get("paymentTerm") != null &&
                        qheaderValidMap.get("paymentTerm")) {
                        dealNode.appendChild(dpayterm);
                    }
                } else
                    dealNode.appendChild(dpayterm);
            }
            if (qheaderObj.getDealObject().getDwtyterm() != null) {
                Node dwtyterm =
                    getNodeElements(doc, null, "dwtyterm", qheaderObj.getDealObject().getDwtyterm());
                dealNode.appendChild(dwtyterm);
            }
            if (qheaderObj.getDealObject().getQdate() != null) {
                Node qdate =
                    getNodeElements(doc, null, "qdate", qheaderObj.getDealObject().getQdate());
                dealNode.appendChild(qdate);
            }
            if (qheaderObj.getDealObject().getExpdate() != null) {
                Node expdate =
                    getNodeElements(doc, null, "expdate", qheaderObj.getDealObject().getExpdate());
                dealNode.appendChild(expdate);
            }
            if (qheaderObj.getDealObject().getOdate() != null) {
                Node odate =
                    getNodeElements(doc, null, "odate", qheaderObj.getDealObject().getOdate());
                dealNode.appendChild(odate);
            }
            //            if (qheaderObj.getDealObject().getCrd() != null) {
            //                Node crd =
            //                    getNodeElements(doc, null, "crd", qheaderObj.getDealObject().getCrd());
            //                //dealNode.appendChild(crd);
            //
            //            }

            if (qheaderObj.getDealObject().getDordertype() != null) {
                Node orderType =
                    getNodeElements(doc, null, "dordertype", qheaderObj.getDealObject().getDordertype());
                if (!qheaderValidMap.isEmpty()) {
                    if (qheaderValidMap.get("OrderType") != null &&
                        qheaderValidMap.get("OrderType")) {
                        dealNode.appendChild(orderType);
                    }
                } else
                    dealNode.appendChild(orderType);
            }
            qheadernode.appendChild(dealNode);
        }

        //Deal node done , Now create Salesteam node
        if (qheaderObj.getSalesteamObject() != null) {
            Element salesteamNode = doc.createElement("salesteam");
            if (qheaderObj.getSalesteamObject().getOu() != null) {
                Node ou =
                    getNodeElements(doc, null, "ou", qheaderObj.getSalesteamObject().getOu());
                if (!qheaderValidMap.isEmpty()) {
                    if (qheaderValidMap.get("OU") != null &&
                        qheaderValidMap.get("OU")) {
                        salesteamNode.appendChild(ou);
                    }
                } else
                    salesteamNode.appendChild(ou);
            }
            if (qheaderObj.getSalesteamObject().getSrespo() != null) {
                Node srespo =
                    getNodeElements(doc, null, "srespo", qheaderObj.getSalesteamObject().getSrespo());
                salesteamNode.appendChild(srespo);
            }
            if (qheaderObj.getSalesteamObject().getSphone() != null) {
                Node sphone =
                    getNodeElements(doc, null, "sphone", qheaderObj.getSalesteamObject().getSphone());
                salesteamNode.appendChild(sphone);
            }
            if (qheaderObj.getSalesteamObject().getSemail() != null) {
                Node semail =
                    getNodeElements(doc, null, "semail", qheaderObj.getSalesteamObject().getSemail());
                salesteamNode.appendChild(semail);
            }
            if (qheaderObj.getSalesteamObject().getCsrrespo() != null) {
                Node csrrespo =
                    getNodeElements(doc, null, "csrrespo", qheaderObj.getSalesteamObject().getCsrrespo());
                if (!qheaderValidMap.isEmpty()) {
                    if (qheaderValidMap.get("custSupportRep") != null &&
                        qheaderValidMap.get("custSupportRep")) {
                        salesteamNode.appendChild(csrrespo);
                    }
                } else
                    salesteamNode.appendChild(csrrespo);
            }
            if (qheaderObj.getSalesteamObject().getCsrphone() != null) {
                Node csrphone =
                    getNodeElements(doc, null, "csrphone", qheaderObj.getSalesteamObject().getCsrphone());
                salesteamNode.appendChild(csrphone);
            }
            if (qheaderObj.getSalesteamObject().getCsremail() != null) {
                Node csremail =
                    getNodeElements(doc, null, "csremail", qheaderObj.getSalesteamObject().getCsremail());
                salesteamNode.appendChild(csremail);
            }

            qheadernode.appendChild(salesteamNode);
        }


        return qheadernode;
    }

    private static Node configPmfNode(V93kQuote v93, Document doc,
                                      List<LinkedHashMap> listOfLines,
                                      List modelNameList) {
        //listOfLines.get(index)
        boolean upgradeFromScratch = false;
        if (v93 != null && v93.getSessionDetails() != null &&
            v93.getSessionDetails().isUpgradefromScratch()) {
            upgradeFromScratch = true;
        }
        Element configNode = doc.createElement("config");
        Config configObj = v93.getConfigObject();
        if (listOfLines != null && !listOfLines.isEmpty()) {
            for (LinkedHashMap mapByLine : listOfLines) {
                Node modelBomNode =
                    modelBomNode(v93, doc, mapByLine, modelNameList);
                if (modelBomNode != null) {
                    configNode.appendChild(modelBomNode);
                }
                //configNode.appendChild(modelBomNode(v93, doc, mapByLine));
            }
        }
        if (configObj != null && configObj.getVersion() != null) {
            configNode.setAttribute("version", configObj.getVersion());
        }
        if (configObj != null && configObj.getPmfObject() != null &&
            !upgradeFromScratch) {
            Node pmfNode = doc.createElement("pmf");
            List<PogoMappingFile> listPmf =
                configObj.getPmfObject().getPmfMap();
            for (PogoMappingFile pmfObj : listPmf) {
                Element mapNode = doc.createElement("map");
                if (pmfObj.getRefId() != null)
                    mapNode.setAttribute("ref-id", pmfObj.getRefId());
                if (pmfObj.getProductName() != null)
                    mapNode.setAttribute("card", pmfObj.getProductName());
                if (pmfObj.getPogoName() != null)
                    mapNode.setAttribute("cable", pmfObj.getPogoName());
                if (pmfObj.getPogoMapping() != null)
                    mapNode.setTextContent(pmfObj.getPogoMapping());

                pmfNode.appendChild(mapNode);
            }
            configNode.appendChild(pmfNode);
        }


        return configNode;
    }

    private static Node modelBomNode(V93kQuote v93, Document doc,
                                     LinkedHashMap<String, List<ConfiguratorNodePOJO>> allNodesByCategoriesMap,
                                     List<String> modelNameList) {
        Element modelbomNode = doc.createElement("modelbom");
        if (v93 != null && allNodesByCategoriesMap != null &&
            !allNodesByCategoriesMap.isEmpty()) {
            Iterator<Map.Entry<String, List<ConfiguratorNodePOJO>>> itr =
                allNodesByCategoriesMap.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry<String, List<ConfiguratorNodePOJO>> entry =
                    itr.next();
                List<ConfiguratorNodePOJO> list = entry.getValue();
                if (list != null) {
                    for (ConfiguratorNodePOJO node : list) {
                        System.out.println("Node " + node.getNodeName());
                        if (node != null && node.getNodeName() != null &&
                            node.getNodeCategory() != null &&
                            node.getNodeCategory().equals("1")) {
                            System.out.println("Setting ModelBom Id " +
                                               node.getNodeName());
                            modelbomNode.setAttribute("id",
                                                      node.getNodeName());
                        }
                        if (node != null && node.getNodeName() != null &&
                            v93.getInputParams() != null &&
                            v93.getInputParams().getRuleSetTopLevelChoice() !=
                            null &&
                            node.getNodeCategory().equalsIgnoreCase("1") &&
                            node.getNodeName().equalsIgnoreCase(v93.getInputParams().getRuleSetTopLevelChoice())) {
                            System.out.println("Matched the values");
                            System.out.println("Rule Set Top Level " +
                                               v93.getInputParams().getRuleSetTopLevelChoice());
                            System.out.println("Config object is " +
                                               v93.getConfigObject());
                            if (v93.getConfigObject() != null) {
                                List<ModelBom> modelBomList =
                                    v93.getConfigObject().getModelbomObjectList();
                                if (modelBomList != null &&
                                    !modelBomList.isEmpty()) {
                                    for (ModelBom modelBomObj : modelBomList) {
                                        if (modelBomObj.getId() != null) {
                                            if (modelBomObj.getCid() != null)
                                                modelbomNode.appendChild(getNodeElements(doc,
                                                                                         null,
                                                                                         "cid",
                                                                                         modelBomObj.getCid()));
                                            if (modelBomObj.getPricelist() !=
                                                null)
                                                modelbomNode.appendChild(getNodeElements(doc,
                                                                                         null,
                                                                                         "pricelist",
                                                                                         modelBomObj.getPricelist()));
                                            if (modelBomObj.getClassObject() !=
                                                null) {
                                                Element classNode =
                                                    doc.createElement("class");
                                                if (modelBomObj.getClassObject().getId() !=
                                                    null)
                                                    classNode.setAttribute("id",
                                                                           modelBomObj.getClassObject().getId());
                                                if (modelBomObj.getClassObject().getCompatibility() !=
                                                    null)
                                                    classNode.setAttribute("compatibility",
                                                                           modelBomObj.getClassObject().getCompatibility());
                                                modelbomNode.appendChild(classNode);

                                                //Add ruleset node

                                            }

                                        }
                                    }
                                    if (v93.getInputParams().getRuleSetSecondLevelChoice() !=
                                        null) {
                                        Element ruleSetNode =
                                            doc.createElement("ruleset");
                                        ruleSetNode.setAttribute("id",
                                                                 v93.getInputParams().getRuleSetSecondLevelChoice());
                                        modelbomNode.appendChild(ruleSetNode);
                                    }
                                }

                            }
                        }
                    }
                }
            }


            for (Map.Entry<String, List<ConfiguratorNodePOJO>> entry :
                 allNodesByCategoriesMap.entrySet()) {
                String key = entry.getKey();
                List<ConfiguratorNodePOJO> listItemsTemp = entry.getValue();
                if (listItemsTemp != null && !listItemsTemp.isEmpty()) {
                    ConfiguratorNodePOJO firstNode = listItemsTemp.get(0);
                    if (firstNode != null && firstNode.getNodeName() != null &&
                        firstNode.getNodeCategory() != null &&
                        (firstNode.getNodeCategory().equals("1") ||
                         firstNode.getNodeCategory().equals("2") ||
                         firstNode.getNodeCategory().equals("3"))) {
                        System.out.println("Setting ModelBom Id " +
                                           firstNode.getNodeName());
                        modelbomNode.setAttribute("id",
                                                  firstNode.getNodeName());
                    }

                }
                if (!key.equalsIgnoreCase("HEADER")) {
                    List<ConfiguratorNodePOJO> listItems = entry.getValue();

                    Element itemNode = doc.createElement(key);
                    System.out.println("**************************************");
                    if (!listItems.isEmpty()) {

                        for (ConfiguratorNodePOJO node : listItems) {
                            Element e = doc.createElement("item");
                            if (node.getNodeName() != null) {

                                e.setTextContent(node.getNodeName());
                            }
                            if (node.getNodeQty() != null) {
                                try {
                                    String s = node.getNodeQty();
                                    float f = new Float(s);
                                    int i = (int)f;

                                    e.setAttribute("qty", Integer.toString(i));
                                } catch (NumberFormatException nfe) {
                                    // TODO: Add catch code
                                    ADFUtils.showFacesMessage(nfe.getMessage(),
                                                              FacesMessage.SEVERITY_ERROR);
                                } catch (DOMException dome) {
                                    // TODO: Add catch code
                                    ADFUtils.showFacesMessage(dome.getMessage(),
                                                              FacesMessage.SEVERITY_ERROR);
                                }
                            }
                            itemNode.appendChild(e);
                        }

                        modelbomNode.appendChild(itemNode);
                    }
                }
            }
            //modelBomNodes.add(modelbomNode) ;
        }


        return modelbomNode;
    }

    private static List<ConfiguratorNodePOJO> getAllNodes(V93kQuote parentObj,
                                                          QuoteLinePOJO line) {
        ArrayList<ConfiguratorNodePOJO> nodeList = null;
        if (parentObj != null) {
            nodeList = line.getItems();
        }
        return nodeList;
    }

    private static List<ConfiguratorNodePOJO> parseAllNodes(V93kQuote v93Obj,
                                                            String lineNum) {
        ArrayList<ConfiguratorNodePOJO> nodeList = null;
        ArrayList<QuoteLinePOJO> quoteLineListRef =
            v93Obj.getReferenceConfigurationLines();
        ArrayList<QuoteLinePOJO> quoteLineListTarget =
            v93Obj.getTargetConfigurationLines();
        if (lineNum != null && lineNum.equalsIgnoreCase("1")) {
            if (quoteLineListTarget != null &&
                quoteLineListTarget.size() > 0) {
                nodeList = quoteLineListTarget.get(0).getItems(); //First line
            }
            //}
        }
        if (lineNum != null && lineNum.equalsIgnoreCase("2")) {
            if (quoteLineListTarget != null &&
                quoteLineListTarget.size() > 1) {
                nodeList = quoteLineListTarget.get(1).getItems(); //2nd line
            }
            //  }
        }
        return nodeList;

    }

    private static V93kQuote populateQuoteDataOnObject(V93kQuote v93, Row r) {
        try {
            if (v93 != null && r != null) {
                //Create Config if it is null
                if (v93.getConfigObject() == null) {
                    Config config = new Config();
                    v93.setConfigObject(config);
                    config.setVersion("1.0");

                }

                if (v93.getConfigObject() != null) {
                    //Create a model bom and add it here
                    if (v93.getConfigObject().getModelbomObjectList() ==
                        null) {
                        String ruleSetVal = null;
                        if (v93.getInputParams() != null) {
                            ruleSetVal =
                                    v93.getInputParams().getRuleSetTopLevelChoice();
                        }
                        ModelBom mb = new ModelBom();
                        mb.setPricelist((String)r.getAttribute("Pricelistname"));
                        mb.setId(ruleSetVal);
                        Ruleset rf = new Ruleset();
                        rf.setId(v93.getInputParams().getRuleSetSecondLevelChoice());
                        mb.setRulesetObject(rf);

                        ArrayList<ModelBom> mbList = new ArrayList<ModelBom>();
                        mbList.add(mb);
                        v93.getConfigObject().setModelbomObjectList(mbList);
                    } else if (v93.getConfigObject().getModelbomObjectList() !=
                               null &&
                               !v93.getConfigObject().getModelbomObjectList().isEmpty()) {
                        String ruleSetVal = null;
                        if (v93.getInputParams() != null) {
                            ruleSetVal =
                                    v93.getInputParams().getRuleSetTopLevelChoice();
                        }
                        ArrayList<ModelBom> mbList =
                            v93.getConfigObject().getModelbomObjectList();
                        if (mbList != null && !mbList.isEmpty()) {
                            ModelBom mb = mbList.get(0);
                            mbList.remove(0);
                            mb.setPricelist((String)r.getAttribute("Pricelistname"));
                            mb.setId(ruleSetVal);
                            Ruleset rf = new Ruleset();
                            rf.setId(v93.getInputParams().getRuleSetSecondLevelChoice());
                            mb.setRulesetObject(rf);
                            mbList.add(0, mb);
                            v93.getConfigObject().setModelbomObjectList(mbList);
                        }
                    }
                }


                if (v93.getQheaderObject() == null) {
                    QHeader qheader = new QHeader();
                    //v93.setQheaderObject(qheader);

                    Customer cust = new Customer();
                    qheader.setCustomerObject(cust);
                    Contract contract = new Contract();
                    qheader.setContractObject(contract);
                    Deal deal = new Deal();
                    qheader.setDealObject(deal);
                    Salesteam salesteam = new Salesteam();
                    qheader.setSalesteamObject(salesteam);
                    v93.setQheaderObject(qheader);

                }
                QHeader qheader = v93.getQheaderObject();
                qheader.setQtitle((String)r.getAttribute("QuoteName"));

                if (qheader.getCustomerObject() != null) {
                    Customer cust = qheader.getCustomerObject();
                    cust.setCaddr1((String)r.getAttribute("Caddr1"));
                    cust.setCaddr2((String)r.getAttribute("Caddr2"));
                    cust.setCemail((String)r.getAttribute("Cemail"));
                    cust.setCphone((String)r.getAttribute("Cphone"));
                    cust.setCcontact((String)r.getAttribute("Attribute12"));
                    cust.setCnumber((String)r.getAttribute("Customernumber"));
                    cust.setGlobalcmpy((String)r.getAttribute("Saleschannel"));
                    cust.setCname((String)r.getAttribute("Customername"));
                    cust.setCemail((String)r.getAttribute("Attribute13"));
                    cust.setCphone((String)r.getAttribute("Attribute15"));
                }
                if (qheader.getContractObject() != null) {
                    Contract contract = qheader.getContractObject();
                    contract.setConid((String)r.getAttribute("Agrimentname"));
                    //contract.setConpay((String)r.getAttribute("Paymentterms"));
                    //contract.setConinco((String)r.getAttribute("Incoterms"));

                }
                if (qheader.getDealObject() != null) {
                    Deal deal = qheader.getDealObject();
                    deal.setDealid((String)r.getAttribute("Attribute8"));
                    deal.setSalesch((String)r.getAttribute("Saleschannel"));
                    BigDecimal quoteId =
                        (BigDecimal)r.getAttribute("QuoteNumber");
                    if (quoteId != null) {
                        deal.setQuoteid(quoteId.toString());
                    }

                    deal.setStatus((String)r.getAttribute("Status"));

                    deal.setSysid((String)r.getAttribute("Attribute2"));
                    deal.setCurrency((String)r.getAttribute("CurrencyCode"));

                    //deal.setDdiscount(r.getAttribute("Discount"));
                    deal.setDincoterm((String)r.getAttribute("Incoterms"));
                    deal.setDpayterm((String)r.getAttribute("Paymentterms"));
                    Date creationDate = (Date)r.getAttribute("CreationDate");
                    if (creationDate != null)
                        deal.setQdate(creationDate.toString());
                    Date expDate = (Date)r.getAttribute("QuoteExpirationDate");
                    if (expDate != null)
                        deal.setExpdate(expDate.toString());
                    Date orderedDate = (Date)r.getAttribute("OrderedDate");
                    if (orderedDate != null)
                        deal.setOdate(orderedDate.toString());
                    deal.setDordertype((String)r.getAttribute("Ordertypename"));
                }
                if (qheader.getSalesteamObject() != null) {
                    Salesteam st = qheader.getSalesteamObject();
                    st.setOu((String)r.getAttribute("Operatingunitname"));
                    st.setSrespo((String)r.getAttribute("Salesrepresentative"));
                    st.setSphone((String)r.getAttribute("Sphone"));
                    st.setSemail((String)r.getAttribute("Semail"));
                    st.setCsrrespo((String)r.getAttribute("Businesscentercsr"));
                    st.setCsrphone((String)r.getAttribute("Csrphone"));
                    st.setCsremail((String)r.getAttribute("Csremail"));
                }

            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }


        return v93;
    }

}
