package com.bawei.www.dome1215.presonter;

import com.bawei.www.dome1215.model.IModel;
import com.bawei.www.dome1215.mycallback.MyCallback;
import com.bawei.www.dome1215.view.Iview;

public class Ipresonter implements IP {
    private Iview iview;
    private IModel iModel;

    public Ipresonter(Iview iview) {
        this.iview = iview;
        iModel = new IModel();
    }

    @Override
    public void setrequset(String url) {

        iModel.setrequest(url, new MyCallback() {
            @Override
            public void onsuccess(Object data) {
                iview.setonData(data);
            }
        });

    }

    public void onDistouch(){
        if(iModel!=null){
            iModel=null;
        }
        if(iview!=null){
            iview=null;
        }
    }
}
