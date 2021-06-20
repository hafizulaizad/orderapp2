package demo.ftmk.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

@WebServlet("/demo/orderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OrderServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get data from HTML form
		String selectedProduct = request.getParameter("product");
		String specifiedQuantity = request.getParameter("quantity");
		
		// Parse to processable type
		int code = Integer.parseInt(selectedProduct);
		int quantity = Integer.parseInt(specifiedQuantity);
		
		// Get price of product and product name
		double price = 0.00;
		String productName = "";
		switch (code) {
		case 101:
		productName = "McChicken Value Meal";
		price = 13.20;
		break;
		case 102:
		productName = "Smoky Grilled Beef Meal";
		price = 17.90;
		break;
		case 103: 
		productName = "Ayam Goreng McD Spicy Meal 2pcs";
		price = 16.95;
		break;
		case 104: 
		productName = "Spicy McChicken Deluxe Meal";
		price = 16.65;
		break;
		case 105: 
		productName = "Chicken McNuggets 6pcs Meal";
		price = 13.20;
		break;
		}
		
		// Calculate total and service tax
		double subTotal = quantity * price;
		double serviceTax = subTotal * 0.06;
		double total = subTotal + serviceTax;
		
		// Get writer
		PrintWriter writer = response.getWriter();
		
		// Display detail of amount
		writer.print("<html><h3>Details of Ordered Product</h3>");
		writer.print("Product: " + productName + "<br>");
		writer.print("Price per quantity: RM " + 
		String.format("%.2f", price) + "<br>");
		writer.print("Quantity: " + quantity + "<br><br>");
		writer.print("<b>Total Amount: RM " + 
		String.format("%.2f", total) + "</b><br>");
		writer.print("Service total: RM " + 
		String.format("%.2f", serviceTax) + "<br>");
		writer.print("Amount before tax: RM " + 
		String.format("%.2f", subTotal) + "<br></html>");
	}
}
