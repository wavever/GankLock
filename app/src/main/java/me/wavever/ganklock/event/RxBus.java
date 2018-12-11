package me.wavever.ganklock.event;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

/**
 * 使用RxJava实现事件总线
 */
public class RxBus {

    private static volatile RxBus instance;

    private final SerializedSubject<Object, Object> subject;

    private RxBus() {
        subject = new SerializedSubject<>(PublishSubject.create());
    }

    /**
     * 单例模式获取RxBus
     *
     * @return
     */
    public static RxBus getInstance() {
        RxBus rxBus = instance;
        if (instance == null) {
            synchronized (RxBus.class) {
                rxBus = instance;
                if (instance == null) {
                    rxBus = new RxBus();
                    instance = rxBus;
                }
            }
        }
        return rxBus;
    }

    /**
     * 发送一个新的事件
     *
     * @param object
     */
    public void post(Object object) {
        subject.onNext(object);
    }

    /**
     * 根据传递的eventType类型返回特定的被观察者
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> Observable<T> tObservable(Class<T> eventType) {
        return subject.ofType(eventType);
    }

    public boolean hasObservers() {
        return subject.hasObservers();
    }
}
