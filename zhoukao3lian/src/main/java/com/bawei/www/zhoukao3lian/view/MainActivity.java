package com.bawei.www.zhoukao3lian.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.www.zhoukao3lian.Apis;
import com.bawei.www.zhoukao3lian.R;
import com.bawei.www.zhoukao3lian.adpter.ListRecyclviewAdapter;
import com.bawei.www.zhoukao3lian.bean.NewsBean;
import com.bawei.www.zhoukao3lian.presonter.Ipresonter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Iview {

    private Ipresonter ipresonter;

    private int page;
    private XRecyclerView xlistview;
    private ListRecyclviewAdapter lp;
    private Boolean p = true;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = 1;

        xlistview = findViewById(R.id.xlistview);


        layoutManager = new LinearLayoutManager(this);
        xlistview.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);

        lp = new ListRecyclviewAdapter(this);
        xlistview.setAdapter(lp);


        ipresonter = new Ipresonter(this);
        initData();
        initpull();
        initClick();








    }

    private void initClick() {

        findViewById(R.id.btn_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ipresonter.setrequset(Apis.MORE);
            }
        });


        findViewById(R.id.btn_price).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ipresonter.setrequset(Apis.PRICE);
            }
        });

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

        findViewById(R.id.btn_sousuo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText putin = findViewById(R.id.btn_putin);
                String things = putin.getText().toString();
                ipresonter.setrequset(String.format(Apis.STRURL, things));
            }
        });

        lp.setItemOnClicklisenter(new ListRecyclviewAdapter.onClickCallBack() {
            @Override
            public void setItemOnClick(int i) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("index", i);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        ipresonter.setrequset(Apis.URL + page);
    }

    private void initpull() {
        xlistview.setPullRefreshEnabled(true);
        xlistview.setLoadingMoreEnabled(true);
        xlistview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                initData();
            }

            @Override
            public void onLoadMore() {
                initData();
            }
        });
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ipresonter != null) {
            ipresonter.onDistouch();
        }
    }

    @Override
    public void setonData(Object data) {
        NewsBean newsBean = (NewsBean) data;
        List<NewsBean.DataBean> newsBeanData = newsBean.getData();


        if(newsBean == null ){
            Toast.makeText(MainActivity.this,newsBean.getMsg(),Toast.LENGTH_SHORT).show();
        }else{
            if(page == 1){
                lp.setData(newsBeanData);
            }else{
                lp.addData(newsBeanData);
            }
            page++;
            xlistview.refreshComplete();
            xlistview.loadMoreComplete();
        }
    }


}
