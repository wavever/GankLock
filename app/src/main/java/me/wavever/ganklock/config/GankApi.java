package me.wavever.ganklock.config;

/**
 * Created by wavever on 2015/12/24.
 */
public class GankApi {

    public static final String BASE_URL = "http://gank.io/api/";

    /*
    每日数据 http://gank.io/api/day/2016/04/12
     */
    public static final String GET_DAY_DATA = "http://gank.io/api/day/";


    /*
    分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
    数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
    请求个数： 数字，大于0
    第几页：数字，大于0
    例如：http://gank.io/api/data/福利/10/1
     */
    public static final String GET_CATEGORY_DATA = "http://gank.io/api/data/";

    public static final String GET_GIRL_DATA = "http://gank.io/api/data/福利/";

    /*
    随机数据：http://gank.io/api/random/data/分类/个数
    数据类型：福利 | Android | iOS | 休息视频 | 拓展资源 | 前端
    个数： 数字，大于0
     */
    public static final String GET_RANDOM_DATA = "http://gank.io/api/random/data/";


    /**
     * 搜索API http://gank.io/api/search/query/listview/category/Android/count/10/page/1
     * category 后面可接受参数 all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
     *  count 最大 50
     */
    public static final String SEARCH_CATEGORY_DATA = "http://gank.io/api/search/query/listview/category/";

}
