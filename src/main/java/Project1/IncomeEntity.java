package Project1;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/8/14
 * Time: 1:09 AM
 */

import java.util.Formatter;

/**
 * Налоги. Определить множество и сумму налоговых выплат физического лица за год с учетом
 * доходов с основного и дополнительного мест работы, авторских вознаграждений,
 * продажи имущества, получения в подарок денежных сумм и имущества, переводов из-за границы,
 * льгот на детей и материальную помощь. Провести сортировку налогов по сумме.
 */

abstract public class IncomeEntity {
	protected String sourceName;
	protected Integer amount;

	protected IncomeEntity(Integer amount, String sourceName) {
		this.amount = amount;
		this.sourceName = sourceName;
	}

	public Integer getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return new Formatter().format("%27s  | %8d  | ", sourceName, amount).toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof IncomeEntity)) return false;

		IncomeEntity that = (IncomeEntity) o;

		if (!amount.equals(that.amount)) return false;
		if (!sourceName.equals(that.sourceName)) return false;

		return true;
	}

}

class MainEmployment extends IncomeEntity{
	MainEmployment(Integer amount, String sourceName) {
		super(amount, sourceName);
	}
}

class AdditionalEmployment extends IncomeEntity{
	AdditionalEmployment(Integer amount, String sourceName) {
		super(amount, sourceName);
	}
}

class Royalties extends IncomeEntity{
	Royalties(Integer amount, String sourceName) {
		super(amount, sourceName);
	}
}

class RealEstate extends IncomeEntity{
	RealEstate(Integer amount, String sourceName) {
		super(amount, sourceName);
	}
}

class Gifts extends IncomeEntity{
	Gifts(Integer amount, String sourceName) {
		super(amount, sourceName);
	}
}

class TransferFunds extends IncomeEntity{
	TransferFunds(Integer amount, String sourceName) {
		super(amount, sourceName);
	}
}

class Privileges extends IncomeEntity{
	Privileges(Integer amount, String sourceName) {
		super(amount, sourceName);
	}
}