package com.azifacemobile.services;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;

public class NetworkingLib {
  private static OkHttpClient _apiClient = null;

  private static OkHttpClient createApiClient() {
    return new OkHttpClient.Builder()
      .callTimeout(180, TimeUnit.SECONDS)
      .connectTimeout(180, TimeUnit.SECONDS)
      .readTimeout(180, TimeUnit.SECONDS)
      .writeTimeout(180, TimeUnit.SECONDS)
      .build();
  }

  public static synchronized OkHttpClient getApiClient() {
    if (_apiClient == null) {
      _apiClient = createApiClient();
    }
    return _apiClient;
  }
}

class ProgressRequestBody extends RequestBody {
  private final RequestBody requestBody;
  private final Listener listener;

  ProgressRequestBody(RequestBody requestBody, Listener listener) {
    this.requestBody = requestBody;
    this.listener = listener;
  }

  @Override
  public MediaType contentType() {
    return requestBody.contentType();
  }

  @Override
  public long contentLength() throws IOException {
    return requestBody.contentLength();
  }

  @Override
  public void writeTo(BufferedSink sink) throws IOException {
    ProgressStream progressStream = new ProgressStream(sink.outputStream(), contentLength());
    BufferedSink progressSink = Okio.buffer(Okio.sink(progressStream));
    requestBody.writeTo(progressSink);
    progressSink.flush();
  }

  protected final class ProgressStream extends OutputStream {
    private final OutputStream stream;
    private final long totalBytes;
    private long bytesSent;

    ProgressStream(OutputStream stream, long totalBytes) {
      this.stream = stream;
      this.totalBytes = totalBytes;
    }

    @Override
    public void write(@NonNull byte[] b, int off, int len) throws IOException {
      this.stream.write(b, off, len);
      this.bytesSent += Math.min(len, b.length);
      listener.onUploadProgressChanged(this.bytesSent, this.totalBytes);
    }

    @Override
    public void write(int b) throws IOException {
      this.stream.write(b);
      this.bytesSent += 1;
      listener.onUploadProgressChanged(this.bytesSent, this.totalBytes);
    }
  }

  interface Listener {
    void onUploadProgressChanged(long bytesWritten, long totalBytes);
  }
}
