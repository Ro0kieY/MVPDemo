package com.ro0kiey.mvpdemo.mvp.view;

import com.ro0kiey.mvpdemo.mvp.model.Bean.MeiziBean;

import java.util.List;

/**
 * Created by Ro0kieY on 2017/7/20.
 */

public interface IMainView extends IBaseView {

    void showErrorView();

    void showNoMeizi();

    void showMeiziData(List<MeiziBean> meiziList);

}
