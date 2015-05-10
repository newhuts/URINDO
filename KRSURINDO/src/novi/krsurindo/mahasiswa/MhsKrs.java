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
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MhsKrs extends Fragment{

	ListAdapter listadapter;
	ArrayList<HashMap<String, String>>terimalist;
	JSONParser jParser = new JSONParser();
	JSONArray mahasiswa = null;
	ListView list;
	SessionManager session;
	String npm;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
				View rootView = inflater.inflate(R.layout.admin_krs, container, false);
				list = (ListView)rootView.findViewById(R.id.adminkrslist);
				session = new SessionManager(getActivity());
				HashMap<String,String>user = session.getUserDetails();
		        npm = user.get(SessionManager.KEY_NPM);
		        loadlistkrs();
		        list.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						String smt = ((TextView)view.findViewById(R.id.khslistsmt)).getText().toString();
						Intent in = new Intent(getActivity(), DetailMhsKrs.class);
						in.putExtra("npm", npm);
						in.putExtra("smt", smt);
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
							map.put("smt", c.getString("smt"));
							terimalist.add(map);
						}
						listadapter = new SimpleAdapter(getActivity(), terimalist, R.layout.listsmtkrs, 
								new String[] {"smt"}, 
								new int[] {R.id.khslistsmt});
						list.setAdapter(listadapter);
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		}).execute(MHSHelper.url_krslist, npm);
	}
}
