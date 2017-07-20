package com.ro0kiey.mvpdemo.mvp.presenter;

import android.content.Context;

import com.ro0kiey.mvpdemo.http.RetrofitClient;
import com.ro0kiey.mvpdemo.mvp.model.Bean.MeiziBean;
import com.ro0kiey.mvpdemo.mvp.model.Meizi;
import com.ro0kiey.mvpdemo.mvp.view.IMainView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ro0kieY on 2017/7/20.
 */

public class MainPresenter extends BasePresenter<IMainView> {

    public MainPresenter(Context context, IMainView iView) {
        super(context, iView);
    }

    @Override
    public void detachView() {
        this.iView = null;
    }

    public void clearMeiziData(){
        iView.showProgress();
        iView.showNoMeizi();
        iView.hideProgress();
    }

    public void getMeiziData(int count, int page){
        iView.showProgress();
        RetrofitClient.getApiServiceInstance().getMeiziData(count, page)
                .map(new Function<Meizi, List<MeiziBean>>() {
                    @Override
                    public List<MeiziBean> apply(@NonNull Meizi meizi) throws Exception {
                        return createMeiziListwithMeizi(meizi);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MeiziBean>>() {
                    @Override
                    public void accept(@NonNull List<MeiziBean> meiziBean) throws Exception {
                        if (meiziBean.size() == 0){
                            iView.showErrorView();
                        } else {
                            iView.showMeiziData(meiziBean);
                        }
                        iView.hideProgress();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iView.showErrorView();
                        iView.hideProgress();
                    }
                });
    }

    private List<MeiziBean> createMeiziListwithMeizi(Meizi meizi) {
        List<MeiziBean> meiziList = new ArrayList<>();
        for (int i = 0; i < meizi.getResults().size(); i++){
            meiziList.add(meizi.getResults().get(i));
        }
        return meiziList;
    }
}
