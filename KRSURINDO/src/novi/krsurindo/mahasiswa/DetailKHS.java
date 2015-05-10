package novi.krsurindo.mahasiswa;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import novi.krsurindo.R;
import novi.krsurindo.adapter.JSONParser;
import novi.krsurindo.helpers.FragmentCallback;
import novi.krsurindo.helpers.MHSHelper;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class DetailKHS extends Activity{

	String npm, smt;
	ListView list;
	ListAdapter listadapter;
	ArrayList<HashMap<String, String>>terimalist;
	JSONParser jParser = new JSONParser();
	JSONArray mahasiswa = null;
	double totalsemua;
	int totalsks;
	TextView tottal, counttranskip, totsks;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detailkhs);
		ActionBar actionBar = getActionBar();
	    actionBar.setHomeButtonEnabled(true);
	    actionBar.setIcon(R.drawable.ic_ab_back_holo_dark_am);
		Intent in  = getIntent();
		npm = in.getStringExtra("npm");
		smt = in.getStringExtra("smt");
		list = (ListView)findViewById(R.id.detaillistkhs);
		tottal = (TextView)findViewById(R.id.detailtotalsemua);
		totsks = (TextView)findViewById(R.id.detailtotalsks);
		counttranskip = (TextView)findViewById(R.id.totinpmk);
		TextView smttxt = (TextView)findViewById(R.id.detailkhssmt);
		smttxt.setText(smt);
		loadkhs();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
            finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
	
	private void loadkhs(){
		new MHSHelper(new FragmentCallback() {
			
			@SuppressLint("DefaultLocale") @SuppressWarnings("unchecked")
			@Override
			public void onTaskDone(String result) {
//				Log.e("", ""+result);
				terimalist = new ArrayList<HashMap<String, String>>();
				try {
					JSONObject json = new JSONObject(result);
					int a = json.getInt("success");
					if (a == 1) {
						mahasiswa = json.getJSONArray("transkip");
						double totall = 0;
						for (int i = 0; i < mahasiswa.length(); i++) {
							JSONObject c = mahasiswa.getJSONObject(i);
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("kodemk", c.getString("kodemk"));
							map.put("mk", c.getString("mk"));
							String sks = c.getString("sks");
							String nilai = c.getString("nilai");
							map.put("sks", sks);
							if (nilai.equals("4")) {
								map.put("nilai", "A");
							}else if (nilai.equals("3")) {
								map.put("nilai", "B");
							}else if (nilai.equals("2")) {
								map.put("nilai", "C");
							}else if (nilai.equals("1")) {
								map.put("nilai", "D");
							}else if (nilai.equals("0")) {
								map.put("nilai", "E");
							}
							double as = Double.parseDouble(sks);
							double bs = Double.parseDouble(nilai);
							double total = as * bs;
							map.put("hasil", ""+total);
							terimalist.add(map);
							
							totall += total;
							totalsemua = totall;
							
						}
						
						listadapter = new SimpleAdapter(DetailKHS.this, terimalist, R.layout.listtranskip, 
								new String[] {"kodemk", "mk", "sks", "nilai", "hasil"}, 
								new int[] {R.id.kdkhs, R.id.mkkhs,R.id.skskhs,R.id.khsnilai, R.id.khsakhir});
						list.setAdapter(listadapter);
						
						counttranskip.setText(""+list.getCount());
						
						//ukuran list
						for(int i=0;i<list.getCount();i++)
						{
						   ViewGroup.LayoutParams params = list.getLayoutParams();
						    params.height = 76 * list.getCount();
						    list.setLayoutParams(params);
						    list.requestLayout();
						}
						
						//total sks
						int childCount = list.getCount();
						int totalskss = 0;
						for (int i = 0; i < childCount; i++)
					    {
					    	HashMap<String, Object> obj =(HashMap<String, Object>) listadapter.getItem(i);
				            String  sks = (String) obj.get("sks");
				            int oi = Integer.parseInt(sks);
				            totalskss += oi;
				            totalsks = totalskss;
					    }
						double akhir = totalsemua / totalsks;
						String results = String.format("%.2f", akhir);
						tottal.setText(results);
						totsks.setText(""+totalsks);
						
						
						
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		}).execute(MHSHelper.url_listtranskipsmt, npm, smt);
	}
}
