package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.BookBean;
import bean.POItemBean;

public class ShoppingCartModel {

	Map<String, POItemBean> shoppingList;

	public ShoppingCartModel() {
		shoppingList = new HashMap<String, POItemBean>();
	}

	public Map<String, POItemBean> addToCart(String isbn, StoreModel storeModel) throws Exception {
		BookBean addedBook = storeModel.getBookDetails(isbn);
		if (!shoppingList.containsKey(isbn)) {
			POItemBean tempBean = new POItemBean(222, isbn, addedBook.getPrice(), 1);
			shoppingList.put(isbn, tempBean);
		} else {
			POItemBean tempBean = shoppingList.get(isbn);
			tempBean.setQuantity(tempBean.getQuantity() + 1);
			shoppingList.put(isbn, tempBean);
		}
		return shoppingList;
	}

	public Map<String, POItemBean> updateCart(Map<String, String[]> parametersMap) {
		for (String s : parametersMap.keySet()) {
			POItemBean POI = shoppingList.get(s);
			if (Integer.parseInt(parametersMap.get(s)[0]) <= 0) {
				shoppingList.remove(s);
			} else {
				POI.setQuantity(Integer.parseInt(parametersMap.get(s)[0]));
				shoppingList.put(s, POI);
			}
		}
		return shoppingList;
	}

	public List<POItemBean> getShoppingList() {
		ArrayList<POItemBean> result = new ArrayList<POItemBean>();
		for(POItemBean POI: shoppingList.values())
			result.add(POI);
		return result;
	}

	public int getSubTotal() {
		int total = 0;
		for (POItemBean POI : shoppingList.values()) {
			total += POI.getPrice() * POI.getQuantity();
		}
		return total;
	}
}
