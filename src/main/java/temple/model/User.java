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
    private Integer id;
    private String name;
    private String password;
    private boolean enabled = true;

    private String confirmPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
