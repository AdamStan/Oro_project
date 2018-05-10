package oro_project.view.exceptions;

public class ProductNotFoundException extends Exception {

	private static final long serialVersionUID = -5539083891283103280L;
	private String nameOfProduct;

	public ProductNotFoundException(String message, String name){
		super(message);
		this.nameOfProduct = name;
	}

	@Override
	public String toString() {
		return "ProductNotFoundException nameOfProduct = " + nameOfProduct;
	}
}
