package io.vertx.book.rx;

import java.util.List;
import java.util.OptionalDouble;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import io.reactivex.Observable;
import io.reactivex.Single;

public class FuturesExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Future<String> downloadTask = download();
		String result = downloadTask.get();
		System.out.println(result);
		System.out.println(downloadTask.get());
		
		Observable<Double> obs1 = Observable.create(emitter -> {
				emitter.onNext(Math.random());
				emitter.onComplete();
		});
		
		Observable<Integer> obs2 = Observable.range(0, 100);
		Single<List<Integer>> list = obs2.toList();
		List<Integer> finalList = list.blockingGet();
		OptionalDouble average = finalList.stream().mapToDouble(i -> i).average();
		System.out.println(average.getAsDouble());
		obs1.window(10L).forEach(System.out::println);
		obs2.window(10L).map(Observable<Integer>::blockingFirst).forEach(System.out::println);
		
	}

	private static Future<String> download() {
		return CompletableFuture.completedFuture("Download completed...");
	}

}
