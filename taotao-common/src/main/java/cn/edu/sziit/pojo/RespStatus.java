package cn.edu.sziit.pojo;

public class RespStatus {
    private int status;

    public RespStatus() {
    }

    public RespStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RespStatus{" +
                "status=" + status +
                '}';
    }
}
