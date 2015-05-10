package novi.krsurindo.admin;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import novi.krsurindo.R;
import novi.krsurindo.adapter.JSONParser;
import novi.krsurindo.helpers.FragmentCallback;
import novi.krsurindo.helpers.MHSHelper;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ListKHS extends Fragment {

	ListAdapter listadapter;
	ArrayList<HashMap<String, String>>terimalist;
	JSONParser jParser = new JSONParser();
	JSONArray mahasiswa = null;
	ListView krslist;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.admin_krs, container, false);
			krslist = (ListView)rootView.findViewById(R.id.adminkrslist);
			loadlistkrs();
			krslist.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					String npms = ((TextView)view.findViewById(R.id.krsnpm)).getText().toString();
					Intent in = new Intent(getActivity(), InputKHS.class);
					in.putExtra("npm", npms);
					startActivity(in);
				}
			});
			return rootView;
	}
	private void loadlistkrs(){
		new MHSHelper(new FragmentCallback() {
			@Override
			public void onTaskDone(String result) {
//				Log.e("", ""+result);
				terimalist = new ArrayList<HashMap<String, String>>();
				try {
					JSONObject json = new JSONObject(result);
					int a = json.getInt("success");
					if (a == 1) {
						mahasiswa = json.getJSONArray("krs");
						for (int i = 0; i < mahasiswa.length(); i++) {
							JSONObject c = mahasiswa.getJSONObject(i);
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("npm", c.getString("npm"));
							map.put("nama", c.getString("nama"));
							map.put("smt", c.getString("smt"));
							map.put("sum(matkul.sks)", c.getString("sum(matkul.sks)"));
							terimalist.add(map);
						}
						listadapter = new SimpleAdapter(getActivity(), terimalist, R.layout.listkrs, 
								new String[] {"npm", "nama", "smt", "sum(matkul.sks)"}, 
								new int[] {R.id.krsnpm, R.id.krsnama,R.id.krssmt,R.id.krstotal});
						krslist.setAdapter(listadapter);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}).execute(MHSHelper.url_listkrs, "1");
	}
}
