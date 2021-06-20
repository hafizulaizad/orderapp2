package demo.ftmk.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import demo.ftmk.product.Product;

/**
 * Servlet implementation class CheckOutRedirectServlet
 */
@WebServlet("/CheckOutRedirectServlet")
public class CheckOutRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutRedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get orderedProducts from session
		HttpSession session = request.getSession(false);
		List orderedProducts = (ArrayList) session.getAttribute("orderList");
		
		// Forward servlet to zeroOrder.html if orderedProducts does not exist
		 if(orderedProducts == null){  
		        RequestDispatcher rd = request.getRequestDispatcher("demo/zeroOrder.html");  
		        rd.forward(request, response);  
		    }  
		    else{ 
		    Order o = new Order();
		    o.setOrderedProducts(orderedProducts);
		    OrderDataManager odm =new OrderDataManager();
		    
		    // Calculate total quantity and total order
			// Invoke the appropriate method from OrderDataManager
		    odm.processOrder(o.getOrderedProducts());
		    
			// Display details of order
		    PrintWriter writer = response.getWriter();
			writer.print("<html>");
			writer.print("Ordered Item : " + o.getOrderedProducts() +"<br>");
			writer.print("Tax:" + o.getServiceTax() + "<br>");
			writer.print("Total Amount: " + o.getTotalAmount());
			writer.print("Total Quantity: " + o.getTotalQuantity());
			writer.print("Date: " + o.getOrderDate());
			writer.print("</html>");
		    }
		
		 // Remove attribute from session
		 session.removeAttribute("orderList");
	}
}
