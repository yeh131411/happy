package org.deadline;

//import android.R;
//import android.R;
//import android.R;
import java.util.ArrayList;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DeadlineActivity extends Activity {
    /** Called when the activity is first created. */
	private int Amount = 0;
	private EditText itemName;
	private EditText money;
	private Button in;
	private Button out;
	private Button clear;
	private SitesDBHlp dbHlp;
	private ArrayList<Site> sites;
	int index = 0;
	StringBuffer str = new StringBuffer("ItemName---Money---Amount\n");
	//private Button back;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViews();
        initDB();
    }
    private void initDB() {
		if(dbHlp == null)
			dbHlp = new SitesDBHlp(this);
		dbHlp.fillDB();	
		sites = dbHlp.getAllSites();
	}

    @Override
	public void onResume() {
		super.onResume();
		if(dbHlp == null)
			dbHlp = new SitesDBHlp(this); 
	}
    private void showSites(int index) {
    		TextView printData = (TextView)findViewById(R.id.printData);
			str.insert(str.length(), sites.get(index).getName()+"---"+sites.get(index).getMoney()+"---"+sites.get(index).getAmount()+"\n");
			printData.setText(str);
			Toast.makeText(DeadlineActivity.this, getString(R.string.fuck), 
					Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPause() {
		super.onPause();
		if(dbHlp != null){
			dbHlp.close(); 
			dbHlp = null;
		}
	}
	public void jumpToData(){
		setContentView(R.layout.data);
		Button back = (Button)findViewById(R.id.back);
		showSites(index);
		index++;
		back.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				jumpback();
			}
		});
	}
	public void jumpback(){
		setContentView(R.layout.main);
		findViews();
	}
    private void findViews(){
    	itemName = (EditText)findViewById(R.id.itemName);
    	money = (EditText)findViewById(R.id.money);
    	in = (Button)findViewById(R.id.in);
    	out = (Button)findViewById(R.id.out);
    	clear = (Button)findViewById(R.id.clear);
    	
    	in.setOnClickListener(new OnClickListener(){
    		@Override
    		public void onClick(View v){
    			String Name = itemName.getText().toString().trim();
    			String Money = money.getText().toString().trim();
    			Amount = Amount + Integer.parseInt(Money);
    			if(Name.length() <= 0 ){
					Toast.makeText(DeadlineActivity.this, getString(R.string.fuck), 
									Toast.LENGTH_SHORT).show();
					return;
				}
    			StringBuilder sb = new StringBuilder();
    			Site site = new Site(Name, Money, Integer.toString(Amount));
    			long rowId = dbHlp.insertDB(site);
    			if(rowId != -1){
					sb.append(Name+Money+Integer.toString(Amount));
						//Ãö³¬ÂÂªºActivity¡A
					jumpToData();
				}else{
					sb.append(getString(R.string.insert_fail));
				}
    			Toast.makeText(DeadlineActivity.this, sb, 
    							Toast.LENGTH_SHORT).show();
    			//setContentView(R.layout.data);
    			//return;
    		}
    	});
    	out.setOnClickListener(new OnClickListener(){
    		@Override
    		public void onClick(View v){
    			String Name = itemName.getText().toString().trim();
    			String Money = money.getText().toString().trim();
    			Amount = Amount - Integer.parseInt(Money);
    			if(Name.length() <= 0 ){
					Toast.makeText(DeadlineActivity.this, getString(R.string.fuck), 
									Toast.LENGTH_SHORT).show();
					return;
				}
    			StringBuilder sb = new StringBuilder();
    			Site site = new Site(Name, Money, Integer.toString(Amount));
    			long rowId = dbHlp.insertDB(site);
    			if(rowId != -1){
					sb.append(getString(R.string.insert_success));
					jumpToData();
				}else{
					sb.append(getString(R.string.insert_fail));
				}
    			Toast.makeText(DeadlineActivity.this, sb, 
    							Toast.LENGTH_SHORT).show();
    		}
    	});
    	clear.setOnClickListener(new OnClickListener(){
    		@Override
			public void onClick(View v) {
				itemName.setText("");
				money.setText("");
			}

    	});
    	/*back.setOnClickListener(new OnClickListener(){
    		@Override
    		public void onClick(View v){
    			setContentView(R.layout.main);
    		}
    	});*/
    }
}