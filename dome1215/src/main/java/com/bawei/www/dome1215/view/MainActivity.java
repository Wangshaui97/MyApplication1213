package com.bawei.www.dome1215.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.Toast;

import com.bawei.www.dome1215.Apis;
import com.bawei.www.dome1215.R;
import com.bawei.www.dome1215.adpter.RviewAdapter;
import com.bawei.www.dome1215.bean.NewsBean;
import com.bawei.www.dome1215.presonter.Ipresonter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Iview {
    private Ipresonter ipresonter;
    private XRecyclerView xlistview;
    private Boolean p = true;
    private LinearLayoutManager layoutManager;
    private RviewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xlistview = findViewById(R.id.xlistview);
        layoutManager = new LinearLayoutManager(this);
        xlistview.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);

        adapter = new RviewAdapter(this);
        xlistview.setAdapter(adapter);

        ipresonter = new Ipresonter(this);

        initData();
        initpull();

        findViewById(R.id.btn_qiehuan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p == true) {
                    xlistview.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                    p = false;
                } else {
                    xlistview.setLayoutManager(layoutManager);
                    p = true;
                }
            }
        });

        //子条目单击事件
        adapter.setItemOnClicklisenter(new RviewAdapter.onClickCallBack() {
            @Override
            public void setItemOnClick(String tt, int i) {
                Toast.makeText(MainActivity.this,"title is "+tt+"，position is"+i,Toast.LENGTH_SHORT);
            }
        });
    }

    private void initpull() {
        //允许刷新加载，但获取加载后直接停止

        xlistview.setPullRefreshEnabled(true);
        xlistview.setLoadingMoreEnabled(true);

        xlistview.refreshComplete();
        xlistview.loadMoreComplete();
    }

    private void initData() {
        ipresonter.setrequset(Apis.URL);
    }

    @Override
    public void setonData(Object data) {
        NewsBean newsBean = (NewsBean) data;
        List<NewsBean.DataBean> newsBeanData = newsBean.getData();
        adapter.setData(newsBeanData);
    }
}
