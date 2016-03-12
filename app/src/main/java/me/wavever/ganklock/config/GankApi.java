package me.wavever.ganklock.config;

/**
 * Created by WAVE on 2015/12/24.
 */
public class GankApi {


    public static final String BASE_URL = "http://gank.io";

    /*
    每日数据
     */
    public static final String GET_DAY_DATA = "http://gank.io/api/day/";


    /*
    分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
    数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
    请求个数： 数字，大于0
    第几页：数字，大于0
     */
    public static final String GET_CATEGORY_DATA = "http://gank.io/api/data/";


    /*
    随机数据：http://gank.io/api/random/data/分类/个数
    数据类型：福利 | Android | iOS | 休息视频 | 拓展资源 | 前端
    个数： 数字，大于0
     */
    public static final String GET_RANDOM_DATA = "http://gank.io/api/random/data/";

}
