package com.ro0kiey.mvpdemo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ro0kiey.mvpdemo.adapter.MyAdapter;
import com.ro0kiey.mvpdemo.mvp.model.Bean.MeiziBean;
import com.ro0kiey.mvpdemo.mvp.presenter.MainPresenter;
import com.ro0kiey.mvpdemo.mvp.view.IMainView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {

    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.btn_get_meizi)
    Button btnGetMeizi;
    @BindView(R.id.btn_clear_meizi)
    Button btnClearMeizi;
    @BindView(R.id.rv_main)
    RecyclerView rvMeizi;
    @BindView(R.id.pb_main)
    ProgressBar pbMain;
    @BindView(R.id.btn_insert_meizi)
    Button btnInsertMeizi;
    @BindView(R.id.btn_remove_meizi)
    Button btnRemoveMeizi;
    private MainPresenter presenter;
    private MyAdapter adapter;
    private List<MeiziBean> mMeiziList = new ArrayList<>();
    public static final int PAGE = 1;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initPresenter() {
        presenter = new MainPresenter(this, this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    private void initView() {
        pbMain.setVisibility(View.GONE);
        adapter = new MyAdapter(mMeiziList);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvMeizi.setLayoutManager(manager);
        rvMeizi.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
        pbMain.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbMain.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoMeizi() {
        mMeiziList.clear();
        count = 1;
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Clear Meizi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMeiziData(List<MeiziBean> meiziList) {
        mMeiziList.clear();
        for (int i = 0; i < meiziList.size(); i++) {
            mMeiziList.add(meiziList.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.btn_get_meizi, R.id.btn_clear_meizi, R.id.btn_insert_meizi, R.id.btn_remove_meizi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_meizi:
                String str = editText.getText().toString();
                Pattern pattern = Pattern.compile("[0-9]*");
                Matcher isNum = pattern.matcher(str);
                if (!str.equals("") & isNum.matches()){
                    count = Integer.valueOf(editText.getText().toString());
                    presenter.getMeiziData(count, PAGE);
                } else {
                    Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_clear_meizi:
                presenter.clearMeiziData();
                break;
            case R.id.btn_insert_meizi:
                count++;
                presenter.getMeiziData(count, PAGE);
                break;
            case R.id.btn_remove_meizi:
                count--;
                presenter.getMeiziData(count, PAGE);
                break;
            default:
                break;
        }
    }
}
