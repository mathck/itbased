package at.ac.tuwien.imw.pdca.cppi;

import at.ac.tuwien.imw.pdca.CheckProcess;
import at.ac.tuwien.imw.pdca.Deviation;
import at.ac.tuwien.imw.pdca.MeasuredPerformanceValue;
import at.ac.tuwien.imw.pdca.ObjectiveSetting;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

import java.math.BigDecimal;

/**
 * Nr 3
 * Name: Check
 * @author mathc_000
 *
 */
public class CPPICheckProcess extends CheckProcess<BigDecimal> {

    CPPIService service = CPPIService.getInstance();

    public void run(){
        this.objectiveSetting = new CPPIObjective();
        objectiveSetting.setObjectiveSetting(calculateFloor());
        
        this.checkingRules = new CPPICheckRules();
        this.checkingRules.applyCheckingRules();
        
        this.performanceValue = null;
    }

    @Override
    public Deviation<BigDecimal> getCheckResult(ObjectiveSetting<BigDecimal> objective, MeasuredPerformanceValue<BigDecimal> performanceMeasureValue) {
        return null;
    }

    @Deprecated
    private BigDecimal calculateFloor(){
        BigDecimal floor = service.getPlanConfiguration().getFloor();
        BigDecimal risklessAsset = service.getPlanConfiguration().getRisklessAssetPercent();
        Integer daysInYear = service.getPlanConfiguration().getRisklessAssetLastDays();
        BigDecimal risklessInterest = service.getPlanConfiguration().getRisklessAssetPercent();
        Integer currentDay = service.getCurrentPeriod();

        BigDecimal calculatedFloor=risklessAsset.divide(new BigDecimal (daysInYear-currentDay));
        calculatedFloor = calculatedFloor.add(new BigDecimal(1));
        calculatedFloor = new BigDecimal(1).divide(calculatedFloor);
        calculatedFloor.pow(daysInYear-currentDay).multiply(floor);

        return calculatedFloor;
    }
}