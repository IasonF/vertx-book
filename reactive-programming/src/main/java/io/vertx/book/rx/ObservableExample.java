package io.vertx.book.rx;

import java.io.Serializable;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Demonstrate how RX java allows observing stream
 */
public class ObservableExample {

	public static void main(String[] args) {
		// Create a stream of integer [0..20]

		Observable<Integer> observable = Observable.range(0, 21);
		Observable<Serializable> observable3 = Observable.just("1", "item2", "item3",
				new Error("Observable not available"), 1);

		Callable<String> callable = new Callable<String>() {
			int count = 10;

			@Override
			public String call() throws Exception {
				count--;
				return count == 0 ? null : String.valueOf(Math.random());

			}
		};
		Observable<String> observable2 = Observable.fromCallable(callable);

		observable.subscribe(data -> {
			// Called with the next data available in the stream
			System.out.println(data);
		}, error -> {
			// Called when an error occurs
			error.printStackTrace();
		}, () -> {
			// Called when we reached the end of the stream
			System.out.println("No more data");
		});

		observable3.subscribe(data -> {System.out.println(data);
		}, error -> {System.out.println("Error!!!");
		}, () -> System.out.println("dfjiw"));
		

		String firstObservableString = observable2.blockingFirst();
		System.out.println(firstObservableString);
		Single<Boolean> all = observable2.all(x -> x.contains("."));
		Boolean blockingGet = all.blockingGet();
		System.out.println("All decimals:" + blockingGet);
	}

}
