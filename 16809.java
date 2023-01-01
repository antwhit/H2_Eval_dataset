import java.sql.ResultSet;
import java.sql.SQLException;
import mysql.Mysql;

/**
 * Class Game
 */
public class Game {

    private int id;

    private GameUser[] guser;

    private int guser_max;

    private int guser_idx;

    private int guser_time;

    private Map map;

    private String gname;

    private int status;

    public Game(Map map, String gname, User user, int color) {
        this.map = map;
        this.gname = gname;
        this.guser_max = 2;
        this.guser = new GameUser[this.guser_max];
        this.guser_idx = 0;
        this.guser_time = user.getId();
        this.status = 0;
        this.addUser(user, color);
    }

    ;

    public void play() {
        this.map.print();
    }

    public GameUser addUser(User user, int color) {
        Auxiliar aux = new Auxiliar();
        GameUser guser;
        if (this.guser_idx < this.guser_max) {
            guser = new GameUser();
            guser.setGame(this);
            guser.setUser(user);
            guser.setColor(color);
            this.guser[this.guser_idx] = guser;
        } else {
            return null;
        }
        this.guser[this.guser_idx].setGold(1000);
        if (this.guser_idx == 0) {
            aux.createComponent(user, this.map, "castle", 0, 0);
        } else {
            aux.createComponent(user, this.map, "castle", this.map.getSize() - 1, this.map.getSize() - 1);
        }
        this.guser_idx++;
        return guser;
    }

    public Map getMap() {
        return this.map;
    }

    public int getId() {
        return this.id;
    }

    public int getGuserIdx() {
        return this.guser_idx;
    }

    public void save() {
        Mysql bd = new Mysql();
        String strsql;
        strsql = "insert into tb_games (guser_max, guser_idx, guser_time, map_id, name, status) values " + "(" + this.guser_max + "," + this.guser_idx + "," + this.guser_time + "," + this.map.getId() + ",'" + this.gname + "'," + this.status + ")";
        ResultSet rs = bd.insertUpdate(strsql);
        try {
            rs.next();
            this.id = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < this.guser_idx; i++) {
            this.guser[i].save();
        }
    }
}
