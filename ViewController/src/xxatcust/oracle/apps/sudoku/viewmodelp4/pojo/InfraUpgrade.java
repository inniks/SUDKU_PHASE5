package xxatcust.oracle.apps.sudoku.viewmodelp4.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="infra-upgrade")
public class InfraUpgrade {
    List<ConfiguratorNodePOJO> infraUpgradeItems ;
    public InfraUpgrade() {
        super();
    }

    public void setInfraUpgradeItems(List<ConfiguratorNodePOJO> infraUpgradeItems) {
        this.infraUpgradeItems = infraUpgradeItems;
    }
    @XmlElement(name = "item")
    public List<ConfiguratorNodePOJO> getInfraUpgradeItems() {
        return infraUpgradeItems;
    }
}