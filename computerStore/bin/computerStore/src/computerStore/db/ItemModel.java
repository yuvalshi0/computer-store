package computerStore.db;

public class ItemModel {
	
	private int amountInStock;
	private final int catalogNumber;
	private int priceForUnit;
	private String name;
	
	public ItemModel(int catalogNumber, int priceForUnit, int amountInStock, String name) {
		this.name = name;
		this.priceForUnit = priceForUnit;
		this.catalogNumber = catalogNumber;
		this.amountInStock = amountInStock;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPriceForUnit() {
		return priceForUnit;
	}
	
	public int getCatalogNumber() {
		return catalogNumber;
	}

	public int getAmountInStock() {
		return amountInStock;
	}

	public void setAmountInStock(int amountInStock) {
		this.amountInStock = amountInStock;
	}
}
