package com.bawei.www.zhoukao3lian.model;

import com.bawei.www.zhoukao3lian.bean.NewsBean;
import com.bawei.www.zhoukao3lian.bean.OtherBean;
import com.bawei.www.zhoukao3lian.mycallback.MyCallback;
import com.bawei.www.zhoukao3lian.okhttputil.Httputil;
import com.bawei.www.zhoukao3lian.okhttputil.ICallBack;

public class IModel implements IM {
    @Override
    public void setrequest(String url, final MyCallback myCallback) {

        Httputil.getInstance().getEnqueue(url, NewsBean.class, new ICallBack() {
            @Override
            public void success(Object obj) {
                myCallback.onsuccess(obj);
            }

            @Override
            public void failed(Exception e) {

            }
        });
    }

    @Override
    public void setotherrequest(String url, final MyCallback myCallback) {
        Httputil.getInstance().getEnqueue(url, OtherBean.class, new ICallBack() {
            @Override
            public void success(Object obj) {
                myCallback.onsuccess(obj);
            }

            @Override
            public void failed(Exception e) {

            }
        });
    }
}
