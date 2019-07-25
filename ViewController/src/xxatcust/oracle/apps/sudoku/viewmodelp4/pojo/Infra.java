package xxatcust.oracle.apps.sudoku.viewmodelp4.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "infra")
public class Infra {
    private List<ConfiguratorNodePOJO> allInfraItems;
    public Infra() {
        super();
    }

    public void setAllInfraItems(List<ConfiguratorNodePOJO> allInfraItems) {
        this.allInfraItems = allInfraItems;
    }
    @XmlElement(name = "item")
    public List<ConfiguratorNodePOJO> getAllInfraItems() {
        return allInfraItems;
    }
}
