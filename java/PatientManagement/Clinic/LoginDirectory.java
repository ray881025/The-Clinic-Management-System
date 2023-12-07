package PatientManagement.Clinic;


import PatientManagement.Patient.Patient;
import PatientManagement.Persona.Person;

import java.util.ArrayList;

public class LoginDirectory {
    ArrayList<LoginUser> loginuserlist;

    public LoginDirectory() {
        loginuserlist = new ArrayList<LoginUser>();
    }

    public LoginUser newLoginUser(String id, String password) {
        LoginUser p = new LoginUser(id, password);
        loginuserlist.add(p);
        return p;
    }

    public boolean findLoginInformation(String id,String passwords){
        for (LoginUser p: loginuserlist){
            if (p.idIsMatch(id) && p.passwordIsMatch(passwords)) {
                return true;
            }
        }
        return false;
    }
}
