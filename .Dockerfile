FROM public.ecr.aws/lambda/java:17

WORKDIR ${LAMBDA_TASK_ROOT}

# Copy function code and runtime dependencies from Maven layout
COPY target/classes ${LAMBDA_TASK_ROOT}
COPY target/lib/* ${LAMBDA_TASK_ROOT}/lib/

# Set the CMD to your handler (could also be done as a parameter override outside of the .Dockerfile)
CMD [ "tw.codethefuckup.pawnshops.StreamLambdaHandler::handleRequest" ]
