package novi.krsurindo.mahasiswa;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import novi.krsurindo.R;
import novi.krsurindo.adapter.JSONParser;
import novi.krsurindo.adapter.SessionManager;
import novi.krsurindo.helpers.FragmentCallback;
import novi.krsurindo.helpers.MHSHelper;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Transkip extends Fragment {

	ListView list;
	SessionManager session;
	String npm;
	ListAdapter listadapter;
	ArrayList<HashMap<String, String>>terimalist;
	JSONParser jParser = new JSONParser();
	JSONArray mahasiswa = null;
	double totalsemua;
	int totalsks;
	TextView tottal, counttranskip, totsks;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.transkip, container, false);
		list = (ListView)rootView.findViewById(R.id.transkiplist);
		tottal = (TextView)rootView.findViewById(R.id.detailtotalsemua);
		totsks = (TextView)rootView.findViewById(R.id.detailtotalsks);
		counttranskip = (TextView)rootView.findViewById(R.id.counttranskip);
		session = new SessionManager(getActivity());
		HashMap<String,String>user = session.getUserDetails();
        npm = user.get(SessionManager.KEY_NPM);
		loadkhs();
		return rootView;
		
	}
	private void loadkhs(){
		new MHSHelper(new FragmentCallback() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void onTaskDone(String result) {
				Log.e("", ""+result);
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
						
						listadapter = new SimpleAdapter(getActivity(), terimalist, R.layout.listtranskip, 
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
		}).execute(MHSHelper.url_transkip, npm);
	}
}
