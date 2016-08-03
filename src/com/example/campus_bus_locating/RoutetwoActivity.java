package com.example.campus_bus_locating;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;




import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.cloud.BoundSearchInfo;
import com.baidu.mapapi.cloud.CloudListener;
import com.baidu.mapapi.cloud.CloudManager;
import com.baidu.mapapi.cloud.CloudPoiInfo;
import com.baidu.mapapi.cloud.CloudSearchResult;
import com.baidu.mapapi.cloud.DetailSearchInfo;
import com.baidu.mapapi.cloud.DetailSearchResult;
import com.baidu.mapapi.cloud.LocalSearchInfo;
import com.baidu.mapapi.cloud.NearbySearchInfo;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.map.RouteOverlay;
import com.baidu.mapapi.search.MKRoute;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.example.campus_bus_locating.RouteoneActivity.MyLocationListener;
import com.example.campus_bus_locating.RouteoneActivity.TimerGet;



public class RoutetwoActivity extends Activity implements CloudListener {
	  MapView mMapView;
	    private MapController mMapController = null;
	    private GeoPoint p=new GeoPoint((int)(36.6776 * 1E6),(int)(117.0658 * 1E6));
	 // LocationClient����������߳�����������ҪContext���͵Ĳ�����
	 	public LocationClient  mLocClient = null;
	 	public BDLocationListener myListener = null;
	 	
	 	public LocationData locData = null;// �û�λ����Ϣ
	 	public MyLocationOverlay myLocationOverlay = null;
	 	
	 	//POIͼ��
	 	 CloudOverlay poiOverlay = null;
	 	 
	 	 //��ʱ����
	 	public Timer timer=null;
	 	
	 	//�Ƿ��״ζ�λ	
		boolean isFirstLoc = true;
	 	
	    
	    
	    @Override
	    protected void onCreate(Bundle icicle) {
	        super.onCreate(icicle);
	        DemoApplication app = (DemoApplication)this.getApplication();
	        if (app.mBMapMan == null) {
	            app.mBMapMan = new BMapManager(this);
	            app.mBMapMan.init(DemoApplication.strKey,new DemoApplication.MyGeneralListener());
	        }
	        setContentView(R.layout.activity_search_and_locate);
	        //�Ƽ�����ʼ��
	        CloudManager.getInstance().init(RoutetwoActivity.this);
	        //��ͼ��ʼ��
	        mMapView = (MapView)findViewById(R.id.bmapView);
	        mMapController = mMapView.getController();
	        mMapView.getController().enableClick(true);
	        mMapView.getController().setZoom(16);
	        mMapView.setDoubleClickZooming(true);
	        mMapController.setCenter(p);
	        //��λ��ʼ��
	        mLocClient = new LocationClient( this );
	        locData = new LocationData();
	        myListener = new MyLocationListener();
	        mLocClient.registerLocationListener( myListener );
	        LocationClientOption option = new LocationClientOption();
	        option.setOpenGps(true);//��gps
	        option.setCoorType("bd09ll");     //������������
	        option.setScanSpan(1000);
	        
	        mLocClient.setLocOption(option);
	        mLocClient.start();
	        
	      //��λͼ���ʼ��
	      		myLocationOverlay = new MyLocationOverlay(mMapView);
	      		//���ö�λ����
	      	    myLocationOverlay.setData(locData);
	      	    //��Ӷ�λͼ��
	      		mMapView.getOverlays().add(myLocationOverlay);
	      		
	      		//�޸Ķ�λ���ݺ�ˢ��ͼ����Ч
	      		mMapView.refresh();
	        
	        
	      	//�趨��ʱ��������
	      		timer = new Timer(); 
	      		TimerGet myTimeTask = new TimerGet(); 
	      		Date date = new Date(); 
	      		long timestamp = 5000; 
	      		timer.schedule(myTimeTask, date, timestamp); 
	      		
	      	//����·��	
	        GeoPoint p1 = new GeoPoint((int)(36.6776* 1E6),(int)( 117.0658 * 1E6));
			GeoPoint p2 = new GeoPoint((int)(36.6776 * 1E6),(int)(117.0625 * 1E6));
			GeoPoint p3 = new GeoPoint((int)(36.6753 * 1E6),(int)(117.0625 * 1E6));
			GeoPoint p4 = new GeoPoint((int)(36.6743 * 1E6),(int)(117.0624 * 1E6));
			GeoPoint p5 = new GeoPoint((int)(36.6720 * 1E6),(int)(117.0624 * 1E6));
			/*GeoPoint p6 = new GeoPoint((int)(36.6856* 1E6),(int)(117.0728 * 1E6));
			GeoPoint p7 = new GeoPoint((int)(36.6854 * 1E6),(int)(117.0701 * 1E6));
			GeoPoint p8 = new GeoPoint((int)(36.6851 * 1E6),(int)(117.0659 * 1E6));
			GeoPoint p9 = new GeoPoint((int)(36.6850 * 1E6),(int)(117.0647 * 1E6));
			GeoPoint p10 = new GeoPoint((int)(36.6827 * 1E6),(int)(117.0647 * 1E6));
			GeoPoint p11= new GeoPoint((int)(36.6827 * 1E6),(int)(117.0658* 1E6));
			GeoPoint p12= new GeoPoint((int)(36.6776 * 1E6),(int)(117.0658 * 1E6));
**/
			
			
		    //�������  
			GeoPoint start = p1;
			//�յ�����
			GeoPoint stop = p5;
			//��һվ��վ������Ϊp3,����p1,p2
			GeoPoint[] step1 = new GeoPoint[1];
			step1[0] = p1;
			
			//�ڶ�վ��վ������Ϊp5,����p4
			GeoPoint[] step2 = new GeoPoint[1];
			step2[0] = p2;
			
			//����վ��վ������Ϊp7,����p6
			GeoPoint[] step3 = new GeoPoint[3];
			step3[0] = p3;
			step3[1] = p4;
			step3[2] = p5;
			//����վ��
			/*GeoPoint[] step4 = new GeoPoint[3];
			step4[0] = p10;
			step4[1] = p11;
			step4[2] = p12;**/
			//վ�����ݱ�����һ����ά������
			GeoPoint [][] routeData = new GeoPoint[3][];
			routeData[0] = step1;
			routeData[1] = step2;
			routeData[2] = step3;
			//routeData[3] = step4;
			//��վ�����ݹ���һ��MKRoute
			MKRoute route = new MKRoute();
			route.customizeRoute(start, stop, routeData);	
			//������վ����Ϣ��MKRoute��ӵ�RouteOverlay��
			RouteOverlay routeOverlay = new RouteOverlay(RoutetwoActivity.this, mMapView);		
			routeOverlay.setData(route);
			//���ͼ��ӹ���õ�RouteOverlay
			mMapView.getOverlays().add(routeOverlay);
			//ִ��ˢ��ʹ��Ч
		    mMapView.refresh();
		  
	       
	    }
	    
	    
	    @Override
	    protected void onPause() {
	        mMapView.onPause();
	        super.onPause();
	    }
	    
	    @Override
	    protected void onResume() {
	        mMapView.onResume();
	        super.onResume();
	    }
	    
	    @Override
	    protected void onDestroy() {
	    	//�˳�ʱ���ٶ�λ
	        if (mLocClient != null)
	            mLocClient.stop();
	        timer.cancel();
	        mMapView.destroy();
	        super.onDestroy();
	    }
	    
	    @Override
	    protected void onSaveInstanceState(Bundle outState) {
	    	super.onSaveInstanceState(outState);
	    	mMapView.onSaveInstanceState(outState);
	    	
	    }
	    
	    @Override
	    protected void onRestoreInstanceState(Bundle savedInstanceState) {
	    	super.onRestoreInstanceState(savedInstanceState);
	    	mMapView.onRestoreInstanceState(savedInstanceState);
	    }

		public void onGetDetailSearchResult(DetailSearchResult result, int error) {
			if (result != null) {
	            if (result.poiInfo != null) {
	                Toast.makeText(RoutetwoActivity.this, result.poiInfo.title, Toast.LENGTH_SHORT).show();
	            }
	            else {
	                Toast.makeText(RoutetwoActivity.this, "status:" + result.status, Toast.LENGTH_SHORT).show();
	            }
	        }
		}

		public void onGetSearchResult(CloudSearchResult result, int error) {
			if (result != null && result.poiList!= null && result.poiList.size() > 0) {
				poiOverlay=new CloudOverlay(this,mMapView);
	               poiOverlay.setData(result.poiList);
	               mMapView.getOverlays().add(poiOverlay);
	               mMapView.refresh();
	            Log.v("search", "success");
	           // mMapView.getController().animateTo(new GeoPoint((int)(result.poiList.get(0).latitude * 1e6), (int)(result.poiList.get(0).longitude * 1e6)));
	        }
		}

		public class MyLocationListener implements BDLocationListener {
			
		    @Override
		    public void onReceiveLocation(BDLocation location) {
		        if (location == null)
		            return ;
		      
		        locData.latitude = location.getLatitude();
		        locData.longitude = location.getLongitude();
		        //�������ʾ��λ����Ȧ����accuracy��ֵΪ0����
		        locData.accuracy = location.getRadius();
		        // �˴��������� locData�ķ�����Ϣ, �����λ SDK δ���ط�����Ϣ���û������Լ�ʵ�����̹�����ӷ�����Ϣ��
		        locData.direction = location.getDerect();
		        //���¶�λ����
		        myLocationOverlay.setData(locData);
		        //����ͼ������ִ��ˢ�º���Ч
		        mMapView.refresh();
		        Log.d("LocationOverlay", "receive location, animate to it");
		        if(isFirstLoc==true){
			        mMapController.animateTo(new GeoPoint((int) (locData.latitude * 1e6),
							(int) (locData.longitude * 1e6)));
			        isFirstLoc=false;
		        }
		      } 
		    
		    @Override
			public void onReceivePoi(BDLocation poiLocation) {
				// TODO Auto-generated method stub
			}

		}
		public	class TimerGet extends TimerTask{
			public void run() {
				LocalSearchInfo info = new LocalSearchInfo();
				info.ak = "MnTcEUkh37oGzSF5kpvCL4I7";
				info.geoTableId =45800;
				info.tags = "";
				info.q="";
				info.region ="������";
				if(poiOverlay!=null)
					mMapView.getOverlays().remove(poiOverlay);
				CloudManager.getInstance().localSearch(info);
				
			}
			
		}

	}
	