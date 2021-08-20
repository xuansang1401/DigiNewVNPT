package com.gpaddy.baseandroid.theu.api;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //https://newsapi.org/v2/everything?q=tesla&from=2021-07-08&sortBy=publishedAt&apiKey=3d30621d12254ce5864d0bad40094a88
    //https://newsapi.org/
    //https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fvnexpress.net%2Frss%2Ftin-moi-nhat.rss
    public static final String BASE_URL="https://api.rss2json.com/";
    public static Retrofit retrofit;
    public static Retrofit getApiClient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(getUnsafeOkHttpClient().build())
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
    public static Retrofit getApiClientSearch(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl("https://newsapi.org/")
                    .client(getUnsafeOkHttpClient().build())
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

        public static OkHttpClient.Builder getUnsafeOkHttpClient () {

            try {
                // Create a trust manager that does not validate certificate chains
                final TrustManager[] trustAllCerts = new TrustManager[]{
                        new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                return new java.security.cert.X509Certificate[]{};
                            }
                        }
                };

                // Install the all-trusting trust manager
                final SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

                // Create an ssl socket factory with our all-trusting manager
                final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
                builder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
                return builder;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    }


    }
