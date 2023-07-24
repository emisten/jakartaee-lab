package se.emisten.movieguy.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Iterator;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException e) {
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation constraintViolation : e.getConstraintViolations()) {
            String propertyName = getLast(constraintViolation.getPropertyPath());
            String message = constraintViolation.getMessage();
            sb.append(propertyName + ": " + message);
        }
        return Response.status(400).entity(sb.toString()).build();

    }

    public String getLast(Path path) {

        Path.Node lastElement = null;

        for (Iterator collectionItr = path.iterator(); collectionItr.hasNext(); ) {
            lastElement = (Path.Node) collectionItr.next();

        }
        return lastElement.getName();
    }
}
