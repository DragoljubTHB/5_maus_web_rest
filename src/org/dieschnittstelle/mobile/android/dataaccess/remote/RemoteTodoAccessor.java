package org.dieschnittstelle.mobile.android.dataaccess.remote;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dieschnittstelle.mobile.android.dataaccess.model.DataItem;
import org.dieschnittstelle.mobile.android.dataaccess.model.Todo;
import org.dieschnittstelle.mobile.android.dataaccess.model.Todo.Rang;

public class RemoteTodoAccessor implements TodoCRUDAccessor{

	protected static Logger logger = Logger
			.getLogger(RemoteTodoAccessor.class);
	/**
	 * we assign the ids here
	 */
	private static int idCount = 0;
	/**
	 * the list of data items, note that the list is *static* as for each client
	 * request a new instance of this class will be created!
	 */
	private static List<Todo> itemlist = new ArrayList<Todo>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		add(new Todo(idCount++, "n"+idCount, "d"+idCount, "url", idCount, idCount, false, Rang.E));
		add(new Todo(idCount++, "n"+idCount, "d"+idCount, "url", idCount, idCount, false, Rang.E));
		add(new Todo(idCount++, "n"+idCount, "d"+idCount, "url", idCount, idCount, false, Rang.E));
		add(new Todo(idCount++, "n"+idCount, "d"+idCount, "url", idCount, idCount, false, Rang.E));
	}
	};


	
	public RemoteTodoAccessor() {
		logger.info("RemoteTodoAccessor : CTOR");
//		itemlist = new ArrayList<Todo>();
//		itemlist.add(new Todo(idCount++, "n"+idCount, "d"+idCount, "url", idCount, idCount, false, Rang.E));
//		itemlist.add(new Todo(idCount++, "n"+idCount, "d"+idCount, "url", idCount, idCount, false, Rang.E));
//		itemlist.add(new Todo(idCount++, "n"+idCount, "d"+idCount, "url", idCount, idCount, false, Rang.E));
//		itemlist.add(new Todo(idCount++, "n"+idCount, "d"+idCount, "url", idCount, idCount, false, Rang.E));
		
	}
	
	@Override
	public List<Todo> readAllItems() {
		logger.info("readAllItems(): " + itemlist);

		return itemlist;
	}

	@Override
	public Todo createItem(Todo item) {
		logger.info("createItem(): " + item);
		item.setId(idCount++);

		itemlist.add(item);
		return item;
	}

	@Override
	public boolean deleteItem(final int itemId) {
		logger.info("deleteItem(): " + itemId);

		boolean removed = itemlist.remove(new Todo() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 71193783355593985L;

			@Override
			public int getId() {
				return itemId;
			}
		});

		return removed;
	}

	@Override
	public Todo updateItem(Todo item) {
		logger.info("updateItem(): " + item);

		return itemlist.get(itemlist.indexOf(item)).updateFrom(item);
	}

	@Override
	public Todo readItem(int itemId) {
		for(Todo t : itemlist){
			if(t.getId() == itemId){
				return t;
			}
		}
		return null;
	}

	@Override
	public boolean deleteAllItems() {
		itemlist.clear();
		return true;
	}

}
