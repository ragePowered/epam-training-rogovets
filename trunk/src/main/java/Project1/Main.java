package Project1;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/7/14
 * Time: 11:40 PM
 */

import java.util.Formatter;
import java.util.TreeSet;

/**
 * Налоги. Определить множество и сумму налоговых выплат физического лица за год с учетом
 * доходов с основного и дополнительного мест работы, авторских вознаграждений,
 * продажи имущества, получения в подарок денежных сумм и имущества, переводов из-за границы,
 * льгот на детей и материальную помощь. Провести сортировку налогов по сумме.
 */

public class Main {
	public static void main(String[] args) {
		new TaxExtract().calculateAndShowTaxesExtract();
	}
}

class TaxExtract{
	private TreeSet<TaxEntity> taxes;

	TaxExtract(){{
		taxes  = new TreeSet<>();

		IncomeEntity mainEmployment = new MainEmployment(20000, "E-PAM Systems salary");
		IncomeEntity additionalEmployment = new AdditionalEmployment(7000, "IT Consultant salary");
		IncomeEntity privileges = new Privileges(85000, "Privileges for children");
		IncomeEntity royalties = new Royalties(8000, "Royalties");
		IncomeEntity transfers = new TransferFunds(5000, "Transfers from abroad");
		IncomeEntity realEstate = new RealEstate(140000, "Selling real estate");
		IncomeEntity gifts = new Gifts(20000, "Gifts");

		taxes.add(new TaxEntity(gifts, 0.0));
		taxes.add(new TaxEntity(additionalEmployment, 0.12));
		taxes.add(new TaxEntity(realEstate, 0.27));
		taxes.add(new TaxEntity(mainEmployment, 0.19));
		taxes.add(new TaxEntity(transfers, 0.1));
		taxes.add(new TaxEntity(royalties, 0.32));
		taxes.add(new TaxEntity(privileges, 0.0));


	}}

	public void calculateAndShowTaxesExtract(){
		Formatter formatter = new Formatter().format("%25s %15s %9s %10s", "Source", "Income", "Rate", "Tax");
		System.out.println(formatter.toString());
		int i = 0;
		int totalIncome = 0;
		double totalTaxes = 0.;
		for (TaxEntity each : taxes){
			totalIncome += each.income.amount;
			totalTaxes += each.rate * each.income.amount;
			System.out.println(++i + ". " + each);
		}
		System.out.printf("   TOTAL  --------------------  %10d %22.2f", totalIncome, totalTaxes);
	}

}