package tn.iit.medicalfile.web.rest.errors;

public class ErrorSM  extends Error{

    public ErrorSM(String message)
    {
        super (message);
    }


    public ErrorSM(String message, Throwable cause) {
        super (message, cause);
    }

    public ErrorSM(Throwable cause) {
        super (cause);
    }

}
