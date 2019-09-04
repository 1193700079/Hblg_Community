package life.hblg.community.model;

public class Notification {
    private Integer id;

    private Integer notifier;

    private Integer reciver;

    private Integer type;

    private Integer outerid;

    private Integer status;

    private Long gmtCreate;

    private Long gmtModify;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNotifier() {
        return notifier;
    }

    public void setNotifier(Integer notifier) {
        this.notifier = notifier;
    }

    public Integer getReciver() {
        return reciver;
    }

    public void setReciver(Integer reciver) {
        this.reciver = reciver;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOuterid() {
        return outerid;
    }

    public void setOuterid(Integer outerid) {
        this.outerid = outerid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Long gmtModify) {
        this.gmtModify = gmtModify;
    }
}