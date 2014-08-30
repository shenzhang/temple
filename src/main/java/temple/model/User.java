package temple.model;

import com.google.common.base.Objects;
import temple.sql.annotation.Table;

/**
 * User: shenzhang
 * Date: 8/19/14
 * Time: 10:56 PM
 */
@Table("T_USER")
public class User {
    private int id;
    private String name;
    private String password;

    private String confirmPassword;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(getClass())
                .add("name", getName())
                .add("password", getPassword())
                .add("confirmPassword", confirmPassword)
                .toString();
    }
}
