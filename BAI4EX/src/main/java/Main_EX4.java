import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import static spark.Spark.*;

public class Main_EX4 {
	public static void main (String[]args){
		LoadingCache<Integer, Integer> number=CacheBuilder.newBuilder()
				.maximumSize(100)//maximum 100 records can be cached
				.expireAfterAccess(10, TimeUnit.SECONDS)//after 10s ,cache will expire
				.build(new CacheLoader<Integer, Integer>(){
					public Integer load(Integer num) throws Exception{
						return getNumber(num);
					}
				});
		get("/factorial",(req, res)->{
			int numb= Integer.parseInt(req.queryParams("n"));
			return number.get(numb);
		});
	}
	private static int getNumber(int num){
		int factorial=1;
		for(int i=1; i<=num;i++){
			factorial*=i;
		}
		System.out.println("New factorial:"+factorial);
		return factorial;
	}
//		get("/factorial", (req, res) -> {
//            int n = Integer.parseInt(req.queryParams("n"));
//            double val = 1;
//            for(int i = 1; i <= n; i++) {
//                val =val* i;
//                System.out.println(val);
//            }
//            return val;
//        });
	
}
