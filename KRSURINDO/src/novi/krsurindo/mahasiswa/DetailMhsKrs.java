package novi.krsurindo.mahasiswa;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import novi.krsurindo.DashBoard;
import novi.krsurindo.R;
import novi.krsurindo.adapter.JSONParser;
import novi.krsurindo.helpers.FragmentCallback;
import novi.krsurindo.helpers.MHSHelper;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class DetailMhsKrs extends Activity {

	ListView list;
	ListAdapter listadapter;
	ArrayList<HashMap<String, String>>terimalist;
	TextView statkrs, smtkrs, totmk, totkrs;
	String npm, smt;
	JSONParser jParser = new JSONParser();
	JSONArray mahasiswa = null;
	int totall;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.krsmhs);
		ActionBar actionBar = getActionBar();
	    actionBar.setHomeButtonEnabled(true);
	    actionBar.setIcon(R.drawable.ic_ab_back_holo_dark_am);
		Intent in = getIntent();
		npm = in.getStringExtra("npm");
		smt = in.getStringExtra("smt");
		statkrs = (TextView)findViewById(R.id.statuskrs);
		smtkrs = (TextView)findViewById(R.id.krssmts);
		totmk = (TextView)findViewById(R.id.totmks);
		totkrs = (TextView)findViewById(R.id.totkrss);
		list = (ListView)findViewById(R.id.mhslistkrss);
		loadmhs();
		
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
	
	private void loadmhs(){
		new MHSHelper(new FragmentCallback() {		
			@Override
			public void onTaskDone(String result) {
				try {
					JSONObject json = new JSONObject(result);
					int a = json.getInt("success");
					if (a == 1) {
						mahasiswa = json.getJSONArray("mahasiswa");
						for (int i = 0; i < mahasiswa.length(); i++) {
							JSONObject c = mahasiswa.getJSONObject(i);
							String npms = c.getString("npm");
							String namas = c.getString("nama");
							String smts = c.getString("smt");
							
							smtkrs.setText(smts);
							loadmatkul();
						}
					}
				}catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}).execute(MHSHelper.url_mhs, npm);
	}
	
	private void loadmatkul(){
		new MHSHelper(new FragmentCallback() {
			@SuppressWarnings("unchecked")
			@Override
			public void onTaskDone(String result) {
				terimalist = new ArrayList<HashMap<String, String>>();
				try {
					System.out.println(""+result);
					JSONObject json = new JSONObject(result);
					int a = json.getInt("success");
					if (a == 1) {
						mahasiswa = json.getJSONArray("matkul");
						for (int i = 0; i < mahasiswa.length(); i++) {
							JSONObject c = mahasiswa.getJSONObject(i);
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("id", c.getString("idm"));
							map.put("kode_mk", c.getString("kodemk"));
							map.put("mk", c.getString("mk"));
							map.put("sks", c.getString("sks"));
							map.put("smt", c.getString("smt"));
							terimalist.add(map);
							String krss = c.getString("status");
							System.out.println(krss);
							if (krss.equals("0")) {
								statkrs.setText("Sedang di Proses");
								statkrs.setTextColor(getResources().getColorStateList(R.color.Gold));
							}else {
								statkrs.setText("Diterima");
								statkrs.setTextColor(getResources().getColorStateList(R.color.urindobiru));
							}
						}
						listadapter = new SimpleAdapter(DetailMhsKrs.this, terimalist, R.layout.listmatkul, 
								new String[] {"id", "kode_mk", "mk", "sks", "smt"}, 
								new int[] {R.id.idmk, R.id.kdmk,R.id.mkmk,R.id.sksmk});
						list.setAdapter(listadapter);
						
						//list ukuran
						for(int i=0;i<list.getCount();i++) {
						   ViewGroup.LayoutParams params = list.getLayoutParams();
						    params.height = 76 * list.getCount();
						    list.setLayoutParams(params);
						    list.requestLayout();
						}
						
						//list total
						int childCount = list.getCount();
						int totali = 0;
					    for (int i = 0; i < childCount; i++) {
					    	HashMap<String, Object> obj =(HashMap<String, Object>) listadapter.getItem(i);
				            String  sks = (String) obj.get("sks");
				            int oi = Integer.parseInt(sks);
				            totali += oi;
				            totall = totali;
					    }
					    
					    totmk.setText(""+list.getCount());
						totkrs.setText(""+totall);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}).execute(MHSHelper.url_krs, npm, smt);
	}
}
