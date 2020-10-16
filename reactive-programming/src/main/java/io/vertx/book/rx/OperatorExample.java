package io.vertx.book.rx;

import io.reactivex.Observable;

/**
 * An example using RX operators
 */
public class OperatorExample {

    public static void main(String[] args) {
        // Create a stream of integer [0..20]
        Observable<Integer> observable = Observable.range(0, 21);
        Observable<Integer> anotherObservable = Observable.range(0, 21);
        
        System.out.println("Start series:");
		Observable<Double> unboundSeries = Observable.fromCallable(() -> Math.random()*100);
		unboundSeries.subscribe(data -> {System.out.println(data);}, OnError -> {}, ()->{});
		Observable<Integer> integers = unboundSeries.map(i -> i.intValue());
		integers.subscribe(System.out::println);
		System.out.println("End series.");
		
        observable
            .map(i -> i + 1)
            .zipWith(anotherObservable, (a, b) -> a + b)
            .subscribe(
                System.out::println,
                Throwable::printStackTrace,
                () -> {
                    // Called when we reached the end of the stream
                    System.out.println("No more data");
                }
            );
    }
}
