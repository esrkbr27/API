package Pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.junit.Test;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyRestApıResponsePojo {

    private String status;
    private String message;
    private  DummyRestApıDataPojo data;

    public DummyRestApıResponsePojo(String status, String message, DummyRestApıDataPojo data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public DummyRestApıResponsePojo() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DummyRestApıDataPojo getData() {
        return data;
    }

    public void setData(DummyRestApıDataPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DummyRestApıResponsePojo{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }


}
