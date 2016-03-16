##GankLock

***Gank锁屏***，一款可以让你在锁屏的时候就看干货的app，因为Gank的app有点多，大多数都是类似与新闻类app，而Gank锁屏则是只显示每日的干货，并将其显示在锁屏界面上，恩，妹纸真不错。<br>
俗话说，千里之行，始于足下，这是自己学习android开发以来，发布第一个app，所以代码不足之处很多，自己会将这个项目不断完善下去，也算是自己学习过程中的一个见证吧。

##干货数据源

数据来源于[代码家](http://weibo.com/daimajia?from=usercardnew&refer_flag=0000020001_&is_all=1)建立的[干货集中营](http://gank.io/)。

##截图
![](http://ww1.sinaimg.cn/mw690/ace35ee1gw1f1ylqor6d3j20k00zkwjo.jpg)
![](http://ww2.sinaimg.cn/mw690/ace35ee1gw1f1yltg88blj20k00zkjvn.jpg)
![](http://ww2.sinaimg.cn/mw690/ace35ee1gw1f1yltpn94aj20k00zk77s.jpg)
![](http://ww3.sinaimg.cn/mw690/ace35ee1gw1f1ylu6y8hzj20k00zk430.jpg)
##下载
[Fir.im](http://fir.im/ganklock)<br>
![](http://ww2.sinaimg.cn/large/ace35ee1gw1f1ylxqhxidj205l05c3yr.jpg)
##代码
app采用了MVP架构，Rxjava响应式编程和Retrofit，这也是时下比较流行的组合，图片加载使用Picasso，就像自己前面说的，因为自己也是学习使用这些优秀的库和架构，所以代码方面还是有点稚嫩。

##感谢
Rxjava和Retrofit部分参考了[@drakeet](https://github.com/drakeet) 的[妹纸](https://github.com/drakeet/Meizhi)，MVP 架构参考了@[咕咚](https://github.com/maoruibin)的[GankDialy](https://github.com/maoruibin/GankDaily)，还有@[Rogero0o](https://github.com/Rogero0o)的开源锁屏项目[Mr.Locker](https://github.com/Rogero0o/ScreenLocker)，感谢这些开发者对于开源世界的贡献。   
##所使用的依赖库   

* [RxJava](https://github.com/ReactiveX/RxJava) 
* [Picasso](https://github.com/square/picasso)
* [Retrofit](https://github.com/square/retrofit)
* [activeandroid](https://github.com/pardom/ActiveAndroid)
* [materialpreference](https://github.com/jenzz/Android-MaterialPreference)

##参考资料

* [给Android开发者的RxJava详解](http://gank.io/post/560e15be2dca930e00da1083)
* [浅谈 MVP in Android](http://blog.csdn.net/lmj623565791/article/details/46596109)


## License

    /*
     *      
     * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
     * Copyright (C) 2015 GuDong <gudong.name@gmail.com>
     * Copyright (C) 2016 Wavever <wavever.ht@gmail.com> 
     *
     * Meizhi is free software: you can redistribute it and/or modify
     * it under the terms of the GNU General Public License as published by
     * the Free Software Foundation, either version 3 of the License, or
     * (at your option) any later version.
     *
     * Meizhi is distributed in the hope that it will be useful,
     * but WITHOUT ANY WARRANTY; without even the implied warranty of
     * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
     * GNU General Public License for more details.
     *
     * You should have received a copy of the GNU General Public License
     * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
     */

