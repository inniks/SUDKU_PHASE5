package xxatcust.oracle.apps.sudoku.viewmodelp4.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "config")
public class Config {
    ArrayList<ModelBom> ModelbomObjectList;
    Pmf PmfObject;
    private String _version;

    public Config() {
        super();
    }

    public void setPmfObject(Pmf PmfObject) {
        this.PmfObject = PmfObject;
    }

    @XmlElement(name = "pmf")
    public Pmf getPmfObject() {
        return PmfObject;
    }

    public void setVersion(String _version) {
        this._version = _version;
    }

    @XmlAttribute(name = "version")
    public String getVersion() {
        return _version;
    }

//    public void setModelbomObject(ArrayList<ModelBom> ModelbomObject) {
//        this.ModelbomObjectList = ModelbomObject;
//    }
//
//    @XmlElement(name = "modelbom")
//    public ArrayList<ModelBom> getModelbomObject() {
//        return ModelbomObjectList;
//    }

    public void setModelbomObjectList(ArrayList<ModelBom> ModelbomObjectList) {
        this.ModelbomObjectList = ModelbomObjectList;
    }
    @XmlElement(name = "modelbom")
    public ArrayList<ModelBom> getModelbomObjectList() {
        return ModelbomObjectList;
    }
}
