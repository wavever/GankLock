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

    /**
     * 获取闲读的主分类
     */
    public static final String XIANDU_CATERORIES = "http://gank.io/api/xiandu/categories";

    /**
     * 获取闲读的子分类
     * category 后面可接受参数为主分类返回的en_name,例如【apps， wow， android，iOS】
     */
    public static final String XIANDU_ITEMS = "http://gank.io/api/xiandu/category/";

    /**
     * 获取闲读数据
     * id 后面可接受参数为子分类返回的id
     * page 第几页，从1开始
     * count 每页的个数
     */
    public static final String XIANDU_DATA = "http://gank.io/api/xiandu/data/id/appinn/count/10/page/1";

    /**
     * 方法：POST
     * 参数：username，password
     * 登录后会在cookie中返回账号密码，只要在客户端做cookie持久化存储即可自动登录验证。
     */
    public static final String LOGIN = "http://www.wanandroid.com/user/login";

    /**
     * 方法：POST
     * 参数：username,password,repassword
     */
    public static final String REGISTER = "http://www.wanandroid.com/user/register";

    /**
     * 方法：GET
     * 访问了 logout 后，服务端会让客户端清除 Cookie（即cookie max-Age=0），
     * 如果客户端 Cookie 实现合理，可以实现自动清理，如果本地做了用户账号密码和保存，及时清理。
     */
    public static final String LOGOUT = "http://www.wanandroid.com/user/logout/json";

    /**
     * http://www.wanandroid.com/lg/collect/list/0/json
     * 方法：GET
     * 参数：页码：拼接在链接中，从0开始。
     */
    public static final String COLLECT_LIST = "http://www.wanandroid.com/lg/collect/list/0/json";

}
