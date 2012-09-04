package org.listActivityEx;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MyListActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] glossary = getResources().getStringArray(R.array.glossary);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.listview_item, glossary));
        ListView listView = getListView();
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, 
					int position, long id) {
				Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
				          Toast.LENGTH_SHORT).show();				
			}
		});        
    }
}