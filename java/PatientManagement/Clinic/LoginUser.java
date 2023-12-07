package PatientManagement.Clinic;

public class LoginUser {
    String id;
    String password;
    public LoginUser(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getLoginUserId() {
        return id;
    }

    public String getLoginUserPassword() {
        return password;
    }

    public boolean idIsMatch(String id) {
        if (getLoginUserId().equals(id))
            return true;
        return false;
    }

    public boolean passwordIsMatch(String password) {
        if (getLoginUserPassword().equals(password))
            return true;
        return false;
    }
}
