package xxatcust.oracle.apps.sudoku.viewmodel.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ruleset")
public class Ruleset {
    private String id ;
//    private String item;
//    public Ruleset() {
//        super();
//    }
//
//    public void setItem(String item) {
//        this.item = item;
//    }
//
//    public String getItem() {
//        return item;
//    }
  @XmlAttribute(name = "id")
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
