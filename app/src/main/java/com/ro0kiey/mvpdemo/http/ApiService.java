package com.ro0kiey.mvpdemo.http;

import com.ro0kiey.mvpdemo.mvp.model.Meizi;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ro0kieY on 2017/7/20.
 */

public interface ApiService {

    @GET("data/福利/{count}/{PAGE}")
    Observable<Meizi> getMeiziData(@Path("count") int count, @Path("PAGE") int page);

}
