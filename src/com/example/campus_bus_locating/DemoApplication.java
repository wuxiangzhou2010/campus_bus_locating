package com.example.campus_bus_locating;


import android.app.Application;
import android.content.Context;
import android.view.Window;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;


public class DemoApplication extends Application {
	
    private static DemoApplication mInstance = null;
    public boolean m_bKeyRight = true;
    BMapManager mBMapMan = null;  //�������SDK�Ķ���

    public static final String strKey = "iLKWnF2OQt7pxggirFm69Fd3";
	
	@Override
    public void onCreate() {
	    super.onCreate();
	    
		mInstance = this;
		initEngineManager(this);
	}
	
	public void initEngineManager(Context context) {
        if (mBMapMan == null) {
            mBMapMan = new BMapManager(context);
        }

        if (!mBMapMan.init(strKey,new MyGeneralListener())) {
            Toast.makeText(DemoApplication.getInstance().getApplicationContext(), 
                    "BMapManager ��ʼ������!", Toast.LENGTH_LONG).show();
        }
	}
	
	public static DemoApplication getInstance() {
		return mInstance;
	}
	
	
	/// �����¼���������������ͨ�������������Ȩ��֤�����
    static class MyGeneralListener implements MKGeneralListener {
        
        @Override
        public void onGetNetworkState(int iError) {
            if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
                Toast.makeText(DemoApplication.getInstance().getApplicationContext(), "���������������",
                    Toast.LENGTH_LONG).show();
            }
            else if (iError == MKEvent.ERROR_NETWORK_DATA) {
                Toast.makeText(DemoApplication.getInstance().getApplicationContext(), "������ȷ�ļ���������",
                        Toast.LENGTH_LONG).show();
            }
            // ...
        }

        @Override
        public void onGetPermissionState(int iError) {
        	////����ֵ��ʾkey��֤δͨ��
            if (iError != 0) {
            	//��ȨKey����
                Toast.makeText(DemoApplication.getInstance().getApplicationContext(), 
                        "���� DemoApplication.java�ļ�������ȷ����ȨKey,������������������Ƿ�������error: "+iError, Toast.LENGTH_LONG).show();
                DemoApplication.getInstance().m_bKeyRight = false;
            }
            else{
            	DemoApplication.getInstance().m_bKeyRight = true;
            	//Toast.makeText(DemoApplication.getInstance().getApplicationContext(), 
                       // "key��֤�ɹ�", Toast.LENGTH_LONG).show();
            }
        }
    }
}