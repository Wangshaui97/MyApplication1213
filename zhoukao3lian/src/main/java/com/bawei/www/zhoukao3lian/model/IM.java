package com.bawei.www.zhoukao3lian.model;

import com.bawei.www.zhoukao3lian.mycallback.MyCallback;

public interface IM {
    void setrequest(String url, MyCallback myCallback);
    void setotherrequest(String url, MyCallback myCallback);
}
