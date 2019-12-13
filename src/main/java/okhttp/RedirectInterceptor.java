package okhttp;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

//处理重定向的拦截器
//詳細參考：https://www.jianshu.com/p/d04bfd6b6146
public class RedirectInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        okhttp3.Request request = chain.request();
        Response response = chain.proceed(request);
        int code = response.code();
        if (code == 307) {
            //获取重定向的地址
            String location = response.headers().get("Location");
            System.out.println("重定向地址：location = " + location);
            //重新构建请求
            Request newRequest = request.newBuilder().url(location).build();
            response = chain.proceed(newRequest);
        }
        return response;
    }
}
