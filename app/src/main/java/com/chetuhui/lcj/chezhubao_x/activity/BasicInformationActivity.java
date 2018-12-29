package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chetuhui.lcj.chezhubao_x.R;

import com.chetuhui.lcj.chezhubao_x.adapter.ShwzAdapter;
import com.chetuhui.lcj.chezhubao_x.model.ShwzBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.dialog.BaseDialog;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogWheelYearMonthDay;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class BasicInformationActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarBi;
    /**
     * 下一步
     */
    private TextView mTvBiXyb;
    /**
     * 川A12345
     */
    private EditText mEtBiChepaihao;
    /**
     * 13815915912
     */
    private EditText mEtBiLxdh;
    /**
     * 前保险杠
     */
    private TextView mEtBiWeizhi;
    /**
     * 2018-11-22
     */
    private TextView mEtBiShijian;
    /**
     * 上午
     */
    private TextView mEtBiShijianduan;
    /**
     * 前保险杠脱落
     */
    private EditText mEtBiQingkuang;
    private String ih_carnum;
    private List<ShwzBean.DataBean> mBeanList=new ArrayList<>();
    private ShwzAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private BaseDialog mDialog;
    private  List<String> mStringList=new ArrayList<>();
    String model;

    private MyHandler mHandler= new MyHandler (BasicInformationActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<BasicInformationActivity> activityWeakReference;

            public MyHandler(BasicInformationActivity activity) {
                activityWeakReference = new WeakReference<BasicInformationActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                BasicInformationActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_information);
        ih_carnum=SPTool.getString(BasicInformationActivity.this,"ih_carnum");
        initView();

    }

    private void initView() {
        mTitlebarBi = (CommonTitleBar) findViewById(R.id.titlebar_bi);
        mTitlebarBi.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }
            }
        });

        mTvBiXyb = (TextView) findViewById(R.id.tv_bi_xyb);

        mEtBiChepaihao = (EditText) findViewById(R.id.et_bi_chepaihao);
        mEtBiLxdh = (EditText) findViewById(R.id.et_bi_lxdh);
        mEtBiWeizhi = (TextView) findViewById(R.id.tv_bi_weizhi);
        mEtBiShijian = (TextView) findViewById(R.id.tv_bi_shijian);
        mEtBiShijianduan = (TextView) findViewById(R.id.tv_bi_shijianduan);
        mEtBiQingkuang = (EditText) findViewById(R.id.et_bi_qingkuang);
        mEtBiChepaihao.setText(ih_carnum);
        mEtBiChepaihao.setEnabled(false);
        mEtBiChepaihao.setFocusable(false);
        mEtBiChepaihao.setKeyListener(null);//重点
        mTvBiXyb.setOnClickListener(this);
        mEtBiShijian.setOnClickListener(this);
        mEtBiShijianduan.setOnClickListener(this);
        mEtBiWeizhi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            default:
                break;
            case R.id.tv_bi_weizhi:

                N_damageLocation();
                break;
            case R.id.tv_bi_shijian:

                initWheelYearMonthDayDialog(false);

                break;
            case R.id.tv_bi_shijianduan:

                initWheelYearMonthDayDialog(true);

                break;
            case R.id.tv_bi_xyb:
                if (DataTool.isNullString(mEtBiChepaihao.getText().toString())||DataTool.isNullString(mEtBiLxdh.getText().toString())
                        ||DataTool.isNullString(mEtBiQingkuang.getText().toString())||DataTool.isNullString(mEtBiShijian.getText().toString())||
                        DataTool.isNullString(mEtBiShijianduan.getText().toString())||DataTool.isNullString(mEtBiWeizhi.getText().toString())||mEtBiWeizhi.getText().equals("请选择")||mEtBiShijian.getText().equals("请选择") ){
                    BaseToast.error("输入不能为空，请检查");

                }else {
                    SPTool.putString(BasicInformationActivity.this,"bi_cph",""+mEtBiChepaihao.getText().toString());
                    SPTool.putString(BasicInformationActivity.this,"bi_lxdh",""+mEtBiLxdh.getText().toString());
                    SPTool.putString(BasicInformationActivity.this,"bi_qk",""+mEtBiQingkuang.getText().toString());
                    SPTool.putString(BasicInformationActivity.this,"bi_sj",""+mEtBiShijian.getText().toString());
                    SPTool.putString(BasicInformationActivity.this,"bi_sjd",""+mEtBiShijianduan.getText().toString());
                    SPTool.putString(BasicInformationActivity.this,"bi_wz",""+mEtBiWeizhi.getText().toString());

                    intent = new Intent(BasicInformationActivity.this, UploadPhotosActivity.class);

                }

                break;

        }
        if (intent != null) {
            startActivity(intent);
        }
    }
    private void N_damageLocation() {
        String s_token = SPTool.getString(BasicInformationActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>get(NetData.N_damageLocation)
                .tag(this)
                .headers("token",s_token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        final String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            final String msg = jsonObject.getString("msg");
                            //BaseToast.success(msg);
                            final int code=jsonObject.getInt("code");
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (code==0){


                                        mBeanList.clear();
                                        ShwzBean bean= new Gson().fromJson(data, ShwzBean.class);
                                        mBeanList=bean.getData();
                                        showquyu(BasicInformationActivity.this);


                                    }else if (code==1004){
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(BasicInformationActivity.this,"token");
                                        startActivity(new Intent(BasicInformationActivity.this,LoginActivity.class));
                                        BaseToast.error("登录过期，请重新登录");

                                    }else {
                                        BaseToast.success(msg);

                                    }
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                    }
                });
    }
    private void initRecylerView() {

        mAdapter = new ShwzAdapter(R.layout.item_quyu, mBeanList);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        //添加动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mDialog.cancel();
                mStringList.add(mBeanList.get(position).getDamageLocation());
                 model = DataTool. ListToString(mStringList);
                mEtBiWeizhi.setText(model);
//                SPTool.putInt(AuditorActivity.this,"qy_id",mBeanList.get(position).getId());


            }
        });



    }
    public void showquyu(Context context) {
        mDialog = new BaseDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_quyu_show, null);
        mRecyclerView = view.findViewById(R.id.rv_dg_quyu);
        initRecylerView();
        ImageView cancel = view.findViewById(R.id.iv_dg_quyu);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.cancel();
            }
        });


        mDialog.setContentView(view);
        mDialog.getLayoutParams();
        mDialog.mLayoutParams.gravity = Gravity.BOTTOM;

        mDialog.show();
        mDialog.setFullScreen();
    }

    private void initWheelYearMonthDayDialog(final boolean istime) {
        Log.d("BasicInformationActivit", ""+istime);
        final DialogWheelYearMonthDay mDialogWheelYearMonthDay;
        // ------------------------------------------------------------------选择日期开始
        mDialogWheelYearMonthDay = new DialogWheelYearMonthDay(this, 1994, 2018);
        mDialogWheelYearMonthDay.setMonthType(true);
        mDialogWheelYearMonthDay.settiemType(istime);
        mDialogWheelYearMonthDay.getSureView().setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        if (istime){
                            mEtBiShijianduan.setText(mDialogWheelYearMonthDay.getSelectorTime());
                        }else {
                            if (mDialogWheelYearMonthDay.getCheckBoxDay().isChecked()) {
                                mEtBiShijian.setText(
                                        mDialogWheelYearMonthDay.getSelectorYear() + "-"
                                                + mDialogWheelYearMonthDay.getSelectorMonth() + "-"
                                                + mDialogWheelYearMonthDay.getSelectorDay() + "");
                            } else {
                                mEtBiShijian.setText(
                                        mDialogWheelYearMonthDay.getSelectorYear() + "-"
                                                + mDialogWheelYearMonthDay.getSelectorMonth() + "");
                            }
                        }

                        mDialogWheelYearMonthDay.cancel();
                    }
                });
        mDialogWheelYearMonthDay.getCancleView().setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        mDialogWheelYearMonthDay.cancel();
                    }
                });
        if (mDialogWheelYearMonthDay == null) {
        }
        mDialogWheelYearMonthDay.show();
        // ------------------------------------------------------------------选择日期结束
    }


}