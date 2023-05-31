package me.lsh.javacrawler.parser.event.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WebCrawlerParsingException extends RuntimeException {

    private final String message;

    private final Class<?> parserClass;

    private String url;

    public WebCrawlerParsingException(final String message, final Class<?> parserClass, final Throwable cause) {
        super(message, cause);
        this.message = message;
        this.parserClass = parserClass;
    }

    public WebCrawlerParsingException(final String message, final String url,
        final Class<?> parserClass, final Throwable cause) {
        super(message, cause);
        this.message = message;
        this.parserClass = parserClass;
        this.url = url;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[%-20s]  :  %s",
            parserClass.getSimpleName(), message)
        );
        if (url != null) {
            sb.append(String.format("\t[%s]", url));
        }
        sb.append("\n\t-> Caused by: ");
        sb.append(this.getCause().getMessage());
        return sb.toString();
    }
}
