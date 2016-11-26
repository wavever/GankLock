package me.wavever.ganklock.config;

/**
 * Created by wavever on 2015/12/24.
 */
public class GankApi {

    public static final String BASE_URL = "http://gank.io/api/";

    /**
     * 获取每日数据
     * http://gank.io/api/day/2015/08/06
     * 注： http://gank.io/api/day/年/月/日
     */
    public static final String GET_DAY_DATA = "http://gank.io/api/day/";


    /**
     * 获取某几日干货数据
     * http://gank.io/api/history/content/2/1
     * 注： 2 代表 2 个数据，1 代表：取第一页数据
     */
    public static final String GET_HISTORY_DATA = "http://gank.io/api/history/content/";


    /*
    http://gank.io/api/data/福利/10/1
    分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
    数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
    请求个数： 数字，大于0
    第几页：数字，大于0
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
