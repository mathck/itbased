package at.ac.tuwien.imw.tables;

import java.util.Locale;

public class CPPITableDrawer {

	private static String format = "| %4d | %7.4f | %8.2f | %8.2f | %8.2f | %8.2f | %9.2f | %7.2f%% | %12.2f |%n";
	
	public static void Headlines() {
		System.out.format("+------+---------+----------+----------+----------+----------+-----------+----------+--------------+%n");
		System.out.format("|    t |   T_t,T |       Ft |       Ct |    X_r,t |    X_f,t |        St |    TSR_t |           Wt |%n");
		System.out.format("+------+---------+----------+----------|----------|----------|-----------+----------+--------------+%n");
	}
	
	public static void AddLine(int currentPeriod, double floor, double TtT, double cushion, double xrt, double xft, double stock, Double tsr, Double portfolio) {
		System.out.format(Locale.GERMAN, format, currentPeriod, TtT, floor, cushion, xrt, xft, stock, tsr != null ? tsr : 0, portfolio != null ? portfolio : "");
	}
	
	public static void CloseTable() {
		System.out.format("+------+---------+----------+----------+----------+----------+-----------+----------+--------------+%n");
	}
}