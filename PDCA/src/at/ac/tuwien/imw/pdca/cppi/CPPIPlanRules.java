package at.ac.tuwien.imw.pdca.cppi;

import at.ac.tuwien.imw.pdca.PlanningRules;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 * Nr 1g
 * Name: Configure Basic Variables
 * Description: Setting of all relevant variables which are needed for further calculations
 * Variables: d, m, R_0
 * @author mathc_000
 *
 */
public class CPPIPlanRules implements PlanningRules<BigDecimal> {
	
	private BigDecimal INITIAL_FLOOR = new BigDecimal(100);
	private BigDecimal INITIAL_TtT = new BigDecimal(1);
	
	@Override
	public BigDecimal applyPlanningRules() { 
		
		CPPIService service = CPPIService.getInstance();
		CPPIPlanConfiguration conf = service.getPlanConfiguration();

		//-------------------------------------------------------
		// SET OBJECTIVE + CALCULATE FLOOR
		//-------------------------------------------------------
		//Bitte schauen, ob es die Klasse Objective �berhaupt noch braucht.
		//Ich bin mir nicht sicher, und habs deshalb drinnen gelassen.
		//Meiner Meinung nach reicht es, wenn man 
		// if( service.getCurrentPeriod() == 0 ){}
		//statt der derzeitige IF Funktion pr�ft und dann, wie im ELSE Pfad
		// conf.setFloor(INITIAL_FLOOR);
		//setzt.
		// TODO SET OBJECTIVE + CALCULATE FLOOR
		
		CPPIObjective objective = CPPIObjective.getInstance();
		if(objective.getObjectiveSetting().equals(null)) {
			// set default
			objective.setObjectiveSetting(INITIAL_FLOOR);
		}
		else {
			// calc =(Ft)/((1+R0)^T0T=1)
			BigDecimal floor;
			int days = service.getPlanConfiguration().getRisklessAssetLastDays();
			
			// calculate current TtT
			BigDecimal currentT = new BigDecimal(1);
			for(int i = 0; i < service.getCurrentPeriod(); i++)
				currentT = currentT.subtract(INITIAL_TtT.divide(new BigDecimal(days)));
			
			double base = (service.getPlanConfiguration().
					getRisklessAssetInterest().doubleValue() + 1);
			double exp = currentT.doubleValue();
			
			// calculate floor
			floor = INITIAL_FLOOR.divide(new BigDecimal(Math.pow(base, exp)));
			conf.setFloor(floor);
		}
		
		return null;
	}
}