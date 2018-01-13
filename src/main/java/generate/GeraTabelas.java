package generate;

import javax.persistence.Persistence;

public class GeraTabelas {

	public static void main(String[] args) {

		Persistence.createEntityManagerFactory("FinanceiroPU");

	}
}
