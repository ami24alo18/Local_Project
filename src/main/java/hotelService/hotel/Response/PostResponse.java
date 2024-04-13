package hotelService.hotel.Response;

public class PostResponse {

    private String message;
    private boolean success;

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public PostResponse(String message, boolean success) {
        this.success = success;
        this.message = message;
    }
}
