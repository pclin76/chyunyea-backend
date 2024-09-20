package app.goog1e.chyunyea;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.model.HttpApiV2ProxyRequest;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@SuppressWarnings("CallToPrintStackTrace")
public class StreamLambdaHandler implements RequestStreamHandler {

	private static SpringBootLambdaContainerHandler<HttpApiV2ProxyRequest, AwsProxyResponse> handler;

	static {
		try {
			handler = SpringBootLambdaContainerHandler.getHttpApiV2ProxyHandler(Application.class);
		} catch (ContainerInitializationException containerInitializationException) {
			// if we fail here. We re-throw the exception to force another cold start
			containerInitializationException.printStackTrace();
			throw new RuntimeException(
				"无法初始化春靴应用",
				containerInitializationException
			);
		}
	}

	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
		handler.proxyStream(
			inputStream,
			outputStream,
			context
		);
	}
}