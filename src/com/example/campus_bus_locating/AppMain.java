package com.example.campus_bus_locating;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class AppMain extends Activity {
	
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		ListView mListView = (ListView)findViewById(R.id.listView); 
		// 添加ListItem，设置事件响应    
		mListView.setAdapter(new DemoListAdapter());
		
        mListView.setOnItemClickListener(new OnItemClickListener() {  
            public void onItemClick(AdapterView<?> arg0, View v, int index, long arg3) {  
            	onListItemClick(index);
            	
        		
            }  
        });  
    }

    void onListItemClick(int index) {
		Intent intent = null;
		intent = new Intent(AppMain.this, demos[index].demoClass);
		this.startActivity(intent);
		
    		
    }
	
	public static final DemoInfo[] demos = {
		new DemoInfo(R.string.demo_title_route_one, R.string.demo_desc_route_one,
				    RouteoneActivity.class),
        new DemoInfo(R.string.demo_title_route_two, R.string.demo_desc_route_two,
        		    RoutetwoActivity.class),
        new DemoInfo(R.string.demo_title_route_three, R.string.demo_desc_route_three,
        		    RouteoneActivity.class),
        new DemoInfo(R.string.demo_title_route_four, R.string.demo_desc_route_four,
        		    RouteoneActivity.class),
        new DemoInfo(R.string.demo_title_route_five, R.string.demo_desc_route_five,
        		    RouteoneActivity.class),
        new DemoInfo(R.string.demo_title_route_six, R.string.demo_desc_route_six,
        		    RouteoneActivity.class),
        new DemoInfo(R.string.demo_title_route_seven, R.string.demo_desc_route_seven,
        		    RouteoneActivity.class),
        new DemoInfo(R.string.demo_title_route_eight, R.string.demo_desc_route_eight,
        		    RouteoneActivity.class),
	};
	
	@Override
	protected void onResume() {
	   // DemoApplication app = (DemoApplication)this.getApplication();
	   
		/*if (!app.m_bKeyRight) {
            
		}
		else{
			text.setTextColor(Color.RED);
			text.setText("欢迎使用山东大学校车定位查询（APP在测试阶段，敬请谅解!）");
		}**/
		super.onResume();
	}

	@Override
	// 建议在APP整体退出之前调用MapApi的destroy()函数，不要在每个activity的OnDestroy中调用，
    // 避免MapApi重复创建初始化，提高效率
	protected void onDestroy() {
	    DemoApplication app = (DemoApplication)this.getApplication();
		if (app.mBMapMan != null) {
			app.mBMapMan.destroy();
			app.mBMapMan = null;
		}
		super.onDestroy();
		System.exit(0);
	}
	
	public   class DemoListAdapter extends BaseAdapter {
		public DemoListAdapter() {
			super();
		}

		@Override
		public View getView(int index, View convertView, ViewGroup parent) {
			convertView = View.inflate(AppMain.this, R.layout.demo_info_item, null);
			TextView title = (TextView)convertView.findViewById(R.id.title);
			TextView desc = (TextView)convertView.findViewById(R.id.desc);
			/*if ( demos[index].demoClass == NaviDemo.class  
					|| demos[index].demoClass == CloudSearchDemo.class 
					|| demos[index].demoClass == ShareDemo.class 
					){
				title.setTextColor(Color.YELLOW);
				desc.setTextColor(Color.YELLOW);
			}*/
			title.setText(demos[index].title);
			desc.setText(demos[index].desc);
			return convertView;
		}
		@Override
		public int getCount() {
			return demos.length;
		}
		@Override
		public Object getItem(int index) {
			return  demos[index];
		}

		@Override
		public long getItemId(int id) {
			return id;
		}
	}
	
   private static class DemoInfo{
		private final int title;
		private final int desc;
		private final Class<? extends android.app.Activity> demoClass;


		public DemoInfo(int title , int desc,Class<? extends android.app.Activity> demoClass) {
			this.title = title;
			this.desc  = desc;
			this.demoClass = demoClass;
			
		}
		
		
	}
}