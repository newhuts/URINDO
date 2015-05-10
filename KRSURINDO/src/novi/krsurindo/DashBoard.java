package novi.krsurindo;

import java.util.ArrayList;
import java.util.HashMap;

import novi.krsurindo.adapter.NavDrawerListAdapter;
import novi.krsurindo.adapter.SessionManager;
import novi.krsurindo.admin.AcceptKrs;
import novi.krsurindo.admin.ListKHS;
import novi.krsurindo.mahasiswa.IsiKrs;
import novi.krsurindo.mahasiswa.MenuMahasiswa;
import novi.krsurindo.mahasiswa.MhsKhs;
import novi.krsurindo.mahasiswa.MhsKrs;
import novi.krsurindo.model.NavDrawerItem;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class DashBoard extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	SessionManager session;
	String npm, role;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard_nav);
		mTitle = mDrawerTitle = getTitle();
		
		session = new SessionManager(getApplicationContext());
		session.checkLogin();
		HashMap<String,String>user = session.getUserDetails();
		npm = user.get(SessionManager.KEY_NPM);
		role = user.get(SessionManager.KEY_AKSES);
		if (role != null) {
			if (role.equals("0")) {
				Mahasiswa();
			}else if (role.equals("1")) {
				Admin();
			}
		}else {
			Default();
		}
		
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_navigation_drawer, R.string.app_name, R.string.app_name ) {
					public void onDrawerClosed(View view) {
						getActionBar().setTitle(mTitle);
						invalidateOptionsMenu();
					}
					public void onDrawerOpened(View drawerView) {
						getActionBar().setTitle(mDrawerTitle);
						invalidateOptionsMenu();
					}
		};
		
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		if (savedInstanceState == null) {
			displayView(0);
		}
	}
	
	private void Default() {
		navMenuTitles  = new String[]{"Ada yang salah"};
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		navDrawerItems = new ArrayList<NavDrawerItem>();
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		navMenuIcons.recycle();
		
	}
	
	private void Admin() {
		navMenuTitles  = new String[]{"Accept krs", "Input KHS"};
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		navDrawerItems = new ArrayList<NavDrawerItem>();
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(4, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(5, -1)));
		navMenuIcons.recycle();
		
	}
	private void Mahasiswa(){
		navMenuTitles  = new String[]{""+npm, "Isi Krs", "Lihat KRS", "Lihat KHS","lima"};
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		navDrawerItems = new ArrayList<NavDrawerItem>();
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
		navMenuIcons.recycle();

	}
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			displayView(position);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.dash_board, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {
		case R.id.about:
			startActivity(new Intent(this, AboutMe.class));
			return true;
		case R.id.Setting:
			startActivity(new Intent(this, Setting.class));
			return true;
		case R.id.Logout:
			session.logoutUser();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.Logout).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		if (role != null) {
			if (role.equals("0")) {
				switch (position) {
				case 0:
					fragment = new MenuMahasiswa();
					break;
				case 1:
					fragment = new IsiKrs();
					break;
				case 2:
					fragment = new MhsKrs();
					break;
				case 3:
					fragment = new MhsKhs();
					break;
//				case 4:
//					fragment = new Logout();
				default:
					break;
				}
			}else if (role.equals("1")) {
				switch (position) {
				case 0:
					fragment = new AcceptKrs();
					break;
				case 1:
					fragment = new ListKHS();
					break;
				default:
					break;
				}
			}
		}else {
			switch (position) {
			case 0:
				break;
			default:
				break;
			}
		}
		
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			Log.e("MainActivity", "Error in creating fragment");
		}
	}
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
	    if ((keyCode == KeyEvent.KEYCODE_BACK))
	    {
	    	moveTaskToBack(true);
	    }
	    return super.onKeyDown(keyCode, event);
	}
}