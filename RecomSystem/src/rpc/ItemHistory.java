package rpc;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import db.MySQLConnection;
import entity.Item;

/**
 * Servlet implementation class ItemHistory
 */
@WebServlet("/history")
public class ItemHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String userId = request.getParameter("user_id");
		JSONArray array = new JSONArray();
		MySQLConnection connection = new MySQLConnection();
		Set<Item> items = connection.getFavoriteItems(userId); 
		connection.close();

		for (Item item : items) {

			JSONObject obj = item.toJSONObject(); 
			try {
				obj.append("favorite", true); 
			} catch (JSONException e) {
				e.printStackTrace(); 
			}
			array.put(obj);

		} 
		RpcHelper.writeJsonArray(response, array);

		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
/**
*		JSONObject input = RpcHelper.readJSONObject(request);
*		try {
*			String userId = input.getString("user_id");
*			Item item = RpcHelper.parseFavoriteItem(input.getJSONObject("favorite"));
*			
*			MySQLConnection connection = new MySQLConnection();
*			connection.setFavoriteItems(userId, item);
*			connection.close();
*			RpcHelper.writeJsonObject(response, new JSONObject().put("result", "SUCCESS"));
*		} catch (JSONException e) { 
*			e.printStackTrace(); 
*		}
*	}ss
*/
		JSONObject input = RpcHelper.readJSONObject(request);

		try { 
			String userId = input.getString("user_id"); 
			Item item = RpcHelper.parseFavoriteItem(input.getJSONObject("favorite"));

			MySQLConnection connection = new MySQLConnection(); 
			connection.setFavoriteItems(userId, item); 
			connection.close(); 
			RpcHelper.writeJsonObject(response, new JSONObject().put("result","SUCCESS"));

		} catch (JSONException e) { 
			e.printStackTrace(); 
		}

	}
		
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject input = RpcHelper.readJSONObject(request);

		try { 
			String userId = input.getString("user_id"); 
			String itemId = input.getJSONObject("favorite").getString("item_id");

			MySQLConnection connection = new MySQLConnection(); 
			connection.unsetFavoriteItems(userId, itemId); 
			connection.close(); 
			RpcHelper.writeJsonObject(response, new JSONObject().put("result","SUCCESS"));

		} catch (JSONException e) { 
			e.printStackTrace(); 
		}

	}

}
