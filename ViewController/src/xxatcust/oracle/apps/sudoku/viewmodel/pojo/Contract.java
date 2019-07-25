package xxatcust.oracle.apps.sudoku.viewmodel.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contract")
public class Contract {
    private String conid;
    public Contract() {
        super();
    }

    public void setConid(String conid) {
        this.conid = conid;
    }

    public String getConid() {
        return conid;
    }
}
