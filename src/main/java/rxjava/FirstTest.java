package rxjava;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <dl>
 * <dt>FirstTest</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/2/26</dd>
 * </dl>
 *
 * @author cuihc
 */
public class FirstTest {

    final static Logger logger = LoggerFactory.getLogger(FirstTest.class);

    @Test
    public void first() throws InterruptedException {
        Observable<String>  observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                logger.info("observable.subscribe()发射。。。。。。。。...");
                observableEmitter.onNext("我来发射数据");
                logger.info("observable.subscribe()发射1。。。。。。。。...");
                observableEmitter.onNext("我来发射数据1");
                logger.info("observable.subscribe()发射2。。。。。。。。...");
                observableEmitter.onNext("我来发射数据2");
                int i = 0;
                while (i<100) {
                    logger.info("observable.subscribe()发射2。。。。。。。。...");
                    Thread.sleep(100);
                    i++;
                    observableEmitter.onNext("我来发射数据=" + i);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.single());

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                logger.info("observer.onSubscribe()...");
            }

            @Override
            public void onNext(String s) {
                logger.info("observer.onNext()..." + s);
            }

            @Override
            public void onError(Throwable e) {
                logger.info("observer.onError()..." + e);
            }

            @Override
            public void onComplete() {
                logger.info("observer.onComplete()...");
            }
        };

        Observer<String> observer2 = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                logger.info("observer2.onSubscribe()...");
            }

            @Override
            public void onNext(String s) {
                logger.info("observer2.onNext()..." + s);
            }

            @Override
            public void onError(Throwable e) {
                logger.info("observer2.onError()..." + e);
            }

            @Override
            public void onComplete() {
                logger.info("observer2.onComplete()...");
            }
        };
        logger.info("subscribe start...");
        observable.subscribe(observer);
//        observable.subscribe(observer2);
        logger.info("subscribe end...");
        Thread.sleep(400000);
    }

    @Test
    public void just() throws InterruptedException {
        Observable<String>  observable = Observable.just("我来发射数据");
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                logger.info("observer.onSubscribe()...");
            }

            @Override
            public void onNext(String s) {
                logger.info("observer.onNext()..." + s);
            }

            @Override
            public void onError(Throwable e) {
                logger.info("observer.onError()..." + e);
            }

            @Override
            public void onComplete() {
                logger.info("observer.onComplete()...");
            }
        };
        logger.info("subscribe start...");
        observable.subscribe(observer);
        logger.info("subscribe end...");
    }

    @Test
    public void fromIterable() throws InterruptedException {
        List<String> list = new ArrayList<String>();
        for(int i =0;i<10;i++){
            list.add("Hello"+i);
        }
        Observable<String> observable = Observable.fromIterable((Iterable<String>) list);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                logger.info("observer.onSubscribe()...");
            }

            @Override
            public void onNext(String s) {
                logger.info("observer.onNext()..." + s);
            }

            @Override
            public void onError(Throwable e) {
                logger.info("observer.onError()..." + e);
            }

            @Override
            public void onComplete() {
                logger.info("observer.onComplete()...");
            }
        };
        logger.info("subscribe start...");
        observable.subscribe(observer);
        logger.info("subscribe end...");
    }

    @Test
    public void interval() throws InterruptedException {
        Observable<Long> observable = Observable.interval(2, TimeUnit.SECONDS);

        Observer<Long> observer = new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                logger.info("observer.onSubscribe()...");
            }

            @Override
            public void onNext(Long s) {
                logger.info("observer.onNext()..." + s);
            }

            @Override
            public void onError(Throwable e) {
                logger.info("observer.onError()..." + e);
            }

            @Override
            public void onComplete() {
                logger.info("observer.onComplete()...");
            }
        };
        logger.info("subscribe start...");
        observable.subscribe(observer);
        logger.info("subscribe end...");
        Thread.sleep(70000);
    }
    @Test
    public void range() throws InterruptedException {
        Observable<Long> observable = Observable.rangeLong(1,20);

        Observer<Long> observer = new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                logger.info("observer.onSubscribe()...");
            }

            @Override
            public void onNext(Long s) {
                logger.info("observer.onNext()..." + s);
            }

            @Override
            public void onError(Throwable e) {
                logger.info("observer.onError()..." + e);
            }

            @Override
            public void onComplete() {
                logger.info("observer.onComplete()...");
            }
        };
        logger.info("subscribe start...");
        observable.subscribe(observer);
        logger.info("subscribe end...");
    }

    @Test
    public void map() throws InterruptedException {
        Observable.just("字符串1")
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {
                        logger.info("收到[" + s + "]");
                        return 100;
                    }
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                logger.info("observer.onSubscribe()...");
            }

            @Override
            public void onNext(Integer s) {
                logger.info("observer.onNext()..." + s);
            }

            @Override
            public void onError(Throwable e) {
                logger.info("observer.onError()..." + e);
            }

            @Override
            public void onComplete() {
                logger.info("observer.onComplete()...");
            }
        });
    }

    @Test
    public void backpressureError() throws InterruptedException {
        Observable<Integer>  observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                int i =0;
               while (true) {
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   logger.info("发射数据=" + (i++));
                   observableEmitter.onNext(i);
               }
            }
        });
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                logger.info("observer.onSubscribe()...");
            }

            @Override
            public void onNext(Integer s) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("observer.onNext()..." + s);
            }

            @Override
            public void onError(Throwable e) {
                logger.info("observer.onError()..." + e);
            }

            @Override
            public void onComplete() {
                logger.info("observer.onComplete()...");
            }
        };
        observable.observeOn(Schedulers.single()).subscribeOn(Schedulers.io()).subscribe(observer);
        Thread.sleep(400000);
    }

    @Test
    public void backpressureException() throws InterruptedException {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(100);
                    logger.info("emit " + i);
                    emitter.onNext(i);
                }
            }
        }, BackpressureStrategy.ERROR).observeOn(Schedulers.io())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        logger.info("Subscription: " + s);
                        s.request(100);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        logger.info("onNext: " + integer);
//                        s.request(1);
                    }
                    @Override
                    public void onError(Throwable t) {
                        logger.info("onError: ", t);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        Thread.sleep(40000);
    }

}
