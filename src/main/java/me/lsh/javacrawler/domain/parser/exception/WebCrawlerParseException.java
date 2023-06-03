package me.lsh.javacrawler.domain.parser.exception;

public class WebCrawlerParseException extends RuntimeException {

    private final String message;

    private final Class<?> parserClass;

    public WebCrawlerParseException(final String message, final Class<?> parserClass) {
        this(message, parserClass, null);
    }

    public WebCrawlerParseException(final String message, final Class<?> parserClass, final Throwable cause) {
        super(message, cause);
        this.message = message;
        this.parserClass = parserClass;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[%-20s]  :  %s",
            parserClass.getSimpleName(), message)
        );
        if (hasCause()) {
            sb.append("\n\t-> Caused by: ");
            sb.append(this.getCause().getMessage());
        }
        return sb.toString();
    }

    private boolean hasCause() {
        return this.getCause() != null;
    }
}
