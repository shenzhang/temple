package temple.model;

import com.google.common.base.Objects;

/**
 * User: shenzhang
 * Date: 8/24/14
 * Time: 10:17 PM
 */
public class NewUser extends User {
    private String confirmPassword;

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
