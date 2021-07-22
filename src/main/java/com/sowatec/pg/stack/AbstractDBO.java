package main.java.com.sowatec.pg.stack;

public abstract class AbstractDBO {
    protected int id;
    protected Action action;

    public AbstractDBO(int id) {

    }



    public void setAction(Action action) {
        this.action = action;
    }

    public AbstractDBO() {

    }

    public Action getAction() {
        return action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public enum Action {
        INSERT
    }
}
