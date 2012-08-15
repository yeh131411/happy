package org.deadline;

//import java.util.ArrayList;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
//import android.database.Cursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SitesDBHlp extends SQLiteOpenHelper {
		private static final String DATABASE_NAME = "sites";
		private static final int DATABASE_VERSION = 1;
		private static final String TABLE_NAME = "sitesInfo";
		private static final String TABLE_CREATE = 
						"CREATE TABLE " + TABLE_NAME + " ( " +
						" name TEXT NOT NULL, " +
						" money TEXT NOT NULL, " +
						" amount TEXT); ";
		private static final String COL_name = "name";
		private static final String COL_money = "money";
		private static final String COL_amount = "amount";
		
		public SitesDBHlp(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, 
				int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}

		
		public void fillDB() {
			SQLiteDatabase db = getWritableDatabase();		
			ContentValues[] values = new ContentValues[3];
			for(int i=0; i<values.length; i++)
				values[i] = new ContentValues();
			
			values[0].put("name", "Salary");
			values[0].put("money", "2000");
			values[0].put("amount", "2000");
			
			values[1].put("name", "Dinner");
			values[1].put("money", "500");
			values[1].put("amount", "1500");
			
			values[2].put("name", "Lottery");
			values[2].put("money", "2000");
			values[2].put("amount", "3500");	
			
			for(ContentValues row : values){
				db.insert(TABLE_NAME, null, row);
			}	
			db.close();
		}
		
		 
		public ArrayList<Site> getAllSites(){
			SQLiteDatabase db = getReadableDatabase();
			String[] columns = {COL_name, COL_money, COL_amount};
			Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
			ArrayList<Site> sites = new ArrayList<Site>();
			while(cursor.moveToNext()){
				String name = cursor.getString(0);
				String money = cursor.getString(1);
				String amount = cursor.getString(2);			
				Site site = new Site( name, money, amount);
				sites.add(site);			
			}
			cursor.close();
			db.close();
			return sites;		
		}
		public long insertDB(Site site){
			SQLiteDatabase db = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(COL_name, site.getName());
			values.put(COL_money, site.getMoney());
			values.put(COL_amount, site.getAmount());
			long rowId = db.insert(TABLE_NAME, null, values);
			db.close();
			return rowId;
		}
		
}
