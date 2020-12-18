package tn.iit.medicalfile.web.rest.errors;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String message,Throwable cause)
    {
        super (message, cause);
    }
    public ResourceNotFoundException(String message)
    {
        super (message);
    }
    public ResourceNotFoundException(Throwable cause)
    {
        super (cause);
    }
}
