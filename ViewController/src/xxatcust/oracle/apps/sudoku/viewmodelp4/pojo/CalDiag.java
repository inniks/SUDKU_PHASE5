package xxatcust.oracle.apps.sudoku.viewmodelp4.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="cal-diag")
public class CalDiag {
    List<ConfiguratorNodePOJO> calDiagItems ;
    public CalDiag() {
        super();
    }

    public void setCalDiagItems(List<ConfiguratorNodePOJO> calDiagItems) {
        this.calDiagItems = calDiagItems;
    }
    @XmlElement(name="item")
    public List<ConfiguratorNodePOJO> getCalDiagItems() {
        return calDiagItems;
    }
}

