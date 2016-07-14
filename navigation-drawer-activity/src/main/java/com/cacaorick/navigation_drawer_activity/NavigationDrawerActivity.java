package com.cacaorick.navigation_drawer_activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class NavigationDrawerActivity extends AppCompatActivity {
	protected OnContentViewSetListener mListener;
	protected DrawerLayout mDrawerLayout;
	protected NavigationView mNavigationView;
	protected CoordinatorLayout mBaseLayout;    // For show Snackbar
	protected Toolbar mToolbar;
	protected TextView mToolBarTitle;
	protected FloatingActionButton mFab;

	public interface OnContentViewSetListener {
		/**
		 * 用於設定 NavigationView 和 ToolBar
		 * 因為這些設定必須在 setContentView() 完成後才能作，因此用 Listener 來等待 setContentView() 完成
		 */
		void onContentViewSetted();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
			mDrawerLayout.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public void setContentView(@LayoutRes int layoutResID) {
		// 用 layout_navigation_drawer xml 建立一個 DrawerLayout，從裡面找出 content_layout 把傳進來的 layout 套進去
		DrawerLayout baseDrawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.layout_navigation_drawer, null);
		CoordinatorLayout contentLayout = (CoordinatorLayout) baseDrawerLayout.findViewById(R.id.content_layout);
		getLayoutInflater().inflate(layoutResID, contentLayout, true);

		// 設定 baseDrawerLayout 為 ContentView
		super.setContentView(baseDrawerLayout);

		// 設定 Toolbar 和 Navigation drawer
		setToolbar();
		setDrawerLayout();

		mNavigationView = (NavigationView) findViewById(R.id.nav_view);

		// 取得給 Snackbar 用的 root layout
		mBaseLayout = (CoordinatorLayout) findViewById(R.id.base_layout);

		// 呼叫子 Activity 中實現的 onContentViewSetted()
		mListener.onContentViewSetted();
	}

	private void setToolbar() {
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);
		this.setTitle(getString(R.string.app_name));
	}

	private void setDrawerLayout() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		mDrawerLayout.addDrawerListener(drawerToggle);
		drawerToggle.syncState();
	}

	@Override
	public void setTitle(CharSequence title) {
		super.setTitle(null);
		mToolBarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title_textview);
		mToolBarTitle.setText(title);
	}

	/**
	 * 設定 Navigation Drawer 的 Header layout
	 * @param layoutResID Header layout resource id
	 */
	public void setHeaderLayout(@LayoutRes int layoutResID) {
		mNavigationView.inflateHeaderView(layoutResID);
	}

	/**
	 * 設定 Menu 的內容
	 * @param menuResID 定義 menu 的 xml resource id
	 */
	public void setDrawerMenu(int menuResID) {
		mNavigationView.inflateMenu(menuResID);
	}

	/**
	 * 設定 NavigationView 的 OnNavigationItemSelectedListener
	 * @param listener 定義項目被選擇時要處理什麼
	 */
	public void setOnNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener listener) {
		mNavigationView.setNavigationItemSelectedListener(listener);
	}

	public void setNavigationCheckItem(int itemIndex) {
		mNavigationView.getMenu().getItem(itemIndex).setChecked(true);
	}

	/**
	 * 設定 FloatingActionButton
	 * @param iconResId Icon 的 Resource Id
	 * @param onClickListener 按下 FAB 時要做的事
	 */
	public void setFab(int iconResId, View.OnClickListener onClickListener) {
		mFab = (FloatingActionButton) findViewById(R.id.fab);
		if (mFab != null) {
			mFab.setVisibility(View.VISIBLE);
			mFab.setOnClickListener(onClickListener);
			mFab.setImageResource(iconResId);
		}
	}
}
