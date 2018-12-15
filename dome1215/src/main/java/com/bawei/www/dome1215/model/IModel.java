package com.bawei.www.dome1215.model;


import com.bawei.www.dome1215.bean.NewsBean;
import com.bawei.www.dome1215.mycallback.MyCallback;
import com.bawei.www.dome1215.okhttputil.Httputil;
import com.bawei.www.dome1215.okhttputil.ICallBack;

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

}
