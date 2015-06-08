package com.example.tabtemplate;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity implements OnClickListener,
		OnPageChangeListener {

	private LinearLayout tab01;
	private LinearLayout tab02;
	private LinearLayout tab03;
	private LinearLayout tab04;

	private ImageView imgMessage;
	private ImageView imgFriend;
	private ImageView imgContact;
	private ImageView imgSetting;

	private ViewPager viewpager;
	private FragmentManager manager;

	// private FragmentPagerAdapter adapter;
	private MyAdapter adapter;

	private List<Fragment> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
		setListener();
	}

	private void setListener() {
		tab01.setOnClickListener(this);
		tab02.setOnClickListener(this);
		tab03.setOnClickListener(this);
		tab04.setOnClickListener(this);
		viewpager.setOnPageChangeListener(this);
	}

	private void initView() {
		tab01 = (LinearLayout) findViewById(R.id.id_tab01);
		tab02 = (LinearLayout) findViewById(R.id.id_tab02);
		tab03 = (LinearLayout) findViewById(R.id.id_tab03);
		tab04 = (LinearLayout) findViewById(R.id.id_tab04);

		imgMessage = (ImageView) findViewById(R.id.img_tab01);
		imgFriend = (ImageView) findViewById(R.id.img_tab02);
		imgContact = (ImageView) findViewById(R.id.img_tab03);
		imgSetting = (ImageView) findViewById(R.id.img_tab04);

		viewpager = (ViewPager) findViewById(R.id.viewpager);

		// 将fragment装入到ArrayList中
		list = new ArrayList<Fragment>();
		Fragment message = new MessageFragment();
		Fragment friend = new FriendFragment();
		Fragment contact = new ContactFragment();
		Fragment setting = new SettingFragment();
		list.add(message);
		list.add(friend);
		list.add(contact);
		list.add(setting);

		manager = getSupportFragmentManager();

		// adapter = new FragmentPagerAdapter(manager) {
		//
		// @Override
		// public int getCount() {
		// // TODO Auto-generated method stub
		// return list.size();
		// }
		//
		// @Override
		// public Fragment getItem(int position) {
		// // TODO Auto-generated method stub
		// return list.get(position);
		// }
		// };
		//
		// viewpager.setAdapter(adapter);

		// viewpager.setOnPageChangeListener(new OnPageChangeListener() {
		//
		// @Override
		// public void onPageSelected(int arg0) {
		// // TODO Auto-generated method stub
		// setTab(arg0);
		// }
		//
		// @Override
		// public void onPageScrolled(int arg0, float arg1, int arg2) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void onPageScrollStateChanged(int arg0) {
		// // TODO Auto-generated method stub
		//
		// }
		// });

		adapter = new MyAdapter(manager);
		viewpager.setAdapter(adapter);
		adapter.notifyDataSetChanged();

		setTab(0); // 设置第一个tab为亮色

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.id_tab01:
			setSelected(0);
			break;

		case R.id.id_tab02:
			setSelected(1);
			break;
		case R.id.id_tab03:
			setSelected(2);
			break;
		case R.id.id_tab04:
			setSelected(3);
			break;
		}
	}

	/**
	 * 设置viewpager位置为i的内容区域
	 * 
	 * @param i
	 */
	private void setSelected(int i) {
		setTab(i);
		// viewpager.setCurrentItem(i); //默认会有滑动的动画效果
		viewpager.setCurrentItem(i, false); // 取消滑动动画效果
	}

	public class MyAdapter extends FragmentPagerAdapter {

		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int postion) {
			return list.get(postion);
		}

		@Override
		public int getCount() {
			return list.size();
		}

	}

	@Override
	public void onPageScrollStateChanged(int position) {

	}

	@Override
	public void onPageScrolled(int position, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int position) {
		setTab(position);
	}

	/**
	 * 将选中的tab设置为亮色
	 * 
	 * @param position
	 */
	private void setTab(int position) {

		resetImgs();

		switch (position) {
		case 0:
			imgMessage.setImageResource(R.drawable.tab_messages_pressed);
			break;
		case 1:
			imgFriend.setImageResource(R.drawable.tab_friends_pressed);
			break;
		case 2:
			imgContact.setImageResource(R.drawable.tab_contact_pressed);
			break;
		case 3:
			imgSetting.setImageResource(R.drawable.tab_settings_pressed);
			break;
		}
	}

	/**
	 * 重置所有tab的颜色为暗色
	 */
	private void resetImgs() {
		imgMessage.setImageResource(R.drawable.tab_messages_normal);
		imgFriend.setImageResource(R.drawable.tab_friends_normal);
		imgContact.setImageResource(R.drawable.tab_contact_normal);
		imgSetting.setImageResource(R.drawable.tab_settings_normal);
	}
}
