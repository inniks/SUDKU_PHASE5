package xxatcust.oracle.apps.sudoku.viewmodel.ui;

import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.DCScaleDPSGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.DigitalResourceGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.SystemInfraGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.WtyTrainingSAndSGroup;

public class UiRoot {
    public UiRoot() {
        super();
    }
    
    SystemInfraGroup systemInfraGroup = null;

    DigitalResourceGroup digitalResourceGroup = null;
    
    DCScaleDPSGroup dCScaleDPSGroup = null;
        
    WtyTrainingSAndSGroup wtyTrainingSAndSGroup = null;

    public void setSystemInfraGroup(SystemInfraGroup systemInfraGroup) {
        this.systemInfraGroup = systemInfraGroup;
    }

    public SystemInfraGroup getSystemInfraGroup() {
        return systemInfraGroup;
    }

    public void setDigitalResourceGroup(DigitalResourceGroup digitalResourceGroup) {
        this.digitalResourceGroup = digitalResourceGroup;
    }

    public DigitalResourceGroup getDigitalResourceGroup() {
        return digitalResourceGroup;
    }

    public void setDCScaleDPSGroup(DCScaleDPSGroup dCScaleDPSGroup) {
        this.dCScaleDPSGroup = dCScaleDPSGroup;
    }

    public DCScaleDPSGroup getDCScaleDPSGroup() {
        return dCScaleDPSGroup;
    }

    public void setWtyTrainingSAndSGroup(WtyTrainingSAndSGroup wtyTrainingSAndSGroup) {
        this.wtyTrainingSAndSGroup = wtyTrainingSAndSGroup;
    }

    public WtyTrainingSAndSGroup getWtyTrainingSAndSGroup() {
        return wtyTrainingSAndSGroup;
    }
}
