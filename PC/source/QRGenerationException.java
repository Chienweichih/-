public class QRGenerationException extends RuntimeException
{
	private static final long serialVersionUID = -6536698481122322958L;

	public QRGenerationException(String message, Throwable underlyingException)
	{
        super(message, underlyingException);
    }
}
