package android.coding.interview.makeitawesome.data;

import android.coding.interview.makeitawesome.domain.entity.Events;

import com.squareup.okhttp.OkHttpClient;

import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.SimpleXmlConverterFactory;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * REST API
 * @see <a href="http://www.finnkino.fi/XML">Finnkino Api</a>
 */
public interface FinnkinoApi {
    String BASE_URL = "http://www.finnkino.fi/xml/";

    @GET("Events")
    Observable<Events> getEvents(@Query("listType") String listType, @Query("area") int areaId);

    class Factory {
        public static FinnkinoApi create(OkHttpClient client) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(FinnkinoApi.class);
        }

        public static FinnkinoApi create() {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(FinnkinoApi.class);
        }
    }

    class AreaID {
        public static final int Helsinki = 1002;
        public static final int Espoo = 1012;
    }

    class ListType {
        public static final String ComingSoon = "ComingSoon";
        public static final String NowInThreatres = "NowInTheatres";
    }
}
