package org.dieschnittstelle.mobile.android.dataaccess.remote;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dieschnittstelle.mobile.android.dataaccess.model.DataItem;
import org.dieschnittstelle.mobile.android.dataaccess.model.TodoUser;

public class RemoteTodoUserAccessor implements TodoUserCRUDAccessor {

	protected static Logger logger = Logger
			.getLogger(RemoteTodoUserAccessor.class);
	
	
	/**
	 * the list of data items, note that the list is *static* as for each client
	 * request a new instance of this class will be created!
	 * 
	 * through WebApp registered Users
	 */
	private static List<TodoUser> itemlist;
	

	/**
	 * we assign the ids here
	 */
	private static int idCount = 0;
	
	public RemoteTodoUserAccessor() {
		itemlist = new ArrayList<TodoUser>();
		itemlist.add(new TodoUser("todo", "local"));
		itemlist.add(new TodoUser("a@a.com", "111111"));
		itemlist.add(new TodoUser("milasino@th-brandenburg.de", "123456"));
	}
	
	
	@Override
	public List<TodoUser> readAllItems() {
		logger.info("readAllItems(): " + itemlist);

		return itemlist;
	}

	@Override
	public TodoUser createItem(TodoUser item) {
		logger.info("createItem(): " + item);
		item.setId(idCount++);

		itemlist.add(item);
		return item;
	}

	@Override
	public boolean deleteItem(final int itemId) {
		logger.info("deleteItem(): " + itemId);

		boolean removed = itemlist.remove(new TodoUser() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 71193783355593985L;

			@Override
			public int getId() {
				return itemId;
			}
		});
		logger.info("deleteItem(): " + removed);

		return removed;
	}

	@Override
	public TodoUser updateItem(TodoUser item) {
		logger.info("updateItem(): " + item);
		TodoUser found = null;		
		for(TodoUser u: itemlist){
			if(u.getEmail().equals(item.getEmail()) && u.getPassword().equals(item.getPassword())){
				found = u;
				logger.info("updateItem(): found" + found.getEmail() + found.getId());
			}
		}
		
		if(found != null){
			logger.info("updateItem(): found" + found.getEmail() + found.getId());
			itemlist.get(itemlist.indexOf(found)).updateFrom(found);
			return found;
		}else {
			logger.info("updateItem(): not found" + item);
//			return item;
			return null;
		}
//		return itemlist.get(itemlist.indexOf(item)).updateFrom(item);
	}

	@Override
	public TodoUser readItem(int itemId) {
		logger.info("readTodoUser(): " + itemId);
		for(TodoUser u : itemlist){
			if(u.getId() == itemId){
				return u;
			}
		}
		return null;
	}
//	@Override
//	public boolean loggedIn(TodoUser item){
//		boolean loggedIn = false; 
//		TodoUser user = itemlist.get(itemlist.indexOf(item)).updateFrom(item);
//		if(user != null){
//			if(item.getEmail().equals(user.getEmail()) && item.getPassword().equals(user.getPassword())){
//				loggedIn = true; 
//			}
//		}
//		return loggedIn;
//	}

	/*
	 * std
	 */


	
//	@Override
//	public TodoUser login(TodoUser item) {
//		// TODO Auto-generated method stub
//		TodoUser user = itemlist.get(itemlist.indexOf(item));
//		if(loggedIn(user)){
//			return updateItem(user);
//		}
//		return null;
//	}
}
