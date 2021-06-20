package demo.ftmk.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import demo.ftmk.product.Product;

/**
 * Servlet implementation class orderRedirectServlet
 */
@WebServlet("/OrderRedirectServlet")
public class OrderRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderRedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get orderedProducts from session and 
		// cast to List of OrderedProduct
		HttpSession session = request.getSession();  
		List orderItem = (List) session.getAttribute("orderList");
		
		Order o = new Order();
		o.setOrderedProducts(orderItem);
		
		// Validate list - instantiate new list if list is null
		if (orderItem == null) {
			orderItem = new ArrayList();
		} else {
			orderItem = orderItem;
		}
		// Get data from web form
			int productId = Integer.parseInt(request.getParameter("product"));
			int qtt = Integer.parseInt(request.getParameter("quantity"));
			
			// Wrap data into object of OrderedProduct
			Product p = new Product();
			p.setProductId(productId);
			
			OrderedProduct op = new OrderedProduct();
			
			op.setOrderedProduct(p);
			op.setQuantity(qtt);
			
			orderItem.add(op);
			
			// Add list to session
			request.setAttribute("orderList",orderItem);
			
			System.out.println(orderItem);
			// Redirect to success page
			response.sendRedirect("demo/orderRedirectForm.html");
		
	}

}
