package rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
                logger.info("observable.subscribe()...");
                observableEmitter.onNext("我来发射数据");
            }
        });
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
        observable.subscribe(observer2);
        logger.info("subscribe end...");
    }
}
