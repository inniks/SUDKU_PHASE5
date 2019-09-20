package xxatcust.oracle.apps.sudoku.viewmodel.ui;

import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.AdditionalSoftToolsGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.DCScaleDPSGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.DiagAndCalibEquipmentGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.DigitalResourceGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.DockingGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.MixedSignalGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.RFResoucesGroup;
import xxatcust.oracle.apps.sudoku.viewmodel.ui.groups.SystemControllerGroup;
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
    
    SystemControllerGroup systemControllerGroup = null;

    DiagAndCalibEquipmentGroup diagAndCalibEquipmentGroup = null;
    
    DockingGroup dockingGroup = null;
    
    AdditionalSoftToolsGroup additionalSoftToolsGroup = null;

    MixedSignalGroup mixedSignalGroup = null;
    
    RFResoucesGroup rFResoucesGroup = null;
        
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

    public void setSystemControllerGroup(SystemControllerGroup systemControllerGroup) {
        this.systemControllerGroup = systemControllerGroup;
    }

    public SystemControllerGroup getSystemControllerGroup() {
        return systemControllerGroup;
    }

    public void setDiagAndCalibEquipmentGroup(DiagAndCalibEquipmentGroup diagAndCalibEquipmentGroup) {
        this.diagAndCalibEquipmentGroup = diagAndCalibEquipmentGroup;
    }

    public DiagAndCalibEquipmentGroup getDiagAndCalibEquipmentGroup() {
        return diagAndCalibEquipmentGroup;
    }

    public void setDockingGroup(DockingGroup dockingGroup) {
        this.dockingGroup = dockingGroup;
    }

    public DockingGroup getDockingGroup() {
        return dockingGroup;
    }

    public void setAdditionalSoftToolsGroup(AdditionalSoftToolsGroup additionalSoftToolsGroup) {
        this.additionalSoftToolsGroup = additionalSoftToolsGroup;
    }

    public AdditionalSoftToolsGroup getAdditionalSoftToolsGroup() {
        return additionalSoftToolsGroup;
    }

    public void setMixedSignalGroup(MixedSignalGroup mixedSignalGroup) {
        this.mixedSignalGroup = mixedSignalGroup;
    }

    public MixedSignalGroup getMixedSignalGroup() {
        return mixedSignalGroup;
    }

    public void setRFResoucesGroup(RFResoucesGroup rFResoucesGroup) {
        this.rFResoucesGroup = rFResoucesGroup;
    }

    public RFResoucesGroup getRFResoucesGroup() {
        return rFResoucesGroup;
    }
}
